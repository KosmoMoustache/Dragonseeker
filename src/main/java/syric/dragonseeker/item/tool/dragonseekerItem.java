package syric.dragonseeker.item.tool;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.*;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import syric.dragonseeker.DragonSeekerConfig;
import syric.dragonseeker.Dragonseeker;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class DragonSeekerItem extends Item {

    private final int maxDistance;
    private final int minDistance;
    private final int randomSize;
    private final int precision;

    private final boolean detectCorpse;
    private final boolean detectCave;
    private final boolean detectRoost;
    private final boolean detectTamed;

    private final Item repairItem;
    private final SeekerType seekerType;

    private static final SoundEvent pingSuccess = SoundEvents.EXPERIENCE_ORB_PICKUP;
    private static final SoundEvent pingFailed = SoundEvents.NOTE_BLOCK_BASS;

    private static final boolean LegendaryTellPlayerDistance = DragonSeekerConfig.COMMON.LegendaryTellPlayerDistance.get();

    public DragonSeekerItem(DragonSeekerItemConfig config, Item repairItem, int durability, Rarity rarity) {
        this(config.maxDistance, config.minDistance, config.randomSize, config.detectCorpse, config.detectCave, config.detectRoost, config.detectTamed, config.precision, config.type, repairItem, durability, rarity);
    }

    public DragonSeekerItem(int maxDistance, int minDistance, int randomSize, boolean detectCorpse, boolean detectCave, boolean detectRoost, boolean detectTamed, int precision, SeekerType seekerType, Item repairItem, int durability, Rarity rarity) {
        super(new Properties().stacksTo(1).tab(IceAndFire.TAB_ITEMS).durability(durability).rarity(rarity));

        this.maxDistance = maxDistance;
        this.minDistance = minDistance;
        this.detectCorpse = detectCorpse;
        this.detectRoost = detectRoost;
        this.detectCave = detectCave;
        this.detectTamed = detectTamed;

        this.precision = precision;
        this.randomSize = randomSize;
        this.repairItem = repairItem;
        this.seekerType = seekerType;
    }

    public void appendHoverText(@NotNull ItemStack itemStack, @Nullable Level level, List<Component> componentList, @NotNull TooltipFlag tooltipFlag) {
        componentList.add((new TranslatableComponent("item.dragonseeker.tooltip.detection.range", this.minDistance, this.maxDistance)));
        componentList.add(new TranslatableComponent("item.dragonseeker.tooltip.detection.detect"));
        if (this.detectCorpse)
            componentList.add((new TranslatableComponent("item.dragonseeker.tooltip.detection.corpse")));
        if (this.detectCave) componentList.add(new TranslatableComponent("item.dragonseeker.tooltip.detection.cave"));
        if (this.detectRoost)
            componentList.add(new TranslatableComponent("item.dragonseeker.tooltip.detection.roost"));
        if (this.detectTamed)
            componentList.add(new TranslatableComponent("item.dragonseeker.tooltip.detection.tame"));
        componentList.add(new TranslatableComponent("item.dragonseeker.tooltip.detection.precision", String.valueOf(100 - precision)));
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack toRepair, ItemStack repairWith) {
        return repairWith.getItem() == repairItem;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            TreeMap<Float, EntityDragonBase> nearestDragons = getNearestDragon(level, player, this.maxDistance);

            // Show message in chat if Goldy Seeker
            if (this.seekerType == SeekerType.Godly) {
                BaseComponent component = new TranslatableComponent("chat.dragonseeker.godly_message_header");
                component.append("\n");

                if (!nearestDragons.values().isEmpty()) {
                    nearestDragons.forEach((distance, dragon) -> {
                        component.append(new TranslatableComponent("chat.dragonseeker.godly_message.body", Math.round(dragon.distanceTo(player)), ComponentUtils.wrapInSquareBrackets(new TranslatableComponent("chat.coordinates", Math.round(dragon.getX()), Math.round(dragon.getY()), Math.round(dragon.getZ()))).withStyle((style) -> style.withColor(ChatFormatting.GREEN).withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp @s " + Math.round(dragon.getX()) + " " + Math.round(dragon.getY()) + " " + Math.round(dragon.getZ()))).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TranslatableComponent("chat.coordinates.tooltip")))), getDragonLocation(dragon, level).toString(), getDragonState(dragon).toString()));
                        component.append("\n");
                    });
                } else {
                    component.append(new TranslatableComponent("chat.dragonseeker.godly_message.no_dragons"));
                }
                player.displayClientMessage(component, false);
            } else {
                player.getCooldowns().addCooldown(this, 10);
            }

            EntityDragonBase nearestDragon = null;
            float distanceTo = -1; // if nearestDragon is null, distanceTo will always be -1
            if (!nearestDragons.values().isEmpty()) {
                nearestDragon = nearestDragons.values().iterator().next();
                distanceTo = nearestDragon.distanceTo(player);
                if (distanceTo < this.minDistance) {
                    distanceTo = this.minDistance;
                }
            }

            // ActionBar title for Legendary Seeker
            if (LegendaryTellPlayerDistance && this.seekerType == SeekerType.Legendary && distanceTo != -1) {
                BaseComponent component = new TranslatableComponent("chat.dragonseeker.legendary_message", String.valueOf(distanceTo));
                player.displayClientMessage(component, true);
            }

            // Improve the fake ping system
            if (getNoise(player) < this.precision || distanceTo == -1) {
                player.playNotifySound(pingFailed, SoundSource.PLAYERS, 100, 1.0F);
            } else {
                float yaw = (float) getYawToLookBlockPos(player, new BlockPos(nearestDragon.getX(), nearestDragon.getY(), nearestDragon.getZ()));
//                float pitch = getPitchBasedOnYawDifference(player.getYRot(), yaw);
                float pitch = 1.0f;
                player.playNotifySound(pingSuccess, SoundSource.PLAYERS, 100 - (distanceTo / this.maxDistance) * 100, pitch);

                Dragonseeker.LOGGER.debug("yaw: {}, pitch: {}", yaw, pitch);

            }
            Dragonseeker.LOGGER.debug("distanceTo: {}, pingVolume: {}, Noise: {}", distanceTo, 100 - (distanceTo / this.maxDistance) * 100, getNoise(player));

            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        }
        return InteractionResultHolder.fail(itemstack);
    }

    private double getNoise(Player player) {
        double X = Math.round(player.getX());
        double Z = Math.round(player.getZ());

        // Calculate the random within the block size
        double rX = X / this.randomSize;
        double rZ = Z / this.randomSize;

        long seed = ((long) rX | (long) rZ) * player.getUUID().hashCode();

        Random random1 = new Random(seed);
        return random1.nextDouble(100);
    }

    private TreeMap<Float, EntityDragonBase> getNearestDragon(Level world, Player player, int range) {
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        AABB box = new AABB(x - (double) range / 2, y - (double) range / 2, z - (double) range / 2, x + (double) range / 2, y + (double) range / 2, z + (double) range / 2);
        List<EntityDragonBase> listOfTargets = world.getEntitiesOfClass(EntityDragonBase.class, box);


        float closestDistance = -1;
        TreeMap<Float, EntityDragonBase> closest = new TreeMap<>(Comparator.naturalOrder());
        for (EntityDragonBase target : listOfTargets) {
            float distanceTarget = target.distanceTo(player);

            if (closestDistance == -1 || distanceTarget < closestDistance) {
                if (this.detectCave && getDragonLocation(target, world) == DragonLocation.Cave) {
                    closestDistance = distanceTarget;
                    closest.put(distanceTarget, target);
                }
                if (this.detectRoost && getDragonLocation(target, world) == DragonLocation.Roost) {
                    closestDistance = distanceTarget;
                    closest.put(distanceTarget, target);
                }

                if (this.detectCorpse && getDragonState(target) == DragonState.Dead) {
                    closestDistance = distanceTarget;
                    closest.put(distanceTarget, target);
                }
                if (this.detectTamed && getDragonState(target) == DragonState.Tamed) {
                    closestDistance = distanceTarget;
                    closest.put(distanceTarget, target);
                }
            }
        }

        return closest;
    }

    // TODO: Fix
    private float getPitchBasedOnYawDifference(float pYaw, float tYaw) {
        float minPitch = 0.0f;
        float maxPitch = 2.0f;

        float diff = Math.abs(pYaw) - tYaw;
        if (diff > 180) {
            diff -= 180;
        }

        int minAngle = 100;
        int maxAngle = 10;

        if (diff > minAngle) {
            return minPitch;
        }
        if (diff < maxAngle) {
            return maxPitch;
        }

        float ratio = (diff - minAngle) / (maxAngle - minAngle);
        return (1 - ratio) * minPitch + ratio * maxPitch;
    }


    /**
     * Return the yaw needed to look at the BlockPos
     */
    private static double getYawToLookBlockPos(Player player, BlockPos pos) {
        double dx = (pos.getX() + 0.5) - player.getX();
        double dz = (pos.getZ() + 0.5) - player.getZ();
        return Math.toDegrees(Math.atan2(dz, dx)) - 90;
    }

    private DragonState getDragonState(EntityDragonBase target) {
        if (target.isModelDead()) {
            return DragonState.Dead;
        }
        if (target.isTame()) {
            return DragonState.Tamed;
        }
        return DragonState.Wild;
    }

    private DragonLocation getDragonLocation(EntityDragonBase target, Level level) {
        if (!level.canSeeSky(new BlockPos(target.getX(), target.getY(), target.getZ()))) {
            return DragonLocation.Cave;
        }
        if (level.canSeeSky(new BlockPos(target.getX(), target.getY(), target.getZ()))) {
            return DragonLocation.Roost;
        }
        throw new IllegalStateException("Unexpected value: " + target);
    }

    public enum SeekerType {
        Common, Epic, Legendary, Godly
    }

    public enum DragonState {
        Dead("Dead"),
        Tamed("Tamed"),
        Wild("Wild");

        DragonState(String state) {
        }

    }

    public enum DragonLocation {
        Roost("Roost"),
        Cave("Cave");

        DragonLocation(String location) {
        }
    }
}
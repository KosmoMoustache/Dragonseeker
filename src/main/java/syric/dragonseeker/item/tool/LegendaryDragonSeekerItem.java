package syric.dragonseeker.item.tool;

import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.NotNull;

public class LegendaryDragonSeekerItem extends DragonSeekerItem {
    public LegendaryDragonSeekerItem(DragonSeekerItemConfig config, Item repairItem, int durability, Rarity rarity) {
        super(config, repairItem, durability, rarity);
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack toRepair, ItemStack repairWith) {
        return ((repairWith.getItem() == IafItemRegistry.DRAGONSTEEL_FIRE_INGOT.get()) || (repairWith.getItem() == IafItemRegistry.DRAGONSTEEL_ICE_INGOT.get()) || (repairWith.getItem() == IafItemRegistry.DRAGONSTEEL_LIGHTNING_INGOT.get()));
    }
}

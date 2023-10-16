package syric.dragonseeker.registry;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import syric.dragonseeker.Dragonseeker;
import syric.dragonseeker.item.tool.*;

public class DSItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dragonseeker.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> DRAGONSEEKER = ITEMS.register("dragonseeker", () -> new DragonSeekerItem(DragonSeekerItemConfig.get(DragonSeekerItem.SeekerType.Common), Items.NETHERITE_INGOT, 128, Rarity.COMMON));
    public static final RegistryObject<Item> EPIC_DRAGONSEEKER = ITEMS.register("epic_dragonseeker", () -> new DragonSeekerItem(DragonSeekerItemConfig.get(DragonSeekerItem.SeekerType.Epic), Items.NETHERITE_INGOT, 256, Rarity.RARE));
    public static final RegistryObject<Item> LEGENDARY_DRAGONSEEKER = ITEMS.register("legendary_dragonseeker", () -> new LegendaryDragonSeekerItem(DragonSeekerItemConfig.get(DragonSeekerItem.SeekerType.Legendary), Items.NETHERITE_INGOT, 512, Rarity.RARE));
    public static final RegistryObject<Item> GODLY_DRAGONSEEKER = ITEMS.register("godly_dragonseeker", () -> new DragonSeekerItem(DragonSeekerItemConfig.get(DragonSeekerItem.SeekerType.Godly), Items.NETHERITE_INGOT, -1, Rarity.EPIC));
    public static final RegistryObject<Item> MUSIC_DISC_01 = ITEMS.register("music_disc_01", () -> new RecordItem(4, DSSounds.MUSIC_DISC_01, new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1)));
}

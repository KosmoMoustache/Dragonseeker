package syric.dragonseeker;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import syric.dragonseeker.registry.DSItems;
import syric.dragonseeker.registry.DSSounds;

@Mod(Dragonseeker.MOD_ID)
public class Dragonseeker {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "dragonseeker";

    public Dragonseeker() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DragonSeekerConfig.COMMON_SPEC);

        DSItems.register(eventBus);
        DSSounds.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}

package syric.dragonseeker;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class DragonSeekerConfig {

    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Boolean> FixBannerResize;
        public final ForgeConfigSpec.ConfigValue<Boolean> LegendaryTellPlayerDistance;

        //Basic Dragonseeker
        public final ForgeConfigSpec.ConfigValue<Integer> Basic_MaxDistance;
        public final ForgeConfigSpec.ConfigValue<Integer> Basic_MinDistance;
        public final ForgeConfigSpec.ConfigValue<Integer> Basic_RandomSize;
        public final ForgeConfigSpec.ConfigValue<Integer> Basic_Precision;
        public final ForgeConfigSpec.ConfigValue<Boolean> Basic_CanDetectDeadDragon;
        public final ForgeConfigSpec.ConfigValue<Boolean> Basic_CanDetectCavedDragon;
        public final ForgeConfigSpec.ConfigValue<Boolean> Basic_CanDetectRoostDragon;
        public final ForgeConfigSpec.ConfigValue<Boolean> Basic_CanDetectTamedDragon;

        //Epic Dragonseeker
        public final ForgeConfigSpec.ConfigValue<Integer> Epic_MaxDistance;
        public final ForgeConfigSpec.ConfigValue<Integer> Epic_MinDistance;
        public final ForgeConfigSpec.ConfigValue<Integer> Epic_RandomSize;
        public final ForgeConfigSpec.ConfigValue<Integer> Epic_Precision;
        public final ForgeConfigSpec.ConfigValue<Boolean> Epic_CanDetectDeadDragon;
        public final ForgeConfigSpec.ConfigValue<Boolean> Epic_CanDetectCavedDragon;
        public final ForgeConfigSpec.ConfigValue<Boolean> Epic_CanDetectRoostDragon;
        public final ForgeConfigSpec.ConfigValue<Boolean> Epic_CanDetectTamedDragon;

        //Legendary Dragonseeker
        public final ForgeConfigSpec.ConfigValue<Integer> Legendary_MaxDistance;
        public final ForgeConfigSpec.ConfigValue<Integer> Legendary_MinDistance;
        public final ForgeConfigSpec.ConfigValue<Integer> Legendary_RandomSize;
        public final ForgeConfigSpec.ConfigValue<Integer> Legendary_Precision;
        public final ForgeConfigSpec.ConfigValue<Boolean> Legendary_CanDetectDeadDragon;
        public final ForgeConfigSpec.ConfigValue<Boolean> Legendary_CanDetectCavedDragon;
        public final ForgeConfigSpec.ConfigValue<Boolean> Legendary_CanDetectRoostDragon;
        public final ForgeConfigSpec.ConfigValue<Boolean> Legendary_CanDetectTamedDragon;

        //Godly Dragonseeker
        public final ForgeConfigSpec.ConfigValue<Integer> Godly_MaxDistance;
        public final ForgeConfigSpec.ConfigValue<Integer> Godly_MinDistance;
        public final ForgeConfigSpec.ConfigValue<Integer> Godly_RandomSize;
        public final ForgeConfigSpec.ConfigValue<Integer> Godly_Precision;
        public final ForgeConfigSpec.ConfigValue<Boolean> Godly_CanDetectDeadDragon;
        public final ForgeConfigSpec.ConfigValue<Boolean> Godly_CanDetectCavedDragon;
        public final ForgeConfigSpec.ConfigValue<Boolean> Godly_CanDetectRoostDragon;
        public final ForgeConfigSpec.ConfigValue<Boolean> Godly_CanDetectTamedDragon;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Basic Dragonseeker").push("basic_dragonseeker");

            FixBannerResize = builder
                    .comment("Enable the banner resize. Default: true")
                    .define("FixBannerResize", true);
            LegendaryTellPlayerDistance = builder
                    .comment("When enable Legendary DragonSeeker will tell player the distance to the dragon. Default: false")
                    .define("LegendaryTellPlayerDistance", false);

            Basic_MaxDistance = builder
                    .comment("Maximum distance at which a dragon will be detected. Default: 200")
                    .defineInRange("Basic: MaxDistance", 200, 1, 500);
            Basic_MinDistance = builder
                    .comment("Minimum distance at which a dragon will be detected. Within this distance ping volume will not increase. Should not be larger than MaxDistance. Default: 50")
                    .defineInRange("Basic: MinDistance", 50, 1, 500);
            Basic_RandomSize = builder
                    .comment("WIP How often fake dragon ping will appear. Default: 25")
                    .defineInRange("Basic: RandomSize", 25, 1, 100);
            Basic_Precision = builder
                    .comment("WIP How often fake dragon ping will appear. Default: 25")
                    .defineInRange("Basic: RandomSize", 25, 1, 100);
            Basic_CanDetectDeadDragon = builder
                    .comment("Whether or not the dragonseeker detects dead dragons. Default: true")
                    .define("Basic: CanDetectDeadDragon", true);
            Basic_CanDetectCavedDragon = builder
                    .comment("Whether or not the dragonseeker detects caved dragons. Default: true")
                    .define("Basic: CanDetectCavedDragon", true);
            Basic_CanDetectRoostDragon = builder
                    .comment("Whether or not the dragonseeker detects roosted dragons. Default: true")
                    .define("Basic: CanDetectRoostDragon", true);
            Basic_CanDetectTamedDragon = builder
                    .comment("Whether or not this dragonseeker detects tamed dragons. Default: true")
                    .define("Basic: CanDetectTamedDragon", true);

            builder.comment("Epic Dragonseeker").push("epic_dragonseeker");
            Epic_MaxDistance = builder
                    .comment("Maximum distance at which a dragon will be detected. Default: 200")
                    .defineInRange("Epic: MaxDistance", 200, 1, 500);
            Epic_MinDistance = builder
                    .comment("Minimum distance at which a dragon will be detected. Within this distance ping volume will not increase. Should not be larger than MaxDistance. Default: 50")
                    .defineInRange("Epic: MinDistance", 50, 1, 500);
            Epic_RandomSize = builder
                    .comment("WIP How often fake dragon ping will appear. Default: 10")
                    .defineInRange("Epic: RandomSize", 10, 1, 100);
            Epic_Precision = builder
                    .comment("WIP How often fake dragon ping will appear. Default: 10")
                    .defineInRange("Epic: RandomSize", 10, 1, 100);
            Epic_CanDetectDeadDragon = builder
                    .comment("Whether or not the dragonseeker detects dead dragons. Default: true")
                    .define("Epic: CanDetectDeadDragon", true);
            Epic_CanDetectCavedDragon = builder
                    .comment("Whether or not the dragonseeker detects caved dragons. Default: true")
                    .define("Epic: CanDetectCavedDragon", true);
            Epic_CanDetectRoostDragon = builder
                    .comment("Whether or not the dragonseeker detects roosting dragons. Default: true")
                    .define("Epic: CanDetectRoostDragon", true);
            Epic_CanDetectTamedDragon = builder
                    .comment("Whether or not this dragonseeker detects tamed dragons. Default: false")
                    .define("Epic: CanDetectTamedDragon", false);

            builder.comment("Legendary Dragonseeker").push("legendary_dragonseeker");
            Legendary_MaxDistance = builder
                    .comment("Maximum distance at which a dragon will be detected. Default: 250")
                    .defineInRange("Legendary: MaxDistance", 250, 1, 500);
            Legendary_MinDistance = builder
                    .comment("Minimum distance at which a dragon will be detected. Within this distance ping volume will not increase. Should not be larger than MaxDistance. Default: 50")
                    .defineInRange("Legendary: MinDistance", 50, 1, 500);
            Legendary_RandomSize = builder
                    .comment("WIP RandomSize of the ping chance. The higher this is, the more random the ping chance will be. Default: 0")
                    .defineInRange("Legendary: Randomness", 0, 1, 100);
            Legendary_Precision = builder
                    .comment("WIP Precision error of the ping. The higher this is, the less precise the ping will be. Default: 0")
                    .defineInRange("Legendary: PrecisionError", 1, 1, 100);
            Legendary_CanDetectDeadDragon = builder
                    .comment("Whether or not the dragonseeker detects dead dragons. Default: false")
                    .define("Legendary: CanDetectDeadDragon", false);
            Legendary_CanDetectCavedDragon = builder
                    .comment("Whether or not the dragonseeker detects caved dragons. Default: true")
                    .define("Legendary: CanDetectCavedDragon", true);
            Legendary_CanDetectRoostDragon = builder
                    .comment("Whether or not the dragonseeker detects roosting dragons. Default: true")
                    .define("Legendary: CanDetectRoostDragon", true);
            Legendary_CanDetectTamedDragon = builder
                    .comment("Whether or not this dragonseeker detects tamed dragons. Default: false")
                    .define("Legendary: IgnoreTamedDragon", false);

            builder.comment("Godly Dragonseeker").push("mythic_dragonseeker");
            Godly_MaxDistance = builder
                    .comment("Maximum distance at which a dragon will be detected. Default: 500")
                    .defineInRange("Godly: MaxDistance", 500, 1, 500);
            Godly_MinDistance = builder
                    .comment("Minimum distance at which a dragon will be detected. Within this distance ping volume will not increase. Should not be larger than MaxDistance. Default: 0")
                    .defineInRange("Godly: MinDistance", 0, 1, 500);
            Godly_RandomSize = builder
                    .comment("WIP RandomSize of the ping chance. The higher this is, the more random the ping chance will be. Default: 0")
                    .defineInRange("Godly: Randomness", 0, 1, 100);
            Godly_Precision = builder
                    .comment("WIP Precision error of the ping. The higher this is, the less precise the ping will be. Default: 0")
                    .defineInRange("Godly: PrecisionError", 1, 1, 100);
            Godly_CanDetectDeadDragon = builder
                    .comment("Whether or not the dragonseeker detects dead dragons. Default: true")
                    .define("Godly: CanDetectDeadDragon", true);
            Godly_CanDetectCavedDragon = builder
                    .comment("Whether or not the dragonseeker detects caved dragons. Default: true")
                    .define("Godly: CanDetectCavedDragon", true);
            Godly_CanDetectRoostDragon = builder
                    .comment("Whether or not the dragonseeker detects roosting dragons. Default: true")
                    .define("Godly: CanDetectRoostDragon", true);
            Godly_CanDetectTamedDragon = builder
                    .comment("Whether or not this dragonseeker detects tamed dragons. Default: true")
                    .define("Godly: IgnoreTamedDragon", true);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}
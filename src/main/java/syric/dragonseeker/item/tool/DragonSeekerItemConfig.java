package syric.dragonseeker.item.tool;

import syric.dragonseeker.DragonSeekerConfig;

public class DragonSeekerItemConfig {
    public final int minDistance;
    public final int maxDistance;
    public final int randomSize;
    public final int precision;
    public final boolean detectCorpse;
    public final boolean detectCave;
    public final boolean detectRoost;
    public final boolean detectTamed;
    public final DragonSeekerItem.SeekerType type;

    public DragonSeekerItemConfig(int maxDistance, int minDistance, int randomSize, int precision, boolean detectCorpse, boolean detectCave, boolean detectRoost, boolean detectTamed, DragonSeekerItem.SeekerType type) {
        this.maxDistance = maxDistance;
        this.minDistance = minDistance;
        this.randomSize = randomSize;
        this.precision = precision;
        this.detectCorpse = detectCorpse;
        this.detectCave = detectCave;
        this.detectRoost = detectRoost;
        this.detectTamed = detectTamed;
        this.type = type;
    }

    public static DragonSeekerItemConfig get(DragonSeekerItem.SeekerType type) {
        switch (type) {
            case Common -> {
                return new DragonSeekerItemConfig(
                        DragonSeekerConfig.COMMON.Basic_MaxDistance.get(),
                        DragonSeekerConfig.COMMON.Basic_MinDistance.get(),
                        DragonSeekerConfig.COMMON.Basic_RandomSize.get(),
                        DragonSeekerConfig.COMMON.Basic_Precision.get(),
                        DragonSeekerConfig.COMMON.Basic_CanDetectDeadDragon.get(),
                        DragonSeekerConfig.COMMON.Basic_CanDetectCavedDragon.get(),
                        DragonSeekerConfig.COMMON.Basic_CanDetectRoostDragon.get(),
                        DragonSeekerConfig.COMMON.Basic_CanDetectTamedDragon.get(),
                        DragonSeekerItem.SeekerType.Common
                );
            }
            case Epic -> {
                return new DragonSeekerItemConfig(
                        DragonSeekerConfig.COMMON.Epic_MaxDistance.get(),
                        DragonSeekerConfig.COMMON.Epic_MinDistance.get(),
                        DragonSeekerConfig.COMMON.Epic_RandomSize.get(),
                        DragonSeekerConfig.COMMON.Epic_Precision.get(),
                        DragonSeekerConfig.COMMON.Epic_CanDetectDeadDragon.get(),
                        DragonSeekerConfig.COMMON.Epic_CanDetectCavedDragon.get(),
                        DragonSeekerConfig.COMMON.Epic_CanDetectRoostDragon.get(),
                        DragonSeekerConfig.COMMON.Epic_CanDetectTamedDragon.get(),
                        DragonSeekerItem.SeekerType.Epic
                );
            }
            case Legendary -> {
                return new DragonSeekerItemConfig(
                        DragonSeekerConfig.COMMON.Legendary_MaxDistance.get(),
                        DragonSeekerConfig.COMMON.Legendary_MinDistance.get(),
                        DragonSeekerConfig.COMMON.Legendary_RandomSize.get(),
                        DragonSeekerConfig.COMMON.Legendary_Precision.get(),
                        DragonSeekerConfig.COMMON.Legendary_CanDetectDeadDragon.get(),
                        DragonSeekerConfig.COMMON.Legendary_CanDetectCavedDragon.get(),
                        DragonSeekerConfig.COMMON.Legendary_CanDetectRoostDragon.get(),
                        DragonSeekerConfig.COMMON.Legendary_CanDetectTamedDragon.get(),
                        DragonSeekerItem.SeekerType.Legendary
                );
            }
            case Godly -> {
                return new DragonSeekerItemConfig(
                        DragonSeekerConfig.COMMON.Godly_MaxDistance.get(),
                        DragonSeekerConfig.COMMON.Godly_MinDistance.get(),
                        DragonSeekerConfig.COMMON.Godly_RandomSize.get(),
                        DragonSeekerConfig.COMMON.Godly_Precision.get(),
                        DragonSeekerConfig.COMMON.Godly_CanDetectDeadDragon.get(),
                        DragonSeekerConfig.COMMON.Godly_CanDetectCavedDragon.get(),
                        DragonSeekerConfig.COMMON.Godly_CanDetectRoostDragon.get(),
                        DragonSeekerConfig.COMMON.Godly_CanDetectTamedDragon.get(),
                        DragonSeekerItem.SeekerType.Godly
                );
            }
            default -> {
                return new DragonSeekerItemConfig(
                        0,
                        0,
                        0,
                        0,
                        false,
                        false,
                        false,
                        false,
                        DragonSeekerItem.SeekerType.Common
                );
            }
        }
    }
}

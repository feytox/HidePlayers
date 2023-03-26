package ru.feytox.toomanyplayers;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

public class HidingAreas {

    public static boolean isArmorHiding(Entity entity) {
        return isSecondArea(entity) && TMPConfig.areasHideArmor;
    }

    public static boolean is2ndLayerHiding(Entity entity) {
        return isSecondArea(entity) && TMPConfig.areasHide2ndLayer;
    }

    public static boolean isOnlyHeadsShown(Entity entity) {
        return isThirdArea(entity) && TMPConfig.areasShowOnlyHeads;
    }

    public static boolean isHeldItemsHiding(Entity entity) {
        return isThirdArea(entity) && TMPConfig.areasHideHeldItems;
    }

    public static boolean isPlayersHiding(Entity entity) {
        return isOtherArea(entity) && TMPConfig.areasHidePlayers;
    }

    private static int getArea(Entity entity) {
        double distance = MinecraftClient.getInstance().player.squaredDistanceTo(entity);
        if (distance <= Math.pow(TMPConfig.firstRadius, 2)) {
            return 1;
        } else if (distance <= Math.pow(TMPConfig.secondRadius, 2)) {
            return 2;
        } else if (distance <= Math.pow(TMPConfig.thirdRadius, 2)) {
            return 3;
        }
        return 4;
    }

    private static boolean isFirstArea(Entity entity) {
        return getArea(entity) == 1 && TMPConfig.toggleAreas;
    }
    private static boolean isSecondArea(Entity entity) {
        return getArea(entity) >= 2 && TMPConfig.toggleAreas;
    }
    private static boolean isThirdArea(Entity entity) {
        return getArea(entity) >= 3 && TMPConfig.toggleAreas;
    }
    private static boolean isOtherArea(Entity entity) {
        return getArea(entity) == 4 && TMPConfig.toggleAreas;
    }
}

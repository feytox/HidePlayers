package name.uwu.feytox.toomanyplayers;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

public class HidingAreas {

    public static int getArea(Entity entity) {
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

    public static boolean isFirstArea(Entity entity) {
        return getArea(entity) == 1 && TMPConfig.toggleAreas;
    }
    public static boolean isSecondArea(Entity entity) {
        return getArea(entity) >= 2 && TMPConfig.toggleAreas;
    }
    public static boolean isThirdArea(Entity entity) {
        return getArea(entity) >= 3 && TMPConfig.toggleAreas;
    }
    public static boolean isOtherArea(Entity entity) {
        return getArea(entity) == 4 && TMPConfig.toggleAreas;
    }
}

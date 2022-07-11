package name.uwu.feytox.toomanyplayers;

import eu.midnightdust.lib.config.MidnightConfig;

import java.util.ArrayList;
import java.util.List;

public class TMPConfig extends MidnightConfig {
    @Entry
    public static boolean toggleMod = false;

    @Entry
    public static savingTypes saveToMemory = savingTypes.ALL;
    public enum savingTypes {
        NOT_TOGGLEMOD, ALL
    }

    @Comment
    public static Comment areas;

    @Entry
    public static boolean toggleAreas = true;

    @Comment
    public static Comment firstArea;
    @Entry(min=0)
    public static double firstRadius = 10;

    @Comment
    public static Comment secondArea;
    @Entry(min=0)
    public static double secondRadius = 20;
    @Entry
    public static boolean areasHideArmor = true;
    @Entry
    public static boolean areasHide2ndLayer = true;

    @Comment
    public static Comment thirdArea;
    @Entry(min=0)
    public static double thirdRadius = 30;
    @Entry
    public static boolean areasShowOnlyHeads = true;
    @Entry
    public static boolean areasHideHeldItems = true;

    @Comment
    public static Comment otherArea;
    @Entry
    public static boolean areasHidePlayers = true;

    @Comment
    public static Comment lists;

    @Entry
    public static List<String> whitelist = new ArrayList<>();

    @Entry
    public static List<String> blocklist = new ArrayList<>();

    @Entry
    public static BlockingType blockingType = BlockingType.ALL;

    public enum BlockingType {
        KEEP_HEADS, ALL
    }

    @Entry
    public static List<String> hideskinlist = new ArrayList<>();

    @Comment
    public static Comment mainSettings;

    @Entry
    public static boolean hidePlayers = false;

    @Entry(min=0)
    public static double hidePlayersdistance = 10;

    @Entry
    public static boolean showOnlyHeads = true;

    @Entry
    public static boolean hideArmor = false;

    @Entry
    public static boolean hideHeldItems = false;

    @Entry
    public static boolean hideGlint = true;

    @Entry
    public static boolean hide2ndLayer = true;

    public static void save() {
        TMPConfig.write(TooManyPlayers.MOD_ID);
    }

    public static void init() {
        MidnightConfig.init("toomanyplayers", TMPConfig.class);
        if (saveToMemory.equals(savingTypes.NOT_TOGGLEMOD)) {
            toggleMod = false;
        }
    }
}

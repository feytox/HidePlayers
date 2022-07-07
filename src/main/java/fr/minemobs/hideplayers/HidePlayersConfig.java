package fr.minemobs.hideplayers;

import eu.midnightdust.lib.config.MidnightConfig;

public class HidePlayersConfig extends MidnightConfig {

    @Entry
    public static boolean hidePlayers = false;

    @Entry(min=0)
    public static double hidePlayersdistance = 10;

    @Entry
    public static boolean showOnlyHeads = false;

    @Entry
    public static boolean hideArmor = false;

    @Entry
    public static boolean hideHeldItems = false;

    @Entry
    public static boolean hideGlint = false;

    @Entry
    public static boolean hide2ndLayer = false;

    @Entry
    public static savingTypes saveToMemory = savingTypes.ONLY_NUMS;
    public enum savingTypes {
        ONLY_NUMS, ALL
    }

    public static void save() {
        HidePlayersConfig.write(HidePlayers.MOD_ID);
    }

    public static void init() {
        MidnightConfig.init(HidePlayers.MOD_ID, HidePlayersConfig.class);
        if (saveToMemory.equals(savingTypes.ONLY_NUMS)) {
            hidePlayers = false;
            hideArmor = false;
            showOnlyHeads = false;
            hideHeldItems = false;
            hideGlint = false;
        }
    }
}

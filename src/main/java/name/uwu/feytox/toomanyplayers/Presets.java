package name.uwu.feytox.toomanyplayers;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Presets {

    public static List<String> getPresets(boolean withDefault) {
        String[] configs = FabricLoader.getInstance().getConfigDir().toFile().list((dir, name) ->
                name.toLowerCase().startsWith("tmp_"));
        List<String> result = new ArrayList<>();
        if (configs != null) {
            for (String configName : configs) {
                result.add(configName.replace("tmp_", "").replace(".json", ""));
            }
        }
        if (withDefault) {
            result.addAll(Arrays.asList("default", "default2", "SP"));
        }

        return result;
    }

    public static void createPreset(String presetName) {
        Path config = FabricLoader.getInstance().getConfigDir().resolve("toomanyplayers.json");
        Path preset = FabricLoader.getInstance().getConfigDir().resolve("tmp_" + presetName + ".json");

        try {
            Files.copy(config, preset, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setPreset(String presetName) {
        List<String> presets = getPresets(false);
        for (String preset:
             presets) {
            if (preset.equals(presetName)) {
                replaceCfgFile(preset);
                TMPConfig.init();
                return;
            }
        }

        switch (presetName) {
            case "default" -> defaultCfg();
            case "default2" -> default2Cfg();
            case "SP" -> spCfg();
        }
        TMPConfig.save();
    }

    public static boolean removePreset(String presetName) {
        File preset = FabricLoader.getInstance().getConfigDir().resolve("tmp_" + presetName + ".json").toFile();
        return preset.delete();
    }

    // cfg by Feytox
    private static void defaultCfg() {
        TMPConfig.toggleMod = false;
        TMPConfig.saveToMemory = TMPConfig.savingTypes.ALL;
        TMPConfig.toggleAreas = true;
        TMPConfig.firstRadius = 5;
        TMPConfig.secondRadius = 10;
        TMPConfig.areasHideArmor = true;
        TMPConfig.areasHide2ndLayer = false;
        TMPConfig.thirdRadius = 15;
        TMPConfig.areasShowOnlyHeads = true;
        TMPConfig.areasHideHeldItems = true;
        TMPConfig.areasHidePlayers = true;
        TMPConfig.whitelist = new ArrayList<>();
        TMPConfig.blocklist = new ArrayList<>();
        TMPConfig.blockingType = TMPConfig.BlockingType.ALL;
        TMPConfig.hideskinlist = new ArrayList<>();
        TMPConfig.hidePlayers = false;
        TMPConfig.hidePlayersdistance = 10;
        TMPConfig.showOnlyHeads = false;
        TMPConfig.hideArmor = false;
        TMPConfig.hideHeldItems = false;
        TMPConfig.hideGlint = false;
        TMPConfig.hide2ndLayer = false;
        TMPConfig.toggleOnlineWhitelist = false;
        TMPConfig.onlineWhitelistUrl = "http://tmp-sp-whitelist.uwu.name";
    }

    // cfg by dearfox
    private static void default2Cfg() {
        defaultCfg();
        TMPConfig.toggleMod = true;
        TMPConfig.secondRadius = 7;
        TMPConfig.thirdRadius = 30;
        TMPConfig.areasHideHeldItems = false;
        TMPConfig.areasHidePlayers = false;
        TMPConfig.blockingType = TMPConfig.BlockingType.KEEP_HEADS;
    }

    // cfg by Feytox
    private static void spCfg() {
        defaultCfg();
        TMPConfig.toggleOnlineWhitelist = true;
        OnlineWhitelist.reloadWhitelist();
    }

    private static void replaceCfgFile(String preset) {
        Path new_config = FabricLoader.getInstance().getConfigDir().resolve("tmp_" + preset + ".json");
        Path old_config = FabricLoader.getInstance().getConfigDir().resolve("toomanyplayers.json");
        try {
            Files.copy(new_config, old_config, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

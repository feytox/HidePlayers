package fr.minemobs.hideplayers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.lwjgl.glfw.GLFW;

public class HidePlayers implements ModInitializer {
    public static final String MOD_ID = "hideplayers";

    @Override
    public void onInitialize() {
        HidePlayersConfig.init();

        KeyBinding hidePlayersKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + MOD_ID + ".hideplayers",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_UNKNOWN, MOD_ID + ".midnightconfig.title"
        ));
        KeyBinding showOnlyHeadsKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + MOD_ID + ".showOnlyHeads",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_UNKNOWN, MOD_ID + ".midnightconfig.title"
                ));

        KeyBinding hideArmorKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + MOD_ID + ".hideArmor",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_UNKNOWN, MOD_ID + ".midnightconfig.title"
                ));

        KeyBinding hideHeldItemsKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + MOD_ID + ".hideHeldItems",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_UNKNOWN, MOD_ID + ".midnightconfig.title"
                ));

        KeyBinding hideGlintKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + MOD_ID + ".showGlint",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_UNKNOWN, MOD_ID + ".midnightconfig.title"
                ));

        KeyBinding hide2ndLayerKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + MOD_ID + ".hide2ndLayer",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_UNKNOWN, MOD_ID + ".midnightconfig.title"
                ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(hidePlayersKey.wasPressed()) {
                HidePlayersConfig.hidePlayers = !HidePlayersConfig.hidePlayers;
                HidePlayersConfig.save();
                showToggleStatus("hidePlayers", HidePlayersConfig.hidePlayers);
            }
            while(showOnlyHeadsKey.wasPressed()) {
                HidePlayersConfig.showOnlyHeads = !HidePlayersConfig.showOnlyHeads;
                HidePlayersConfig.save();
                showToggleStatus("showOnlyHeads", HidePlayersConfig.showOnlyHeads);
            }
            while(hideArmorKey.wasPressed()) {
                HidePlayersConfig.hideArmor = !HidePlayersConfig.hideArmor;
                HidePlayersConfig.save();
                showToggleStatus("hideArmor", HidePlayersConfig.hideArmor);
            }
            while(hideHeldItemsKey.wasPressed()) {
                HidePlayersConfig.hideHeldItems = !HidePlayersConfig.hideHeldItems;
                HidePlayersConfig.save();
                showToggleStatus("hideHeldItems", HidePlayersConfig.hideHeldItems);
            }
            while(hideGlintKey.wasPressed()) {
                HidePlayersConfig.hideGlint = !HidePlayersConfig.hideGlint;
                HidePlayersConfig.save();
                showToggleStatus("hideGlint", HidePlayersConfig.hideGlint);
            }
            while(hide2ndLayerKey.wasPressed()) {
                HidePlayersConfig.hide2ndLayer = !HidePlayersConfig.hideGlint;
                HidePlayersConfig.save();
                showToggleStatus("hide2ndLayer", HidePlayersConfig.hide2ndLayer);
            }
        });
    }

    public static void showToggleStatus(String keyName, boolean toggleBoolean) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(new LiteralText(new TranslatableText(MOD_ID + ".midnightconfig." + keyName)
                .getString() + ": " + new TranslatableText(toggleBoolean? "options.on":"options.off").getString()), true);

    }

    public static double getDistance() {
        return Math.pow(HidePlayersConfig.hidePlayersdistance, 2);
    }
}

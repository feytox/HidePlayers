package fr.minemobs.hideplayers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.lwjgl.glfw.GLFW;

public class HidePlayers implements ModInitializer {

    private static boolean renderPlayer;

    @Override
    public void onInitialize() {
        HidePlayersConfig.init("hideplayers", HidePlayersConfig.class);

        renderPlayer = true;
        KeyBinding renderPlayerKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.hideplayers.hideplayers",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_H,
                    "key.hideplayers.hideplayers"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(renderPlayerKey.wasPressed()) {
                renderPlayer = !renderPlayer;
                client.player.sendMessage(new LiteralText(new TranslatableText("hideplayers.hiding_players").getString() + ": " + new TranslatableText(renderPlayer? "options.off":"options.on").getString()), true);
            }
        });
    }

    public static boolean isRenderPlayer() {
        return renderPlayer;
    }

    public static double getDistance() {
        return Math.pow(HidePlayersConfig.distance, 2);
    }
}

package name.uwu.feytox.toomanyplayers.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import name.uwu.feytox.toomanyplayers.TMPConfig;
import name.uwu.feytox.toomanyplayers.TooManyPlayers;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class FastMenuGui extends LightweightGuiDescription {

    public FastMenuGui() {
        this("");
    }

    public FastMenuGui(String nickname) {
        WFreePlainPanel root = new WFreePlainPanel();
        setRootPanel(root);
        root.setSize(160, 35);
        root.setInsets(Insets.ROOT_PANEL);

        FTextField nicknameField = new FTextField(Text.of(nickname));

        WButton whitelist_button = new WButton(new TextureIcon(new Identifier(TooManyPlayers.MOD_ID, "textures/gui/white_list.png")),
                new TranslatableText(TooManyPlayers.MOD_ID + ".gui.white_list"));
        whitelist_button.setOnClick(() -> {
            String nick = getNick(nicknameField);
            if (TMPConfig.whitelist.contains(nick)) {
                TMPConfig.whitelist.remove(nick);
            } else {
                TMPConfig.whitelist.add(nick);
            }
            TMPConfig.save();
            MinecraftClient.getInstance().setScreen(null);
        });
        WSprite whitelist_status = new WSprite(new Texture(new Identifier(TooManyPlayers.MOD_ID, "textures/gui/white_list_indicator.png")));

        WButton blocklist_button = new WButton(new TextureIcon(new Identifier(TooManyPlayers.MOD_ID, "textures/gui/block_list.png")),
                new TranslatableText(TooManyPlayers.MOD_ID + ".gui.block_list"));
        blocklist_button.setOnClick(() -> {
            String nick = getNick(nicknameField);
            if (TMPConfig.blocklist.contains(nick)) {
                TMPConfig.blocklist.remove(nick);
            } else {
                TMPConfig.blocklist.add(nick);
            }
            TMPConfig.save();
            MinecraftClient.getInstance().setScreen(null);
        });
        WSprite blocklist_status = new WSprite(new Texture(new Identifier(TooManyPlayers.MOD_ID, "textures/gui/block_list_indicator.png")));

        WButton hideskinslist_button = new WButton(new TextureIcon(new Identifier(TooManyPlayers.MOD_ID, "textures/gui/hide_skin.png")),
                new TranslatableText(TooManyPlayers.MOD_ID + ".gui.hide_skin"));
        hideskinslist_button.setOnClick(() -> {
            String nick = getNick(nicknameField);
            if (TMPConfig.hideskinlist.contains(nick)) {
                TMPConfig.hideskinlist.remove(nick);
            } else {
                TMPConfig.hideskinlist.add(nick);
            }
            TMPConfig.save();
            MinecraftClient.getInstance().setScreen(null);
        });
        WSprite hideskin_status = new WSprite(new Texture(new Identifier(TooManyPlayers.MOD_ID, "textures/gui/hide_skin_indicator.png")));


        root.add(nicknameField, 0, 0, 145, 20);
        root.add(whitelist_button, 1, 32, 145, 20);
        root.add(blocklist_button, 1, 57, 145, 20);
        root.add(hideskinslist_button, 1, 82, 145, 20);

        if (TMPConfig.whitelist.contains(getNick(nicknameField))) {
            root.add(whitelist_status, 130, 34, 16, 16);
        }
        if (TMPConfig.blocklist.contains(getNick(nicknameField))) {
            root.add(blocklist_status, 130, 59, 16, 16);
        }
        if (TMPConfig.hideskinlist.contains(getNick(nicknameField))) {
            root.add(hideskin_status, 130, 84, 16, 16);
        }

        root.validate(this);
    }

    private static String getNick(FTextField nicknameField) {
        String nick = nicknameField.getText();
        if (nick.equals("")) {
            nick = nicknameField.getSuggestion().getString();
        }
        return nick;
    }
}

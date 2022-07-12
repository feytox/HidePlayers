package name.uwu.feytox.toomanyplayers;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import static name.uwu.feytox.toomanyplayers.TooManyPlayers.MOD_ID;
import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal;

public class Commands {
    public static void init() {
        ClientCommandManager.DISPATCHER.register(literal("tmp")
                .then(literal("whitelist")
                        .then(literal("add")
                                .then(argument("nickname", EntityArgumentType.player())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String nick = inputSplitted[inputSplitted.length-1];
                                            if (!TMPConfig.whitelist.contains(nick)) {
                                                TMPConfig.whitelist.add(nick);
                                                TMPConfig.save();
                                                sendFormattedText(MOD_ID + ".whitelist.add", nick);
                                            } else {
                                                sendFormattedText(MOD_ID + ".list.alreadyAdded", nick);
                                            }
                                            return 1;
                                        })))
                        .then(literal("remove")
                                .then(argument("nickname", TMPlistArgumentType.whitelist())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String nick = inputSplitted[inputSplitted.length-1];
                                            if (TMPConfig.whitelist.contains(nick)) {
                                                TMPConfig.whitelist.remove(nick);
                                                TMPConfig.save();
                                                sendFormattedText(MOD_ID + ".whitelist.del.success", nick);
                                            } else {
                                                sendFormattedText(MOD_ID + ".whitelist.del.fail", nick);
                                            }
                                            return 1;
                                        })))
                        .then(literal("list")
                                .executes(context -> {
                                    sendFormattedText(MOD_ID + ".whitelist.list", String.join(", ",
                                            TMPConfig.whitelist));
                                    return 1;
                                }))
                        .then(literal("clear")
                                .executes(context -> {
                                    TMPConfig.whitelist.clear();
                                    TMPConfig.save();
                                    sendTranslatableText(MOD_ID + ".whitelist.clear");
                                    return 1;
                                })))
                .then(literal("blocklist")
                        .then(literal("add")
                                .then(argument("nickname", EntityArgumentType.player())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String nick = inputSplitted[inputSplitted.length-1];
                                            if (!TMPConfig.blocklist.contains(nick)) {
                                                TMPConfig.blocklist.add(nick);
                                                TMPConfig.save();
                                                sendFormattedText(MOD_ID + ".blocklist.add", nick);
                                            } else {
                                                sendFormattedText(MOD_ID + ".list.alreadyAdded", nick);
                                            }
                                            return 1;
                                        })))
                        .then(literal("remove")
                                .then(argument("nickname", TMPlistArgumentType.blocklist())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String nick = inputSplitted[inputSplitted.length-1];
                                            if (TMPConfig.blocklist.contains(nick)) {
                                                TMPConfig.blocklist.remove(nick);
                                                TMPConfig.save();
                                                sendFormattedText(MOD_ID + ".blocklist.del.success", nick);
                                            } else {
                                                sendFormattedText(MOD_ID + ".blocklist.del.fail", nick);
                                            }
                                            return 1;
                                        })))
                        .then(literal("list")
                                .executes(context -> {
                                    sendFormattedText(MOD_ID + ".blocklist.list", String.join(", ",
                                            TMPConfig.blocklist));
                                    return 1;
                                }))
                        .then(literal("clear")
                                .executes(context -> {
                                    TMPConfig.blocklist.clear();
                                    TMPConfig.save();
                                    sendTranslatableText(MOD_ID + ".blocklist.clear");
                                    return 1;
                                })))
                .then(literal("hideskin")
                        .then(literal("add")
                                .then(argument("nickname", EntityArgumentType.player())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String nick = inputSplitted[inputSplitted.length-1];
                                            if (!TMPConfig.hideskinlist.contains(nick)) {
                                                TMPConfig.hideskinlist.add(nick);
                                                TMPConfig.save();
                                                sendFormattedText(MOD_ID + ".hideskinlist.add", nick);
                                            } else {
                                                sendFormattedText(MOD_ID + ".list.alreadyAdded", nick);
                                            }
                                            return 1;
                                        })))
                        .then(literal("remove")
                                .then(argument("nickname", TMPlistArgumentType.hideskinlist())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String nick = inputSplitted[inputSplitted.length-1];
                                            if (TMPConfig.hideskinlist.contains(nick)) {
                                                TMPConfig.hideskinlist.remove(nick);
                                                TMPConfig.save();
                                                sendFormattedText(MOD_ID + ".hideskinlist.del.success", nick);
                                            } else {
                                                sendFormattedText(MOD_ID + ".hideskinlist.del.fail", nick);
                                            }
                                            return 1;
                                        })))
                        .then(literal("list")
                                .executes(context -> {
                                    sendFormattedText(MOD_ID + ".hideskinlist.list", String.join(", ",
                                            TMPConfig.hideskinlist));
                                    return 1;
                                }))
                        .then(literal("clear")
                                .executes(context -> {
                                    TMPConfig.hideskinlist.clear();
                                    TMPConfig.save();
                                    sendTranslatableText(MOD_ID + ".hideskinlist.clear");
                                    return 1;
                                })))
                .then(literal("preset")
                        .then(literal("create")
                                .then(argument("preset name", StringArgumentType.string())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String presetName = inputSplitted[inputSplitted.length-1];
                                            Presets.createPreset(presetName);
                                            sendTranslatableText(MOD_ID + ".preset.create");
                                            return 1;
                                        })))
                        .then(literal("remove")
                                .then(argument("preset name", PresetArgumentType.presetWithoutDefaults())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String presetName = inputSplitted[inputSplitted.length-1];
                                            boolean result = Presets.removePreset(presetName);
                                            if (result) {
                                                sendTranslatableText(MOD_ID + ".preset.remove.success");
                                            } else {
                                                sendTranslatableText(MOD_ID + ".preset.remove.fail");
                                            }
                                            return 1;
                                        })))
                        .then(literal("select")
                                .then(argument("preset name", PresetArgumentType.preset())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String presetName = inputSplitted[inputSplitted.length-1];
                                            if (Presets.getPresets(true).contains(presetName)) {
                                                Presets.setPreset(presetName);
                                                sendTranslatableText(MOD_ID + ".preset.select.success");
                                            } else {
                                                sendTranslatableText(MOD_ID + ".preset.select.fail");
                                            }
                                            return 1;
                                        })))
                        .then(literal("list")
                                .executes(context -> {
                                    sendFormattedText(MOD_ID + ".preset.list", String.join(", ",
                                            Presets.getPresets(true)));
                                    return 1;
                                })))
                .then(literal("url")
                        .executes(context -> {
                            if (OnlineWhitelist.reloadWhitelist()) {
                                sendTranslatableText(MOD_ID + ".online.success");
                            } else {
                                sendTranslatableText(MOD_ID + ".online.fail");
                            }
                            return 1;
                        })
                        .then(argument("URL", StringArgumentType.greedyString())
                                .executes(context -> {
                                    String[] inputSplitted = context.getInput().split(" ");
                                    String url = inputSplitted[inputSplitted.length-1];
                                    if (OnlineWhitelist.reloadWhitelist(url)) {
                                        sendTranslatableText(MOD_ID + ".online.success");
                                    } else {
                                        sendTranslatableText(MOD_ID + ".online.fail");
                                    }

                                    return 1;
                                }))));
    }

    private static void sendFormattedText(String key, Object formatObj) {
        sendMessage(new LiteralText(I18n.translate(key, formatObj)));
    }

    private static void sendTranslatableText(String key) {
        sendMessage(new TranslatableText(key));
    }

    private static void sendMessage(Text message) {
        MinecraftClient.getInstance().player.sendMessage(message, false);
    }
}

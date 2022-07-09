package name.uwu.feytox.toomanyplayers;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import static name.uwu.feytox.toomanyplayers.TooManyPlayers.MOD_ID;
import static net.fabricmc.fabric.api.client.command.v1.ClientCommandManager.literal;

public class Commands {
    public static void init() {

        ClientCommandManager.DISPATCHER.register(literal("tmp")
                .then(literal("whitelist")
                        .then(literal("add")
                                .then(ClientCommandManager.argument("nickname", EntityArgumentType.player())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String nick = inputSplitted[inputSplitted.length-1];
                                            if (!TMPConfig.whitelist.contains(nick)) {
                                                TMPConfig.whitelist.add(nick);
                                                TMPConfig.save();
                                                MinecraftClient.getInstance().player.sendMessage(new LiteralText(
                                                                I18n.translate(MOD_ID + ".whitelist.add", nick)),
                                                        false);
                                            } else {
                                                MinecraftClient.getInstance().player.sendMessage(new LiteralText(
                                                                I18n.translate(MOD_ID + ".list.alreadyAdded", nick)),
                                                        false);
                                            }
                                            return 1;
                                        })))
                        .then(literal("remove")
                                .then(ClientCommandManager.argument("nickname", TMPlistArgumentType.whitelist())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String nick = inputSplitted[inputSplitted.length-1];
                                            if (TMPConfig.whitelist.contains(nick)) {
                                                TMPConfig.whitelist.remove(nick);
                                                TMPConfig.save();
                                                MinecraftClient.getInstance().player.sendMessage(new LiteralText(
                                                        I18n.translate(MOD_ID + ".whitelist.del.success", nick)),
                                                        false);
                                            } else {
                                                MinecraftClient.getInstance().player.sendMessage(
                                                        new LiteralText(I18n.translate(MOD_ID + ".whitelist.del.fail", nick)),
                                                        false);
                                            }
                                            return 1;
                                        })))
                        .then(literal("list")
                                .executes(context -> {
                                    MinecraftClient.getInstance().player.sendMessage(Text.of(
                                            I18n.translate(MOD_ID + ".whitelist.list", String.join(", ",
                                                    TMPConfig.whitelist))), false);
                                    return 1;
                                }))
                        .then(literal("clear")
                                .executes(context -> {
                                    TMPConfig.whitelist.clear();
                                    TMPConfig.save();
                                    MinecraftClient.getInstance().player.sendMessage(new TranslatableText(MOD_ID + ".whitelist.clear"),
                                            false);
                                    return 1;
                                })))
                .then(literal("blocklist")
                        .then(literal("add")
                                .then(ClientCommandManager.argument("nickname", EntityArgumentType.player())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String nick = inputSplitted[inputSplitted.length-1];
                                            if (!TMPConfig.blocklist.contains(nick)) {
                                                TMPConfig.blocklist.add(nick);
                                                TMPConfig.save();
                                                MinecraftClient.getInstance().player.sendMessage(new LiteralText(
                                                                I18n.translate(MOD_ID + ".blocklist.add", nick)),
                                                        false);
                                            } else {
                                                MinecraftClient.getInstance().player.sendMessage(new LiteralText(
                                                                I18n.translate(MOD_ID + ".list.alreadyAdded", nick)),
                                                        false);
                                            }
                                            return 1;
                                        })))
                        .then(literal("remove")
                                .then(ClientCommandManager.argument("nickname", TMPlistArgumentType.blocklist())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String nick = inputSplitted[inputSplitted.length-1];
                                            if (TMPConfig.blocklist.contains(nick)) {
                                                TMPConfig.blocklist.remove(nick);
                                                TMPConfig.save();
                                                MinecraftClient.getInstance().player.sendMessage(new LiteralText(
                                                                I18n.translate(MOD_ID + ".blocklist.del.success", nick)),
                                                        false);
                                            } else {
                                                MinecraftClient.getInstance().player.sendMessage(
                                                        new LiteralText(I18n.translate(MOD_ID + ".blocklist.del.fail", nick)),
                                                        false);
                                            }
                                            return 1;
                                        })))
                        .then(literal("list")
                                .executes(context -> {
                                    MinecraftClient.getInstance().player.sendMessage(Text.of(
                                            I18n.translate(MOD_ID + ".blocklist.list", String.join(", ",
                                                    TMPConfig.blocklist))), false);
                                    return 1;
                                }))
                        .then(literal("clear")
                                .executes(context -> {
                                    TMPConfig.blocklist.clear();
                                    TMPConfig.save();
                                    MinecraftClient.getInstance().player.sendMessage(new TranslatableText(MOD_ID + ".blocklist.clear"),
                                            false);
                                    return 1;
                                })))
                .then(literal("hideskin")
                        .then(literal("add")
                                .then(ClientCommandManager.argument("nickname", EntityArgumentType.player())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String nick = inputSplitted[inputSplitted.length-1];
                                            if (!TMPConfig.hideskinlist.contains(nick)) {
                                                TMPConfig.hideskinlist.add(nick);
                                                TMPConfig.save();
                                                MinecraftClient.getInstance().player.sendMessage(new LiteralText(
                                                                I18n.translate(MOD_ID + ".hideskinlist.add", nick)),
                                                        false);
                                            } else {
                                                MinecraftClient.getInstance().player.sendMessage(new LiteralText(
                                                                I18n.translate(MOD_ID + ".list.alreadyAdded", nick)),
                                                        false);
                                            }
                                            return 1;
                                        })))
                        .then(literal("remove")
                                .then(ClientCommandManager.argument("nickname", TMPlistArgumentType.hideskinlist())
                                        .executes(context -> {
                                            String[] inputSplitted = context.getInput().split(" ");
                                            String nick = inputSplitted[inputSplitted.length-1];
                                            if (TMPConfig.hideskinlist.contains(nick)) {
                                                TMPConfig.hideskinlist.remove(nick);
                                                TMPConfig.save();
                                                MinecraftClient.getInstance().player.sendMessage(new LiteralText(
                                                                I18n.translate(MOD_ID + ".hideskinlist.del.success", nick)),
                                                        false);
                                            } else {
                                                MinecraftClient.getInstance().player.sendMessage(
                                                        new LiteralText(I18n.translate(MOD_ID + ".hideskinlist.del.fail", nick)),
                                                        false);
                                            }
                                            return 1;
                                        })))
                        .then(literal("list")
                                .executes(context -> {
                                    MinecraftClient.getInstance().player.sendMessage(Text.of(
                                            I18n.translate(MOD_ID + ".hideskinlist.list", String.join(", ",
                                                    TMPConfig.hideskinlist))), false);
                                    return 1;
                                }))
                        .then(literal("clear")
                                .executes(context -> {
                                    TMPConfig.hideskinlist.clear();
                                    TMPConfig.save();
                                    MinecraftClient.getInstance().player.sendMessage(new TranslatableText(MOD_ID + ".hideskinlist.clear"),
                                            false);
                                    return 1;
                                }))));
    }
}

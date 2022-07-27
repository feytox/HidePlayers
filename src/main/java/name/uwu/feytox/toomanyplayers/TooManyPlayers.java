package name.uwu.feytox.toomanyplayers;

import name.uwu.feytox.toomanyplayers.gui.FastMenuGui;
import name.uwu.feytox.toomanyplayers.gui.GuiScreen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;

public class TooManyPlayers implements ModInitializer {
    public static final String MOD_ID = "toomanyplayers";

    @Override
    public void onInitialize() {
        TMPConfig.init();
        Commands.init();
        
        OnlineWhitelist.reloadWhitelist();

        KeyBinding hidePlayersKey = registerKey("hidePlayers");
        KeyBinding showOnlyHeadsKey = registerKey("showOnlyHeads");
        KeyBinding hideArmorKey = registerKey("hideArmor");
        KeyBinding hideHeldItemsKey = registerKey("hideHeldItems");
        KeyBinding hideGlintKey = registerKey("hideGlint");
        KeyBinding hide2ndLayerKey = registerKey("hide2ndLayer");
        KeyBinding toggleModKey = registerKey("toggleMod", GLFW.GLFW_KEY_J);
        KeyBinding toggleAreasKey = registerKey("toggleAreas");
        KeyBinding toggleOnlineWhitelist_key = registerKey("toggleOnlineWhitelist");

        KeyBinding fastMenuKey = registerKey("fastMenu");
        KeyBinding openConfigKey = registerKey("openConfigKey");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while(hidePlayersKey.wasPressed()) {
                TMPConfig.hidePlayers = !TMPConfig.hidePlayers;
                TMPConfig.save();
                showToggleStatus("hidePlayers", TMPConfig.hidePlayers);
            }
            while(showOnlyHeadsKey.wasPressed()) {
                TMPConfig.showOnlyHeads = !TMPConfig.showOnlyHeads;
                TMPConfig.save();
                showToggleStatus("showOnlyHeads", TMPConfig.showOnlyHeads);
            }
            while(hideArmorKey.wasPressed()) {
                TMPConfig.hideArmor = !TMPConfig.hideArmor;
                TMPConfig.save();
                showToggleStatus("hideArmor", TMPConfig.hideArmor);
            }
            while(hideHeldItemsKey.wasPressed()) {
                TMPConfig.hideHeldItems = !TMPConfig.hideHeldItems;
                TMPConfig.save();
                showToggleStatus("hideHeldItems", TMPConfig.hideHeldItems);
            }
            while(hideGlintKey.wasPressed()) {
                TMPConfig.hideGlint = !TMPConfig.hideGlint;
                TMPConfig.save();
                showToggleStatus("hideGlint", TMPConfig.hideGlint);
            }
            while(hide2ndLayerKey.wasPressed()) {
                TMPConfig.hide2ndLayer = !TMPConfig.hide2ndLayer;
                TMPConfig.save();
                showToggleStatus("hide2ndLayer", TMPConfig.hide2ndLayer);
            }
            while(toggleModKey.wasPressed()) {
                TMPConfig.toggleMod = !TMPConfig.toggleMod;
                TMPConfig.save();
                showToggleStatus("toggleMod", TMPConfig.toggleMod);
            }
            while(toggleAreasKey.wasPressed()) {
                TMPConfig.toggleAreas = !TMPConfig.toggleAreas;
                TMPConfig.save();
                showToggleStatus("toggleAreas", TMPConfig.toggleAreas);
            }
            while (fastMenuKey.wasPressed()) {
                Entity targetedEntity = getTargetedPlayer();
                if (targetedEntity != null) {
                    client.setScreen(new GuiScreen(new FastMenuGui(targetedEntity.getName().getString())));
                } else {
                    client.setScreen(new GuiScreen(new FastMenuGui()));
                }
            }
            while (toggleOnlineWhitelist_key.wasPressed()) {
                TMPConfig.toggleOnlineWhitelist = !TMPConfig.toggleOnlineWhitelist;
                TMPConfig.save();
                showToggleStatus("toggleOnlineWhitelist", TMPConfig.toggleOnlineWhitelist);
            }
            while (openConfigKey.wasPressed()) {
                client.setScreen(TMPConfig.getScreen(client.currentScreen, "toomanyplayers"));
            }
        });
    }

    public static void showToggleStatus(String keyName, boolean toggleBoolean) {
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.literal(Text.translatable(MOD_ID + ".midnightconfig." + keyName)
                .getString() + ": " + Text.translatable(toggleBoolean? "options.on":"options.off").getString()), true);

    }

    public static double getDistance() {
        return Math.pow(TMPConfig.hidePlayersdistance, 2);
    }

    public static boolean checkWhitelist(Entity entity) {
        String entityName = entity.getName().getString();
        return (TMPConfig.toggleOnlineWhitelist && OnlineWhitelist.onlineList.contains(entityName))
                || TMPConfig.whitelist.contains(entityName);
    }

    public static boolean checkBlocklistALL(Entity entity) {
        return TMPConfig.blocklist.contains(entity.getName().getString()) && TMPConfig.blockingType.equals(TMPConfig.BlockingType.ALL);
    }

    public static boolean checkBlocklistKeepHeads(Entity entity) {
        return TMPConfig.blocklist.contains(entity.getName().getString()) && TMPConfig.blockingType.equals(TMPConfig.BlockingType.KEEP_HEADS);
    }

    private static KeyBinding registerKey(String keyname) {
        return registerKey(keyname, GLFW.GLFW_KEY_UNKNOWN);
    }

    private static KeyBinding registerKey(String keyname, int key) {
        return KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key." + MOD_ID + "." + keyname,
                        InputUtil.Type.KEYSYM,
                        key, MOD_ID + ".midnightconfig.title"
                ));
    }

    @Nullable
    public static PlayerEntity getTargetedPlayer() {
        Entity targetedEntity = getTargetedEntity();
        if (targetedEntity instanceof PlayerEntity) {
            return (PlayerEntity) targetedEntity;
        }
        return null;
    }

    @Nullable
    public static Entity getTargetedEntity() {
        Entity targetedEntity = calcTargetedEntity();
        if (targetedEntity != null) {
            return targetedEntity;
        }
        return MinecraftClient.getInstance().targetedEntity;
    }

    @Nullable
    private static Entity calcTargetedEntity() {
        float tickDelta = 1.0F;

        MinecraftClient client = MinecraftClient.getInstance();

        Entity entity = client.getCameraEntity();
        if (entity != null) {
            if (client.world != null) {
                client.getProfiler().push("pick");
                double d = 500;
                HitResult crosshairTarget = entity.raycast(d, tickDelta, false);
                Vec3d vec3d = entity.getCameraPosVec(tickDelta);
                double e = d;

                e *= e;
                if (crosshairTarget != null) {
                    e = crosshairTarget.getPos().squaredDistanceTo(vec3d);
                }

                Vec3d vec3d2 = entity.getRotationVec(1.0F);
                Vec3d vec3d3 = vec3d.add(vec3d2.x * d, vec3d2.y * d, vec3d2.z * d);
                Box box = entity.getBoundingBox().stretch(vec3d2.multiply(d)).expand(1.0, 1.0, 1.0);
                EntityHitResult entityHitResult = ProjectileUtil.raycast(entity, vec3d, vec3d3, box, (entityx) ->
                        !entityx.isSpectator() && entityx.collides(), e);
                if (entityHitResult != null) {
                    Entity entity2 = entityHitResult.getEntity();
                    Vec3d vec3d4 = entityHitResult.getPos();
                    double g = vec3d.squaredDistanceTo(vec3d4);
                    if (g > 9.0 && (g < e || crosshairTarget == null)) {
                        if (entity2 instanceof LivingEntity || entity2 instanceof ItemFrameEntity) {
                            return entity2;
                        }
                    }
                }
            }
        }
        return null;
    }
}

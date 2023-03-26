package ru.feytox.toomanyplayers.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.feytox.toomanyplayers.HidingAreas;
import ru.feytox.toomanyplayers.TMPConfig;
import ru.feytox.toomanyplayers.TooManyPlayers;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private <E extends Entity> void render(E entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (TMPConfig.toggleMod) {
            if ((TMPConfig.hidePlayers && !TMPConfig.toggleAreas) || HidingAreas.isPlayersHiding(entity)
                    || (entity instanceof PlayerEntity && TooManyPlayers.checkBlocklistALL(entity))) {
                if (entity instanceof PlayerEntity player && !player.isMainPlayer()
                    && (MinecraftClient.getInstance().player.squaredDistanceTo(player) > TooManyPlayers.getDistance() ||
                        TooManyPlayers.checkBlocklistALL(entity))) {
                    if (!TooManyPlayers.checkWhitelist(entity)) {
                        ci.cancel();
                    }
                }
            }
//            if (((TMPConfig.hidePlayers ^ HidingAreas.isOtherArea(entity))
//                    && entity instanceof PlayerEntity player && !player.isMainPlayer()
//                    && MinecraftClient.getInstance().player.squaredDistanceTo(player) > TooManyPlayers.getDistance()
//                    && !TooManyPlayers.checkWhitelist(entity))
//                    || (entity instanceof PlayerEntity && TooManyPlayers.checkBlocklistALL(entity))) {
//                ci.cancel();
//            }
        }
    }
}

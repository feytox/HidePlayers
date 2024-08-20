package ru.feytox.toomanyplayers.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.player.PlayerModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.feytox.toomanyplayers.HidingAreas;
import ru.feytox.toomanyplayers.TMPConfig;
import ru.feytox.toomanyplayers.TooManyPlayers;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Inject(method = "setModelPose", at = @At("HEAD"), cancellable = true)
    public void onSetModelPose(AbstractClientPlayerEntity player, CallbackInfo ci) {
        if (TMPConfig.toggleMod) {
            PlayerEntityModel<AbstractClientPlayerEntity> playerEntityModel = ((PlayerEntityRenderer) (Object) this).getModel();
            if ((TMPConfig.showOnlyHeads && !TMPConfig.toggleAreas) || HidingAreas.isOnlyHeadsShown(player)
                    || TooManyPlayers.checkBlocklistKeepHeads(player)) {
                if (!player.isMainPlayer() && !TooManyPlayers.checkWhitelist(player)) {
                    playerEntityModel.setVisible(false);
                    playerEntityModel.head.visible = true;
                    playerEntityModel.hat.visible = player.isPartVisible(PlayerModelPart.HAT);
                    ci.cancel();
                }
            }
//            if (((TMPConfig.showOnlyHeads ^ HidingAreas.isThirdArea(player)) || TooManyPlayers.checkBlocklistKeepHeads(player))
//                    && !player.isMainPlayer() && !TooManyPlayers.checkWhitelist(player)) {
//                playerEntityModel.setVisible(false);
//                playerEntityModel.head.visible = true;
//                playerEntityModel.hat.visible = player.isPartVisible(PlayerModelPart.HAT);
//                ci.cancel();
//            }
        }
    }
}

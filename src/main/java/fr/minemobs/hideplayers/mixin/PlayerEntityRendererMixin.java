package fr.minemobs.hideplayers.mixin;

import fr.minemobs.hideplayers.HidePlayersConfig;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Inject(method = "setModelPose", at = @At("HEAD"), cancellable = true)
    public void onSetModelPose(AbstractClientPlayerEntity player, CallbackInfo ci) {
        PlayerEntityModel<AbstractClientPlayerEntity> playerEntityModel = ((PlayerEntityRenderer)(Object) this).getModel();
        if (HidePlayersConfig.showOnlyHeads && !player.isMainPlayer()) {
            playerEntityModel.setVisible(false);
            playerEntityModel.head.visible = true;
            playerEntityModel.hat.visible = player.isPartVisible(PlayerModelPart.HAT);
            ci.cancel();
        }
    }
}

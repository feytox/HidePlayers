package fr.minemobs.hideplayers.mixin;

import fr.minemobs.hideplayers.HidePlayersConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "isPartVisible", at = @At("HEAD"), cancellable = true)
    public void onIsPartVisible(PlayerModelPart modelPart, CallbackInfoReturnable<Boolean> cir) {
        if (HidePlayersConfig.hide2ndLayer) {
            assert MinecraftClient.getInstance().player != null;
            if (!((PlayerEntity)(Object) this).getUuid().equals(MinecraftClient.getInstance().player.getUuid())) {
                cir.setReturnValue(false);
            }
        }
    }
}

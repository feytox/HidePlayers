package name.uwu.feytox.toomanyplayers.mixin;

import name.uwu.feytox.toomanyplayers.HidingAreas;
import name.uwu.feytox.toomanyplayers.TMPConfig;
import name.uwu.feytox.toomanyplayers.TooManyPlayers;
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
        PlayerEntity player = ((PlayerEntity) (Object) this);
        if (TMPConfig.toggleMod) {
            if ((TMPConfig.hide2ndLayer && !TMPConfig.toggleAreas) || HidingAreas.is2ndLayerHiding(player)) {
                if (!player.isMainPlayer() && !TooManyPlayers.checkWhitelist(player)) {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}

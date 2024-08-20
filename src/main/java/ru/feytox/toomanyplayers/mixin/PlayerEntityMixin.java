package ru.feytox.toomanyplayers.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.feytox.toomanyplayers.HidingAreas;
import ru.feytox.toomanyplayers.TMPConfig;
import ru.feytox.toomanyplayers.TooManyPlayers;

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

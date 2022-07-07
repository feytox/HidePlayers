package fr.minemobs.hideplayers.mixin;

import fr.minemobs.hideplayers.HidePlayersConfig;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "hasGlint", at = @At("HEAD"), cancellable = true)
    public void onHasGlint(CallbackInfoReturnable<Boolean> cir) {
        if (HidePlayersConfig.hideGlint) {
            cir.setReturnValue(false);
        }
    }
}

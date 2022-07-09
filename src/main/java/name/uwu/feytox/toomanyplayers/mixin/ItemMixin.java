package name.uwu.feytox.toomanyplayers.mixin;

import name.uwu.feytox.toomanyplayers.TMPConfig;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "hasGlint", at = @At("HEAD"), cancellable = true)
    public void onHasGlint(CallbackInfoReturnable<Boolean> cir) {
        if (TMPConfig.toggleMod && TMPConfig.hideGlint) {
            cir.setReturnValue(false);
        }
    }
}

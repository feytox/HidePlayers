package name.uwu.feytox.toomanyplayers.mixin;

import com.mojang.authlib.GameProfile;
import name.uwu.feytox.toomanyplayers.TMPConfig;
import name.uwu.feytox.toomanyplayers.mixin.accessors.PlayerListEntryAccessor;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerListEntry.class)
public abstract class PlayerListEntryMixin {

    @Inject(method = "getSkinTexture", at = @At("HEAD"), cancellable = true)
    public void onGetSkinTexture(CallbackInfoReturnable<Identifier> cir) {
        GameProfile profile = ((PlayerListEntryAccessor) this).getProfile();
        if (TMPConfig.hideskinlist.contains(profile.getName())) {
            cir.setReturnValue(DefaultSkinHelper.getTexture(profile.getId()));
        }
    }
}

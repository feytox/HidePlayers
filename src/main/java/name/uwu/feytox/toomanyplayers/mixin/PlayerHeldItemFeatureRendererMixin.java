package name.uwu.feytox.toomanyplayers.mixin;

import name.uwu.feytox.toomanyplayers.HidingAreas;
import name.uwu.feytox.toomanyplayers.TMPConfig;
import name.uwu.feytox.toomanyplayers.TooManyPlayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.PlayerHeldItemFeatureRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerHeldItemFeatureRenderer.class)
public class PlayerHeldItemFeatureRendererMixin {

    @Inject(method = "renderItem", at = @At("HEAD"), cancellable = true)
    public void onRenderItem(LivingEntity entity, ItemStack stack, ModelTransformation.Mode transformationMode, Arm arm, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (TMPConfig.toggleMod) {
            if ((TMPConfig.hideHeldItems && !TMPConfig.toggleAreas) || HidingAreas.isHeldItemsHiding(entity)) {
                if (entity instanceof PlayerEntity && !((PlayerEntity) entity).isMainPlayer()
                        && !TooManyPlayers.checkWhitelist(entity)) {
                    ci.cancel();
                }
            }
        }
    }
}

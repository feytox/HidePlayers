package name.uwu.feytox.toomanyplayers.mixin;

import name.uwu.feytox.toomanyplayers.HidingAreas;
import name.uwu.feytox.toomanyplayers.TMPConfig;
import name.uwu.feytox.toomanyplayers.TooManyPlayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public class ArmorFeatureRendererMixin<T extends LivingEntity, A extends BipedEntityModel<T>> {

    @Inject(method = "renderArmor", at = @At("HEAD"), cancellable = true)
    public void onRenderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot armorSlot, int light, A model, CallbackInfo ci) {
        if (TMPConfig.toggleMod) {
            if (((TMPConfig.hideArmor || ((TMPConfig.showOnlyHeads || TooManyPlayers.checkBlocklistKeepHeads(entity))
                    && !armorSlot.equals(EquipmentSlot.HEAD)))
                    && !TMPConfig.toggleAreas) || HidingAreas.isArmorHiding(entity)) {
                if (entity instanceof PlayerEntity && !((PlayerEntity) entity).isMainPlayer()) {
                    if (!TooManyPlayers.checkWhitelist(entity)) {
                        ci.cancel();
                    }
                }
            }
//            if ((((TMPConfig.hideArmor || (TMPConfig.showOnlyHeads
//                    && !armorSlot.equals(EquipmentSlot.HEAD)) && !TMPConfig.toggleAreas) || HidingAreas.isSecondArea(entity))
//                    && entity instanceof PlayerEntity && !((PlayerEntity) entity).isMainPlayer()
//                    && !TooManyPlayers.checkWhitelist(entity))) {
//                ci.cancel();
//            }
        }
    }
}

package syric.dragonseeker.mixins;

import com.github.alexthe666.iceandfire.client.render.entity.layer.LayerDragonBanner;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import syric.dragonseeker.DragonSeekerConfig;

@Mixin(value = LayerDragonBanner.class, remap = false)
public class MixinLayerDragonBanner {
    @Unique
    private static final Boolean dragonseeker$FixBannerResize = DragonSeekerConfig.COMMON.FixBannerResize.get();

    @Inject(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILcom/github/alexthe666/iceandfire/entity/EntityDragonBase;FFFFFF)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void DS$scale(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, EntityDragonBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci, ItemStack itemstack, float f, float f2) {
        float scaleFactor = f2;

        if (dragonseeker$FixBannerResize) {
            scaleFactor = 0.5f * (entity.getRenderSize() / 5.0f);
            scaleFactor = Math.max(1.0f, scaleFactor);
        }

        matrixStackIn.scale(scaleFactor, scaleFactor, scaleFactor);
    }
}

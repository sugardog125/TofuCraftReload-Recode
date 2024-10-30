package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.client.render.entity.TofuSlimeRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;

public class TofuSlimeOuterLayer<T extends SlimeRenderState> extends RenderLayer<T, SlimeModel> {
    private final SlimeModel model;

    public TofuSlimeOuterLayer(RenderLayerParent<T, SlimeModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new SlimeModel(modelSet.bakeLayer(ModelLayers.SLIME_OUTER));
    }

    public void render(PoseStack p_117470_, MultiBufferSource p_117471_, int p_117472_, T p_360800_, float p_117474_, float p_117475_) {
        boolean flag = p_360800_.appearsGlowing && p_360800_.isInvisible;
        if (!p_360800_.isInvisible || flag) {
            VertexConsumer vertexconsumer;
            if (flag) {
                vertexconsumer = p_117471_.getBuffer(RenderType.outline(TofuSlimeRender.LOCATION));
            } else {
                vertexconsumer = p_117471_.getBuffer(RenderType.entityTranslucent(TofuSlimeRender.LOCATION));
            }

            this.model.setupAnim(p_360800_);
            this.model.renderToBuffer(p_117470_, vertexconsumer, p_117472_, LivingEntityRenderer.getOverlayCoords(p_360800_, 0.0F));
        }
    }
}

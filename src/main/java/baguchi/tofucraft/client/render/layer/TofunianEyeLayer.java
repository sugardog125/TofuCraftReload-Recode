package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.model.AbstractTofunianModel;
import baguchi.tofucraft.client.render.state.AbstractTofunianRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Pose;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofunianEyeLayer<T extends AbstractTofunianRenderState, M extends AbstractTofunianModel<T>> extends RenderLayer<T, M> {
	public static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/tofunian_eye.png");

	public TofunianEyeLayer(RenderLayerParent<T, M> tofunianRender) {
		super(tofunianRender);
	}

	@Override
	public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, T p_361554_, float p_117353_, float p_117354_) {
		float f3 = (p_361554_.ageInTicks + p_361554_.id);

		if (!p_361554_.isInvisible && (0 > Math.sin(f3 * 0.05F) + Math.sin(f3 * 0.13F) + Math.sin(f3 * 0.7F) + 2.55F) || p_361554_.pose == Pose.SLEEPING) {
			renderColoredCutoutModel(this.getParentModel(), LOCATION, p_117349_, p_117350_, p_117351_, p_361554_, -1);
		}
	}
}

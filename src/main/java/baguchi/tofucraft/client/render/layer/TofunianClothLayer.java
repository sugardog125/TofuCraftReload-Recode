package baguchi.tofucraft.client.render.layer;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.model.TofunianModel;
import baguchi.tofucraft.client.render.state.TofunianRenderState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

@OnlyIn(Dist.CLIENT)
public class TofunianClothLayer extends RenderLayer<TofunianRenderState, TofunianModel<TofunianRenderState>> {
	public static final ResourceLocation BAGU_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/secret/bagu_chan.png");

	public TofunianClothLayer(RenderLayerParent<TofunianRenderState, TofunianModel<TofunianRenderState>> tofunianRender) {
		super(tofunianRender);
	}

	@Override
	public void render(
			PoseStack p_117148_,
			MultiBufferSource p_117149_,
			int p_117150_,
			TofunianRenderState p_117151_,
			float p_117156_,
			float p_117157_
	) {
		if (!p_117151_.isInvisible && p_117151_.texture != null) {
			renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(p_117151_), p_117148_, p_117149_, p_117150_, p_117151_, -1);
		}
	}

	public ResourceLocation getTextureLocation(TofunianRenderState entity) {
		if (entity.nameTag != null) {
			String s = ChatFormatting.stripFormatting(entity.nameTag.getString());
			if (s != null && "bagu_chan".equals(s)) {
				LocalDate localdate = LocalDate.now();
				int i = localdate.get(ChronoField.DAY_OF_MONTH);
				int j = localdate.get(ChronoField.MONTH_OF_YEAR);
				if (!(j == 10 && i == 31 || (j == 12 && i == 15))) {
					return BAGU_LOCATION;
				}
			}
		}

		return entity.texture;
	}
}

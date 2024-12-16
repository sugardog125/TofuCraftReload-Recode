package baguchi.tofucraft.client.render.special;

import baguchi.tofucraft.client.render.blockentity.TofunianStatueRender;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofunianStatueSpecialRenderer implements NoDataSpecialModelRenderer {
	private final TofunianStatueRender bedRenderer;

	public TofunianStatueSpecialRenderer(TofunianStatueRender p_386864_) {
		this.bedRenderer = p_386864_;
	}

	@Override
	public void render(ItemDisplayContext p_387275_, PoseStack p_387960_, MultiBufferSource p_386542_, int p_386921_, int p_387639_, boolean p_387936_) {
		this.bedRenderer.renderInHand(p_387960_, p_386542_, p_386921_, p_387639_);
	}

	@OnlyIn(Dist.CLIENT)
	public static record Unbaked() implements SpecialModelRenderer.Unbaked {
		public static final MapCodec<TofunianStatueSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(TofunianStatueSpecialRenderer.Unbaked::new);

		@Override
		public MapCodec<TofunianStatueSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public SpecialModelRenderer<?> bake(EntityModelSet p_386741_) {
			return new TofunianStatueSpecialRenderer(new TofunianStatueRender(p_386741_));
		}
	}
}
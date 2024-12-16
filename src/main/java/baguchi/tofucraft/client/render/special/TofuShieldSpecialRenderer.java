package baguchi.tofucraft.client.render.special;

import baguchi.tofucraft.TofuCraftReload;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class TofuShieldSpecialRenderer implements SpecialModelRenderer<DataComponentMap> {
	private final ShieldModel model;

	public TofuShieldSpecialRenderer(ShieldModel p_386724_) {
		this.model = p_386724_;
	}

	@Nullable
	public DataComponentMap extractArgument(ItemStack p_387204_) {
		return p_387204_.immutableComponents();
	}

	public void render(
			@Nullable DataComponentMap p_386991_,
			ItemDisplayContext p_388730_,
			PoseStack p_387961_,
			MultiBufferSource p_388686_,
			int p_387382_,
			int p_387013_,
			boolean p_387902_
	) {
		p_387961_.pushPose();
		p_387961_.scale(1.0F, -1.0F, -1.0F);
		VertexConsumer vertexconsumer = ItemRenderer.getFoilBuffer(p_388686_, this.model.renderType(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofumetal_shield.png")), p_388730_ == ItemDisplayContext.GUI, p_387902_);
		this.model.handle().render(p_387961_, vertexconsumer, p_387382_, p_387013_);
		this.model.plate().render(p_387961_, vertexconsumer, p_387382_, p_387013_);


		p_387961_.popPose();
	}

	@OnlyIn(Dist.CLIENT)
	public static record Unbaked() implements SpecialModelRenderer.Unbaked {
		public static final TofuShieldSpecialRenderer.Unbaked INSTANCE = new TofuShieldSpecialRenderer.Unbaked();
		public static final MapCodec<TofuShieldSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

		@Override
		public MapCodec<TofuShieldSpecialRenderer.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public SpecialModelRenderer<?> bake(EntityModelSet p_387269_) {
			return new TofuShieldSpecialRenderer(new ShieldModel(p_387269_.bakeLayer(ModelLayers.SHIELD)));
		}
	}
}

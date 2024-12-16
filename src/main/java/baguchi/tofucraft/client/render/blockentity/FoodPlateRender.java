package baguchi.tofucraft.client.render.blockentity;

import baguchi.tofucraft.block.FoodPlateBlock;
import baguchi.tofucraft.blockentity.FoodPlateBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class FoodPlateRender implements BlockEntityRenderer<FoodPlateBlockEntity> {
	private final RandomSource random = RandomSource.create();

	public FoodPlateRender(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(FoodPlateBlockEntity plateBlockEntity, float p_112308_, PoseStack poseStack, MultiBufferSource p_112310_, int p_112311_, int p_112312_) {
		Direction direction = plateBlockEntity.getBlockState().getValue(FoodPlateBlock.FACING).getOpposite();
		ItemStack boardStack = plateBlockEntity.getStoredItem();
		int posLong = (int) plateBlockEntity.getBlockPos().asLong();
		int j = this.getRenderAmount(boardStack);
		int i = boardStack.isEmpty() ? 187 : Item.getId(boardStack.getItem()) + boardStack.getDamageValue();

		this.random.setSeed((long) i);
		if (!boardStack.isEmpty()) {
			for (int k = 0; k < j; ++k) {
				poseStack.pushPose();
				ItemRenderer itemRenderer = Minecraft.getInstance()
						.getItemRenderer();
				/*if (isBlockItem) {
					if (k > 0) {
						float f11 = (this.random.nextFloat()) * 0.15F;
						float f13 = (this.random.nextFloat()) * 0.15F;
						float f10 = (this.random.nextFloat()) * 0.15F;
						poseStack.translate(f11, f13, f10);
					}
					renderBlock(poseStack, direction);
				} else {*/
					if (k > 0) {
						float f12 = (this.random.nextFloat()) * 0.15F * 0.5F;
						float f14 = (this.random.nextFloat()) * 0.15F * 0.5F;
						poseStack.translate(f12, k * 0.1F * 0.5F, f14);
					}
					renderItemLayingDown(poseStack, direction);
				Minecraft.getInstance().getItemRenderer().renderStatic(boardStack, ItemDisplayContext.FIXED, p_112311_, p_112312_, poseStack, p_112310_, plateBlockEntity.getLevel(), posLong);
				poseStack.popPose();
			}
		}
	}

	protected int getRenderAmount(ItemStack p_115043_) {
		int i = 1;
		if (p_115043_.getCount() > 48) {
			i = 5;
		} else if (p_115043_.getCount() > 32) {
			i = 4;
		} else if (p_115043_.getCount() > 16) {
			i = 3;
		} else if (p_115043_.getCount() > 1) {
			i = 2;
		}

		return i;
	}

	public void renderItemLayingDown(PoseStack matrixStackIn, Direction direction) {
		matrixStackIn.translate(0.5D, 0.07D, 0.5D);
		float f = -direction.toYRot();
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
		matrixStackIn.mulPose(Axis.XP.rotationDegrees(90.0F));
		matrixStackIn.scale(0.6F, 0.6F, 0.6F);
	}

	public void renderBlock(PoseStack matrixStackIn, Direction direction) {
		matrixStackIn.translate(0.5D, 0.25D, 0.5D);
		float f = -direction.toYRot();
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(f));
		matrixStackIn.scale(0.8F, 0.8F, 0.8F);
	}
}
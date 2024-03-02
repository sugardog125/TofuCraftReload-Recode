package baguchan.tofucraft.client.render.item;

import baguchan.tofucraft.blockentity.TofunianStatueBlockEntity;
import baguchan.tofucraft.registry.TofuBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class TofunianStatueBWLR extends BlockEntityWithoutLevelRenderer {

	public TofunianStatueBWLR() {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	@Override
	public void renderByItem(ItemStack pStack, ItemDisplayContext pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pOverlay) {
		Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(new TofunianStatueBlockEntity(BlockPos.ZERO, TofuBlocks.TOFUNIAN_STATUE.get().defaultBlockState()), pPoseStack, pBuffer, pPackedLight, pOverlay);
	}
}
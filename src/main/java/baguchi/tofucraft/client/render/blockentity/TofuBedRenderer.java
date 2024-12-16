package baguchi.tofucraft.client.render.blockentity;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.TofuBedBlockEntity;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuBedRenderer implements BlockEntityRenderer<TofuBedBlockEntity> {
	public static final ResourceLocation BED_TEXTURES = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/bed/tofubed.png");
	private final ModelPart headRoot;
	private final ModelPart footRoot;

	public TofuBedRenderer(BlockEntityRendererProvider.Context p_173540_) {
		this.headRoot = p_173540_.bakeLayer(ModelLayers.BED_HEAD);
		this.footRoot = p_173540_.bakeLayer(ModelLayers.BED_FOOT);
	}

	public static LayerDefinition createHeadLayer() {
		MeshDefinition var0 = new MeshDefinition();
		PartDefinition var1 = var0.getRoot();
		var1.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F), PartPose.ZERO);
		var1.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(50, 6).addBox(0.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F), PartPose.rotation(1.5707964F, 0.0F, 1.5707964F));
		var1.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(50, 18).addBox(-16.0F, 6.0F, 0.0F, 3.0F, 3.0F, 3.0F), PartPose.rotation(1.5707964F, 0.0F, 3.1415927F));
		return LayerDefinition.create(var0, 64, 64);
	}

	public static LayerDefinition createFootLayer() {
		MeshDefinition var0 = new MeshDefinition();
		PartDefinition var1 = var0.getRoot();
		var1.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 6.0F), PartPose.ZERO);
		var1.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(50, 0).addBox(0.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F), PartPose.rotation(1.5707964F, 0.0F, 0.0F));
		var1.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(50, 12).addBox(-16.0F, 6.0F, -16.0F, 3.0F, 3.0F, 3.0F), PartPose.rotation(1.5707964F, 0.0F, 4.712389F));
		return LayerDefinition.create(var0, 64, 64);
	}

	public void render(TofuBedBlockEntity p_112205_, float p_112206_, PoseStack p_112207_, MultiBufferSource p_112208_, int p_112209_, int p_112210_) {
		Level var8 = p_112205_.getLevel();
		if (var8 != null) {
			BlockState var9 = p_112205_.getBlockState();
			DoubleBlockCombiner.NeighborCombineResult<? extends TofuBedBlockEntity> var10 = DoubleBlockCombiner.combineWithNeigbour(TofuBlockEntitys.TOFUBED.get(), BedBlock::getBlockType, BedBlock::getConnectedDirection, ChestBlock.FACING, var9, var8, p_112205_.getBlockPos(), (p_112202_, p_112203_) -> {
				return false;
			});
			int var11 = ((Int2IntFunction) var10.apply(new BrightnessCombiner())).get(p_112209_);
			this.renderPiece(p_112207_, p_112208_, var9.getValue(BedBlock.PART) == BedPart.HEAD ? this.headRoot : this.footRoot, (Direction) var9.getValue(BedBlock.FACING), var11, p_112210_, false);
		} else {
			this.renderPiece(p_112207_, p_112208_, this.headRoot, Direction.SOUTH, p_112209_, p_112210_, false);
			this.renderPiece(p_112207_, p_112208_, this.footRoot, Direction.SOUTH, p_112209_, p_112210_, true);
		}

	}

	private void renderPiece(PoseStack p_173542_, MultiBufferSource p_173543_, ModelPart p_173544_, Direction p_173545_, int p_173547_, int p_173548_, boolean p_173549_) {
		p_173542_.pushPose();
		p_173542_.translate(0.0D, 0.5625D, p_173549_ ? -1.0D : 0.0D);
		p_173542_.mulPose(Axis.XP.rotationDegrees(90.0F));
		p_173542_.translate(0.5D, 0.5D, 0.5D);
		p_173542_.mulPose(Axis.ZP.rotationDegrees(180.0F + p_173545_.toYRot()));
		p_173542_.translate(-0.5D, -0.5D, -0.5D);
		VertexConsumer var9 = p_173543_.getBuffer(RenderType.entitySolid(BED_TEXTURES));
		p_173544_.render(p_173542_, var9, p_173547_, p_173548_);
		p_173542_.popPose();
	}
}

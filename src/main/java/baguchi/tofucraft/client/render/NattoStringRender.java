package baguchi.tofucraft.client.render;

import baguchi.tofucraft.client.render.state.ProjectileRenderState;
import baguchi.tofucraft.entity.projectile.NattoStringEntity;
import baguchi.tofucraft.registry.TofuItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NattoStringRender<E extends NattoStringEntity, T extends ProjectileRenderState> extends EntityRenderer<E, T> {
	private final float scale;
	private final boolean fullBright;
	private final ItemRenderer itemRenderer;

	public NattoStringRender(EntityRendererProvider.Context context, float scale, boolean bright) {
		super(context);
		this.scale = scale;
		this.fullBright = bright;
		this.itemRenderer = Minecraft.getInstance().getItemRenderer();
	}

	protected int getBlockLightLevel(E p_116092_, BlockPos p_116093_) {
		return this.fullBright ? 15 : super.getBlockLightLevel(p_116092_, p_116093_);
	}

	@Override
	public T createRenderState() {
		return (T) new ProjectileRenderState();
	}

	public void render(T p_116085_, PoseStack p_116088_, MultiBufferSource p_116089_, int p_116090_) {
		p_116088_.pushPose();
		p_116088_.scale(this.scale, this.scale, this.scale);
		p_116088_.mulPose(this.entityRenderDispatcher.cameraOrientation());
		p_116088_.mulPose(Axis.YP.rotationDegrees(180.0F));
		this.itemRenderer.renderStatic(new ItemStack(TofuItems.NATTO_COBWEB.get()), ItemDisplayContext.GROUND, p_116090_, OverlayTexture.NO_OVERLAY, p_116088_, p_116089_, null, 0);
		p_116088_.popPose();
		super.render(p_116085_, p_116088_, p_116089_, p_116090_);
	}
}

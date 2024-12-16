package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.SaltFurnaceBlockEntity;
import baguchi.tofucraft.client.ClientProxy;
import baguchi.tofucraft.inventory.SaltFurnaceMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.CoreShaders;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class SaltFurnaceScreen extends AbstractContainerScreen<SaltFurnaceMenu> {
	private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/salt_furnace.png");

	public SaltFurnaceScreen(SaltFurnaceMenu p_i51104_1_, Inventory p_i51104_3_, Component p_i51104_4_) {
		super(p_i51104_1_, p_i51104_3_, p_i51104_4_);
	}

	public void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	public void render(GuiGraphics p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
		super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
		renderTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
	}

	@Override
	protected void renderBg(GuiGraphics p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		int i = this.leftPos;
		int j = this.topPos;
		p_230450_1_.blit(RenderType::guiTextured, texture, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);
		if (this.menu.isLit()) {
			int k = this.menu.getLitProgress();
			p_230450_1_.blit(RenderType::guiTextured, texture, i + 23, j + 36 + 12 - k, 176, 12 - k, 14, k + 1, 256, 256);
		}
		int l = this.menu.getBurnProgress();
		p_230450_1_.blit(RenderType::guiTextured, texture, i + 54, j + 54, 176, 14, l + 1, 16, 256, 256);
		p_230450_1_.pose().pushPose();
		if (ClientProxy.PROXY.getRefrencedTE() instanceof SaltFurnaceBlockEntity && ((SaltFurnaceBlockEntity) ClientProxy.PROXY.getRefrencedTE()).bitternTank.getFluid() != null) {
			FluidTank fluidTank = ((SaltFurnaceBlockEntity) ClientProxy.PROXY.getRefrencedTE()).bitternTank;
			int heightInd = (int) (44.0F * fluidTank.getFluidAmount() / fluidTank.getCapacity());
			if (heightInd > 0)
				renderFluidStack(p_230450_1_.pose(), i + 145, j + 69, 10, heightInd, fluidTank.getFluid().getFluid());
		}
		p_230450_1_.pose().popPose();
		p_230450_1_.pose().pushPose();
		if (ClientProxy.PROXY.getRefrencedTE() instanceof SaltFurnaceBlockEntity && ((SaltFurnaceBlockEntity) ClientProxy.PROXY.getRefrencedTE()).waterTank.getFluid() != null) {
			FluidTank fluidTank2 = ((SaltFurnaceBlockEntity) ClientProxy.PROXY.getRefrencedTE()).waterTank;
			int heightInd2 = (int) (44.0F * fluidTank2.getFluidAmount() / fluidTank2.getCapacity());
			if (heightInd2 > 0)
				renderFluidStack(p_230450_1_.pose(), i + 158, j + 69, 10, heightInd2, fluidTank2.getFluid().getFluid());
		}
		p_230450_1_.pose().popPose();
	}


	public static void renderFluidStack(PoseStack stack, int xPosition, int yPosition, int desiredWidth, int desiredHeight, Fluid fluid) {
		TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(IClientFluidTypeExtensions.of(fluid).getStillTexture());
		int color = IClientFluidTypeExtensions.of(fluid).getTintColor();

		float alpha = (float) (color >> 24 & 255) / 255.0F;
		float red = (float) (color >> 16 & 0xFF) / 255.0F;
		float green = (float) (color >> 8 & 0xFF) / 255.0F;
		float blue = (float) (color & 0xFF) / 255.0F;

		RenderSystem.setShader(CoreShaders.POSITION_TEX_COLOR);
		RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_BLOCKS);
		int xTileCount = desiredWidth / 16;
		int xRemainder = desiredWidth - (xTileCount * 16);
		int yTileCount = desiredHeight / 16;
		int yRemainder = desiredHeight - (yTileCount * 16);
		float uMin = sprite.getU0();
		float uMax = sprite.getU1();
		float vMin = sprite.getV0();
		float vMax = sprite.getV1();
		float uDif = uMax - uMin;
		float vDif = vMax - vMin;
		RenderSystem.enableBlend();
		BufferBuilder vertexBuffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
		Matrix4f matrix4f = stack.last().pose();
		for (int xTile = 0; xTile <= xTileCount; xTile++) {
			int width = (xTile == xTileCount) ? xRemainder : 16;
			if (width == 0) {
				break;
			}
			int x = xPosition + (xTile * 16);
			int maskRight = 16 - width;
			int shiftedX = x + 16 - maskRight;
			float uLocalDif = uDif * maskRight / 16;

			for (int yTile = 0; yTile <= yTileCount; yTile++) {
				int height = (yTile == yTileCount) ? yRemainder : 16;
				if (height == 0) {
					break;
				}
				int y = yPosition - ((yTile + 1) * 16);
				int maskTop = 16 - height;
				float vLocalDif = vDif * maskTop / 16;

				vertexBuffer.addVertex(matrix4f, x, y + 16, 0).setUv(uMin + uLocalDif, vMax).setColor(red, green, blue, alpha);
				vertexBuffer.addVertex(matrix4f, shiftedX, y + 16, 0).setUv(uMax, vMax).setColor(red, green, blue, alpha);
				vertexBuffer.addVertex(matrix4f, shiftedX, y + maskTop, 0).setUv(uMax, vMin + vLocalDif).setColor(red, green, blue, alpha);
				vertexBuffer.addVertex(matrix4f, x, y + maskTop, 0).setUv(uMin + uLocalDif, vMin + vLocalDif).setColor(red, green, blue, alpha);
			}
		}
		BufferUploader.drawWithShader(vertexBuffer.buildOrThrow());
		RenderSystem.disableBlend();
	}
}
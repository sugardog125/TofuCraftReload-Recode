package baguchi.tofucraft.client.screen;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.blockentity.tfenergy.TFStorageBlockEntity;
import baguchi.tofucraft.client.ClientProxy;
import baguchi.tofucraft.inventory.TFStorageMenu;
import baguchi.tofucraft.mixin.client.GuiGraphicsAccessor;
import baguchi.tofucraft.registry.TofuFluids;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import org.joml.Matrix4f;

import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class TFStorageScreen extends AbstractContainerScreen<TFStorageMenu> {
	private static final ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/tf_storage.png");
	private static final Component MISSING_ITEM_TOOLTIP = Component.translatable("container.tofucraft.tf_storage.missing_item_tooltip_consume");
	private static final Component MISSING_ITEM_TOOLTIP_2 = Component.translatable("container.tofucraft.tf_storage.missing_item_tooltip_repair");
	public TFStorageScreen(TFStorageMenu p_i51104_1_, Inventory p_i51104_3_, Component p_i51104_4_) {
		super(p_i51104_1_, p_i51104_3_, p_i51104_4_);
	}

	public void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	public void render(GuiGraphics p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
		super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
		this.renderTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
		this.renderOnboardingTooltips(p_230430_1_, p_230430_2_, p_230430_3_);
	}

	protected void renderBg(GuiGraphics p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
		int i = this.leftPos;
		int j = this.topPos;
		p_230450_1_.blit(RenderType::guiTextured, texture, i, j, 0, 0, this.imageWidth, this.imageHeight, 256, 256);

		if (ClientProxy.PROXY.getRefrencedTE() instanceof TFStorageBlockEntity && ((TFStorageBlockEntity) ClientProxy.PROXY.getRefrencedTE()).getTank().getFluid() != null) {
			FluidTank fluidTank = ((TFStorageBlockEntity) ClientProxy.PROXY.getRefrencedTE()).getTank();
			int heightInd = (int) (44.0F * fluidTank.getFluidAmount() / fluidTank.getCapacity());
			if (heightInd > 0)
				renderFluidStack(p_230450_1_, p_230450_1_.pose(), i + 145, j + 69, 10, heightInd, fluidTank.getFluid().getFluid());
		}
		p_230450_1_.pose().pushPose();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		FluidStack fluidTank2 = new FluidStack(TofuFluids.SOYMILK_FLOW.get(), 1000);
		int heightInd2 = (int) (44.0F * menu.getTFEnergy() / menu.getTFMaxEnergy());
		if (heightInd2 > 0)
			renderFluidStack(p_230450_1_, p_230450_1_.pose(), i + 76, j + 69, 10, heightInd2, fluidTank2.getFluid());
		p_230450_1_.pose().popPose();
	}


	public static void renderFluidStack(GuiGraphics guiGraphics, PoseStack stack, int xPosition, int yPosition, int desiredWidth, int desiredHeight, Fluid fluid) {
		TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(IClientFluidTypeExtensions.of(fluid).getStillTexture());
		int color = IClientFluidTypeExtensions.of(fluid).getTintColor();

		float alpha = (float) (color >> 24 & 255) / 255.0F;
		float red = (float) (color >> 16 & 0xFF) / 255.0F;
		float green = (float) (color >> 8 & 0xFF) / 255.0F;
		float blue = (float) (color & 0xFF) / 255.0F;

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
		VertexConsumer vertexBuffer = ((GuiGraphicsAccessor) guiGraphics).bufferSource().getBuffer(RenderType.guiTextured(TextureAtlas.LOCATION_BLOCKS));
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
		RenderSystem.disableBlend();
	}

	private void renderOnboardingTooltips(GuiGraphics p_281668_, int p_267192_, int p_266859_) {
		Optional<Component> optional = Optional.empty();

		if (this.hoveredSlot != null) {
			ItemStack itemstack = this.menu.getSlot(0).getItem();
			if (itemstack.isEmpty()) {
				if (this.hoveredSlot.index == 0) {
					optional = Optional.of(MISSING_ITEM_TOOLTIP);
				}
			}
			if (itemstack.isEmpty()) {
				if (this.hoveredSlot.index == 1) {
					optional = Optional.of(MISSING_ITEM_TOOLTIP_2);
				}
			}
		}

		optional.ifPresent((p_280863_) -> {
			p_281668_.renderTooltip(this.font, this.font.split(p_280863_, 115), p_267192_, p_266859_);
		});
	}
}
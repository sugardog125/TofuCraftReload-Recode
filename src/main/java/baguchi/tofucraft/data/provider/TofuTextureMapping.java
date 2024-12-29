package baguchi.tofucraft.data.provider;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;

public class TofuTextureMapping {
	public static final TextureSlot GLOW_ALL = TextureSlot.create("glow_all");

	public static final TextureSlot OVERLAY = TextureSlot.create("overlay");

	public static TextureMapping grassBlock(Block block, Block dirt) {
		ResourceLocation resourcelocation = getBlockTexture(block).withSuffix("_top");
		ResourceLocation resourcelocation2 = getBlockTexture(block).withSuffix("_side");
		ResourceLocation resourcelocation3 = getBlockTexture(block).withSuffix("_side_overlay");
		ResourceLocation resourcelocation4 = getBlockTexture(dirt);
		return grassBlock(resourcelocation, resourcelocation2, resourcelocation3, resourcelocation4);
	}

	public static TextureMapping grassBlock(ResourceLocation p_386993_, ResourceLocation side, ResourceLocation overlay, ResourceLocation dirt) {
		return new TextureMapping().put(TextureSlot.PARTICLE, dirt).put(TextureSlot.TOP, p_386993_).put(TextureSlot.SIDE, side).put(TextureSlot.BOTTOM, dirt).put(OVERLAY, overlay);
	}

	public static TextureMapping glowCube(Block p_387253_) {
		ResourceLocation resourcelocation = getBlockTexture(p_387253_);
		ResourceLocation resourcelocation2 = getBlockTexture(p_387253_, "_emissive");
		return glowCube(resourcelocation, resourcelocation2);
	}

	public static TextureMapping glowCube(ResourceLocation p_386993_, ResourceLocation glow) {
		return new TextureMapping().put(TextureSlot.ALL, p_386993_).put(GLOW_ALL, glow);
	}
}
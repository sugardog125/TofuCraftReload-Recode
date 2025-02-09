package baguchi.tofucraft.data.provider;

import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;

public class TofuTextureMapping {
	public static final TextureSlot GLOW_ALL = TextureSlot.create("glow_all");
	public static final TextureSlot GLOW_END = TextureSlot.create("glow_end");
	public static final TextureSlot GLOW_SIDE = TextureSlot.create("glow_side");

	public static final TextureSlot OVERLAY = TextureSlot.create("overlay");

	public static TextureMapping doorTop(Block block) {
		return new TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(block)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top")).copySlot(TextureSlot.TOP, TextureSlot.PARTICLE);
	}

	public static TextureMapping doorBottom(Block block) {
		return new TextureMapping().put(TextureSlot.ALL, TextureMapping.getBlockTexture(block)).put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(block, "_bottom")).copySlot(TextureSlot.BOTTOM, TextureSlot.PARTICLE);
	}

	public static TextureMapping logGlowColumn(Block p_388105_) {
		return new TextureMapping()
				.put(TextureSlot.SIDE, getBlockTexture(p_388105_))
				.put(TextureSlot.END, getBlockTexture(p_388105_, "_top"))
				.put(GLOW_SIDE, getBlockTexture(p_388105_, "_glow"))
				.put(GLOW_END, getBlockTexture(p_388105_, "_top_glow"))
				.put(TextureSlot.PARTICLE, getBlockTexture(p_388105_));
	}

	public static TextureMapping candleCake(Block candle, Block cake, boolean p_387959_) {
		return new TextureMapping()
				.put(TextureSlot.PARTICLE, getBlockTexture(cake, "_side"))
				.put(TextureSlot.BOTTOM, getBlockTexture(cake, "_bottom"))
				.put(TextureSlot.TOP, getBlockTexture(cake, "_top"))
				.put(TextureSlot.SIDE, getBlockTexture(cake, "_side"))
				.put(TextureSlot.CANDLE, getBlockTexture(candle, p_387959_ ? "_lit" : ""));
	}

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
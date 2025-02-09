package baguchi.tofucraft.data.provider;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

import static net.minecraft.client.data.models.model.ModelTemplates.createItem;

public class TofuModelTemplate {
	public static final ModelTemplate GLOW_CUBE = create("glow_cube", TextureSlot.ALL, TofuTextureMapping.GLOW_ALL);
	public static final ModelTemplate CUBE_COLUMN = create("glow_column", TextureSlot.END, TextureSlot.SIDE, TofuTextureMapping.GLOW_END, TofuTextureMapping.GLOW_SIDE);
	public static final ModelTemplate CUBE_COLUMN_HORIZONTAL = create("glow_column_horizontal", "_horizontal", TextureSlot.END, TextureSlot.SIDE, TofuTextureMapping.GLOW_END, TofuTextureMapping.GLOW_SIDE);

	public static final ModelTemplate CHAIN = create("chain", TextureSlot.ALL);
	public static final ModelTemplate LADDER = create("ladder", TextureSlot.ALL);
	public static final ModelTemplate TRANSLUCENT_CUBE = create("translucent_cube", TextureSlot.ALL);
	public static final ModelTemplate BIG_HANDHELD = createItem("handheld", TextureSlot.LAYER0);

	public static ModelTemplate create(String p_386521_, TextureSlot... p_388561_) {
		return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, p_386521_).withPrefix("block/")), Optional.empty(), p_388561_);
	}

	public static ModelTemplate createDefault(String p_386521_, TextureSlot... p_388561_) {
		return new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace(p_386521_).withPrefix("block/")), Optional.empty(), p_388561_);
	}

	public static ModelTemplate create(String p_386833_, String p_386662_, TextureSlot... p_387086_) {
		return new ModelTemplate(Optional.of(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, p_386833_)), Optional.of(p_386662_), p_387086_);
	}
}
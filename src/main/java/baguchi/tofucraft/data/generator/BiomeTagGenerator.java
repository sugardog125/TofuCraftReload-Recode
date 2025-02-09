package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBiomes;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class BiomeTagGenerator extends BiomeTagsProvider {
	public BiomeTagGenerator(PackOutput p_341093_, CompletableFuture<HolderLookup.Provider> p_341136_) {
		super(p_341093_, p_341136_, TofuCraftReload.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		this.tag(TofuTags.Biomes.HOT_VARIANT_TOFUNIAN).addTag(BiomeTags.IS_BADLANDS).addTag(Tags.Biomes.IS_DESERT).add(TofuBiomes.TOFU_WASTES);
	}
}

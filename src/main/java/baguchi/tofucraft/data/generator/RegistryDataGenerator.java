package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.data.resources.ModConfiguredFeatures;
import baguchi.tofucraft.data.resources.TofuConfiguredWorldCarvers;
import baguchi.tofucraft.data.resources.builder.TofuNoiseBuilder;
import baguchi.tofucraft.data.resources.registries.TofunianVariants;
import baguchi.tofucraft.registry.TofuBannerPatterns;
import baguchi.tofucraft.registry.TofuBiomeSources;
import baguchi.tofucraft.registry.TofuBiomes;
import baguchi.tofucraft.registry.TofuDamageTypes;
import baguchi.tofucraft.registry.TofuDimensionTypes;
import baguchi.tofucraft.registry.TofuEnchantments;
import baguchi.tofucraft.registry.TofuLevelStems;
import baguchi.tofucraft.registry.TofuStructures;
import baguchi.tofucraft.registry.TofuTrimMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.NOISE, (context) -> {
			})
			.add(Registries.DENSITY_FUNCTION, TofuNoiseBuilder::bootstrapDensity)
			.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrapConfiguredFeature)
			.add(Registries.PLACED_FEATURE, ModConfiguredFeatures::bootstrapPlacedFeature)
			.add(Registries.PROCESSOR_LIST, TofuStructures::bootstrapProcessors)
			.add(Registries.STRUCTURE, TofuStructures::bootstrapStructures)
			.add(Registries.STRUCTURE_SET, TofuStructures::bootstrapSets)
			.add(Registries.TEMPLATE_POOL, TofuStructures::bootstrapPools)
			.add(Registries.CONFIGURED_CARVER, TofuConfiguredWorldCarvers::bootstrap)
			.add(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST, TofuBiomeSources::bootstrapPreset)
			.add(Registries.NOISE_SETTINGS, TofuNoiseBuilder::bootstrap)
			.add(Registries.DIMENSION_TYPE, TofuDimensionTypes::bootstrap)
			.add(Registries.BIOME, TofuBiomes::bootstrap)
			.add(Registries.LEVEL_STEM, TofuLevelStems::bootstrapLevelStem)
			.add(Registries.TRIM_MATERIAL, TofuTrimMaterials::bootstrap)
			.add(Registries.DAMAGE_TYPE, TofuDamageTypes::bootstrap)
			.add(Registries.ENCHANTMENT, TofuEnchantments::bootstrap)
			.add(Registries.BANNER_PATTERN, TofuBannerPatterns::bootstrap)
			.add(TofunianVariants.TOFUNIAN_VARIANT_REGISTRY_KEY, TofunianVariants::bootstrap);


	public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, BUILDER, Set.of("minecraft", TofuCraftReload.MODID));
	}
}
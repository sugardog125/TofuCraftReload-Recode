package baguchi.tofucraft.world.gen.features;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModPaleFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> PALE_SOYBEAN = registerKey("pale_soybean");


	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, PALE_SOYBEAN, Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(TofuBlocks.SOYBEAN_PALE.get().defaultBlockState().setValue(SoybeanNetherCropsBlock.AGE, 3)), 32));
	}

	private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
		return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
	}
}
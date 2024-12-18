package baguchi.tofucraft.world.gen.features;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.crop.SoybeanPaleCropsBlock;
import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static net.minecraft.data.worldgen.placement.PlacementUtils.filtered;
import static net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate.matchesBlocks;

public class ModPaleFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> PALE_SOYBEAN = registerKey("pale_soybean");
	public static final BlockPredicate ONLY_IN_AIR_OR_GRASS_PREDICATE = matchesBlocks(Blocks.AIR, Blocks.SHORT_GRASS, Blocks.TALL_GRASS);


	public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, PALE_SOYBEAN, Feature.RANDOM_PATCH, new RandomPatchConfiguration(
				64,
				7,
				3,
				onlyWhenEmptyOrGrass(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(TofuBlocks.SOYBEAN_PALE.get().defaultBlockState().setValue(SoybeanPaleCropsBlock.AGE, 3)), true))));
	}

	private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
		return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_)));
	}

	public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<PlacedFeature> onlyWhenEmptyOrGrass(F p_206496_, FC p_206497_) {
		return filtered(p_206496_, p_206497_, ONLY_IN_AIR_OR_GRASS_PREDICATE);
	}

}
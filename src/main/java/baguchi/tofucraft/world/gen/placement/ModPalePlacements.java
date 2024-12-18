package baguchi.tofucraft.world.gen.placement;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.world.gen.features.ModPaleFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class ModPalePlacements {

	public static final PlacementModifier NETHER_SOYBEAN_HEIGHT = HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.belowTop(-20));

	public static final ResourceKey<PlacedFeature> PATCH_PALE_SOYBEAN = registerKey("patch_pale_soybean");

	public static ResourceKey<PlacedFeature> registerKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, TofuCraftReload.prefix(name));
	}

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE);
		PlacementUtils.register(context, PATCH_PALE_SOYBEAN, configuredFeature.getOrThrow(ModPaleFeatures.PALE_SOYBEAN), List.of(RarityFilter.onAverageOnceEvery(3),
				InSquarePlacement.spread(),
				PlacementUtils.HEIGHTMAP,
				BiomeFilter.biome()));
	}
}

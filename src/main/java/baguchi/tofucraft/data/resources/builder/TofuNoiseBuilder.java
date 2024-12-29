package baguchi.tofucraft.data.resources.builder;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.data.resources.TofuTerrainProvider;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuNoiseSettings;
import baguchi.tofucraft.world.gen.TofuSurfaceRuleData;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.DensityFunctions;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;

public class TofuNoiseBuilder {

	private static final DensityFunction BLENDING_FACTOR = DensityFunctions.constant(10.0D);
	private static final DensityFunction BLENDING_JAGGEDNESS = DensityFunctions.zero();
	private static final ResourceKey<DensityFunction> ZERO = createKey("zero");
	private static final ResourceKey<DensityFunction> Y = createKey("y");
	private static final ResourceKey<DensityFunction> SHIFT_X = createKey("shift_x");
	private static final ResourceKey<DensityFunction> SHIFT_Z = createKey("shift_z");
	public static final ResourceKey<DensityFunction> CONTINENTS = createModKey("tofu_world/continents");
	public static final ResourceKey<DensityFunction> EROSION = createModKey("tofu_world/erosion");
	public static final ResourceKey<DensityFunction> RIDGES = createKey("overworld/ridges");
	public static final ResourceKey<DensityFunction> FACTOR = createModKey("tofu_world/factor");
	public static final ResourceKey<DensityFunction> DEPTH = createModKey("tofu_world/depth");
	private static final ResourceKey<DensityFunction> BASE_3D_NOISE_END = createKey("end/base_3d_noise");
	public static final ResourceKey<DensityFunction> RIDGES_FOLDED = createKey("overworld/ridges_folded");
	public static final ResourceKey<DensityFunction> OFFSET = createModKey("tofu_world/offset");
	public static final ResourceKey<DensityFunction> JAGGEDNESS = createModKey("tofu_world/jaggedness");

	public static final ResourceKey<DensityFunction> SLOPED_CHEESE = createKey("tofu_world/sloped_cheese");
	private static final ResourceKey<DensityFunction> SPAGHETTI_ROUGHNESS_FUNCTION = createKey("overworld/caves/spaghetti_roughness_function");
	private static final ResourceKey<DensityFunction> ENTRANCES = createKey("overworld/caves/entrances");
	private static final ResourceKey<DensityFunction> NOODLE = createKey("overworld/caves/noodle");
	private static final ResourceKey<DensityFunction> PILLARS = createKey("overworld/caves/pillars");
	private static final ResourceKey<DensityFunction> SPAGHETTI_2D = createKey("overworld/caves/spaghetti_2d");

	private static final ResourceKey<DensityFunction> BASE_3D_NOISE_DEEP_CLIFF = createModKey("tofu_world/base_3d_noise");

	public static NoiseGeneratorSettings tofuWorld(BootstrapContext<NoiseGeneratorSettings> p_256478_) {
		return new NoiseGeneratorSettings(new NoiseSettings(-64, 384, 1, 2), TofuBlocks.TOFU_TERRAIN.get().defaultBlockState(), TofuBlocks.SOYMILK.get().defaultBlockState(), TofuNoiseBuilder.overworld(p_256478_.lookup(Registries.DENSITY_FUNCTION), p_256478_.lookup(Registries.NOISE), false, false), TofuSurfaceRuleData.tofuWorld(), List.of(), 63, false, true, false, false);
	}

	public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> p_256365_) {
		p_256365_.register(TofuNoiseSettings.TOFU_WORLD, TofuNoiseBuilder.tofuWorld(p_256365_));
	}

	private static ResourceKey<DensityFunction> createKey(String p_209537_) {
		return ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.parse(p_209537_));
	}

	private static ResourceKey<DensityFunction> createModKey(String p_209537_) {
		return ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, p_209537_));
	}


	private static DensityFunction getFunction(HolderGetter<DensityFunction> p_256312_, ResourceKey<DensityFunction> p_256077_) {
		return new DensityFunctions.HolderHolder(p_256312_.getOrThrow(p_256077_));
	}

	public static void bootstrapDensity(BootstrapContext<DensityFunction> p_256220_) {
		HolderGetter<NormalNoise.NoiseParameters> $$1 = p_256220_.lookup(Registries.NOISE);
		HolderGetter<DensityFunction> $$2 = p_256220_.lookup(Registries.DENSITY_FUNCTION);
		int $$3 = DimensionType.MIN_Y * 2;
		int $$4 = DimensionType.MAX_Y * 2;
		DensityFunction $$5 = getFunction($$2, SHIFT_X);
		DensityFunction $$6 = getFunction($$2, SHIFT_Z);
		p_256220_.register(BASE_3D_NOISE_DEEP_CLIFF, BlendedNoise.createUnseeded(0.25, 0.125, 80.0, 160.0, 8.0));
		Holder<DensityFunction> $$7 = p_256220_.register(CONTINENTS, DensityFunctions.add(DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d($$5, $$6, 0.25, $$1.getOrThrow(Noises.CONTINENTALNESS))), DensityFunctions.constant(0.25F)));
		Holder<DensityFunction> $$8 = p_256220_.register(EROSION, DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d($$5, $$6, 0.25, $$1.getOrThrow(Noises.EROSION))));
		DensityFunction $$9 = getFunction($$2, RIDGES);
		DensityFunction $$10 = DensityFunctions.noise($$1.getOrThrow(Noises.JAGGED), 1500.0, 0.0);
		registerTerrainNoises(p_256220_, $$2, $$10, $$7, $$8, OFFSET, FACTOR, JAGGEDNESS, DEPTH, SLOPED_CHEESE, false);
	}

	private static void registerTerrainNoises(BootstrapContext<DensityFunction> p_256336_, HolderGetter<DensityFunction> p_256393_, DensityFunction p_224476_, Holder<DensityFunction> p_224477_, Holder<DensityFunction> p_224478_, ResourceKey<DensityFunction> p_224479_, ResourceKey<DensityFunction> p_224480_, ResourceKey<DensityFunction> p_224481_, ResourceKey<DensityFunction> p_224482_, ResourceKey<DensityFunction> p_224483_, boolean p_224484_) {
		DensityFunctions.Spline.Coordinate $$11 = new DensityFunctions.Spline.Coordinate(p_224477_);
		DensityFunctions.Spline.Coordinate $$12 = new DensityFunctions.Spline.Coordinate(p_224478_);
		DensityFunctions.Spline.Coordinate $$13 = new DensityFunctions.Spline.Coordinate(p_256393_.getOrThrow(RIDGES));
		DensityFunctions.Spline.Coordinate $$14 = new DensityFunctions.Spline.Coordinate(p_256393_.getOrThrow(RIDGES_FOLDED));
		DensityFunction $$15 = registerAndWrap(p_256336_, p_224479_, splineWithBlending(DensityFunctions.add(DensityFunctions.constant(-0.5037500262260437), DensityFunctions.spline(TofuTerrainProvider.overworldOffset($$11, $$12, $$14, p_224484_))), DensityFunctions.blendOffset()));
		DensityFunction $$16 = registerAndWrap(p_256336_, p_224480_, splineWithBlending(DensityFunctions.spline(TofuTerrainProvider.overworldFactor($$11, $$12, $$13, $$14, p_224484_)), BLENDING_FACTOR));
		DensityFunction $$17 = registerAndWrap(p_256336_, p_224482_, DensityFunctions.add(DensityFunctions.yClampedGradient(-64, 320, 1.5, -1.5), $$15));
		DensityFunction $$18 = registerAndWrap(p_256336_, p_224481_, splineWithBlending(DensityFunctions.spline(TofuTerrainProvider.overworldJaggedness($$11, $$12, $$13, $$14, p_224484_)), BLENDING_JAGGEDNESS));
		DensityFunction $$19 = DensityFunctions.mul($$18, p_224476_.halfNegative());
		DensityFunction $$20 = noiseGradientDensity($$16, DensityFunctions.add($$17, $$19));
		p_256336_.register(p_224483_, DensityFunctions.add($$20, getFunction(p_256393_, BASE_3D_NOISE_DEEP_CLIFF)));
	}

	private static DensityFunction registerAndWrap(BootstrapContext<DensityFunction> p_256149_, ResourceKey<DensityFunction> p_255905_, DensityFunction p_255856_) {
		return new DensityFunctions.HolderHolder(p_256149_.register(p_255905_, p_255856_));
	}

	private static DensityFunction underground(HolderGetter<DensityFunction> p_256548_, HolderGetter<NormalNoise.NoiseParameters> p_256236_, DensityFunction p_256658_) {
		DensityFunction densityfunction = getFunction(p_256548_, SPAGHETTI_2D);
		DensityFunction densityfunction1 = getFunction(p_256548_, SPAGHETTI_ROUGHNESS_FUNCTION);
		DensityFunction densityfunction2 = DensityFunctions.noise(p_256236_.getOrThrow(Noises.CAVE_LAYER), 8.0D);
		DensityFunction densityfunction3 = DensityFunctions.mul(DensityFunctions.constant(4.0D), densityfunction2.square());
		DensityFunction densityfunction4 = DensityFunctions.noise(p_256236_.getOrThrow(Noises.CAVE_CHEESE), 0.6666666666666666D);
		DensityFunction densityfunction5 = DensityFunctions.add(DensityFunctions.add(DensityFunctions.constant(0.5D), densityfunction4).clamp(-1.0D, 1.0D), DensityFunctions.add(DensityFunctions.constant(-0.2D), DensityFunctions.mul(DensityFunctions.constant(-0.64D), p_256658_)).clamp(-0.25D, 0.5D));
		DensityFunction densityfunction6 = DensityFunctions.add(densityfunction3, densityfunction5);
		DensityFunction densityfunction7 = DensityFunctions.min(DensityFunctions.min(densityfunction6, getFunction(p_256548_, ENTRANCES)), DensityFunctions.add(densityfunction, densityfunction1));
		DensityFunction densityfunction8 = getFunction(p_256548_, PILLARS);
		DensityFunction densityfunction9 = DensityFunctions.rangeChoice(densityfunction8, -1000000.0D, 0.06D, DensityFunctions.constant(-1000000.0D), densityfunction8);
		return DensityFunctions.max(densityfunction7, densityfunction9);
	}

	private static DensityFunction postProcess(DensityFunction p_224493_) {
		DensityFunction densityfunction = DensityFunctions.blendDensity(p_224493_);
		return DensityFunctions.mul(DensityFunctions.interpolated(densityfunction), DensityFunctions.constant(0.64D)).squeeze();
	}

	public static NoiseRouter overworld(HolderGetter<DensityFunction> p_255681_, HolderGetter<NormalNoise.NoiseParameters> p_256005_, boolean p_255649_, boolean p_255617_) {
		DensityFunction densityfunction = DensityFunctions.noise(p_256005_.getOrThrow(Noises.AQUIFER_BARRIER), 0.5D);
		DensityFunction densityfunction1 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67D);
		DensityFunction densityfunction2 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143D);
		DensityFunction densityfunction3 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.AQUIFER_LAVA));
		DensityFunction densityfunction4 = getFunction(p_255681_, SHIFT_X);
		DensityFunction densityfunction5 = getFunction(p_255681_, SHIFT_Z);
		DensityFunction densityfunction6 = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D, p_256005_.getOrThrow(p_255649_ ? Noises.TEMPERATURE_LARGE : Noises.TEMPERATURE));
		DensityFunction densityfunction7 = DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, 0.25D, p_256005_.getOrThrow(p_255649_ ? Noises.VEGETATION_LARGE : Noises.VEGETATION));
		DensityFunction densityfunction8 = getFunction(p_255681_, FACTOR);
		DensityFunction densityfunction9 = getFunction(p_255681_, DEPTH);
		DensityFunction densityfunction10 = noiseGradientDensity(DensityFunctions.cache2d(densityfunction8), densityfunction9);
		DensityFunction densityfunction11 = getFunction(p_255681_, SLOPED_CHEESE);
		DensityFunction densityfunction12 = DensityFunctions.min(densityfunction11, DensityFunctions.mul(DensityFunctions.constant(5.0D), getFunction(p_255681_, ENTRANCES)));
		DensityFunction densityfunction13 = DensityFunctions.rangeChoice(densityfunction11, -1000000.0D, 1.5625D, densityfunction12, underground(p_255681_, p_256005_, densityfunction11));
		DensityFunction densityfunction14 = DensityFunctions.min(postProcess(slideOverworld(p_255617_, densityfunction13)), getFunction(p_255681_, NOODLE));
		DensityFunction densityfunction15 = getFunction(p_255681_, Y);
		DensityFunction densityfunction16 = DensityFunctions.zero();
		float f = 4.0F;
		DensityFunction densityfunction19 = DensityFunctions.zero();
		DensityFunction densityfunction20 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.ORE_GAP));
		return new NoiseRouter(densityfunction, densityfunction1, densityfunction2, densityfunction3, densityfunction6, densityfunction7, getFunction(p_255681_, CONTINENTS), getFunction(p_255681_, EROSION), densityfunction9, getFunction(p_255681_, RIDGES), slideOverworld(p_255617_, DensityFunctions.add(densityfunction10, DensityFunctions.constant(-0.703125D)).clamp(-64.0D, 64.0D)), densityfunction14, densityfunction16, densityfunction19, densityfunction20);
	}

	private static DensityFunction slideOverworld(boolean p_224490_, DensityFunction p_224491_) {
		return slide(p_224491_, -64, 384, p_224490_ ? 16 : 80, p_224490_ ? 0 : 64, -0.078125D, 0, 24, p_224490_ ? 0.4D : 0.1171875D);
	}

	protected static NoiseRouter none() {
		return new NoiseRouter(DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero(), DensityFunctions.zero());
	}

	private static DensityFunction splineWithBlending(DensityFunction p_224454_, DensityFunction p_224455_) {
		DensityFunction densityfunction = DensityFunctions.lerp(DensityFunctions.blendAlpha(), p_224455_, p_224454_);
		return DensityFunctions.flatCache(DensityFunctions.cache2d(densityfunction));
	}

	private static DensityFunction noiseGradientDensity(DensityFunction p_212272_, DensityFunction p_212273_) {
		DensityFunction densityfunction = DensityFunctions.mul(p_212273_, p_212272_);
		return DensityFunctions.mul(DensityFunctions.constant(4.0D), densityfunction.quarterNegative());
	}

	private static DensityFunction yLimitedInterpolatable(DensityFunction p_209472_, DensityFunction p_209473_, int p_209474_, int p_209475_, int p_209476_) {
		return DensityFunctions.interpolated(DensityFunctions.rangeChoice(p_209472_, (double) p_209474_, (double) (p_209475_ + 1), p_209473_, DensityFunctions.constant((double) p_209476_)));
	}

	private static DensityFunction slide(DensityFunction p_224444_, int p_224445_, int p_224446_, int p_224447_, int p_224448_, double p_224449_, int p_224450_, int p_224451_, double p_224452_) {
		DensityFunction densityfunction1 = DensityFunctions.yClampedGradient(p_224445_ + p_224446_ - p_224447_, p_224445_ + p_224446_ - p_224448_, 1.0D, 0.0D);
		DensityFunction $$9 = DensityFunctions.lerp(densityfunction1, p_224449_, p_224444_);
		DensityFunction densityfunction2 = DensityFunctions.yClampedGradient(p_224445_ + p_224450_, p_224445_ + p_224451_, 0.0D, 1.0D);
		return DensityFunctions.lerp(densityfunction2, p_224452_, $$9);
	}
}

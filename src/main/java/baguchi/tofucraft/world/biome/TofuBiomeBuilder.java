package baguchi.tofucraft.world.biome;

import baguchi.tofucraft.registry.TofuBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.List;
import java.util.function.Consumer;

public class TofuBiomeBuilder {
	private static final float VALLEY_SIZE = 0.05F;
	private static final float LOW_START = 0.26666668F;
	public static final float HIGH_START = 0.4F;
	private static final float HIGH_END = 0.93333334F;
	private static final float PEAK_SIZE = 0.1F;
	public static final float PEAK_START = 0.56666666F;
	private static final float PEAK_END = 0.7666667F;
	public static final float NEAR_INLAND_START = -0.11F;
	public static final float MID_INLAND_START = 0.03F;
	public static final float FAR_INLAND_START = 0.3F;
	public static final float EROSION_INDEX_1_START = -0.78F;
	public static final float EROSION_INDEX_2_START = -0.375F;
	private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);
	private final Climate.Parameter[] temperatures = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.45F), Climate.Parameter.span(-0.45F, -0.15F), Climate.Parameter.span(-0.15F, 0.2F), Climate.Parameter.span(0.2F, 0.55F), Climate.Parameter.span(0.55F, 1.0F)};
	private final Climate.Parameter[] humidities = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.35F), Climate.Parameter.span(-0.35F, -0.1F), Climate.Parameter.span(-0.1F, 0.1F), Climate.Parameter.span(0.1F, 0.3F), Climate.Parameter.span(0.3F, 1.0F)};
	private final Climate.Parameter[] erosions = new Climate.Parameter[]{Climate.Parameter.span(-1.0F, -0.78F), Climate.Parameter.span(-0.78F, -0.375F), Climate.Parameter.span(-0.375F, -0.2225F), Climate.Parameter.span(-0.2225F, 0.05F), Climate.Parameter.span(0.05F, 0.45F), Climate.Parameter.span(0.45F, 0.55F), Climate.Parameter.span(0.55F, 1.0F)};
	private final Climate.Parameter FROZEN_RANGE = this.temperatures[0];
	private final Climate.Parameter UNFROZEN_RANGE = Climate.Parameter.span(this.temperatures[1], this.temperatures[4]);
	private final Climate.Parameter mushroomFieldsContinentalness = Climate.Parameter.span(-1.2F, -1.05F);
	private final Climate.Parameter deepOceanContinentalness = Climate.Parameter.span(-1.05F, -0.455F);
	private final Climate.Parameter oceanContinentalness = Climate.Parameter.span(-0.455F, -0.19F);
	private final Climate.Parameter coastContinentalness = Climate.Parameter.span(-0.19F, -0.11F);
	private final Climate.Parameter inlandContinentalness = Climate.Parameter.span(-0.11F, 0.55F);
	private final Climate.Parameter nearInlandContinentalness = Climate.Parameter.span(-0.11F, 0.03F);
	private final Climate.Parameter midInlandContinentalness = Climate.Parameter.span(0.03F, 0.3F);
	private final Climate.Parameter farInlandContinentalness = Climate.Parameter.span(0.3F, 1.0F);

	private final ResourceKey<Biome>[][] MIDDLE_BIOMES = new ResourceKey[][]{{TofuBiomes.TOFU_PLAINS, TofuBiomes.TOFU_PLAINS, TofuBiomes.TOFU_PLAINS, TofuBiomes.TOFU_FOREST, TofuBiomes.TOFU_FOREST}, {TofuBiomes.TOFU_PLAINS, TofuBiomes.TOFU_PLAINS, TofuBiomes.TOFU_FOREST, TofuBiomes.TOFU_FOREST, TofuBiomes.TOFU_FOREST}, {TofuBiomes.TOFU_PLAINS, TofuBiomes.TOFU_PLAINS, TofuBiomes.TOFU_FOREST, TofuBiomes.TOFU_FOREST, TofuBiomes.TOFU_FOREST}, {TofuBiomes.ZUNDA_FOREST, TofuBiomes.ZUNDA_FOREST, TofuBiomes.ZUNDA_FOREST, TofuBiomes.SOYBEAN_FOREST, TofuBiomes.SOYBEAN_FOREST}, {TofuBiomes.TOFU_WASTES, TofuBiomes.TOFU_WASTES, TofuBiomes.TOFU_WASTES, TofuBiomes.TOFU_WASTES, TofuBiomes.TOFU_WASTES}};
	private final ResourceKey<Biome>[][] MIDDLE_BIOMES_VARIANT = new ResourceKey[][]{{null, null, null, null, null}, {null, null, null, null, null}, {null, null, null, null, null}, {null, null, TofuBiomes.TOFU_PLAINS, TofuBiomes.SOYBEAN_FOREST_SPARSE, TofuBiomes.SOYBEAN_FOREST_SPARSE}, {null, null, null, null, null}};
	private final ResourceKey<Biome>[][] PLATEAU_BIOMES = new ResourceKey[][]{{TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_PLAINS, TofuBiomes.TOFU_FOREST, TofuBiomes.TOFU_FOREST}, {TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_FOREST, TofuBiomes.TOFU_FOREST, TofuBiomes.TOFU_FOREST}, {TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN}, {TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN}, {TofuBiomes.MABOU_MOUNTAIN, TofuBiomes.MABOU_MOUNTAIN, TofuBiomes.MABOU_MOUNTAIN, TofuBiomes.MABOU_MOUNTAIN, TofuBiomes.MABOU_MOUNTAIN}};
	private final ResourceKey<Biome>[][] PLATEAU_BIOMES_VARIANT = new ResourceKey[][]{{null, null, null, null, null}, {null, null, null, null, null}, {null, null, null, null, null}, {null, null, TofuBiomes.TOFU_FOREST, TofuBiomes.TOFU_FOREST, null}, {null, null, null, null, null}};
	private final ResourceKey<Biome>[][] EXTREME_HILLS = new ResourceKey[][]{{TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_FOREST, TofuBiomes.TOFU_FOREST}, {TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_FOREST, TofuBiomes.TOFU_FOREST}, {TofuBiomes.MABOU_MOUNTAIN, TofuBiomes.MABOU_MOUNTAIN, TofuBiomes.TOFU_MOUNTAIN, TofuBiomes.TOFU_FOREST, TofuBiomes.TOFU_FOREST}, {null, null, null, null, null}, {null, null, null, null, null}};

	public List<Climate.ParameterPoint> spawnTarget() {
		Climate.Parameter climate$parameter = Climate.Parameter.point(0.0F);
		float f = 0.16F;
		return List.of(new Climate.ParameterPoint(this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.FULL_RANGE), this.FULL_RANGE, climate$parameter, Climate.Parameter.span(-1.0F, -0.16F), 0L), new Climate.ParameterPoint(this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.FULL_RANGE), this.FULL_RANGE, climate$parameter, Climate.Parameter.span(0.16F, 1.0F), 0L));
	}

	public void addBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187176_) {
		this.addOffCoastBiomes(p_187176_);
		this.addInlandBiomes(p_187176_);
		this.addUndergroundBiomes(p_187176_);
	}

	private void addOffCoastBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187196_) {
		this.addSurfaceBiome(p_187196_, this.FULL_RANGE, this.FULL_RANGE, this.mushroomFieldsContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, TofuBiomes.TOFU_PLAINS);

		this.addSurfaceBiome(p_187196_, this.FULL_RANGE, this.FULL_RANGE, this.deepOceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, TofuBiomes.TOFU_OCEAN);
		this.addSurfaceBiome(p_187196_, this.FULL_RANGE, this.FULL_RANGE, this.oceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, TofuBiomes.TOFU_OCEAN);
	}

	private void addInlandBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187216_) {
		this.addMidSlice(p_187216_, Climate.Parameter.span(-1.0F, -0.93333334F));
		this.addHighSlice(p_187216_, Climate.Parameter.span(-0.93333334F, -0.7666667F));
		this.addPeaks(p_187216_, Climate.Parameter.span(-0.7666667F, -0.56666666F));
		this.addHighSlice(p_187216_, Climate.Parameter.span(-0.56666666F, -0.4F));
		this.addMidSlice(p_187216_, Climate.Parameter.span(-0.4F, -0.26666668F));
		this.addLowSlice(p_187216_, Climate.Parameter.span(-0.26666668F, -0.05F));
		this.addValleys(p_187216_, Climate.Parameter.span(-0.05F, 0.05F));
		this.addLowSlice(p_187216_, Climate.Parameter.span(0.05F, 0.26666668F));
		this.addMidSlice(p_187216_, Climate.Parameter.span(0.26666668F, 0.4F));
		this.addHighSlice(p_187216_, Climate.Parameter.span(0.4F, 0.56666666F));
		this.addPeaks(p_187216_, Climate.Parameter.span(0.56666666F, 0.7666667F));
		this.addHighSlice(p_187216_, Climate.Parameter.span(0.7666667F, 0.93333334F));
		this.addMidSlice(p_187216_, Climate.Parameter.span(0.93333334F, 1.0F));
	}

	private void addPeaks(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187178_, Climate.Parameter p_187179_) {
		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter climate$parameter = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter climate$parameter1 = this.humidities[j];
				ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, p_187179_);
				ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, p_187179_);
				ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, p_187179_);
				ResourceKey<Biome> resourcekey3 = this.pickPlateauBiome(i, j, p_187179_);
				ResourceKey<Biome> resourcekey4 = this.pickExtremeHillsBiome(i, j, p_187179_);
				ResourceKey<Biome> resourcekey5 = this.maybePickShatteredBiome(i, j, p_187179_, resourcekey4);
				ResourceKey<Biome> resourcekey6 = this.pickPeakBiome(i, j, p_187179_);
				this.addSurfaceBiome(p_187178_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[0], p_187179_, 0.0F, resourcekey6);
				this.addSurfaceBiome(p_187178_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[1], p_187179_, 0.0F, resourcekey2);
				this.addSurfaceBiome(p_187178_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], p_187179_, 0.0F, resourcekey6);
				this.addSurfaceBiome(p_187178_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), p_187179_, 0.0F, resourcekey);
				this.addSurfaceBiome(p_187178_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], p_187179_, 0.0F, resourcekey3);
				this.addSurfaceBiome(p_187178_, climate$parameter, climate$parameter1, this.midInlandContinentalness, this.erosions[3], p_187179_, 0.0F, resourcekey1);
				this.addSurfaceBiome(p_187178_, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[3], p_187179_, 0.0F, resourcekey3);
				this.addSurfaceBiome(p_187178_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], p_187179_, 0.0F, resourcekey);
				this.addSurfaceBiome(p_187178_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], p_187179_, 0.0F, resourcekey5);
				this.addSurfaceBiome(p_187178_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], p_187179_, 0.0F, resourcekey4);
				this.addSurfaceBiome(p_187178_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], p_187179_, 0.0F, resourcekey);
			}
		}

	}

	private void addHighSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187198_, Climate.Parameter p_187199_) {
		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter climate$parameter = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter climate$parameter1 = this.humidities[j];
				ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, p_187199_);
				ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, p_187199_);
				ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, p_187199_);
				ResourceKey<Biome> resourcekey3 = this.pickPlateauBiome(i, j, p_187199_);
				ResourceKey<Biome> resourcekey4 = this.pickExtremeHillsBiome(i, j, p_187199_);
				ResourceKey<Biome> resourcekey5 = this.maybePickShatteredBiome(i, j, p_187199_, resourcekey);
				ResourceKey<Biome> resourcekey6 = this.pickSlopeBiome(i, j, p_187199_);
				ResourceKey<Biome> resourcekey7 = this.pickPeakBiome(i, j, p_187199_);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187199_, 0.0F, resourcekey);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[0], p_187199_, 0.0F, resourcekey6);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[0], p_187199_, 0.0F, resourcekey7);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[1], p_187199_, 0.0F, resourcekey2);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[1], p_187199_, 0.0F, resourcekey6);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), p_187199_, 0.0F, resourcekey);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[2], p_187199_, 0.0F, resourcekey3);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, this.midInlandContinentalness, this.erosions[3], p_187199_, 0.0F, resourcekey1);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[3], p_187199_, 0.0F, resourcekey3);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], p_187199_, 0.0F, resourcekey);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[5], p_187199_, 0.0F, resourcekey5);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], p_187199_, 0.0F, resourcekey4);
				this.addSurfaceBiome(p_187198_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], p_187199_, 0.0F, resourcekey);
			}
		}

	}

	private void addMidSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187218_, Climate.Parameter p_187219_) {
		this.addSurfaceBiome(p_187218_, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), p_187219_, 0.0F, TofuBiomes.TOFU_BEACH);
		//Replace swamp to something
		//this.addSurfaceBiome(p_187218_, Climate.Parameter.span(this.temperatures[1], this.temperatures[2]), this.FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], p_187219_, 0.0F, Biomes.SWAMP);
		//this.addSurfaceBiome(p_187218_, Climate.Parameter.span(this.temperatures[3], this.temperatures[4]), this.FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], p_187219_, 0.0F, Biomes.MANGROVE_SWAMP);


		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter climate$parameter = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter climate$parameter1 = this.humidities[j];
				ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, p_187219_);
				ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, p_187219_);
				ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, p_187219_);
				ResourceKey<Biome> resourcekey3 = this.pickExtremeHillsBiome(i, j, p_187219_);
				ResourceKey<Biome> resourcekey4 = this.pickPlateauBiome(i, j, p_187219_);
				ResourceKey<Biome> resourcekey5 = this.pickBeachBiome(i, j);
				ResourceKey<Biome> resourcekey6 = this.maybePickShatteredBiome(i, j, p_187219_, resourcekey);
				ResourceKey<Biome> resourcekey7 = this.pickShatteredCoastBiome(i, j, p_187219_);
				ResourceKey<Biome> resourcekey8 = this.pickSlopeBiome(i, j, p_187219_);
				this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[0], p_187219_, 0.0F, resourcekey8);
				this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[1], p_187219_, 0.0F, resourcekey2);
				this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[1], p_187219_, 0.0F, i == 0 ? resourcekey8 : resourcekey4);
				this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[2], p_187219_, 0.0F, resourcekey);
				this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, this.midInlandContinentalness, this.erosions[2], p_187219_, 0.0F, resourcekey1);
				this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, this.farInlandContinentalness, this.erosions[2], p_187219_, 0.0F, resourcekey4);
				this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], p_187219_, 0.0F, resourcekey);
				this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[3], p_187219_, 0.0F, resourcekey1);
				if (p_187219_.max() < 0L) {
					this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[4], p_187219_, 0.0F, resourcekey5);
					this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], p_187219_, 0.0F, resourcekey);
				} else {
					this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], p_187219_, 0.0F, resourcekey);
				}

				this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[5], p_187219_, 0.0F, resourcekey7);
				this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[5], p_187219_, 0.0F, resourcekey6);
				this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], p_187219_, 0.0F, resourcekey3);
				if (p_187219_.max() < 0L) {
					this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[6], p_187219_, 0.0F, resourcekey5);
				} else {
					this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[6], p_187219_, 0.0F, resourcekey);
				}

				if (i == 0) {
					this.addSurfaceBiome(p_187218_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], p_187219_, 0.0F, resourcekey);
				}
			}
		}

	}

	private void addLowSlice(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187229_, Climate.Parameter p_187230_) {
		this.addSurfaceBiome(p_187229_, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[2]), p_187230_, 0.0F, TofuBiomes.TOFU_BEACH);
		//Replace swamp to something
		//this.addSurfaceBiome(p_187229_, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], p_187230_, 0.0F, Biomes.SWAMP);

		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter climate$parameter = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter climate$parameter1 = this.humidities[j];
				ResourceKey<Biome> resourcekey = this.pickMiddleBiome(i, j, p_187230_);
				ResourceKey<Biome> resourcekey1 = this.pickMiddleBiomeOrBadlandsIfHot(i, j, p_187230_);
				ResourceKey<Biome> resourcekey2 = this.pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(i, j, p_187230_);
				ResourceKey<Biome> resourcekey3 = this.pickBeachBiome(i, j);
				ResourceKey<Biome> resourcekey4 = this.maybePickShatteredBiome(i, j, p_187230_, resourcekey);
				ResourceKey<Biome> resourcekey5 = this.pickShatteredCoastBiome(i, j, p_187230_);
				this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187230_, 0.0F, resourcekey1);
				this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187230_, 0.0F, resourcekey2);
				this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[2], this.erosions[3]), p_187230_, 0.0F, resourcekey);
				this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[3]), p_187230_, 0.0F, resourcekey1);
				this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, this.coastContinentalness, Climate.Parameter.span(this.erosions[3], this.erosions[4]), p_187230_, 0.0F, resourcekey3);
				this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], p_187230_, 0.0F, resourcekey);
				this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[5], p_187230_, 0.0F, resourcekey5);
				this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, this.nearInlandContinentalness, this.erosions[5], p_187230_, 0.0F, resourcekey4);
				this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], p_187230_, 0.0F, resourcekey);
				this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, this.coastContinentalness, this.erosions[6], p_187230_, 0.0F, resourcekey3);
				if (i == 0) {
					this.addSurfaceBiome(p_187229_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], p_187230_, 0.0F, resourcekey);
				}
			}
		}

	}

	private void addValleys(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187238_, Climate.Parameter p_187239_) {
		this.addSurfaceBiome(p_187238_, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187239_, 0.0F, p_187239_.max() < 0L ? TofuBiomes.TOFU_BEACH : TofuBiomes.TOFU_RIVER);
		this.addSurfaceBiome(p_187238_, this.FULL_RANGE, this.FULL_RANGE, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187239_, 0.0F, TofuBiomes.TOFU_RIVER);

		this.addSurfaceBiome(p_187238_, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[2], this.erosions[5]), p_187239_, 0.0F, TofuBiomes.TOFU_RIVER);
		this.addSurfaceBiome(p_187238_, this.FULL_RANGE, this.FULL_RANGE, this.coastContinentalness, this.erosions[6], p_187239_, 0.0F, TofuBiomes.TOFU_RIVER);
		//this.addSurfaceBiome(p_187238_, this.UNFROZEN_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], p_187239_, 0.0F, Biomes.SWAMP);
		this.addSurfaceBiome(p_187238_, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[6], p_187239_, 0.0F, TofuBiomes.TOFU_RIVER);

		for (int i = 0; i < this.temperatures.length; ++i) {
			Climate.Parameter climate$parameter = this.temperatures[i];

			for (int j = 0; j < this.humidities.length; ++j) {
				Climate.Parameter climate$parameter1 = this.humidities[j];
				ResourceKey<Biome> resourcekey = this.pickMiddleBiomeOrBadlandsIfHot(i, j, p_187239_);
				this.addSurfaceBiome(p_187238_, climate$parameter, climate$parameter1, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), p_187239_, 0.0F, resourcekey);
			}
		}

	}

	private void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187227_) {
		//this.addUndergroundBiome(p_187227_, this.FULL_RANGE, this.FULL_RANGE, Climate.Parameter.span(0.8F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, 0.0F, Biomes.DRIPSTONE_CAVES);
		//this.addUndergroundBiome(p_187227_, this.FULL_RANGE, Climate.Parameter.span(0.7F, 1.0F), this.FULL_RANGE, this.FULL_RANGE, this.FULL_RANGE, 0.0F, Biomes.LUSH_CAVES);
	}

	private ResourceKey<Biome> pickMiddleBiome(int p_187164_, int p_187165_, Climate.Parameter p_187166_) {
		if (p_187166_.max() < 0L) {
			return this.MIDDLE_BIOMES[p_187164_][p_187165_];
		} else {
			ResourceKey<Biome> resourcekey = this.MIDDLE_BIOMES_VARIANT[p_187164_][p_187165_];
			return resourcekey == null ? this.MIDDLE_BIOMES[p_187164_][p_187165_] : resourcekey;
		}
	}

	private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHot(int p_187192_, int p_187193_, Climate.Parameter p_187194_) {
		return this.pickMiddleBiome(p_187192_, p_187193_, p_187194_);
	}

	private ResourceKey<Biome> pickMiddleBiomeOrBadlandsIfHotOrSlopeIfCold(int p_187212_, int p_187213_, Climate.Parameter p_187214_) {
		return p_187212_ == 0 ? this.pickSlopeBiome(p_187212_, p_187213_, p_187214_) : this.pickMiddleBiomeOrBadlandsIfHot(p_187212_, p_187213_, p_187214_);
	}

	private ResourceKey<Biome> maybePickShatteredBiome(int p_187168_, int p_187169_, Climate.Parameter p_187170_, ResourceKey<Biome> p_187171_) {
		return p_187171_;
	}

	private ResourceKey<Biome> pickShatteredCoastBiome(int p_187223_, int p_187224_, Climate.Parameter p_187225_) {
		ResourceKey<Biome> resourcekey = p_187225_.max() >= 0L ? this.pickMiddleBiome(p_187223_, p_187224_, p_187225_) : this.pickBeachBiome(p_187223_, p_187224_);
		return this.maybePickShatteredBiome(p_187223_, p_187224_, p_187225_, resourcekey);
	}

	private ResourceKey<Biome> pickBeachBiome(int p_187161_, int p_187162_) {
		if (p_187161_ == 0) {
			return TofuBiomes.TOFU_BEACH;
		} else {
			return TofuBiomes.TOFU_BEACH;
		}
	}

	private ResourceKey<Biome> pickPlateauBiome(int p_187234_, int p_187235_, Climate.Parameter p_187236_) {
		if (p_187236_.max() < 0L) {
			return this.PLATEAU_BIOMES[p_187234_][p_187235_];
		} else {
			ResourceKey<Biome> resourcekey = this.PLATEAU_BIOMES_VARIANT[p_187234_][p_187235_];
			return resourcekey == null ? this.PLATEAU_BIOMES[p_187234_][p_187235_] : resourcekey;
		}
	}

	private ResourceKey<Biome> pickPeakBiome(int p_187241_, int p_187242_, Climate.Parameter p_187243_) {
		return this.pickPlateauBiome(p_187241_, p_187242_, p_187243_);
	}

	private ResourceKey<Biome> pickSlopeBiome(int p_187245_, int p_187246_, Climate.Parameter p_187247_) {
		return this.pickPlateauBiome(p_187245_, p_187246_, p_187247_);
	}

	private ResourceKey<Biome> pickExtremeHillsBiome(int p_187249_, int p_187250_, Climate.Parameter p_187251_) {
		ResourceKey<Biome> resourcekey = this.EXTREME_HILLS[p_187249_][p_187250_];
		return resourcekey == null ? this.pickMiddleBiome(p_187249_, p_187250_, p_187251_) : resourcekey;
	}

	private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187181_, Climate.Parameter p_187182_, Climate.Parameter p_187183_, Climate.Parameter p_187184_, Climate.Parameter p_187185_, Climate.Parameter p_187186_, float p_187187_, ResourceKey<Biome> p_187188_) {
		p_187181_.accept(Pair.of(Climate.parameters(p_187182_, p_187183_, p_187184_, p_187185_, Climate.Parameter.point(0.0F), p_187186_, p_187187_), p_187188_));
		p_187181_.accept(Pair.of(Climate.parameters(p_187182_, p_187183_, p_187184_, p_187185_, Climate.Parameter.point(1.0F), p_187186_, p_187187_), p_187188_));
	}

	private void addUndergroundBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187201_, Climate.Parameter p_187202_, Climate.Parameter p_187203_, Climate.Parameter p_187204_, Climate.Parameter p_187205_, Climate.Parameter p_187206_, float p_187207_, ResourceKey<Biome> p_187208_) {
		p_187201_.accept(Pair.of(Climate.parameters(p_187202_, p_187203_, p_187204_, p_187205_, Climate.Parameter.span(0.2F, 0.9F), p_187206_, p_187207_), p_187208_));
	}
}
package baguchi.tofucraft.data.resources.builder;

import baguchi.tofucraft.registry.TofuSounds;
import baguchi.tofucraft.world.biome.TofuBiomeDefaultFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class TofuBiomeBuilders {
	public static Biome zundaForestBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addZundaForestFeatures(builder);
		TofuBiomeDefaultFeatures.addDefaultCarvers(builder);
		TofuBiomeDefaultFeatures.addDefaultOres(builder);
		TofuBiomeDefaultFeatures.tofuCreatureSpawns(builder1);
		return fullDefinition(
				Biome.Precipitation.NONE,
				0.8F,
				0.0F,
				new BiomeSpecialEffects.Builder()
						.fogColor(0xAFFFCA)
						.skyColor(0xFFFFFF)
						.waterColor(0xDCF6E5)
						.waterFogColor(0x6ACF8D)
						.grassColorOverride(7115607)
						.foliageColorOverride(7115607)
						.grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
						.backgroundMusic(new Music(TofuSounds.GREEN_BRANCH_BGM, 12000, 24000, false))
						.build(),
				builder1.build(),
				builder.build(),
				Biome.TemperatureModifier.NONE
		);
	}

	public static Biome soybeanForestBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addSoybeanForestFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome soybeanForestSpareBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addSoybeanForestSpareFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome tofuWasteBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addWasteFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1, TofuSounds.ROUGH_GROUND_BGM);
	}

	public static Biome tofuBeachBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome tofuForestBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addForestFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1, TofuSounds.ROUGH_GROUND_BGM);
	}

	public static Biome tofuPlainBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1);
	}

	public static Biome tofuRiverBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		TofuBiomeDefaultFeatures.tofuWaterCreatureSpawns(builder1);
		return makeDefaultBiome(builder, builder1, TofuSounds.TOFU_ROAD_BGM);
	}

	public static Biome tofuOceanBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addPlainsFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		TofuBiomeDefaultFeatures.tofuWaterCreatureSpawns(builder1);
		return makeDefaultBiome(builder, builder1, TofuSounds.TOFU_ROAD_BGM);
	}

	public static Biome tofuMountainBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addMountainFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1, TofuSounds.MILKY_EARTH_BGM);
	}

	public static Biome mabouMountainBiome(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
		BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);
		MobSpawnSettings.Builder builder1 = new MobSpawnSettings.Builder();
		TofuBiomeDefaultFeatures.addMountainFeatures(builder);
		TofuBiomeDefaultFeatures.addMabouMountainFeatures(builder);
		TofuBiomeDefaultFeatures.tofuMonsterSpawns(builder1);
		return makeDefaultBiome(builder, builder1, TofuSounds.ROUGH_GROUND_BGM);
	}

	public static Biome makeDefaultBiome(BiomeGenerationSettings.Builder builder, MobSpawnSettings.Builder mobSpawnSetting) {
		return makeDefaultBiome(builder, mobSpawnSetting, TofuSounds.SOFT_BGM);
	}

	public static Biome makeDefaultBiome(BiomeGenerationSettings.Builder builder, MobSpawnSettings.Builder mobSpawnSetting, Holder<SoundEvent> soundEvent) {
		TofuBiomeDefaultFeatures.addDefaultCarvers(builder);
		TofuBiomeDefaultFeatures.addDefaultOres(builder);
		TofuBiomeDefaultFeatures.tofuCreatureSpawns(mobSpawnSetting);
		return fullDefinition(
				Biome.Precipitation.NONE,
				0.8F,
				0.0F,
				new BiomeSpecialEffects.Builder()
						.fogColor(0x93_93_bc)
						.skyColor(0xFFFFFF)
						.waterColor(0xBBDAF0)
						.waterFogColor(0x6099C0)
						.grassColorOverride(7115607)
						.foliageColorOverride(7115607)
						.grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE)
						.backgroundMusic(new Music(soundEvent, 12000, 24000, false))
						.build(),
				mobSpawnSetting.build(),
				builder.build(),
				Biome.TemperatureModifier.NONE
		);
	}

	public static Biome fullDefinition(Biome.Precipitation precipitation, float temperature, float downfall, BiomeSpecialEffects effects, MobSpawnSettings spawnSettings, BiomeGenerationSettings generationSettings, Biome.TemperatureModifier temperatureModifier) {
		return new Biome.BiomeBuilder()
				.temperature(temperature)
				.downfall(downfall)
				.specialEffects(effects)
				.mobSpawnSettings(spawnSettings)
				.generationSettings(generationSettings)
				.temperatureAdjustment(temperatureModifier)
				.build();
	}
}

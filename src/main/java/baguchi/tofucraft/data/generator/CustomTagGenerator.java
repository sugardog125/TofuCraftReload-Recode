package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBannerPatterns;
import baguchi.tofucraft.registry.TofuPoiTypes;
import baguchi.tofucraft.registry.TofuSounds;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.concurrent.CompletableFuture;

public class CustomTagGenerator {
	public static class BannerPatternTagGenerator extends TagsProvider<BannerPattern> {

		public static final TagKey<BannerPattern> TOFUNIAN_BANNER_PATTERN = create("pattern_item/tofunian");

		public BannerPatternTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
			super(output, Registries.BANNER_PATTERN, provider, TofuCraftReload.MODID);
		}

		private static TagKey<BannerPattern> create(String name) {
			return TagKey.create(Registries.BANNER_PATTERN, TofuCraftReload.prefix(name));
		}

		@Override
		protected void addTags(HolderLookup.Provider p_256380_) {
			tag(TOFUNIAN_BANNER_PATTERN).add(TofuBannerPatterns.TOFUNIAN);
		}

		@Override
		public String getName() {
			return "Tofucraft Banner Pattern Tags";
		}
	}

	public static class PoiTypeTagGenerator extends TagsProvider<PoiType> {

		public PoiTypeTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
			super(output, Registries.POINT_OF_INTEREST_TYPE, provider, TofuCraftReload.MODID);
		}

		@Override
		protected void addTags(HolderLookup.Provider p_256380_) {
			tag(TofuTags.PoiTypes.TOFU_VILLAGE).add(TofuPoiTypes.TOFUNIAN_STATUE);
			tag(PoiTypeTags.ACQUIRABLE_JOB_SITE).add(TofuPoiTypes.TOFU_CRAFTSMAN);
		}
	}

	public static class SoundEventTagGenerator extends TagsProvider<SoundEvent> {

		public SoundEventTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
			super(output, Registries.SOUND_EVENT, provider, TofuCraftReload.MODID);
		}

		@Override
		protected void addTags(HolderLookup.Provider p_256380_) {
			tag(TofuTags.SoundEvents.BOSS_MUSIC).add(TofuSounds.TOFU_DUNGEON_BGM.getKey());
		}
	}
}

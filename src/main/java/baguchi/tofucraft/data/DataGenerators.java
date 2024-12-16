package baguchi.tofucraft.data;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.data.generator.BlockTagGenerator;
import baguchi.tofucraft.data.generator.BlockstateGenerator;
import baguchi.tofucraft.data.generator.CustomTagGenerator;
import baguchi.tofucraft.data.generator.EnchantTagGenerator;
import baguchi.tofucraft.data.generator.EntityTagGenerator;
import baguchi.tofucraft.data.generator.FluidTagGenerator;
import baguchi.tofucraft.data.generator.ItemModelGenerator;
import baguchi.tofucraft.data.generator.ItemTagGenerator;
import baguchi.tofucraft.data.generator.RegistryDataGenerator;
import baguchi.tofucraft.data.generator.TofuAdvancementGenerator;
import baguchi.tofucraft.data.generator.TofuClientItemsProvider;
import baguchi.tofucraft.data.generator.TofuDataMapsProvider;
import baguchi.tofucraft.data.generator.TofuEquipmentModelProvider;
import baguchi.tofucraft.data.generator.recipe.CraftingGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGenerator(packOutput, event.getLookupProvider());

		CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
		generator.addProvider(true, datapackProvider);
		generator.addProvider(true, new BlockstateGenerator(packOutput, existingFileHelper));
		generator.addProvider(true, new ItemModelGenerator(packOutput, existingFileHelper));
		generator.addProvider(true, new TofuEquipmentModelProvider(packOutput));
		generator.addProvider(true, new TofuClientItemsProvider(packOutput));

		BlockTagsProvider blocktags = new BlockTagGenerator(packOutput, lookupProvider, existingFileHelper);
		generator.addProvider(true, blocktags);
		generator.addProvider(true, new ItemTagGenerator(packOutput, lookupProvider, blocktags.contentsGetter(), existingFileHelper));
		generator.addProvider(true, new EntityTagGenerator(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(true, new TofuDamageTypeTags(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(true, new EnchantTagGenerator(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(true, new CustomTagGenerator.BannerPatternTagGenerator(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(true, new CustomTagGenerator.PoiTypeTagGenerator(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(true, new CustomTagGenerator.SoundEventTagGenerator(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(true, new FluidTagGenerator(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(true, TofuLootTableProvider.create(packOutput, lookupProvider));
		generator.addProvider(true, new Runner(packOutput, lookupProvider));
		generator.addProvider(true, new TofuAdvancementGenerator(packOutput, lookupProvider, existingFileHelper));
		generator.addProvider(true, new TofuDataMapsProvider(packOutput, lookupProvider));
	}

	public static final class Runner extends RecipeProvider.Runner {
		public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
			super(output, lookupProvider);
		}

		@Override
		protected RecipeProvider createRecipeProvider(HolderLookup.Provider lookupProvider, RecipeOutput output) {
			return new CraftingGenerator(lookupProvider, output);
		}

		@Override
		public String getName() {
			return TofuCraftReload.MODID + "recipes";
		}
	}
}
package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Stream;


public class TofuCreativeModeTabs {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TofuCraftReload.MODID);


	public static final RegistryObject<CreativeModeTab> TOFUS = CREATIVE_MODE_TABS.register("tofus", () -> CreativeModeTab.builder()
			.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
			.title(Component.translatable("itemGroup." + TofuCraftReload.MODID + ".tofus" + ".main_tab"))
			.icon(() -> TofuItems.TOFUKINU.get().getDefaultInstance())
			.displayItems((parameters, output) -> {
				output.acceptAll(Stream.of(
						TofuItems.TOFUKINU,
						TofuItems.TOFUMOMEN,
						TofuItems.TOFUISHI,
						TofuItems.TOFUMETAL,
						TofuItems.TOFUDIAMOND,
						TofuItems.TOFUDIAMOND_NUGGET,
						TofuItems.TOFUHELL,
						TofuItems.TOFUSOUL,
						TofuItems.TOFUMISO,
						TofuItems.TOFUANNIN,
						TofuItems.TOFUDRIED,
						TofuItems.TOFUEGG,
						TofuItems.TOFUZUNDA,
						TofuItems.TOFUSTRAWBERRY,
						TofuItems.TOFUSESAME,
						TofuItems.TOFUSMOKE,
						TofuItems.TOFUGRILLED,
						TofuItems.TOFUFRIED,
						TofuItems.TOFUFRIED_POUCH
				).map(sup -> {
					return sup.get().getDefaultInstance();
				}).toList());
			}).build());
	public static final RegistryObject<CreativeModeTab> TOFU_MECHANICAL_BLOCKS = CREATIVE_MODE_TABS.register("tofu_mechanical_blocks", () -> CreativeModeTab.builder()
			.withTabsBefore(TOFUS.getKey())
			.title(Component.translatable("itemGroup." + TofuCraftReload.MODID + ".tofu_mechanical_blocks" + ".main_tab"))
			.icon(() -> TofuBlocks.TF_STORAGE.get().asItem().getDefaultInstance())
			.displayItems((parameters, output) -> {
				output.acceptAll(Stream.of(
						TofuBlocks.TF_STORAGE
						, TofuBlocks.ANTENNA_BASIC
						, TofuBlocks.TOFU_DETECTOR
						, TofuBlocks.TOFU_WORK_STATION).map(sup -> {
					return sup.get().asItem().getDefaultInstance();
				}).toList());
				output.acceptAll(Stream.of(
						TofuItems.TF_CAPACITOR
						, TofuItems.TF_CIRCUIT
						, TofuItems.TF_COIL
						, TofuItems.TF_OSCILLATOR
						, TofuItems.TOFU_CORE
						, TofuItems.TF_BATTERY
						, TofuItems.REFLECT_TOFU_SHIELD).map(sup -> {
					return sup.get().getDefaultInstance();
				}).toList());
			}).build());

	public static final RegistryObject<CreativeModeTab> TOFU_BUILDING_BLOCKS = CREATIVE_MODE_TABS.register("tofu_building_blocks", () -> CreativeModeTab.builder()
			.withTabsBefore(TOFUS.getKey())
			.title(Component.translatable("itemGroup." + TofuCraftReload.MODID + ".tofu_building_blocks" + ".main_tab"))
			.icon(() -> TofuBlocks.ISHITOFU_BRICK.get().asItem().getDefaultInstance())
			.displayItems((parameters, output) -> {
				output.acceptAll(Stream.of(
						TofuBlocks.KINUTOFU,
						TofuBlocks.TOFUSLAB_KINU,
						TofuBlocks.TOFUSTAIR_KINU,
						TofuBlocks.TOFUFENCE_KINU,
						TofuBlocks.TOFUDOOR_KINU,
						TofuBlocks.TOFUTRAPDOOR_KINU,
						TofuBlocks.TOFULADDER_KINU,
						TofuBlocks.TOFUTORCH_KINU,
						TofuBlocks.MOMENTOFU,
						TofuBlocks.TOFUSLAB_MOMEN,
						TofuBlocks.TOFUSTAIR_MOMEN,
						TofuBlocks.TOFUFENCE_MOMEN,
						TofuBlocks.TOFUDOOR_MOMEN,
						TofuBlocks.TOFUTRAPDOOR_MOMEN,
						TofuBlocks.TOFULADDER_MOMEN,
						TofuBlocks.TOFUTORCH_MOMEN,
						TofuBlocks.ISHITOFU,
						TofuBlocks.TOFUSLAB_ISHI,
						TofuBlocks.TOFUSTAIR_ISHI,
						TofuBlocks.TOFUFENCE_ISHI,
						TofuBlocks.TOFUDOOR_ISHI,
						TofuBlocks.TOFUTRAPDOOR_ISHI,
						TofuBlocks.TOFULADDER_ISHI,
						TofuBlocks.TOFUTORCH_ISHI,
						TofuBlocks.ISHITOFU_BRICK,
						TofuBlocks.TOFUSLAB_ISHIBRICK,
						TofuBlocks.TOFUSTAIR_ISHIBRICK,
						TofuBlocks.TOFULADDER_ISHIBRICK,
						TofuBlocks.ISHITOFU_CHISELED_BRICK,
						TofuBlocks.ISHITOFU_SMOOTH_BRICK,
						TofuBlocks.METALTOFU,
						TofuBlocks.TOFUSLAB_METAL,
						TofuBlocks.TOFUSTAIR_METAL,
						TofuBlocks.TOFUFENCE_METAL,
						TofuBlocks.TOFUDOOR_METAL,
						TofuBlocks.TOFUTRAPDOOR_METAL,
						TofuBlocks.TOFULADDER_METAL,
						TofuBlocks.TOFUTORCH_METAL,
						TofuBlocks.TOFU_METAL_LANTERN,
						TofuBlocks.TOFU_METAL_SOUL_LANTERN,
						TofuBlocks.TOFU_METAL_CHAIN,
						TofuBlocks.DIAMONDTOFU,
						TofuBlocks.TOFU_GEM_BLOCK,
						TofuBlocks.EGGTOFU,
						TofuBlocks.TOFUSLAB_EGG,
						TofuBlocks.TOFUSTAIR_EGG,
						TofuBlocks.EGGTOFU_BRICK,
						TofuBlocks.TOFUSLAB_EGGBRICK,
						TofuBlocks.TOFUSTAIR_EGGBRICK,
						TofuBlocks.SESAMETOFU,
						TofuBlocks.TOFUSLAB_SESAME,
						TofuBlocks.TOFUSTAIR_SESAME,
						TofuBlocks.GRILLEDTOFU,
						TofuBlocks.TOFUSLAB_GRILLED,
						TofuBlocks.TOFUSTAIR_GRILLED,
						TofuBlocks.TOFUFENCE_GRILLED,
						TofuBlocks.TOFUDOOR_GRILLED,
						TofuBlocks.TOFUTRAPDOOR_GRILLED,
						TofuBlocks.TOFULADDER_GRILLED,
						TofuBlocks.TOFUTORCH_GRILLED,
						TofuBlocks.DRIEDTOFU,
						TofuBlocks.TOFUSLAB_DRIED,
						TofuBlocks.TOFUSTAIR_DRIED,
						TofuBlocks.ZUNDATOFU,
						TofuBlocks.TOFUSLAB_ZUNDA,
						TofuBlocks.TOFUSTAIR_ZUNDA,
						TofuBlocks.TOFUFENCE_ZUNDA,
						TofuBlocks.TOFUDOOR_ZUNDA,
						TofuBlocks.TOFUTRAPDOOR_ZUNDA,
						TofuBlocks.TOFULADDER_ZUNDA,
						TofuBlocks.TOFUTORCH_ZUNDA,
						TofuBlocks.ZUNDATOFU_BRICK,
						TofuBlocks.ZUNDATOFU_SMOOTH_BRICK,
						TofuBlocks.TOFUSLAB_ZUNDABRICK,
						TofuBlocks.TOFUSTAIR_ZUNDABRICK,
						TofuBlocks.HELLTOFU,
						TofuBlocks.TOFUSLAB_HELL,
						TofuBlocks.TOFUSTAIR_HELL,
						TofuBlocks.TOFUFENCE_HELL,
						TofuBlocks.TOFUDOOR_HELL,
						TofuBlocks.TOFUTRAPDOOR_HELL,
						TofuBlocks.HELLTOFU_BRICK,
						TofuBlocks.HELLTOFU_SMOOTH_BRICK,
						TofuBlocks.SOULTOFU,
						TofuBlocks.TOFUSLAB_SOUL,
						TofuBlocks.TOFUSTAIR_SOUL,
						TofuBlocks.TOFUFENCE_SOUL,
						TofuBlocks.TOFUDOOR_SOUL,
						TofuBlocks.TOFUTRAPDOOR_SOUL,
						TofuBlocks.SOULTOFU_BRICK,
						TofuBlocks.SOULTOFU_SMOOTH_BRICK,
						TofuBlocks.MINCEDTOFU,
						TofuBlocks.TOFU_TERRAIN,
						TofuBlocks.TOFU_TERRAIN_ZUNDA,
						TofuBlocks.SUSPICIOUS_TOFU_TERRAIN,
						TofuBlocks.ORE_TOFUGEM,
						TofuBlocks.ORE_TOFU_DIAMOND,
						TofuBlocks.TOFUSLATE,
						TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE,
						TofuBlocks.TOFU_BEDROCK,
						TofuBlocks.LEEK,
						TofuBlocks.LEEK_GREEN_STEM,
						TofuBlocks.LEEK_GREEN_PLANKS,
						TofuBlocks.LEEK_GREEN_PLANKS_SLAB,
						TofuBlocks.LEEK_GREEN_PLANKS_STAIR,
						TofuBlocks.LEEK_GREEN_FENCE,
						TofuBlocks.LEEK_GREEN_FENCE_GATE,
						TofuBlocks.LEEK_GREEN_SIGN,
						TofuBlocks.LEEK_GREEN_HANGING_SIGN,
						TofuBlocks.LEEK_GREEN_TRAPDOOR,
						TofuBlocks.LEEK_GREEN_DOOR,
						TofuBlocks.LEEK_STEM,
						TofuBlocks.LEEK_PLANKS,
						TofuBlocks.LEEK_PLANKS_SLAB,
						TofuBlocks.LEEK_PLANKS_STAIR,
						TofuBlocks.LEEK_FENCE,
						TofuBlocks.LEEK_FENCE_GATE,
						TofuBlocks.LEEK_SIGN,
						TofuBlocks.LEEK_HANGING_SIGN,
						TofuBlocks.TOFU_STEM,
						TofuBlocks.TOFU_STEM_PLANKS,
						TofuBlocks.TOFU_STEM_PLANKS_STAIR,
						TofuBlocks.TOFU_STEM_PLANKS_SLAB,
						TofuBlocks.TOFU_STEM_FENCE,
						TofuBlocks.TOFU_STEM_FENCE_GATE,
						TofuBlocks.TOFU_STEM_SIGN,
						TofuBlocks.TOFU_STEM_HANGING_SIGN,
						TofuBlocks.TOFU_STEM_TRAPDOOR,
						TofuBlocks.TOFU_STEM_DOOR,
						TofuBlocks.ZUNDATOFU_MUSHROOM,
						TofuBlocks.SAPLING_TOFU,
						TofuBlocks.LEAVES_TOFU,
						TofuBlocks.SAPLING_APRICOT,
						TofuBlocks.LEAVES_APRICOT,
						TofuBlocks.BARREL_MISO,
						TofuBlocks.BARREL_MISOTOFU,
						TofuBlocks.BARREL_ADV_TOFUGEM,
						TofuBlocks.NATTOBED,
						TofuBlocks.NETHER_NATTOBED,
						TofuBlocks.TOFUBED,
						TofuBlocks.TOFUCHEST,
						TofuBlocks.FOODPLATE,
						TofuBlocks.ZUNDAMA_BLOCK,
						TofuBlocks.SALTPAN,
						TofuBlocks.SALT_FURNACE,
						TofuBlocks.MORIJIO,
						TofuBlocks.SALT_BLOCK,
						TofuBlocks.SOYBEANS_SEEDS_BLOCK,
						TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK,
						TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK,
						TofuBlocks.TOFU_FLOWER).map(sup -> {
					return sup.get().asItem().getDefaultInstance();
				}).toList()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
			}).build());

	public static final RegistryObject<CreativeModeTab> TOFU_TOOLS = CREATIVE_MODE_TABS.register("tofu_tools", () -> CreativeModeTab.builder()
			.withTabsBefore(TOFUS.getKey())
			.title(Component.translatable("itemGroup." + TofuCraftReload.MODID + ".tofu_tools" + ".main_tab"))
			.icon(() -> TofuItems.TOFU_METAL_SWORD.get().getDefaultInstance())
			.displayItems((parameters, output) -> {
				output.acceptAll(Stream.of(
						TofuItems.BUGLE,
						TofuItems.TOFUSCOOP,
						TofuItems.ROLLINGPIN,
						TofuItems.FILTERCLOTH,
						TofuItems.TOFUSTICK,
						TofuItems.TOFU_KINU_SWORD,
						TofuItems.TOFU_KINU_AXE,
						TofuItems.TOFU_KINU_PICKAXE,
						TofuItems.TOFU_KINU_SHOVEL,
						TofuItems.TOFU_KINU_HOE,
						TofuItems.TOFU_MOMEN_SWORD,
						TofuItems.TOFU_MOMEN_AXE,
						TofuItems.TOFU_MOMEN_PICKAXE,
						TofuItems.TOFU_MOMEN_SHOVEL,
						TofuItems.TOFU_MOMEN_HOE,
						TofuItems.TOFU_SOLID_SWORD,
						TofuItems.TOFU_SOLID_AXE,
						TofuItems.TOFU_SOLID_PICKAXE,
						TofuItems.TOFU_SOLID_SHOVEL,
						TofuItems.TOFU_SOLID_HOE,
						TofuItems.TOFU_METAL_SWORD,
						TofuItems.TOFU_METAL_AXE,
						TofuItems.TOFU_METAL_PICKAXE,
						TofuItems.TOFU_METAL_SHOVEL,
						TofuItems.TOFU_METAL_HOE,
						TofuItems.TOFU_SHIELD,
						TofuItems.TOFU_METAL_SHEARS,
						TofuItems.TOFU_DIAMOND_SWORD,
						TofuItems.TOFU_DIAMOND_AXE,
						TofuItems.TOFU_DIAMOND_PICKAXE,
						TofuItems.TOFU_DIAMOND_SHOVEL,
						TofuItems.TOFU_DIAMOND_HOE,
						TofuItems.TOFU_KINU_HELMET,
						TofuItems.TOFU_KINU_CHESTPLATE,
						TofuItems.TOFU_KINU_LEGGINGS,
						TofuItems.TOFU_KINU_BOOTS,
						TofuItems.TOFU_MOMEN_HELMET,
						TofuItems.TOFU_MOMEN_CHESTPLATE,
						TofuItems.TOFU_MOMEN_LEGGINGS,
						TofuItems.TOFU_MOMEN_BOOTS,
						TofuItems.ARMOR_TOFU_SOLIDHELMET,
						TofuItems.ARMOR_TOFU_SOLIDCHESTPLATE,
						TofuItems.ARMOR_TOFU_SOLIDLEGGINGS,
						TofuItems.ARMOR_TOFU_SOLIDBOOTS,
						TofuItems.TOFU_METAL_HELMET,
						TofuItems.TOFU_METAL_CHESTPLATE,
						TofuItems.TOFU_METAL_LEGGINGS,
						TofuItems.TOFU_METAL_BOOTS,
						TofuItems.TOFU_DIAMOND_HELMET,
						TofuItems.TOFU_DIAMOND_CHESTPLATE,
						TofuItems.TOFU_DIAMOND_LEGGINGS,
						TofuItems.TOFU_DIAMOND_BOOTS,
						TofuItems.SCULK_BONE_HELMET,
						TofuItems.SCULK_BONE_CHESTPLATE,
						TofuItems.SCULK_BONE_LEGGINGS,
						TofuItems.SCULK_BONE_BOOTS,
						TofuItems.ZUNDA_ARROW,
						TofuItems.ZUNDA_BOW,
						TofuItems.ZUNDAMUSHROOM_ON_A_STICK,
						TofuItems.FUKUMAME,
						TofuItems.NETHER_FUKUMAME,
						TofuItems.SOUL_FUKUMAME).map(itemRegistryObject -> {
					return itemRegistryObject.get().asItem().getDefaultInstance();
				}).toList());
			}).build());

	public static final RegistryObject<CreativeModeTab> TOFU_FOODS = CREATIVE_MODE_TABS.register("tofu_foods", () -> CreativeModeTab.builder()
			.withTabsBefore(TOFU_BUILDING_BLOCKS.getKey())
			.title(Component.translatable("itemGroup." + TofuCraftReload.MODID + ".tofu_foods" + ".main_tab"))
			.icon(() -> TofuItems.TOFUCOOKIE.get().getDefaultInstance())
			.displayItems((parameters, output) -> {
				output.accept(TofuBlocks.TOFUCAKE.get());
				output.accept(TofuBlocks.ZUNDATOFUCAKE.get());
				output.accept(TofuBlocks.SOYCHEESE_TART.get());
				output.acceptAll(Stream.of(
						TofuItems.SOYSTICK,
						TofuItems.TOFUCOOKIE,
						TofuItems.MOYASHIOHITASHI,
						TofuItems.MOYASHIITAME,
						TofuItems.CHIKUWA,
						TofuItems.TOFU_CHIKUWA,
						TofuItems.NATTO,
						TofuItems.NETHER_NATTO,
						TofuItems.EDAMAME,
						TofuItems.BOILED_EDAMAME,
						TofuItems.KINAKO_MANJU,
						TofuItems.KINAKO_MOCHI,
						TofuItems.ZUNDA_MANJU,
						TofuItems.ZUNDA_MOCHI,
						TofuItems.NETHER_MANJU,
						TofuItems.SOUL_MANJU,
						TofuItems.GOHEIMOCHI,
						TofuItems.APRICOTJERRY_BREAD,
						TofuItems.KOYADOFUSTEW,
						TofuItems.MABODOFU,
						TofuItems.TOMATO_SOYBEAN_STEW,
						TofuItems.KOYADOFUSTEW,
						TofuItems.TASTYBEEFSTEW,
						TofuItems.TASTYSTEW,
						TofuItems.CRIMSON_SOUP,
						TofuItems.MISOSOUP,
						TofuItems.NANBAN,
						TofuItems.NANBANTOFU,
						TofuItems.NIKUJAGA,
						TofuItems.OAGE,
						TofuItems.INARI,
						TofuItems.OKARASTICK,
						TofuItems.OKARA_DONUT,
						TofuItems.ONIGIRI,
						TofuItems.ONIGIRI_SALT,
						TofuItems.YAKIONIGIRI_MISO,
						TofuItems.YAKIONIGIRI_SHOYU,
						TofuItems.RAW_TOFU_FISH,
						TofuItems.COOKED_TOFU_FISH,
						TofuItems.RICE_BURGER,
						TofuItems.RICE_TOFU,
						TofuItems.RICE_SOBORO_TOFU,
						TofuItems.RICE_NATTO,
						TofuItems.RICE_NATTO_LEEK,
						TofuItems.RICE_NETHER_NATTO,
						TofuItems.RICE_NETHER_NATTO_LEEK,
						TofuItems.EDAMAME_RICE,
						TofuItems.YUBA,
						TofuItems.YUDOFU,
						TofuItems.MEAT_WRAPPED_YUBA,
						TofuItems.SOYMEAT,
						TofuItems.SOY_CHEESE,
						TofuItems.SOY_NETHER_CHEESE,
						TofuItems.SOY_SOUL_CHEESE,
						TofuItems.SOY_NETHER_CHEESE,
						TofuItems.SOY_SOUL_CHEESE,
						TofuItems.SOY_CHOCOLATE,
						TofuItems.TOFUNIAN_SOY_CHOCOLATE,
						TofuItems.TOFU_STEAK,
						TofuItems.TOFU_HAMBURG_RAW,
						TofuItems.TOFU_HAMBURG,
						TofuItems.TOFUSOMEN,
						TofuItems.TOFUSOMENBOWL_GLASS,
						TofuItems.PUDDING,
						TofuItems.PUDDING_SOYMILK,
						TofuItems.SOYMILK,
						TofuItems.SOYMILK_ANNIN,
						TofuItems.SOYMILK_APPLE,
						TofuItems.SOYMILK_COCOA,
						TofuItems.SOYMILK_FRUITS,
						TofuItems.SOYMILK_HONEY,
						TofuItems.SOYMILK_KINAKO,
						TofuItems.SOYMILK_PUDDING,
						TofuItems.SOYMILK_PUMPKIN,
						TofuItems.SOYMILK_RAMUNE,
						TofuItems.SOYMILK_SAKURA,
						TofuItems.SOYMILK_STRAWBERRY,
						TofuItems.SOYMILK_TEA,
						TofuItems.SOYMILK_HELL_BOTTLE,
						TofuItems.SOYMILK_SOUL_BOTTLE,
						TofuItems.HELL_MABOU,
						TofuItems.RED_SOUP,
						TofuItems.HELL_RED_SOUP,
						TofuItems.SUKIYAKI,
						TofuItems.TOFU_BUNS_BURGER,
						TofuItems.TTTBURGER,
						TofuItems.STEAMED_BREAD,
						TofuItems.STEAMED_BREAD_COCOA,
						TofuItems.KINAKO_BREAD,
						TofuItems.EDAMAME_TEMPLA,
						TofuItems.NEGIMA,
						TofuItems.SOY_KARAAGE,
						TofuItems.SOYMEATDON).map(itemRegistryObject -> {
					return itemRegistryObject.get().getDefaultInstance();
				}).toList()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
			}).build());

	public static final RegistryObject<CreativeModeTab> TOFU_MISC = CREATIVE_MODE_TABS.register("tofu_misc", () -> CreativeModeTab.builder()
			.withTabsBefore(TOFU_FOODS.getKey())
			.title(Component.translatable("itemGroup." + TofuCraftReload.MODID + ".tofu_misc" + ".main_tab"))
			.icon(() -> TofuItems.ZUNDARUBY.get().getDefaultInstance())
			.displayItems((parameters, output) -> {
				output.acceptAll(Stream.of(
						TofuItems.GLASSBOWL,
						TofuItems.BUCKET_SOYMILK,
						TofuItems.BUCKET_SOYMILK_NETHER,
						TofuItems.BUCKET_SOYMILK_SOUL,
						TofuItems.BUCKET_BITTERN,
						TofuItems.TOFUFISH_SOYMILK_BUCKET,
						TofuItems.TOFUFISH_BUCKET,
						TofuItems.BITTERN_BOTTLE,
						TofuItems.CRIMSON_BOTTLE,
						TofuItems.WARPED_BOTTLE,
						TofuItems.SHROOM_BOTTLE,
						TofuItems.BOTTLE_DASHI,
						TofuItems.BOTTLE_SOYOIL,
						TofuItems.BOTTLE_SOYSAUSE,
						TofuItems.APRICOT,
						TofuItems.APRICOTSEED,
						TofuItems.APRICOTJERRY_BOTTLE,
						TofuItems.SEEDS_SOYBEANS,
						TofuItems.SEEDS_SOYBEANS_NETHER,
						TofuItems.SEEDS_SOYBEANS_SOUL,
						TofuItems.SEEDS_RICE,
						TofuItems.SEEDS_CHILI,
						TofuItems.CHILI,
						TofuItems.RICE,
						TofuItems.SOYBEAN_PARCHED,
						TofuItems.KINAKO,
						TofuItems.EDAMAME,
						TofuItems.KINAKO,
						TofuItems.SALT,
						TofuItems.LEEK,
						TofuItems.TOFUCOW_SPAWNEGG,
						TofuItems.TOFUFISH_SPAWNEGG,
						TofuItems.TOFUPIG_SPAWNEGG,
						TofuItems.TOFUNIAN_SPAWNEGG,
						TofuItems.TRAVELER_TOFUNIAN_SPAWNEGG,
						TofuItems.TOFUSLIME_SPAWNEGG,
						TofuItems.TOFUCREEPER_SPAWNEGG,
						TofuItems.TOFUSPIDER_SPAWNEGG,
						TofuItems.TOFU_GANDLEM_SPAWNEGG,
						TofuItems.SHUDOFUSPIDER_SPAWNEGG,
						TofuItems.FUKUMAME_THOWER_SPAWNEGG,
						TofuItems.ZUNDAMITE_SPAWNEGG,
						TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE,
						TofuItems.ZUNDA_UPGRADE_SMITHING_TEMPLATE,
						TofuItems.TOFUNIAN_BANNER_PATTERN,
						TofuItems.TOFU_STEM_BOAT,
						TofuItems.TOFU_STEM_CHEST_BOAT,
						TofuItems.LEEK_BOAT,
						TofuItems.LEEK_CHEST_BOAT,
						TofuItems.LEEK_GREEN_BOAT,
						TofuItems.LEEK_GREEN_CHEST_BOAT,
						TofuItems.ZUNDAMA,
						TofuItems.ZUNDARUBY,
						TofuItems.TOFUGEM,
						TofuItems.ADVANCE_TOFUGEM).map(itemRegistryObject -> {
					return itemRegistryObject.get().getDefaultInstance();
				}).toList()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
			}).build());
}

package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

import static baguchi.tofucraft.TofuCraftReload.prefix;

public class ItemModelGenerator extends ItemModelProvider {
	public ItemModelGenerator(PackOutput generator, ExistingFileHelper existingFileHelper) {
		super(generator, TofuCraftReload.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		//block
		toBlock(TofuBlocks.KINUTOFU);
		toBlock(TofuBlocks.MOMENTOFU);
		toBlock(TofuBlocks.ISHITOFU);
		toBlock(TofuBlocks.ISHITOFU_BRICK);
		toBlock(TofuBlocks.ISHITOFU_SMOOTH_BRICK);
		toBlock(TofuBlocks.ISHITOFU_CHISELED_BRICK);
		toBlock(TofuBlocks.METALTOFU);
		toBlock(TofuBlocks.METAL_TOFU_GRATE);
		toBlock(TofuBlocks.METAL_TOFU_LUMP);
		toBlock(TofuBlocks.DIAMONDTOFU);
		toBlock(TofuBlocks.TOFU_GEM_BLOCK);
		toBlock(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK);
		toBlock(TofuBlocks.GRILLEDTOFU);
		toBlock(TofuBlocks.ZUNDATOFU);

		toBlock(TofuBlocks.ZUNDATOFU_BRICK);
		toBlock(TofuBlocks.ZUNDATOFU_SMOOTH_BRICK);
		toBlock(TofuBlocks.TOFUSTAIR_ZUNDABRICK);
		toBlock(TofuBlocks.TOFUSLAB_ZUNDABRICK);

		toBlock(TofuBlocks.MISOTOFU);
		toBlock(TofuBlocks.DRIEDTOFU);

		toBlock(TofuBlocks.HELLTOFU);
		toBlock(TofuBlocks.HELLTOFU_BRICK);
		toBlock(TofuBlocks.HELLTOFU_SMOOTH_BRICK);
		toBlock(TofuBlocks.SOULTOFU);
		toBlock(TofuBlocks.SOULTOFU_BRICK);
		toBlock(TofuBlocks.SOULTOFU_SMOOTH_BRICK);
		toBlock(TofuBlocks.EGGTOFU_BRICK);

		toBlock(TofuBlocks.EGGTOFU);
		toBlock(TofuBlocks.TOFUSTAIR_EGG);
		toBlock(TofuBlocks.TOFUSLAB_EGG);

		toBlock(TofuBlocks.EGGTOFU_BRICK);
		toBlock(TofuBlocks.TOFUSTAIR_EGGBRICK);
		toBlock(TofuBlocks.TOFUSLAB_EGGBRICK);

		toBlock(TofuBlocks.SESAMETOFU);
		toBlock(TofuBlocks.TOFUSTAIR_SESAME);
		toBlock(TofuBlocks.TOFUSLAB_SESAME);


		toBlock(TofuBlocks.MINCEDTOFU);

		toBlock(TofuBlocks.TOFUSTAIR_KINU);
		toBlock(TofuBlocks.TOFUSTAIR_MOMEN);
		toBlock(TofuBlocks.TOFUSTAIR_ISHI);
		toBlock(TofuBlocks.TOFUSTAIR_METAL);
		toBlock(TofuBlocks.TOFUSTAIR_GRILLED);
		toBlock(TofuBlocks.TOFUSTAIR_ZUNDA);
		toBlock(TofuBlocks.TOFUSTAIR_HELL);
		toBlock(TofuBlocks.TOFUSTAIR_SOUL);
		toBlock(TofuBlocks.TOFUSTAIR_ISHIBRICK);
		toBlock(TofuBlocks.TOFUSTAIR_HELLBRICK);
		toBlock(TofuBlocks.TOFUSTAIR_SOULBRICK);
		toBlock(TofuBlocks.TOFUSTAIR_MISO);
		toBlock(TofuBlocks.TOFUSTAIR_DRIED);

		toBlock(TofuBlocks.TOFUSLAB_KINU);
		toBlock(TofuBlocks.TOFUSLAB_MOMEN);
		toBlock(TofuBlocks.TOFUSLAB_ISHI);
		toBlock(TofuBlocks.TOFUSLAB_METAL);
		toBlock(TofuBlocks.TOFUSLAB_GRILLED);
		toBlock(TofuBlocks.TOFUSLAB_ZUNDA);
		toBlock(TofuBlocks.TOFUSLAB_HELL);
		toBlock(TofuBlocks.TOFUSLAB_SOUL);
		toBlock(TofuBlocks.TOFUSLAB_ISHIBRICK);
		toBlock(TofuBlocks.TOFUSLAB_HELLBRICK);
		toBlock(TofuBlocks.TOFUSLAB_SOULBRICK);
		toBlock(TofuBlocks.TOFUSLAB_MISO);
		toBlock(TofuBlocks.TOFUSLAB_DRIED);

		wall(TofuBlocks.TOFUFENCE_KINU, TofuBlocks.KINUTOFU);
		wall(TofuBlocks.TOFUFENCE_MOMEN, TofuBlocks.MOMENTOFU);
		wall(TofuBlocks.TOFUFENCE_ISHI, TofuBlocks.ISHITOFU);
		wall(TofuBlocks.TOFUFENCE_METAL, TofuBlocks.METALTOFU);
		wall(TofuBlocks.TOFUFENCE_HELL, TofuBlocks.HELLTOFU);
		wall(TofuBlocks.TOFUFENCE_SOUL, TofuBlocks.SOULTOFU);
		wall(TofuBlocks.TOFUFENCE_GRILLED, TofuBlocks.GRILLEDTOFU);
		wall(TofuBlocks.TOFUFENCE_ZUNDA, TofuBlocks.ZUNDATOFU);

		singleTex(TofuBlocks.TOFUDOOR_KINU);
		singleTex(TofuBlocks.TOFUDOOR_MOMEN);
		singleTex(TofuBlocks.TOFUDOOR_ISHI);
		singleTex(TofuBlocks.TOFUDOOR_METAL);
		singleTex(TofuBlocks.TOFUDOOR_HELL);
		singleTex(TofuBlocks.TOFUDOOR_SOUL);
		singleTex(TofuBlocks.TOFUDOOR_GRILLED);
		singleTex(TofuBlocks.TOFUDOOR_ZUNDA);

		toBlockModel(TofuBlocks.TOFUTRAPDOOR_KINU, "tofutrapdoor_kinu_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_MOMEN, "tofutrapdoor_momen_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_ISHI, "tofutrapdoor_ishi_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_METAL, "tofutrapdoor_metal_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_HELL, "tofutrapdoor_hell_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_SOUL, "tofutrapdoor_soul_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_GRILLED, "tofutrapdoor_grilled_bottom");
		toBlockModel(TofuBlocks.TOFUTRAPDOOR_ZUNDA, "tofutrapdoor_zunda_bottom");

		torchItem(TofuBlocks.TOFUTORCH_KINU);
		torchItem(TofuBlocks.TOFUTORCH_MOMEN);
		torchItem(TofuBlocks.TOFUTORCH_ISHI);
		torchItem(TofuBlocks.TOFUTORCH_METAL);
		torchItem(TofuBlocks.TOFUTORCH_GRILLED);
		torchItem(TofuBlocks.TOFUTORCH_ZUNDA);
		torchItem(TofuBlocks.TOFUTORCH_HELL);
		torchItem(TofuBlocks.TOFUTORCH_SOUL);

		itemBlockFlat(TofuBlocks.TOFULADDER_HELL);
		itemBlockFlat(TofuBlocks.TOFULADDER_SOUL);

		toBlock(TofuBlocks.TOFU_TERRAIN);
		toBlock(TofuBlocks.MABOU_TERRAIN);
		toBlock(TofuBlocks.TOFU_TERRAIN_ZUNDA);
		toBlock(TofuBlocks.TOFUSLATE);
		toBlock(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE);
		toBlock(TofuBlocks.ORE_TOFU_DIAMOND);
		toBlock(TofuBlocks.ORE_TOFUGEM);
		toBlock(TofuBlocks.TOFU_BEDROCK);

		toBlock(TofuBlocks.LEEK_GREEN_STEM);
		toBlock(TofuBlocks.LEEK_GREEN_PLANKS);
		toBlock(TofuBlocks.LEEK_GREEN_PLANKS_STAIR);
		toBlock(TofuBlocks.LEEK_GREEN_PLANKS_SLAB);
		this.woodenFence(TofuBlocks.LEEK_GREEN_FENCE, TofuBlocks.LEEK_GREEN_PLANKS);
		toBlock(TofuBlocks.LEEK_GREEN_FENCE_GATE);
		sign(TofuBlocks.LEEK_GREEN_SIGN);
		sign(TofuBlocks.LEEK_GREEN_HANGING_SIGN);
		singleTex(TofuBlocks.LEEK_GREEN_DOOR);
		toBlockModel(TofuBlocks.LEEK_GREEN_TRAPDOOR, "leek_green_trapdoor_bottom");
		toBlock(TofuBlocks.LEEK_GREEN_PRESSURE_PLATE);
		button(TofuBlocks.LEEK_GREEN_BUTTON, TofuBlocks.LEEK_GREEN_PLANKS);


		toBlock(TofuBlocks.LEEK_STEM);
		toBlock(TofuBlocks.LEEK_PLANKS);
		toBlock(TofuBlocks.LEEK_PLANKS_STAIR);
		toBlock(TofuBlocks.LEEK_PLANKS_SLAB);
		this.woodenFence(TofuBlocks.LEEK_FENCE, TofuBlocks.LEEK_PLANKS);
		toBlock(TofuBlocks.LEEK_FENCE_GATE);
		sign(TofuBlocks.LEEK_SIGN);
		sign(TofuBlocks.LEEK_HANGING_SIGN);
		toBlock(TofuBlocks.LEEK_PRESSURE_PLATE);
		button(TofuBlocks.LEEK_BUTTON, TofuBlocks.LEEK_PLANKS);

		itemBlockFlat(TofuBlocks.ZUNDATOFU_MUSHROOM);


		toBlock(TofuBlocks.TOFU_STEM);
		toBlock(TofuBlocks.TOFU_STEM_PLANKS);
		toBlock(TofuBlocks.TOFU_STEM_PLANKS_STAIR);
		toBlock(TofuBlocks.TOFU_STEM_PLANKS_SLAB);
		this.woodenFence(TofuBlocks.TOFU_STEM_FENCE, TofuBlocks.TOFU_STEM_PLANKS);
		toBlock(TofuBlocks.TOFU_STEM_FENCE_GATE);
		sign(TofuBlocks.TOFU_STEM_SIGN);
		sign(TofuBlocks.TOFU_STEM_HANGING_SIGN);
		singleTex(TofuBlocks.TOFU_STEM_DOOR);
		toBlockModel(TofuBlocks.TOFU_STEM_TRAPDOOR, "tofustem_trapdoor_bottom");
		toBlock(TofuBlocks.TOFU_STEM_PRESSURE_PLATE);
		button(TofuBlocks.TOFU_STEM_BUTTON, TofuBlocks.TOFU_STEM_PLANKS);

		itemBlockFlat(TofuBlocks.SAPLING_TOFU);
		toBlock(TofuBlocks.LEAVES_TOFU);

		itemBlockFlat(TofuBlocks.SAPLING_APRICOT);
		toBlock(TofuBlocks.LEAVES_APRICOT);
		singleTex(TofuItems.APRICOT);
		singleTex(TofuItems.APRICOTJERRY_BOTTLE);
		singleTex(TofuItems.APRICOTJERRY_BREAD);
		singleTex(TofuItems.APRICOTSEED);
		singleTex(TofuItems.KYONINSO);

		itemBlockFlat(TofuBlocks.TOFU_FLOWER);
		itemBlockFlat(TofuBlocks.LEEK);
		toBlock(TofuBlocks.TOFU_CHIKUWA_BLOCK);
		toBlock(TofuBlocks.CHIKUWA_BLOCK);
		singleTex(TofuBlocks.TOFUCAKE);
		singleTex(TofuBlocks.ZUNDATOFUCAKE);
		singleTex(TofuBlocks.SOYCHEESE_TART);

		toBlock(TofuBlocks.ZUNDAMA_BLOCK);

		//item
		singleTex(TofuItems.TOFUKINU);
		singleTex(TofuItems.TOFUMOMEN);
		singleTex(TofuItems.TOFUISHI);
		singleTex(TofuItems.TOFUMETAL);
		singleTex(TofuItems.TOFUDIAMOND);
		singleTex(TofuItems.TOFUDIAMOND_NUGGET);

		singleTex(TofuItems.TOFUHELL);
		singleTex(TofuItems.TOFUSOUL);

		singleTex(TofuItems.TOFUGRILLED);
		singleTex(TofuItems.TOFUZUNDA);
		singleTex(TofuItems.TOFUMISO);
		singleTex(TofuItems.TOFUDRIED);
		singleTex(TofuItems.TOFUFRIED);
		singleTex(TofuItems.TOFUSMOKE);
		singleTex(TofuItems.SHUDOFU);
		singleTex(TofuItems.TOFUSESAME);
		singleTex(TofuItems.TOFUFRIED_POUCH);
		singleTex(TofuItems.TOFUANNIN);
		singleTex(TofuItems.TOFUSTRAWBERRY);

		singleTex(TofuItems.AGEDASHI_TOFU);
		singleTex(TofuItems.TOFU_STEAK);
		singleTex(TofuItems.OAGE);
		singleTex(TofuItems.TOFU_MINCED);


		singleTex(TofuItems.BITTERN_BOTTLE);
		singleTex(TofuItems.CRIMSON_BOTTLE);
		singleTex(TofuItems.WARPED_BOTTLE);
		singleTex(TofuItems.SHROOM_BOTTLE);
		singleTex(TofuItems.SALT);

		singleTex(TofuItems.SEEDS_SOYBEANS);
		singleTex(TofuItems.SEEDS_SOYBEANS_NETHER);
		singleTex(TofuItems.SEEDS_SOYBEANS_SOUL);
		singleTex(TofuItems.SEEDS_SOYBEANS_PALE);
		singleTex(TofuItems.SEEDS_SOYBEANS_PALE_GLOW);
		singleTex(TofuItems.SOYBEAN_PARCHED);
		singleTex(TofuItems.KINAKO);
		singleTex(TofuItems.EDAMAME);
		singleTex(TofuItems.BOILED_EDAMAME);
		singleTex(TofuItems.MINCEDPOTATO);

		singleTex(TofuItems.SEEDS_CHILI);
		singleTex(TofuItems.CHILI);
		singleTex(TofuItems.DOUBANJIANG);
		singleTex(TofuItems.MABODOFU);

		singleTex(TofuItems.FUKUMENI);
		singleTex(TofuItems.KOYADOFUSTEW);

		singleTex(TofuItems.KOUJI_BASE);
		singleTex(TofuItems.KOUJI);

		singleTex(TofuItems.MISO);
		singleTex(TofuItems.BOTTLE_SOYSAUSE);
		singleTex(TofuItems.NANBAN);
		singleTex(TofuItems.NANBANTOFU);
		singleTex(TofuItems.NATTO);
		singleTex(TofuItems.NETHER_NATTO);

		singleTex(TofuItems.STARCH);
		singleTex(TofuItems.STARCH_RAW);
		singleTex(TofuItems.FILTERCLOTH);

		singleTex(TofuItems.GELATIN);
		singleTex(TofuItems.GELATINRAW);

		singleTex(TofuItems.LEEK);
		singleTex(TofuItems.RICE);
		singleTex(TofuItems.SEEDS_RICE);
		singleTex(TofuItems.SPROUTS);
		singleTex(TofuItems.CHIKUWA);
		singleTex(TofuItems.TOFU_CHIKUWA);
		singleTex(TofuItems.YUBA);

		singleTex(TofuItems.SOYBALL);

		singleTex(TofuItems.ZUNDA);
		singleTex(TofuItems.ZUNDAMA);
		singleTex(TofuItems.ZUNDARUBY);

		singleTex(TofuItems.TOFU_HAMBURG_RAW);
		singleTex(TofuItems.TOFU_HAMBURG);
		singleTex(TofuItems.RAW_TOFU_FISH);
		singleTex(TofuItems.COOKED_TOFU_FISH);
		singleTex(TofuItems.MISODENGAKU);
		singleTex(TofuItems.MISO_CHEESE_DENGAKU);
		singleTex(TofuItems.SOYMEAT);

		singleTex(TofuItems.TOFUCOOKIE);
		singleTex(TofuItems.TTTBURGER);
		singleTex(TofuItems.MEAT_WRAPPED_YUBA);
		singleTex(TofuItems.SOYSTICK);
		singleTex(TofuItems.MISOSOUP);
		singleTex(TofuItems.MOYASHIITAME);
		singleTex(TofuItems.MOYASHIOHITASHI);
		singleTex(TofuItems.SALTYMELON);

		singleTex(TofuItems.SOYMILK);
		singleTex(TofuItems.SOYMILK_ANNIN);
		singleTex(TofuItems.SOYMILK_APPLE);
		singleTex(TofuItems.SOYMILK_COCOA);
		singleTex(TofuItems.SOYMILK_FRUITS);
		singleTex(TofuItems.SOYMILK_HONEY);
		singleTex(TofuItems.SOYMILK_KINAKO);
		singleTex(TofuItems.SOYMILK_PUDDING);
		singleTex(TofuItems.SOYMILK_PUMPKIN);
		singleTex(TofuItems.SOYMILK_RAMUNE);
		singleTex(TofuItems.SOYMILK_SAKURA);
		singleTex(TofuItems.SOYMILK_STRAWBERRY);
		singleTex(TofuItems.SOYMILK_TEA);
		singleTex(TofuItems.SOYMILK_HELL_BOTTLE);
		singleTex(TofuItems.SOYMILK_SOUL_BOTTLE);
		singleTex(TofuItems.SOYMILK_PALE_BOTTLE);
		singleTex(TofuItems.SOYMILK_PALE_GLOW_BOTTLE);
		singleTex(TofuItems.KINAKO_MANJU);
		singleTex(TofuItems.ZUNDA_MANJU);
		singleTex(TofuItems.NETHER_MANJU);
		singleTex(TofuItems.SOUL_MANJU);

		singleTex(TofuItems.KINAKO_MOCHI);
		singleTex(TofuItems.CRIMSON_SOUP);
		singleTex(TofuItems.ZUNDA_MOCHI);

		singleTex(TofuItems.PUDDING);
		singleTex(TofuItems.PUDDING_SOYMILK);

		singleTex(TofuItems.NIKUJAGA);

		singleTex(TofuItems.ONIGIRI);
		singleTex(TofuItems.ONIGIRI_SALT);
		singleTex(TofuItems.YAKIONIGIRI_MISO);
		singleTex(TofuItems.YAKIONIGIRI_SHOYU);
		singleTex(TofuItems.RICE_BURGER);
		singleTex(TofuItems.RICE_NATTO);
		singleTex(TofuItems.RICE_NATTO_LEEK);
		singleTex(TofuItems.RICE_NETHER_NATTO);
		singleTex(TofuItems.RICE_NETHER_NATTO_LEEK);
		singleTex(TofuItems.RICE_TOFU);
		singleTex(TofuItems.RICE_SOBORO_TOFU);
		singleTex(TofuItems.GOHEIMOCHI);

		singleTex(TofuItems.INARI);

		singleTex(TofuItems.OKARA);
		singleTex(TofuItems.OKARASTICK);
		singleTex(TofuItems.OKARA_DONUT);

		singleTex(TofuItems.SOBOROTOFUSAUTE);

		singleTex(TofuItems.SOY_CHOCOLATE);
		singleTex(TofuItems.TOFUNIAN_SOY_CHOCOLATE);

		singleTex(TofuItems.BUCKET_SOYMILK);
		singleTex(TofuItems.BUCKET_SOYMILK_NETHER);
		singleTex(TofuItems.BUCKET_SOYMILK_SOUL);

		singleTex(TofuItems.BUCKET_BITTERN);
		singleTex(TofuItems.BUCKET_DOUBANJIANG);

		singleTex(TofuItems.TOFUFISH_BUCKET);
		singleTex(TofuItems.TOFUFISH_SOYMILK_BUCKET);

		singleTex(TofuItems.GLASSBOWL);

		singleTex(TofuItems.TOFUSOMEN);
		singleTex(TofuItems.TOFUSOMENBOWL_GLASS);

		singleTex(TofuItems.TASTYBEEFSTEW);
		singleTex(TofuItems.TASTYSTEW);

		singleTex(TofuItems.HIYAYAKKO_GLASS);
		singleTex(TofuItems.NATTOHIYAYAKKO_GLASS);

		singleTexTool(TofuItems.TOFUSCOOP);
		singleTexTool(TofuItems.TOFUSTICK);
		singleTexTool(TofuItems.ROLLINGPIN);
		singleTexTool(TofuItems.BUGLE);
		singleTexTool(TofuItems.FUKUMAME);
		singleTexTool(TofuItems.NETHER_FUKUMAME);
		singleTexTool(TofuItems.INFERNO_NETHER_FUKUMAME);
		singleTexTool(TofuItems.SOUL_FUKUMAME);
		bowItem(TofuItems.ZUNDA_BOW);
		singleTex(TofuItems.ZUNDA_ARROW);
		singleTexRodTool(TofuItems.ZUNDAMUSHROOM_ON_A_STICK);

		singleTex(TofuItems.KINAKO_BREAD);
		singleTex(TofuItems.EDAMAME_TEMPLA);
		singleTex(TofuItems.NEGIMA);
		singleTex(TofuItems.SOY_KARAAGE);
		singleTex(TofuItems.SOYMEATDON);

		singleTex(TofuItems.TOFUGEM);
		singleTex(TofuItems.ADVANCE_TOFUGEM);
		singleTex(TofuItems.TF_COIL);
		singleTex(TofuItems.TF_CIRCUIT);
		singleTex(TofuItems.TF_CAPACITOR);
		singleTex(TofuItems.TF_OSCILLATOR);
		singleTex(TofuItems.TOFU_CORE);
		singleTex(TofuItems.TF_BATTERY);
		toBlock(TofuBlocks.TF_CRAFTER);
		toBlock(TofuBlocks.TF_OVEN);
		toBlock(TofuBlocks.TF_COLLECTOR);

		singleTexTool(TofuItems.TOFU_KINU_SWORD);
		singleTexTool(TofuItems.TOFU_KINU_PICKAXE);
		singleTexTool(TofuItems.TOFU_KINU_AXE);
		singleTexTool(TofuItems.TOFU_KINU_SHOVEL);
		singleTexTool(TofuItems.TOFU_KINU_HOE);

		singleTexTool(TofuItems.TOFU_MOMEN_SWORD);
		singleTexTool(TofuItems.TOFU_MOMEN_PICKAXE);
		singleTexTool(TofuItems.TOFU_MOMEN_AXE);
		singleTexTool(TofuItems.TOFU_MOMEN_SHOVEL);
		singleTexTool(TofuItems.TOFU_MOMEN_HOE);

		singleTexTool(TofuItems.TOFU_SOLID_SWORD);
		singleTexTool(TofuItems.TOFU_SOLID_PICKAXE);
		singleTexTool(TofuItems.TOFU_SOLID_AXE);
		singleTexTool(TofuItems.TOFU_SOLID_SHOVEL);
		singleTexTool(TofuItems.TOFU_SOLID_HOE);

		singleTexTool(TofuItems.TOFU_METAL_SWORD);
		singleTexTool(TofuItems.TOFU_METAL_PICKAXE);
		singleTexTool(TofuItems.TOFU_METAL_AXE);
		singleTexTool(TofuItems.TOFU_METAL_SHOVEL);
		singleTexTool(TofuItems.TOFU_METAL_HOE);
		singleTexTool(TofuItems.TOFU_METAL_SHEARS);

		singleTexTool(TofuItems.TOFU_DIAMOND_SWORD);
		singleTexTool(TofuItems.TOFU_DIAMOND_PICKAXE);
		singleTexTool(TofuItems.TOFU_DIAMOND_AXE);
		singleTexTool(TofuItems.TOFU_DIAMOND_SHOVEL);
		singleTexTool(TofuItems.TOFU_DIAMOND_HOE);

		singleTex(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE);
		singleTex(TofuItems.ZUNDA_UPGRADE_SMITHING_TEMPLATE);

		singleTex(TofuItems.TOFU_KINU_HELMET);
		singleTex(TofuItems.TOFU_KINU_CHESTPLATE);
		singleTex(TofuItems.TOFU_KINU_LEGGINGS);
		singleTex(TofuItems.TOFU_KINU_BOOTS);

		singleTex(TofuItems.TOFU_MOMEN_HELMET);
		singleTex(TofuItems.TOFU_MOMEN_CHESTPLATE);
		singleTex(TofuItems.TOFU_MOMEN_LEGGINGS);
		singleTex(TofuItems.TOFU_MOMEN_BOOTS);

		singleTex(TofuItems.ARMOR_TOFU_SOLIDHELMET);
		singleTex(TofuItems.ARMOR_TOFU_SOLIDCHESTPLATE);
		singleTex(TofuItems.ARMOR_TOFU_SOLIDLEGGINGS);
		singleTex(TofuItems.ARMOR_TOFU_SOLIDBOOTS);

		singleTex(TofuItems.TOFU_METAL_HELMET);
		singleTex(TofuItems.TOFU_METAL_CHESTPLATE);
		singleTex(TofuItems.TOFU_METAL_LEGGINGS);
		singleTex(TofuItems.TOFU_METAL_BOOTS);

		singleTex(TofuItems.TOFU_DIAMOND_HELMET);
		singleTex(TofuItems.TOFU_DIAMOND_CHESTPLATE);
		singleTex(TofuItems.TOFU_DIAMOND_LEGGINGS);
		singleTex(TofuItems.TOFU_DIAMOND_BOOTS);

		egg(TofuItems.TOFUNIAN_SPAWNEGG);
		egg(TofuItems.TRAVELER_TOFUNIAN_SPAWNEGG);
		egg(TofuItems.TOFU_GANDLEM_SPAWNEGG);
		egg(TofuItems.TOFU_GOLEM_SPAWNEGG);
		egg(TofuItems.SHUDOFUSPIDER_SPAWNEGG);
		egg(TofuItems.TOFUCOW_SPAWNEGG);
		egg(TofuItems.TOFUPIG_SPAWNEGG);
		egg(TofuItems.TOFUSLIME_SPAWNEGG);
		egg(TofuItems.TOFUCREEPER_SPAWNEGG);
		egg(TofuItems.TOFUSPIDER_SPAWNEGG);
		egg(TofuItems.TOFUFISH_SPAWNEGG);
		egg(TofuItems.FUKUMAME_THROWER_SPAWNEGG);
		egg(TofuItems.ZUNDAMITE_SPAWNEGG);

		singleTex(TofuItems.TOMATO_SOYBEAN_STEW);
		singleTex(TofuItems.YUDOFU);
		singleTex(TofuItems.EDAMAME_RICE);

		singleTex(TofuItems.BOTTLE_DASHI);
		singleTex(TofuItems.BOTTLE_SOYOIL);
		singleTex(TofuItems.TOFUEGG);
		singleTex(TofuItems.SOYSAUSE_RAMEN);

		singleTex(TofuItems.HELL_MABOU);
		singleTex(TofuItems.RED_SOUP);
		singleTex(TofuItems.HELL_RED_SOUP);
		singleTex(TofuItems.SUKIYAKI);
		singleTex(TofuItems.TOFU_BUNS_BURGER);
		singleTex(TofuItems.STEAMED_BREAD);
		singleTex(TofuItems.STEAMED_BREAD_COCOA);

		singleTex(TofuItems.SOY_CHEESE);
		singleTex(TofuItems.SOY_NETHER_CHEESE);
		singleTex(TofuItems.SOY_SOUL_CHEESE);

		singleTex(TofuItems.NATTO_COBWEB);
		toBlock(TofuBlocks.SUSPICIOUS_TOFU_TERRAIN);

		singleTex(TofuItems.TOFUNIAN_BANNER_PATTERN);
		singleTex(TofuItems.TOFU_STEM_BOAT);
		singleTex(TofuItems.LEEK_BOAT);
		singleTex(TofuItems.LEEK_GREEN_BOAT);
		singleTex(TofuItems.TOFU_STEM_CHEST_BOAT);
		singleTex(TofuItems.LEEK_CHEST_BOAT);
		singleTex(TofuItems.LEEK_GREEN_CHEST_BOAT);

		singleTex(TofuBlocks.TOFU_METAL_CHAIN);
		singleTex(TofuBlocks.TOFU_METAL_LANTERN);
		singleTex(TofuBlocks.TOFU_METAL_SOUL_LANTERN);
		toBlock(TofuBlocks.TOFU_DETECTOR);
		itemBlockFlat(TofuBlocks.ANTENNA_BASIC);

		toBlock(TofuBlocks.SALT_BLOCK);
		toBlock(TofuBlocks.SOYBEANS_SEEDS_BLOCK);
		toBlock(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK);
		toBlock(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK);

		singleTex(TofuItems.MUSIC_DISC_GREEN_BRANCH);
	}

	public ItemModelBuilder button(Supplier<? extends ButtonBlock> button, Supplier<? extends Block> fullBlock) {
		return buttonInventory(BuiltInRegistries.BLOCK.getKey(button.get()).getPath(), texture(blockName(fullBlock)));
	}

	public void trapdoor(Supplier<? extends TrapDoorBlock> trapdoor) {
		withExistingParent(BuiltInRegistries.BLOCK.getKey(trapdoor.get()).getPath(), ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/" + blockName(trapdoor) + "_bottom"));
	}

	public void sign(Supplier<? extends SignBlock> sign) {
		withExistingParent(blockName(sign), mcLoc("item/generated"))
				.texture("layer0", modLoc("item/" + blockName(sign)));
	}

	private void woodenFence(Supplier<? extends Block> fence, Supplier<? extends Block> block) {
		getBuilder(BuiltInRegistries.BLOCK.getKey(fence.get()).getPath())
				.parent(getExistingFile(mcLoc("block/fence_inventory")))
				.texture("texture", "block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath());
	}

	public ItemModelBuilder torchItem(Supplier<Block> item) {
		return withExistingParent(BuiltInRegistries.BLOCK.getKey(item.get()).getPath(), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + BuiltInRegistries.BLOCK.getKey(item.get()).getPath()));
	}


	private ItemModelBuilder generated(String name, ResourceLocation... layers) {
		return buildItem(name, "item/generated", 0, layers);
	}


	private ItemModelBuilder buildItem(String name, String parent, int emissivity, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		if (emissivity > 0)
			builder = builder.customLoader(ItemLayerModelBuilder::begin).emissive(emissivity, emissivity, 0).renderType("minecraft:translucent", 0).end();
		return builder;
	}

	private ItemModelBuilder tool(String name, ResourceLocation... layers) {
		return buildItem(name, "item/handheld", 0, layers);
	}

	private ItemModelBuilder singleTexTool(Supplier<? extends Item> item) {
		return tool(itemPath(item).getPath(), prefix("item/" + itemPath(item).getPath()));
	}

	private ItemModelBuilder singleTexRodTool(Supplier<? extends Item> item) {
		return toolRod(itemPath(item).getPath(), prefix("item/" + itemPath(item).getPath()));
	}

	private ItemModelBuilder toolRod(String name, ResourceLocation... layers) {
		return buildItem(name, "item/handheld_rod", 0, layers);
	}

	private ItemModelBuilder singleTex(Supplier<? extends ItemLike> item) {
		return generated(itemPath(item).getPath(), prefix("item/" + itemPath(item).getPath()));
	}

	private ItemModelBuilder emmisiveTex(Supplier<? extends Item> item) {
		return singleTex(item).customLoader(ItemLayerModelBuilder::begin).emissive(15, 15, 0).renderType("minecraft:translucent", 0).end();
	}

	public ItemModelBuilder glowBowItem(Supplier<? extends Item> item) {
		ResourceLocation id = BuiltInRegistries.ITEM.getKey(item.get());
		buildItem(id.getPath() + "_pulling_0", mcLoc("item/bow").toString(), 15, modLoc("item/" + id.getPath() + "_pulling_0"));
		buildItem(id.getPath() + "_pulling_1", mcLoc("item/bow").toString(), 15, modLoc("item/" + id.getPath() + "_pulling_1"));
		buildItem(id.getPath() + "_pulling_2", mcLoc("item/bow").toString(), 15, modLoc("item/" + id.getPath() + "_pulling_2"));
		return withExistingParent(id.getPath(), mcLoc("item/bow"))
				.customLoader(ItemLayerModelBuilder::begin).emissive(15, 15, 0).renderType("minecraft:translucent", 0).end()
				.texture("layer0", modLoc("item/" + id.getPath()))
				.override().predicate(ResourceLocation.parse("pulling"), 1).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_0"))).end()
				.override().predicate(ResourceLocation.parse("pulling"), 1).predicate(ResourceLocation.parse("pull"), 0.65F).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_1"))).end()
				.override().predicate(ResourceLocation.parse("pulling"), 1).predicate(ResourceLocation.parse("pull"), 0.9F).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_2"))).end();
	}

	public ItemModelBuilder bowItem(Supplier<? extends Item> item) {
		ResourceLocation id = BuiltInRegistries.ITEM.getKey(item.get());
		withExistingParent(id.getPath() + "_pulling_0", mcLoc("item/bow")).texture("layer0", modLoc("item/" + id.getPath() + "_pulling_0"));
		withExistingParent(id.getPath() + "_pulling_1", mcLoc("item/bow")).texture("layer0", modLoc("item/" + id.getPath() + "_pulling_1"));
		withExistingParent(id.getPath() + "_pulling_2", mcLoc("item/bow")).texture("layer0", modLoc("item/" + id.getPath() + "_pulling_2"));
		return withExistingParent(id.getPath(), mcLoc("item/bow"))
				.texture("layer0", modLoc("item/" + id.getPath()))
				.override().predicate(ResourceLocation.parse("pulling"), 1).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_0"))).end()
				.override().predicate(ResourceLocation.parse("pulling"), 1).predicate(ResourceLocation.parse("pull"), 0.65F).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_1"))).end()
				.override().predicate(ResourceLocation.parse("pulling"), 1).predicate(ResourceLocation.parse("pull"), 0.9F).model(getExistingFile(modLoc("item/" + id.getPath() + "_pulling_2"))).end();
	}

	private void woodenButton(Supplier<? extends Block> button, String variant) {
		getBuilder(BuiltInRegistries.BLOCK.getKey(button.get()).getPath())
				.parent(getExistingFile(mcLoc("block/button_inventory")))
				.texture("texture", "block/wood/planks_" + variant + "_0");
	}

	private void woodenFence(Block fence, String variant) {
		getBuilder(BuiltInRegistries.BLOCK.getKey(fence).getPath())
				.parent(getExistingFile(mcLoc("block/fence_inventory")))
				.texture("texture", "block/wood/planks_" + variant + "_0");
	}

	public ItemModelBuilder wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
		return wallInventory(BuiltInRegistries.BLOCK.getKey(wall.get()).getPath(), texture(blockName(fullBlock)));
	}

	private ItemModelBuilder toBlock(Supplier<? extends Block> b) {
		return toBlockModel(b, BuiltInRegistries.BLOCK.getKey(b.get()).getPath());
	}

	private ItemModelBuilder toBlockModel(Supplier<? extends Block> b, String model) {
		return toBlockModel(b, prefix("block/" + model));
	}

	private ItemModelBuilder toBlockModel(Supplier<? extends Block> b, ResourceLocation model) {
		return withExistingParent(BuiltInRegistries.BLOCK.getKey(b.get()).getPath(), model);
	}

	public ItemModelBuilder itemBlockFlat(Supplier<? extends Block> block) {
		return itemBlockFlat(block, blockName(block));
	}

	public ItemModelBuilder itemBlockFlat(Supplier<? extends Block> block, String name) {
		return withExistingParent(blockName(block), mcLoc("item/generated"))
				.texture("layer0", modLoc("block/" + name));
	}

	public ItemModelBuilder egg(Supplier<Item> item) {
		return withExistingParent(BuiltInRegistries.ITEM.getKey(item.get()).getPath(), mcLoc("item/template_spawn_egg"));
	}

	public String blockName(Supplier<? extends Block> block) {
		return BuiltInRegistries.BLOCK.getKey(block.get()).getPath();
	}

	@Override
	public String getName() {
		return "TofuCraftReload item and itemblock models";
	}

	private ResourceLocation texture(String name) {
		return modLoc("block/" + name);
	}

	public ResourceLocation itemPath(Supplier<? extends ItemLike> item) {
		return BuiltInRegistries.ITEM.getKey(item.get().asItem());
	}
}
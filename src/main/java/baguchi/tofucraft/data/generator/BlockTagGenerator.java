package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {
	public BlockTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider, TofuCraftReload.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider p_256380_) {
		tag(BlockTags.MINEABLE_WITH_SHOVEL).add(TofuBlocks.KINUTOFU.get(), TofuBlocks.MOMENTOFU.get(), TofuBlocks.HELLTOFU.get(), TofuBlocks.SOULTOFU.get()
				, TofuBlocks.GRILLEDTOFU.get(), TofuBlocks.ZUNDATOFU.get()
				, TofuBlocks.TOFUSTAIR_KINU.get(), TofuBlocks.TOFUSTAIR_MOMEN.get(), TofuBlocks.TOFUSTAIR_ZUNDA.get(), TofuBlocks.TOFUSTAIR_HELL.get(), TofuBlocks.TOFUSTAIR_SOUL.get()
				, TofuBlocks.TOFUSLAB_KINU.get(), TofuBlocks.TOFUSLAB_MOMEN.get(), TofuBlocks.TOFUSLAB_ZUNDA.get(), TofuBlocks.TOFUSLAB_HELL.get(), TofuBlocks.TOFUSLAB_SOUL.get()
				, TofuBlocks.TOFULADDER_KINU.get(), TofuBlocks.TOFULADDER_MOMEN.get()
				, TofuBlocks.TOFUFENCE_KINU.get(), TofuBlocks.TOFUFENCE_MOMEN.get(), TofuBlocks.TOFUFENCE_HELL.get(), TofuBlocks.TOFUFENCE_SOUL.get()
				, TofuBlocks.TOFUDOOR_KINU.get(), TofuBlocks.TOFUDOOR_MOMEN.get(), TofuBlocks.TOFUDOOR_HELL.get(), TofuBlocks.TOFUDOOR_SOUL.get()
				, TofuBlocks.TOFUTRAPDOOR_KINU.get(), TofuBlocks.TOFUTRAPDOOR_MOMEN.get(), TofuBlocks.TOFUTRAPDOOR_HELL.get(), TofuBlocks.TOFUTRAPDOOR_SOUL.get()
						, TofuBlocks.TOFU_TERRAIN.get(), TofuBlocks.MABOU_TERRAIN.get(), TofuBlocks.TOFU_TERRAIN_ZUNDA.get(), TofuBlocks.ORE_TOFU_DIAMOND.get(), TofuBlocks.ORE_TOFUGEM.get()
				, TofuBlocks.EGGTOFU.get(), TofuBlocks.TOFUSTAIR_EGG.get(), TofuBlocks.TOFUSLAB_EGG.get()
				, TofuBlocks.SESAMETOFU.get(), TofuBlocks.TOFUSTAIR_SESAME.get(), TofuBlocks.TOFUSLAB_SESAME.get()
				, TofuBlocks.MISOTOFU.get(), TofuBlocks.TOFUSTAIR_MISO.get(), TofuBlocks.TOFUSLAB_MISO.get()
						, TofuBlocks.DRIEDTOFU.get(), TofuBlocks.TOFUSTAIR_DRIED.get(), TofuBlocks.TOFUSLAB_DRIED.get(), TofuBlocks.MINCEDTOFU.get())
				.add(TofuBlocks.SALT_BLOCK.get());
		tag(BlockTags.MINEABLE_WITH_PICKAXE).add(TofuBlocks.ISHITOFU.get(), TofuBlocks.ISHITOFU_BRICK.get(), TofuBlocks.ISHITOFU_SMOOTH_BRICK.get(), TofuBlocks.ISHITOFU_CHISELED_BRICK.get()
				, TofuBlocks.METALTOFU.get(), TofuBlocks.METAL_TOFU_GRATE.get(), TofuBlocks.METAL_TOFU_LUMP.get(), TofuBlocks.DIAMONDTOFU.get(), TofuBlocks.TOFU_GEM_BLOCK.get(), TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.get()
				, TofuBlocks.HELLTOFU_BRICK.get(), TofuBlocks.HELLTOFU_SMOOTH_BRICK.get()
				, TofuBlocks.SOULTOFU_BRICK.get(), TofuBlocks.SOULTOFU_SMOOTH_BRICK.get()
				, TofuBlocks.EGGTOFU_BRICK.get(), TofuBlocks.TOFUSTAIR_EGGBRICK.get(), TofuBlocks.TOFUSLAB_EGGBRICK.get()
				, TofuBlocks.ZUNDATOFU_BRICK.get(), TofuBlocks.ZUNDATOFU_SMOOTH_BRICK.get(), TofuBlocks.TOFUSTAIR_ZUNDABRICK.get(), TofuBlocks.TOFUSLAB_ZUNDABRICK.get()
				, TofuBlocks.TOFUSTAIR_ISHI.get(), TofuBlocks.TOFUSTAIR_METAL.get(), TofuBlocks.TOFUSTAIR_ISHIBRICK.get(), TofuBlocks.TOFUSTAIR_HELLBRICK.get(), TofuBlocks.TOFUSTAIR_SOULBRICK.get()
				, TofuBlocks.TOFUSLAB_ISHI.get(), TofuBlocks.TOFUSLAB_METAL.get(), TofuBlocks.TOFUSLAB_ISHIBRICK.get(), TofuBlocks.TOFUSLAB_HELLBRICK.get(), TofuBlocks.TOFUSLAB_SOULBRICK.get()
				, TofuBlocks.TOFULADDER_ISHI.get(), TofuBlocks.TOFULADDER_ISHIBRICK.get(), TofuBlocks.TOFULADDER_METAL.get()
				, TofuBlocks.TOFUFENCE_ISHI.get(), TofuBlocks.TOFUFENCE_METAL.get()
				, TofuBlocks.TOFUDOOR_ISHI.get(), TofuBlocks.TOFUDOOR_METAL.get()
				, TofuBlocks.TOFUTRAPDOOR_ISHI.get(), TofuBlocks.TOFUTRAPDOOR_METAL.get()
				, TofuBlocks.TOFUCHEST.get()
				, TofuBlocks.TOFU_BEDROCK.get()
				, TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get()
				, TofuBlocks.TOFUSLATE.get()
				, TofuBlocks.SALT_FURNACE.get()
				, TofuBlocks.SOYMILK_CAULDRON.get(), TofuBlocks.SOYMILK_NETHER_CAULDRON.get(), TofuBlocks.SOYMILK_SOUL_CAULDRON.get()
				, TofuBlocks.TOFU_METAL_LANTERN.get(), TofuBlocks.TOFU_METAL_SOUL_LANTERN.get(), TofuBlocks.TOFU_METAL_CHAIN.get()
				, TofuBlocks.TOFU_DETECTOR.get(), TofuBlocks.TF_STORAGE.get(), TofuBlocks.TF_CRAFTER.get(), TofuBlocks.TF_OVEN.get(), TofuBlocks.ANTENNA_BASIC.get(), TofuBlocks.TOFU_WORK_STATION.get(), TofuBlocks.TF_COLLECTOR.get()
				, TofuBlocks.TOFUNIAN_STATUE.get());
		tag(BlockTags.MINEABLE_WITH_AXE).add(TofuBlocks.SALTPAN.get()
				, TofuBlocks.LEEK_GREEN_STEM.get(), TofuBlocks.LEEK_STEM.get()
				, TofuBlocks.TOFU_STEM.get(), TofuBlocks.TOFU_STEM_PLANKS.get(), TofuBlocks.TOFU_STEM_PLANKS_STAIR.get(), TofuBlocks.TOFU_STEM_PLANKS_SLAB.get(), TofuBlocks.TOFU_STEM_FENCE.get(), TofuBlocks.TOFU_STEM_FENCE_GATE.get(), TofuBlocks.TOFU_STEM_DOOR.get(), TofuBlocks.TOFU_STEM_TRAPDOOR.get()
				, TofuBlocks.LEEK_STEM.get(), TofuBlocks.LEEK_PLANKS.get(), TofuBlocks.LEEK_PLANKS_STAIR.get(), TofuBlocks.LEEK_PLANKS_SLAB.get(), TofuBlocks.LEEK_FENCE.get(), TofuBlocks.LEEK_FENCE_GATE.get()
				, TofuBlocks.LEEK_GREEN_STEM.get(), TofuBlocks.LEEK_GREEN_PLANKS.get(), TofuBlocks.LEEK_GREEN_PLANKS_STAIR.get(), TofuBlocks.LEEK_GREEN_PLANKS_SLAB.get(), TofuBlocks.LEEK_GREEN_FENCE.get(), TofuBlocks.LEEK_GREEN_FENCE_GATE.get(), TofuBlocks.LEEK_GREEN_DOOR.get(), TofuBlocks.LEEK_GREEN_TRAPDOOR.get()
				, TofuBlocks.MORIJIO.get()
				, TofuBlocks.BARREL_MISO.get(), TofuBlocks.BARREL_MISOTOFU.get(), TofuBlocks.BARREL_ADV_TOFUGEM.get()
				, TofuBlocks.TOFU_STEM_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_SIGN.get(), TofuBlocks.TOFU_STEM_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.get()
				, TofuBlocks.LEEK_GREEN_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_SIGN.get(), TofuBlocks.LEEK_GREEN_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.get()
						, TofuBlocks.LEEK_SIGN.get(), TofuBlocks.LEEK_WALL_SIGN.get(), TofuBlocks.LEEK_HANGING_SIGN.get(), TofuBlocks.LEEK_WALL_HANGING_SIGN.get())
				.add(TofuBlocks.TOFU_STEM_PRESSURE_PLATE.get(), TofuBlocks.TOFU_STEM_BUTTON.get())
				.add(TofuBlocks.LEEK_PRESSURE_PLATE.get(), TofuBlocks.LEEK_BUTTON.get())
				.add(TofuBlocks.LEEK_GREEN_PRESSURE_PLATE.get(), TofuBlocks.LEEK_GREEN_BUTTON.get());
		tag(BlockTags.MINEABLE_WITH_HOE).add(TofuBlocks.RICE_ROOT.get(), TofuBlocks.LEAVES_APRICOT.get(), TofuBlocks.LEAVES_TOFU.get(), TofuBlocks.RICE_BLOCK.get(), TofuBlocks.SOYBEANS_SEEDS_BLOCK.get(), TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.get(), TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.get());

		tag(BlockTags.NEEDS_STONE_TOOL)
				.add(TofuBlocks.METALTOFU.get(), TofuBlocks.TOFUSTAIR_METAL.get(), TofuBlocks.TOFUSLAB_METAL.get(), TofuBlocks.TOFULADDER_METAL.get(), TofuBlocks.TOFUFENCE_METAL.get(), TofuBlocks.TOFUDOOR_METAL.get(), TofuBlocks.TOFUTRAPDOOR_METAL.get())
				.add(TofuBlocks.TOFU_GEM_BLOCK.get()).add(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.get());
		tag(BlockTags.NEEDS_IRON_TOOL)
				.add(TofuBlocks.DIAMONDTOFU.get(), TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get());
		tag(BlockTags.PIGLIN_REPELLENTS).add(TofuBlocks.SOYBEAN_SOUL.get());

		tag(TofuTags.Blocks.SOFT_TOFU).add(TofuBlocks.KINUTOFU.get(), TofuBlocks.MOMENTOFU.get(), TofuBlocks.HELLTOFU.get(), TofuBlocks.SOULTOFU.get()
				, TofuBlocks.GRILLEDTOFU.get(), TofuBlocks.ZUNDATOFU.get(), TofuBlocks.MINCEDTOFU.get());
		tag(TofuTags.Blocks.TOFU_TERRAIN).add(TofuBlocks.TOFU_TERRAIN.get(), TofuBlocks.TOFU_TERRAIN_ZUNDA.get());

		tag(BlockTags.REPLACEABLE).add(TofuBlocks.LEEK.get());
		tag(TofuTags.Blocks.TF_TRANSMITTER).addTag(BlockTags.REPLACEABLE).add(TofuBlocks.ANTENNA_BASIC.get());


		tag(BlockTags.BEDS).add(TofuBlocks.TOFUBED.get());
		tag(BlockTags.WALLS).add(TofuBlocks.TOFUFENCE_KINU.get(), TofuBlocks.TOFUFENCE_MOMEN.get(), TofuBlocks.TOFUFENCE_HELL.get(), TofuBlocks.TOFUFENCE_SOUL.get(), TofuBlocks.TOFUFENCE_ISHI.get(), TofuBlocks.TOFUFENCE_METAL.get())
				.add(TofuBlocks.TOFUFENCE_GRILLED.get(), TofuBlocks.TOFUFENCE_ZUNDA.get());
		tag(BlockTags.CLIMBABLE).add(TofuBlocks.TOFULADDER_KINU.get(), TofuBlocks.TOFULADDER_MOMEN.get(), TofuBlocks.TOFULADDER_ISHI.get(), TofuBlocks.TOFULADDER_ISHIBRICK.get(), TofuBlocks.TOFULADDER_METAL.get(), TofuBlocks.TOFULADDER_GRILLED.get(), TofuBlocks.TOFULADDER_ZUNDA.get(), TofuBlocks.TOFULADDER_HELL.get(), TofuBlocks.TOFULADDER_SOUL.get());
		tag(BlockTags.WITHER_IMMUNE).add(TofuBlocks.TOFU_BEDROCK.get());
		tag(BlockTags.DRAGON_IMMUNE).add(TofuBlocks.TOFU_BEDROCK.get());
		tag(BlockTags.FEATURES_CANNOT_REPLACE).add(TofuBlocks.TOFU_BEDROCK.get());
		tag(BlockTags.TRAPDOORS).add(TofuBlocks.TOFUTRAPDOOR_KINU.get(), TofuBlocks.TOFUTRAPDOOR_MOMEN.get(), TofuBlocks.TOFUTRAPDOOR_HELL.get(), TofuBlocks.TOFUTRAPDOOR_SOUL.get(), TofuBlocks.TOFUTRAPDOOR_ISHI.get(), TofuBlocks.TOFUTRAPDOOR_METAL.get(), TofuBlocks.TOFUTRAPDOOR_GRILLED.get(), TofuBlocks.TOFUTRAPDOOR_ZUNDA.get());
		tag(BlockTags.WOODEN_DOORS).add(TofuBlocks.TOFUDOOR_KINU.get(), TofuBlocks.TOFUDOOR_MOMEN.get(), TofuBlocks.TOFUDOOR_HELL.get(), TofuBlocks.TOFUDOOR_SOUL.get(), TofuBlocks.TOFUDOOR_ISHI.get(), TofuBlocks.TOFUDOOR_GRILLED.get(), TofuBlocks.TOFUDOOR_ZUNDA.get())
				.add(TofuBlocks.TOFU_STEM_DOOR.get(), TofuBlocks.LEEK_GREEN_DOOR.get());
		tag(BlockTags.DOORS).add(TofuBlocks.TOFUDOOR_METAL.get()).add(TofuBlocks.TOFUDOOR_KINU.get(), TofuBlocks.TOFUDOOR_MOMEN.get(), TofuBlocks.TOFUDOOR_HELL.get(), TofuBlocks.TOFUDOOR_SOUL.get(), TofuBlocks.TOFUDOOR_ISHI.get(), TofuBlocks.TOFUDOOR_GRILLED.get(), TofuBlocks.TOFUDOOR_ZUNDA.get())
				.add(TofuBlocks.TOFU_STEM_DOOR.get(), TofuBlocks.LEEK_GREEN_DOOR.get());

		tag(BlockTags.WOODEN_TRAPDOORS).add(TofuBlocks.TOFUTRAPDOOR_KINU.get(), TofuBlocks.TOFUTRAPDOOR_MOMEN.get(), TofuBlocks.TOFUTRAPDOOR_HELL.get(), TofuBlocks.TOFUTRAPDOOR_SOUL.get(), TofuBlocks.TOFUTRAPDOOR_ISHI.get(), TofuBlocks.TOFUTRAPDOOR_GRILLED.get(), TofuBlocks.TOFUTRAPDOOR_ZUNDA.get())
				.add(TofuBlocks.TOFU_STEM_TRAPDOOR.get(), TofuBlocks.LEEK_GREEN_TRAPDOOR.get());
		tag(BlockTags.TRAPDOORS).add(TofuBlocks.TOFUTRAPDOOR_METAL.get()).add(TofuBlocks.TOFUTRAPDOOR_KINU.get(), TofuBlocks.TOFUTRAPDOOR_MOMEN.get(), TofuBlocks.TOFUTRAPDOOR_HELL.get(), TofuBlocks.TOFUTRAPDOOR_SOUL.get(), TofuBlocks.TOFUTRAPDOOR_ISHI.get(), TofuBlocks.TOFUTRAPDOOR_GRILLED.get(), TofuBlocks.TOFUTRAPDOOR_ZUNDA.get())
				.add(TofuBlocks.TOFU_STEM_TRAPDOOR.get(), TofuBlocks.LEEK_GREEN_TRAPDOOR.get());

		tag(BlockTags.SOUL_FIRE_BASE_BLOCKS).add(TofuBlocks.SOULTOFU.get(), TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.get());
		tag(BlockTags.INFINIBURN_OVERWORLD).add(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.get());

		tag(BlockTags.SOUL_SPEED_BLOCKS).add(TofuBlocks.SOULTOFU.get());
		tag(BlockTags.CAULDRONS).add(TofuBlocks.SOYMILK_CAULDRON.get(), TofuBlocks.SOYMILK_NETHER_CAULDRON.get(), TofuBlocks.SOYMILK_SOUL_CAULDRON.get());

		tag(BlockTags.PLANKS).add(TofuBlocks.TOFU_STEM_PLANKS.get()).add(TofuBlocks.LEEK_PLANKS.get()).add(TofuBlocks.LEEK_GREEN_PLANKS.get());
		tag(BlockTags.WOODEN_SLABS).add(TofuBlocks.TOFU_STEM_PLANKS_SLAB.get()).add(TofuBlocks.LEEK_PLANKS_SLAB.get()).add(TofuBlocks.LEEK_GREEN_PLANKS_SLAB.get());
		tag(BlockTags.WOODEN_STAIRS).add(TofuBlocks.TOFU_STEM_PLANKS_STAIR.get()).add(TofuBlocks.LEEK_PLANKS_STAIR.get()).add(TofuBlocks.LEEK_GREEN_PLANKS_STAIR.get());
		tag(BlockTags.WOODEN_FENCES).add(TofuBlocks.TOFU_STEM_FENCE.get()).add(TofuBlocks.LEEK_FENCE.get()).add(TofuBlocks.LEEK_GREEN_FENCE.get());
		tag(BlockTags.FENCE_GATES).add(TofuBlocks.TOFU_STEM_FENCE_GATE.get()).add(TofuBlocks.LEEK_FENCE_GATE.get()).add(TofuBlocks.LEEK_GREEN_FENCE_GATE.get());

		this.tag(BlockTags.FEATURES_CANNOT_REPLACE).add(TofuBlocks.TOFU_BEDROCK.get(), TofuBlocks.TOFUCHEST.get());
		this.tag(BlockTags.PORTALS).add(TofuBlocks.TOFU_PORTAL.get());


		tag(TofuTags.Blocks.TOFU_WORLD_CARVER_REPLACEABLE).add(TofuBlocks.TOFU_TERRAIN.get(), TofuBlocks.TOFU_TERRAIN_ZUNDA.get(), TofuBlocks.SOYMILK.get())
				.add(TofuBlocks.TOFUSLATE.get());
		tag(BlockTags.LEAVES).add(TofuBlocks.LEAVES_APRICOT.get(), TofuBlocks.LEAVES_TOFU.get());
		this.tag(BlockTags.REPLACEABLE_BY_TREES).add(TofuBlocks.LEAVES_APRICOT.get(), TofuBlocks.LEAVES_TOFU.get(), TofuBlocks.LEEK.get());
		this.tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add(TofuBlocks.TOFU_STEM.get()).add(TofuBlocks.LEEK_STEM.get()).add(TofuBlocks.LEEK_GREEN_STEM.get());
		tag(BlockTags.CROPS).add(TofuBlocks.RICE_CROP.get()).add(TofuBlocks.SOYBEAN.get()).add(TofuBlocks.SOYBEAN_NETHER.get()).add(TofuBlocks.SOYBEAN_SOUL.get()).add(TofuBlocks.SOYBEAN_PALE.get()).add(TofuBlocks.LEEK_CROP.get());
		tag(BlockTags.SAPLINGS).add(TofuBlocks.SAPLING_TOFU.get(), TofuBlocks.SAPLING_APRICOT.get());
		tag(BlockTags.WOODEN_SLABS).add(TofuBlocks.TOFU_STEM_PLANKS_SLAB.get());
		tag(BlockTags.WOODEN_STAIRS).add(TofuBlocks.TOFU_STEM_PLANKS_STAIR.get());
		this.tag(BlockTags.CANDLE_CAKES).add(TofuBlocks.TOFU_CANDLE_CAKE.get(), TofuBlocks.WHITE_TOFU_CANDLE_CAKE.get(), TofuBlocks.ORANGE_TOFU_CANDLE_CAKE.get(), TofuBlocks.MAGENTA_TOFU_CANDLE_CAKE.get(), TofuBlocks.LIGHT_BLUE_TOFU_CANDLE_CAKE.get(), TofuBlocks.YELLOW_TOFU_CANDLE_CAKE.get(), TofuBlocks.LIME_TOFU_CANDLE_CAKE.get(), TofuBlocks.PINK_TOFU_CANDLE_CAKE.get(), TofuBlocks.GRAY_TOFU_CANDLE_CAKE.get(), TofuBlocks.LIGHT_GRAY_TOFU_CANDLE_CAKE.get(), TofuBlocks.CYAN_TOFU_CANDLE_CAKE.get(), TofuBlocks.PURPLE_TOFU_CANDLE_CAKE.get(), TofuBlocks.BLUE_TOFU_CANDLE_CAKE.get(), TofuBlocks.BROWN_TOFU_CANDLE_CAKE.get(), TofuBlocks.GREEN_TOFU_CANDLE_CAKE.get(), TofuBlocks.RED_TOFU_CANDLE_CAKE.get(), TofuBlocks.BLACK_TOFU_CANDLE_CAKE.get());
		this.tag(BlockTags.CANDLE_CAKES).add(TofuBlocks.ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.WHITE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ORANGE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.MAGENTA_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.LIGHT_BLUE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.YELLOW_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.LIME_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.PINK_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.GRAY_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.LIGHT_GRAY_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.CYAN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.PURPLE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.BLUE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.BROWN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.GREEN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.RED_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.BLACK_ZUNDA_TOFU_CANDLE_CAKE.get());
		this.tag(BlockTags.CANDLE_CAKES).add(TofuBlocks.SOYCHEESE_CANDLE_TART.get(), TofuBlocks.WHITE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.ORANGE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.MAGENTA_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.LIGHT_BLUE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.YELLOW_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.LIME_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.PINK_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.GRAY_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.LIGHT_GRAY_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.CYAN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.PURPLE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.BLUE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.BROWN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.GREEN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.RED_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.BLACK_SOYCHEESE_CANDLE_TART.get());
		this.tag(BlockTags.REPLACEABLE_BY_TREES).add(TofuBlocks.LEEK.get(), TofuBlocks.ZUNDATOFU_MUSHROOM.get());
		this.tag(BlockTags.STANDING_SIGNS).add(TofuBlocks.TOFU_STEM_SIGN.get(), TofuBlocks.LEEK_GREEN_SIGN.get(), TofuBlocks.LEEK_SIGN.get());
		this.tag(BlockTags.WALL_SIGNS).add(TofuBlocks.TOFU_STEM_WALL_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_SIGN.get(), TofuBlocks.LEEK_WALL_SIGN.get());
		this.tag(BlockTags.CEILING_HANGING_SIGNS).add(TofuBlocks.TOFU_STEM_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_HANGING_SIGN.get(), TofuBlocks.LEEK_HANGING_SIGN.get());
		this.tag(BlockTags.WALL_HANGING_SIGNS).add(TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.get(), TofuBlocks.LEEK_WALL_HANGING_SIGN.get());


		this.tag(BlockTags.SMALL_FLOWERS).add(TofuBlocks.TOFU_FLOWER.get());
		this.tag(BlockTags.BEE_GROWABLES).add(TofuBlocks.SOYBEAN.get()).add(TofuBlocks.SOYBEAN_NETHER.get()).add(TofuBlocks.SOYBEAN_SOUL.get()).add(TofuBlocks.SOYBEAN_PALE.get())
				.add(TofuBlocks.RICE_CROP.get());
		this.tag(BlockTags.MAINTAINS_FARMLAND).add(TofuBlocks.SOYBEAN.get());
		this.tag(Tags.Blocks.ORES).add(TofuBlocks.ORE_TOFUGEM.get(), TofuBlocks.ORE_TOFU_DIAMOND.get(), TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get());
	}
}
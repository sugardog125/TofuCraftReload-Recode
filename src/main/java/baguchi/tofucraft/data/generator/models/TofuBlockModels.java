package baguchi.tofucraft.data.generator.models;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.CandleTofuCakeBlock;
import baguchi.tofucraft.data.provider.TofuBlockstateModelProvider;
import baguchi.tofucraft.data.resources.builder.TofuBlockFamilies;
import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.blockstates.BlockStateGenerator;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TofuBlockModels extends TofuBlockstateModelProvider {
	private Supplier<CandleTofuCakeBlock> block;

	public TofuBlockModels(Consumer<BlockStateGenerator> blockStateOutput, ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
		super(blockStateOutput, itemModelOutput, modelOutput);
	}
	@Override
	public void run() {
		TofuBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateModel).forEach((family) -> family(family.getBaseBlock()).generateFor(family));


		createTrivialCube(TofuBlocks.ISHITOFU_SMOOTH_BRICK.get());
		createTrivialCube(TofuBlocks.ISHITOFU_CHISELED_BRICK.get());
		createCutoutCube(TofuBlocks.METAL_TOFU_GRATE.get());
		createTrivialCube(TofuBlocks.METAL_TOFU_LUMP.get());
		createTrivialCube(TofuBlocks.DIAMONDTOFU.get());
		createTrivialCube(TofuBlocks.TOFU_GEM_BLOCK.get());
		createTrivialCube(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.get());

		logWithHorizontal(TofuBlocks.GRILLEDTOFU.get());

		createTrivialCube(TofuBlocks.ZUNDATOFU_SMOOTH_BRICK.get());

		family(TofuBlocks.ZUNDATOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_ZUNDABRICK.get())
				.slab(TofuBlocks.TOFUSLAB_ZUNDABRICK.get());

		family(TofuBlocks.EGGTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_EGG.get())
				.slab(TofuBlocks.TOFUSLAB_EGG.get());

		family(TofuBlocks.SESAMETOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_SESAME.get())
				.slab(TofuBlocks.TOFUSLAB_SESAME.get());

		family(TofuBlocks.EGGTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_EGGBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_EGGBRICK.get());

		createTrivialCube(TofuBlocks.HELLTOFU_SMOOTH_BRICK.get());
		createTrivialCube(TofuBlocks.SOULTOFU_SMOOTH_BRICK.get());
		createTrivialCube(TofuBlocks.MINCEDTOFU.get());

		family(TofuBlocks.HELLTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_HELLBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_HELLBRICK.get());

		family(TofuBlocks.SOULTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_SOULBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_SOULBRICK.get());

		family(TofuBlocks.KINUTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_KINU.get())
				.slab(TofuBlocks.TOFUSLAB_KINU.get())
				.wall(TofuBlocks.TOFUFENCE_KINU.get())
				.door(TofuBlocks.TOFUDOOR_KINU.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_KINU.get());

		family(TofuBlocks.MOMENTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_MOMEN.get())
				.slab(TofuBlocks.TOFUSLAB_MOMEN.get())
				.wall(TofuBlocks.TOFUFENCE_MOMEN.get())
				.door(TofuBlocks.TOFUDOOR_MOMEN.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_MOMEN.get());

		family(TofuBlocks.ISHITOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_ISHI.get())
				.slab(TofuBlocks.TOFUSLAB_ISHI.get())
				.wall(TofuBlocks.TOFUFENCE_ISHI.get())
				.door(TofuBlocks.TOFUDOOR_ISHI.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_ISHI.get());

		family(TofuBlocks.METALTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_METAL.get())
				.slab(TofuBlocks.TOFUSLAB_METAL.get())
				.wall(TofuBlocks.TOFUFENCE_METAL.get())
				.door(TofuBlocks.TOFUDOOR_METAL.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_METAL.get());

		family(TofuBlocks.HELLTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_HELL.get())
				.slab(TofuBlocks.TOFUSLAB_HELL.get())
				.wall(TofuBlocks.TOFUFENCE_HELL.get())
				.door(TofuBlocks.TOFUDOOR_HELL.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_HELL.get());
		family(TofuBlocks.SOULTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_SOUL.get())
				.slab(TofuBlocks.TOFUSLAB_SOUL.get())
				.wall(TofuBlocks.TOFUFENCE_SOUL.get())
				.door(TofuBlocks.TOFUDOOR_SOUL.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_SOUL.get());
		familyWithExistingFullBlock(TofuBlocks.GRILLEDTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_GRILLED.get())
				.slab(TofuBlocks.TOFUSLAB_GRILLED.get())
				.wall(TofuBlocks.TOFUFENCE_GRILLED.get())
				.door(TofuBlocks.TOFUDOOR_GRILLED.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_GRILLED.get());

		family(TofuBlocks.ZUNDATOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_ZUNDA.get())
				.slab(TofuBlocks.TOFUSLAB_ZUNDA.get())
				.wall(TofuBlocks.TOFUFENCE_ZUNDA.get())
				.door(TofuBlocks.TOFUDOOR_ZUNDA.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_ZUNDA.get());

		family(TofuBlocks.ISHITOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_ISHIBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_ISHIBRICK.get());

		family(TofuBlocks.MISOTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_MISO.get())
				.slab(TofuBlocks.TOFUSLAB_MISO.get());

		family(TofuBlocks.DRIEDTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_DRIED.get())
				.slab(TofuBlocks.TOFUSLAB_DRIED.get());

		this.createNormalTorch(TofuBlocks.TOFUTORCH_KINU.get(), TofuBlocks.WALLTOFUTORCH_KINU.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_MOMEN.get(), TofuBlocks.WALLTOFUTORCH_MOMEN.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_ISHI.get(), TofuBlocks.WALLTOFUTORCH_ISHI.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_METAL.get(), TofuBlocks.WALLTOFUTORCH_METAL.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_GRILLED.get(), TofuBlocks.WALLTOFUTORCH_GRILLED.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_ZUNDA.get(), TofuBlocks.WALLTOFUTORCH_ZUNDA.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_HELL.get(), TofuBlocks.WALLTOFUTORCH_HELL.get());
		this.createNormalTorch(TofuBlocks.TOFUTORCH_SOUL.get(), TofuBlocks.WALLTOFUTORCH_SOUL.get());


		createLadder(TofuBlocks.TOFULADDER_KINU.get());
		createLadder(TofuBlocks.TOFULADDER_MOMEN.get());
		createLadder(TofuBlocks.TOFULADDER_ISHI.get());
		createLadder(TofuBlocks.TOFULADDER_ISHIBRICK.get());
		createLadder(TofuBlocks.TOFULADDER_METAL.get());
		createLadder(TofuBlocks.TOFULADDER_GRILLED.get());
		createLadder(TofuBlocks.TOFULADDER_ZUNDA.get());
		createLadder(TofuBlocks.TOFULADDER_HELL.get());
		createLadder(TofuBlocks.TOFULADDER_SOUL.get());
		/*this.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_KINU.get());
		this.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_MOMEN.get());
		this.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_ISHI.get());
		this.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_METAL.get());
		this.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_GRILLED.get());
		this.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_ZUNDA.get());
		this.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_HELL.get());
		this.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_SOUL.get());
*/
		/*stairs(TofuBlocks.TOFUSTAIR_KINU, TofuBlocks.KINUTOFU);
		stairs(TofuBlocks.TOFUSTAIR_MOMEN, TofuBlocks.MOMENTOFU);
		stairs(TofuBlocks.TOFUSTAIR_ISHI, TofuBlocks.ISHITOFU);
		stairs(TofuBlocks.TOFUSTAIR_METAL, TofuBlocks.METALTOFU);
		stairsBlock(TofuBlocks.TOFUSTAIR_GRILLED.get(), texture(name(TofuBlocks.MOMENTOFU)), texture(name(TofuBlocks.GRILLEDTOFU) + "_top"), texture(name(TofuBlocks.GRILLEDTOFU) + "_top"));
		stairs(TofuBlocks.TOFUSTAIR_ZUNDA, TofuBlocks.ZUNDATOFU);
		stairs(TofuBlocks.TOFUSTAIR_HELL, TofuBlocks.HELLTOFU);
		stairs(TofuBlocks.TOFUSTAIR_SOUL, TofuBlocks.SOULTOFU);
		stairs(TofuBlocks.TOFUSTAIR_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		stairs(TofuBlocks.TOFUSTAIR_HELLBRICK, TofuBlocks.HELLTOFU_BRICK);
		stairs(TofuBlocks.TOFUSTAIR_SOULBRICK, TofuBlocks.SOULTOFU_BRICK);
		stairs(TofuBlocks.TOFUSTAIR_MISO, TofuBlocks.MISOTOFU);
		stairs(TofuBlocks.TOFUSTAIR_DRIED, TofuBlocks.DRIEDTOFU);

		slab(TofuBlocks.TOFUSLAB_KINU, TofuBlocks.KINUTOFU);
		slab(TofuBlocks.TOFUSLAB_MOMEN, TofuBlocks.MOMENTOFU);
		slab(TofuBlocks.TOFUSLAB_ISHI, TofuBlocks.ISHITOFU);
		slab(TofuBlocks.TOFUSLAB_METAL, TofuBlocks.METALTOFU);
		slab(TofuBlocks.TOFUSLAB_GRILLED, texture(name(TofuBlocks.MOMENTOFU)), texture(name(TofuBlocks.GRILLEDTOFU) + "_top"), texture(name(TofuBlocks.GRILLEDTOFU) + "_top"));
		slab(TofuBlocks.TOFUSLAB_ZUNDA, TofuBlocks.ZUNDATOFU);
		slab(TofuBlocks.TOFUSLAB_HELL, TofuBlocks.HELLTOFU);
		slab(TofuBlocks.TOFUSLAB_SOUL, TofuBlocks.SOULTOFU);
		slab(TofuBlocks.TOFUSLAB_ISHIBRICK, TofuBlocks.ISHITOFU_BRICK);
		slab(TofuBlocks.TOFUSLAB_HELLBRICK, TofuBlocks.HELLTOFU_BRICK);
		slab(TofuBlocks.TOFUSLAB_SOULBRICK, TofuBlocks.SOULTOFU_BRICK);
		slab(TofuBlocks.TOFUSLAB_MISO, TofuBlocks.MISOTOFU);
		slab(TofuBlocks.TOFUSLAB_DRIED, TofuBlocks.DRIEDTOFU);

		wall(TofuBlocks.TOFUFENCE_KINU, TofuBlocks.KINUTOFU);
		wall(TofuBlocks.TOFUFENCE_MOMEN, TofuBlocks.MOMENTOFU);
		wall(TofuBlocks.TOFUFENCE_ISHI, TofuBlocks.ISHITOFU);
		wall(TofuBlocks.TOFUFENCE_METAL, TofuBlocks.METALTOFU);
		wall(TofuBlocks.TOFUFENCE_HELL, TofuBlocks.HELLTOFU);
		wall(TofuBlocks.TOFUFENCE_SOUL, TofuBlocks.SOULTOFU);
		wallBlock(TofuBlocks.TOFUFENCE_GRILLED.get(), texture(name(TofuBlocks.GRILLEDTOFU) + "_top"));
		wall(TofuBlocks.TOFUFENCE_ZUNDA, TofuBlocks.ZUNDATOFU);

		ancientFormatDoor(TofuBlocks.TOFUDOOR_KINU, "kinu");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_MOMEN, "momen");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_ISHI, "ishi");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_METAL, "metal");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_HELL, "hell");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_SOUL, "soul");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_GRILLED, "grilled");
		ancientFormatDoor(TofuBlocks.TOFUDOOR_ZUNDA, "zunda");

		trapdoor(TofuBlocks.TOFUTRAPDOOR_KINU);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_MOMEN);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_ISHI);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_METAL);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_HELL);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_SOUL);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_GRILLED);
		trapdoor(TofuBlocks.TOFUTRAPDOOR_ZUNDA);

		torchBlock(TofuBlocks.TOFUTORCH_KINU, TofuBlocks.WALLTOFUTORCH_KINU);
		torchBlock(TofuBlocks.TOFUTORCH_MOMEN, TofuBlocks.WALLTOFUTORCH_MOMEN);
		torchBlock(TofuBlocks.TOFUTORCH_ISHI, TofuBlocks.WALLTOFUTORCH_ISHI);
		torchBlock(TofuBlocks.TOFUTORCH_METAL, TofuBlocks.WALLTOFUTORCH_METAL);
		torchBlock(TofuBlocks.TOFUTORCH_GRILLED, TofuBlocks.WALLTOFUTORCH_GRILLED);
		torchBlock(TofuBlocks.TOFUTORCH_ZUNDA, TofuBlocks.WALLTOFUTORCH_ZUNDA);
		torchBlock(TofuBlocks.TOFUTORCH_HELL, TofuBlocks.WALLTOFUTORCH_HELL);
		torchBlock(TofuBlocks.TOFUTORCH_SOUL, TofuBlocks.WALLTOFUTORCH_SOUL);
*/
		createTrivialCube(TofuBlocks.TOFU_TERRAIN.get());
		createGlowCube(TofuBlocks.MABOU_TERRAIN.get());
		createTrivialCube(TofuBlocks.TOFUSLATE.get());
		createTrivialCube(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get());
		createTrivialCube(TofuBlocks.ORE_TOFU_DIAMOND.get());
		createTrivialCube(TofuBlocks.ORE_TOFUGEM.get());
		createTrivialCube(TofuBlocks.TOFU_BEDROCK.get());

		logWithHorizontalGlow(TofuBlocks.TOFU_STEM.get());

		logWithHorizontal(TofuBlocks.LEEK_STEM.get());

		logWithHorizontal(TofuBlocks.LEEK_GREEN_STEM.get());

		this.createHangingSign(TofuBlocks.LEEK_HANGING_SIGN.get(), TofuBlocks.LEEK_HANGING_SIGN.get(), TofuBlocks.LEEK_WALL_HANGING_SIGN.get());
		this.createHangingSign(TofuBlocks.LEEK_GREEN_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.get());
		this.createHangingSign(TofuBlocks.TOFU_STEM_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.get());

		this.createCrossBlockWithDefaultItem(TofuBlocks.ZUNDATOFU_MUSHROOM.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		this.createTrivialCube(TofuBlocks.ZUNDA_MUSHROOM_BLOCK.get());
		this.createCrossBlockWithDefaultItem(TofuBlocks.SAPLING_TOFU.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		createTrivialBlock(TofuBlocks.LEAVES_TOFU.get(), LEAVES_PROVIDER);

		this.createCrossBlockWithDefaultItem(TofuBlocks.SAPLING_APRICOT.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		createTrivialBlock(TofuBlocks.LEAVES_APRICOT.get(), LEAVES_PROVIDER);

		this.createCrossBlockWithDefaultItem(TofuBlocks.TOFU_FLOWER.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		this.createCrossBlockWithDefaultItem(TofuBlocks.LEEK.get(), BlockModelGenerators.PlantType.NOT_TINTED);

		createTofuCakeBlock(TofuBlocks.TOFUCAKE.get());
		createTofuCakeBlock(TofuBlocks.ZUNDATOFUCAKE.get());
		createTofuCakeBlock(TofuBlocks.SOYCHEESE_TART.get());

		this.createTofuCandleCake(Blocks.WHITE_CANDLE, TofuBlocks.WHITE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.ORANGE_CANDLE, TofuBlocks.ORANGE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.MAGENTA_CANDLE, TofuBlocks.MAGENTA_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.LIGHT_BLUE_CANDLE, TofuBlocks.LIGHT_BLUE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.YELLOW_CANDLE, TofuBlocks.YELLOW_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.LIME_CANDLE, TofuBlocks.LIME_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.PINK_CANDLE, TofuBlocks.PINK_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.GRAY_CANDLE, TofuBlocks.GRAY_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.LIGHT_GRAY_CANDLE, TofuBlocks.LIGHT_GRAY_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.CYAN_CANDLE, TofuBlocks.CYAN_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.PURPLE_CANDLE, TofuBlocks.PURPLE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.BLUE_CANDLE, TofuBlocks.BLUE_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.BROWN_CANDLE, TofuBlocks.BROWN_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.GREEN_CANDLE, TofuBlocks.GREEN_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.RED_CANDLE, TofuBlocks.RED_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.BLACK_CANDLE, TofuBlocks.BLACK_TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());
		this.createTofuCandleCake(Blocks.CANDLE, TofuBlocks.TOFU_CANDLE_CAKE.get(), TofuBlocks.TOFUCAKE.get());

		this.createTofuCandleCake(Blocks.WHITE_CANDLE, TofuBlocks.WHITE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.ORANGE_CANDLE, TofuBlocks.ORANGE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.MAGENTA_CANDLE, TofuBlocks.MAGENTA_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.LIGHT_BLUE_CANDLE, TofuBlocks.LIGHT_BLUE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.YELLOW_CANDLE, TofuBlocks.YELLOW_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.LIME_CANDLE, TofuBlocks.LIME_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.PINK_CANDLE, TofuBlocks.PINK_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.GRAY_CANDLE, TofuBlocks.GRAY_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.LIGHT_GRAY_CANDLE, TofuBlocks.LIGHT_GRAY_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.CYAN_CANDLE, TofuBlocks.CYAN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.PURPLE_CANDLE, TofuBlocks.PURPLE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.BLUE_CANDLE, TofuBlocks.BLUE_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.BROWN_CANDLE, TofuBlocks.BROWN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.GREEN_CANDLE, TofuBlocks.GREEN_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.RED_CANDLE, TofuBlocks.RED_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.BLACK_CANDLE, TofuBlocks.BLACK_ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());
		this.createTofuCandleCake(Blocks.CANDLE, TofuBlocks.ZUNDA_TOFU_CANDLE_CAKE.get(), TofuBlocks.ZUNDATOFUCAKE.get());

		this.createTofuCandleCake(Blocks.WHITE_CANDLE, TofuBlocks.WHITE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.ORANGE_CANDLE, TofuBlocks.ORANGE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.MAGENTA_CANDLE, TofuBlocks.MAGENTA_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.LIGHT_BLUE_CANDLE, TofuBlocks.LIGHT_BLUE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.YELLOW_CANDLE, TofuBlocks.YELLOW_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.LIME_CANDLE, TofuBlocks.LIME_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.PINK_CANDLE, TofuBlocks.PINK_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.GRAY_CANDLE, TofuBlocks.GRAY_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.LIGHT_GRAY_CANDLE, TofuBlocks.LIGHT_GRAY_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.CYAN_CANDLE, TofuBlocks.CYAN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.PURPLE_CANDLE, TofuBlocks.PURPLE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.BLUE_CANDLE, TofuBlocks.BLUE_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.BROWN_CANDLE, TofuBlocks.BROWN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.GREEN_CANDLE, TofuBlocks.GREEN_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.RED_CANDLE, TofuBlocks.RED_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.BLACK_CANDLE, TofuBlocks.BLACK_SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());
		this.createTofuCandleCake(Blocks.CANDLE, TofuBlocks.SOYCHEESE_CANDLE_TART.get(), TofuBlocks.SOYCHEESE_TART.get());

		//createSingleCarpetBlocks(TofuBlocks.YUBA.get());
		createTrivialCube(TofuBlocks.SUSPICIOUS_TOFU_TERRAIN.get());

		this.registerSimpleFlatItemModel(TofuBlocks.TOFU_METAL_CHAIN.get());
		this.createAxisAlignedPillarBlock(TofuBlocks.TOFU_METAL_CHAIN.get(), CHAIN);
		createLantern(TofuBlocks.TOFU_METAL_LANTERN.get());
		createLantern(TofuBlocks.TOFU_METAL_SOUL_LANTERN.get());
		createTranslucentCube(TofuBlocks.ZUNDAMA_BLOCK.get());
		this.createCrossBlockWithDefaultItem(TofuBlocks.ANTENNA_BASIC.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		createTrivialCube(TofuBlocks.TF_COLLECTOR.get());
		createTrivialCube(TofuBlocks.SALT_BLOCK.get());

		this.createCropBlock(TofuBlocks.SOYBEAN.get(), BlockStateProperties.AGE_7, 0, 1, 1, 2, 2, 3, 3, 4);
		this.createCropBlock(TofuBlocks.SOYBEAN_NETHER.get(), BlockStateProperties.AGE_7, 0, 1, 1, 1, 2, 2, 2, 3);
		this.createCropBlock(TofuBlocks.SOYBEAN_SOUL.get(), BlockStateProperties.AGE_7, 0, 1, 1, 1, 2, 2, 2, 3);

		this.createCropBlock(TofuBlocks.CHILI_CROP.get(), BlockStateProperties.AGE_7, 0, 1, 1, 2, 2, 3, 3, 4);
		this.createCropBlock(TofuBlocks.RICE_CROP.get(), BlockStateProperties.AGE_7, 0, 1, 1, 2, 2, 3, 3, 4);
		this.createCropBlock(TofuBlocks.LEEK_CROP.get(), BlockStateProperties.AGE_3, 0, 1, 2, 3);
		this.createCropBlock(TofuBlocks.SPROUTS.get(), BlockStateProperties.AGE_3, 0, 1, 2, 3);
		this.createSingleCrop(TofuBlocks.RICE_ROOT.get());
		this.createTofuFarmland();
		this.createTofuPortalBlock();
		this.createTofunianState(TofuBlocks.TOFUNIAN_STATUE.get(), TofuBlocks.TOFUSLATE.get());
		this.createChest(TofuBlocks.TOFUCHEST.get(), TofuBlocks.ISHITOFU.get(), TofuCraftReload.prefix("tofuchest"), false);
		this.createTofuBed(TofuBlocks.TOFUBED.get(), TofuBlocks.KINUTOFU.get(), TofuCraftReload.prefix("tofubed"));
		this.itemModelOutput.accept(TofuBlocks.TOFU_FARMLAND.asItem(), ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(TofuBlocks.TOFU_FARMLAND.get())));
		this.itemModelOutput.accept(TofuBlocks.SALTPAN.asItem(), ItemModelUtils.plainModel(TofuCraftReload.prefix("block/saltpan_inventory")));
		this.itemModelOutput.accept(TofuBlocks.SPROUTSJAR.asItem(), ItemModelUtils.plainModel(TofuCraftReload.prefix("block/sprouts_jar_inventory")));
	}
}

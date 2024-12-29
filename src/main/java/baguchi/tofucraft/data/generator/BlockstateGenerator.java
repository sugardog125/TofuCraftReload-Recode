package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.CandleTofuCakeBlock;
import baguchi.tofucraft.data.provider.TofuBlockstateModelProvider;
import baguchi.tofucraft.data.resources.builder.TofuBlockFamilies;
import baguchi.tofucraft.registry.TofuBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class BlockstateGenerator extends TofuBlockstateModelProvider {
	private Supplier<CandleTofuCakeBlock> block;

	public BlockstateGenerator(PackOutput gen) {
		super(gen, TofuCraftReload.MODID);
	}

	@Override
	protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
		TofuBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateModel).forEach((family) -> family(blockModels, family.getBaseBlock()).generateFor(family));


		createTrivialCube(blockModels, TofuBlocks.ISHITOFU_SMOOTH_BRICK.get());
		createTrivialCube(blockModels, TofuBlocks.ISHITOFU_CHISELED_BRICK.get());
		createCutoutCube(blockModels, TofuBlocks.METAL_TOFU_GRATE.get());
		createTrivialCube(blockModels, TofuBlocks.METAL_TOFU_LUMP.get());
		createTrivialCube(blockModels, TofuBlocks.DIAMONDTOFU.get());
		createTrivialCube(blockModels, TofuBlocks.TOFU_GEM_BLOCK.get());
		createTrivialCube(blockModels, TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.get());

		logWithHorizontal(blockModels, TofuBlocks.GRILLEDTOFU.get());

		createTrivialCube(blockModels, TofuBlocks.ZUNDATOFU_SMOOTH_BRICK.get());

		family(blockModels, TofuBlocks.ZUNDATOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_ZUNDABRICK.get())
				.slab(TofuBlocks.TOFUSLAB_ZUNDABRICK.get());

		family(blockModels, TofuBlocks.EGGTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_EGG.get())
				.slab(TofuBlocks.TOFUSLAB_EGG.get());

		family(blockModels, TofuBlocks.SESAMETOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_SESAME.get())
				.slab(TofuBlocks.TOFUSLAB_SESAME.get());

		family(blockModels, TofuBlocks.EGGTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_EGGBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_EGGBRICK.get());

		createTrivialCube(blockModels, TofuBlocks.HELLTOFU_SMOOTH_BRICK.get());
		createTrivialCube(blockModels, TofuBlocks.SOULTOFU_SMOOTH_BRICK.get());
		createTrivialCube(blockModels, TofuBlocks.MINCEDTOFU.get());

		family(blockModels, TofuBlocks.HELLTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_HELLBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_HELLBRICK.get());

		family(blockModels, TofuBlocks.SOULTOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_SOULBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_SOULBRICK.get());

		family(blockModels, TofuBlocks.KINUTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_KINU.get())
				.slab(TofuBlocks.TOFUSLAB_KINU.get())
				.wall(TofuBlocks.TOFUFENCE_KINU.get())
				.door(TofuBlocks.TOFUDOOR_KINU.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_KINU.get());

		family(blockModels, TofuBlocks.MOMENTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_MOMEN.get())
				.slab(TofuBlocks.TOFUSLAB_MOMEN.get())
				.wall(TofuBlocks.TOFUFENCE_MOMEN.get())
				.door(TofuBlocks.TOFUDOOR_MOMEN.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_MOMEN.get());

		family(blockModels, TofuBlocks.ISHITOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_ISHI.get())
				.slab(TofuBlocks.TOFUSLAB_ISHI.get())
				.wall(TofuBlocks.TOFUFENCE_ISHI.get())
				.door(TofuBlocks.TOFUDOOR_ISHI.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_ISHI.get());

		family(blockModels, TofuBlocks.METALTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_METAL.get())
				.slab(TofuBlocks.TOFUSLAB_METAL.get())
				.wall(TofuBlocks.TOFUFENCE_METAL.get())
				.door(TofuBlocks.TOFUDOOR_METAL.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_METAL.get());

		family(blockModels, TofuBlocks.HELLTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_HELL.get())
				.slab(TofuBlocks.TOFUSLAB_HELL.get())
				.wall(TofuBlocks.TOFUFENCE_HELL.get())
				.door(TofuBlocks.TOFUDOOR_HELL.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_HELL.get());
		family(blockModels, TofuBlocks.SOULTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_SOUL.get())
				.slab(TofuBlocks.TOFUSLAB_SOUL.get())
				.wall(TofuBlocks.TOFUFENCE_SOUL.get())
				.door(TofuBlocks.TOFUDOOR_SOUL.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_SOUL.get());
		familyWithExistingFullBlock(blockModels, TofuBlocks.GRILLEDTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_GRILLED.get())
				.slab(TofuBlocks.TOFUSLAB_GRILLED.get())
				.wall(TofuBlocks.TOFUFENCE_GRILLED.get())
				.door(TofuBlocks.TOFUDOOR_GRILLED.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_GRILLED.get());

		family(blockModels, TofuBlocks.ZUNDATOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_ZUNDA.get())
				.slab(TofuBlocks.TOFUSLAB_ZUNDA.get())
				.wall(TofuBlocks.TOFUFENCE_ZUNDA.get())
				.door(TofuBlocks.TOFUDOOR_ZUNDA.get())
				.trapdoor(TofuBlocks.TOFUTRAPDOOR_ZUNDA.get());

		family(blockModels, TofuBlocks.ISHITOFU_BRICK.get())
				.stairs(TofuBlocks.TOFUSTAIR_ISHIBRICK.get())
				.slab(TofuBlocks.TOFUSLAB_ISHIBRICK.get());

		family(blockModels, TofuBlocks.MISOTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_MISO.get())
				.slab(TofuBlocks.TOFUSLAB_MISO.get());

		family(blockModels, TofuBlocks.DRIEDTOFU.get())
				.stairs(TofuBlocks.TOFUSTAIR_DRIED.get())
				.slab(TofuBlocks.TOFUSLAB_DRIED.get());

		blockModels.createNormalTorch(TofuBlocks.TOFUTORCH_KINU.get(), TofuBlocks.WALLTOFUTORCH_KINU.get());
		blockModels.createNormalTorch(TofuBlocks.TOFUTORCH_MOMEN.get(), TofuBlocks.WALLTOFUTORCH_MOMEN.get());
		blockModels.createNormalTorch(TofuBlocks.TOFUTORCH_ISHI.get(), TofuBlocks.WALLTOFUTORCH_ISHI.get());
		blockModels.createNormalTorch(TofuBlocks.TOFUTORCH_METAL.get(), TofuBlocks.WALLTOFUTORCH_METAL.get());
		blockModels.createNormalTorch(TofuBlocks.TOFUTORCH_GRILLED.get(), TofuBlocks.WALLTOFUTORCH_GRILLED.get());
		blockModels.createNormalTorch(TofuBlocks.TOFUTORCH_ZUNDA.get(), TofuBlocks.WALLTOFUTORCH_ZUNDA.get());
		blockModels.createNormalTorch(TofuBlocks.TOFUTORCH_HELL.get(), TofuBlocks.WALLTOFUTORCH_HELL.get());
		blockModels.createNormalTorch(TofuBlocks.TOFUTORCH_SOUL.get(), TofuBlocks.WALLTOFUTORCH_SOUL.get());


		blockModels.createNonTemplateHorizontalBlock(TofuBlocks.TOFULADDER_KINU.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFULADDER_KINU.get());
		blockModels.createNonTemplateHorizontalBlock(TofuBlocks.TOFULADDER_MOMEN.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFULADDER_MOMEN.get());
		blockModels.createNonTemplateHorizontalBlock(TofuBlocks.TOFULADDER_ISHI.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFULADDER_ISHI.get());
		blockModels.createNonTemplateHorizontalBlock(TofuBlocks.TOFULADDER_ISHIBRICK.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFULADDER_ISHIBRICK.get());
		blockModels.createNonTemplateHorizontalBlock(TofuBlocks.TOFULADDER_METAL.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFULADDER_METAL.get());
		blockModels.createNonTemplateHorizontalBlock(TofuBlocks.TOFULADDER_GRILLED.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFULADDER_GRILLED.get());
		blockModels.createNonTemplateHorizontalBlock(TofuBlocks.TOFULADDER_ZUNDA.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFULADDER_ZUNDA.get());
		blockModels.createNonTemplateHorizontalBlock(TofuBlocks.TOFULADDER_HELL.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFULADDER_HELL.get());
		blockModels.createNonTemplateHorizontalBlock(TofuBlocks.TOFULADDER_SOUL.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFULADDER_SOUL.get());
		/*blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_KINU.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_MOMEN.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_ISHI.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_METAL.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_GRILLED.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_ZUNDA.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_HELL.get());
		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFUTORCH_SOUL.get());
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
		createTrivialCube(blockModels, TofuBlocks.TOFU_TERRAIN.get());
		createGlowCube(blockModels, TofuBlocks.MABOU_TERRAIN.get());
		createTrivialCube(blockModels, TofuBlocks.TOFUSLATE.get());
		createTrivialCube(blockModels, TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get());
		createTrivialCube(blockModels, TofuBlocks.ORE_TOFU_DIAMOND.get());
		createTrivialCube(blockModels, TofuBlocks.ORE_TOFUGEM.get());
		createTrivialCube(blockModels, TofuBlocks.TOFU_BEDROCK.get());

		logWithHorizontal(blockModels, TofuBlocks.TOFU_STEM.get());

		logWithHorizontal(blockModels, TofuBlocks.LEEK_STEM.get());

		logWithHorizontal(blockModels, TofuBlocks.LEEK_GREEN_STEM.get());

		blockModels.createHangingSign(TofuBlocks.LEEK_HANGING_SIGN.get(), TofuBlocks.LEEK_HANGING_SIGN.get(), TofuBlocks.LEEK_WALL_HANGING_SIGN.get());
		blockModels.createHangingSign(TofuBlocks.LEEK_GREEN_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.get());
		blockModels.createHangingSign(TofuBlocks.TOFU_STEM_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.get());

		blockModels.createCrossBlockWithDefaultItem(TofuBlocks.ZUNDATOFU_MUSHROOM.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		blockModels.createCrossBlockWithDefaultItem(TofuBlocks.SAPLING_TOFU.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		createTrivialBlock(blockModels, TofuBlocks.LEAVES_TOFU.get(), LEAVES_PROVIDER);

		blockModels.createCrossBlockWithDefaultItem(TofuBlocks.SAPLING_APRICOT.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		createTrivialBlock(blockModels, TofuBlocks.LEAVES_APRICOT.get(), LEAVES_PROVIDER);

		blockModels.createCrossBlockWithDefaultItem(TofuBlocks.TOFU_FLOWER.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		blockModels.createCrossBlockWithDefaultItem(TofuBlocks.LEEK.get(), BlockModelGenerators.PlantType.NOT_TINTED);

		createTofuCakeBlock(blockModels, TofuBlocks.TOFUCAKE.get());
		createTofuCakeBlock(blockModels, TofuBlocks.ZUNDATOFUCAKE.get());
		createTofuCakeBlock(blockModels, TofuBlocks.SOYCHEESE_TART.get());

		CandleTofuCakeBlock.getCandleCakes().forEach((block -> createCandleCake(blockModels, ((CandleTofuCakeBlock) block).getCandle(), (CandleTofuCakeBlock) block)));
		//createSingleCarpetBlocks(blockModels, TofuBlocks.YUBA.get());
		createTrivialCube(blockModels, TofuBlocks.SUSPICIOUS_TOFU_TERRAIN.get());

		blockModels.registerSimpleFlatItemModel(TofuBlocks.TOFU_METAL_CHAIN.asItem());
		blockModels.createAxisAlignedPillarBlockCustomModel(TofuBlocks.TOFU_METAL_CHAIN.get(), ModelLocationUtils.getModelLocation(TofuBlocks.TOFU_METAL_CHAIN.get()));
		blockModels.createLantern(TofuBlocks.TOFU_METAL_LANTERN.get());
		blockModels.createLantern(TofuBlocks.TOFU_METAL_SOUL_LANTERN.get());
		createTranslucentCube(blockModels, TofuBlocks.ZUNDAMA_BLOCK.get());
		blockModels.createCrossBlockWithDefaultItem(TofuBlocks.ANTENNA_BASIC.get(), BlockModelGenerators.PlantType.NOT_TINTED);
		createTrivialCube(blockModels, TofuBlocks.TF_COLLECTOR.get());
		createTrivialCube(blockModels, TofuBlocks.SALT_BLOCK.get());

		blockModels.createCropBlock(TofuBlocks.SOYBEAN.get(), BlockStateProperties.AGE_7, 0, 1, 1, 2, 2, 3, 3, 4);
		blockModels.createCropBlock(TofuBlocks.SOYBEAN_NETHER.get(), BlockStateProperties.AGE_7, 0, 1, 1, 2, 2, 3, 3, 4);
		blockModels.createCropBlock(TofuBlocks.SOYBEAN_SOUL.get(), BlockStateProperties.AGE_7, 0, 1, 1, 2, 2, 3, 3, 4);

		blockModels.createCropBlock(TofuBlocks.CHILI_CROP.get(), BlockStateProperties.AGE_7, 0, 1, 1, 2, 2, 3, 3, 4);
		blockModels.createCropBlock(TofuBlocks.RICE_CROP.get(), BlockStateProperties.AGE_7, 0, 1, 1, 2, 2, 3, 3, 4);
		blockModels.createCropBlock(TofuBlocks.LEEK_CROP.get(), BlockStateProperties.AGE_3, 0, 1, 2, 3);
		blockModels.createCropBlock(TofuBlocks.SPROUTS.get(), BlockStateProperties.AGE_3, 0, 1, 2, 3);
		createSingleCrop(blockModels, TofuBlocks.RICE_ROOT.get());
		createTofuFarmland(blockModels);
		createTofuPortalBlock(blockModels);
		blockModels.itemModelOutput.accept(TofuBlocks.TOFU_FARMLAND.asItem(), ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(TofuBlocks.TOFU_FARMLAND.get())));
	}


	@Nonnull
	@Override
	public String getName() {
		return "TofuCraftReload blockstates and block models";
	}
}

package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.block.BagBlock;
import baguchi.tofucraft.block.BurnableRotatedPillarBlock;
import baguchi.tofucraft.block.CandleTofuCakeBlock;
import baguchi.tofucraft.block.DeferredStairBlock;
import baguchi.tofucraft.block.FallFoodBlock;
import baguchi.tofucraft.block.FoodPlateBlock;
import baguchi.tofucraft.block.KinuTofuBlock;
import baguchi.tofucraft.block.LeekBlock;
import baguchi.tofucraft.block.MorijioBlock;
import baguchi.tofucraft.block.RiceBlock;
import baguchi.tofucraft.block.SuspiciousTofuTerrainBlock;
import baguchi.tofucraft.block.TofuBlock;
import baguchi.tofucraft.block.TofuCakeBlock;
import baguchi.tofucraft.block.TofuDetectorBlock;
import baguchi.tofucraft.block.TofuFarmlandBlock;
import baguchi.tofucraft.block.TofuFlowerBlock;
import baguchi.tofucraft.block.TofuGemBlock;
import baguchi.tofucraft.block.TofuGrateBlock;
import baguchi.tofucraft.block.TofuGrilledBlock;
import baguchi.tofucraft.block.TofuLeavesBlock;
import baguchi.tofucraft.block.TofuMagmaBlock;
import baguchi.tofucraft.block.TofuMushroomBlock;
import baguchi.tofucraft.block.TofuPortalBlock;
import baguchi.tofucraft.block.TofuSaplingBlock;
import baguchi.tofucraft.block.TofuTerrainBlock;
import baguchi.tofucraft.block.TofuTrapDoorBlock;
import baguchi.tofucraft.block.TofunianStatueBlock;
import baguchi.tofucraft.block.YubaBlock;
import baguchi.tofucraft.block.ZundamaBlock;
import baguchi.tofucraft.block.crop.ChiliCropsBlock;
import baguchi.tofucraft.block.crop.LeekCropsBlock;
import baguchi.tofucraft.block.crop.RiceCropsBlock;
import baguchi.tofucraft.block.crop.RiceRootBlock;
import baguchi.tofucraft.block.crop.SoybeanCropsBlock;
import baguchi.tofucraft.block.crop.SoybeanNetherCropsBlock;
import baguchi.tofucraft.block.crop.SoybeanPaleCropsBlock;
import baguchi.tofucraft.block.crop.SoybeanSoulCropsBlock;
import baguchi.tofucraft.block.crop.SproutsCropBlock;
import baguchi.tofucraft.block.tfenergy.TFAntennaBlock;
import baguchi.tofucraft.block.tfenergy.TFCollectorBlock;
import baguchi.tofucraft.block.tfenergy.TFCrafterBlock;
import baguchi.tofucraft.block.tfenergy.TFOvenBlock;
import baguchi.tofucraft.block.tfenergy.TFStorageBlock;
import baguchi.tofucraft.block.tfenergy.TofuWorkStationBlock;
import baguchi.tofucraft.block.tree.ApricotLeavesBlock;
import baguchi.tofucraft.block.tree.ApricotSaplingBlock;
import baguchi.tofucraft.block.utils.ChikuwaBlock;
import baguchi.tofucraft.block.utils.MisoBarrelBlock;
import baguchi.tofucraft.block.utils.NoWeightBaseBlock;
import baguchi.tofucraft.block.utils.SaltFurnaceBlock;
import baguchi.tofucraft.block.utils.SaltPanBlock;
import baguchi.tofucraft.block.utils.SoymilkCauldronBlock;
import baguchi.tofucraft.block.utils.SproutsJarBlock;
import baguchi.tofucraft.block.utils.TofuBedBlock;
import baguchi.tofucraft.block.utils.TofuChestBlock;
import baguchi.tofucraft.block.utils.TofuDoorBlock;
import baguchi.tofucraft.block.utils.WeightBaseBlock;
import baguchi.tofucraft.item.block.EdiableBlockItem;
import baguchi.tofucraft.world.gen.grower.TofuTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Objects;
import java.util.function.Function;

public class TofuBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TofuCraftReload.MODID);

	public static final DeferredBlock<Block> SOYMILK = noItemRegisterWithEmpty("soymilk", (properties) -> new LiquidBlock(TofuFluids.SOYMILK.value(), properties.mapColor(MapColor.TERRACOTTA_WHITE).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
	public static final DeferredBlock<Block> SOYMILK_HELL = noItemRegisterWithEmpty("soymilk_hell", (properties) -> new LiquidBlock(TofuFluids.SOYMILK_HELL.value(), properties.mapColor(MapColor.COLOR_RED).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
	public static final DeferredBlock<Block> SOYMILK_SOUL = noItemRegisterWithEmpty("soymilk_soul", (properties) -> new LiquidBlock(TofuFluids.SOYMILK_SOUL.value(), properties.mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
	public static final DeferredBlock<Block> BITTERN = noItemRegisterWithEmpty("bittern", (properties) -> new LiquidBlock(TofuFluids.BITTERN.value(), properties.mapColor(MapColor.COLOR_BLUE).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().sound(SoundType.EMPTY)));
	public static final DeferredBlock<Block> DOUBANJIANG = noItemRegisterWithEmpty("doubanjiang", (properties) -> new LiquidBlock(TofuFluids.DOUBANJIANG.value(), properties.mapColor(MapColor.COLOR_RED).replaceable().noCollission().strength(100.0F).pushReaction(PushReaction.DESTROY).noLootTable().liquid().lightLevel((blockstate) -> 10).sound(SoundType.EMPTY)));

	public static final DeferredBlock<Block> YUBA = noItemRegisterWithEmpty("yuba", (properties) -> new YubaBlock(properties.noOcclusion().randomTicks().strength(0.25F).mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.WOOL)));


	public static final DeferredBlock<Block> SOYBEAN = noItemRegisterWithEmpty("soybean", (properties) -> new SoybeanCropsBlock(properties.noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final DeferredBlock<Block> SOYBEAN_NETHER = noItemRegisterWithEmpty("soybean_nether", (properties) -> new SoybeanNetherCropsBlock(properties.noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final DeferredBlock<Block> SOYBEAN_SOUL = noItemRegisterWithEmpty("soybean_soul", (properties) -> new SoybeanSoulCropsBlock(properties.noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final DeferredBlock<Block> SOYBEAN_PALE = noItemRegisterWithEmpty("soybean_pale", (properties) -> new SoybeanPaleCropsBlock(properties.noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final DeferredBlock<Block> LEEK_CROP = noItemRegisterWithEmpty("leek_crop", (properties) -> new LeekCropsBlock(properties.noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final DeferredBlock<Block> RICE_CROP = noItemRegisterWithEmpty("rice", (properties) -> new RiceCropsBlock(properties.noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final DeferredBlock<Block> RICE_ROOT = noItemRegisterWithEmpty("rice_root", (properties) -> new RiceRootBlock(properties.noCollission().randomTicks().strength(0.1F).sound(SoundType.CROP)));
	public static final DeferredBlock<Block> CHILI_CROP = noItemRegisterWithEmpty("chili_crop", (properties) -> new ChiliCropsBlock(properties.noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
	public static final DeferredBlock<Block> SPROUTS = noItemRegisterWithEmpty("sprouts_crop", (properties) -> new SproutsCropBlock(properties.noCollission().randomTicks().instabreak().sound(SoundType.CROP)));

	public static final DeferredBlock<Block> KINUTOFU = register("blocktofukinu", (properties) -> new KinuTofuBlock(properties.randomTicks().strength(0.1F, 0.2F).sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> MOMENTOFU = register("blocktofumomen", (properties) -> new TofuBlock(properties.randomTicks().strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> ISHITOFU = register("blocktofuishi", (properties) -> new TofuBlock(properties.randomTicks().requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> ISHITOFU_BRICK = register("tofuishi_brick", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> ISHITOFU_SMOOTH_BRICK = register("tofuishi_smooth_brick", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> ISHITOFU_CHISELED_BRICK = register("tofuishi_chiseled_brick", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> METALTOFU = register("blocktofumetal", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final DeferredBlock<Block> METAL_TOFU_GRATE = register("tofu_metal_grate", (properties) -> new TofuGrateBlock(properties.requiresCorrectToolForDrops().strength(3.0F, 6.0F).noOcclusion().sound(SoundType.COPPER_GRATE)));
	public static final DeferredBlock<Block> METAL_TOFU_LUMP = register("tofu_metal_lump", (properties) -> new TofuGrateBlock(properties.requiresCorrectToolForDrops().strength(5.0F, 6.0F).lightLevel(state -> {
		return 15;
	}).sound(SoundType.METAL)));
	public static final DeferredBlock<Block> DIAMONDTOFU = register("blocktofudiamond", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final DeferredBlock<Block> TOFU_GEM_BLOCK = register("tofu_gem_block", (properties) -> new TofuGemBlock(properties.requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(SoundType.METAL)));
	public static final DeferredBlock<Block> ADVANCE_TOFU_GEM_BLOCK = register("adv_tofu_gem_block", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(2.0F, 6.0F).sound(SoundType.METAL)));

	public static final DeferredBlock<RotatedPillarBlock> GRILLEDTOFU = register("blocktofugrilled", (properties) -> new TofuGrilledBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> ZUNDATOFU = register("blocktofuzunda", (properties) -> new Block(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> ZUNDATOFU_BRICK = register("tofuzunda_brick", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> ZUNDATOFU_SMOOTH_BRICK = register("tofuzunda_smooth_brick", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final DeferredBlock<Block> MISOTOFU = register("blocktofumiso", (properties) -> new Block(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> DRIEDTOFU = register("blocktofudried", (properties) -> new Block(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));

	public static final DeferredBlock<Block> EGGTOFU = register("blocktofuegg", (properties) -> new Block(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> EGGTOFU_BRICK = register("tofuegg_brick", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final DeferredBlock<Block> SESAMETOFU = register("blocktofusesame", (properties) -> new Block(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));

	public static final DeferredBlock<Block> HELLTOFU = register("blocktofuhell", (properties) -> new Block(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> HELLTOFU_BRICK = register("tofuhell_brick", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> HELLTOFU_SMOOTH_BRICK = register("tofuhell_smooth_brick", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));

	public static final DeferredBlock<Block> SOULTOFU = register("blocktofusoul", (properties) -> new TofuBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> SOULTOFU_BRICK = register("tofusoul_brick", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> SOULTOFU_SMOOTH_BRICK = register("tofusoul_smooth_brick", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> MINCEDTOFU = register("blocktofuminced", (properties) -> new FallFoodBlock(properties.strength(0.2F, 0.3F).sound(SoundType.SNOW)));

	public static final DeferredBlock<StairBlock> TOFUSTAIR_KINU = register("tofustair_kinu", (properties) -> new DeferredStairBlock(KINUTOFU, properties.strength(0.1F, 0.2F)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_MOMEN = register("tofustair_momen", (properties) -> new DeferredStairBlock(MOMENTOFU, properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_ISHI = register("tofustair_ishi", (properties) -> new DeferredStairBlock(ISHITOFU, properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_METAL = register("tofustair_metal", (properties) -> new DeferredStairBlock(METALTOFU, properties.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_GRILLED = register("tofustair_grilled", (properties) -> new DeferredStairBlock(GRILLEDTOFU, properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));

	public static final DeferredBlock<StairBlock> TOFUSTAIR_ZUNDA = register("tofustair_zunda", (properties) -> new DeferredStairBlock(ZUNDATOFU, properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_ZUNDABRICK = register("tofustair_zundabrick", (properties) -> new DeferredStairBlock(ZUNDATOFU_BRICK, properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_HELL = register("tofustair_hell", (properties) -> new DeferredStairBlock(HELLTOFU, properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_SOUL = register("tofustair_soul", (properties) -> new DeferredStairBlock(SOULTOFU, properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));

	public static final DeferredBlock<StairBlock> TOFUSTAIR_ISHIBRICK = register("tofustair_ishibrick", (properties) -> new DeferredStairBlock(ISHITOFU_BRICK, properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_HELLBRICK = register("tofustair_hellbrick", (properties) -> new DeferredStairBlock(HELLTOFU_BRICK, properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_SOULBRICK = register("tofustair_soulbrick", (properties) -> new DeferredStairBlock(SOULTOFU_BRICK, properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_MISO = register("tofustair_miso", (properties) -> new DeferredStairBlock(MISOTOFU, properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_DRIED = register("tofustair_dried", (properties) -> new DeferredStairBlock(DRIEDTOFU, properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_EGG = register("tofustair_egg", (properties) -> new DeferredStairBlock(EGGTOFU, properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_EGGBRICK = register("tofustair_eggbrick", (properties) -> new DeferredStairBlock(EGGTOFU_BRICK, properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<StairBlock> TOFUSTAIR_SESAME = register("tofustair_sesame", (properties) -> new DeferredStairBlock(SESAMETOFU, properties.strength(0.35F, 0.5F).sound(SoundType.SNOW)));

	public static final DeferredBlock<SlabBlock> TOFUSLAB_KINU = register("tofuslab_kinu", (properties) -> new SlabBlock(properties.strength(0.1F, 0.2F).sound(SoundType.SNOW)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_MOMEN = register("tofuslab_momen", (properties) -> new SlabBlock(properties.strength(0.35F, 0.5F)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_ISHI = register("tofuslab_ishi", (properties) -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_METAL = register("tofuslab_metal", (properties) -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_GRILLED = register("tofuslab_grilled", (properties) -> new SlabBlock(properties.strength(0.35F, 0.5F)));

	public static final DeferredBlock<SlabBlock> TOFUSLAB_ZUNDA = register("tofuslab_zunda", (properties) -> new SlabBlock(properties.strength(0.35F, 0.5F)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_ZUNDABRICK = register("tofuslab_zundabrick", (properties) -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_HELL = register("tofuslab_hell", (properties) -> new SlabBlock(properties.strength(0.35F, 0.5F)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_SOUL = register("tofuslab_soul", (properties) -> new SlabBlock(properties.strength(0.35F, 0.5F)));

	public static final DeferredBlock<SlabBlock> TOFUSLAB_ISHIBRICK = register("tofuslab_ishibrick", (properties) -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_HELLBRICK = register("tofuslab_hellbrick", (properties) -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_SOULBRICK = register("tofuslab_soulbrick", (properties) -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_MISO = register("tofuslab_miso", (properties) -> new SlabBlock(properties.strength(0.35F, 0.5F)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_DRIED = register("tofuslab_dried", (properties) -> new SlabBlock(properties.strength(0.35F, 0.5F)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_EGG = register("tofuslab_egg", (properties) -> new SlabBlock(properties.strength(0.35F, 0.5F)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_EGGBRICK = register("tofuslab_eggbrick", (properties) -> new SlabBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F)));
	public static final DeferredBlock<SlabBlock> TOFUSLAB_SESAME = register("tofuslab_sesame", (properties) -> new SlabBlock(properties.strength(0.35F, 0.5F)));


	public static final DeferredBlock<Block> TOFUTORCH_KINU = register("tofutorch_kinu", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> TOFUTORCH_MOMEN = register("tofutorch_momen", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> TOFUTORCH_ISHI = register("tofutorch_ishi", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 6.0F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.STONE)));
	public static final DeferredBlock<Block> TOFUTORCH_METAL = register("tofutorch_metal", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 7.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.METAL)));
	public static final DeferredBlock<Block> TOFUTORCH_GRILLED = register("tofutorch_grilled", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noOcclusion().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> TOFUTORCH_ZUNDA = register("tofutorch_zunda", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noOcclusion().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> TOFUTORCH_HELL = register("tofutorch_hell", (properties) -> new TorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noOcclusion().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> TOFUTORCH_SOUL = register("tofutorch_soul", (properties) -> new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, properties.strength(0.0F, 0.5F).noCollission().lightLevel(state -> 10)
			.noOcclusion().sound(SoundType.SNOW)));

	public static final DeferredBlock<Block> WALLTOFUTORCH_KINU = noItemRegisterWithEmpty("walltofutorch_kinu", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> WALLTOFUTORCH_MOMEN = noItemRegisterWithEmpty("walltofutorch_momen", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> WALLTOFUTORCH_ISHI = noItemRegisterWithEmpty("walltofutorch_ishi", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 6.0F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.STONE)));
	public static final DeferredBlock<Block> WALLTOFUTORCH_METAL = noItemRegisterWithEmpty("walltofutorch_metal", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 7.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.METAL)));
	public static final DeferredBlock<Block> WALLTOFUTORCH_GRILLED = noItemRegisterWithEmpty("walltofutorch_grilled", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> WALLTOFUTORCH_ZUNDA = noItemRegisterWithEmpty("walltofutorch_zunda", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> WALLTOFUTORCH_HELL = noItemRegisterWithEmpty("walltofutorch_hell", (properties) -> new WallTorchBlock(ParticleTypes.FLAME, properties.strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> WALLTOFUTORCH_SOUL = noItemRegisterWithEmpty("walltofutorch_soul", (properties) -> new WallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, properties.strength(0.0F, 0.5F).noCollission().lightLevel(state -> 14)
			.noCollission().sound(SoundType.SNOW)));

	public static final DeferredBlock<Block> TOFU_METAL_CHAIN = register("tofu_metal_chain", (properties) -> new ChainBlock(properties.strength(5.0F, 6.0F).noOcclusion().sound(SoundType.CHAIN)));
	public static final DeferredBlock<Block> TOFU_METAL_LANTERN = register("tofu_metal_lantern", (properties) -> new LanternBlock(properties.strength(5.0F, 6.0F).noOcclusion().sound(SoundType.LANTERN).lightLevel(state -> 15)
			.sound(SoundType.LANTERN)));
	public static final DeferredBlock<Block> TOFU_METAL_SOUL_LANTERN = register("tofu_metal_soul_lantern", (properties) -> new LanternBlock(properties.strength(5.0F, 6.0F).noOcclusion().sound(SoundType.LANTERN).lightLevel(state -> 10)
			.sound(SoundType.LANTERN)));

	public static final DeferredBlock<Block> TOFULADDER_KINU = register("tofuladder_kinu", (properties) -> new LadderBlock(properties.strength(0.1F, 0.2F).noOcclusion()));
	public static final DeferredBlock<Block> TOFULADDER_MOMEN = register("tofuladder_momen", (properties) -> new LadderBlock(properties.strength(0.3F, 0.5F).noOcclusion()));
	public static final DeferredBlock<Block> TOFULADDER_ISHI = register("tofuladder_ishi", (properties) -> new LadderBlock(properties.strength(1.5F, 6.0F).noOcclusion()));
	public static final DeferredBlock<Block> TOFULADDER_METAL = register("tofuladder_metal", (properties) -> new LadderBlock(properties.strength(5.0F, 6.0F).noOcclusion()));

	public static final DeferredBlock<Block> TOFULADDER_GRILLED = register("tofuladder_grilled", (properties) -> new LadderBlock(properties.strength(0.3F, 0.5F).noOcclusion()));
	public static final DeferredBlock<Block> TOFULADDER_ZUNDA = register("tofuladder_zunda", (properties) -> new LadderBlock(properties.strength(0.3F, 0.5F).noOcclusion()));
	public static final DeferredBlock<Block> TOFULADDER_HELL = register("tofuladder_hell", (properties) -> new LadderBlock(properties.strength(0.3F, 0.5F).noOcclusion()));
	public static final DeferredBlock<Block> TOFULADDER_SOUL = register("tofuladder_soul", (properties) -> new LadderBlock(properties.strength(0.3F, 0.5F).noOcclusion()));

	public static final DeferredBlock<Block> TOFULADDER_ISHIBRICK = register("tofuladder_ishibrick", (properties) -> new LadderBlock(properties.strength(1.5F, 6.0F).noOcclusion()));
	public static final DeferredBlock<WallBlock> TOFUFENCE_KINU = register("tofufence_kinu", (properties) -> new WallBlock(properties.strength(0.1F, 0.2F).sound(SoundType.SNOW).noOcclusion()));
	public static final DeferredBlock<WallBlock> TOFUFENCE_MOMEN = register("tofufence_momen", (properties) -> new WallBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion()));
	public static final DeferredBlock<WallBlock> TOFUFENCE_ISHI = register("tofufence_ishi", (properties) -> new WallBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion()));
	public static final DeferredBlock<WallBlock> TOFUFENCE_METAL = register("tofufence_metal", (properties) -> new WallBlock(properties.strength(5.0F, 6.0F).noOcclusion().requiresCorrectToolForDrops()));
	public static final DeferredBlock<WallBlock> TOFUFENCE_HELL = register("tofufence_hell", (properties) -> new WallBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion()));
	public static final DeferredBlock<WallBlock> TOFUFENCE_SOUL = register("tofufence_soul", (properties) -> new WallBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion()));
	public static final DeferredBlock<WallBlock> TOFUFENCE_GRILLED = register("tofufence_grilled", (properties) -> new WallBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion()));
	public static final DeferredBlock<WallBlock> TOFUFENCE_ZUNDA = register("tofufence_zunda", (properties) -> new WallBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion()));


	public static final DeferredBlock<DoorBlock> TOFUDOOR_KINU = register("tofudoor_kinu", (properties) -> new TofuDoorBlock(properties.noOcclusion().strength(0.2F, 0.4F).sound(SoundType.SNOW).noOcclusion(), BlockSetType.OAK));
	public static final DeferredBlock<DoorBlock> TOFUDOOR_MOMEN = register("tofudoor_momen", (properties) -> new TofuDoorBlock(properties.noOcclusion().strength(0.5F, 1.0F).sound(SoundType.SNOW).noOcclusion(), BlockSetType.OAK));
	public static final DeferredBlock<DoorBlock> TOFUDOOR_ISHI = register("tofudoor_ishi", (properties) -> new TofuDoorBlock(properties.noOcclusion().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion(), BlockSetType.OAK));
	public static final DeferredBlock<DoorBlock> TOFUDOOR_METAL = register("tofudoor_metal", (properties) -> new TofuDoorBlock(properties.strength(5.0F, 6.0F).noOcclusion(), BlockSetType.IRON));
	public static final DeferredBlock<DoorBlock> TOFUDOOR_HELL = register("tofudoor_hell", (properties) -> new TofuDoorBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion(), BlockSetType.OAK));
	public static final DeferredBlock<DoorBlock> TOFUDOOR_SOUL = register("tofudoor_soul", (properties) -> new TofuDoorBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion(), BlockSetType.OAK));
	public static final DeferredBlock<DoorBlock> TOFUDOOR_GRILLED = register("tofudoor_grilled", (properties) -> new TofuDoorBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion(), BlockSetType.OAK));
	public static final DeferredBlock<DoorBlock> TOFUDOOR_ZUNDA = register("tofudoor_zunda", (properties) -> new TofuDoorBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion(), BlockSetType.OAK));

	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_KINU = register("tofutrapdoor_kinu", (properties) -> new TofuTrapDoorBlock(properties.strength(0.2F, 0.4F).sound(SoundType.SNOW).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_MOMEN = register("tofutrapdoor_momen", (properties) -> new TofuTrapDoorBlock(properties.strength(0.5F, 1.0F).sound(SoundType.SNOW).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_ISHI = register("tofutrapdoor_ishi", (properties) -> new TofuTrapDoorBlock(properties.requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.STONE).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_METAL = register("tofutrapdoor_metal", (properties) -> new TofuTrapDoorBlock(properties.noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false).sound(SoundType.METAL), BlockSetType.IRON));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_HELL = register("tofutrapdoor_hell", (properties) -> new TofuTrapDoorBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_SOUL = register("tofutrapdoor_soul", (properties) -> new TofuTrapDoorBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_GRILLED = register("tofutrapdoor_grilled", (properties) -> new TofuTrapDoorBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));
	public static final DeferredBlock<TrapDoorBlock> TOFUTRAPDOOR_ZUNDA = register("tofutrapdoor_zunda", (properties) -> new TofuTrapDoorBlock(properties.strength(0.35F, 0.5F).sound(SoundType.SNOW).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false), BlockSetType.OAK));

	public static final DeferredBlock<Block> TOFU_TERRAIN = register("tofu_terrain", (properties) -> new TofuTerrainBlock(properties.strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> MABOU_TERRAIN = register("mabou_terrain", (properties) -> new TofuMagmaBlock(properties.mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM)
			.lightLevel(p_152684_ -> 3)
			.strength(0.6F)
			.isValidSpawn((p_187421_, p_187422_, p_187423_, p_187424_) -> p_187424_.fireImmune())
			.hasPostProcess(TofuBlocks::always)
			.sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> TOFU_TERRAIN_ZUNDA = register("tofu_terrain_zunda", (properties) -> new TofuTerrainBlock(properties.strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).randomTicks().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> SUSPICIOUS_TOFU_TERRAIN = register("suspicious_tofu_terrain", (properties) -> new SuspiciousTofuTerrainBlock(properties.strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_WHITE).sound(SoundType.SNOW)));

	public static final DeferredBlock<Block> TOFUSLATE = register("tofuslate", (properties) -> new Block(properties.requiresCorrectToolForDrops().strength(2.5F, 4.0F).sound(SoundType.DEEPSLATE)));
	public static final DeferredBlock<Block> TOFUSLATE_TOFU_DIAMOND_ORE = register("tofuslate_tofu_diamond_ore", (properties) -> new DropExperienceBlock(UniformInt.of(3, 5), properties.requiresCorrectToolForDrops().strength(3.5F, 4.0F).sound(SoundType.DEEPSLATE)));
	public static final DeferredBlock<Block> ORE_TOFU_DIAMOND = register("ore_tofu_diamond", (properties) -> new DropExperienceBlock(UniformInt.of(3, 5), properties.strength(0.5F, 1.0F).sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> ORE_TOFUGEM = register("ore_tofugem", (properties) -> new DropExperienceBlock(UniformInt.of(2, 3), properties.strength(0.5F, 1.0F).sound(SoundType.SNOW)));

	public static final DeferredBlock<Block> TOFU_BEDROCK = register("tofu_bedrock", (properties) -> new Block(properties.strength(-1.0F, 3600000.0F).sound(SoundType.STONE).isValidSpawn((state, blockGetter, blockPos, entityType) -> false)));

	public static final DeferredBlock<Block> SAPLING_TOFU = register("sapling_tofu", (properties) -> new TofuSaplingBlock(TofuTreeGrowers.TOFU_TREE, properties.noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> LEAVES_TOFU = register("leaves_tofu", (properties) -> new TofuLeavesBlock(properties.strength(0.2F).noOcclusion().randomTicks().isSuffocating((state, getter, pos) -> false).sound(SoundType.GRASS)));

	public static final DeferredBlock<Block> TOFU_FLOWER = register("tofu_flower", (properties) -> new TofuFlowerBlock(TofuEffects.SOY_HEALTHY, 20.0F, properties.instabreak().noOcclusion().noCollission().sound(SoundType.GRASS)));

	public static final DeferredBlock<Block> LEEK = register("blockleek", (properties) -> new LeekBlock(properties.instabreak().noOcclusion().noCollission().sound(SoundType.GRASS)));

	public static final DeferredBlock<Block> SAPLING_APRICOT = register("sapling_apricot", (properties) -> new ApricotSaplingBlock(TofuTreeGrowers.APRICOT_TREE, properties.instabreak()));
	public static final DeferredBlock<Block> LEAVES_APRICOT = register("leaves_apricot", (properties) -> new ApricotLeavesBlock(properties.strength(0.2F).noOcclusion().isSuffocating((state, getter, pos) -> false).randomTicks().sound(SoundType.GRASS)));

	public static final DeferredBlock<RotatedPillarBlock> LEEK_GREEN_STEM = register("leek_green_stem", (properties) -> new BurnableRotatedPillarBlock(properties.strength(2.0F, 3.0F).sound(SoundType.STEM)));

	public static final DeferredBlock<Block> LEEK_GREEN_PLANKS = register("leek_green_planks", (properties) -> new Block(properties.strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<StairBlock> LEEK_GREEN_PLANKS_STAIR = register("leek_green_planks_stair", (properties) -> new DeferredStairBlock(LEEK_GREEN_PLANKS, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<SlabBlock> LEEK_GREEN_PLANKS_SLAB = register("leek_green_planks_slab", (properties) -> new SlabBlock(properties.strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<FenceBlock> LEEK_GREEN_FENCE = register("leek_green_fence", (properties) -> new FenceBlock(properties.strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<FenceGateBlock> LEEK_GREEN_FENCE_GATE = register("leek_green_fence_gate", (properties) -> new FenceGateBlock(TofuWoodTypes.LEEK_GREEN, properties.strength(2.0F, 3.0F)));
	public static final DeferredBlock<StandingSignBlock> LEEK_GREEN_SIGN = register("leek_green_sign", (properties) -> new StandingSignBlock(TofuWoodTypes.LEEK_GREEN, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<WallSignBlock> LEEK_GREEN_WALL_SIGN = noItemRegister("leek_green_wall_sign", (properties) -> new WallSignBlock(TofuWoodTypes.LEEK_GREEN, properties.sound(SoundType.NETHER_WOOD)), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_SIGN));
	public static final DeferredBlock<CeilingHangingSignBlock> LEEK_GREEN_HANGING_SIGN = register("leek_green_hanging_sign", (properties) -> new CeilingHangingSignBlock(TofuWoodTypes.LEEK_GREEN, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<WallHangingSignBlock> LEEK_GREEN_WALL_HANGING_SIGN = noItemRegister("leek_green_wall_hanging_sign", (properties) -> new WallHangingSignBlock(TofuWoodTypes.LEEK_GREEN, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN));
	public static final DeferredBlock<DoorBlock> LEEK_GREEN_DOOR = register("leek_green_door", (properties) -> new DoorBlock(TofuBlockSetTypes.LEEK_GREEN, properties.strength(2.0F, 3.0F).noOcclusion().strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<TrapDoorBlock> LEEK_GREEN_TRAPDOOR = register("leek_green_trapdoor", (properties) -> new TrapDoorBlock(TofuBlockSetTypes.LEEK_GREEN, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD).noOcclusion().isValidSpawn((state, blockGetter, blockPos, entityType) -> false)));
	public static final DeferredBlock<ButtonBlock> LEEK_GREEN_BUTTON = register("leek_green_button", (properties) -> new ButtonBlock(TofuBlockSetTypes.LEEK_GREEN, 30, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<PressurePlateBlock> LEEK_GREEN_PRESSURE_PLATE = register("leek_green_pressure_plate", (properties) -> new PressurePlateBlock(TofuBlockSetTypes.LEEK_GREEN, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));


	public static final DeferredBlock<RotatedPillarBlock> LEEK_STEM = register("leek_stem", (properties) -> new BurnableRotatedPillarBlock(properties.strength(2.0F, 3.0F).sound(SoundType.STEM)));

	public static final DeferredBlock<Block> LEEK_PLANKS = register("leek_planks", (properties) -> new Block(properties.strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<StairBlock> LEEK_PLANKS_STAIR = register("leek_planks_stair", (properties) -> new DeferredStairBlock(LEEK_PLANKS, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<SlabBlock> LEEK_PLANKS_SLAB = register("leek_planks_slab", (properties) -> new SlabBlock(properties.strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<FenceBlock> LEEK_FENCE = register("leek_fence", (properties) -> new FenceBlock(properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<FenceGateBlock> LEEK_FENCE_GATE = register("leek_fence_gate", (properties) -> new FenceGateBlock(TofuWoodTypes.LEEK, properties.strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<StandingSignBlock> LEEK_SIGN = register("leek_sign", (properties) -> new StandingSignBlock(TofuWoodTypes.LEEK, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<WallSignBlock> LEEK_WALL_SIGN = noItemRegister("leek_wall_sign", (properties) -> new WallSignBlock(TofuWoodTypes.LEEK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_SIGN));
	public static final DeferredBlock<CeilingHangingSignBlock> LEEK_HANGING_SIGN = register("leek_hanging_sign", (properties) -> new CeilingHangingSignBlock(TofuWoodTypes.LEEK, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<WallHangingSignBlock> LEEK_WALL_HANGING_SIGN = noItemRegister("leek_wall_hanging_sign", (properties) -> new WallHangingSignBlock(TofuWoodTypes.LEEK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN));
	public static final DeferredBlock<ButtonBlock> LEEK_BUTTON = register("leek_button", (properties) -> new ButtonBlock(TofuBlockSetTypes.LEEK, 30, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<PressurePlateBlock> LEEK_PRESSURE_PLATE = register("leek_pressure_plate", (properties) -> new PressurePlateBlock(TofuBlockSetTypes.LEEK_GREEN, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));


	public static final DeferredBlock<Block> ZUNDATOFU_MUSHROOM = register("zundatofu_mushroom", (properties) -> new TofuMushroomBlock(TofuTreeGrowers.ZUNDA_MUSHROOM, properties.instabreak().noCollission().sound(SoundType.FUNGUS)));


	public static final DeferredBlock<RotatedPillarBlock> TOFU_STEM = register("tofustem", (properties) -> new BurnableRotatedPillarBlock(properties.strength(2.0F, 3.0F).sound(SoundType.STEM)));
	public static final DeferredBlock<Block> TOFU_STEM_PLANKS = register("tofustem_planks", (properties) -> new Block(properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<StairBlock> TOFU_STEM_PLANKS_STAIR = register("tofustem_planks_stair", (properties) -> new DeferredStairBlock(TOFU_STEM_PLANKS, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<SlabBlock> TOFU_STEM_PLANKS_SLAB = register("tofustem_planks_slab", (properties) -> new SlabBlock(properties.strength(2.0F, 3.0F).sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<FenceBlock> TOFU_STEM_FENCE = register("tofustem_fence", (properties) -> new FenceBlock(properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<FenceGateBlock> TOFU_STEM_FENCE_GATE = register("tofustem_fence_gate", (properties) -> new FenceGateBlock(TofuWoodTypes.TOFU_STEM, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));


	public static final DeferredBlock<StandingSignBlock> TOFU_STEM_SIGN = register("tofustem_sign", (properties) -> new StandingSignBlock(TofuWoodTypes.TOFU_STEM, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<WallSignBlock> TOFU_STEM_WALL_SIGN = noItemRegister("tofustem_wall_sign", (properties) -> new WallSignBlock(TofuWoodTypes.TOFU_STEM, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_SIGN));
	public static final DeferredBlock<CeilingHangingSignBlock> TOFU_STEM_HANGING_SIGN = register("tofustem_hanging_sign", (properties) -> new CeilingHangingSignBlock(TofuWoodTypes.TOFU_STEM, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<WallHangingSignBlock> TOFU_STEM_WALL_HANGING_SIGN = noItemRegister("tofustem_wall_hanging_sign", (properties) -> new WallHangingSignBlock(TofuWoodTypes.TOFU_STEM, properties.strength(2.0F, 3.0F).noOcclusion()), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_WALL_HANGING_SIGN));
	public static final DeferredBlock<DoorBlock> TOFU_STEM_DOOR = register("tofustem_door", (properties) -> new DoorBlock(TofuBlockSetTypes.TOFU_STEM, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<TrapDoorBlock> TOFU_STEM_TRAPDOOR = register("tofustem_trapdoor", (properties) -> new TrapDoorBlock(TofuBlockSetTypes.TOFU_STEM, properties.strength(2.0F, 3.0F).noOcclusion().noOcclusion().sound(SoundType.NETHER_WOOD).isValidSpawn((state, blockGetter, blockPos, entityType) -> false)));
	public static final DeferredBlock<ButtonBlock> TOFU_STEM_BUTTON = register("tofustem_button", (properties) -> new ButtonBlock(TofuBlockSetTypes.TOFU_STEM, 30, properties.strength(2.0F, 3.0F).noOcclusion().sound(SoundType.NETHER_WOOD)));
	public static final DeferredBlock<PressurePlateBlock> TOFU_STEM_PRESSURE_PLATE = register("tofustem_pressure_plate", (properties) -> new PressurePlateBlock(TofuBlockSetTypes.TOFU_STEM, properties.strength(2.0F, 3.0F).noOcclusion()));

	public static final DeferredBlock<TofuPortalBlock> TOFU_PORTAL = noItemRegisterWithEmpty("tofuportal", (properties) -> new TofuPortalBlock(properties.strength(-1.0F).noCollission().noLootTable().sound(SoundType.GLASS).requiredFeatures(TofuCraftReload.EXPERIMENTAL).lightLevel((p_50872_) -> {
		return 11;
	})));
	public static final DeferredBlock<Block> TOFU_FARMLAND = register("tofu_farmland", (properties) -> new TofuFarmlandBlock(properties.strength(0.5F, 1.0F).noOcclusion().randomTicks().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> SALTPAN = register("blocksaltpan", (properties) -> new SaltPanBlock(properties.strength(2.0F, 3.0F).randomTicks().noOcclusion().sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> SALT_FURNACE = register("salt_furnace", (properties) -> new SaltFurnaceBlock(properties.strength(2.5F).sound(SoundType.STONE).lightLevel((p_50872_) -> {
		return p_50872_.getValue(SaltFurnaceBlock.LIT) ? 13 : 0;
	})));
	public static final DeferredBlock<Block> SPROUTSJAR = register("blocksproutsjar", (properties) -> new SproutsJarBlock(properties.strength(2.0F, 3.0F).randomTicks().noCollission().sound(SoundType.GLASS)));
	public static final DeferredBlock<Block> SALT_BLOCK = register("salt_block", (properties) -> new FallFoodBlock(properties.strength(0.5F).sound(SoundType.SAND)));

	public static final DeferredBlock<Block> MORIJIO = register("morijio", (properties) -> new MorijioBlock(properties.strength(0.5F, 3.0F).noOcclusion().sound(SoundType.WOOD)));
	//BARREL
	public static final DeferredBlock<Block> BARREL_MISO = register("barrel_miso", (properties) -> new MisoBarrelBlock(TofuItems.BOTTLE_SOYSAUSE, properties.strength(2.0F, 3.0F).randomTicks().sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> BARREL_MISOTOFU = register("barrel_misotofu", (properties) -> new WeightBaseBlock(properties.strength(2.0F, 3.0F).randomTicks().sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> BARREL_ADV_TOFUGEM = register("barrel_adv_tofugem", (properties) -> new WeightBaseBlock(properties.strength(2.0F, 3.0F).randomTicks().sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> NATTOBED = register("nattobed", (properties) -> new NoWeightBaseBlock(properties.strength(1.0F, 2.0F).randomTicks().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> NETHER_NATTOBED = register("nether_nattobed", (properties) -> new NoWeightBaseBlock(properties.strength(1.0F, 2.0F).randomTicks().sound(SoundType.GRASS)));

	public static final DeferredBlock<Block> TOFU_CHIKUWA_BLOCK = register("tofu_chikuwa_block", (properties) -> new ChikuwaBlock(properties.strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_WHITE).isValidSpawn(Blocks::never).noOcclusion().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> CHIKUWA_BLOCK = register("chikuwa_block", (properties) -> new ChikuwaBlock(properties.strength(0.4F, 0.5F).mapColor(MapColor.TERRACOTTA_WHITE).isValidSpawn(Blocks::never).noOcclusion().sound(SoundType.SNOW)));


	public static final DeferredBlock<Block> TOFUCAKE = register("tofucake", (properties) -> new TofuCakeBlock(properties.strength(0.5F).noOcclusion().sound(SoundType.WOOL), 1, 0.1F));
	public static final DeferredBlock<Block> ZUNDATOFUCAKE = register("zundatofucake", (properties) -> new TofuCakeBlock(properties.strength(0.5F).noOcclusion().sound(SoundType.WOOL), 1, 0.2F));
	public static final DeferredBlock<Block> SOYCHEESE_TART = register("soycheese_tart", (properties) -> new TofuCakeBlock(properties.strength(0.5F).noOcclusion().sound(SoundType.WOOL), 2, 0.3F));

	public static final DeferredBlock<Block> TOFU_CANDLE_CAKE = noItemRegister("candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> WHITE_TOFU_CANDLE_CAKE = noItemRegister("white_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.WHITE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> ORANGE_TOFU_CANDLE_CAKE = noItemRegister("orange_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.ORANGE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> MAGENTA_TOFU_CANDLE_CAKE = noItemRegister("magenta_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.MAGENTA_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_TOFU_CANDLE_CAKE = noItemRegister("light_blue_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.LIGHT_BLUE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> YELLOW_TOFU_CANDLE_CAKE = noItemRegister("yellow_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.YELLOW_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIME_TOFU_CANDLE_CAKE = noItemRegister("lime_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.LIME_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> PINK_TOFU_CANDLE_CAKE = noItemRegister("pink_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.PINK_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> GRAY_TOFU_CANDLE_CAKE = noItemRegister("gray_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.GRAY_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_TOFU_CANDLE_CAKE = noItemRegister("light_gray_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.LIGHT_GRAY_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> CYAN_TOFU_CANDLE_CAKE = noItemRegister("cyan_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.CYAN_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> PURPLE_TOFU_CANDLE_CAKE = noItemRegister("purple_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.PURPLE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BLUE_TOFU_CANDLE_CAKE = noItemRegister("blue_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.BLUE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BROWN_TOFU_CANDLE_CAKE = noItemRegister("brown_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.BROWN_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> GREEN_TOFU_CANDLE_CAKE = noItemRegister("green_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.GREEN_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> RED_TOFU_CANDLE_CAKE = noItemRegister("red_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.RED_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BLACK_TOFU_CANDLE_CAKE = noItemRegister("black_candle_tofu_cake", (properties) -> new CandleTofuCakeBlock(TOFUCAKE.get(), Blocks.BLACK_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));

	public static final DeferredBlock<Block> ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> WHITE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("white_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.WHITE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> ORANGE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("orange_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.ORANGE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> MAGENTA_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("magenta_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.MAGENTA_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("light_blue_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.LIGHT_BLUE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> YELLOW_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("yellow_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.YELLOW_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIME_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("lime_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.LIME_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> PINK_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("pink_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.PINK_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> GRAY_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("gray_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.GRAY_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("light_gray_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.LIGHT_GRAY_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> CYAN_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("cyan_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.CYAN_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> PURPLE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("purple_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.PURPLE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BLUE_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("blue_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.BLUE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BROWN_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("brown_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.BROWN_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> GREEN_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("green_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.GREEN_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> RED_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("red_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.RED_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BLACK_ZUNDA_TOFU_CANDLE_CAKE = noItemRegister("black_candle_zundatofu_cake", (properties) -> new CandleTofuCakeBlock(ZUNDATOFUCAKE.get(), Blocks.BLACK_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));


	public static final DeferredBlock<Block> SOYCHEESE_CANDLE_TART = noItemRegister("candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> WHITE_SOYCHEESE_CANDLE_TART = noItemRegister("white_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.WHITE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> ORANGE_SOYCHEESE_CANDLE_TART = noItemRegister("orange_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.ORANGE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> MAGENTA_SOYCHEESE_CANDLE_TART = noItemRegister("magenta_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.MAGENTA_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIGHT_BLUE_SOYCHEESE_CANDLE_TART = noItemRegister("light_blue_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.LIGHT_BLUE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> YELLOW_SOYCHEESE_CANDLE_TART = noItemRegister("yellow_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.YELLOW_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIME_SOYCHEESE_CANDLE_TART = noItemRegister("lime_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.LIME_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> PINK_SOYCHEESE_CANDLE_TART = noItemRegister("pink_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.PINK_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> GRAY_SOYCHEESE_CANDLE_TART = noItemRegister("gray_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.GRAY_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> LIGHT_GRAY_SOYCHEESE_CANDLE_TART = noItemRegister("light_gray_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.LIGHT_GRAY_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> CYAN_SOYCHEESE_CANDLE_TART = noItemRegister("cyan_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.CYAN_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> PURPLE_SOYCHEESE_CANDLE_TART = noItemRegister("purple_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.PURPLE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BLUE_SOYCHEESE_CANDLE_TART = noItemRegister("blue_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.BLUE_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BROWN_SOYCHEESE_CANDLE_TART = noItemRegister("brown_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.BROWN_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> GREEN_SOYCHEESE_CANDLE_TART = noItemRegister("green_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.GREEN_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> RED_SOYCHEESE_CANDLE_TART = noItemRegister("red_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.RED_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
	public static final DeferredBlock<Block> BLACK_SOYCHEESE_CANDLE_TART = noItemRegister("black_candle_soycheese_candle_tart", (properties) -> new CandleTofuCakeBlock(SOYCHEESE_TART.get(), Blocks.BLACK_CANDLE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));

	public static final DeferredBlock<ZundamaBlock> ZUNDAMA_BLOCK = register("zundama_block", (properties) -> new ZundamaBlock(properties.strength(0.4F).noOcclusion().lightLevel((blockState) -> 9).sound(SoundType.HONEY_BLOCK)));

	public static final DeferredBlock<SoymilkCauldronBlock> SOYMILK_CAULDRON = noItemRegisterWithEmpty("soymilk_cauldron", (properties) -> new SoymilkCauldronBlock(properties.strength(2.0F).noOcclusion().requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL), TofuItems.BUCKET_SOYMILK, TofuItems.SOY_CHEESE));
	public static final DeferredBlock<SoymilkCauldronBlock> SOYMILK_NETHER_CAULDRON = noItemRegisterWithEmpty("soymilk_nether_cauldron", (properties) -> new SoymilkCauldronBlock(properties.strength(2.0F).noOcclusion().requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL), TofuItems.BUCKET_SOYMILK_NETHER, TofuItems.SOY_NETHER_CHEESE));
	public static final DeferredBlock<SoymilkCauldronBlock> SOYMILK_SOUL_CAULDRON = noItemRegisterWithEmpty("soymilk_soul_cauldron", (properties) -> new SoymilkCauldronBlock(properties.strength(2.0F).noOcclusion().requiresCorrectToolForDrops().randomTicks().sound(SoundType.METAL), TofuItems.BUCKET_SOYMILK_SOUL, TofuItems.SOY_SOUL_CHEESE));

	public static final DeferredBlock<Block> TOFUBED = register("tofubed", (properties) -> new TofuBedBlock(properties.strength(0.2F).noOcclusion().sound(SoundType.SNOW)));
	public static final DeferredBlock<Block> TOFUCHEST = register("tofuchest", (properties) -> new TofuChestBlock(properties.strength(2.5F).noOcclusion().sound(SoundType.STONE), TofuBlockEntitys.TOFUCHEST::get));

	public static final DeferredBlock<Block> FOODPLATE = register("foodplate", (properties) -> new FoodPlateBlock(properties.strength(1.0F).sound(SoundType.METAL)));
	public static final DeferredBlock<Block> TOFUNIAN_STATUE = register("tofunian_statue", (properties) -> new TofunianStatueBlock(properties.strength(100F, 3600000.0F).requiresCorrectToolForDrops().pushReaction(PushReaction.BLOCK).sound(SoundType.LODESTONE)));

	public static final DeferredBlock<Block> RICE_BLOCK = register("rice_block",
			(properties) -> new RiceBlock(properties
					.strength(2.0F)
					.sound(SoundType.GRASS)
					.noOcclusion())
	);
	public static final DeferredBlock<Block> SOYBEANS_SEEDS_BLOCK = register("seeds_soybeans_block",
			(properties) -> new BagBlock(properties
					.strength(1.0F)
					.sound(SoundType.WOOL)
					.noOcclusion())
	);
	public static final DeferredBlock<Block> NETHER_SOYBEANS_SEEDS_BLOCK = register("seeds_soybeans_nether_block",
			(properties) -> new BagBlock(properties
					.strength(1.0F)
					.sound(SoundType.WOOL)
					.noOcclusion())
	);

	public static final DeferredBlock<Block> SOUL_SOYBEANS_SEEDS_BLOCK = register("seeds_soybeans_soul_block",
			(properties) -> new BagBlock(properties
					.strength(1.0F)
					.sound(SoundType.WOOL)
					.noOcclusion())
	);


	public static final DeferredBlock<FlowerPotBlock> POTTED_LEEK = noItemRegister("potted_leek", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, LEEK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));

	public static final DeferredBlock<FlowerPotBlock> POTTED_TOFU_SAPLING = noItemRegister("potted_tofu_sapling", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SAPLING_TOFU, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_TOFU_FLOWER = noItemRegister("potted_tofu_flower", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, TOFU_FLOWER, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_ZUNDA_TOFU_MUSHROOM = noItemRegister("potted_zunda_tofu_mushroom", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ZUNDATOFU_MUSHROOM, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));
	public static final DeferredBlock<FlowerPotBlock> POTTED_APRICOT_SAPLING = noItemRegister("potted_apricot_sapling", (properties) -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SAPLING_APRICOT, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT));

	public static final DeferredBlock<Block> TOFU_DETECTOR = register("tofu_detector", (properties) -> new TofuDetectorBlock(properties.strength(5.0F, 6.0F).requiresCorrectToolForDrops().isRedstoneConductor(TofuBlocks::never)));
	public static final DeferredBlock<Block> TF_STORAGE = register("tf_storage", (properties) -> new TFStorageBlock(properties.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFStorageBlock.LIT) ? 13 : 0;
	})));
	public static final DeferredBlock<Block> TF_CRAFTER = register("tf_crafter", (properties) -> new TFCrafterBlock(properties.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFCrafterBlock.CRAFTING) ? 10 : 0;
	})));
	public static final DeferredBlock<Block> TF_OVEN = register("tf_oven", (properties) -> new TFOvenBlock(properties.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFOvenBlock.LIT) ? 13 : 0;
	})));
	public static final DeferredBlock<Block> TF_COLLECTOR = register("tf_collector", (properties) -> new TFCollectorBlock(properties.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion().lightLevel((p_50872_) -> {
		return p_50872_.getValue(TFCollectorBlock.LIT) ? 13 : 0;
	})));

	public static final DeferredBlock<Block> ANTENNA_BASIC = register("antenna_basic", (properties) -> new TFAntennaBlock(properties.requiresCorrectToolForDrops().noOcclusion().noCollission().strength(5.0F, 6.0F).sound(SoundType.METAL)));
	public static final DeferredBlock<Block> TOFU_WORK_STATION = register("tofu_work_station", (properties) -> new TofuWorkStationBlock(properties.requiresCorrectToolForDrops().noOcclusion().strength(1.5F, 6.0F).sound(SoundType.STONE)));
	private static boolean always(BlockState p_50775_, BlockGetter p_50776_, BlockPos p_50777_) {
		return true;
	}

	private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
		return false;
	}

	private static <B extends Block> DeferredBlock<B> noItemRegister(String name, Function<BlockBehaviour.Properties, ? extends B> block, BlockBehaviour.Properties properties) {
		DeferredBlock<B> register = BLOCKS.registerBlock(name, block, properties.setId(ResourceKey.create(Registries.BLOCK, TofuCraftReload.prefix(name))));
		return register;
	}

	private static <B extends Block> DeferredBlock<B> noItemRegisterWithEmpty(String name, Function<BlockBehaviour.Properties, ? extends B> block) {
		DeferredBlock<B> register = noItemRegister(name, block, BlockBehaviour.Properties.of());
		return register;
	}

	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> block) {
		DeferredBlock<B> bDeferredBlock = noItemRegister(name, block, BlockBehaviour.Properties.of());
		TofuBlocks.registerBlockItem(name, bDeferredBlock);
		return bDeferredBlock;
	}

	private static <B extends Block> DeferredItem<? extends BlockItem> registerBlockItem(String name, DeferredBlock<B> bDeferredBlock) {
		DeferredBlock<B> block = Objects.requireNonNull(bDeferredBlock);
		if (block == GRILLEDTOFU) {
			return TofuItems.ITEMS.registerItem(name, properties -> new EdiableBlockItem(GRILLEDTOFU.get(), properties.food(TofuFoods.TOFU_GRILLED_BLOCK)));
		} else if (block == TOFUTORCH_KINU) {
			return TofuItems.ITEMS.registerItem(name, properties -> new StandingAndWallBlockItem(TOFUTORCH_KINU.get(), WALLTOFUTORCH_KINU.get(), Direction.DOWN, properties));
		} else if (block == TOFUTORCH_MOMEN) {
			return TofuItems.ITEMS.registerItem(name, properties -> new StandingAndWallBlockItem(TOFUTORCH_MOMEN.get(), WALLTOFUTORCH_MOMEN.get(), Direction.DOWN, properties));
		} else if (block == TOFUTORCH_ISHI) {
			return TofuItems.ITEMS.registerItem(name, properties -> new StandingAndWallBlockItem(TOFUTORCH_ISHI.get(), WALLTOFUTORCH_ISHI.get(), Direction.DOWN, properties));
		} else if (block == TOFUTORCH_METAL) {
			return TofuItems.ITEMS.registerItem(name, properties -> new StandingAndWallBlockItem(TOFUTORCH_METAL.get(), WALLTOFUTORCH_METAL.get(), Direction.DOWN, properties));
		} else if (block == TOFUTORCH_GRILLED) {
			return TofuItems.ITEMS.registerItem(name, properties -> new StandingAndWallBlockItem(TOFUTORCH_GRILLED.get(), WALLTOFUTORCH_GRILLED.get(), Direction.DOWN, properties));
		} else if (block == TOFUTORCH_ZUNDA) {
			return TofuItems.ITEMS.registerItem(name, properties -> new StandingAndWallBlockItem(TOFUTORCH_ZUNDA.get(), WALLTOFUTORCH_ZUNDA.get(), Direction.DOWN, properties));
		} else if (block == TOFUTORCH_HELL) {
			return TofuItems.ITEMS.registerItem(name, properties -> new StandingAndWallBlockItem(TOFUTORCH_HELL.get(), WALLTOFUTORCH_HELL.get(), Direction.DOWN, properties));
		} else if (block == TOFUTORCH_SOUL) {
			return TofuItems.ITEMS.registerItem(name, properties -> new StandingAndWallBlockItem(TOFUTORCH_SOUL.get(), WALLTOFUTORCH_SOUL.get(), Direction.DOWN, properties));
		} else if (block == TOFU_STEM_SIGN) {
			return TofuItems.ITEMS.registerItem(name, properties -> new SignItem(TOFU_STEM_SIGN.get(), TOFU_STEM_WALL_SIGN.get(), properties.stacksTo(16)));
		} else if (block == LEEK_GREEN_SIGN) {
			return TofuItems.ITEMS.registerItem(name, properties -> new SignItem(LEEK_GREEN_SIGN.get(), LEEK_GREEN_WALL_SIGN.get(), properties.stacksTo(16)));
		} else if (block == LEEK_SIGN) {
			return TofuItems.ITEMS.registerItem(name, properties -> new SignItem(LEEK_SIGN.get(), LEEK_WALL_SIGN.get(), properties.stacksTo(16)));
		} else if (block == TOFU_STEM_HANGING_SIGN) {
			return TofuItems.ITEMS.registerItem(name, properties -> new HangingSignItem(TOFU_STEM_HANGING_SIGN.get(), TOFU_STEM_WALL_HANGING_SIGN.get(), properties.stacksTo(16)));
		} else if (block == LEEK_GREEN_HANGING_SIGN) {
			return TofuItems.ITEMS.registerItem(name, properties -> new HangingSignItem(LEEK_GREEN_HANGING_SIGN.get(), LEEK_GREEN_WALL_HANGING_SIGN.get(), properties.stacksTo(16)));
		} else if (block == LEEK_HANGING_SIGN) {
			return TofuItems.ITEMS.registerItem(name, properties -> new HangingSignItem(LEEK_HANGING_SIGN.get(), LEEK_WALL_HANGING_SIGN.get(), properties.stacksTo(16)));
			} else {
			return TofuItems.ITEMS.registerSimpleBlockItem(bDeferredBlock);
			}

	}

	public static void flamableInit() {
		FireBlock fireBlock = (FireBlock) Blocks.FIRE;
		fireBlock.setFlammable(ZUNDATOFU_MUSHROOM.get(), 20, 20);
		fireBlock.setFlammable(LEEK.get(), 20, 20);
		fireBlock.setFlammable(TOFU_STEM_PLANKS.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_PLANKS_STAIR.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_PLANKS_SLAB.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_FENCE.get(), 5, 20);
		fireBlock.setFlammable(TOFU_STEM_FENCE_GATE.get(), 5, 20);
		fireBlock.setFlammable(LEEK_PLANKS.get(), 5, 20);
		fireBlock.setFlammable(LEEK_PLANKS_STAIR.get(), 5, 20);
		fireBlock.setFlammable(LEEK_PLANKS_SLAB.get(), 5, 20);
		fireBlock.setFlammable(LEEK_FENCE.get(), 5, 20);
		fireBlock.setFlammable(LEEK_FENCE_GATE.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_PLANKS.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_PLANKS_STAIR.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_PLANKS_SLAB.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_FENCE.get(), 5, 20);
		fireBlock.setFlammable(LEEK_GREEN_FENCE_GATE.get(), 5, 20);
	}
}

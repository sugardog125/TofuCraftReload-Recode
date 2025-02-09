package baguchi.tofucraft.data;

import baguchi.tofucraft.block.crop.RiceCropsBlock;
import baguchi.tofucraft.block.crop.SoybeanCropsBlock;
import baguchi.tofucraft.block.crop.SoybeanPaleCropsBlock;
import baguchi.tofucraft.block.crop.SproutsCropBlock;
import baguchi.tofucraft.block.utils.MisoBarrelBlock;
import baguchi.tofucraft.block.utils.WeightBaseBlock;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuDataComponents;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.fml.util.ObfuscationReflectionHelper;

import java.util.HashSet;
import java.util.Set;

public class BlockLootTables extends BlockLootSubProvider {
	private final Set<Block> knownBlocks = new HashSet<>();
	// [VanillaCopy] super
	private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
	private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

	private static final Set<Item> EXPLOSION_RESISTANT = Set.of();


	protected BlockLootTables(HolderLookup.Provider p_344943_) {
		super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), p_344943_);
	}

	@Override
	protected void add(Block block, LootTable.Builder builder) {
		super.add(block, builder);
		knownBlocks.add(block);
	}

	@Override
	protected void generate() {
		LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 6));
		LootItemCondition.Builder lootitemcondition$builder1 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));
		LootItemCondition.Builder lootitemcondition$builder_extra = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 5));


		add(TofuBlocks.SOYBEAN.get(), applyExplosionDecay(TofuBlocks.SOYBEAN.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS.get()).when(lootitemcondition$builder1).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS.get())))).withPool(LootPool.lootPool().when(lootitemcondition$builder1).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))).withPool(LootPool.lootPool().when(lootitemcondition$builder).add(LootItem.lootTableItem(TofuItems.EDAMAME.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))).withPool(LootPool.lootPool().when(lootitemcondition$builder_extra).add(LootItem.lootTableItem(TofuItems.EDAMAME.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))));

		LootItemCondition.Builder lootitemcondition$builder2 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN_NETHER.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));

		add(TofuBlocks.SOYBEAN_NETHER.get(), applyExplosionDecay(TofuBlocks.SOYBEAN_NETHER.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_NETHER.get()).when(lootitemcondition$builder2).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_NETHER.get())))).withPool(LootPool.lootPool().when(lootitemcondition$builder2).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_NETHER.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));

		LootItemCondition.Builder lootitemcondition$builder3 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN_SOUL.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));

		add(TofuBlocks.SOYBEAN_SOUL.get(), applyExplosionDecay(TofuBlocks.SOYBEAN_SOUL.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_SOUL.get()).when(lootitemcondition$builder3).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_SOUL.get())))).withPool(LootPool.lootPool().when(lootitemcondition$builder3).add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_SOUL.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));

		LootItemCondition.Builder pale = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN_PALE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanPaleCropsBlock.AGE, 3));
		LootItemCondition.Builder pale_glow = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.SOYBEAN_PALE.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanPaleCropsBlock.AGE, 3).hasProperty(SoybeanPaleCropsBlock.BLOOM, true));

		add(TofuBlocks.SOYBEAN_PALE.get(), applyExplosionDecay(TofuBlocks.SOYBEAN_PALE.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_PALE_GLOW.get()).when(pale_glow).otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_PALE.get()))))
				.withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_PALE_GLOW.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)).when(pale_glow)
						.otherwise(LootItem.lootTableItem(TofuItems.SEEDS_SOYBEANS_PALE.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))))));

		LootItemCondition.Builder lootitemcondition$builder4 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.LEEK_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 3));

		add(TofuBlocks.LEEK_CROP.get(), applyExplosionDecay(TofuBlocks.LEEK_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.LEEK.get()).when(lootitemcondition$builder4).otherwise(LootItem.lootTableItem(TofuItems.LEEK.get())))).withPool(LootPool.lootPool().when(lootitemcondition$builder4).add(LootItem.lootTableItem(TofuItems.LEEK.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));

		LootItemCondition.Builder lootitemconditon$chili_crop = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.CHILI_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SoybeanCropsBlock.AGE, 7));

		add(TofuBlocks.CHILI_CROP.get(), applyExplosionDecay(TofuBlocks.CHILI_CROP.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.CHILI.get()).when(lootitemconditon$chili_crop).otherwise(LootItem.lootTableItem(TofuItems.CHILI.get())))).withPool(LootPool.lootPool().when(lootitemconditon$chili_crop).add(LootItem.lootTableItem(TofuItems.CHILI.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));

		LootItemCondition.Builder lootitemcondition$builder7 = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.RICE_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RiceCropsBlock.AGE, 7));

		this.add(TofuBlocks.RICE_CROP.get(), createCropDrops(TofuBlocks.RICE_CROP.get(), TofuItems.RICE.get(), TofuItems.SEEDS_RICE.get(), lootitemcondition$builder7));
		registerEmpty(TofuBlocks.RICE_ROOT.get());

		LootItemCondition.Builder lootitemconditon$sprouts = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.CHILI_CROP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SproutsCropBlock.AGE, 3));

		add(TofuBlocks.SPROUTS.get(), applyExplosionDecay(TofuBlocks.SPROUTS.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.SPROUTS.get()).when(lootitemconditon$sprouts).otherwise(LootItem.lootTableItem(TofuItems.SPROUTS.get())))).withPool(LootPool.lootPool().when(lootitemconditon$sprouts).add(LootItem.lootTableItem(TofuItems.SPROUTS.get()).apply(ApplyBonusCount.addBonusBinomialDistributionCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3))))));


		dropOther(TofuBlocks.SOYMILK_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(TofuBlocks.SOYMILK_NETHER_CAULDRON.get(), Blocks.CAULDRON);
		dropOther(TofuBlocks.SOYMILK_SOUL_CAULDRON.get(), Blocks.CAULDRON);

		registerTofuDrop(TofuBlocks.KINUTOFU.get(), TofuItems.TOFUKINU.get());
		registerTofuDrop(TofuBlocks.MOMENTOFU.get(), TofuItems.TOFUMOMEN.get());
		registerTofuDrop(TofuBlocks.ISHITOFU.get(), TofuItems.TOFUISHI.get());
		dropSelf(TofuBlocks.ISHITOFU_BRICK.get());
		dropSelf(TofuBlocks.ISHITOFU_SMOOTH_BRICK.get());
		dropSelf(TofuBlocks.ISHITOFU_CHISELED_BRICK.get());
		registerTofuDrop(TofuBlocks.METALTOFU.get(), TofuItems.TOFUMETAL.get());
		dropSelf(TofuBlocks.METAL_TOFU_GRATE.get());
		dropSelf(TofuBlocks.METAL_TOFU_LUMP.get());
		registerTofuDrop(TofuBlocks.DIAMONDTOFU.get(), TofuItems.TOFUDIAMOND.get());
		dropSelf(TofuBlocks.TOFU_GEM_BLOCK.get());
		dropSelf(TofuBlocks.ADVANCE_TOFU_GEM_BLOCK.get());
		registerTofuDrop(TofuBlocks.GRILLEDTOFU.get(), TofuItems.TOFUGRILLED.get());
		registerTofuDrop(TofuBlocks.ZUNDATOFU.get(), TofuItems.TOFUZUNDA.get());

		dropSelf(TofuBlocks.ZUNDATOFU_BRICK.get());
		dropSelf(TofuBlocks.ZUNDATOFU_SMOOTH_BRICK.get());
		dropSelf(TofuBlocks.TOFUSTAIR_ZUNDABRICK.get());
		registerSlab(TofuBlocks.TOFUSLAB_ZUNDABRICK.get());

		registerTofuDrop(TofuBlocks.HELLTOFU.get(), TofuItems.TOFUHELL.get());
		registerTofuDrop(TofuBlocks.SOULTOFU.get(), TofuItems.TOFUSOUL.get());
		registerTofuDrop(TofuBlocks.MISOTOFU.get(), TofuItems.TOFUMISO.get());
		registerTofuDrop(TofuBlocks.DRIEDTOFU.get(), TofuItems.TOFUDRIED.get());

		registerTofuDrop(TofuBlocks.EGGTOFU.get(), TofuItems.TOFUEGG.get());
		dropSelf(TofuBlocks.TOFUSTAIR_EGG.get());
		registerSlab(TofuBlocks.TOFUSLAB_EGG.get());

		dropSelf(TofuBlocks.EGGTOFU_BRICK.get());
		dropSelf(TofuBlocks.TOFUSTAIR_EGGBRICK.get());
		registerSlab(TofuBlocks.TOFUSLAB_EGGBRICK.get());

		registerTofuDrop(TofuBlocks.SESAMETOFU.get(), TofuItems.TOFUSESAME.get());
		dropSelf(TofuBlocks.TOFUSTAIR_SESAME.get());
		registerSlab(TofuBlocks.TOFUSLAB_SESAME.get());

		dropSelf(TofuBlocks.HELLTOFU_BRICK.get());
		dropSelf(TofuBlocks.HELLTOFU_SMOOTH_BRICK.get());
		dropSelf(TofuBlocks.SOULTOFU_BRICK.get());
		dropSelf(TofuBlocks.SOULTOFU_SMOOTH_BRICK.get());
		registerTofuDrop(TofuBlocks.MINCEDTOFU.get(), TofuItems.TOFU_MINCED.get());

		dropSelf(TofuBlocks.TOFUSTAIR_KINU.get());
		dropSelf(TofuBlocks.TOFUSTAIR_MOMEN.get());
		dropSelf(TofuBlocks.TOFUSTAIR_ISHI.get());
		dropSelf(TofuBlocks.TOFUSTAIR_METAL.get());
		dropSelf(TofuBlocks.TOFUSTAIR_GRILLED.get());
		dropSelf(TofuBlocks.TOFUSTAIR_ZUNDA.get());
		dropSelf(TofuBlocks.TOFUSTAIR_HELL.get());
		dropSelf(TofuBlocks.TOFUSTAIR_SOUL.get());
		dropSelf(TofuBlocks.TOFUSTAIR_ISHIBRICK.get());
		dropSelf(TofuBlocks.TOFUSTAIR_HELLBRICK.get());
		dropSelf(TofuBlocks.TOFUSTAIR_SOULBRICK.get());
		dropSelf(TofuBlocks.TOFUSTAIR_MISO.get());
		dropSelf(TofuBlocks.TOFUSTAIR_DRIED.get());

		registerSlab(TofuBlocks.TOFUSLAB_KINU.get());
		registerSlab(TofuBlocks.TOFUSLAB_MOMEN.get());
		registerSlab(TofuBlocks.TOFUSLAB_ISHI.get());
		registerSlab(TofuBlocks.TOFUSLAB_METAL.get());
		registerSlab(TofuBlocks.TOFUSLAB_GRILLED.get());
		registerSlab(TofuBlocks.TOFUSLAB_ZUNDA.get());
		registerSlab(TofuBlocks.TOFUSLAB_HELL.get());
		registerSlab(TofuBlocks.TOFUSLAB_SOUL.get());
		registerSlab(TofuBlocks.TOFUSLAB_ISHIBRICK.get());
		registerSlab(TofuBlocks.TOFUSLAB_HELLBRICK.get());
		registerSlab(TofuBlocks.TOFUSLAB_SOULBRICK.get());
		registerSlab(TofuBlocks.TOFUSLAB_MISO.get());
		registerSlab(TofuBlocks.TOFUSLAB_DRIED.get());

		dropSelf(TofuBlocks.TOFUFENCE_KINU.get());
		dropSelf(TofuBlocks.TOFUFENCE_MOMEN.get());
		dropSelf(TofuBlocks.TOFUFENCE_ISHI.get());
		dropSelf(TofuBlocks.TOFUFENCE_METAL.get());
		dropSelf(TofuBlocks.TOFUFENCE_HELL.get());
		dropSelf(TofuBlocks.TOFUFENCE_SOUL.get());
		dropSelf(TofuBlocks.TOFUFENCE_GRILLED.get());
		dropSelf(TofuBlocks.TOFUFENCE_ZUNDA.get());

		this.add(TofuBlocks.TOFUDOOR_KINU.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_MOMEN.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_ISHI.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_METAL.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_HELL.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_SOUL.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_GRILLED.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		this.add(TofuBlocks.TOFUDOOR_ZUNDA.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));

		dropSelf(TofuBlocks.TOFUTRAPDOOR_KINU.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_MOMEN.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_ISHI.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_METAL.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_HELL.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_SOUL.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_GRILLED.get());
		dropSelf(TofuBlocks.TOFUTRAPDOOR_ZUNDA.get());

		dropSelf(TofuBlocks.TOFUTORCH_KINU.get());
		dropSelf(TofuBlocks.TOFUTORCH_MOMEN.get());
		dropSelf(TofuBlocks.TOFUTORCH_ISHI.get());
		dropSelf(TofuBlocks.TOFUTORCH_METAL.get());
		dropSelf(TofuBlocks.TOFUTORCH_GRILLED.get());
		dropSelf(TofuBlocks.TOFUTORCH_ZUNDA.get());
		dropSelf(TofuBlocks.TOFUTORCH_HELL.get());
		dropSelf(TofuBlocks.TOFUTORCH_SOUL.get());

		dropOther(TofuBlocks.WALLTOFUTORCH_KINU.get(), TofuBlocks.TOFUTORCH_KINU.get());
		dropOther(TofuBlocks.WALLTOFUTORCH_MOMEN.get(), TofuBlocks.TOFUTORCH_MOMEN);
		dropOther(TofuBlocks.WALLTOFUTORCH_ISHI.get(), TofuBlocks.TOFUTORCH_ISHI);
		dropOther(TofuBlocks.WALLTOFUTORCH_METAL.get(), TofuBlocks.TOFUTORCH_METAL);
		dropOther(TofuBlocks.WALLTOFUTORCH_GRILLED.get(), TofuBlocks.TOFUTORCH_GRILLED);
		dropOther(TofuBlocks.WALLTOFUTORCH_ZUNDA.get(), TofuBlocks.TOFUTORCH_ZUNDA);
		dropOther(TofuBlocks.WALLTOFUTORCH_HELL.get(), TofuBlocks.TOFUTORCH_HELL);
		dropOther(TofuBlocks.WALLTOFUTORCH_SOUL.get(), TofuBlocks.TOFUTORCH_SOUL);
		dropSelf(TofuBlocks.TOFULADDER_KINU.get());
		dropSelf(TofuBlocks.TOFULADDER_MOMEN.get());
		dropSelf(TofuBlocks.TOFULADDER_ISHI.get());
		dropSelf(TofuBlocks.TOFULADDER_ISHIBRICK.get());
		dropSelf(TofuBlocks.TOFULADDER_METAL.get());
		dropSelf(TofuBlocks.TOFULADDER_GRILLED.get());
		dropSelf(TofuBlocks.TOFULADDER_ZUNDA.get());
		dropSelf(TofuBlocks.TOFULADDER_HELL.get());
		dropSelf(TofuBlocks.TOFULADDER_SOUL.get());

		dropSelf(TofuBlocks.TOFU_TERRAIN.get());
		dropSelf(TofuBlocks.MABOU_TERRAIN.get());
		dropSelf(TofuBlocks.TOFU_TERRAIN_ZUNDA.get());
		dropSelf(TofuBlocks.TOFUSLATE.get());
		this.add(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get(), createTofuDiamondOreDrop(TofuBlocks.TOFUSLATE_TOFU_DIAMOND_ORE.get(), TofuItems.TOFUDIAMOND_NUGGET.get()));
		this.add(TofuBlocks.ORE_TOFU_DIAMOND.get(), createTofuDiamondOreDrop(TofuBlocks.ORE_TOFU_DIAMOND.get(), TofuItems.TOFUDIAMOND_NUGGET.get()));
		this.add(TofuBlocks.ORE_TOFUGEM.get(), createTofuGemOreDrop(TofuBlocks.ORE_TOFUGEM.get(), TofuItems.TOFUGEM.get()));

		dropSelf(TofuBlocks.TOFU_BEDROCK.get());
		dropSelf(TofuBlocks.SAPLING_TOFU.get());
		this.add(TofuBlocks.LEAVES_TOFU.get(), createTofuLeavesDrops(TofuBlocks.LEAVES_TOFU.get(), TofuBlocks.SAPLING_TOFU.get(), DEFAULT_SAPLING_DROP_RATES));

		dropSelf(TofuBlocks.SAPLING_APRICOT.get());
		this.add(TofuBlocks.LEAVES_APRICOT.get(), createApricotLeavesDrop(TofuBlocks.LEAVES_APRICOT.get(), TofuBlocks.SAPLING_APRICOT.get(), DEFAULT_SAPLING_DROP_RATES));

		dropSelf(TofuBlocks.TOFU_FLOWER.get());

		dropSelf(TofuBlocks.LEEK_GREEN_STEM.get());
		dropSelf(TofuBlocks.LEEK_GREEN_PLANKS.get());
		dropSelf(TofuBlocks.LEEK_GREEN_PLANKS_STAIR.get());
		registerSlab(TofuBlocks.LEEK_GREEN_PLANKS_SLAB.get());
		dropSelf(TofuBlocks.LEEK_GREEN_FENCE.get());
		dropSelf(TofuBlocks.LEEK_GREEN_FENCE_GATE.get());
		this.add(TofuBlocks.LEEK_GREEN_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(TofuBlocks.LEEK_GREEN_TRAPDOOR.get());
		dropSelf(TofuBlocks.LEEK_GREEN_PRESSURE_PLATE.get());
		dropSelf(TofuBlocks.LEEK_GREEN_BUTTON.get());

		dropSelf(TofuBlocks.LEEK_STEM.get());
		dropSelf(TofuBlocks.LEEK_PLANKS.get());
		dropSelf(TofuBlocks.LEEK_PLANKS_STAIR.get());
		registerSlab(TofuBlocks.LEEK_PLANKS_SLAB.get());
		dropSelf(TofuBlocks.LEEK_FENCE.get());
		dropSelf(TofuBlocks.LEEK_FENCE_GATE.get());
		dropSelf(TofuBlocks.LEEK_PRESSURE_PLATE.get());
		dropSelf(TofuBlocks.LEEK_BUTTON.get());

		dropSelf(TofuBlocks.ZUNDATOFU_MUSHROOM.get());
		this.add(TofuBlocks.ZUNDA_MUSHROOM_BLOCK.get(), createZundaMushroomDrop(TofuBlocks.ZUNDA_MUSHROOM_BLOCK.get(), TofuBlocks.ZUNDATOFU_MUSHROOM.get(), DEFAULT_SAPLING_DROP_RATES));

		dropSelf(TofuBlocks.TOFU_STEM.get());
		dropSelf(TofuBlocks.TOFU_STEM_PLANKS.get());
		dropSelf(TofuBlocks.TOFU_STEM_PLANKS_STAIR.get());
		registerSlab(TofuBlocks.TOFU_STEM_PLANKS_SLAB.get());
		dropSelf(TofuBlocks.TOFU_STEM_FENCE.get());
		dropSelf(TofuBlocks.TOFU_STEM_FENCE_GATE.get());
		this.add(TofuBlocks.TOFU_STEM_DOOR.get(), (block) -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
		dropSelf(TofuBlocks.TOFU_STEM_TRAPDOOR.get());
		dropSelf(TofuBlocks.TOFU_STEM_PRESSURE_PLATE.get());
		dropSelf(TofuBlocks.TOFU_STEM_BUTTON.get());

		this.dropOther(TofuBlocks.LEEK.get(), TofuItems.LEEK.get());

		dropOther(TofuBlocks.TOFU_FARMLAND.get(), TofuBlocks.TOFU_TERRAIN.get());
		dropSelf(TofuBlocks.SALTPAN.get());
		dropSelf(TofuBlocks.SALT_FURNACE.get());
		dropSelf(TofuBlocks.SPROUTSJAR.get());
		dropSelf(TofuBlocks.MORIJIO.get());
		dropSelf(TofuBlocks.FOODPLATE.get());
		dropSelf(TofuBlocks.ZUNDAMA_BLOCK.get());

		dropSelf(TofuBlocks.RICE_BLOCK.get());
		dropSelf(TofuBlocks.SOYBEANS_SEEDS_BLOCK.get());
		dropSelf(TofuBlocks.NETHER_SOYBEANS_SEEDS_BLOCK.get());
		dropSelf(TofuBlocks.SOUL_SOYBEANS_SEEDS_BLOCK.get());
		dropSelf(TofuBlocks.SALT_BLOCK.get());

		LootItemCondition.Builder miso = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.BARREL_MISO.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MisoBarrelBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.BARREL_MISO.get(), applyExplosionDecay(TofuBlocks.BARREL_MISO.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.MISO.get()).when(miso).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(Blocks.BARREL).when(miso).when(ExplosionCondition.survivesExplosion()).otherwise(LootItem.lootTableItem(TofuBlocks.BARREL_MISO.get()).when(ExplosionCondition.survivesExplosion()))))));

		LootItemCondition.Builder miso_tofu = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.BARREL_MISOTOFU.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeightBaseBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.BARREL_MISOTOFU.get(), applyExplosionDecay(TofuBlocks.BARREL_MISOTOFU.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.TOFUMISO.get()).when(miso_tofu).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(Blocks.BARREL).when(miso_tofu).when(ExplosionCondition.survivesExplosion()).otherwise(LootItem.lootTableItem(TofuBlocks.BARREL_MISOTOFU.get()).when(ExplosionCondition.survivesExplosion()))))));

		LootItemCondition.Builder natto = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.NATTOBED.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeightBaseBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.NATTOBED.get(), applyExplosionDecay(TofuBlocks.NATTOBED.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.NATTO.get()).when(natto).apply(SetItemCountFunction.setCount(ConstantValue.exactly(6.0F)))))));

		LootItemCondition.Builder nether_natto = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.NETHER_NATTOBED.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeightBaseBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.NETHER_NATTOBED.get(), applyExplosionDecay(TofuBlocks.NETHER_NATTOBED.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.NETHER_NATTO.get()).when(nether_natto).apply(SetItemCountFunction.setCount(ConstantValue.exactly(6.0F)))))));

		LootItemCondition.Builder tofugemAdvBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(TofuBlocks.BARREL_ADV_TOFUGEM.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(WeightBaseBlock.STAT, WeightBaseBlock.Stat.USED));

		add(TofuBlocks.BARREL_ADV_TOFUGEM.get(), applyExplosionDecay(TofuBlocks.BARREL_ADV_TOFUGEM.get(), LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(TofuItems.ADVANCE_TOFUGEM.get()).when(tofugemAdvBuilder).apply(SetItemCountFunction.setCount(ConstantValue.exactly(3.0F))))).withPool(LootPool.lootPool().add(LootItem.lootTableItem(Blocks.BARREL).when(tofugemAdvBuilder).when(ExplosionCondition.survivesExplosion()).otherwise(LootItem.lootTableItem(TofuBlocks.BARREL_ADV_TOFUGEM.get()).when(ExplosionCondition.survivesExplosion()))))));

		dropSelf(TofuBlocks.TOFU_CHIKUWA_BLOCK.get());
		dropSelf(TofuBlocks.CHIKUWA_BLOCK.get());

		registerEmpty(TofuBlocks.TOFUCAKE.get());
		registerEmpty(TofuBlocks.ZUNDATOFUCAKE.get());
		registerEmpty(TofuBlocks.SOYCHEESE_TART.get());

		this.add(TofuBlocks.TOFUBED.get(), (p_124233_) -> {
			return createSinglePropConditionTable(p_124233_, BedBlock.PART, BedPart.HEAD);
		});
		dropSelf(TofuBlocks.TOFUCHEST.get());

		registerEmpty(TofuBlocks.YUBA.get());
		registerEmpty(TofuBlocks.SUSPICIOUS_TOFU_TERRAIN.get());
		dropSelf(TofuBlocks.TOFU_STEM_SIGN.get());
		dropOther(TofuBlocks.TOFU_STEM_WALL_SIGN.get(), TofuBlocks.TOFU_STEM_SIGN.get());
		dropSelf(TofuBlocks.LEEK_GREEN_SIGN.get());
		dropOther(TofuBlocks.LEEK_GREEN_WALL_SIGN.get(), TofuBlocks.LEEK_GREEN_SIGN.get());
		dropSelf(TofuBlocks.LEEK_SIGN.get());
		dropOther(TofuBlocks.LEEK_WALL_SIGN.get(), TofuBlocks.LEEK_SIGN.get());
		dropSelf(TofuBlocks.TOFU_STEM_HANGING_SIGN.get());
		dropOther(TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_HANGING_SIGN.get());
		dropSelf(TofuBlocks.LEEK_GREEN_HANGING_SIGN.get());
		dropOther(TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_HANGING_SIGN.get());
		dropSelf(TofuBlocks.LEEK_HANGING_SIGN.get());
		dropOther(TofuBlocks.LEEK_WALL_HANGING_SIGN.get(), TofuBlocks.LEEK_HANGING_SIGN.get());
		dropSelf(TofuBlocks.TOFU_METAL_LANTERN.get());
		dropSelf(TofuBlocks.TOFU_METAL_SOUL_LANTERN.get());
		dropSelf(TofuBlocks.TOFU_METAL_CHAIN.get());

		dropPottedContents(TofuBlocks.POTTED_TOFU_SAPLING.get());
		dropPottedContents(TofuBlocks.POTTED_APRICOT_SAPLING.get());
		dropPottedContents(TofuBlocks.POTTED_ZUNDA_TOFU_MUSHROOM.get());
		dropPottedContents(TofuBlocks.POTTED_LEEK.get());
		dropPottedContents(TofuBlocks.POTTED_TOFU_FLOWER.get());
		dropSelf(TofuBlocks.TOFU_DETECTOR.get());
		this.add(TofuBlocks.TF_STORAGE.get(), this::createTFMechaTable);
		this.add(TofuBlocks.TF_CRAFTER.get(), this::createTFMechaTable);
		this.add(TofuBlocks.TF_OVEN.get(), this::createTFMechaTable);
		dropSelf(TofuBlocks.ANTENNA_BASIC.get());
		dropSelf(TofuBlocks.TOFU_WORK_STATION.get());
		this.add(TofuBlocks.TF_COLLECTOR.get(), this::createTFMechaTable);
	}

	private LootTable.Builder createTFMechaTable(Block p_277929_) {
		return LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
								.setRolls(ConstantValue.exactly(1.0F))
								.add(
										LootItem.lootTableItem(p_277929_)
												.apply(
														CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
																.include(TofuDataComponents.TF_ENERGY_DATA.get())
												)
								)

				);
	}

	protected LootTable.Builder createTofuDiamondOreDrop(Block p_124140_, Item p_124141_) {
		return createSilkTouchDispatchTable(p_124140_, applyExplosionDecay(p_124140_, LootItem.lootTableItem(p_124141_).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)))));
	}

	protected LootTable.Builder createTofuGemOreDrop(Block p_124140_, Item p_124141_) {
		return createSilkTouchDispatchTable(p_124140_, applyExplosionDecay(p_124140_, LootItem.lootTableItem(p_124141_).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))).apply(ApplyBonusCount.addOreBonusCount(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)))));
	}

	protected LootTable.Builder createApricotLeavesDrop(Block p_124264_, Block p_124265_, float... p_124266_) {
		return createLeavesDrops(p_124264_, p_124265_, p_124266_).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionCondition(TofuBlocks.LEAVES_APRICOT.get(), LootItem.lootTableItem(TofuItems.APRICOT.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
	}

	protected LootTable.Builder createTofuLeavesDrops(Block p_250088_, Block p_250731_, float... p_248949_) {
		return createSilkTouchOrShearsDispatchTable(p_250088_, this.applyExplosionCondition(p_250088_, LootItem.lootTableItem(p_250731_)).when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), p_248949_)));
	}

	protected LootTable.Builder createZundaMushroomDrop(Block p_124264_, Block p_124265_, float... p_124266_) {
		return createTofuLeavesDrops(p_124264_, p_124265_, p_124266_).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionCondition(p_124264_, LootItem.lootTableItem(TofuItems.TOFUZUNDA.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))).add(applyExplosionCondition(p_124264_, LootItem.lootTableItem(TofuItems.ZUNDA.get())).when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.01F, 0.0025555555F, 0.00625F, 0.018333334F, 0.025F))));
	}

	private void registerTofuDrop(Block tofu, Item dropItem) {
		LootPoolEntryContainer.Builder<?> sticks = applyExplosionDecay(tofu, LootItem.lootTableItem(dropItem)
				.apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))));
		add(tofu, createSilkTouchOrShearsDispatchTable(tofu, sticks));
	}


	// [VanillaCopy] super.droppingWithChancesAndSticks, but non-silk touch parameter can be an item instead of a block
	private LootTable.Builder silkAndStick(Block block, ItemLike nonSilk, float... nonSilkFortune) {
		LootItemCondition.Builder NOT_SILK_TOUCH_OR_SHEARS = ObfuscationReflectionHelper.getPrivateValue(BlockLootSubProvider.class, null, "HAS_NO_SHEARS_OR_SILK_TOUCH");
		return createSilkTouchOrShearsDispatchTable(block, applyExplosionCondition(block, LootItem.lootTableItem(nonSilk.asItem())).when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), nonSilkFortune))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).when(NOT_SILK_TOUCH_OR_SHEARS).add(applyExplosionDecay(block, LootItem.lootTableItem(Items.STICK).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))).when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), 0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F))));
	}

	private void registerEmpty(Block b) {
		add(b, LootTable.lootTable());
	}

	private void registerSlab(Block b) {
		add(b, createSlabItemTable(b));
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return knownBlocks;
	}
}
package baguchi.tofucraft.data.provider;

import baguchi.tofucraft.registry.TofuBlocks;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.stream.Stream;

import static net.minecraft.client.data.models.BlockModelGenerators.createBooleanModelDispatch;
import static net.minecraft.client.data.models.model.TextureMapping.getBlockTexture;

public abstract class TofuBlockstateModelProvider extends ModelProvider {
	public static final ModelTemplate GLOW_CUBE = TofuModelTemplate.GLOW_CUBE.extend().renderType("cutout").build();
	public static final ModelTemplate TRANSLUCENT_CUBE = ModelTemplates.CUBE_ALL.extend().renderType("translucent").build();
	public static final ModelTemplate CUTOUT_CUBE = ModelTemplates.CUBE_ALL.extend().renderType("cutout").build();
	public static final ModelTemplate LEAVES = ModelTemplates.LEAVES.extend().renderType("cutout").build();
	public static final ModelTemplate CROP = ModelTemplates.CROP.extend().renderType("cutout").build();

	public TofuBlockstateModelProvider(PackOutput p_388260_, String modId) {
		super(p_388260_, modId);
	}

	public void createItemWithDoubleGrassTint(BlockModelGenerators blockModels, Block p_388714_) {
		blockModels.registerSimpleTintedItemModel(p_388714_, ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(p_388714_), TextureMapping.layer0(getBlockTexture(p_388714_).withSuffix("_top")), blockModels.modelOutput), new GrassColorSource());
	}

	public void createTintedDoublePlant(BlockModelGenerators blockModels, Block p_388276_) {
		createItemWithDoubleGrassTint(blockModels, p_388276_);
		createDoublePlant(blockModels, p_388276_, BlockModelGenerators.PlantType.TINTED);
	}

	public void createDoublePlant(BlockModelGenerators blockModels, Block p_388543_, BlockModelGenerators.PlantType p_388551_) {
		ResourceLocation resourcelocation = blockModels.createSuffixedVariant(p_388543_, "_top", p_388551_.getCross().extend().renderType("cutout").build(), TextureMapping::cross);
		ResourceLocation resourcelocation1 = blockModels.createSuffixedVariant(p_388543_, "_bottom", p_388551_.getCross().extend().renderType("cutout").build(), TextureMapping::cross);
		blockModels.createDoubleBlock(p_388543_, resourcelocation, resourcelocation1);
	}

	public void createCrossBlock(BlockModelGenerators blockModels, Block p_388178_, BlockModelGenerators.PlantType p_387157_) {
		TextureMapping texturemapping = p_387157_.getTextureMapping(p_388178_);
		this.createCrossBlock(blockModels, p_388178_, p_387157_, texturemapping);
	}

	public void createCrossBlock(BlockModelGenerators blockModels, Block p_388360_, BlockModelGenerators.PlantType p_386631_, TextureMapping p_388352_) {
		ResourceLocation resourcelocation = p_386631_.getCross().extend().renderType("cutout").build().create(p_388360_, p_388352_, blockModels.modelOutput);
		blockModels.blockStateOutput.accept(createSimpleBlock(p_388360_, resourcelocation));
	}

	public void createCutoutCube(BlockModelGenerators blockModels, Block block) {
		blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, CUTOUT_CUBE.create(block, TextureMapping.cube(block), blockModels.modelOutput)));
	}

	public void createSingleCrop(BlockModelGenerators blockModels, Block block) {
		blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, CROP.create(block, TextureMapping.crop(getBlockTexture(block)), blockModels.modelOutput)));
	}

	public void createLeaves(BlockModelGenerators blockModels, Block block) {
		blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, LEAVES.create(block, TextureMapping.cube(block), blockModels.modelOutput)));
	}

	public void createTranslucentCube(BlockModelGenerators blockModels, Block block) {
		blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, TRANSLUCENT_CUBE.create(block, TextureMapping.cube(block), blockModels.modelOutput)));
	}

	public void createGlowCube(BlockModelGenerators blockModels, Block block) {
		blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, GLOW_CUBE.create(block, TofuTextureMapping.glowCube(block), blockModels.modelOutput)));
	}


	public TofuBlockFamilyProvider family(BlockModelGenerators generators, Block p_388779_) {
		TexturedModel texturedmodel = generators.texturedModels.getOrDefault(p_388779_, TexturedModel.CUBE.get(p_388779_));
		return new TofuBlockFamilyProvider(generators, texturedmodel.getMapping()).fullBlock(p_388779_, texturedmodel.getTemplate());
	}

	public TofuBlockFamilyProvider familyWithExistingFullBlock(BlockModelGenerators generators, Block fullBlock) {
		var provider = new TofuBlockFamilyProvider(generators, TextureMapping.cube(fullBlock));
		provider.fullBlock = ModelLocationUtils.getModelLocation(fullBlock);
		return provider;
	}

	public void createCandleCake(BlockModelGenerators generators, Block p_388274_, Block p_387301_) {

		ResourceLocation resourcelocation8 = ModelTemplates.CANDLE_CAKE.create(p_387301_, TextureMapping.candleCake(p_388274_, false), generators.modelOutput);
		ResourceLocation resourcelocation9 = ModelTemplates.CANDLE_CAKE
				.createWithSuffix(p_387301_, "_lit", TextureMapping.candleCake(p_388274_, true), generators.modelOutput);
		generators.blockStateOutput
				.accept(
						MultiVariantGenerator.multiVariant(p_387301_).with(createBooleanModelDispatch(BlockStateProperties.LIT, resourcelocation9, resourcelocation8))
				);
	}

	public static String getBlockName(Block p_387523_) {
		ResourceLocation resourcelocation = BuiltInRegistries.BLOCK.getKey(p_387523_);
		return resourcelocation.getPath();
	}

	public void createTofuFarmland(BlockModelGenerators blockModels) {
		TextureMapping texturemapping = new TextureMapping().put(TextureSlot.DIRT, getBlockTexture(TofuBlocks.TOFU_TERRAIN.get())).put(TextureSlot.TOP, getBlockTexture(TofuBlocks.TOFU_FARMLAND.get()));
		TextureMapping texturemapping1 = new TextureMapping().put(TextureSlot.DIRT, getBlockTexture(TofuBlocks.TOFU_TERRAIN.get())).put(TextureSlot.TOP, getBlockTexture(TofuBlocks.TOFU_FARMLAND.get(), "_moist"));
		ResourceLocation resourcelocation = ModelTemplates.FARMLAND.create(TofuBlocks.TOFU_FARMLAND.get(), texturemapping, blockModels.modelOutput);
		ResourceLocation resourcelocation1 = ModelTemplates.FARMLAND.create(getBlockTexture(TofuBlocks.TOFU_FARMLAND.get(), "_moist"), texturemapping1, blockModels.modelOutput);
		blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(TofuBlocks.TOFU_FARMLAND.get()).with(BlockModelGenerators.createEmptyOrFullDispatch(BlockStateProperties.MOISTURE, 7, resourcelocation1, resourcelocation)));
	}

	public static MultiVariantGenerator createSimpleBlock(Block p_387997_, ResourceLocation p_388814_) {
		return MultiVariantGenerator.multiVariant(p_387997_, Variant.variant().with(VariantProperties.MODEL, p_388814_));
	}

	public void createTofuPortalBlock(BlockModelGenerators blockModels) {
		blockModels.blockStateOutput.accept(MultiVariantGenerator.multiVariant(TofuBlocks.TOFU_PORTAL.get()).with(PropertyDispatch.property(BlockStateProperties.HORIZONTAL_AXIS)
				.select(Direction.Axis.X, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(TofuBlocks.TOFU_PORTAL.get(), "_ns")))
				.select(Direction.Axis.Z, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(TofuBlocks.TOFU_PORTAL.get(), "_ew")))));
	}


	public void createTofuCakeBlock(BlockModelGenerators generators, Block block) {
		generators.registerSimpleFlatItemModel(block.asItem());
		generators.blockStateOutput
				.accept(
						MultiVariantGenerator.multiVariant(block)
								.with(
										PropertyDispatch.property(BlockStateProperties.BITES)
												.select(0, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block)))
												.select(1, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, "_slice1")))
												.select(2, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, "_slice2")))
												.select(3, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, "_slice3")))
												.select(4, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, "_slice4")))
												.select(5, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, "_slice5")))
												.select(6, Variant.variant().with(VariantProperties.MODEL, ModelLocationUtils.getModelLocation(block, "_slice6")))
								)
				);
	}

	public void createSingleCarpetBlocks(BlockModelGenerators generators, Block p_387946_) {
		ResourceLocation resourcelocation = TexturedModel.CARPET.get(p_387946_).create(p_387946_, generators.modelOutput);
		generators.blockStateOutput.accept(createSimpleBlock(p_387946_, resourcelocation));
	}

	public void createPaleCropBlock(BlockModelGenerators generators, Block p_387553_, Property<Integer> p_386757_, int... p_388514_) {
		if (p_386757_.getPossibleValues().size() != p_388514_.length) {
			throw new IllegalArgumentException();
		} else {
			Int2ObjectMap<ResourceLocation> int2objectmap = new Int2ObjectOpenHashMap<>();
			PropertyDispatch propertydispatch = PropertyDispatch.property(p_386757_)
					.generate(
							p_388091_ -> {
								int i = p_388514_[p_388091_];
								ResourceLocation resourcelocation = int2objectmap.computeIfAbsent(
										i, p_387534_ -> generators.createSuffixedVariant(p_387553_, "_stage" + i, ModelTemplates.CROP, TextureMapping::crop)
								);
								return Variant.variant().with(VariantProperties.MODEL, resourcelocation);
							}
					);
			generators.registerSimpleFlatItemModel(p_387553_.asItem());
			generators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(p_387553_).with(propertydispatch));
		}
	}

	@Override
	public final Stream<? extends Holder<Item>> getKnownItems() {
		return Stream.of();
	}

	@Override
	protected Stream<? extends Holder<Block>> getKnownBlocks() {
		return Stream.of();
	}
}
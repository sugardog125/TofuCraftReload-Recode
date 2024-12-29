package baguchi.tofucraft.data.provider;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.BlockFamily;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class TofuBlockFamilyProvider {
	public static final Map<BlockFamily.Variant, BiConsumer<TofuBlockFamilyProvider, Block>> SHAPE_CONSUMERS = ImmutableMap.<BlockFamily.Variant, BiConsumer<TofuBlockFamilyProvider, Block>>builder()
			.put(BlockFamily.Variant.BUTTON, TofuBlockFamilyProvider::button)
			.put(BlockFamily.Variant.DOOR, TofuBlockFamilyProvider::door)
			.put(BlockFamily.Variant.CHISELED, TofuBlockFamilyProvider::fullBlockVariant)
			.put(BlockFamily.Variant.CRACKED, TofuBlockFamilyProvider::fullBlockVariant)
			.put(BlockFamily.Variant.CUSTOM_FENCE, TofuBlockFamilyProvider::customFence)
			.put(BlockFamily.Variant.FENCE, TofuBlockFamilyProvider::fence)
			.put(BlockFamily.Variant.CUSTOM_FENCE_GATE, TofuBlockFamilyProvider::customFenceGate)
			.put(BlockFamily.Variant.FENCE_GATE, TofuBlockFamilyProvider::fenceGate)
			.put(BlockFamily.Variant.SIGN, TofuBlockFamilyProvider::sign)
			.put(BlockFamily.Variant.SLAB, TofuBlockFamilyProvider::slab)
			.put(BlockFamily.Variant.STAIRS, TofuBlockFamilyProvider::stairs)
			.put(BlockFamily.Variant.PRESSURE_PLATE, TofuBlockFamilyProvider::pressurePlate)
			.put(BlockFamily.Variant.TRAPDOOR, TofuBlockFamilyProvider::trapdoor)
			.put(BlockFamily.Variant.WALL, TofuBlockFamilyProvider::wall)
			.build();

	private final BlockModelGenerators generators;
	private final TextureMapping mapping;
	private final Map<ModelTemplate, ResourceLocation> models = Maps.newHashMap();
	@Nullable
	private BlockFamily family;
	@Nullable
	protected ResourceLocation fullBlock;
	private final Set<Block> skipGeneratingModelsFor = new HashSet<>();

	public TofuBlockFamilyProvider(BlockModelGenerators generators, TextureMapping p_388151_) {
		this.generators = generators;
		this.mapping = p_388151_;
	}

	public TofuBlockFamilyProvider fullBlock(Block p_388401_, ModelTemplate p_387245_) {
		this.fullBlock = p_387245_.create(p_388401_, this.mapping, generators.modelOutput);
		if (generators.fullBlockModelCustomGenerators.containsKey(p_388401_)) {
			generators.blockStateOutput
					.accept(
							generators.fullBlockModelCustomGenerators
									.get(p_388401_)
									.create(p_388401_, this.fullBlock, this.mapping, generators.modelOutput)
					);
		} else {
			generators.blockStateOutput.accept(createSimpleBlock(p_388401_, this.fullBlock));
		}

		return this;
	}

	public TofuBlockFamilyProvider donateModelTo(Block p_387771_, Block p_388618_) {
		ResourceLocation resourcelocation = ModelLocationUtils.getModelLocation(p_387771_);
		generators.blockStateOutput.accept(createSimpleBlock(p_388618_, resourcelocation));
		generators.itemModelOutput.copy(p_387771_.asItem(), p_388618_.asItem());
		this.skipGeneratingModelsFor.add(p_388618_);
		return this;
	}

	public TofuBlockFamilyProvider button(Block p_388929_) {
		ResourceLocation resourcelocation = ModelTemplates.BUTTON.create(p_388929_, this.mapping, generators.modelOutput);
		ResourceLocation resourcelocation1 = ModelTemplates.BUTTON_PRESSED.create(p_388929_, this.mapping, generators.modelOutput);
		generators.blockStateOutput.accept(createButton(p_388929_, resourcelocation, resourcelocation1));
		ResourceLocation resourcelocation2 = ModelTemplates.BUTTON_INVENTORY.create(p_388929_, this.mapping, generators.modelOutput);
		generators.registerSimpleItemModel(p_388929_, resourcelocation2);
		return this;
	}

	public TofuBlockFamilyProvider wall(Block p_387051_) {
		ResourceLocation resourcelocation = ModelTemplates.WALL_POST.create(p_387051_, this.mapping, generators.modelOutput);
		ResourceLocation resourcelocation1 = ModelTemplates.WALL_LOW_SIDE.create(p_387051_, this.mapping, generators.modelOutput);
		ResourceLocation resourcelocation2 = ModelTemplates.WALL_TALL_SIDE.create(p_387051_, this.mapping, generators.modelOutput);
		generators.blockStateOutput
				.accept(createWall(p_387051_, resourcelocation, resourcelocation1, resourcelocation2));
		ResourceLocation resourcelocation3 = ModelTemplates.WALL_INVENTORY.create(p_387051_, this.mapping, generators.modelOutput);
		generators.registerSimpleItemModel(p_387051_, resourcelocation3);
		return this;
	}

	public TofuBlockFamilyProvider customFence(Block p_387431_) {
		TextureMapping texturemapping = TextureMapping.customParticle(p_387431_);
		ResourceLocation resourcelocation = ModelTemplates.CUSTOM_FENCE_POST.create(p_387431_, texturemapping, generators.modelOutput);
		ResourceLocation resourcelocation1 = ModelTemplates.CUSTOM_FENCE_SIDE_NORTH
				.create(p_387431_, texturemapping, generators.modelOutput);
		ResourceLocation resourcelocation2 = ModelTemplates.CUSTOM_FENCE_SIDE_EAST.create(p_387431_, texturemapping, generators.modelOutput);
		ResourceLocation resourcelocation3 = ModelTemplates.CUSTOM_FENCE_SIDE_SOUTH
				.create(p_387431_, texturemapping, generators.modelOutput);
		ResourceLocation resourcelocation4 = ModelTemplates.CUSTOM_FENCE_SIDE_WEST.create(p_387431_, texturemapping, generators.modelOutput);
		generators.blockStateOutput
				.accept(
						createCustomFence(
								p_387431_, resourcelocation, resourcelocation1, resourcelocation2, resourcelocation3, resourcelocation4
						)
				);
		ResourceLocation resourcelocation5 = ModelTemplates.CUSTOM_FENCE_INVENTORY.create(p_387431_, texturemapping, generators.modelOutput);
		generators.registerSimpleItemModel(p_387431_, resourcelocation5);
		return this;
	}

	public TofuBlockFamilyProvider fence(Block p_387512_) {
		ResourceLocation resourcelocation = ModelTemplates.FENCE_POST.create(p_387512_, this.mapping, generators.modelOutput);
		ResourceLocation resourcelocation1 = ModelTemplates.FENCE_SIDE.create(p_387512_, this.mapping, generators.modelOutput);
		generators.blockStateOutput.accept(createFence(p_387512_, resourcelocation, resourcelocation1));
		ResourceLocation resourcelocation2 = ModelTemplates.FENCE_INVENTORY.create(p_387512_, this.mapping, generators.modelOutput);
		generators.registerSimpleItemModel(p_387512_, resourcelocation2);
		return this;
	}

	public TofuBlockFamilyProvider customFenceGate(Block p_387810_) {
		TextureMapping texturemapping = TextureMapping.customParticle(p_387810_);
		ResourceLocation resourcelocation = ModelTemplates.CUSTOM_FENCE_GATE_OPEN.create(p_387810_, texturemapping, generators.modelOutput);
		ResourceLocation resourcelocation1 = ModelTemplates.CUSTOM_FENCE_GATE_CLOSED
				.create(p_387810_, texturemapping, generators.modelOutput);
		ResourceLocation resourcelocation2 = ModelTemplates.CUSTOM_FENCE_GATE_WALL_OPEN
				.create(p_387810_, texturemapping, generators.modelOutput);
		ResourceLocation resourcelocation3 = ModelTemplates.CUSTOM_FENCE_GATE_WALL_CLOSED
				.create(p_387810_, texturemapping, generators.modelOutput);
		generators.blockStateOutput
				.accept(createFenceGate(p_387810_, resourcelocation, resourcelocation1, resourcelocation2, resourcelocation3, false));
		return this;
	}

	public TofuBlockFamilyProvider fenceGate(Block p_386624_) {
		ResourceLocation resourcelocation = ModelTemplates.FENCE_GATE_OPEN.create(p_386624_, this.mapping, generators.modelOutput);
		ResourceLocation resourcelocation1 = ModelTemplates.FENCE_GATE_CLOSED.create(p_386624_, this.mapping, generators.modelOutput);
		ResourceLocation resourcelocation2 = ModelTemplates.FENCE_GATE_WALL_OPEN.create(p_386624_, this.mapping, generators.modelOutput);
		ResourceLocation resourcelocation3 = ModelTemplates.FENCE_GATE_WALL_CLOSED.create(p_386624_, this.mapping, generators.modelOutput);
		generators.blockStateOutput
				.accept(createFenceGate(p_386624_, resourcelocation, resourcelocation1, resourcelocation2, resourcelocation3, true));
		this.generators.registerSimpleItemModel(p_386624_, resourcelocation1);
		return this;
	}

	public TofuBlockFamilyProvider pressurePlate(Block p_387753_) {
		ResourceLocation resourcelocation = ModelTemplates.PRESSURE_PLATE_UP.create(p_387753_, this.mapping, generators.modelOutput);
		ResourceLocation resourcelocation1 = ModelTemplates.PRESSURE_PLATE_DOWN.create(p_387753_, this.mapping, generators.modelOutput);
		generators.blockStateOutput.accept(createPressurePlate(p_387753_, resourcelocation, resourcelocation1));
		this.generators.registerSimpleItemModel(p_387753_, resourcelocation);
		return this;
	}

	public TofuBlockFamilyProvider sign(Block p_388436_) {
		if (this.family == null) {
			throw new IllegalStateException("Family not defined");
		} else {
			Block block = this.family.getVariants().get(BlockFamily.Variant.WALL_SIGN);
			ResourceLocation resourcelocation = ModelTemplates.PARTICLE_ONLY.create(p_388436_, this.mapping, generators.modelOutput);
			generators.blockStateOutput.accept(createSimpleBlock(p_388436_, resourcelocation));
			generators.blockStateOutput.accept(createSimpleBlock(block, resourcelocation));
			generators.registerSimpleFlatItemModel(p_388436_.asItem());
			return this;
		}
	}

	public TofuBlockFamilyProvider slab(Block p_388247_) {
		if (this.fullBlock == null) {
			throw new IllegalStateException("Full block not generated yet");
		} else {
			ResourceLocation resourcelocation = this.getOrCreateModel(ModelTemplates.SLAB_BOTTOM, p_388247_);
			ResourceLocation resourcelocation1 = this.getOrCreateModel(ModelTemplates.SLAB_TOP, p_388247_);
			generators.blockStateOutput
					.accept(createSlab(p_388247_, resourcelocation, resourcelocation1, this.fullBlock));
			generators.registerSimpleItemModel(p_388247_, resourcelocation);
			return this;
		}
	}

	public TofuBlockFamilyProvider stairs(Block p_386852_) {
		ResourceLocation resourcelocation = this.getOrCreateModel(ModelTemplates.STAIRS_INNER, p_386852_);
		ResourceLocation resourcelocation1 = this.getOrCreateModel(ModelTemplates.STAIRS_STRAIGHT, p_386852_);
		ResourceLocation resourcelocation2 = this.getOrCreateModel(ModelTemplates.STAIRS_OUTER, p_386852_);
		generators.blockStateOutput
				.accept(createStairs(p_386852_, resourcelocation, resourcelocation1, resourcelocation2));
		generators.registerSimpleItemModel(p_386852_, resourcelocation1);
		return this;
	}

	public TofuBlockFamilyProvider fullBlockVariant(Block p_386846_) {
		TexturedModel texturedmodel = generators.texturedModels.getOrDefault(p_386846_, TexturedModel.CUBE.get(p_386846_));
		ResourceLocation resourcelocation = texturedmodel.create(p_386846_, generators.modelOutput);
		generators.blockStateOutput.accept(createSimpleBlock(p_386846_, resourcelocation));
		return this;
	}

	public TofuBlockFamilyProvider door(Block p_388017_) {
		createDoor(p_388017_);
		return this;
	}

	private void createDoor(Block p_386982_) {
		TextureMapping texturemapping = TextureMapping.door(p_386982_);
		ResourceLocation resourcelocation = ModelTemplates.DOOR_BOTTOM_LEFT.extend().renderType("cutout").build().create(p_386982_, texturemapping, this.generators.modelOutput);
		ResourceLocation resourcelocation1 = ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.extend().renderType("cutout").build().create(p_386982_, texturemapping, this.generators.modelOutput);
		ResourceLocation resourcelocation2 = ModelTemplates.DOOR_BOTTOM_RIGHT.extend().renderType("cutout").build().create(p_386982_, texturemapping, this.generators.modelOutput);
		ResourceLocation resourcelocation3 = ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.extend().renderType("cutout").build().create(p_386982_, texturemapping, this.generators.modelOutput);
		ResourceLocation resourcelocation4 = ModelTemplates.DOOR_TOP_LEFT.extend().renderType("cutout").build().create(p_386982_, texturemapping, this.generators.modelOutput);
		ResourceLocation resourcelocation5 = ModelTemplates.DOOR_TOP_LEFT_OPEN.extend().renderType("cutout").build().create(p_386982_, texturemapping, this.generators.modelOutput);
		ResourceLocation resourcelocation6 = ModelTemplates.DOOR_TOP_RIGHT.extend().renderType("cutout").build().create(p_386982_, texturemapping, this.generators.modelOutput);
		ResourceLocation resourcelocation7 = ModelTemplates.DOOR_TOP_RIGHT_OPEN.extend().renderType("cutout").build().create(p_386982_, texturemapping, this.generators.modelOutput);

		this.generators.blockStateOutput
				.accept(
						this.generators.createDoor(
								p_386982_,
								resourcelocation,
								resourcelocation1,
								resourcelocation2,
								resourcelocation3,
								resourcelocation4,
								resourcelocation5,
								resourcelocation6,
								resourcelocation7
						)
				);
		this.generators.registerSimpleFlatItemModel(p_386982_.asItem());
	}

	public void trapdoor(Block p_388553_) {
		if (this.generators.nonOrientableTrapdoor.contains(p_388553_)) {
			createTrapdoor(p_388553_);
		} else {
			createOrientableTrapdoor(p_388553_);
		}
	}

	private void createTrapdoor(Block p_387551_) {
		TextureMapping texturemapping = TextureMapping.defaultTexture(p_387551_);
		ResourceLocation resourcelocation = ModelTemplates.TRAPDOOR_TOP.extend().renderType("cutout").build().create(p_387551_, texturemapping, this.generators.modelOutput);
		ResourceLocation resourcelocation1 = ModelTemplates.TRAPDOOR_BOTTOM.extend().renderType("cutout").build().create(p_387551_, texturemapping, this.generators.modelOutput);
		ResourceLocation resourcelocation2 = ModelTemplates.TRAPDOOR_OPEN.extend().renderType("cutout").build().create(p_387551_, texturemapping, this.generators.modelOutput);
		this.generators.blockStateOutput.accept(this.generators.createTrapdoor(p_387551_, resourcelocation, resourcelocation1, resourcelocation2));
		this.generators.registerSimpleItemModel(p_387551_, resourcelocation1);
	}

	private void createOrientableTrapdoor(Block p_388937_) {
		TextureMapping texturemapping = TextureMapping.defaultTexture(p_388937_);
		ResourceLocation resourcelocation = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.extend().renderType("cutout").build().create(p_388937_, texturemapping, this.generators.modelOutput);
		ResourceLocation resourcelocation1 = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.extend().renderType("cutout").build().create(p_388937_, texturemapping, this.generators.modelOutput);
		ResourceLocation resourcelocation2 = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.extend().renderType("cutout").build().create(p_388937_, texturemapping, this.generators.modelOutput);
		this.generators.blockStateOutput.accept(this.generators.createOrientableTrapdoor(p_388937_, resourcelocation, resourcelocation1, resourcelocation2));
		this.generators.registerSimpleItemModel(p_388937_, resourcelocation1);
	}

	public ResourceLocation getOrCreateModel(ModelTemplate p_387416_, Block p_388850_) {
		return this.models.computeIfAbsent(p_387416_, p_387666_ -> p_387666_.create(p_388850_, this.mapping, generators.modelOutput));
	}

	public TofuBlockFamilyProvider generateFor(BlockFamily p_387069_) {
		this.family = p_387069_;
		p_387069_.getVariants().forEach((p_388584_, p_388675_) -> {
			if (!this.skipGeneratingModelsFor.contains(p_388675_)) {
				BiConsumer<TofuBlockFamilyProvider, Block> biconsumer = SHAPE_CONSUMERS.get(p_388584_);
				if (biconsumer != null) {
					biconsumer.accept(this, p_388675_);
				}
			}
		});
		return this;
	}
}
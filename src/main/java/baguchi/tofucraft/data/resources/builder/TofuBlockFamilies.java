package baguchi.tofucraft.data.resources.builder;

import baguchi.tofucraft.registry.TofuBlocks;
import com.google.common.collect.Maps;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;

import java.util.Map;
import java.util.stream.Stream;

public class TofuBlockFamilies {
	private static final Map<Block, BlockFamily> MAP = Maps.newHashMap();

	public static final BlockFamily TOFU_STEM_PLANKS = familyBuilder(TofuBlocks.TOFU_STEM_PLANKS.get())
			.button(TofuBlocks.TOFU_STEM_BUTTON.get())
			.fence(TofuBlocks.TOFU_STEM_FENCE.get())
			.fenceGate(TofuBlocks.TOFU_STEM_FENCE_GATE.get())
			.pressurePlate(TofuBlocks.TOFU_STEM_PRESSURE_PLATE.get())
			.sign(TofuBlocks.TOFU_STEM_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_SIGN.get())
			.slab(TofuBlocks.TOFU_STEM_PLANKS_SLAB.get())
			.stairs(TofuBlocks.TOFU_STEM_PLANKS_STAIR.get())
			.door(TofuBlocks.TOFU_STEM_DOOR.get())
			.trapdoor(TofuBlocks.TOFU_STEM_TRAPDOOR.get())
			.getFamily();
	public static final BlockFamily LEEK_PLANKS = familyBuilder(TofuBlocks.LEEK_PLANKS.get())
			.button(TofuBlocks.LEEK_BUTTON.get())
			.fence(TofuBlocks.LEEK_FENCE.get())
			.fenceGate(TofuBlocks.LEEK_FENCE_GATE.get())
			.pressurePlate(TofuBlocks.LEEK_PRESSURE_PLATE.get())
			.sign(TofuBlocks.LEEK_SIGN.get(), TofuBlocks.LEEK_WALL_SIGN.get())
			.slab(TofuBlocks.LEEK_PLANKS_SLAB.get())
			.stairs(TofuBlocks.LEEK_PLANKS_STAIR.get())
			.getFamily();

	public static final BlockFamily LEEK_GREEN_PLANKS = familyBuilder(TofuBlocks.LEEK_GREEN_PLANKS.get())
			.button(TofuBlocks.LEEK_GREEN_BUTTON.get())
			.fence(TofuBlocks.LEEK_GREEN_FENCE.get())
			.fenceGate(TofuBlocks.LEEK_GREEN_FENCE_GATE.get())
			.pressurePlate(TofuBlocks.LEEK_GREEN_PRESSURE_PLATE.get())
			.sign(TofuBlocks.LEEK_GREEN_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_SIGN.get())
			.slab(TofuBlocks.LEEK_GREEN_PLANKS_SLAB.get())
			.stairs(TofuBlocks.LEEK_GREEN_PLANKS_STAIR.get())
			.door(TofuBlocks.LEEK_GREEN_DOOR.get())
			.trapdoor(TofuBlocks.LEEK_GREEN_TRAPDOOR.get())
			.getFamily();

	private static BlockFamily.Builder familyBuilder(Block baseBlock) {
		BlockFamily.Builder builder = new BlockFamily.Builder(baseBlock);
		BlockFamily blockfamily = MAP.put(baseBlock, builder.getFamily());
		if (blockfamily != null) {
			throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(baseBlock));
		} else {
			return builder;
		}
	}

	public static Stream<BlockFamily> getAllFamilies() {
		return MAP.values().stream();
	}
}
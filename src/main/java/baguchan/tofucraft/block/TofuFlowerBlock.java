package baguchan.tofucraft.block;

import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class TofuFlowerBlock extends FlowerBlock {
	public TofuFlowerBlock(Supplier<MobEffect> p_316154_, int p_332744_, Properties p_53514_) {
		super(p_316154_, p_332744_, p_53514_);
	}

	@Override
	protected boolean mayPlaceOn(BlockState p_51042_, BlockGetter p_51043_, BlockPos p_51044_) {
		return super.mayPlaceOn(p_51042_, p_51043_, p_51044_) || p_51042_.is(TofuTags.Blocks.TOFU_TERRAIN);
	}
}

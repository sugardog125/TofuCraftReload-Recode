package baguchi.tofucraft.item.tool;

import baguchi.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchi.tofucraft.registry.TofuToolMaterials;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class TofuShovelItem extends TofuDiggerItem implements IEnergyInsertable {
	public TofuShovelItem(TofuToolMaterials.TofuToolMaterial tofuItemTier, float p_362481_, float p_364182_, Properties properties) {
		super(tofuItemTier, BlockTags.MINEABLE_WITH_SHOVEL, p_362481_, p_364182_, properties);
	}


	@Override
	public int fill(ItemStack inst, int energy, boolean simulate) {
		int calculated = Math.min(energy, inst.getDamageValue());
		if (!simulate) {
			if (inst.getDamageValue() > 0) {
				inst.setDamageValue(Mth.clamp(inst.getDamageValue() - calculated, 0, inst.getMaxDamage()));
				return calculated * 5;
			}
		}
		return 0;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = level.getBlockState(blockpos);
		if (context.getClickedFace() == Direction.DOWN) {
			return InteractionResult.PASS;
		} else {
			Player player = context.getPlayer();
			BlockState blockstate1 = blockstate.getToolModifiedState(context, net.neoforged.neoforge.common.ItemAbilities.SHOVEL_FLATTEN, false);
			BlockState blockstate2 = null;
			if (blockstate1 != null && level.getBlockState(blockpos.above()).isAir()) {
				level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
				blockstate2 = blockstate1;
			} else if ((blockstate2 = blockstate.getToolModifiedState(context, net.neoforged.neoforge.common.ItemAbilities.SHOVEL_DOUSE, false)) != null) {
				if (!level.isClientSide()) {
					level.levelEvent(null, 1009, blockpos, 0);
				}

			}

			if (blockstate2 != null) {
				if (!level.isClientSide) {
					level.setBlock(blockpos, blockstate2, 11);
					level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, blockstate2));
					if (player != null) {
						context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
					}
				}

				return InteractionResult.SUCCESS;
			} else {
				return InteractionResult.PASS;
			}
		}
	}

	@Override
	public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
		return net.neoforged.neoforge.common.ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);
	}
}

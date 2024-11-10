package baguchi.tofucraft.item.tool;

import baguchi.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchi.tofucraft.registry.TofuToolMaterials;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import javax.annotation.Nullable;
import java.util.Optional;

public class TofuAxeItem extends TofuDiggerItem implements IEnergyInsertable {
	public TofuAxeItem(TofuToolMaterials.TofuToolMaterial tofuItemTier, float p_362481_, float p_364182_, Properties properties) {
		super(tofuItemTier, BlockTags.MINEABLE_WITH_AXE, p_362481_, p_364182_, properties);
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
		Player player = context.getPlayer();
		if (playerHasShieldUseIntent(context)) {
			return InteractionResult.PASS;
		} else {
			Optional<BlockState> optional = this.evaluateNewBlockState(level, blockpos, player, level.getBlockState(blockpos), context);
			if (optional.isEmpty()) {
				return InteractionResult.PASS;
			} else {
				ItemStack itemstack = context.getItemInHand();
				if (player instanceof ServerPlayer) {
					CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer) player, blockpos, itemstack);
				}

				level.setBlock(blockpos, optional.get(), 11);
				level.gameEvent(GameEvent.BLOCK_CHANGE, blockpos, GameEvent.Context.of(player, optional.get()));
				if (player != null) {
					itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
				}

				return InteractionResult.SUCCESS;
			}
		}
	}

	private static boolean playerHasShieldUseIntent(UseOnContext context) {
		Player player = context.getPlayer();
		return context.getHand().equals(InteractionHand.MAIN_HAND) && player.getOffhandItem().is(Items.SHIELD) && !player.isSecondaryUseActive();
	}

	private Optional<BlockState> evaluateNewBlockState(Level level, BlockPos pos, @Nullable Player player, BlockState state, UseOnContext p_40529_) {
		Optional<BlockState> optional = Optional.ofNullable(state.getToolModifiedState(p_40529_, net.neoforged.neoforge.common.ItemAbilities.AXE_STRIP, false));
		if (optional.isPresent()) {
			level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
			return optional;
		} else {
			Optional<BlockState> optional1 = Optional.ofNullable(state.getToolModifiedState(p_40529_, net.neoforged.neoforge.common.ItemAbilities.AXE_SCRAPE, false));
			if (optional1.isPresent()) {
				level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.levelEvent(player, 3005, pos, 0);
				return optional1;
			} else {
				Optional<BlockState> optional2 = Optional.ofNullable(state.getToolModifiedState(p_40529_, net.neoforged.neoforge.common.ItemAbilities.AXE_WAX_OFF, false));
				if (optional2.isPresent()) {
					level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
					level.levelEvent(player, 3004, pos, 0);
					return optional2;
				} else {
					return Optional.empty();
				}
			}
		}
	}

	@Override
	public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
		return net.neoforged.neoforge.common.ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility);
	}
}

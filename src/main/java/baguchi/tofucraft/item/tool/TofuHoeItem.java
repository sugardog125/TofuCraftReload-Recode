package baguchi.tofucraft.item.tool;

import baguchi.tofucraft.api.tfenergy.IEnergyInsertable;
import baguchi.tofucraft.registry.TofuToolMaterials;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
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

import java.util.function.Consumer;
import java.util.function.Predicate;

public class TofuHoeItem extends TofuDiggerItem implements IEnergyInsertable {
	public TofuHoeItem(TofuToolMaterials.TofuToolMaterial tofuItemTier, float p_362481_, float p_364182_, Properties properties) {
		super(tofuItemTier, BlockTags.MINEABLE_WITH_HOE, p_362481_, p_364182_, properties);
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
		BlockState toolModifiedState = level.getBlockState(blockpos).getToolModifiedState(context, net.neoforged.neoforge.common.ItemAbilities.HOE_TILL, false);
		Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = toolModifiedState == null ? null : Pair.of(ctx -> true, changeIntoState(toolModifiedState));
		if (pair == null) {
			return InteractionResult.PASS;
		} else {
			Predicate<UseOnContext> predicate = pair.getFirst();
			Consumer<UseOnContext> consumer = pair.getSecond();
			if (predicate.test(context)) {
				Player player = context.getPlayer();
				level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
				if (!level.isClientSide) {
					consumer.accept(context);
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

	public static Consumer<UseOnContext> changeIntoState(BlockState state) {
		return p_316061_ -> {
			p_316061_.getLevel().setBlock(p_316061_.getClickedPos(), state, 11);
			p_316061_.getLevel().gameEvent(GameEvent.BLOCK_CHANGE, p_316061_.getClickedPos(), GameEvent.Context.of(p_316061_.getPlayer(), state));
		};
	}

	@Override
	public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {
		return net.neoforged.neoforge.common.ItemAbilities.DEFAULT_HOE_ACTIONS.contains(itemAbility);
	}
}

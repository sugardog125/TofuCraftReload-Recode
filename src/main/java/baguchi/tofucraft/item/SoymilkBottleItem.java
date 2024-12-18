package baguchi.tofucraft.item;

import baguchi.tofucraft.attachment.SoyHealthAttachment;
import baguchi.tofucraft.registry.TofuAttachments;
import baguchi.tofucraft.registry.TofuEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SoymilkBottleItem extends Item {
	private final Holder<MobEffect> effect;

	private final Holder<MobEffect> secondEffect;

	public SoymilkBottleItem(Holder<MobEffect> effect, Holder<MobEffect> secondEffect, Item.Properties properties) {
		super(properties);
		this.effect = effect;
		this.secondEffect = secondEffect;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
		super.finishUsingItem(stack, level, livingEntity);
		SoyHealthAttachment cap = livingEntity.getData(TofuAttachments.SOY_HEALTH);
		if (!level.isClientSide) {
			if (level.getGameTime() > cap.getRemainTick() + 12000L) {
				cap.setSoyHealthLevel(livingEntity, cap.getSoyHealthLevel() + 1, true);

					if (cap.getSoyHealthLevel() > 4) {
						livingEntity.addEffect(new MobEffectInstance(this.getSecondEffect(), 24000, 0));
					}
					cap.setSoyHealthBaseLevel(1 + cap.getSoyHealthBaseLevel());
				}
			livingEntity.addEffect(new MobEffectInstance(TofuEffects.SOY_HEALTHY, 600 + 200 * cap.getSoyHealthLevel() + cap.getSoyHealthBaseLevel() * 40, 0));
			livingEntity.addEffect(new MobEffectInstance(this.getEffect(), 200 * cap.getSoyHealthLevel() + cap.getSoyHealthBaseLevel() * 40, 0));
			}
		if (livingEntity instanceof ServerPlayer) {
			ServerPlayer serverplayerentity = (ServerPlayer) livingEntity;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}
		if (stack.isEmpty()) {
			return new ItemStack(Items.GLASS_BOTTLE);
		} else {
			if (!(livingEntity instanceof Player) || !((Player) livingEntity).getAbilities().instabuild) {
				stack.shrink(1);
			}
			if (livingEntity instanceof Player && !((Player) livingEntity).getAbilities().instabuild) {
				ItemStack itemstack = new ItemStack(Items.GLASS_BOTTLE);
				Player playerentity = (Player) livingEntity;
				if (!playerentity.getInventory().add(itemstack)) {
					playerentity.drop(itemstack, false);
				}
			}
		}
		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, List<Component> components, TooltipFlag flag) {
		super.appendHoverText(stack, tooltipContext, components, flag);
		components.add(this.getEffect().value().getDisplayName().copy().withStyle(ChatFormatting.BLUE));
	}

	public Holder<MobEffect> getEffect() {
		return effect;
	}

	public Holder<MobEffect> getSecondEffect() {
		return secondEffect;
	}
}


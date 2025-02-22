package baguchi.tofucraft.item;

import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class SoyBallItem extends Item {
	public SoyBallItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
		ItemStack stack = p_41433_.getItemInHand(p_41434_);
		p_41433_.getCooldowns().addCooldown(stack, 60);
		return repairTofuArmor(p_41433_, stack) ? InteractionResult.SUCCESS : super.use(p_41432_, p_41433_, p_41434_);
	}

	public static boolean repairTofuArmor(LivingEntity p_371788_, ItemStack stack) {
		for (ItemStack itemstack : p_371788_.getArmorAndBodyArmorSlots()) {
			if (itemstack.is(TofuTags.Items.REPAIRABLE_TOFU) && itemstack.isDamaged()) {
				stack.shrink(1);
				p_371788_.playSound(SoundEvents.HONEYCOMB_WAX_ON);
				itemstack.setDamageValue(itemstack.getDamageValue() - 50);
				return true;
			}
		}

		for (ItemStack itemstack : p_371788_.getHandSlots()) {
			if (itemstack.is(TofuTags.Items.REPAIRABLE_TOFU) && itemstack.isDamaged()) {
				stack.shrink(1);
				p_371788_.playSound(SoundEvents.HONEYCOMB_WAX_ON);
				itemstack.setDamageValue(itemstack.getDamageValue() - 50);
				return true;
			}
		}

		return false;
	}

	@Override
	public void appendHoverText(ItemStack p_43094_, TooltipContext p_339613_, List<Component> p_43096_, TooltipFlag p_43097_) {
		super.appendHoverText(p_43094_, p_339613_, p_43096_, p_43097_);
		p_43096_.add(Component.translatable("tooltip.tofucraft.soyball"));

	}
}

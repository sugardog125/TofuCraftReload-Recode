package baguchi.tofucraft.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class HoneySoymilkBottleItem extends SoymilkBottleItem {
	public HoneySoymilkBottleItem(Properties properties) {
		super(MobEffects.REGENERATION, MobEffects.ABSORPTION, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, List<Component> components, TooltipFlag flag) {
		super.appendHoverText(stack, tooltipContext, components, flag);
		components.add(Component.translatable("item.tofucraft.soymilk_honey.desc").withStyle(ChatFormatting.GRAY));
	}
}

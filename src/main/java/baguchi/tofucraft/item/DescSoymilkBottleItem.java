package baguchi.tofucraft.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class DescSoymilkBottleItem extends SoymilkBottleItem {
	public DescSoymilkBottleItem(Holder<MobEffect> effect, Holder<MobEffect> secondEffect, Properties properties) {
		super(effect, secondEffect, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, List<Component> components, TooltipFlag flag) {
		super.appendHoverText(stack, tooltipContext, components, flag);
		components.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
	}
}

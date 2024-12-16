package baguchi.tofucraft.item.armor;

import baguchi.tofucraft.registry.TofuDataComponents;
import com.google.common.base.Suppliers;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.awt.*;
import java.util.List;
import java.util.function.Supplier;

public class BreakableTofuBootsItem extends BreakableTofuArmorItem {
	private final Supplier<ItemAttributeModifiers> defaultModifiers;

	public BreakableTofuBootsItem(ArmorMaterial tofuArmorMaterial, ArmorType type, float reduceFallDamage, Properties properties) {
		super(tofuArmorMaterial, type, properties);
		this.defaultModifiers = Suppliers.memoize(
				() -> {
					ItemAttributeModifiers.Builder itemattributemodifiers$builder = ItemAttributeModifiers.builder();
					EquipmentSlotGroup equipmentslotgroup = EquipmentSlotGroup.bySlot(type.getSlot());
					ResourceLocation resourcelocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
					itemattributemodifiers$builder.add(
							Attributes.FALL_DAMAGE_MULTIPLIER, new AttributeModifier(resourcelocation, (double) reduceFallDamage, AttributeModifier.Operation.ADD_MULTIPLIED_BASE), equipmentslotgroup
					);

					return itemattributemodifiers$builder.build();
				}
		);
	}

	@Override
	public ItemAttributeModifiers getDefaultAttributeModifiers(ItemStack stack) {
		return this.defaultModifiers.get();
	}


	public int getUnstability(ItemStack inst) {
		return inst.get(TofuDataComponents.UNSTABILITY) != null ? inst.get(TofuDataComponents.UNSTABILITY) : 0;
	}

	public int getFallDurability(ItemStack inst) {
		return inst.get(TofuDataComponents.MAX_FALL_DURABILITY) != null ? inst.get(TofuDataComponents.MAX_FALL_DURABILITY) : 1;
	}

	private boolean getShowState(ItemStack stack) {
		return getUnstability(stack) != 0;
	}

	@Override
	public boolean isBarVisible(ItemStack p_150899_) {
		return getShowState(p_150899_) || super.isBarVisible(p_150899_);
	}

	@Override
	public int getBarWidth(ItemStack stack) {
		return Math.round(13.0F - (float) getUnstability(stack) * 13.0F / (float) this.getFallDurability(stack));
	}

	@Override
	public int getBarColor(ItemStack p_150901_) {
		return getShowState(p_150901_) ? Color.white.getRGB() : super.getBarColor(p_150901_);
	}

	@Override
	public void appendHoverText(ItemStack p_43094_, TooltipContext p_339613_, List<net.minecraft.network.chat.Component> p_43096_, TooltipFlag p_43097_) {
		super.appendHoverText(p_43094_, p_339613_, p_43096_, p_43097_);
		p_43096_.add(Component.translatable("tooltip.tofucraft.tofu_boots", getUnstability(p_43094_), getFallDurability(p_43094_)));

	}
}

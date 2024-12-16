package baguchi.tofucraft.registry;

import baguchi.tofucraft.data.generator.TofuEquipmentAssets;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;

public interface TofuArmorMaterials {
	ArmorMaterial KINU = new ArmorMaterial(
			1,
			Util.make(new EnumMap<>(ArmorType.class), p_323384_ -> {
				p_323384_.put(ArmorType.BOOTS, 0);
				p_323384_.put(ArmorType.LEGGINGS, 0);
				p_323384_.put(ArmorType.CHESTPLATE, 0);
				p_323384_.put(ArmorType.HELMET, 0);
				p_323384_.put(ArmorType.BODY, 0);
			}),
			1,
			SoundEvents.ARMOR_EQUIP_LEATHER,
			0.0F,
			0.0F,
			TofuTags.Items.REPAIRABLE_TOFU,
			TofuEquipmentAssets.KINU);
	ArmorMaterial MOMEN = new ArmorMaterial(
			1,
			Util.make(new EnumMap<>(ArmorType.class), p_323384_ -> {
				p_323384_.put(ArmorType.BOOTS, 0);
				p_323384_.put(ArmorType.LEGGINGS, 0);
				p_323384_.put(ArmorType.CHESTPLATE, 0);
				p_323384_.put(ArmorType.HELMET, 0);
				p_323384_.put(ArmorType.BODY, 0);
			}),
			1,
			SoundEvents.ARMOR_EQUIP_LEATHER,
			0.0F,
			0.0F,
			TofuTags.Items.REPAIRABLE_TOFU,
			TofuEquipmentAssets.MOMEN);
	ArmorMaterial METAL = new ArmorMaterial(14, Util.make(new EnumMap<>(ArmorType.class), p_323378_ -> {
		p_323378_.put(ArmorType.BOOTS, 2);
		p_323378_.put(ArmorType.LEGGINGS, 5);
		p_323378_.put(ArmorType.CHESTPLATE, 6);
		p_323378_.put(ArmorType.HELMET, 2);
		p_323378_.put(ArmorType.BODY, 6);
	}), 11, SoundEvents.ARMOR_EQUIP_IRON, 0.0F, 0.0F, TofuTags.Items.TOFU_METAL_TOOL_MATERIAL, TofuEquipmentAssets.METAL);
	ArmorMaterial SOLID = new ArmorMaterial(10, Util.make(new EnumMap<>(ArmorType.class), p_323383_ -> {
		p_323383_.put(ArmorType.BOOTS, 1);
		p_323383_.put(ArmorType.LEGGINGS, 3);
		p_323383_.put(ArmorType.CHESTPLATE, 5);
		p_323383_.put(ArmorType.HELMET, 2);
		p_323383_.put(ArmorType.BODY, 5);
	}), 9, SoundEvents.ARMOR_EQUIP_GOLD, 0.0F, 0.0F, TofuTags.Items.TOFU_SOLID_TOOL_MATERIAL, TofuEquipmentAssets.SOLID);
	ArmorMaterial DIAMOND = new ArmorMaterial(34, Util.make(new EnumMap<>(ArmorType.class), p_323379_ -> {
		p_323379_.put(ArmorType.BOOTS, 4);
		p_323379_.put(ArmorType.LEGGINGS, 6);
		p_323379_.put(ArmorType.CHESTPLATE, 8);
		p_323379_.put(ArmorType.HELMET, 4);
		p_323379_.put(ArmorType.BODY, 18);
	}), 20, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.5F, 0.05F, TofuTags.Items.TOFU_DIAMOND_TOOL_MATERIAL, TofuEquipmentAssets.DIAMOND);

}
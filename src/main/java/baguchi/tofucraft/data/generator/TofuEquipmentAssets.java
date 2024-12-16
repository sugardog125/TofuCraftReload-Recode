package baguchi.tofucraft.data.generator;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.function.BiConsumer;

public interface TofuEquipmentAssets {
	ResourceKey<EquipmentAsset> KINU = createId("kinu");
	ResourceKey<EquipmentAsset> MOMEN = createId("momen");
	ResourceKey<EquipmentAsset> SOLID = createId("solid");
	ResourceKey<EquipmentAsset> METAL = createId("metal");
	ResourceKey<EquipmentAsset> DIAMOND = createId("diamond");

	static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> p_371586_) {
		p_371586_.accept(KINU, onlyHumanoid("kinu"));
		p_371586_.accept(MOMEN, onlyHumanoid("momen"));
		p_371586_.accept(SOLID, onlyHumanoid("solid"));
		p_371586_.accept(METAL, onlyHumanoid("metal"));
		p_371586_.accept(DIAMOND, onlyHumanoid("diamond"));
	}

	static ResourceKey<EquipmentAsset> createId(String p_386630_) {
		return ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, p_386630_));
	}


	private static EquipmentClientInfo onlyHumanoid(String p_371738_) {
		return EquipmentClientInfo.builder().addHumanoidLayers(TofuCraftReload.prefix(p_371738_)).build();
	}
}

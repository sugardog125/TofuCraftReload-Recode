package baguchi.tofucraft.data.generator;

import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class TofuEquipmentModelProvider implements DataProvider {
	private final PackOutput.PathProvider pathProvider;

	public TofuEquipmentModelProvider(PackOutput p_371200_) {
		this.pathProvider = p_371200_.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
	}

	@Override
	public CompletableFuture<?> run(CachedOutput p_387304_) {
		Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> map = new HashMap<>();
		TofuEquipmentAssets.bootstrap((p_386976_, p_388942_) -> {
			if (map.putIfAbsent(p_386976_, p_388942_) != null) {
				throw new IllegalStateException("Tried to register equipment asset twice for id: " + p_386976_);
			}
		});
		return DataProvider.saveAll(p_387304_, EquipmentClientInfo.CODEC, this.pathProvider::json, map);
	}

	@Override
	public String getName() {
		return "Equipment Asset Definitions";
	}
}

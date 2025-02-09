package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.api.entity.TofunianVariant;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class TofuEntityDatas {
	public static final DeferredRegister<EntityDataSerializer<?>> ENTITY_DATAS = DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, TofuCraftReload.MODID);


	public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Holder<TofunianVariant>>> TOFUNIAN_VARIANT = ENTITY_DATAS.register("tofunian_variant", () -> EntityDataSerializer.forValueType(TofunianVariant.STREAM_CODEC));

}

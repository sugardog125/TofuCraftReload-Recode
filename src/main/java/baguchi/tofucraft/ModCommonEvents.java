package baguchi.tofucraft;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.KnownPack;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;

import java.util.Optional;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {

	public static final KnownPack CORE_PACK_INFO = new KnownPack(TofuCraftReload.MODID, "builtin/tofucraft_legacy", "1.0");
	private static final PackLocationInfo RESOURCE_PACK_INFO = new PackLocationInfo(
			"builtin/tofucraft_legacy", Component.literal("TofuCraft Classic"), PackSource.BUILT_IN, Optional.of(CORE_PACK_INFO)
	);
	private static final PackSelectionConfig SELECTION_CONFIG = new PackSelectionConfig(false, Pack.Position.TOP, false);

	@SubscribeEvent
	public static void addPackFinders(AddPackFindersEvent event) {
		if (event.getPackType() == PackType.CLIENT_RESOURCES) {
			var resourcePath = ModList.get().getModFileById(TofuCraftReload.MODID).getFile().findResource("tofucraft_legacy");
			var supplier = new PathPackResources.PathResourcesSupplier(resourcePath);

			var pack = createBuiltinResourcePack(supplier);

			event.addRepositorySource(packConsumer -> packConsumer.accept(pack));
		}

		event.addPackFinders(
				ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofucraft_extra"),

				// What kind of resources are contained within this pack
				// 'CLIENT_RESOURCES' for packs with client assets (resource packs)
				// 'SERVER_DATA' for packs with server data (data packs)
				PackType.SERVER_DATA,

				// Display name shown in the Experiments screen
				Component.literal("TofuCraftReload: Extra"),
				PackSource.FEATURE,
				TofuConfig.COMMON.enableExtra.get(),
				Pack.Position.TOP
		);
	}

	protected static Pack createBuiltinResourcePack(Pack.ResourcesSupplier p_249625_) {
		return Pack.readMetaAndCreate(RESOURCE_PACK_INFO, p_249625_, PackType.CLIENT_RESOURCES, SELECTION_CONFIG);
	}
}

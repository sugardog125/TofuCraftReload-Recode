package baguchi.tofucraft;

import baguchi.tofucraft.api.tfenergy.TofuEnergyMap;
import baguchi.tofucraft.client.ClientRegistrar;
import baguchi.tofucraft.event.CraftingEvents;
import baguchi.tofucraft.network.BossInfoPacket;
import baguchi.tofucraft.network.SaltFurnaceBitternPacket;
import baguchi.tofucraft.network.SaltFurnaceWaterPacket;
import baguchi.tofucraft.network.SoyMilkDrinkedPacket;
import baguchi.tofucraft.network.TFStorageSoymilkPacket;
import baguchi.tofucraft.registry.ModInteractionInformations;
import baguchi.tofucraft.registry.TofuAdvancements;
import baguchi.tofucraft.registry.TofuAttachments;
import baguchi.tofucraft.registry.TofuBiomeSources;
import baguchi.tofucraft.registry.TofuBiomes;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.registry.TofuBlockSetTypes;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuCarvers;
import baguchi.tofucraft.registry.TofuCreativeModeTabs;
import baguchi.tofucraft.registry.TofuDataComponents;
import baguchi.tofucraft.registry.TofuEffects;
import baguchi.tofucraft.registry.TofuEnchantmentEffects;
import baguchi.tofucraft.registry.TofuEntityDatas;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuFeatures;
import baguchi.tofucraft.registry.TofuFluidTypes;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuFoliagePlacerType;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuLootModifiers;
import baguchi.tofucraft.registry.TofuMenus;
import baguchi.tofucraft.registry.TofuParticleTypes;
import baguchi.tofucraft.registry.TofuPoiTypes;
import baguchi.tofucraft.registry.TofuProfessions;
import baguchi.tofucraft.registry.TofuRecipes;
import baguchi.tofucraft.registry.TofuSounds;
import baguchi.tofucraft.registry.TofuTags;
import com.google.common.collect.Maps;
import com.google.common.reflect.Reflection;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.Map;

@Mod(TofuCraftReload.MODID)
public class TofuCraftReload {
	public static final String MODID = "tofucraft";

	public static final String NETWORK_PROTOCOL = "2";

	public static final Logger LOGGER = LogManager.getLogger(TofuCraftReload.MODID);

	public TofuCraftReload(ModContainer modContainer, IEventBus modBus) {
		IEventBus forgeBus = NeoForge.EVENT_BUS;

		modBus.addListener(this::setup);
		modBus.addListener(this::setupPackets);

		Reflection.initialize(TofuTags.Items.class);


		TofuBlocks.BLOCKS.register(modBus);
		TofuItems.ITEMS.register(modBus);
		TofuItems.ITEMS.addAlias(prefix("sculk_bone_helmet"), prefix("tofu_diamond_helmet"));
		TofuItems.ITEMS.addAlias(prefix("sculk_bone_chestplate"), prefix("tofu_diamond_chestplate"));
		TofuItems.ITEMS.addAlias(prefix("sculk_bone_leggings"), prefix("tofu_diamond_leggings"));
		TofuItems.ITEMS.addAlias(prefix("sculk_bone_boots"), prefix("tofu_diamond_boots"));

		TofuEntityTypes.ENTITIES.register(modBus);
		TofuEnchantmentEffects.ENTITY_EFFECTS.register(modBus);
		TofuBlockEntitys.BLOCK_ENTITIES.register(modBus);
		TofuMenus.MENU_TYPES.register(modBus);
		TofuEffects.MOB_EFFECTS.register(modBus);
		TofuFluidTypes.FLUID_TYPES.register(modBus);
		TofuFluids.FLUIDS.register(modBus);
		modBus.addListener(TofuFluids::registerFluids);
		TofuCreativeModeTabs.CREATIVE_MODE_TABS.register(modBus);
		TofuRecipes.RECIPE_TYPES.register(modBus);
		TofuEntityDatas.ENTITY_DATAS.register(modBus);
		TofuDataComponents.DATA_COMPONENT_TYPES.register(modBus);
		TofuRecipes.RECIPE_SERIALIZERS.register(modBus);
		TofuPoiTypes.POI_TYPES.register(modBus);
		TofuProfessions.PROFESSIONS.register(modBus);
		TofuLootModifiers.LOOT_MODIFIERS.register(modBus);

		TofuFoliagePlacerType.FOLIAGE_PLACER_TYPE.register(modBus);
		TofuFeatures.FEATURES.register(modBus);
		TofuSounds.SOUND_EVENTS.register(modBus);
		TofuAttachments.ATTACHMENT_TYPES.register(modBus);
		TofuParticleTypes.PARTICLE_TYPES.register(modBus);
		TofuAdvancements.CRITERIONS_REGISTER.register(modBus);
		TofuCarvers.WORLD_CARVER.register(modBus);


		if (FMLEnvironment.dist == Dist.CLIENT) {
			modBus.addListener(ClientRegistrar::setup);
		}
		NeoForge.EVENT_BUS.register(new CraftingEvents());
		modContainer.registerConfig(ModConfig.Type.COMMON, TofuConfig.COMMON_SPEC);
		modBus.addListener(TofuCraftReload::registerBETypes);
	}

	private static void registerBETypes(BlockEntityTypeAddBlocksEvent event) {
		event.modify(BlockEntityType.SIGN,
				TofuBlocks.TOFU_STEM_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_SIGN.get(),
				TofuBlocks.LEEK_SIGN.get(), TofuBlocks.LEEK_WALL_SIGN.get(),
				TofuBlocks.LEEK_GREEN_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_SIGN.get());

		event.modify(BlockEntityType.HANGING_SIGN,
				TofuBlocks.TOFU_STEM_HANGING_SIGN.get(), TofuBlocks.TOFU_STEM_WALL_HANGING_SIGN.get(),
				TofuBlocks.LEEK_HANGING_SIGN.get(), TofuBlocks.LEEK_WALL_HANGING_SIGN.get(),
				TofuBlocks.LEEK_GREEN_HANGING_SIGN.get(), TofuBlocks.LEEK_GREEN_WALL_HANGING_SIGN.get());

		event.modify(BlockEntityType.BRUSHABLE_BLOCK, TofuBlocks.SUSPICIOUS_TOFU_TERRAIN.get());
	}

	private void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			Map<ResourceLocation, MultiNoiseBiomeSourceParameterList.Preset> map = Maps.newHashMap();
			map.putAll(Map.copyOf(MultiNoiseBiomeSourceParameterList.Preset.BY_NAME));
			map.put(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_world"), TofuBiomeSources.TOFU_WORLD_PRESET);
			MultiNoiseBiomeSourceParameterList.Preset.BY_NAME = map;

			TofuBlockSetTypes.init();
			TofuAdvancements.init();
			TofuItems.registerDispenserItem();
			TofuBlocks.flamableInit();
			TofuBiomes.init();
			TofuEnergyMap.init();
			ModInteractionInformations.init();

			FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;


			pot.addPlant(BuiltInRegistries.BLOCK.getKey(TofuBlocks.SAPLING_TOFU.get()), TofuBlocks.POTTED_TOFU_SAPLING);
			pot.addPlant(BuiltInRegistries.BLOCK.getKey(TofuBlocks.SAPLING_APRICOT.get()), TofuBlocks.POTTED_APRICOT_SAPLING);
			pot.addPlant(BuiltInRegistries.BLOCK.getKey(TofuBlocks.ZUNDATOFU_MUSHROOM.get()), TofuBlocks.POTTED_ZUNDA_TOFU_MUSHROOM);
			pot.addPlant(BuiltInRegistries.BLOCK.getKey(TofuBlocks.LEEK.get()), TofuBlocks.POTTED_LEEK);
			pot.addPlant(BuiltInRegistries.BLOCK.getKey(TofuBlocks.TOFU_FLOWER.get()), TofuBlocks.POTTED_TOFU_FLOWER);
		});
	}


	public void setupPackets(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar(MODID).versioned("1.0.0").optional();
		registrar.playBidirectional(SoyMilkDrinkedPacket.TYPE, SoyMilkDrinkedPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playBidirectional(SaltFurnaceWaterPacket.TYPE, SaltFurnaceWaterPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playBidirectional(SaltFurnaceBitternPacket.TYPE, SaltFurnaceBitternPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playBidirectional(TFStorageSoymilkPacket.TYPE, TFStorageSoymilkPacket.STREAM_CODEC, (handler, payload) -> handler.handle(handler, payload));
		registrar.playToClient(BossInfoPacket.Display.TYPE, BossInfoPacket.Display.STREAM_CODEC, BossInfoPacket.Display::execute);
		registrar.playToClient(BossInfoPacket.Remove.TYPE, BossInfoPacket.Remove.STREAM_CODEC, BossInfoPacket.Remove::execute);


	}


	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, name.toLowerCase(Locale.ROOT));
	}
}

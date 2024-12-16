package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

import java.util.Map;

public class TofuTrimMaterials {

	public static final ResourceKey<TrimMaterial> TOFU_METAL = registerKey("tofu_metal");
	public static final ResourceKey<TrimMaterial> TOFU_DIAMOND = registerKey("tofu_diamond");
	public static final ResourceKey<TrimMaterial> ZUNDA_RUBY = registerKey("zunda_ruby");

	private static ResourceKey<TrimMaterial> registerKey(String name) {
		return ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, name));
	}

	public static void bootstrap(BootstrapContext<TrimMaterial> context) {
		register(context, TOFU_METAL, TofuItems.TOFUMETAL.get(), Style.EMPTY.withColor(0xAAB9C2));
		register(context, TOFU_DIAMOND, TofuItems.TOFUDIAMOND.get(), Style.EMPTY.withColor(0x6CBEEB));
		register(context, ZUNDA_RUBY, TofuItems.ZUNDARUBY.get(), Style.EMPTY.withColor(0x39650D));

	}

	private static void register(BootstrapContext<TrimMaterial> p_371580_, ResourceKey<TrimMaterial> p_371417_, Item p_371230_, Style p_371405_) {
		register(p_371580_, p_371417_, p_371230_, p_371405_, Map.of());
	}

	private static void register(
			BootstrapContext<TrimMaterial> p_371763_,
			ResourceKey<TrimMaterial> p_371867_,
			Item p_371472_,
			Style p_371730_,
			Map<ResourceKey<EquipmentAsset>, String> p_388368_
	) {
		TrimMaterial trimmaterial = TrimMaterial.create(
				p_371867_.location().getPath(),
				p_371472_,
				Component.translatable(Util.makeDescriptionId("trim_material", p_371867_.location())).withStyle(p_371730_),
				p_388368_
		);
		p_371763_.register(p_371867_, trimmaterial);
	}


}
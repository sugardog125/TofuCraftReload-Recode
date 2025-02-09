package baguchi.tofucraft.data.resources.registries;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.api.entity.TofunianVariant;
import baguchi.tofucraft.registry.TofuBiomes;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class TofunianVariants {
	public static final ResourceKey<Registry<TofunianVariant>> TOFUNIAN_VARIANT_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofunian_variant"));

	public static final ResourceKey<TofunianVariant> NORMAL = createKey("normal");
	public static final ResourceKey<TofunianVariant> ZUNDA = createKey("zunda");
	public static final ResourceKey<TofunianVariant> WASTE_LAND = createKey("waste_land");
	public static final ResourceKey<TofunianVariant> DEFAULT = NORMAL;

	private static ResourceKey<TofunianVariant> createKey(String name) {
		return ResourceKey.create(TOFUNIAN_VARIANT_REGISTRY_KEY, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, name));
	}

	static void register(BootstrapContext<TofunianVariant> context, ResourceKey<TofunianVariant> key, String name, ResourceKey<Biome> biomeResourceKey) {
		register(context, key, name, HolderSet.direct(context.lookup(Registries.BIOME).getOrThrow(biomeResourceKey)));
	}

	static void register(BootstrapContext<TofunianVariant> context, ResourceKey<TofunianVariant> key, String name, TagKey<Biome> biomeTag) {
		register(context, key, name, context.lookup(Registries.BIOME).getOrThrow(biomeTag));
	}

	static void register(BootstrapContext<TofunianVariant> context, ResourceKey<TofunianVariant> key, String name, HolderSet<Biome> biomeHolderSet) {
		ResourceLocation resourcelocation = TofuCraftReload.prefix("entity/tofunian/cloth/" + name);
		context.register(key, new TofunianVariant(resourcelocation, biomeHolderSet));
	}

	public static Holder<TofunianVariant> getSpawnVariant(RegistryAccess p_332694_, Holder<Biome> p_332773_) {
		Registry<TofunianVariant> registry = p_332694_.lookupOrThrow(TOFUNIAN_VARIANT_REGISTRY_KEY);
		return registry.listElements()
				.filter(p_332674_ -> p_332674_.value().biomes().contains(p_332773_))
				.findFirst()
				.or(() -> registry.get(DEFAULT))
				.or(registry::getAny)
				.orElseThrow();
	}

	public static void bootstrap(BootstrapContext<TofunianVariant> context) {
		register(context, NORMAL, "normal", HolderSet.empty());
		register(context, ZUNDA, "zunda", TofuBiomes.ZUNDA_FOREST);
		register(context, WASTE_LAND, "waste_land", TofuTags.Biomes.HOT_VARIANT_TOFUNIAN);
	}
}
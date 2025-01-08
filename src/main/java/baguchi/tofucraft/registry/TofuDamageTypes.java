package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public interface TofuDamageTypes {
	ResourceKey<DamageType> ZUNDA = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "zunda"));
	ResourceKey<DamageType> FALLING_TOFU = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "falling_tofu"));
	ResourceKey<DamageType> SOY_SPORE = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "soy_spore"));
	ResourceKey<DamageType> SOY_SPLASH = ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "soy_splash"));

	static void bootstrap(BootstrapContext<DamageType> p_321708_) {
		p_321708_.register(ZUNDA, new DamageType("zunda", 0.0F));
		p_321708_.register(FALLING_TOFU, new DamageType("fallingTofu", 0.1F));
		p_321708_.register(SOY_SPORE, new DamageType("soy_spore", 0.1F));
		p_321708_.register(SOY_SPLASH, new DamageType("soy_splash", 0.1F));
	}
}

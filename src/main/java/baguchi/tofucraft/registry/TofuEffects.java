package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.effect.CoughEffect;
import baguchi.tofucraft.effect.RecoverEffect;
import baguchi.tofucraft.effect.SoyHealthyEffect;
import baguchi.tofucraft.effect.TofuEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TofuEffects {
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, TofuCraftReload.MODID);

	public static final Holder<MobEffect> SOY_HEALTHY = MOB_EFFECTS.register("soy_healthy", () -> new SoyHealthyEffect(MobEffectCategory.BENEFICIAL, 0xFFFFFF));
	public static final Holder<MobEffect> COUGH = MOB_EFFECTS.register("cough", () -> new CoughEffect(MobEffectCategory.HARMFUL, 0xFF7D7D));
	public static final Holder<MobEffect> SALT_BOOST = MOB_EFFECTS.register("salt_boost", () -> new TofuEffect(MobEffectCategory.BENEFICIAL, 0xFFFFFF).addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "salt_speed"), 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE).addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "salt_attack_speed"), 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
	public static final Holder<MobEffect> MISO_BOOST = MOB_EFFECTS.register("miso_boost", () -> new TofuEffect(MobEffectCategory.BENEFICIAL, 0xA97D26).addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "salt_speed"), 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE).addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "salt_attack_speed"), 0.1F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
	public static final Holder<MobEffect> HEART_RECOVER = MOB_EFFECTS.register("health_recover", () -> new RecoverEffect(MobEffectCategory.BENEFICIAL, 0xC7490A));

}

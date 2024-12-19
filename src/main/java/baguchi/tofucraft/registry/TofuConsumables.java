package baguchi.tofucraft.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;

import java.util.List;

public class TofuConsumables {
	public static final Consumable SOYMILK = defaultDrink()
			.build();

	public static final Consumable SOYMILK_HONEY = defaultDrink()
			.onConsume(new RemoveStatusEffectsConsumeEffect(MobEffects.POISON))
			.build();

	public static final Consumable SOYMILK_PALE = defaultDrink()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(TofuEffects.HEART_RECOVER, 1200)))
			.build();
	public static final Consumable SOYMILK_PALE_GLOW = defaultDrink()
			.onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(TofuEffects.HEART_RECOVER, 2400)))
			.build();

	public static final Consumable COUGH = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(
					List.of(new MobEffectInstance(TofuEffects.COUGH, 600, 0))
			))
			.build();

	public static final Consumable CHILI_FOOD = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(
					List.of(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 0))
			))
			.build();
	public static final Consumable HELL_CHILI_FOOD = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(
					List.of(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600 * 2, 0),
							new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600, 0))
			))
			.build();

	public static final Consumable HELL_FOOD = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(
					List.of(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0))
			))
			.build();
	public static final Consumable SOUL_FOOD = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(
					List.of(new MobEffectInstance(MobEffects.ABSORPTION, 600, 0))
			))
			.build();

	public static final Consumable CRIMSON_SOUP = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(
					List.of(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0),
							new MobEffectInstance(MobEffects.CONFUSION, 300, 0))
			))
			.build();

	public static final Consumable SOY_FOOD = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(
					List.of(new MobEffectInstance(TofuEffects.SOY_HEALTHY, 2400, 0))
			))
			.build();
	public static final Consumable MISO_FOOD = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(
					List.of(new MobEffectInstance(TofuEffects.MISO_BOOST, 2400, 0))
			))
			.build();

	public static final Consumable SALT_FOOD = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(
					List.of(new MobEffectInstance(TofuEffects.SALT_BOOST, 2400, 0))
			))
			.build();

	public static final Consumable SMALL_SALT_FOOD = defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(
					List.of(new MobEffectInstance(TofuEffects.SALT_BOOST, 600, 0))
			))
			.build();

	public static Consumable.Builder defaultFood() {
		return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true);
	}

	public static Consumable.Builder defaultDrink() {
		return Consumable.builder().consumeSeconds(1.6F).animation(ItemUseAnimation.DRINK).sound(SoundEvents.GENERIC_DRINK).hasConsumeParticles(false);
	}
}

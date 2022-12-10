package baguchan.tofucraft.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;

import java.util.List;
import java.util.Random;

public class RamuneSoymilkBottleItem extends SoymilkBottleItem {
	private final Random random = new Random();
	private List<MobEffect> effectList = null;

	public RamuneSoymilkBottleItem(Properties properties) {
		super(MobEffects.LUCK, MobEffects.LUCK, properties);
	}

	@Override
	public MobEffect getEffect() {
		return getRandomEffect();
	}

	@Override
	public MobEffect getSecondEffect() {
		return getRandomEffect();
	}

	private MobEffect getRandomEffect() {
		if (effectList == null) {
			effectList = BuiltInRegistries.MOB_EFFECT
					.stream()
					.filter(mobEffect -> mobEffect.getCategory() != MobEffectCategory.HARMFUL)
					.filter(mobEffect -> mobEffect != MobEffects.HEAL)
					.toList();
		}
		return effectList.get(random.nextInt(effectList.size()));
	}
}

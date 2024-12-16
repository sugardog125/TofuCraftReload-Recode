package baguchi.tofucraft.entity.behaviors;

import baguchi.tofucraft.entity.FukumameThrower;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.behavior.Behavior;

public class EatFukumame<E extends FukumameThrower> extends Behavior<E> {
	protected int ticks;

	public EatFukumame() {
		super(ImmutableMap.of());
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel p_22538_, E p_22539_) {
		return p_22539_.getFukumameCount() > 0 && (p_22539_.getTarget() == null && (p_22539_.getHealth() < p_22539_.getMaxHealth()) || p_22539_.isOnFire() && !p_22539_.hasEffect(MobEffects.FIRE_RESISTANCE));
	}

	@Override
	protected boolean canStillUse(ServerLevel p_22545_, E p_22546_, long p_22547_) {
		return this.ticks > 0;
	}

	@Override
	protected void start(ServerLevel p_22540_, E p_22541_, long p_22542_) {
		super.start(p_22540_, p_22541_, p_22542_);
		p_22541_.setCharge(true);
		p_22541_.playSound(SoundEvents.BARREL_OPEN);
		this.ticks = 60;
	}

	@Override
	protected void stop(ServerLevel p_22548_, E p_22549_, long p_22550_) {
		super.stop(p_22548_, p_22549_, p_22550_);
		p_22549_.setCharge(false);
	}

	@Override
	protected void tick(ServerLevel p_22551_, E p_22552_, long p_22553_) {
		super.tick(p_22551_, p_22552_, p_22553_);

		if (--this.ticks == 10) {
			p_22552_.swing(InteractionHand.MAIN_HAND);
			p_22552_.setCharge(false);
			p_22552_.playSound(SoundEvents.GENERIC_EAT.value());
			p_22552_.eatFukumame();
		}

	}
}

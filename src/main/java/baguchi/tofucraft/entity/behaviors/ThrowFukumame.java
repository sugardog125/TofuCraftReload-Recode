package baguchi.tofucraft.entity.behaviors;

import baguchi.tofucraft.entity.FukumameThrower;
import baguchi.tofucraft.entity.projectile.NetherFukumameEntity;
import baguchi.tofucraft.registry.TofuEntityTypes;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class ThrowFukumame<E extends FukumameThrower> extends Behavior<E> {
	protected int ticks;

	public ThrowFukumame() {
		super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED));
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel p_22538_, E p_22539_) {
		return p_22539_.getFukumameCount() > 0 && p_22539_.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).isPresent();
	}

	@Override
	protected boolean canStillUse(ServerLevel p_22545_, E p_22546_, long p_22547_) {
		return p_22546_.getFukumameCount() > 0 && p_22546_.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET).isPresent();
	}

	@Override
	protected void start(ServerLevel p_22540_, E p_22541_, long p_22542_) {
		super.start(p_22540_, p_22541_, p_22542_);
		p_22541_.setCharge(true);
	}

	@Override
	protected void stop(ServerLevel p_22548_, E p_22549_, long p_22550_) {
		super.stop(p_22548_, p_22549_, p_22550_);
		p_22549_.setCharge(false);
	}

	@Override
	protected void tick(ServerLevel p_22551_, E p_22552_, long p_22553_) {
		super.tick(p_22551_, p_22552_, p_22553_);
		Optional<LivingEntity> optional = p_22552_.getBrain().getMemory(MemoryModuleType.ATTACK_TARGET);

		if (optional.isPresent()) {
			LivingEntity livingEntity = optional.get();
			if (p_22552_.hasLineOfSight(optional.get()) && p_22552_.distanceToSqr(optional.get()) < 64F) {
				if (++this.ticks == 80) {
					p_22552_.swing(InteractionHand.MAIN_HAND);
					p_22552_.setCharge(false);
					p_22552_.setFukumameCount(Math.max(p_22552_.getFukumameCount() - 1, 0));
					Vec3 vec3 = livingEntity.getDeltaMovement();
					double d0 = livingEntity.getX() + vec3.x - p_22552_.getX();
					double d1 = livingEntity.getEyeY() - p_22552_.getEyeY() - 0.1F;
					double d2 = livingEntity.getZ() + vec3.z - p_22552_.getZ();
					double d3 = Math.sqrt(d0 * d0 + d2 * d2);
					for (int i = 0; i < 4; i++) {
						NetherFukumameEntity entity = TofuEntityTypes.NETHER_FUKUMAME.get().create(p_22551_, EntitySpawnReason.SPAWN_ITEM_USE);
						entity.setXRot(entity.getXRot() - -20.0F);
						entity.shoot(d0, d1 + d3 * 0.3D, d2, 0.75F, 8.0F);
						entity.setOwner(p_22552_);

						entity.setPos(p_22552_.position().x(), p_22552_.getEyeY() - 0.1F, p_22552_.position().z());
						p_22551_.addFreshEntity(entity);
					}
					p_22552_.playSound(SoundEvents.SNOWBALL_THROW);
				}
				if (this.ticks > 100) {
					this.ticks = 0;
					p_22552_.setCharge(true);
				}
			} else {
				p_22552_.getBrain().setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(optional.get(), 0.8F, 6));
			}
		} else {
			this.ticks = 0;
			p_22552_.setCharge(true);
		}
	}
}

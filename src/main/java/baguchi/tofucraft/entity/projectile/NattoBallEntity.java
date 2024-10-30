package baguchi.tofucraft.entity.projectile;

import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class NattoBallEntity extends ThrowableProjectile {

	public NattoBallEntity(EntityType<? extends NattoBallEntity> p_36892_, Level p_36893_) {
		super(p_36892_, p_36893_);
	}

	public NattoBallEntity(Level worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.NATTO_STRNIG.get(), throwerIn.getX(), throwerIn.getEyeY(), throwerIn.getZ(), worldIn);
		this.setOwner(throwerIn);
	}

	protected boolean canHitEntity(Entity p_37250_) {
		return false;
	}

	protected void onHit(HitResult p_36913_) {
		super.onHit(p_36913_);
		if (p_36913_.getType() != HitResult.Type.ENTITY || !this.ownedBy(((EntityHitResult) p_36913_).getEntity())) {
			if (!this.level().isClientSide) {
				List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));
				AreaEffectCloud areaeffectcloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
				Entity entity = this.getOwner();
				if (entity instanceof LivingEntity) {
					areaeffectcloud.setOwner((LivingEntity) entity);
				}

				areaeffectcloud.setParticle(TofuParticleTypes.SIMPLE_STINKE.get());
				areaeffectcloud.setRadius(3.0F);
				areaeffectcloud.setDuration(200);
				areaeffectcloud.addEffect(new MobEffectInstance(MobEffects.HARM, 1, 1));
				if (!list.isEmpty()) {
					for (LivingEntity livingentity : list) {
						double d0 = this.distanceToSqr(livingentity);
						if (d0 < 16.0D) {
							areaeffectcloud.setPos(livingentity.getX(), livingentity.getY(), livingentity.getZ());
							break;
						}
					}
				}

				this.level().addFreshEntity(areaeffectcloud);
				this.discard();
			}

		}
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder p_326003_) {

	}

	public boolean isPickable() {
		return false;
	}

	protected void defineSynchedData() {
	}

	protected ParticleOptions getTrailParticle() {
		return TofuParticleTypes.SIMPLE_STINKE.get();
	}

	protected boolean shouldBurn() {
		return false;
	}
}

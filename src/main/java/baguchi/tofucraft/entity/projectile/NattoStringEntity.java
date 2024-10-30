package baguchi.tofucraft.entity.projectile;

import baguchi.tofucraft.entity.effect.NattoCobWebEntity;
import baguchi.tofucraft.registry.TofuEntityTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class NattoStringEntity extends ThrowableProjectile {
	public float damage = 2;

	public NattoStringEntity(EntityType<? extends ThrowableProjectile> p_37466_, Level p_37467_) {
		super(p_37466_, p_37467_);
	}

	public NattoStringEntity(Level worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.NATTO_STRNIG.get(), throwerIn.getX(), throwerIn.getEyeY(), throwerIn.getZ(), worldIn);
		this.setOwner(throwerIn);
	}

	protected void defineSynchedData() {
	}

	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		playSound(SoundEvents.SLIME_JUMP_SMALL, 0.8F, 0.8F + this.level().random.nextFloat() * 0.4F);

		if (!this.level().isClientSide) {
			NattoCobWebEntity entity = new NattoCobWebEntity(this.level(), getX(), getY(), getZ());
			this.level().addFreshEntity(entity);
			this.discard();
		}
	}


	protected boolean canHitEntity(Entity p_37250_) {
		return false;
	}

	protected void onHitBlock(BlockHitResult p_37406_) {
		super.onHitBlock(p_37406_);
		playSound(SoundEvents.SLIME_JUMP_SMALL, 0.8F, 0.8F + this.level().random.nextFloat() * 0.4F);
		if (!this.level().isClientSide) {
			//I don't use it particularly, but it's something.↓
			this.level().broadcastEntityEvent(this, (byte) 80);
			NattoCobWebEntity entity = new NattoCobWebEntity(this.level(), p_37406_.getLocation().x, p_37406_.getLocation().y, p_37406_.getLocation().z);
			entity.setAttachFace(p_37406_.getDirection());
			this.level().addFreshEntity(entity);
			this.discard();
		}
	}

	public void addAdditionalSaveData(CompoundTag p_37222_) {
		super.addAdditionalSaveData(p_37222_);
		p_37222_.putFloat("Damage", (byte) this.damage);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder p_326003_) {

	}

	public void readAdditionalSaveData(CompoundTag p_37220_) {
		super.readAdditionalSaveData(p_37220_);
		if (p_37220_.contains("Damage", 99)) {
			this.damage = p_37220_.getFloat("Damage");
		}

	}
}

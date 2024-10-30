package baguchi.tofucraft.entity.projectile;

import baguchi.tofucraft.registry.TofuDamageSource;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class SoyballEntity extends ThrowableProjectile {
	public float damage = 2;
	@Nullable
	protected ItemStack firedFromWeapon = null;

	public SoyballEntity(EntityType<? extends SoyballEntity> p_i50154_1_, Level p_i50154_2_) {
		super(p_i50154_1_, p_i50154_2_);
	}

	public SoyballEntity(Level worldIn, LivingEntity thrower) {
		super(TofuEntityTypes.SOYBALL.get(), thrower.getX(), thrower.getEyeY(), thrower.getZ(), worldIn);
		this.setOwner(thrower);
	}

	public SoyballEntity(Level level, LivingEntity thrower, ItemStack stack) {
		super(TofuEntityTypes.SOYBALL.get(), thrower.getX(), thrower.getEyeY(), thrower.getZ(), level);
		this.setOwner(thrower);
		this.firedFromWeapon = stack.copy();
	}

	public SoyballEntity(Level worldIn, double x, double y, double z) {
		super(TofuEntityTypes.SOYBALL.get(), x, y, z, worldIn);

	}

	public SoyballEntity(EntityType<? extends SoyballEntity> p_i50154_1_, Level worldIn, double x, double y, double z) {
		super(p_i50154_1_, x, y, z, worldIn);
	}

	public SoyballEntity(EntityType<? extends SoyballEntity> entityType, LivingEntity thrower, Level level) {
		super(entityType, thrower.getX(), thrower.getY(), thrower.getZ(), level);
		this.setOwner(thrower);
	}

	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			double d0 = 0.08D;
			for (int i = 0; i < 6; i++)
				this.level().addParticle(TofuParticleTypes.SOYMILK_SPLASH.get(), getX(), getY(), getZ(), (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D);
		}
	}

	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		Entity entity = p_37404_.getEntity();
		DamageSource damagesource = this.damageSources().source(TofuDamageSource.SOY_SPLASH, this, this.getOwner());
		double d0 = this.damage;
		if (this.getWeaponItem() != null && this.level() instanceof ServerLevel serverlevel) {
			d0 = (double) EnchantmentHelper.modifyDamage(serverlevel, this.getWeaponItem(), entity, damagesource, (float) d0);
		}
		if (this.getOwner() instanceof LivingEntity livingEntity && entity instanceof LivingEntity target && livingEntity.canAttack(target)) {
			return;
		}
		if (this.level() instanceof ServerLevel serverLevel) {
			if (entity.hurtServer(serverLevel, damagesource, (float) d0) && this.level() instanceof ServerLevel serverlevel) {
				EnchantmentHelper.doPostAttackEffects(serverlevel, entity, damagesource);
				if (!this.level().isClientSide) {
					this.level().broadcastEntityEvent(this, (byte) 3);
					this.discard();
				}
			}
		}
	}

	protected void hitBlockEnchantmentEffects(ServerLevel p_345462_, BlockHitResult p_345204_, ItemStack p_345083_) {
		Vec3 vec3 = p_345204_.getBlockPos().clampLocationWithin(p_345204_.getLocation());
		EnchantmentHelper.onHitBlock(
				p_345462_,
				p_345083_,
				this.getOwner() instanceof LivingEntity livingentity ? livingentity : null,
				this,
				null,
				vec3,
				p_345462_.getBlockState(p_345204_.getBlockPos()),
				p_348569_ -> this.firedFromWeapon = null
		);
	}

	@Override
	public ItemStack getWeaponItem() {
		return this.firedFromWeapon;
	}

	protected void onHit(HitResult p_37406_) {
		super.onHit(p_37406_);
		playSound(SoundEvents.GENERIC_SPLASH, 0.8F, 0.8F + this.level().random.nextFloat() * 0.4F);
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);

		ItemStack itemstack = this.getWeaponItem();
		if (this.level() instanceof ServerLevel serverlevel && itemstack != null) {
			this.hitBlockEnchantmentEffects(serverlevel, result, itemstack);
		}
		if (!this.level().isClientSide) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}

	public void addAdditionalSaveData(CompoundTag p_37222_) {
		super.addAdditionalSaveData(p_37222_);
		p_37222_.putFloat("Damage", (byte) this.damage);
		if (this.firedFromWeapon != null) {
			p_37222_.put("weapon", this.firedFromWeapon.save(this.registryAccess(), new CompoundTag()));
		}
	}

	public void readAdditionalSaveData(CompoundTag p_37220_) {
		super.readAdditionalSaveData(p_37220_);
		if (p_37220_.contains("Damage", 99)) {
			this.damage = p_37220_.getFloat("Damage");
		}
		if (p_37220_.contains("weapon", 10)) {
			this.firedFromWeapon = ItemStack.parse(this.registryAccess(), p_37220_.getCompound("weapon")).orElse(null);
		} else {
			this.firedFromWeapon = null;
		}
	}
}
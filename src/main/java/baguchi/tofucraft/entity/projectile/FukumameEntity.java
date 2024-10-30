package baguchi.tofucraft.entity.projectile;

import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuSounds;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
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

public class FukumameEntity extends ThrowableProjectile {
	public float damage = 1;
	private int totalHits;
	@Nullable
	protected ItemStack firedFromWeapon = null;

	public FukumameEntity(EntityType<? extends FukumameEntity> p_i50154_1_, Level p_i50154_2_) {
		super(p_i50154_1_, p_i50154_2_);
	}

	public FukumameEntity(Level worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.FUKUMAME.get(), throwerIn.getX(), throwerIn.getEyeY(), throwerIn.getZ(), worldIn);
		this.setOwner(throwerIn);
	}

	public FukumameEntity(Level worldIn, LivingEntity throwerIn, ItemStack stack) {
		super(TofuEntityTypes.FUKUMAME.get(), throwerIn.getX(), throwerIn.getEyeY(), throwerIn.getZ(), worldIn);
		this.setOwner(throwerIn);
		this.firedFromWeapon = stack.copy();
	}

	public FukumameEntity(Level worldIn, double x, double y, double z) {
		super(TofuEntityTypes.FUKUMAME.get(), x, y, z, worldIn);

	}

	public FukumameEntity(EntityType<? extends FukumameEntity> p_i50154_1_, Level worldIn, double x, double y, double z) {
		super(p_i50154_1_, x, y, z, worldIn);
	}

	public FukumameEntity(EntityType<? extends FukumameEntity> entityType, LivingEntity throwerIn, Level worldIn) {
		super(entityType, throwerIn.getX(), throwerIn.getY(), throwerIn.getZ(), worldIn);
		this.setOwner(throwerIn);
	}

	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			double d0 = 0.08D;
			for (int i = 0; i < 6; i++)
				this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(TofuItems.SEEDS_SOYBEANS.get())), getX(), getY(), getZ(), (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D);
		}
	}

	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
		Entity entity = p_37404_.getEntity();
		DamageSource damagesource = this.damageSources().thrown(this, this.getOwner());
		double d0 = this.damage;
		if (this.level() instanceof ServerLevel serverlevel) {
			if (this.getWeaponItem() != null) {
				d0 = (double) EnchantmentHelper.modifyDamage(serverlevel, this.getWeaponItem(), entity, damagesource, (float) d0);
			}
			if (entity.hurtServer(serverlevel, damagesource, (float) d0)) {
				EnchantmentHelper.doPostAttackEffects(serverlevel, entity, damagesource);
			}
		}

		entity.invulnerableTime = 5;
		if (!this.level().isClientSide) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
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
		playSound(TofuSounds.SOYBEAN_CRACK.get(), 0.8F, 0.8F + this.level().random.nextFloat() * 0.4F);
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
		p_37222_.putInt("TotalHits", this.totalHits);
		if (this.firedFromWeapon != null) {
			p_37222_.put("weapon", this.firedFromWeapon.save(this.registryAccess(), new CompoundTag()));
		}
	}

	public void readAdditionalSaveData(CompoundTag p_37220_) {
		super.readAdditionalSaveData(p_37220_);
		if (p_37220_.contains("Damage", 99)) {
			this.damage = p_37220_.getFloat("Damage");
		}
		this.totalHits = p_37220_.getInt("TotalHits");
		if (p_37220_.contains("weapon", 10)) {
			this.firedFromWeapon = ItemStack.parse(this.registryAccess(), p_37220_.getCompound("weapon")).orElse(null);
		} else {
			this.firedFromWeapon = null;
		}
	}
}
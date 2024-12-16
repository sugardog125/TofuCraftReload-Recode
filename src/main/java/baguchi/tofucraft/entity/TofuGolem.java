package baguchi.tofucraft.entity;

import baguchi.tofucraft.entity.goal.DefendTofuVillageTargetGoal;
import baguchi.tofucraft.entity.goal.MoveBackToTofuVillageGoal;
import baguchi.tofucraft.entity.projectile.SoyballEntity;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class TofuGolem extends AbstractGolem implements NeutralMob, RangedAttackMob {
	protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID = SynchedEntityData.defineId(TofuGolem.class, EntityDataSerializers.BYTE);
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	private int remainingPersistentAngerTime;
	@javax.annotation.Nullable
	private UUID persistentAngerTarget;
	public AnimationState spitAnimationState = new AnimationState();
	public AnimationState idleAnimationState = new AnimationState();

	public int spitAnimationTick;

	public TofuGolem(EntityType<? extends TofuGolem> p_27508_, Level p_27509_) {
		super(p_27508_, p_27509_);
		this.moveControl = new FlyingMoveControl(this, 20, true);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 30.0D).add(Attributes.FOLLOW_RANGE, 20F).add(Attributes.MOVEMENT_SPEED, 0.11D).add(Attributes.FLYING_SPEED, 0.11D).add(Attributes.ATTACK_KNOCKBACK, 0.6F).add(Attributes.KNOCKBACK_RESISTANCE, 0.85D).add(Attributes.ARMOR, 8.0F).add(Attributes.ATTACK_DAMAGE, 10.0D);
	}

	protected int decreaseAirSupply(int p_28882_) {
		return p_28882_;
	}


	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.1, 15, 20, 10.0F));
		this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 1.0D, 32.0F));
		this.goalSelector.addGoal(2, new MoveBackToTofuVillageGoal(this, 1.0D, false));
		//this.goalSelector.addGoal(4, new GolemRandomStrollInVillageGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Mob.class, 6.0F));
		this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new DefendTofuVillageTargetGoal(this));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_, level) -> {
			return p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper);
		}));
		this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_FLAGS_ID, (byte) 0);
	}

	@Override
	protected InteractionResult mobInteract(Player p_28861_, InteractionHand p_28862_) {
		ItemStack itemstack = p_28861_.getItemInHand(p_28862_);
		if (!itemstack.is(TofuItems.TOFUISHI.get())) {
			return InteractionResult.PASS;
		} else {
			float f = this.getHealth();
			this.heal(20.0F);
			if (this.getHealth() == f) {
				return InteractionResult.PASS;
			} else {
				float f1 = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
				this.playSound(SoundEvents.IRON_GOLEM_REPAIR, 1.0F, f1);
				itemstack.consume(1, p_28861_);
				return InteractionResult.TRY_WITH_EMPTY_HAND;
			}
		}
	}
	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide()) {
			if (!this.spitAnimationState.isStarted()) {
				this.idleAnimationState.startIfStopped(this.tickCount);
			}

			if (this.spitAnimationState.isStarted() && ++this.spitAnimationTick > 20) {
				this.spitAnimationState.stop();
			}
		}
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (!this.level().isClientSide) {
			this.updatePersistentAnger((ServerLevel) this.level(), true);
		}
	}



	@Override
	public void handleEntityEvent(byte p_28844_) {
		if (p_28844_ == 4) {
			this.spitAnimationState.start(this.tickCount);
			this.spitAnimationTick = 0;
		} else {
			super.handleEntityEvent(p_28844_);
		}
	}


	public void addAdditionalSaveData(CompoundTag p_28867_) {
		super.addAdditionalSaveData(p_28867_);
		p_28867_.putBoolean("PlayerCreated", this.isPlayerCreated());
		this.addPersistentAngerSaveData(p_28867_);
	}

	public void readAdditionalSaveData(CompoundTag p_28857_) {
		super.readAdditionalSaveData(p_28857_);
		this.setPlayerCreated(p_28857_.getBoolean("PlayerCreated"));
		this.readPersistentAngerSaveData(this.level(), p_28857_);
	}

	public boolean isPlayerCreated() {
		return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
	}

	public void setPlayerCreated(boolean p_28888_) {
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		if (p_28888_) {
			this.entityData.set(DATA_FLAGS_ID, (byte) (b0 | 1));
		} else {
			this.entityData.set(DATA_FLAGS_ID, (byte) (b0 & -2));
		}

	}

	@Override
	protected PathNavigation createNavigation(Level p_218342_) {
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_218342_);
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(true);
		return flyingpathnavigation;
	}

	@Override
	public void travel(Vec3 p_218382_) {
		if (this.isControlledByLocalInstance()) {
			if (this.isInWater()) {
				this.moveRelative(0.02F, p_218382_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.8F));
			} else if (this.isInLava()) {
				this.moveRelative(0.02F, p_218382_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
			} else {
				this.moveRelative(this.getSpeed(), p_218382_);
				this.move(MoverType.SELF, this.getDeltaMovement());
				this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.91F));
			}
		}

		this.calculateEntityAnimation(false);
	}


	@Override
	public boolean causeFallDamage(float p_148989_, float p_148990_, DamageSource p_148991_) {
		return false;
	}

	@Override
	protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) {
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
	}

	@Override
	public void setRemainingPersistentAngerTime(int p_28859_) {
		this.remainingPersistentAngerTime = p_28859_;
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.remainingPersistentAngerTime;
	}

	@Override
	public void setPersistentAngerTarget(@javax.annotation.Nullable UUID p_28855_) {
		this.persistentAngerTarget = p_28855_;
	}

	@Nullable
	@Override
	public UUID getPersistentAngerTarget() {
		return this.persistentAngerTarget;
	}

	@Override
	public boolean removeWhenFarAway(double p_27519_) {
		return false;
	}

	@Override
	public void performRangedAttack(LivingEntity p_33317_, float p_33318_) {
		this.playSound(SoundEvents.LLAMA_SPIT, 1.0F, 1.0F);

		SoyballEntity fukumame = new SoyballEntity(this.level(), this);
		double d1 = p_33317_.getX() - this.getX();
		double d2 = p_33317_.getEyeY() - this.getEyeY();
		double d3 = p_33317_.getZ() - this.getZ();
		fukumame.shoot(d1, d2, d3, 1.25F, 6.0F + p_33318_);

		this.level().addFreshEntity(fukumame);
		this.level().broadcastEntityEvent(this, (byte) 4);
	}

	@Override
	public boolean canAttack(LivingEntity p_20355_) {
		if (p_20355_ instanceof AbstractTofunian || p_20355_.getType() == TofuEntityTypes.TOFU_GOLEM) {
			return true;
		}
		return super.canAttack(p_20355_);
	}
	@Override
	public boolean canAttackType(EntityType<?> p_21399_) {
		if (this.isPlayerCreated() && p_21399_ == EntityType.PLAYER) {
			return false;
		} else {
			return p_21399_ == EntityType.CREEPER ? false : super.canAttackType(p_21399_);
		}
	}
}

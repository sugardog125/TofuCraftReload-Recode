package baguchi.tofucraft.entity;

import baguchi.tofucraft.api.TofuBossMob;
import baguchi.tofucraft.entity.control.StafeableFlyingMoveControl;
import baguchi.tofucraft.entity.goal.ChargeGoal;
import baguchi.tofucraft.entity.goal.SpinAttackGoal;
import baguchi.tofucraft.entity.projectile.FukumameEntity;
import baguchi.tofucraft.network.BossInfoPacket;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuParticleTypes;
import baguchi.tofucraft.registry.TofuSounds;
import baguchi.tofucraft.registry.TofuStructures;
import baguchi.tofucraft.world.TofuData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;

public class TofuGandlem extends Monster implements RangedAttackMob, TofuBossMob {
	private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS)).setPlayBossMusic(true);

	private static final EntityDataAccessor<Boolean> DATA_ID_SHOOT = SynchedEntityData.defineId(TofuGandlem.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_ID_RUSH = SynchedEntityData.defineId(TofuGandlem.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_ID_SLEEP = SynchedEntityData.defineId(TofuGandlem.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Byte> DATA_CHARGE_FLAGS_ID = SynchedEntityData.defineId(TofuGandlem.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Float> DATA_CHARGE_HEALTH = SynchedEntityData.defineId(TofuGandlem.class, EntityDataSerializers.FLOAT);

	private static final EntityDataAccessor<String> ACTION = SynchedEntityData.defineId(TofuGandlem.class, EntityDataSerializers.STRING);


	private static final UniformInt RUSH_COOLDOWN = UniformInt.of(200, 400);
	private static final UniformInt CHARGE_COOLDOWN = UniformInt.of(400, 600);


	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState shootAnimationState = new AnimationState();
	public final AnimationState shootingAnimationState = new AnimationState();
	public final AnimationState rushAnimationState = new AnimationState();
	public final AnimationState deathAnimationState = new AnimationState();

	public final AnimationState chargeAnimationState = new AnimationState();
	public final AnimationState chargeStopAnimationState = new AnimationState();
	public final AnimationState chargeFailAnimationState = new AnimationState();

	public int failTick;
	private int actionTick;

	@Nullable
	private BlockPos homePos;

	private static final Music GANDLEM_MUSIC = new Music(TofuSounds.TOFU_DUNGEON_BGM, 0, 0, true);


	public TofuGandlem(EntityType<? extends TofuGandlem> p_27508_, Level p_27509_) {
		super(p_27508_, p_27509_);
		this.moveControl = new StafeableFlyingMoveControl(this, 20, false);
		this.xpReward = 60;
	}


	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_ID_SHOOT, false);
		builder.define(DATA_ID_RUSH, false);
		builder.define(DATA_ID_SLEEP, false);
		builder.define(DATA_CHARGE_FLAGS_ID, (byte) 0);
		builder.define(DATA_CHARGE_HEALTH, 0F);
		builder.define(ACTION, Actions.NORMAL.name());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new DoNothingGoal());
		this.goalSelector.addGoal(3, new ChargeGoal(this, CHARGE_COOLDOWN));
		this.goalSelector.addGoal(4, new SpinAttackGoal(this, RUSH_COOLDOWN));
		this.goalSelector.addGoal(5, new AttackGoal(this));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomFlyingGoal(this, 0.9D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Mob.class, 6.0F));
		this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractTofunian.class, true));
	}

	@Override
	protected PathNavigation createNavigation(Level p_218342_) {
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_218342_);
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(true);
		return flyingpathnavigation;
	}

	public boolean isShoot() {
		return this.entityData.get(DATA_ID_SHOOT);
	}

	public void setShoot(boolean shoot) {
		this.entityData.set(DATA_ID_SHOOT, shoot);
	}

	public boolean hasChargeHealth() {
		return this.entityData.get(DATA_CHARGE_HEALTH) > 0;
	}

	public float getChargeHealth() {
		return this.entityData.get(DATA_CHARGE_HEALTH);
	}

	public void setChargeHealth(float chargeHealth) {
		this.entityData.set(DATA_CHARGE_HEALTH, chargeHealth);
	}

	public boolean isRush() {
		return this.entityData.get(DATA_ID_RUSH);
	}

	public void setRush(boolean rush) {
		this.entityData.set(DATA_ID_RUSH, rush);
	}

	public boolean isSleepSelf() {
		return this.entityData.get(DATA_ID_SLEEP);
	}

	public void setSleepSelf(boolean sleep) {
		this.bossEvent.setVisible(!sleep);

		this.entityData.set(DATA_ID_SLEEP, sleep);
	}

	private void setChargeFlag(int p_28533_, boolean p_28534_) {
		if (p_28534_) {
			this.entityData.set(DATA_CHARGE_FLAGS_ID, (byte) (this.entityData.get(DATA_CHARGE_FLAGS_ID) | p_28533_));
		} else {
			this.entityData.set(DATA_CHARGE_FLAGS_ID, (byte) (this.entityData.get(DATA_CHARGE_FLAGS_ID) & ~p_28533_));
		}

	}

	private boolean getChargeFlag(int p_28609_) {
		return (this.entityData.get(DATA_CHARGE_FLAGS_ID) & p_28609_) != 0;
	}


	public boolean isFullCharge() {
		return this.getChargeFlag(16);
	}

	//Once Fully Charged. Charged Health max it
	public void setFullCharge(boolean fullcharge) {
		this.setChargeFlag(16, fullcharge);
		if (fullcharge) {
			this.setChargeHealth(40);
		}
	}

	public void setCharging(boolean p_28615_) {
		this.setChargeFlag(4, p_28615_);
	}

	public boolean isCharging() {
		return this.getChargeFlag(4);
	}

	public void setChargeFailed(boolean p_28617_) {
		this.setChargeFlag(8, p_28617_);
		if (p_28617_) {
			this.setAction(Actions.CHARGE_FAILED);
		}
	}

	public boolean isChargeFailed() {
		return this.getChargeFlag(8);
	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("Sleep", this.isSleepSelf());
		compound.putBoolean("FullCharge", this.isFullCharge());
		compound.putFloat("ChargeHealth", this.getChargeHealth());
		if (this.homePos != null) {
			compound.put("HomePos", NbtUtils.writeBlockPos(this.homePos));
		} else if (this.isPersistenceRequired()) {
			compound.put("HomePos", NbtUtils.writeBlockPos(this.blockPosition()));
		}
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setSleepSelf(compound.getBoolean("Sleep"));
		this.setFullCharge(compound.getBoolean("FullCharge"));
		this.setChargeHealth(compound.getFloat("ChargeHealth"));
		if (compound.contains("HomePos")) {
			this.homePos = NbtUtils.readBlockPos(compound, "HomePos").orElse(null);
		}
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, EntitySpawnReason p_21436_, @Nullable SpawnGroupData p_21437_) {
		if (p_21436_ == EntitySpawnReason.STRUCTURE) {
			this.homePos = this.blockPosition();
		}
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_);
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public boolean removeWhenFarAway(double p_21542_) {
		return false;
	}

	public int getAmbientSoundInterval() {
		return 120;
	}

	@Override
	public void tick() {
		if (this.level().isClientSide()) {
			if (this.isAlive() && !this.isSleepSelf()) {
				this.idleAnimationState.startIfStopped(this.tickCount);
			} else {
				this.idleAnimationState.stop();
				this.rushAnimationState.stop();
			}

			if (this.isAlive() && this.isShoot()) {
				this.shootAnimationState.startIfStopped(this.tickCount);
			} else {
				this.shootAnimationState.stop();
				this.shootingAnimationState.stop();
			}
		} else {
			this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
		}

		if (this.isAlive() && this.isRush()) {
			Vec3 movement = this.getDeltaMovement();
			this.checkRushAttack(this.getBoundingBox(), this.getBoundingBox().expandTowards(movement.x, movement.y, movement.z));
		}

		if (this.isChargeFailed()) {
			if (++this.failTick > 80) {
				this.setChargeFailed(false);
				this.failTick = 0;
			}
		}

		super.tick();
	}


	@Override
	public boolean hurtServer(ServerLevel serverLevel, DamageSource p_21016_, float amount) {
		if (p_21016_.getDirectEntity() instanceof LivingEntity) {
			if (this.isSleepSelf()) {
				this.setSleepSelf(false);
			}
		}

		if (this.isCharging() && this.random.nextFloat() < 0.015F * amount) {
			this.setCharging(false);
			this.setChargeFailed(true);
			this.playSound(SoundEvents.SHIELD_BREAK, 2.0F, 1.0F);
		}

		if (this.hasChargeHealth()) {
			this.setChargeHealth(this.getChargeHealth() - amount);
		}

		if (this.isFullCharge() && !this.hasChargeHealth()) {
			this.setFullCharge(false);
			this.setChargeFailed(true);
			this.playSound(SoundEvents.SHIELD_BREAK, 2.0F, 1.0F);
		} else if (this.isFullCharge()) {
			return super.hurtServer(serverLevel, p_21016_, amount * 0.45F);
		}


		if (p_21016_.is(DamageTypeTags.IS_PROJECTILE)) {
			return super.hurtServer(serverLevel, p_21016_, amount * 0.8F);
		}

		return super.hurtServer(serverLevel, p_21016_, amount);
	}

	protected void checkRushAttack(AABB p_21072_, AABB p_21073_) {
		AABB aabb = p_21072_.minmax(p_21073_);
		List<Entity> list = this.level().getEntities(this, aabb);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); ++i) {
				Entity entity = list.get(i);
				this.rushAttack(entity);
				break;
			}
		} else if (this.horizontalCollision && this.tickCount % 3 == 0) {
			boolean flag = false;
			int l = Mth.floor(this.getBbWidth() / 2.0F + 1.0F);
			int i1 = Mth.floor(this.getBbHeight()) + 1;

			for (BlockPos blockpos : BlockPos.betweenClosed(
					this.getBlockX() - l, this.getBlockY(), this.getBlockZ() - l, this.getBlockX() + l, this.getBlockY() + i1, this.getBlockZ() + l
			)) {
				BlockState blockstate = this.level().getBlockState(blockpos);
				if (blockstate.canEntityDestroy(this.level(), blockpos, this) && net.neoforged.neoforge.event.EventHooks.onEntityDestroyBlock(this, blockpos, blockstate)) {
					flag = this.level().destroyBlock(blockpos, true, this) || flag;
				}
			}

			if (flag) {
				this.level().levelEvent(null, 1022, this.blockPosition(), 0);
			}
			this.playSound(SoundEvents.PLAYER_ATTACK_KNOCKBACK, 2.0F, 1.0F);
		}
	}

	public void rushAttack(Entity p_36347_) {
		DamageSource source = this.damageSources().mobAttack(this);
		if (p_36347_ instanceof LivingEntity living && living.isDamageSourceBlocked(source)) {
			this.setRush(false);
			this.setChargeFailed(true);
			this.playSound(SoundEvents.PLAYER_ATTACK_KNOCKBACK, 2.0F, 1.0F);

		} else
		if (p_36347_.isAttackable()) {
			p_36347_.hurt(source, 16.0F);
			float i = (float) this.getAttributeValue(Attributes.ATTACK_KNOCKBACK); // Forge: Initialize this value to the attack knockback attribute of the player, which is by default 0
			i += 0.65F;

			if (i > 0) {
				if (p_36347_ instanceof LivingEntity) {
					((LivingEntity) p_36347_).knockback((double) ((float) i * 0.5F), (double) Mth.sin(this.getYRot() * ((float) Math.PI / 180F)), (double) (-Mth.cos(this.getYRot() * ((float) Math.PI / 180F))));
				} else {
					p_36347_.push((double) (-Mth.sin(this.getYRot() * ((float) Math.PI / 180F)) * (float) i * 0.5F), 0.1D, (double) (Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * (float) i * 0.5F));
				}

				this.setDeltaMovement(this.getDeltaMovement().multiply(0.6D, 1.0D, 0.6D));
				this.setSprinting(false);
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte p_70103_1_) {
		if (p_70103_1_ == 4) {
			this.attackAnimationState.start(this.tickCount);
		} else if (p_70103_1_ == 5) {
			this.shootingAnimationState.start(this.tickCount);
		} else if (p_70103_1_ == 7) {
			this.deathAnimationState.start(this.tickCount);
		} else {
			super.handleEntityEvent(p_70103_1_);
		}

	}

	@Override
	public boolean doHurtTarget(ServerLevel serverLevel, Entity p_21372_) {
		this.level().broadcastEntityEvent(this, (byte) 4);
		return super.doHurtTarget(serverLevel, p_21372_);
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

	public void aiStep() {
		if (this.isFullCharge()) {
			if (this.level().isClientSide) {
				for (int i = 0; i < 2; ++i) {
					this.level().addParticle(TofuParticleTypes.TOFU_PORTAL.get(), this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.random.nextDouble() - 0.5D) * 2.0D, -this.random.nextDouble(), (this.random.nextDouble() - 0.5D) * 2.0D);
				}
			}
		}

		super.aiStep();

		if (this.isAlive() && !this.isSleepSelf()) {
			this.calculateFlapping();
		}

		if (this.isSleepSelf() && this.getTarget() != null) {
			this.setSleepSelf(false);
			this.playSound(SoundEvents.BEACON_ACTIVATE, 3.0F, 1.0F);
		}

		if (!this.level().isClientSide && this.isAlive() && this.tickCount % 10 == 0 && this.isCharging()) {
			this.heal(4.0F);
		}
		this.actionTicks();
		if (this.level().isClientSide) {
			this.actionAnimations(this.getAction(), true);
		}
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> p_146754_) {
		if (ACTION.equals(p_146754_)) {
			this.actionAnimations(this.getAction(), false);
		}
		if (DATA_ID_RUSH.equals(p_146754_)) {
			this.refreshDimensions();
		}
		super.onSyncedDataUpdated(p_146754_);

	}

	public Actions getAction() {
		return Actions.get(this.entityData.get(ACTION));
	}

	public void setAction(Actions action) {
		this.entityData.set(ACTION, action.name());
		this.actionTick = 0;
	}

	public void actionTicks() {
		if (getAction().tick > -1) {
			if (getAction().tick <= this.actionTick) {
				this.actionTick = 0;
				setAction(Actions.NORMAL);
			} else {
				++this.actionTick;
			}

		} else {
			this.actionTick = 0;
		}
	}

	public void actionAnimations(Actions actions, boolean loop) {
		if (loop && actions.loop || !loop && !actions.loop) {
			switch (actions) {
				case START_RUSH:
					attackAnimationState.stop();
					shootAnimationState.stop();
					shootingAnimationState.stop();
					this.stopAnimations();
					rushAnimationState.start(this.tickCount);
					break;
				case CHARGE:
					attackAnimationState.stop();
					shootAnimationState.stop();
					shootingAnimationState.stop();
					this.stopAnimations();
					chargeAnimationState.start(this.tickCount);
					break;
				case CHARGE_STOP:
					attackAnimationState.stop();
					shootAnimationState.stop();
					shootingAnimationState.stop();
					this.stopAnimations();
					chargeStopAnimationState.start(this.tickCount);
					break;
				case CHARGE_FAILED:
					attackAnimationState.stop();
					shootAnimationState.stop();
					shootingAnimationState.stop();
					this.stopAnimations();
					chargeFailAnimationState.start(this.tickCount);

					break;
				default:
					this.stopAnimations();
					break;
			}
		}
	}

	public void stopAnimations() {
		chargeAnimationState.stop();
		chargeFailAnimationState.stop();
		chargeStopAnimationState.stop();
		rushAnimationState.stop();
	}

	private void calculateFlapping() {
		Vec3 vec3 = this.getDeltaMovement();
	}


	@Override
	public void die(DamageSource p_21014_) {
		super.die(p_21014_);
		this.playSound(SoundEvents.BEACON_DEACTIVATE, 2.0F, 1.0F);

		if (!this.level().isClientSide()) {
			this.level().broadcastEntityEvent(this, (byte) 7);
			if (this.level() instanceof ServerLevel serverLevel && this.homePos != null) {
				Structure structure = serverLevel.registryAccess().lookupOrThrow(Registries.STRUCTURE).getValueOrThrow(TofuStructures.TOFU_CASTLE);
				if (structure != null) {
					TofuData data = TofuData.get(serverLevel);
					StructureStart structureStart = serverLevel.structureManager().getStructureAt(this.homePos, structure);
					if (structureStart.isValid() && !data.getBeatenDungeons().contains(structureStart.getBoundingBox())) {
						data.addBeatenDungeons(structureStart.getBoundingBox());
					}
				}
			}
		}
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 38) {
			this.playSound(SoundEvents.ITEM_BREAK, 1.0F, 1.4F);
		}
		if (this.deathTime == 40) {
			this.playSound(SoundEvents.ITEM_BREAK, 1.0F, 1.35F);
		}

		if (this.deathTime == 100 && !this.level().isClientSide()) {
			this.level().broadcastEntityEvent(this, (byte) 60);
			this.remove(Entity.RemovalReason.KILLED);
		}
	}

	public boolean causeFallDamage(float p_148989_, float p_148990_, DamageSource p_148991_) {
		return false;
	}

	protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) {
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 300.0D).add(Attributes.FOLLOW_RANGE, 28F).add(Attributes.MOVEMENT_SPEED, 0.11D).add(Attributes.FLYING_SPEED, 0.11D).add(Attributes.ATTACK_KNOCKBACK, 0.9F).add(Attributes.KNOCKBACK_RESISTANCE, 0.9D).add(Attributes.ARMOR, 14.0F).add(Attributes.ARMOR_TOUGHNESS, 2.0F).add(Attributes.ATTACK_DAMAGE, 4.0D);
	}

	protected int decreaseAirSupply(int p_28882_) {
		return p_28882_;
	}

	@Override
	public void push(Entity p_21294_) {
		if (!this.isSleepSelf()) {
			super.push(p_21294_);
		}
	}

	@Override
	public boolean canAttack(LivingEntity p_20355_) {
		if (p_20355_.getType() == TofuEntityTypes.TOFU_GANDLEM) {
			return true;
		}
		return super.canAttack(p_20355_);
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);
		PacketDistributor.sendToPlayer(player, new BossInfoPacket.Display(this.bossEvent.getId(), this.getId()));
		if (this.homePos != null) {
			this.bossEvent.addPlayer(player);
		}
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);
		PacketDistributor.sendToPlayer(player, new BossInfoPacket.Remove(this.bossEvent.getId(), this.getId()));
		this.bossEvent.removePlayer(player);
	}

	@Override
	protected EntityDimensions getDefaultDimensions(Pose p_21047_) {
		EntityDimensions entitydimensions = super.getDefaultDimensions(p_21047_);
		return this.isRush() ? EntityDimensions.fixed(entitydimensions.width(), entitydimensions.height() * 0.45F) : entitydimensions;
	}

	@Override
	public void performRangedAttack(LivingEntity p_29912_, float p_29913_) {
		this.playSound(SoundEvents.SHULKER_SHOOT, 3.0F, 1.0F);
		for (int i = 0; i < 4; i++) {
			FukumameEntity fukumame = new FukumameEntity(this.level(), this);
			double d1 = p_29912_.getX() - this.getX();
			double d2 = p_29912_.getEyeY() - this.getEyeY();
			double d3 = p_29912_.getZ() - this.getZ();
			float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
			fukumame.shoot(d1, d2 + f, d3, 1.0F, 2.0F + p_29913_);
			fukumame.damage = 1.0F;
			this.level().addFreshEntity(fukumame);
		}
	}

	@Override
	public Music getBossMusic() {
		return null;
	}

	static class AttackGoal extends Goal {
		private final TofuGandlem gandlem;
		private int attackStep;
		private int attackTime;
		private int lastSeen;

		private boolean strafingClockwise;
		private boolean strafingBackwards;
		private int strafingTime = -1;

		public AttackGoal(TofuGandlem p_32247_) {
			this.gandlem = p_32247_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public boolean canUse() {
			LivingEntity livingentity = this.gandlem.getTarget();
			return livingentity != null && livingentity.isAlive() && this.gandlem.canAttack(livingentity);
		}

		public void start() {
			this.attackStep = 0;
			this.gandlem.setShoot(true);
		}

		public void stop() {
			this.lastSeen = 0;
			this.gandlem.setShoot(false);
		}

		public void tick() {
			--this.attackTime;
			LivingEntity livingentity = this.gandlem.getTarget();
			if (livingentity != null) {
				boolean flag = this.gandlem.getSensing().hasLineOfSight(livingentity);
				if (flag) {
					this.lastSeen = 0;
				} else {
					++this.lastSeen;
				}

				double d0 = this.gandlem.distanceToSqr(livingentity);
				if (d0 < 8.5D) {
					if (!flag) {
						return;
					}
					this.attackStep = 0;

					if (this.gandlem.isShoot()) {
						this.gandlem.setShoot(false);
					}

					if (d0 < 5.0D + this.gandlem.getBbWidth() && this.attackTime <= 0) {
						this.attackTime = 20;
						if (this.gandlem.level() instanceof ServerLevel serverLevel) {
							this.gandlem.doHurtTarget(serverLevel, livingentity);
						}
					}

					this.gandlem.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
					this.gandlem.getNavigation().moveTo(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0F);
				} else if (d0 < this.getFollowDistance() * this.getFollowDistance() * 0.75F && flag) {
					if (!this.gandlem.isShoot()) {
						this.gandlem.setShoot(true);
					}
					if (this.attackTime <= 0) {
						++this.attackStep;
						if (this.attackStep == 1) {
							this.attackTime = 30;
						} else if (this.attackStep <= 4) {
							this.attackTime = 8;
						} else {
							this.attackTime = 30;
							this.attackStep = 0;
						}

						if (this.attackStep > 1) {
							double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
							this.gandlem.level().broadcastEntityEvent(this.gandlem, (byte) 5);
							this.gandlem.performRangedAttack(livingentity, attackTime);
						}
					}
					if (d0 < this.getFollowDistance() * this.getFollowDistance() * 0.5F && this.lastSeen <= 20) {
						++this.strafingTime;
						this.gandlem.getNavigation().stop();
					} else {
						this.strafingTime = -1;
						this.gandlem.getNavigation().moveTo(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0F);
					}

					if (this.strafingTime >= 20) {
						if ((double) this.gandlem.getRandom().nextFloat() < 0.3D) {
							this.strafingClockwise = !this.strafingClockwise;
						}

						if ((double) this.gandlem.getRandom().nextFloat() < 0.3D) {
							this.strafingBackwards = !this.strafingBackwards;
						}

						this.strafingTime = 0;
					}

					if (this.strafingTime > -1) {
						if (d0 > (double) (this.getFollowDistance() * this.getFollowDistance() * 0.4F)) {
							this.strafingBackwards = false;
						} else if (d0 < (double) (this.getFollowDistance() * this.getFollowDistance() * 0.25F)) {
							this.strafingBackwards = true;
						}

						this.gandlem.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);

						this.gandlem.lookAt(livingentity, 10.0F, 10.0F);
					} else {
						this.gandlem.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
					}

				} else if (this.lastSeen < 200 && d0 > this.getFollowDistance() * this.getFollowDistance() || d0 <= this.getFollowDistance() * this.getFollowDistance()) {
					this.gandlem.getNavigation().moveTo(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.1F);
				}

				super.tick();
			}
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		private double getFollowDistance() {
			return this.gandlem.getAttributeValue(Attributes.FOLLOW_RANGE);
		}
	}

	class DoNothingGoal extends Goal {
		public DoNothingGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
		}

		public boolean canUse() {
			return TofuGandlem.this.isSleepSelf() || TofuGandlem.this.isChargeFailed();
		}
	}

	public enum Actions {
		NORMAL(true, -1),
		START_RUSH(false, (int) (20 * 5.6)),
		CHARGE(false, (int) (120)),
		CHARGE_STOP(false, (int) (20 * 0.68)),
		CHARGE_FAILED(false, (int) (20 * 3)),
		SLEEP(true, -1);

		private final boolean loop;
		private final int tick;

		Actions(boolean loop, int tick) {

			this.loop = loop;
			this.tick = tick;
		}

		public static Actions get(String nameIn) {
			for (Actions role : values()) {
				if (role.name().equals(nameIn))
					return role;
			}
			return NORMAL;
		}
	}

}

package baguchi.tofucraft.entity;

import baguchi.bagus_lib.client.camera.CameraCore;
import baguchi.bagus_lib.client.camera.holder.EntityCameraHolder;
import baguchi.bagus_lib.util.GlobalVec3;
import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.entity.effect.NattoCobWebEntity;
import baguchi.tofucraft.entity.projectile.FukumameEntity;
import baguchi.tofucraft.entity.projectile.NattoBallEntity;
import baguchi.tofucraft.entity.projectile.NattoStringEntity;
import baguchi.tofucraft.network.BossInfoPacket;
import baguchi.tofucraft.registry.TofuDamageTypes;
import baguchi.tofucraft.registry.TofuEffects;
import baguchi.tofucraft.registry.TofuParticleTypes;
import baguchi.tofucraft.registry.TofuSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.JumpGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.entity.PartEntity;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class ShuDofuSpider extends Monster {
	private static final EntityDataAccessor<Boolean> DATA_ID_JUMP = SynchedEntityData.defineId(ShuDofuSpider.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> ATTACK_ANIMATION = SynchedEntityData.defineId(ShuDofuSpider.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> JUMP_ANIMATION = SynchedEntityData.defineId(ShuDofuSpider.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DATA_ID_RANGED = SynchedEntityData.defineId(ShuDofuSpider.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> GRASP_ANIMATION = SynchedEntityData.defineId(ShuDofuSpider.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> ANGRY = SynchedEntityData.defineId(ShuDofuSpider.class, EntityDataSerializers.BOOLEAN);


	private static final AttributeModifier ATTACK_MODIFIER = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "attack_boost"), 0.1D, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

	private static final AttributeModifier ARMOR_MODIFIER = new AttributeModifier(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "armor_boost"), -0.15D, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

	private final ShuDofuSpiderPart[] subEntities;
	public final ShuDofuSpiderPart body;
	private final ShuDofuSpiderPart leg1;
	private final ShuDofuSpiderPart leg2;
	private final ShuDofuSpiderPart leg3;
	private final ShuDofuSpiderPart leg4;
	private final ShuDofuSpiderPart leg5;
	private final ShuDofuSpiderPart leg6;

	private int attackTime;
	private int jumpTime;
	private int impactTime;
	private int stinkTime;
	private int rangedTime;
	private int graspTime;

	private float leftLegAnimation;
	private float leftLegAnimationOld;
	private float rightLegAnimation;
	private float rightLegAnimationOld;

	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState deathAnimationState = new AnimationState();
	public final AnimationState jumpAnimationState = new AnimationState();

	public final AnimationState graspAnimationState = new AnimationState();
	public final AnimationState graspPreAnimationState = new AnimationState();

	private final ServerBossEvent bossEvent = (ServerBossEvent) (new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
	private float graspDamageReceived;

	public ShuDofuSpider(EntityType<? extends ShuDofuSpider> p_27508_, Level p_27509_) {
		super(p_27508_, p_27509_);
		this.xpReward = 200;
		this.body = new ShuDofuSpiderPart(this, "body", 2F, 3.0F);
		this.leg1 = new ShuDofuSpiderPart(this, "leg", 0.75F, 2.0F);
		this.leg2 = new ShuDofuSpiderPart(this, "leg", 0.75F, 2.0F);
		this.leg3 = new ShuDofuSpiderPart(this, "leg", 0.75F, 2.0F);
		this.leg4 = new ShuDofuSpiderPart(this, "leg", 0.75F, 2.0F);
		this.leg5 = new ShuDofuSpiderPart(this, "leg", 0.75F, 2.0F);
		this.leg6 = new ShuDofuSpiderPart(this, "leg", 0.75F, 2.0F);
		this.subEntities = new ShuDofuSpiderPart[]{this.body, this.leg1, this.leg2, this.leg3, this.leg4, this.leg5, this.leg6};
		this.setId(ENTITY_COUNTER.getAndAdd(this.subEntities.length + 1) + 1); // Forge: Fix MC-158205: Make sure part ids are successors of parent mob id
	}

	@Override
	public void setId(int id) {
		super.setId(id);
		for (int i = 0; i < this.subEntities.length; i++) // Forge: Fix MC-158205: Set part ids to successors of parent mob id
			this.subEntities[i].setId(id + i + 1);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(ATTACK_ANIMATION, false);
		builder.define(JUMP_ANIMATION, false);
		builder.define(DATA_ID_JUMP, false);
		builder.define(DATA_ID_RANGED, false);
		builder.define(GRASP_ANIMATION, false);
		builder.define(ANGRY, false);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Mob.class, 6.0F));
		this.goalSelector.addGoal(4, new ShuDofuSpider.AttackGoal(this));
		this.goalSelector.addGoal(4, new ShuDofuSpider.JumpAttackGoal());
		this.goalSelector.addGoal(3, new ShuDofuSpider.graspAttackGoal(this));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.8D));

		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, true, null));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractTofunian.class, 10, true, true, null));
	}

	protected float getStandingEyeHeight(Pose p_33799_, EntityDimensions p_33800_) {
		return 1.25F;
	}

	private boolean isMovingOnLand() {
		return this.onGround() && this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D && !this.isInWaterOrBubble();
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return false;
	}

	@Override
	public boolean removeWhenFarAway(double p_21542_) {
		return false;
	}

	@Override
	public float getVoicePitch() {
		return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.6F;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return TofuSounds.TOFUSPIDER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_33814_) {
		return TofuSounds.TOFUSPIDER_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return TofuSounds.TOFUSPIDER_DEATH.get();
	}

	@Override
	protected void positionRider(Entity passenger, Entity.MoveFunction p_19958_) {
		if (this.isGraspAnim()) {
			if (!this.getPassengers().isEmpty()) {
				Vec3 riderPos = this.getRiderPosition(passenger);
				p_19958_.accept(passenger, riderPos.x(), riderPos.y(), riderPos.z());
			}
		} else {
			super.positionRider(passenger, p_19958_);
		}
	}

	@Override
	public boolean canRiderInteract() {
		return true;
	}

	private Vec3 getRiderPosition(Entity entity) {
		if (!this.getPassengers().isEmpty() && this.isGraspAnim()) {
			float distance = 3.0F;

			double dx = Math.cos((this.getYRot() + 90) * Math.PI / 180.0D) * distance;
			double dz = Math.sin((this.getYRot() + 90) * Math.PI / 180.0D) * distance;

			return new Vec3(this.getX() + dx, this.getY() + this.getDimensions(this.getPose()).height() * 0.15D + this.getPassengers().get(0).getVehicleAttachmentPoint(this).y, this.getZ() + dz);
		} else {
			Vec3 vec3 = this.getPassengerRidingPosition(entity);
			Vec3 vec31 = entity.getVehicleAttachmentPoint(this);
			return new Vec3(vec3.x - vec31.x, vec3.y - vec31.y, vec3.z - vec31.z);
		}
	}

	@Override
	public void tick() {
		++this.stinkTime;
		if (this.stinkTime == 120) {
			this.level().addParticle(TofuParticleTypes.STINK.get(), this.getX(), this.getY() + 1.0, this.getZ(), 0, 0, 0);
			this.stinkTime = this.random.nextInt(0, 20);
		}
		if (this.isAlive() && !this.isMovingOnLand()) {
			this.idleAnimationState.startIfStopped(this.tickCount);
		} else {
			this.idleAnimationState.stop();
		}
		if (this.isAlive() && this.isGraspAnim()) {
			this.graspAnimationState.startIfStopped(this.tickCount);
		} else {
			this.graspAnimationState.stop();
		}

		if (!this.level().isClientSide()) {
			if (this.isAlive() && !this.isGraspAnim() && this.isAttackAnim() && this.getTarget() != null) {
				++this.attackTime;
				if (this.attackTime == 10) {
					this.level().broadcastEntityEvent(this, (byte) 100);
				}
				if (this.attackTime == 14) {
					float radius = 2;
					float swipePosX = (float) (this.getX() + radius * Math.cos(Math.toRadians(this.getYHeadRot() + 90)));
					float swipePosZ = (float) (this.getZ() + radius * Math.sin(Math.toRadians(this.getYHeadRot() + 90)));
					if (this.level() instanceof ServerLevel serverLevel) {
						AABB hitBox = new AABB(BlockPos.containing(swipePosX, this.getY() - 0.5f, swipePosZ)).inflate(1.55, 1.55, 1.55);
						List<LivingEntity> entitiesHit = this.level().getEntitiesOfClass(LivingEntity.class, hitBox);
						for (LivingEntity entity : entitiesHit) {
							if (entity != this) {
								if (this.canAttack(entity) && !this.canAttack(entity) && this.isWithinMeleeAttackRange(entity)) {
									doHurtTarget(serverLevel, entity);
								}
							}
						}
					}
					this.attackTime = 0;
					this.setAttackAnimation(false);
				}
			}

			if (this.isAlive() && this.isRanged() && this.getTarget() != null) {
				++this.rangedTime;
				if (this.rangedTime == 1) {
					this.level().broadcastEntityEvent(this, (byte) 103);
				}
				if (this.rangedTime == 30) {
					if (this.random.nextInt(2) == 1) {
						this.performRangedAttack(this.getTarget());
						this.playSound(TofuSounds.TOFUSPIDER_SPIT.get(), 2.0F, (float) (0.6F + this.random.nextDouble() * 0.2F));
					} else {
						this.performBreathAttack(this.getTarget());
						this.playSound(SoundEvents.ENDER_DRAGON_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
					}
				}
				if (this.rangedTime == 50) {
					this.rangedTime = 0;
					this.setRanged(false);
				}
			}

			if (this.isAlive() && this.isGraspAnim() && this.getTarget() != null) {
				LivingEntity target = this.getTarget();
				++this.graspTime;
				if (this.graspTime == 1) {
					this.getLookControl().setLookAt(target, 60.0F, 60F);
					Vec3 vec3 = (new Vec3(target.getX() - this.getX(), target.getY() - this.getY(), target.getZ() - this.getZ())).normalize();
					this.setDeltaMovement(this.getDeltaMovement().add(vec3.x * 1.25D, 0.325D, vec3.z * 1.25D));

				}
				if (this.graspTime > 1) {
					Vec3 movement = this.getDeltaMovement();
					this.checkGraspAttack(this.getBoundingBox(), this.getBoundingBox().expandTowards(movement.x, movement.y, movement.z).inflate(1.0F));
				}
				if (this.graspTime == 100) {
					this.graspTime = 0;
					this.setGraspAnimation(false);
					this.ejectPassengers();
				}
			} else if (this.isGraspAnim()) {
				this.setGraspAnimation(false);
			}


			if (this.isAlive() && this.isJumpAnim()) {
				++this.jumpTime;
				if (this.jumpTime == 1) {
					this.level().broadcastEntityEvent(this, (byte) 102);
				}
				if (this.jumpTime == 2) {
					Vec3 movement = this.getDeltaMovement();
					this.checkJumpAttack(this.getBoundingBox(), this.getBoundingBox().expandTowards(movement.x, movement.y, movement.z));
				}
				if (this.jumpTime == 30 && this.onGround()) {
					this.impactTime = this.jumpTime + 40;
				}
				if (this.jumpTime >= this.impactTime && this.onGround()) {
					var world = ((ServerLevel) this.level());
					int count = 36;
					float distance = 4;
					for (int i = 1; i <= count; i++) {
						double yaw = i * 360f / count;
						world.sendParticles(TofuParticleTypes.STINK.get(), this.getX() + Math.cos(Math.toRadians(yaw)) * distance, this.getY(), this.getZ() + Math.sin(Math.toRadians(yaw)) * distance, 0, 0, 0, 0, 0);
					}
					float radius = 5;
					AABB hitBox = new AABB(ShuDofuSpider.this.getX() - radius, ShuDofuSpider.this.getY() - 1, ShuDofuSpider.this.getZ() - radius, ShuDofuSpider.this.getX() + radius, ShuDofuSpider.this.getY() + 2, ShuDofuSpider.this.getZ() + radius);
					List<LivingEntity> entitiesHit = ShuDofuSpider.this.level().getEntitiesOfClass(LivingEntity.class, hitBox);
					for (LivingEntity entity : entitiesHit) {
						if (entity != this) {
							if (!this.canAttack(entity)) {
								double d12 = Math.sqrt(entity.distanceToSqr(entity)) / (double) radius * 2.0F;
								double d14 = ServerExplosion.getSeenPercent(new Vec3(this.getX(), this.getY(), this.getZ()), entity);
								double d10 = (1.0D - d12) * d14;
								entity.addEffect(new MobEffectInstance(TofuEffects.COUGH, (int) (400 * d10), 0));
								entity.hurt(this.damageSources().source(TofuDamageTypes.SOY_SPORE, ShuDofuSpider.this), (float) (((d10 * d10 + d10) / 2.0D) * 26.0D));
							}
						}
					}
					playSound(SoundEvents.WITHER_BREAK_BLOCK, 2.0f, 1.0f);
					this.impactTime = 0;
					this.jumpTime = 0;
					CameraCore.addCameraHolderList(this.level(), new EntityCameraHolder<>(16, 60, 0.4F, GlobalVec3.of(this.level().dimension(), this.getEyePosition()), this));
					this.setJumpAnimation(false);
				}
			}
			if (this.isJump() && this.isInFluidType()) {
				this.setJumping(false);
				this.setJumpAnimation(false);
			}
		} else {
			this.rightLegAnimationOld = this.rightLegAnimation;
			this.leftLegAnimationOld = this.leftLegAnimation;
			if (!this.isJump() && this.subEntities != null) {
				var direction = Direction.fromYRot(this.yBodyRot);
				var pos = this.blockPosition();
				var air = Blocks.AIR.defaultBlockState();

				var rBlock = this.level().getBlockState(this.leg1.blockPosition().below());
				var rBlockF = this.level().getBlockState(this.leg2.blockPosition().below());
				var rBlockB = this.level().getBlockState(this.leg3.blockPosition().below());
					if (rBlock == air && rBlockF == air && rBlockB == air) {
						this.rightLegAnimation = Mth.clamp(this.rightLegAnimation + 0.1F, -1, 1);
					} else {
						this.rightLegAnimation = Mth.clamp(this.rightLegAnimation * 0.5F, -1, 1);

					}
				var lBlock = this.hasEmptyCollisionOnLeg(this.leg4.blockPosition().below());
				var lBlockF = this.hasEmptyCollisionOnLeg(this.leg5.blockPosition().below());
				var lBlockB = this.hasEmptyCollisionOnLeg(this.leg6.blockPosition().below());
					if (lBlock && lBlockF && lBlockB) {
						this.leftLegAnimation = Mth.clamp(this.leftLegAnimation + 0.1F, -1, 1);
					} else {
						this.leftLegAnimation = Mth.clamp(this.leftLegAnimation * 0.5F, -1, 1);

					}

			} else {
				this.rightLegAnimation = Mth.clamp(this.rightLegAnimation + 0.1F, -1, 1);
				this.leftLegAnimation = Mth.clamp(this.leftLegAnimation + 0.1F, -1, 1);
			}
		}
		super.tick();
	}

	@Override
	public void aiStep() {
		super.aiStep();
		Vec3[] avec3 = new Vec3[this.subEntities.length];

		for (int j = 0; j < this.subEntities.length; j++) {
			avec3[j] = new Vec3(this.subEntities[j].getX(), this.subEntities[j].getY(), this.subEntities[j].getZ());
		}
		Vec3 vec3 = new Vec3(0, 1, -2)
				.yRot(-this.yBodyRot * (float) (Math.PI / 180.0));
		Vec3 vec32 = new Vec3(2.0F, 0, 1.0)
				.yRot(-this.yBodyRot * (float) (Math.PI / 180.0));
		Vec3 vec33 = new Vec3(2.0F, 0, 0)
				.yRot(-this.yBodyRot * (float) (Math.PI / 180.0));
		Vec3 vec34 = new Vec3(2.0F, 0, -1.0)
				.yRot(-this.yBodyRot * (float) (Math.PI / 180.0));
		Vec3 vec35 = new Vec3(-2.0F, 0, 1.0)
				.yRot(-this.yBodyRot * (float) (Math.PI / 180.0));
		Vec3 vec36 = new Vec3(-2.0F, 0, 0)
				.yRot(-this.yBodyRot * (float) (Math.PI / 180.0));
		Vec3 vec37 = new Vec3(-2.0F, 0, -1.0)
				.yRot(-this.yBodyRot * (float) (Math.PI / 180.0));
		this.tickPart(this.body, vec3.x * getScale(), vec3.y * getScale(), vec3.z * getScale());
		this.tickPart(this.leg1, vec32.x * getScale(), vec32.y * getScale(), vec32.z * getScale());
		this.tickPart(this.leg2, vec33.x * getScale(), vec33.y * getScale(), vec33.z * getScale());
		this.tickPart(this.leg3, vec34.x * getScale(), vec34.y * getScale(), vec34.z * getScale());
		this.tickPart(this.leg4, vec35.x * getScale(), vec35.y * getScale(), vec35.z * getScale());
		this.tickPart(this.leg5, vec36.x * getScale(), vec36.y * getScale(), vec36.z * getScale());
		this.tickPart(this.leg6, vec37.x * getScale(), vec37.y * getScale(), vec37.z * getScale());

		for (int l = 0; l < this.subEntities.length; l++) {
			this.subEntities[l].xo = avec3[l].x;
			this.subEntities[l].yo = avec3[l].y;
			this.subEntities[l].zo = avec3[l].z;
			this.subEntities[l].xOld = avec3[l].x;
			this.subEntities[l].yOld = avec3[l].y;
			this.subEntities[l].zOld = avec3[l].z;
		}
	}

	protected float sanitizeScale(float p_320290_) {
		return 1F;
	}

	private void tickPart(ShuDofuSpiderPart p_31116_, double p_31117_, double p_31118_, double p_31119_) {
		p_31116_.setPos(this.getX() + p_31117_, this.getY() + p_31118_, this.getZ() + p_31119_);
	}

	public boolean hasEmptyCollisionOnLeg(BlockPos blockPos) {
		var rBlock = this.level().getBlockState(blockPos).getCollisionShape(this.level(), blockPos).isEmpty();
		return rBlock;
	}

	@OnlyIn(Dist.CLIENT)
	public float getRightLegAnimationScale(float p_29570_) {
		return Mth.lerp(p_29570_, this.rightLegAnimationOld, this.rightLegAnimation);
	}

	@OnlyIn(Dist.CLIENT)
	public float getLeftLegAnimationScale(float p_29570_) {
		return Mth.lerp(p_29570_, this.leftLegAnimationOld, this.leftLegAnimation);
	}

	@Override
	public boolean canDisableShield() {
		return this.isGraspAnim();
	}

	public void performRangedAttack(LivingEntity p_29912_) {
		this.playSound(TofuSounds.TOFUSPIDER_SPIT.get(), 2.0F, (float) (0.6F + this.random.nextDouble() * 0.2F));
		for (int i = 0; i < 3; i++) {
			NattoStringEntity natto = new NattoStringEntity(this.level(), this);
			double d1 = p_29912_.getX() - this.getX();
			double d2 = p_29912_.getY() - this.getEyeY();
			double d3 = p_29912_.getZ() - this.getZ();
			float f = Mth.sqrt((float) (d1 * d1 + d3 * d3)) * 0.2F;
			natto.shoot(d1, d2 + f, d3, 1.0F, 2.0F + this.random.nextInt(20) + 10);

			this.level().addFreshEntity(natto);
		}
	}

	public void performBreathAttack(LivingEntity p_29912_) {
		NattoBallEntity ball = new NattoBallEntity(this.level(), this);
		double d1 = p_29912_.getX() - this.getX();
		double d2 = p_29912_.getEyeY() - this.getY();
		double d3 = p_29912_.getZ() - this.getZ();
		ball.shoot(d1, d2 + 0.5F, d3, 1.0F, 0F);

		this.level().addFreshEntity(ball);
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte p_70103_1_) {
		if (p_70103_1_ == 100) {
			this.attackAnimationState.start(this.tickCount);
		} else if (p_70103_1_ == 101) {
			this.deathAnimationState.start(this.tickCount);
		} else if (p_70103_1_ == 102) {
			this.jumpAnimationState.start(this.tickCount);
		} else if (p_70103_1_ == 104) {
			this.graspPreAnimationState.start(this.tickCount);
		} else {
			super.handleEntityEvent(p_70103_1_);
		}
	}

	protected void checkJumpAttack(AABB p_21072_, AABB p_21073_) {
		AABB aabb = p_21072_.minmax(p_21073_);
		List<Entity> list = this.level().getEntities(this, aabb);
		if (!list.isEmpty()) {
			for (Entity entity : list) {
				if (entity != this) {
					this.jumpAttack(entity);
					break;
				}
			}
		} else if (this.horizontalCollision && this.tickCount % 3 == 0) {
			this.playSound(SoundEvents.PLAYER_ATTACK_KNOCKBACK, 2.0F, 1.0F);
		}
	}

	public void jumpAttack(Entity p_36347_) {
		if (p_36347_.isAttackable() && (!(p_36347_ instanceof LivingEntity livingEntity) || !this.canAttack(livingEntity))) {
			p_36347_.hurt(this.damageSources().mobAttack(this), 18.0F);
			float i = (float) this.getAttributeValue(Attributes.ATTACK_KNOCKBACK); // Forge: Initialize this value to the attack knockback attribute of the player, which is by default 0
			i += 1.5F;

			if (i > 0) {
				if (p_36347_ instanceof LivingEntity) {
					((LivingEntity) p_36347_).knockback(i, Mth.sin(this.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(this.getYRot() * ((float) Math.PI / 180F)));
				} else {
					p_36347_.push(-Mth.sin(this.getYRot() * ((float) Math.PI / 180F)) * i, 0.1D, Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * i);
				}
				this.setSprinting(false);
			}
		}
	}

	protected void checkGraspAttack(AABB p_21072_, AABB p_21073_) {
		AABB aabb = p_21072_.minmax(p_21073_);
		List<Entity> list = this.level().getEntities(this, aabb);
		if (!list.isEmpty()) {
			for (Entity entity : list) {
				if (entity != this && !(entity instanceof PartEntity<?>) && entity.isAttackable() && (!(entity instanceof LivingEntity livingEntity) || !this.canAttack(livingEntity)) && !(entity instanceof NattoCobWebEntity)) {
					this.graspAttack(entity);
					break;
				}
			}
		}
	}

	public void graspAttack(Entity entity) {
		float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		if (this.level() instanceof ServerLevel serverLevel) {
			if (entity.hurtServer(serverLevel, this.damageSources().mobAttack(this), f * 0.2F)) {
				this.heal(f * 0.2F);
			}
		}
		if (entity instanceof LivingEntity && !entity.getType().is(Tags.EntityTypes.BOSSES)) {
				if (this.getPassengers().isEmpty()) {
					entity.stopRiding();
					entity.startRiding(this, true);
				}
			}
			this.setSprinting(false);
	}

	protected void playStepSound(BlockPos p_33804_, BlockState p_33805_) {
		this.playSound(SoundEvents.SPIDER_STEP, 1.0F, 0.6F);
	}


	@Override
	public void die(DamageSource p_21014_) {
		super.die(p_21014_);
		this.playSound(SoundEvents.SPIDER_DEATH, 1.0F, 0.6F);

		if (!this.level().isClientSide()) {
			this.level().broadcastEntityEvent(this, (byte) 101);
		}
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 38) {
		}
		if (this.deathTime == 40) {
		}

		if (this.deathTime == 100 && !this.level().isClientSide()) {
			this.level().broadcastEntityEvent(this, (byte) 101);
			this.remove(Entity.RemovalReason.KILLED);
		}
	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return false;
	}

	@Override
	public boolean hurtServer(ServerLevel serverLevel, DamageSource p_31461_, float p_31462_) {
		if (this.isInvulnerableTo(serverLevel, p_31461_)) {
			return false;
		} else if (!p_31461_.is(DamageTypes.SWEET_BERRY_BUSH) && !p_31461_.is(DamageTypes.CACTUS) && !p_31461_.is(DamageTypes.CRAMMING) && !p_31461_.is(DamageTypes.IN_WALL) && !p_31461_.is(DamageTypes.STALAGMITE)) {
			Entity entity = p_31461_.getDirectEntity();

			if (!this.isAngry() && this.getHealth() < this.getMaxHealth() / 2) {
				setAngry(true);
				this.playSound(SoundEvents.WITHER_BREAK_BLOCK, 2.0F, 1.0F);
			}

			if (entity instanceof FukumameEntity || !this.isAngry() && (p_31461_.is(DamageTypes.MAGIC) || p_31461_.is(DamageTypes.INDIRECT_MAGIC))) {
				return false;
			} else if (entity instanceof Projectile) {
				return super.hurtServer(serverLevel, p_31461_, p_31462_ * 0.35F);
			}

			return super.hurtServer(serverLevel, p_31461_, p_31462_);
		} else {
			return false;
		}
	}

	public boolean isPushable() {
		return false;
	}

	public boolean causeFallDamage(float p_148989_, float p_148990_, DamageSource p_148991_) {
		return false;
	}

	protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) {
	}

	public void makeStuckInBlock(BlockState p_20006_, Vec3 p_20007_) {
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 500.0D).add(Attributes.FOLLOW_RANGE, 32F).add(Attributes.MOVEMENT_SPEED, 0.4D).add(Attributes.ATTACK_KNOCKBACK, 1.00F).add(Attributes.KNOCKBACK_RESISTANCE, 5.0D).add(Attributes.ARMOR, 14.0D).add(Attributes.ARMOR_TOUGHNESS, 1.0F).add(Attributes.ATTACK_DAMAGE, 19.0D);
	}

	@Override
	public boolean canAttack(LivingEntity p_20355_) {
		if (p_20355_ instanceof ShuDofuSpider || p_20355_ instanceof TofuSpider) {
			return this.getTeam() == null && p_20355_.getTeam() == null;
		}

		if (p_20355_ instanceof NattoCobWebEntity) {
			return false;
		}

		return super.canAttack(p_20355_);
	}

	protected int decreaseAirSupply(int p_28882_) {
		return p_28882_;
	}

	public boolean hurt(ShuDofuSpiderPart shuDofuSpiderPart, DamageSource damageSource, float damage) {
		float f = this.getHealth();
		if (this.level() instanceof ServerLevel serverLevel) {
			this.reallyHurt(serverLevel, damageSource, damage * 0.9F);
		}
		if (this.isGraspAnim()) {
			this.graspDamageReceived = this.graspDamageReceived + f - this.getHealth();
			if (this.graspDamageReceived > 0.075F * this.getMaxHealth()) {
				this.graspDamageReceived = 0.0F;
				this.setGraspAnimation(false);
				this.attackTime = -40;
			}
		}

		return true;

	}

	private boolean reallyHurt(ServerLevel serverLevel, DamageSource damageSource, float damage) {
		return super.hurtServer(serverLevel, damageSource, damage);
	}

	

	static class AttackGoal extends Goal {
		private final ShuDofuSpider spider;
		private int attackTime;
		private int attackStep;
		private int lastSeen;
		private Path path;

		public AttackGoal(ShuDofuSpider p_32247_) {
			this.spider = p_32247_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public boolean canUse() {
			LivingEntity livingentity = this.spider.getTarget();
			if (livingentity == null) {
				return false;
			} else if (!livingentity.isAlive()) {
				return false;
			} else if (this.spider.isGraspAnim()) {
				return false;
			} else if (this.spider.isJumpAnim()) {
				return false;
			} else {
				this.path = this.spider.getNavigation().createPath(livingentity, 0);
				if (this.path != null) {
					return true;
				} else {
					return this.spider.canAttack(livingentity);
				}
			}
		}

		public void start() {
			this.attackStep = 0;
		}

		public void stop() {
			this.lastSeen = 0;
		}

		protected double getAttackReachSqr(LivingEntity p_29587_) {
			return 4.0F + p_29587_.getBbWidth();
		}

		public void tick() {
			--this.attackTime;

			var entity = this.spider;
			var entityTarget = entity.getTarget();

			if (entityTarget != null) {
				boolean flag = this.spider.getSensing().hasLineOfSight(entityTarget);

				double d0 = this.spider.distanceToSqr(entityTarget);
				if (!this.spider.isRanged()) {
					this.spider.getNavigation().moveTo(this.path, 0.8);
				}
				if (!flag) {
					return;
				}
				if (entity.isWithinMeleeAttackRange(entityTarget) && attackTime <= 10) {
					entity.setAttackAnimation(true);
					this.attackTime = 20;
					this.attackStep = 0;
				}
				if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
					if (this.attackTime <= 0) {
						++this.attackStep;
						if (this.attackStep == 1) {
							this.attackTime = 20;
						} else if (this.attackStep <= 2) {
							this.attackTime = 10;
						} else {
							this.attackTime = 20;
							this.attackStep = 0;
						}

						if (this.attackStep > 1) {
							if (!entity.isRanged()) {
								this.spider.setRanged(true);
							}
						}
					}
					entity.getLookControl().setLookAt(entityTarget, 10.0F, 10.0F);
				}
			}

			super.tick();
		}

		public boolean requiresUpdateEveryTick() {
			return true;
		}

		private double getFollowDistance() {
			return this.spider.getAttributeValue(Attributes.FOLLOW_RANGE);
		}
	}

	static class graspAttackGoal extends Goal {
		private final ShuDofuSpider spider;
		private int attackTime;
		private int preAttackTime;

		public graspAttackGoal(ShuDofuSpider p_32247_) {
			this.spider = p_32247_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		public boolean canUse() {
			if (attackTime == 0) {
				LivingEntity livingentity = this.spider.getTarget();
				if (livingentity != null && this.spider.isAngry() && livingentity.isAlive() && 10 >= this.spider.distanceTo(livingentity)) {
					if (livingentity.getMotionDirection() != livingentity.getDirection()) {
						return false;
					} else {
						boolean flag = ShuDofuSpider.isPathClear(this.spider, livingentity);
						if (!flag) {
							this.spider.getNavigation().createPath(livingentity, 0);
						}
						this.attackTime = 350;
						return flag;
					}
				} else {
					return false;
				}
			} else {
				--this.attackTime;
				return false;
			}
		}

		@Override
		public boolean canContinueToUse() {
			return this.preAttackTime < 30;
		}

		public boolean isInterruptable() {
			return false;
		}

		public void start() {
			LivingEntity livingentity = this.spider.getTarget();
			if (livingentity != null) {
				this.spider.level().broadcastEntityEvent(this.spider, (byte) 104);
				this.spider.playAmbientSound();
			}
			this.preAttackTime = 0;
		}

		public void tick() {
			this.spider.getNavigation().stop();
			++this.preAttackTime;
		}

		@Override
		public void stop() {
			super.stop();
			LivingEntity livingentity = this.spider.getTarget();
			if (livingentity != null) {
				this.spider.setGraspAnimation(true);
			}
		}

		@Override
		public boolean requiresUpdateEveryTick() {
			return true;
		}
	}

	public class JumpAttackGoal extends JumpGoal {
		private int attackTime;

		public boolean canUse() {
			if (attackTime == 0) {
				if (ShuDofuSpider.this.getHealth() <= ShuDofuSpider.this.getMaxHealth() / 1.5F && !ShuDofuSpider.this.isGraspAnim()) {
					LivingEntity livingentity = ShuDofuSpider.this.getTarget();
					if (livingentity != null && livingentity.isAlive() && 10 >= ShuDofuSpider.this.distanceTo(livingentity)) {
						if (ShuDofuSpider.this.random.nextInt(2) == 0) {
							return false;
						}
						if (livingentity.getMotionDirection() != livingentity.getDirection()) {
							return false;
						} else {
							boolean flag = ShuDofuSpider.isPathClear(ShuDofuSpider.this, livingentity);
							if (!flag) {
								ShuDofuSpider.this.getNavigation().createPath(livingentity, 0);
							}
							ShuDofuSpider.this.setJump(true);
							this.attackTime = 120;
							return flag;
						}
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				--this.attackTime;
				return false;
			}
		}

		public boolean canContinueToUse() {
			LivingEntity livingentity = ShuDofuSpider.this.getTarget();
			if (livingentity != null && livingentity.isAlive()) {
				double d0 = ShuDofuSpider.this.getDeltaMovement().y;
				return (!(d0 * d0 < (double) 0.05F) || !(Math.abs(ShuDofuSpider.this.getXRot()) < 15.0F) || !ShuDofuSpider.this.onGround());
			} else {
				return false;
			}
		}

		public boolean isInterruptable() {
			return false;
		}

		public void start() {
			ShuDofuSpider.this.setJumping(true);
			LivingEntity livingentity = ShuDofuSpider.this.getTarget();
			if (livingentity != null) {
				ShuDofuSpider.this.setJumpAnimation(true);
				ShuDofuSpider.this.getLookControl().setLookAt(livingentity, 60.0F, 60F);
				Vec3 vec3 = (new Vec3(livingentity.getX() - ShuDofuSpider.this.getX(), livingentity.getY() - ShuDofuSpider.this.getY(), livingentity.getZ() - ShuDofuSpider.this.getZ())).normalize();
				ShuDofuSpider.this.setDeltaMovement(ShuDofuSpider.this.getDeltaMovement().add(vec3.x * 1.2D, 1.2D, vec3.z * 1.2D));
			}

			ShuDofuSpider.this.getNavigation().stop();
		}

		public void stop() {
			ShuDofuSpider.this.setJump(false);
		}

		public void tick() {
			LivingEntity livingentity = ShuDofuSpider.this.getTarget();
			Vec3 vec3 = ShuDofuSpider.this.getDeltaMovement();
			if (vec3.y * vec3.y < (double) 0.03F && ShuDofuSpider.this.getXRot() != 0.0F) {

				ShuDofuSpider.this.setXRot(Mth.rotLerp(ShuDofuSpider.this.getXRot(), 0.0F, 0.2F));
			} else {
				double d0 = vec3.horizontalDistance();
				double d1 = Math.signum(-vec3.y) * Math.acos(d0 / vec3.length()) * (double) (180F / (float) Math.PI);
				ShuDofuSpider.this.setXRot((float) d1);
			}

		}
	}

	@Override
	protected void customServerAiStep(ServerLevel serverLevel) {
		this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
	}

	public static boolean isPathClear(ShuDofuSpider p_28472_, LivingEntity p_28473_) {
		double d0 = p_28473_.getZ() - p_28472_.getZ();
		double d1 = p_28473_.getX() - p_28472_.getX();
		double d2 = d0 / d1;

		for (int j = 0; j < 6; ++j) {
			double d3 = d2 == 0.0D ? 0.0D : d0 * (double) ((float) j / 6.0F);
			double d4 = d2 == 0.0D ? d1 * (double) ((float) j / 6.0F) : d3 / d2;

			for (int k = 1; k < 4; ++k) {
				if (!p_28472_.level().getBlockState(BlockPos.containing(p_28472_.getX() + d4, p_28472_.getY() + (double) k, p_28472_.getZ() + d3)).canBeReplaced()) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);
		PacketDistributor.sendToPlayer(player, new BossInfoPacket.Display(this.bossEvent.getId(), this.getId()));
		this.bossEvent.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);
		PacketDistributor.sendToPlayer(player, new BossInfoPacket.Remove(this.bossEvent.getId(), this.getId()));
		this.bossEvent.removePlayer(player);
	}


	@Override
	public void addAdditionalSaveData(CompoundTag p_21484_) {
		super.addAdditionalSaveData(p_21484_);
		p_21484_.putBoolean("Angry", this.isAngry());
	}

	public void readAdditionalSaveData(CompoundTag p_31474_) {
		super.readAdditionalSaveData(p_31474_);
		this.setAngry(p_31474_.getBoolean("Angry"));
		if (this.hasCustomName()) {
			this.bossEvent.setName(this.getDisplayName());
		}
	}

	public void setCustomName(@Nullable Component p_31476_) {
		super.setCustomName(p_31476_);
		this.bossEvent.setName(this.getDisplayName());
	}

	public void setAttackAnimation(boolean attack) {
		this.entityData.set(ATTACK_ANIMATION, attack);
	}

	public boolean isAttackAnim() {
		return this.entityData.get(ATTACK_ANIMATION);
	}

	public void setGraspAnimation(boolean grasp) {
		this.entityData.set(GRASP_ANIMATION, grasp);
	}

	public boolean isGraspAnim() {
		return this.entityData.get(GRASP_ANIMATION);
	}

	public void setJumpAnimation(boolean jump) {
		this.entityData.set(JUMP_ANIMATION, jump);
	}

	public boolean isJumpAnim() {
		return entityData.get(JUMP_ANIMATION);
	}

	private void setJump(boolean jump) {
		this.entityData.set(DATA_ID_JUMP, jump);
	}

	public boolean isJump() {
		return this.entityData.get(DATA_ID_JUMP);
	}

	public void setRanged(boolean ranged) {
		this.entityData.set(DATA_ID_RANGED, ranged);
	}

	public boolean isRanged() {
		return this.entityData.get(DATA_ID_RANGED);
	}

	public void setAngry(boolean angry) {
		this.entityData.set(ANGRY, angry);
		if (this.level() != null && !this.level().isClientSide) {
			AttributeInstance attributeinstance = this.getAttribute(Attributes.ATTACK_DAMAGE);
			attributeinstance.removeModifier(ATTACK_MODIFIER.id());
			AttributeInstance attributeinstance2 = this.getAttribute(Attributes.ARMOR);
			attributeinstance2.removeModifier(ARMOR_MODIFIER.id());
			if (angry) {
				attributeinstance.addTransientModifier(ATTACK_MODIFIER);
				attributeinstance2.addTransientModifier(ARMOR_MODIFIER);
			}
		}
	}

	public boolean isAngry() {
		return this.entityData.get(ANGRY);
	}

	@Override
	public PartEntity<?>[] getParts() {
		return subEntities;
	}

	@Override
	public boolean isMultipartEntity() {
		return true;
	}
}

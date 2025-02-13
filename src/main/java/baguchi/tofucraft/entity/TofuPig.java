package baguchi.tofucraft.entity;

import baguchi.tofucraft.registry.TofuBiomes;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuParticleTypes;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.List;

public class TofuPig extends Pig implements ItemInteractable {
	private static final Ingredient FOOD_ITEMS = Ingredient.of(TofuItems.LEEK.get(), Items.CARROT);
	private static final EntityDataAccessor<Integer> DATA_HEALING_TIME = SynchedEntityData.defineId(TofuPig.class, EntityDataSerializers.INT);
	private final ItemBasedInteractable healilng = new HealInteractable(this.entityData, DATA_HEALING_TIME);

	private static final EntityDataAccessor<String> TOFUPIG_TYPE = SynchedEntityData.defineId(TofuPig.class, EntityDataSerializers.STRING);

	public TofuPig(EntityType<? extends TofuPig> p_29462_, Level p_29463_) {
		super(p_29462_, p_29463_);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, itemstack -> itemstack.is(TofuTags.Items.TOFU_PIG_FOOD), false));
		this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.25D);
	}

	public static boolean checkTofuAnimalSpawnRules(EntityType<? extends Animal> p_27578_, LevelAccessor p_27579_, EntitySpawnReason p_27580_, BlockPos p_27581_, RandomSource p_27582_) {
		return p_27579_.getBlockState(p_27581_.below()).is(TofuTags.Blocks.TOFU_TERRAIN) && p_27579_.getRawBrightness(p_27581_, 0) > 8;
	}

	public InteractionResult mobInteract(Player p_28298_, InteractionHand p_28299_) {
		ItemStack var3 = p_28298_.getItemInHand(p_28299_);
		if (var3.is(TofuItems.TOFUMETAL.get()) && !this.isBaby() && this.getTofuPigType().equals(TofuPigType.NORMAL)) {
			p_28298_.playSound(SoundEvents.ANVIL_USE, 1.0F, 1.0F);
			var3.shrink(1);
			this.setTofuPigType(TofuPigType.METAL);
			return InteractionResult.SUCCESS;
		} else if (var3.is(TofuItems.TOFUGRILLED.get()) && !this.isBaby() && this.getTofuPigType().equals(TofuPigType.NORMAL)) {
			p_28298_.playSound(SoundEvents.GENERIC_EAT.value(), 1.0F, 1.0F);
			var3.shrink(1);
			this.setTofuPigType(TofuPigType.GRILLED);
			return InteractionResult.SUCCESS;
		} else if (var3.is(TofuItems.TOFUZUNDA.get()) && !this.isBaby() && this.getTofuPigType().equals(TofuPigType.NORMAL)) {
			p_28298_.playSound(SoundEvents.GENERIC_EAT.value(), 1.0F, 1.0F);
			var3.shrink(1);
			this.setTofuPigType(TofuPigType.ZUNDA);
			return InteractionResult.SUCCESS;
		} else {
			return super.mobInteract(p_28298_, p_28299_);
		}
	}

	@Nullable
	public LivingEntity getControllingPassenger() {
		if (this.isSaddled()) {
			Entity entity = this.getFirstPassenger();
			if (entity instanceof Player) {
				Player player = (Player) entity;
				if (this.getTofuPigType() == TofuPigType.ZUNDA) {
					if (player.getMainHandItem().is(TofuItems.ZUNDAMUSHROOM_ON_A_STICK.get()) || player.getOffhandItem().is(TofuItems.ZUNDAMUSHROOM_ON_A_STICK.get())) {
						return player;
					}
				}
				if (player.getMainHandItem().is(Items.CARROT_ON_A_STICK) || player.getOffhandItem().is(Items.CARROT_ON_A_STICK)) {
					return player;
				}
			}
		}

		return null;
	}

	public void thunderHit(ServerLevel p_29473_, LightningBolt p_29474_) {
		if (this.getTofuPigType() == TofuPigType.NORMAL) {
			this.setTofuPigType(TofuPigType.ZUNDA);
		}
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_, EntitySpawnReason p_146748_, @org.jetbrains.annotations.Nullable SpawnGroupData p_146749_) {
		if (p_146746_.getBiome(this.blockPosition()).is(TofuBiomes.ZUNDA_FOREST)) {
			this.setTofuPigType(TofuPigType.ZUNDA);
		}
		return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_);
	}

	public boolean isActivated() {
		return this.healilng.on;
	}

	@Override
	public void tick() {
		if (this.isActivated() && this.getTofuPigType() == TofuPigType.ZUNDA) {
			this.healEffect();
			this.healilng.deActive();
		}
		super.tick();
	}

	@Override
	public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float damage) {
		if (this.getTofuPigType() == TofuPigType.METAL) {
			damage = (float) (damage * 0.5);
		} else if (this.getTofuPigType() == TofuPigType.GRILLED && source.is(DamageTypeTags.IS_FIRE)) {
			return false;
		}
		return super.hurtServer(serverLevel, source, damage);
	}

	public void setTofuPigType(TofuPigType type) {
		this.entityData.set(TOFUPIG_TYPE, type.name());
	}

	public TofuPig.TofuPigType getTofuPigType() {
		return TofuPig.TofuPigType.get(this.entityData.get(TOFUPIG_TYPE));
	}

	public boolean canHeal() {
		return this.healilng.active(this.getRandom());
	}

	private void healEffect() {
		float radius = 5;
		AABB box = new AABB(this.getX() - radius, this.getY() - 1, this.getZ() - radius, this.getX() + radius, this.getY() + 3, this.getZ() + radius);
		List<LivingEntity> hitEntities = this.level().getEntitiesOfClass(LivingEntity.class, box);
		for (LivingEntity hitEntity : hitEntities) {
			if (!hitEntity.level().isClientSide) {
				hitEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200));
			}
		}
		if (this.level().isClientSide) {
			int count = 20;
			for (int i = 1; i <= count; i++) {
				double yaw = i * 365f / count;
				double speed = 0.5;
				double xSpeed = speed * Math.cos(Math.toRadians(yaw));
				double zSpeed = speed * Math.sin(Math.toRadians(yaw));
				this.level().addParticle(TofuParticleTypes.ZUNDA_CLOUD.get(), this.getX(), this.getY(), this.getZ(), xSpeed, 0, zSpeed);
			}
		}
	}

	@Override
	public TofuPig getBreedOffspring(ServerLevel p_148890_, AgeableMob p_148891_) {
		TofuPig tofuPig = TofuEntityTypes.TOFUPIG.get().create(p_148890_, EntitySpawnReason.BREEDING);
		if (tofuPig != null) {
			TofuPigType variant = this.random.nextBoolean() ? this.getTofuPigType() : ((TofuPig) p_148891_).getTofuPigType();

			tofuPig.setTofuPigType(variant);
		}
		return tofuPig;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("TofuPigType", getTofuPigType().name());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("TofuPigType")) {
			setTofuPigType(TofuPig.TofuPigType.get(compound.getString("TofuPigType")));
		}
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> p_29480_) {
		if (DATA_HEALING_TIME.equals(p_29480_) && this.level().isClientSide) {
			this.healilng.onSynced();
		}

		super.onSyncedDataUpdated(p_29480_);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(TOFUPIG_TYPE, "NORMAL");
		builder.define(DATA_HEALING_TIME, 0);
	}

	public float getSteeringSpeed() {
		return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.35F;
	}

	public boolean isFood(ItemStack p_27600_) {
		return p_27600_.is(TofuTags.Items.TOFU_PIG_FOOD);
	}

	public enum TofuPigType {
		NORMAL,
		METAL,
		GRILLED,
		ZUNDA;

		public static TofuPig.TofuPigType get(String nameIn) {
			for (TofuPig.TofuPigType role : values()) {
				if (role.name().equals(nameIn))
					return role;
			}
			return NORMAL;
		}
	}
}
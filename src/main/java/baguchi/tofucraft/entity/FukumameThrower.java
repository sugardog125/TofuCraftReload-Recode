package baguchi.tofucraft.entity;

import com.mojang.serialization.Dynamic;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

public class FukumameThrower extends Piglin {
	private static final EntityDataAccessor<Boolean> DATA_CHARGE = SynchedEntityData.defineId(FukumameThrower.class, EntityDataSerializers.BOOLEAN);

	private static final EntityDataAccessor<Integer> DATA_FUKUMAME_COUNT = SynchedEntityData.defineId(FukumameThrower.class, EntityDataSerializers.INT);

	public FukumameThrower(EntityType<? extends FukumameThrower> p_34683_, Level p_34684_) {
		super(p_34683_, p_34684_);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_CHARGE, false);
		builder.define(DATA_FUKUMAME_COUNT, 64);
	}

	public void setCharge(boolean charge) {
		this.entityData.set(DATA_CHARGE, charge);
	}

	public boolean isCharge() {
		return this.entityData.get(DATA_CHARGE);
	}

	public void setFukumameCount(int fukumame) {
		this.entityData.set(DATA_FUKUMAME_COUNT, fukumame);
	}

	public int getFukumameCount() {
		return this.entityData.get(DATA_FUKUMAME_COUNT);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag p_34751_) {
		super.addAdditionalSaveData(p_34751_);
		p_34751_.putInt("FukumameCount", this.getFukumameCount());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag p_34725_) {
		super.readAdditionalSaveData(p_34725_);
		this.setFukumameCount(p_34725_.getInt("FukumameCount"));
	}

	@Override
	public boolean canHunt() {
		return true;
	}

	protected Brain.Provider<FukumameThrower> revampedBrainProvider() {
		return Brain.provider(Piglin.MEMORY_TYPES, SENSOR_TYPES);
	}


	@Override
	protected Brain<?> makeBrain(Dynamic<?> p_34723_) {
		return FukumameThrowerAi.makeBrain(this, this.revampedBrainProvider().makeBrain(p_34723_));
	}

	public ItemStack addToInventory(ItemStack p_34779_) {
		return super.addToInventory(p_34779_);
	}

	public boolean canAddToInventory(ItemStack p_34781_) {
		return super.canAddToInventory(p_34781_);
	}

	public void eatFukumame() {
		this.heal(1);
		this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600));
	}

	public boolean canReplaceCurrentItem(ItemStack p_34788_) {
		EquipmentSlot equipmentslot = this.getEquipmentSlotForItem(p_34788_);
		ItemStack itemstack = this.getItemBySlot(equipmentslot);
		return this.canReplaceCurrentItem(p_34788_, itemstack, equipmentslot);
	}

	public void holdInMainHand(ItemStack p_34784_) {
		this.setItemSlotAndDropWhenKilled(EquipmentSlot.MAINHAND, p_34784_);
	}

	public void holdInOffHand(ItemStack p_34786_) {
		if (p_34786_.isPiglinCurrency()) {
			this.setItemSlot(EquipmentSlot.OFFHAND, p_34786_);
			this.setGuaranteedDrop(EquipmentSlot.OFFHAND);
		} else {
			this.setItemSlotAndDropWhenKilled(EquipmentSlot.OFFHAND, p_34786_);
		}

	}

	@Override
	public boolean isBaby() {
		return false;
	}

	public static boolean checkFukumameSpawnRules(EntityType<? extends FukumameThrower> p_219198_, LevelAccessor p_219199_, EntitySpawnReason p_219200_, BlockPos p_219201_, RandomSource p_219202_) {
		return !p_219199_.getBlockState(p_219201_.below()).is(Blocks.NETHER_WART_BLOCK);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34717_, DifficultyInstance p_34718_, EntitySpawnReason p_34719_, @Nullable SpawnGroupData p_34720_) {

		return super.finalizeSpawn(p_34717_, p_34718_, p_34719_, p_34720_);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource source, DifficultyInstance pDifficulty) {
		super.populateDefaultEquipmentSlots(source, pDifficulty);
		if (this.isAdult()) {
			this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
		}
		this.setFukumameCount(32 + random.nextInt(32));
	}
}

package baguchi.tofucraft.entity;

import baguchi.tofucraft.registry.TofuBiomes;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuFluids;
import baguchi.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
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
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.FluidUtil;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.IFluidHandlerItem;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;

import java.util.concurrent.atomic.AtomicReference;

public class TofuCow extends Cow {
	private static final EntityDataAccessor<String> TOFUCOW_TYPE = SynchedEntityData.defineId(TofuCow.class, EntityDataSerializers.STRING);


	public TofuCow(EntityType<? extends Cow> p_28285_, Level p_28286_) {
		super(p_28285_, p_28286_);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(TOFUCOW_TYPE, TofuCowType.NORMAL.name());
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, itemstack -> itemstack.is(TofuTags.Items.TOFU_COW_FOOD), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Animal.createAnimalAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224D);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("TofuCowType", getTofuCowType().name());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("TofuCowType")) {
			setTofuCowType(TofuCowType.get(compound.getString("TofuCowType")));
		}
	}

	public void setTofuCowType(TofuCowType type) {
		this.entityData.set(TOFUCOW_TYPE, type.name());
	}

	public TofuCowType getTofuCowType() {
		return TofuCowType.get(this.entityData.get(TOFUCOW_TYPE));
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_146746_, DifficultyInstance p_146747_, EntitySpawnReason p_146748_, @org.jetbrains.annotations.Nullable SpawnGroupData p_146749_) {
		if (p_146746_.getBiome(this.blockPosition()).is(TofuBiomes.ZUNDA_FOREST)) {
			this.setTofuCowType(TofuCowType.ZUNDA);
		}


		return super.finalizeSpawn(p_146746_, p_146747_, p_146748_, p_146749_);
	}


	public static boolean checkTofuAnimalSpawnRules(EntityType<? extends Animal> p_27578_, LevelAccessor p_27579_, EntitySpawnReason p_27580_, BlockPos p_27581_, RandomSource p_27582_) {
		return p_27579_.getBlockState(p_27581_.below()).is(TofuTags.Blocks.TOFU_TERRAIN) && p_27579_.getRawBrightness(p_27581_, 0) > 8;
	}

	public InteractionResult mobInteract(Player p_28298_, InteractionHand p_28299_) {
		ItemStack itemstack = p_28298_.getItemInHand(p_28299_);
		if (!this.isBaby()) {
			IFluidHandlerItem handler = FluidUtil.getFluidHandler(itemstack.copyWithCount(1)).orElse(null);
			if (handler != null && handler instanceof FluidBucketWrapper && ((FluidBucketWrapper) handler).getFluid().isEmpty()) {
				p_28298_.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
				AtomicReference<ItemStack> resultItemStack = new AtomicReference<>(itemstack.copy());
				FluidUtil.getFluidHandler(resultItemStack.get()).ifPresent(fluidHandler -> {
					fluidHandler.fill(new FluidStack(TofuFluids.SOYMILK.get(), FluidType.BUCKET_VOLUME), IFluidHandler.FluidAction.EXECUTE);
					resultItemStack.set(fluidHandler.getContainer());
				});
				p_28298_.setItemInHand(p_28299_, resultItemStack.get());
				return InteractionResult.SUCCESS;
			}
		}
		return super.mobInteract(p_28298_, p_28299_);
	}

	@Override
	public TofuCow getBreedOffspring(ServerLevel p_148890_, AgeableMob p_148891_) {
		TofuCow tofuCow = TofuEntityTypes.TOFUCOW.get().create(p_148890_, EntitySpawnReason.BREEDING);
		if (tofuCow != null) {
			TofuCowType variant = this.random.nextBoolean() ? this.getTofuCowType() : ((TofuCow) p_148891_).getTofuCowType();

			tofuCow.setTofuCowType(variant);
		}
		return tofuCow;
	}

	@Override
	public boolean isFood(ItemStack p_335696_) {
		return p_335696_.is(TofuTags.Items.TOFU_COW_FOOD);
	}

	public enum TofuCowType {
		NORMAL,
		ZUNDA;

		TofuCowType() {

		}

		public static TofuCowType get(String nameIn) {
			for (TofuCowType role : values()) {
				if (role.name().equals(nameIn))
					return role;
			}
			return NORMAL;
		}
	}
}

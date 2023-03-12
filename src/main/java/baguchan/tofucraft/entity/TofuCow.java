package baguchan.tofucraft.entity;

import baguchan.tofucraft.registry.TofuEntityTypes;
import baguchan.tofucraft.registry.TofuFluids;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class TofuCow extends Cow {
	public TofuCow(EntityType<? extends Cow> p_28285_, Level p_28286_) {
		super(p_28285_, p_28286_);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(TofuItems.LEEK.get()), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.MOVEMENT_SPEED, 0.20000000298023224D);
	}

	public static boolean checkTofuAnimalSpawnRules(EntityType<? extends Animal> p_27578_, LevelAccessor p_27579_, MobSpawnType p_27580_, BlockPos p_27581_, Random p_27582_) {
		return p_27579_.getBlockState(p_27581_.below()).is(TofuTags.Blocks.TOFU_TERRAIN) && p_27579_.getRawBrightness(p_27581_, 0) > 8;
	}

	public InteractionResult mobInteract(Player p_28298_, InteractionHand p_28299_) {
		ItemStack itemstack = p_28298_.getItemInHand(p_28299_);
		if (!this.isBaby()) {
			IFluidHandlerItem handler = FluidUtil.getFluidHandler(ItemHandlerHelper.copyStackWithSize(itemstack, 1)).orElse(null);
			if (handler != null && handler instanceof net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper && ((FluidBucketWrapper) handler).getFluid().isEmpty()) {
				p_28298_.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
				AtomicReference<ItemStack> resultItemStack = new AtomicReference<>(itemstack.copy());
				FluidUtil.getFluidHandler(resultItemStack.get()).ifPresent(fluidHandler -> {
					fluidHandler.fill(new FluidStack(TofuFluids.SOYMILK.get(), 1000), IFluidHandler.FluidAction.EXECUTE);
					resultItemStack.set(fluidHandler.getContainer());
				});
				p_28298_.setItemInHand(p_28299_, resultItemStack.get());
				return InteractionResult.sidedSuccess(this.level.isClientSide);
			}
		}
		return super.mobInteract(p_28298_, p_28299_);
	}

	@Override
	public TofuCow getBreedOffspring(ServerLevel p_148890_, AgeableMob p_148891_) {
		return TofuEntityTypes.TOFUCOW.get().create(p_148890_);
	}

	public boolean isFood(ItemStack p_27600_) {
		return p_27600_.is(TofuItems.LEEK.get());
	}
}

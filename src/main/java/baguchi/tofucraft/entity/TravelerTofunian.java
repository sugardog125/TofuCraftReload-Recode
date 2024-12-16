package baguchi.tofucraft.entity;

import baguchi.tofucraft.entity.goal.LookAtTofunianTradingPlayerGoal;
import baguchi.tofucraft.entity.goal.TofunianTradeWithPlayerGoal;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuSounds;
import baguchi.tofucraft.registry.TofuTrades;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.InteractGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.UseItemGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class TravelerTofunian extends AbstractTofunian {
	private static final int NUMBER_OF_TRADE_OFFERS = 5;
	@Nullable
	private BlockPos wanderTarget;
	private int despawnDelay;

	public TravelerTofunian(EntityType<? extends TravelerTofunian> p_35843_, Level p_35844_) {
		super(p_35843_, p_35844_);
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(0, new UseItemGoal<>(this, PotionContents.createItemStack(Items.POTION, Potions.INVISIBILITY), TofuSounds.TOFUNIAN_YES.get(), (p_35882_) -> {
			return this.level().isNight() && !p_35882_.isInvisible();
		}));
		this.goalSelector.addGoal(0, new UseItemGoal<>(this, new ItemStack(Items.MILK_BUCKET), TofuSounds.TOFUNIAN_YES.get(), (p_35880_) -> {
			return this.level().isDay() && p_35880_.isInvisible();
		}));
		this.goalSelector.addGoal(1, new TofunianTradeWithPlayerGoal(this));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zombie.class, 8.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Evoker.class, 12.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vindicator.class, 8.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vex.class, 8.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Pillager.class, 15.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Illusioner.class, 12.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zoglin.class, 10.0F, 1.2D, 1.2D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, ShuDofuSpider.class, 10.0F, 1.2D, 1.3D));
		this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, TofuGandlem.class, 10.0F, 1.2D, 1.3D));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
		this.goalSelector.addGoal(1, new LookAtTofunianTradingPlayerGoal(this));
		this.goalSelector.addGoal(2, new TravelerTofunian.WanderToPositionGoal(this, 2.0D, 1.2D));
		this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1.1D));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
	}

	@Nullable
	public AgeableMob getBreedOffspring(ServerLevel p_150046_, AgeableMob p_150047_) {
		return null;
	}

	public boolean showProgressBar() {
		return false;
	}

	public InteractionResult mobInteract(Player p_35856_, InteractionHand p_35857_) {
		ItemStack itemstack = p_35856_.getItemInHand(p_35857_);
		if (!itemstack.is(TofuItems.TRAVELER_TOFUNIAN_SPAWNEGG.get()) && this.isAlive() && !this.isTrading() && !this.isBaby()) {
			if (p_35857_ == InteractionHand.MAIN_HAND) {
				p_35856_.awardStat(Stats.TALKED_TO_VILLAGER);
			}

			if (this.getOffers().isEmpty()) {
				return InteractionResult.CONSUME;
			} else {
				if (!this.level().isClientSide) {
					this.setTradingPlayer(p_35856_);
					this.openTradingScreen(p_35856_, this.getDisplayName(), 1);
				}

				return InteractionResult.SUCCESS;
			}
		} else {
			return super.mobInteract(p_35856_, p_35857_);
		}
	}

	protected void updateTrades() {
		VillagerTrades.ItemListing[] avillagertrades$itemlisting = TofuTrades.TRAVELER_TOFUNIAN_TRADE.get(1);
		VillagerTrades.ItemListing[] avillagertrades$itemlisting1 = TofuTrades.TRAVELER_TOFUNIAN_TRADE.get(2);
		if (avillagertrades$itemlisting != null && avillagertrades$itemlisting1 != null) {
			MerchantOffers merchantoffers = this.getOffers();
			this.addOffersFromItemListings(merchantoffers, avillagertrades$itemlisting, 3);
			this.addOffersFromItemListings(merchantoffers, avillagertrades$itemlisting1, 5);

		}
	}

	public void addAdditionalSaveData(CompoundTag p_35861_) {
		super.addAdditionalSaveData(p_35861_);
		p_35861_.putInt("DespawnDelay", this.despawnDelay);
		if (this.wanderTarget != null) {
			p_35861_.put("WanderTarget", NbtUtils.writeBlockPos(this.wanderTarget));
		}

	}

	public void readAdditionalSaveData(CompoundTag p_35852_) {
		super.readAdditionalSaveData(p_35852_);
		if (p_35852_.contains("DespawnDelay", 99)) {
			this.despawnDelay = p_35852_.getInt("DespawnDelay");
		}

		if (p_35852_.contains("WanderTarget")) {
			this.wanderTarget = NbtUtils.readBlockPos(p_35852_, "WanderTarget").orElse(null);
		}

		this.setAge(Math.max(0, this.getAge()));
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_35282_, DifficultyInstance p_35283_, EntitySpawnReason p_35284_, @org.jetbrains.annotations.Nullable SpawnGroupData p_35285_) {
		RandomSource randomsource = p_35282_.getRandom();
		this.populateDefaultEquipmentSlots(randomsource, p_35283_);

		return super.finalizeSpawn(p_35282_, p_35283_, p_35284_, p_35285_);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_217055_, DifficultyInstance p_217056_) {
		if (p_217055_.nextFloat() < 0.1F + p_217056_.getEffectiveDifficulty() * 0.01F) {
			this.setItemSlot(EquipmentSlot.FEET, new ItemStack(Items.LEATHER_BOOTS));
			this.setDropChance(EquipmentSlot.FEET, 0.0F);
		} else if (p_217055_.nextFloat() < 0.25F) {
			this.setItemSlot(EquipmentSlot.FEET, new ItemStack(TofuItems.TOFU_MOMEN_BOOTS));
			this.setDropChance(EquipmentSlot.FEET, 0.0F);
		}
	}

	public boolean removeWhenFarAway(double p_35886_) {
		return false;
	}

	protected void rewardTradeXp(MerchantOffer p_35859_) {
		if (p_35859_.shouldRewardExp()) {
			int i = 3 + this.random.nextInt(4);
			this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5D, this.getZ(), i));
		}

	}

	public void setDespawnDelay(int p_35892_) {
		this.despawnDelay = p_35892_;
	}

	public int getDespawnDelay() {
		return this.despawnDelay;
	}

	public void aiStep() {
		super.aiStep();
		if (!this.level().isClientSide) {
			this.maybeDespawn();
		}

	}

	private void maybeDespawn() {
		if (this.despawnDelay > 0 && !this.isTrading() && --this.despawnDelay == 0) {
			this.discard();
		}

	}

	public void setWanderTarget(@Nullable BlockPos p_35884_) {
		this.wanderTarget = p_35884_;
	}

	@Nullable
	BlockPos getWanderTarget() {
		return this.wanderTarget;
	}

	class WanderToPositionGoal extends Goal {
		final TravelerTofunian trader;
		final double stopDistance;
		final double speedModifier;

		WanderToPositionGoal(TravelerTofunian p_35899_, double p_35900_, double p_35901_) {
			this.trader = p_35899_;
			this.stopDistance = p_35900_;
			this.speedModifier = p_35901_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public void stop() {
			this.trader.setWanderTarget((BlockPos) null);
			TravelerTofunian.this.navigation.stop();
		}

		public boolean canUse() {
			BlockPos blockpos = this.trader.getWanderTarget();
			return blockpos != null && this.isTooFarAway(blockpos, this.stopDistance);
		}

		public void tick() {
			BlockPos blockpos = this.trader.getWanderTarget();
			if (blockpos != null && TravelerTofunian.this.navigation.isDone()) {
				if (this.isTooFarAway(blockpos, 10.0D)) {
					Vec3 vec3 = (new Vec3((double) blockpos.getX() - this.trader.getX(), (double) blockpos.getY() - this.trader.getY(), (double) blockpos.getZ() - this.trader.getZ())).normalize();
					Vec3 vec31 = vec3.scale(10.0D).add(this.trader.getX(), this.trader.getY(), this.trader.getZ());
					TravelerTofunian.this.navigation.moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
				} else {
					TravelerTofunian.this.navigation.moveTo((double) blockpos.getX(), (double) blockpos.getY(), (double) blockpos.getZ(), this.speedModifier);
				}
			}

		}

		private boolean isTooFarAway(BlockPos p_35904_, double p_35905_) {
			return !p_35904_.closerToCenterThan(this.trader.position(), p_35905_);
		}
	}
}
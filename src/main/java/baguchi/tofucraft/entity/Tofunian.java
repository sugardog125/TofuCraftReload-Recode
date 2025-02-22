package baguchi.tofucraft.entity;

import baguchi.tofucraft.api.entity.TofunianVariant;
import baguchi.tofucraft.data.resources.registries.TofunianVariants;
import baguchi.tofucraft.entity.goal.CropHarvestGoal;
import baguchi.tofucraft.entity.goal.DoSleepingGoal;
import baguchi.tofucraft.entity.goal.EatItemGoal;
import baguchi.tofucraft.entity.goal.FindJobBlockGoal;
import baguchi.tofucraft.entity.goal.FindStatueBlockGoal;
import baguchi.tofucraft.entity.goal.LookAtTofunianTradingPlayerGoal;
import baguchi.tofucraft.entity.goal.MakeFoodGoal;
import baguchi.tofucraft.entity.goal.MoveToJobGoal;
import baguchi.tofucraft.entity.goal.MoveToStatueGoal;
import baguchi.tofucraft.entity.goal.OpenTofuDoorGoal;
import baguchi.tofucraft.entity.goal.RestockGoal;
import baguchi.tofucraft.entity.goal.ShareItemAndGossipGoal;
import baguchi.tofucraft.entity.goal.TofunianLoveGoal;
import baguchi.tofucraft.entity.goal.TofunianSleepOnBedGoal;
import baguchi.tofucraft.entity.goal.TofunianTradeWithPlayerGoal;
import baguchi.tofucraft.entity.goal.WakeUpGoal;
import baguchi.tofucraft.registry.TofuAdvancements;
import baguchi.tofucraft.registry.TofuEntityDatas;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuParticleTypes;
import baguchi.tofucraft.registry.TofuSounds;
import baguchi.tofucraft.registry.TofunianTrades;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Dynamic;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.SpawnUtil;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ReputationEventHandler;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.InteractGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.gossip.GossipContainer;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.village.ReputationEventType;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.VillagerData;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Tofunian extends AbstractTofunian implements ReputationEventHandler, VariantHolder<Holder<TofunianVariant>> {

	private static final EntityDataAccessor<String> ACTION = SynchedEntityData.defineId(Tofunian.class, EntityDataSerializers.STRING);
	private static final EntityDataAccessor<String> ROLE = SynchedEntityData.defineId(Tofunian.class, EntityDataSerializers.STRING);
	private static final EntityDataAccessor<Holder<TofunianVariant>> DATA_VARIANT_ID = SynchedEntityData.defineId(Tofunian.class, TofuEntityDatas.TOFUNIAN_VARIANT.get());

	public static final Map<Item, Integer> FOOD_POINTS = ImmutableMap.of(TofuItems.SOYMILK.get(), 3, TofuItems.TOFUCOOKIE.get(), 3, TofuItems.TOFUGRILLED.get(), 1);

	private static final Set<Item> WANTED_ITEMS = ImmutableSet.of(TofuItems.SOYMILK.get(), TofuItems.TOFUCOOKIE.get(), TofuItems.TOFUGRILLED.get(), TofuItems.SEEDS_SOYBEANS.get());
	private static final Predicate<? super ItemEntity> ALLOWED_ITEMS = (p_213616_0_) -> {
		return WANTED_ITEMS.contains(p_213616_0_.getItem().getItem());
	};


	private byte foodLevel;
	private final GossipContainer gossips = new GossipContainer();

	@Nullable
	private BlockPos tofunianHome;

	@Nullable
	private BlockPos tofunianJobBlock;

	@Nullable
	private BlockPos villageCenter;

	private long lastGossipTime;
	private long lastGossipDecay;
	private long lastRestock;
	private int restocksToday;
	private long lastRestockDayTime;

	private int timeUntilReset;
	private boolean leveledUp;

	@Nullable
	private Player previousCustomer;


	private int xp;

	private int tofunianLevel = 1;

	private int actionTick;

	public final AnimationState happyAnimationState = new AnimationState();
	public final AnimationState eatFoodAnimationState = new AnimationState();
	public final AnimationState callAnimationState = new AnimationState();

	public Tofunian(EntityType<? extends Tofunian> type, Level worldIn) {
		super(type, worldIn);
		((GroundPathNavigation) getNavigation()).setCanOpenDoors(true);
		setCanPickUpLoot(true);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new WakeUpGoal(this));
		this.goalSelector.addGoal(0, new DoSleepingGoal(this));
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new TofunianTradeWithPlayerGoal(this) {
			@Override
			public void start() {
				super.start();
				setAction(Actions.NORMAL);
			}
		});
		this.goalSelector.addGoal(1, new OpenTofuDoorGoal(this, true));
		this.goalSelector.addGoal(1, new TofunianAvoidEntityGoal<>(this, Zombie.class, 8.0F, 1.25D, 1.3D));
		this.goalSelector.addGoal(1, new TofunianAvoidEntityGoal<>(this, AbstractIllager.class, 12.0F, 1.25D, 1.3D));
		this.goalSelector.addGoal(1, new TofunianAvoidEntityGoal<>(this, EnderMan.class, 10.0F, 1.25D, 1.3D));
		this.goalSelector.addGoal(1, new TofunianAvoidEntityGoal<>(this, Vex.class, 8.0F, 1.25D, 1.3D));
		this.goalSelector.addGoal(1, new TofunianAvoidEntityGoal<>(this, Zoglin.class, 10.0F, 1.25D, 1.3D));
		this.goalSelector.addGoal(1, new TofunianAvoidEntityGoal<>(this, ShuDofuSpider.class, 10.0F, 1.25D, 1.3D));
		this.goalSelector.addGoal(1, new TofunianAvoidEntityGoal<>(this, TofuGandlem.class, 10.0F, 1.25D, 1.3D));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.35D) {
			@Override
			public void start() {
				super.start();
				setAction(Actions.AVOID);
			}

			@Override
			public void stop() {
				super.stop();
				setAction(Actions.NORMAL);
			}
		});
		this.goalSelector.addGoal(1, new LookAtTofunianTradingPlayerGoal(this));
		this.goalSelector.addGoal(2, new TofunianSleepOnBedGoal(this, 0.85F, 6));
		this.goalSelector.addGoal(3, new EatItemGoal<>(this, null, (p_35882_) -> {
			return getHealth() < getMaxHealth();
		}));
		this.goalSelector.addGoal(4, new TofunianLoveGoal(this, 0.8F));
		this.goalSelector.addGoal(5, new GetItemGoal<>(this));
		this.goalSelector.addGoal(6, new MoveToStatueGoal(this, 0.8F, 5));
		this.goalSelector.addGoal(8, new CropHarvestGoal(this, 0.9F));
		this.goalSelector.addGoal(9, new MakeFoodGoal(this, 0.9F, 1));
		this.goalSelector.addGoal(10, new RestockGoal(this, 0.9F, 1));
		this.goalSelector.addGoal(11, new MoveToJobGoal(this, 0.9F, 1));
		this.goalSelector.addGoal(12, new MoveToGoal(this, 42.0D, 1.0D));
		this.goalSelector.addGoal(13, new FindJobBlockGoal(this, 0.85F, 6));
		this.goalSelector.addGoal(14, new FindStatueBlockGoal(this, 0.85F, 6));

		this.goalSelector.addGoal(15, new WaterAvoidingRandomStrollGoal(this, 0.9D));
		this.goalSelector.addGoal(16, new InteractGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(17, new ShareItemAndGossipGoal(this, 0.9F));
		this.goalSelector.addGoal(18, new InteractGoal(this, AbstractTofunian.class, 4.0F, 0.25F) {
			@Override
			public void start() {
				super.start();
				setAction(Actions.CALL);
			}
		});
		this.goalSelector.addGoal(18, new InteractGoal(this, Player.class, 3.0F, 1.0F) {
			@Override
			public void start() {
				super.start();
				setAction(Actions.CALL);
			}
		});

		this.goalSelector.addGoal(18, new LookAtPlayerGoal(this, Mob.class, 6.0F));

		this.goalSelector.addGoal(19, new RandomLookAroundGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.24D).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.SAFE_FALL_DISTANCE, 5.0F);
	}


	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
		Tofunian tofunian = TofuEntityTypes.TOFUNIAN.get().create(p_241840_1_, EntitySpawnReason.BREEDING);
		if (tofunian != null && p_241840_2_ instanceof Tofunian tofunian1) {
			if (this.random.nextBoolean()) {
				tofunian.setVariant(this.getVariant());
			} else {
				tofunian.setVariant(tofunian1.getVariant());
			}
		}
		return tofunian;
	}

	public boolean isMeeting() {
		long time = level().getDayTime();
		long day = time / 24000;
		return day % 5 == 0;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(ROLE, "NORMAL");
		builder.define(ACTION, "NORMAL");
		RegistryAccess registryaccess = this.registryAccess();
		Registry<TofunianVariant> registry = registryaccess.lookupOrThrow(TofunianVariants.TOFUNIAN_VARIANT_REGISTRY_KEY);
		builder.define(DATA_VARIANT_ID, registry.get(TofunianVariants.DEFAULT).or(registry::getAny).orElseThrow());

	}

	public Holder<TofunianVariant> getVariant() {
		return this.entityData.get(DATA_VARIANT_ID);
	}

	public void setVariant(Holder<TofunianVariant> p_332777_) {
		this.entityData.set(DATA_VARIANT_ID, p_332777_);
	}

	@Nullable
	public ResourceLocation getTexture() {
		TofunianVariant tofunianVariant = this.getVariant().value();
		if (this.getVariant().is(TofunianVariants.NORMAL)) {
			return null;
		}
		return tofunianVariant.texture();
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> p_146754_) {
		if (ACTION.equals(p_146754_)) {
			this.actionAnimations(this.getAction(), false);
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

	public void setRole(Roles role) {
		this.entityData.set(ROLE, role.name());
	}

	public Roles getRole() {
		return Roles.get(this.entityData.get(ROLE));
	}
	public void setTofunianHome(@Nullable BlockPos pos) {
		this.tofunianHome = pos;
	}

	@Nullable
	public BlockPos getTofunianHome() {
		return this.tofunianHome;
	}

	public void setTofunianJobBlock(@Nullable BlockPos tofunianJobBlock) {
		this.tofunianJobBlock = tofunianJobBlock;
	}

	@Nullable
	public BlockPos getTofunianJobBlock() {
		return this.tofunianJobBlock;
	}

	public void setVillageCenter(@Nullable BlockPos villageCenter) {
		this.villageCenter = villageCenter;
	}

	@Nullable
	public BlockPos getVillageCenter() {
		return this.villageCenter;
	}

	@Override
	protected void customServerAiStep(ServerLevel serverLevel) {
		if (!isTrading() && this.timeUntilReset > 0) {
			this.timeUntilReset--;
			if (this.timeUntilReset <= 0) {
				if (this.leveledUp) {
					increaseMerchantCareer();
					this.leveledUp = false;
				}
				addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
			}
		}
		if (this.previousCustomer != null) {
			serverLevel.onReputationEvent(ReputationEventType.TRADE, this.previousCustomer, this);
			level().broadcastEntityEvent(this, (byte) 14);
			this.previousCustomer = null;
		}
		if (getRole() == Roles.TOFUNIAN && isTrading()) {
			stopTrading();
		}
		ProfilerFiller profilerfiller = Profiler.get();
		profilerfiller.push("tofunianCheck");
		this.tofunianJobCheck();
		this.tofunianHomeCheck();
		profilerfiller.pop();

		super.customServerAiStep(serverLevel);
	}

	@Override
	public void aiStep() {
		this.updateSwingTime();
		super.aiStep();
		this.actionTicks();
		if (this.level().isClientSide) {
			this.actionAnimations(this.getAction(), true);
			if (this.getAction() == Actions.CRY) {
				this.level().addParticle(TofuParticleTypes.SOYMILK_SPLASH.get(), this.getX(), this.getEyeY(), this.getZ(), Mth.nextFloat(this.random, -1, 1) * 0.15D, 0.05D, Mth.nextFloat(this.random, -1, 1) * 0.15D);
			}
		}
	}

	public void actionTicks() {
		// if action tick is -1. loop
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
				case HAPPY:
					this.stopAnimations();
					happyAnimationState.start(this.tickCount);
					break;
				case CALL:
					this.stopAnimations();
					callAnimationState.start(this.tickCount);
					break;
				case EAT:
					if (!loop && !actions.loop) {
						this.stopAnimations();
					}
					eatFoodAnimationState.startIfStopped(this.tickCount);
					break;
				case NORMAL:
					this.stopAnimations();
					break;
				default:
					this.stopAnimations();
					break;
			}
		}
	}

	public void stopAnimations() {
		eatFoodAnimationState.stop();
		happyAnimationState.stop();
	}

	public void tofunianJobCheck() {
		if ((level().getGameTime() + this.getId()) % (50) != 0) return;

		//validate job position
		if (this.getTofunianJobBlock() != null && this.getRole() != Roles.TOFUNIAN) {
			if (!this.getRole().is(this.level().getBlockState(this.getTofunianJobBlock()))) {
				this.setTofunianJobBlock(null);

				//if xp is none. set role normal
				if (this.getTofunianLevel() == 1 && this.getVillagerXp() == 0) {
					this.setOffers(null);
					this.setRole(Roles.TOFUNIAN);
				}
			}
		}

		if (this.getVillageCenter() != null) {
			if (this.level() instanceof ServerLevel serverLevel) {
				//don't forget release poi
				PoiManager poimanager = serverLevel.getPoiManager();
				if (poimanager.exists(this.getVillageCenter(), (p_217230_) -> {
					return true;
				}) && (this.getVillageCenter().distManhattan(this.blockPosition()) > 16 * 8)) {
					poimanager.release(this.getVillageCenter());
					DebugPackets.sendPoiTicketCountPacket(serverLevel, this.getVillageCenter());
					this.setVillageCenter(null);
				} else if (!poimanager.exists(this.getVillageCenter(), (p_217230_) -> {
					return true;
				})) {
					this.setVillageCenter(null);
				}
			}
		}
	}

	private boolean findNearbyTofunianHadHome(Tofunian tofunian, BlockPos pos) {
		List<Tofunian> list = tofunian.level().getEntitiesOfClass(Tofunian.class, tofunian.getBoundingBox().inflate(32.0D));

		return list.stream().anyMatch((p_34881_) -> {
			return p_34881_ != tofunian && (pos.equals(p_34881_.getTofunianHome()));
		});
	}

	public void tofunianHomeCheck() {
		if ((level().getGameTime() + this.getId()) % (90) != 0) return;

		//validate home position
		boolean tryFind = false;
		if (getTofunianHome() == null) {
			tryFind = true;
		} else {
			if (!this.level().getBlockState(this.getTofunianHome()).is(BlockTags.BEDS)) {
				//home position isnt a chest, keep current position but find better one
				tryFind = true;
				this.setTofunianHome(null);
			}
		}

		if (tryFind) {
			int range = 8;
			for (int x = -range; x <= range; x++) {
				for (int y = -range / 2; y <= range / 2; y++) {
					for (int z = -range; z <= range; z++) {
						BlockPos pos = this.blockPosition().offset(x, y, z);
						if (this.level().getBlockState(pos).is(BlockTags.BEDS) && !this.findNearbyTofunianHadHome(this, pos)) {
							setTofunianHome(pos);
							return;
						}
					}
				}
			}
		}
	}

	@Override
	protected void rewardTradeXp(MerchantOffer offer) {
		int i = 3 + this.random.nextInt(4);
		this.xp += offer.getXp();
		this.previousCustomer = this.getTradingPlayer();
		if (canLevelUp()) {
			this.timeUntilReset = 40;
			this.leveledUp = true;
			i += 5;
		}
		if (offer.shouldRewardExp())
			level().addFreshEntity(new ExperienceOrb(level(), getX(), getY() + 0.5D, getZ(), i));
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_35282_, DifficultyInstance p_35283_, EntitySpawnReason p_35284_, @org.jetbrains.annotations.Nullable SpawnGroupData p_35285_) {
		Holder<Biome> holder = p_35282_.getBiome(this.blockPosition());
		this.setVariant(TofunianVariants.getSpawnVariant(this.registryAccess(), holder));
		return super.finalizeSpawn(p_35282_, p_35283_, p_35284_, p_35285_);
	}

	@Override
	public InteractionResult mobInteract(Player p_35472_, InteractionHand p_35473_) {
		ItemStack itemstack = p_35472_.getItemInHand(p_35473_);
		if (itemstack.getItem() != TofuItems.TOFUNIAN_SPAWNEGG.get() && this.isAlive() && !this.isTrading() && !this.isSleeping() && !p_35472_.isSecondaryUseActive()) {
			if (p_35472_ instanceof ServerPlayer) {
				TofuAdvancements.MY_TOFU_CHILD.get().trigger((ServerPlayer) p_35472_);
			}
			if (this.isBaby()) {
				this.setUnhappy();
				return InteractionResult.SUCCESS;
			} else {
				boolean flag = this.getOffers().isEmpty();
				if (this.getAction() == Actions.HAPPY || this.getAction() == Actions.EAT || this.getAction() == Actions.CRY) {
					return InteractionResult.CONSUME;

				}
				if (p_35473_ == InteractionHand.MAIN_HAND) {
					if (flag && !this.level().isClientSide) {
						this.setUnhappy();
					}

					p_35472_.awardStat(Stats.TALKED_TO_VILLAGER);
				}

				if (flag) {
					return InteractionResult.SUCCESS;
				} else {
					if (!this.level().isClientSide && !this.offers.isEmpty()) {
						this.startTrading(p_35472_);
					}

					return InteractionResult.SUCCESS;
				}
			}
		} else {
			return super.mobInteract(p_35472_, p_35473_);
		}
	}

	private void setUnhappy() {
		this.setUnhappyCounter(40);
		if (!this.level().isClientSide()) {
			this.playSound(TofuSounds.TOFUNIAN_NO.get(), this.getSoundVolume(), this.getVoicePitch());
		}

	}

	private void updateSpecialPrices(Player p_35541_) {
		int i = this.getPlayerReputation(p_35541_);
		if (i != 0) {
			for (MerchantOffer merchantoffer : this.getOffers()) {
				merchantoffer.addToSpecialPriceDiff(-Mth.floor((float) i * merchantoffer.getPriceMultiplier()));
			}
		}

		if (p_35541_.hasEffect(MobEffects.HERO_OF_THE_VILLAGE)) {
			MobEffectInstance mobeffectinstance = p_35541_.getEffect(MobEffects.HERO_OF_THE_VILLAGE);
			int k = mobeffectinstance.getAmplifier();

			for (MerchantOffer merchantoffer1 : this.getOffers()) {
				double d0 = 0.3D + 0.0625D * (double) k;
				int j = (int) Math.floor(d0 * (double) merchantoffer1.getBaseCostA().getCount());
				merchantoffer1.addToSpecialPriceDiff(-Math.max(j, 1));
			}
		}

	}

	private void startTrading(Player p_35537_) {
		this.updateSpecialPrices(p_35537_);
		this.setTradingPlayer(p_35537_);
		this.openTradingScreen(p_35537_, this.getDisplayName(), this.getTofunianLevel());
	}

	@Override
	public void setTradingPlayer(@Nullable Player player) {
		boolean flag = (getTradingPlayer() != null && player == null);
		super.setTradingPlayer(player);
		if (flag)
			stopTrading();
	}

	@Override
	protected void stopTrading() {
		super.stopTrading();
		resetSpecialPrices();
	}

	private void resetSpecialPrices() {
		for (MerchantOffer merchantoffer : this.getOffers())
			merchantoffer.resetSpecialPriceDiff();
	}

	public boolean canRestock() {
		return true;
	}

	public void restock() {
		calculateDemandOfOffers();
		for (MerchantOffer merchantoffer : this.getOffers()) {
			merchantoffer.resetUses();
		}
		this.lastRestock = level().getGameTime();
		this.restocksToday++;
	}

	private boolean allowedToRestock() {
		return (this.restocksToday == 0 || (this.restocksToday < 2 && level().getGameTime() > this.lastRestock + 2400L));
	}

	public boolean canResetStock() {
		long i = this.lastRestock + 12000L;
		long j = this.level().getGameTime();
		boolean flag = j > i;
		long k = this.level().getDayTime();
		if (this.lastRestockDayTime > 0L) {
			long l = this.lastRestockDayTime / 24000L;
			long i1 = k / 24000L;
			flag |= i1 > l;
		}

		this.lastRestockDayTime = k;
		if (flag) {
			this.lastRestock = j;
			this.resetNumberOfRestocks();
		}

		return this.allowedToRestock() && this.hasUsedOffer();
	}

	private void resetNumberOfRestocks() {
		resetOffersAndAdjustForDemand();
		this.restocksToday = 0;
	}

	private void resetOffersAndAdjustForDemand() {
		int i = 2 - this.restocksToday;
		if (i > 0) {
			for (MerchantOffer merchantoffer : this.getOffers()) {
				merchantoffer.resetUses();
			}
		}
		for (int j = 0; j < i; j++) {
			calculateDemandOfOffers();
		}
	}

	private boolean hasUsedOffer() {
		for (MerchantOffer merchantoffer : this.getOffers()) {
			if (merchantoffer.needsRestock()) {
				return true;
			}

		}
		return false;
	}

	private void calculateDemandOfOffers() {
		for (MerchantOffer merchantoffer : this.getOffers()) {
			merchantoffer.updateDemand();
		}
	}

	public void setOffers(MerchantOffers offersIn) {
		this.offers = offersIn;
	}

	private boolean canLevelUp() {
		int i = this.tofunianLevel;
		return VillagerData.canLevelUp(i) && this.xp >= VillagerData.getMaxXpPerLevel(i);
	}

	private void increaseMerchantCareer() {
		setTofunianLevel(this.tofunianLevel + 1);
		updateTrades();
		this.setAction(Actions.HAPPY);
	}

	public void setTofunianLevel(int level) {
		this.tofunianLevel = level;
	}

	public int getTofunianLevel() {
		return this.tofunianLevel;
	}

	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("FoodLevel", this.foodLevel);
		compound.put("Gossips", this.gossips.store(NbtOps.INSTANCE).copy());
		compound.putInt("Xp", this.xp);
		compound.putInt("Level", this.tofunianLevel);
		compound.putLong("LastRestock", this.lastRestock);
		compound.putLong("LastGossipDecay", this.lastGossipDecay);
		compound.putInt("RestocksToday", this.restocksToday);
		if (this.tofunianHome != null) {
			compound.put("TofunianHome", NbtUtils.writeBlockPos(this.tofunianHome));
		}
		if (this.tofunianJobBlock != null) {
			compound.put("TofunianJobBlock", NbtUtils.writeBlockPos(this.tofunianJobBlock));
		}
		if (this.villageCenter != null) {
			compound.put("VillageCenter", NbtUtils.writeBlockPos(this.villageCenter));
		}
		compound.putString("Roles", getRole().name());
		this.getVariant().unwrapKey().ifPresent(p_344339_ -> compound.putString("variant", p_344339_.location().toString()));

	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("FoodLevel", 1)) {
			this.foodLevel = compound.getByte("FoodLevel");
		}
		ListTag listtag = compound.getList("Gossips", 10);
		this.gossips.update(new Dynamic<>(NbtOps.INSTANCE, listtag));
		if (compound.contains("Xp", 3)) {
			this.xp = compound.getInt("Xp");
		}
		if (compound.contains("Level")) {
			this.tofunianLevel = compound.getInt("Level");
		}
		this.lastGossipDecay = compound.getLong("LastGossipDecay");
		this.lastRestock = compound.getLong("LastRestock");
		this.restocksToday = compound.getInt("RestocksToday");
		if (compound.contains("TofunianHome")) {
			this.tofunianHome = NbtUtils.readBlockPos(compound, "TofunianHome").orElse(null);
		}
		if (compound.contains("TofunianJobBlock")) {
			this.tofunianJobBlock = NbtUtils.readBlockPos(compound, "TofunianJobBlock").orElse(null);
		}
		if (compound.contains("VillageCenter")) {
			this.villageCenter = NbtUtils.readBlockPos(compound, "VillageCenter").orElse(null);
		}
		if (compound.contains("Roles")) {
			setRole(Roles.get(compound.getString("Roles")));
		}
		Optional.ofNullable(ResourceLocation.tryParse(compound.getString("variant")))
				.map(p_332608_ -> ResourceKey.create(TofunianVariants.TOFUNIAN_VARIANT_REGISTRY_KEY, p_332608_))
				.flatMap(p_352803_ -> this.registryAccess().lookupOrThrow(TofunianVariants.TOFUNIAN_VARIANT_REGISTRY_KEY).get(p_352803_))
				.ifPresent(this::setVariant);
		setCanPickUpLoot(true);
	}

	public int getVillagerXp() {
		return this.xp;
	}

	@Override
	protected void pickUpItem(ServerLevel serverLevel, ItemEntity p_175445_1_) {
		ItemStack itemstack = p_175445_1_.getItem();
		if (wantsToPickUp(serverLevel, itemstack)) {
			SimpleContainer inventory = getInventory();
			boolean flag = inventory.canAddItem(itemstack);
			if (!flag)
				return;
			this.onItemPickup(p_175445_1_);
			this.take(p_175445_1_, itemstack.getCount());
			ItemStack itemstack1 = inventory.addItem(itemstack);
			if (itemstack1.isEmpty()) {
				p_175445_1_.discard();
			} else {
				itemstack.setCount(itemstack1.getCount());
			}
		}
	}

	public boolean canMate() {
		return (this.foodLevel + countFoodPointsInInventory() >= 42 && getAge() == 0);
	}

	private boolean hungry() {
		return (this.foodLevel < 12);
	}

	@Override
	public boolean wantsToPickUp(ServerLevel serverLevel, ItemStack p_230293_1_) {
		Item item = p_230293_1_.getItem();
		return (WANTED_ITEMS.contains(item) && this.getInventory().canAddItem(p_230293_1_));
	}

	public boolean hasExcessFood() {
		return (countFoodPointsInInventory() >= 42);
	}

	public boolean wantsMoreFood() {
		return (countFoodPointsInInventory() < 42);
	}

	public boolean hasFarmSeeds() {
		return this.getInventory().hasAnyOf(ImmutableSet.of(TofuItems.SEEDS_SOYBEANS.get()));
	}

	private int countFoodPointsInInventory() {
		SimpleContainer inventory = this.getInventory();
		return FOOD_POINTS.entrySet().stream().mapToInt(p_226553_1_ -> inventory.countItem(p_226553_1_.getKey()) * p_226553_1_.getValue().intValue())

				.sum();
	}

	private void eatUntilFull() {
		if (this.hungry() && this.countFoodPointsInInventory() != 0) {
			for (int i = 0; i < this.getInventory().getContainerSize(); ++i) {
				ItemStack itemstack = this.getInventory().getItem(i);
				if (!itemstack.isEmpty()) {
					Integer integer = FOOD_POINTS.get(itemstack.getItem());
					if (integer != null) {
						int j = itemstack.getCount();

						for (int k = j; k > 0; --k) {
							this.foodLevel += integer;
							this.getInventory().removeItem(i, 1);
							if (!this.hungry()) {
								return;
							}
						}
					}
				}
			}

		}
	}

	private void digestFood(int p_35549_) {
		this.foodLevel -= p_35549_;
	}

	public void eatAndDigestFood() {
		this.eatUntilFull();
		this.digestFood(12);
	}


	public void cookingFood() {
		for (int i = 0; i < this.getInventory().getContainerSize(); i++) {
			ItemStack itemstack = this.getInventory().getItem(i);
			if (!itemstack.isEmpty() &&
					itemstack.getItem() == TofuItems.SEEDS_SOYBEANS.get()) {
				this.getInventory().removeItem(i, 1);
				cookResult();
			}
		}
	}

	public boolean eatFood() {
		if (this.getInventory().isEmpty()) {
			return false;
		}
		for (int i = 0; i < this.getInventory().getContainerSize(); i++) {
			ItemStack itemstack = this.getInventory().getItem(i);
			if (!itemstack.isEmpty() && FOOD_POINTS.containsKey(itemstack.getItem())) {
				this.setItemInHand(InteractionHand.MAIN_HAND, itemstack.split(1));
				return true;
			}
		}
		return false;
	}

	private void cookResult() {
		this.getInventory().addItem(new ItemStack(TofuItems.TOFUGRILLED.get()));
	}


	@Override
	protected void completeUsingItem() {
		FoodProperties foodproperties = this.useItem.get(DataComponents.FOOD);
		float f = foodproperties != null ? (float) foodproperties.nutrition() : 1.0F;

		this.heal(f);
		this.playSound(SoundEvents.PLAYER_BURP, 0.5F, this.random.nextFloat() * 0.1F + 0.9F);

		super.completeUsingItem();
	}

	@Override
	public void updateTrades() {
		Int2ObjectMap<VillagerTrades.ItemListing[]> int2objectmap = TofunianTrades.TOFUNIAN_TRADE.get(getRole());
		if (int2objectmap != null && !int2objectmap.isEmpty()) {
			VillagerTrades.ItemListing[] avillagertrades$ItemListing = int2objectmap.get(this.tofunianLevel);
			if (avillagertrades$ItemListing != null) {
				MerchantOffers merchantoffers = this.getOffers();
				addOffersFromItemListings(merchantoffers, avillagertrades$ItemListing, 2);
			}
		}
	}


	@Override
	public void tick() {
		super.tick();
		this.maybeDecayGossip();
	}

	@Override
	public void setLastHurtByMob(@Nullable LivingEntity p_70604_1_) {
		if (p_70604_1_ != null && this.level() instanceof ServerLevel) {
			((ServerLevel) this.level()).onReputationEvent(ReputationEventType.VILLAGER_HURT, p_70604_1_, this);
			if (this.isAlive() && p_70604_1_ instanceof Player) {
				this.level().broadcastEntityEvent(this, (byte) 13);
			}
		}

		super.setLastHurtByMob(p_70604_1_);
	}

	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte p_70103_1_) {
		if (p_70103_1_ == 12) {
			this.addParticlesAroundSelf(ParticleTypes.HEART);
		} else if (p_70103_1_ == 13) {
			this.addParticlesAroundSelf(ParticleTypes.ANGRY_VILLAGER);
		} else if (p_70103_1_ == 14) {
			this.addParticlesAroundSelf(ParticleTypes.HAPPY_VILLAGER);
		} else if (p_70103_1_ == 42) {
			this.addParticlesAroundSelf(ParticleTypes.SPLASH);
		} else {
			super.handleEntityEvent(p_70103_1_);
		}

	}

	@Override
	public void die(DamageSource p_35419_) {
		Entity entity = p_35419_.getEntity();

		if (this.getVillageCenter() != null) {
			if (this.level() instanceof ServerLevel) {
				//don't forget release poi
				PoiManager poimanager = ((ServerLevel) this.level()).getPoiManager();
				if (poimanager.exists(this.getVillageCenter(), (p_217230_) -> {
					return true;
				})) {
					poimanager.release(this.getVillageCenter());
				}
			}
		}
		super.die(p_35419_);
	}

	@Override
	public void onReputationEventFrom(ReputationEventType reputationEventType, Entity entity) {
		if (reputationEventType == ReputationEventType.ZOMBIE_VILLAGER_CURED) {
			this.gossips.add(entity.getUUID(), GossipType.MAJOR_POSITIVE, 20);
			this.gossips.add(entity.getUUID(), GossipType.MINOR_POSITIVE, 25);
		} else if (reputationEventType == ReputationEventType.TRADE) {
			this.gossips.add(entity.getUUID(), GossipType.TRADING, 2);
		} else if (reputationEventType == ReputationEventType.VILLAGER_HURT) {
			this.gossips.add(entity.getUUID(), GossipType.MINOR_NEGATIVE, 25);
		} else if (reputationEventType == ReputationEventType.VILLAGER_KILLED) {
			this.gossips.add(entity.getUUID(), GossipType.MAJOR_NEGATIVE, 25);
		}

	}

	public int getPlayerReputation(Player p_35533_) {
		return this.gossips.getReputation(p_35533_.getUUID(), (p_35427_) -> {
			return true;
		});
	}

	public void gossip(ServerLevel p_35412_, Tofunian p_35413_, long p_35414_) {
		if ((p_35414_ < this.lastGossipTime || p_35414_ >= this.lastGossipTime + 1200L) && (p_35414_ < p_35413_.lastGossipTime || p_35414_ >= p_35413_.lastGossipTime + 1200L)) {
			this.gossips.transferFrom(p_35413_.gossips, this.random, 10);
			this.lastGossipTime = p_35414_;
			p_35413_.lastGossipTime = p_35414_;
			this.spawnGolemIfNeeded(p_35412_, p_35414_, 4);
		}
	}

	public void spawnGolemIfNeeded(ServerLevel p_35398_, long p_35399_, int p_35400_) {
		if (this.wantsToSpawnGolem(p_35398_, p_35399_)) {
			AABB aabb = this.getBoundingBox().inflate(10.0D, 10.0D, 10.0D);
			List<Tofunian> list = p_35398_.getEntitiesOfClass(Tofunian.class, aabb);
			List<Tofunian> list1 = list.stream().filter((p_186293_) -> {
				return p_186293_.wantsToSpawnGolem(p_35398_, p_35399_) && p_186293_.getTofunianHome() != null;
			}).limit(5L).collect(Collectors.toList());
			if (list1.size() >= p_35400_) {
				if (SpawnUtil.trySpawnMob(TofuEntityTypes.TOFU_GOLEM.get(), EntitySpawnReason.MOB_SUMMONED, p_35398_, this.blockPosition(), 10, 8, 6, SpawnUtil.Strategy.LEGACY_IRON_GOLEM,
						false).isPresent()) {
				}
			}
		}
	}

	private boolean wantsToSpawnGolem(ServerLevel p_35398_, long p_35399_) {
		AABB aabb = this.getBoundingBox().inflate(14.0D, 14.0D, 14.0D);
		List<TofuGolem> list = p_35398_.getEntitiesOfClass(TofuGolem.class, aabb);

		return list.isEmpty();
	}

	private void maybeDecayGossip() {
		long i = this.level().getGameTime();
		if (this.lastGossipDecay == 0L) {
			this.lastGossipDecay = i;
		} else if (i >= this.lastGossipDecay + 24000L) {
			this.gossips.decay();
			this.lastGossipDecay = i;
		}
	}

	public GossipContainer getGossips() {
		return this.gossips;
	}

	public void setGossips(Tag p_35456_) {
		this.gossips.update(new Dynamic<>(NbtOps.INSTANCE, p_35456_));
	}

	@Override
	public float getWalkTargetValue(BlockPos p_27573_, LevelReader p_27574_) {
		return p_27574_.getBlockState(p_27573_.below()).is(Blocks.GRASS_BLOCK) ? 10.0F : p_27574_.getPathfindingCostFromLightLevels(p_27573_);
	}

	@Override
	protected Component getTypeName() {
		return Component.translatable("entity.tofucraft.tofunian." + this.getRole().name().toLowerCase(Locale.ROOT));
	}

	public enum Actions {
		NORMAL(true, -1),
		CRY(true, 80),
		AVOID(true, -1),
		SIT(true, -1),
		HAPPY(false, 30),
		CALL(false, 25),
		EAT(true, -1);

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

	public enum Roles {
		TOFUCOOK(getBlockStates(Blocks.COMPOSTER)), TOFUSMITH(getBlockStates(Blocks.BLAST_FURNACE)), SOYWORKER(ImmutableList.of(Blocks.CAULDRON, Blocks.LAVA_CAULDRON, Blocks.WATER_CAULDRON, Blocks.POWDER_SNOW_CAULDRON).stream().flatMap((p_218093_) -> {
			return p_218093_.getStateDefinition().getPossibleStates().stream();
		}).collect(ImmutableSet.toImmutableSet())), TOFUNIAN(Set.of());

		private final Set<BlockState> matchingStates;

		Roles(Set<BlockState> matchingStates) {
			matchingStates = Set.copyOf(matchingStates);
			this.matchingStates = matchingStates;
		}

		public boolean is(BlockState p_148693_) {
			return this.matchingStates.contains(p_148693_);
		}

		private static Set<BlockState> getBlockStates(Block p_218074_) {
			return ImmutableSet.copyOf(p_218074_.getStateDefinition().getPossibleStates());
		}

		public static Roles get(String nameIn) {
			for (Roles role : values()) {
				if (role.name().equals(nameIn))
					return role;
			}
			return TOFUNIAN;
		}

		@Nullable
		public static Roles getJob(BlockState blockState) {
			for (Roles role : values()) {
				if (role != TOFUNIAN && role.is(blockState)) {
					return role;
				}
			}
			return null;
		}
	}

	class MoveToGoal extends Goal {
		final Tofunian tofunian;
		final double stopDistance;
		final double speedModifier;

		MoveToGoal(Tofunian p_i50459_2_, double p_i50459_3_, double p_i50459_5_) {
			this.tofunian = p_i50459_2_;
			this.stopDistance = p_i50459_3_;
			this.speedModifier = p_i50459_5_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public void stop() {
			Tofunian.this.navigation.stop();
		}

		public boolean canUse() {
			BlockPos blockpos = this.tofunian.getTofunianHome();

			double distance = this.tofunian.level().isDay() ? this.stopDistance : this.stopDistance / 4.0F;

			return blockpos != null && this.isTooFarAway(blockpos, distance);
		}

		public void tick() {
			BlockPos blockpos = this.tofunian.getTofunianHome();
			if (blockpos != null && Tofunian.this.navigation.isDone()) {
				if (this.isTooFarAway(blockpos, 10.0D)) {
					Vec3 vector3d = (new Vec3((double) blockpos.getX() - this.tofunian.getX(), (double) blockpos.getY() - this.tofunian.getY(), (double) blockpos.getZ() - this.tofunian.getZ())).normalize();
					Vec3 vector3d1 = vector3d.scale(10.0D).add(this.tofunian.getX(), this.tofunian.getY(), this.tofunian.getZ());
					Tofunian.this.navigation.moveTo(vector3d1.x, vector3d1.y, vector3d1.z, this.speedModifier);
				} else {
					Tofunian.this.navigation.moveTo(blockpos.getX(), blockpos.getY(), blockpos.getZ(), this.speedModifier);
				}
			}

		}

		private boolean isTooFarAway(BlockPos p_220846_1_, double p_220846_2_) {
			return !p_220846_1_.closerThan(this.tofunian.blockPosition(), p_220846_2_);
		}
	}

	public class GetItemGoal<T extends Tofunian> extends Goal {
		private final T mob;

		public GetItemGoal(T p_i50572_2_) {
			this.mob = p_i50572_2_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public boolean canUse() {

			List<ItemEntity> list = this.mob.level().getEntitiesOfClass(ItemEntity.class, this.mob.getBoundingBox().inflate(4.0D, 4.0D, 4.0D), Tofunian.ALLOWED_ITEMS);
			if (!list.isEmpty() && this.mob.hasLineOfSight(list.get(0))) {
				return this.mob.getNavigation().moveTo(list.get(0), 1.0F);
			}

			return false;
		}
	}

	class TofunianAvoidEntityGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
		private final Tofunian tofunian;

		public TofunianAvoidEntityGoal(Tofunian p_30454_, Class<T> p_30455_, float p_30456_, double p_30457_, double p_30458_) {
			super(p_30454_, p_30455_, p_30456_, p_30457_, p_30458_);
			this.tofunian = p_30454_;
		}


		public void start() {
			this.tofunian.setAction(Actions.AVOID);
			super.start();
		}

		@Override
		public void stop() {
			super.stop();
			this.tofunian.setAction(Actions.NORMAL);
		}
	}
}

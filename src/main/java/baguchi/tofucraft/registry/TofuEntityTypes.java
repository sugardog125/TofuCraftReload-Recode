package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.entity.FukumameThrower;
import baguchi.tofucraft.entity.ShuDofuSpider;
import baguchi.tofucraft.entity.TofuCow;
import baguchi.tofucraft.entity.TofuCreeper;
import baguchi.tofucraft.entity.TofuFish;
import baguchi.tofucraft.entity.TofuGandlem;
import baguchi.tofucraft.entity.TofuGolem;
import baguchi.tofucraft.entity.TofuPig;
import baguchi.tofucraft.entity.TofuSlime;
import baguchi.tofucraft.entity.TofuSpider;
import baguchi.tofucraft.entity.Tofunian;
import baguchi.tofucraft.entity.TravelerTofunian;
import baguchi.tofucraft.entity.Zundamite;
import baguchi.tofucraft.entity.effect.NattoCobWebEntity;
import baguchi.tofucraft.entity.projectile.FallingTofuEntity;
import baguchi.tofucraft.entity.projectile.FukumameEntity;
import baguchi.tofucraft.entity.projectile.NattoBallEntity;
import baguchi.tofucraft.entity.projectile.NattoStringEntity;
import baguchi.tofucraft.entity.projectile.NetherFukumameEntity;
import baguchi.tofucraft.entity.projectile.SoulFukumameEntity;
import baguchi.tofucraft.entity.projectile.SoyballEntity;
import baguchi.tofucraft.entity.projectile.ZundaArrow;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class TofuEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, TofuCraftReload.MODID);

	public static final Supplier<EntityType<Tofunian>> TOFUNIAN = ENTITIES.register("tofunian", () -> EntityType.Builder.of(Tofunian::new, MobCategory.CREATURE)
			.sized(0.6F, 1.2F).eyeHeight(1.2F * 0.8F).clientTrackingRange(10).build(prefix("tofunian")));

	public static final Supplier<EntityType<TravelerTofunian>> TRAVELER_TOFUNIAN = ENTITIES.register("traveler_tofunian", () -> EntityType.Builder.of(TravelerTofunian::new, MobCategory.CREATURE)
			.sized(0.6F, 1.2F).eyeHeight(1.2F * 0.8F).build(prefix("traveler_tofunian")));

	public static final Supplier<EntityType<TofuCow>> TOFUCOW = ENTITIES.register("tofucow", () -> EntityType.Builder.of(TofuCow::new, MobCategory.CREATURE)
			.sized(0.9F, 1.4F).eyeHeight(1.4F * 0.8F).requiredFeatures(TofuCraftReload.EXPERIMENTAL).build(prefix("tofucow")));

	public static final Supplier<EntityType<TofuPig>> TOFUPIG = ENTITIES.register("tofupig", () -> EntityType.Builder.of(TofuPig::new, MobCategory.CREATURE)
			.sized(0.9F, 0.9F).eyeHeight(0.9F * 0.8F).requiredFeatures(TofuCraftReload.EXPERIMENTAL).build(prefix("tofupig")));

	public static final Supplier<EntityType<TofuFish>> TOFUFISH = ENTITIES.register("tofufish", () -> EntityType.Builder.of(TofuFish::new, MobCategory.WATER_AMBIENT)
			.sized(0.5F, 0.35F).eyeHeight(0.3F).setTrackingRange(4).requiredFeatures(TofuCraftReload.EXPERIMENTAL).build(prefix("tofufish")));
	public static final Supplier<EntityType<TofuGolem>> TOFU_GOLEM = ENTITIES.register("tofu_golem", () -> EntityType.Builder.of(TofuGolem::new, MobCategory.MISC)
			.sized(0.8F, 0.9F).eyeHeight(0.9F * 0.55F).clientTrackingRange(10).fireImmune().build(prefix("tofu_golem")));

	public static final Supplier<EntityType<TofuGandlem>> TOFU_GANDLEM = ENTITIES.register("tofu_gandlem", () -> EntityType.Builder.of(TofuGandlem::new, MobCategory.CREATURE)
			.sized(0.6F, 1.6F).eyeHeight(1.6F * 0.8F).clientTrackingRange(10).fireImmune().requiredFeatures(TofuCraftReload.EXPERIMENTAL).build(prefix("tofu_gandlem")));


	public static final Supplier<EntityType<TofuSlime>> TOFUSLIME = ENTITIES.register("tofuslime", () -> EntityType.Builder.of(TofuSlime::new, MobCategory.MONSTER)
			.sized(0.52F, 0.52F).eyeHeight(0.325F).requiredFeatures(TofuCraftReload.EXPERIMENTAL).build(prefix("tofuslime")));
	public static final Supplier<EntityType<TofuCreeper>> TOFUCREEPER = ENTITIES.register("tofucreeper", () -> EntityType.Builder.of(TofuCreeper::new, MobCategory.MONSTER)
			.sized(0.6F, 1.6F).requiredFeatures(TofuCraftReload.EXPERIMENTAL).build(prefix("tofucreeper")));

	public static final Supplier<EntityType<TofuSpider>> TOFUSPIDER = ENTITIES.register("tofuspider", () -> EntityType.Builder.of(TofuSpider::new, MobCategory.MONSTER)
			.sized(0.95F, 0.55F).eyeHeight(0.3F).requiredFeatures(TofuCraftReload.EXPERIMENTAL).build(prefix("tofuspider")));
	public static final Supplier<EntityType<Zundamite>> ZUNDAMITE = ENTITIES.register("zundamite", () -> EntityType.Builder.of(Zundamite::new, MobCategory.MONSTER)
			.sized(0.4F, 0.3F).eyeHeight(0.13F).clientTrackingRange(8).requiredFeatures(TofuCraftReload.EXPERIMENTAL).build(prefix("zundamite")));

	public static final Supplier<EntityType<FukumameEntity>> FUKUMAME = ENTITIES.register("fukumame", () -> EntityType.Builder.<FukumameEntity>of(FukumameEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).updateInterval(30).build(prefix("fukumame")));

	public static final Supplier<EntityType<NetherFukumameEntity>> NETHER_FUKUMAME = ENTITIES.register("nether_fukumame", () -> EntityType.Builder.<NetherFukumameEntity>of(NetherFukumameEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).updateInterval(30).build(prefix("nether_fukumame")));

	public static final Supplier<EntityType<SoulFukumameEntity>> SOUL_FUKUMAME = ENTITIES.register("soul_fukumame", () -> EntityType.Builder.<SoulFukumameEntity>of(SoulFukumameEntity::new, MobCategory.MISC)
			.sized(0.25F, 0.25F).updateInterval(30).build(prefix("soul_fukumame")));
	public static final Supplier<EntityType<SoyballEntity>> SOYBALL = ENTITIES.register("soyball", () -> EntityType.Builder.<SoyballEntity>of(SoyballEntity::new, MobCategory.MISC)
			.sized(0.3F, 0.3F).updateInterval(30).build(prefix("soyball")));



	public static final Supplier<EntityType<NattoStringEntity>> NATTO_STRNIG = ENTITIES.register("natto_string", () -> EntityType.Builder.<NattoStringEntity>of(NattoStringEntity::new, MobCategory.MISC)
			.sized(0.2F, 0.2F).updateInterval(40).build(prefix("natto_string")));

	public static final Supplier<EntityType<ZundaArrow>> ZUNDA_ARROW = ENTITIES.register("zunda_arrow", () -> EntityType.Builder.<ZundaArrow>of(ZundaArrow::new, MobCategory.MISC)
			.sized(0.5F, 0.5F).updateInterval(40).build(prefix("zunda_arrow")));


	public static final Supplier<EntityType<NattoCobWebEntity>> NATTO_COBWEB = ENTITIES.register("natto_cobweb", () -> EntityType.Builder.<NattoCobWebEntity>of(NattoCobWebEntity::new, MobCategory.MISC)
			.sized(3F, 0.1F).updateInterval(10).fireImmune().build(prefix("natto_cobweb")));

	public static final Supplier<EntityType<NattoBallEntity>> NATTO_BALL = ENTITIES.register("natto_ball", () -> EntityType.Builder.<NattoBallEntity>of(NattoBallEntity::new, MobCategory.MISC)
			.sized(0.35F, 0.35F).updateInterval(20).build(prefix("natto_ball")));

	public static final Supplier<EntityType<FallingTofuEntity>> FALLING_TOFU = ENTITIES.register("falling_tofu", () -> EntityType.Builder.<FallingTofuEntity>of(FallingTofuEntity::new, MobCategory.MISC)
			.sized(1.0F, 1.0F).updateInterval(20).build(prefix("falling_tofu")));

	public static final Supplier<EntityType<ShuDofuSpider>> SHUDOFUSPIDER = ENTITIES.register("shudofuspider", () -> EntityType.Builder.of(ShuDofuSpider::new, MobCategory.CREATURE)
			.sized(3.5F, 2.9F).eyeHeight(2.0F).clientTrackingRange(10).requiredFeatures(TofuCraftReload.EXPERIMENTAL).fireImmune().build(prefix("shudofuspider")));

	public static final Supplier<EntityType<FukumameThrower>> FUKUMAME_THROWER = ENTITIES.register("fukumame_thrower", () -> EntityType.Builder.of(FukumameThrower::new, MobCategory.MONSTER).sized(0.6F, 1.85F).clientTrackingRange(8).build(prefix("fukumame_thrower")));

	public static final Supplier<EntityType<Boat>> LEEK_BOAT = ENTITIES.register(
			"leek_boat",
			() ->
					EntityType.Builder.of(boatFactory(TofuItems.LEEK_BOAT), MobCategory.MISC)
							.noLootTable()
							.requiredFeatures(TofuCraftReload.EXPERIMENTAL)
							.sized(1.375F, 0.5625F)
							.eyeHeight(0.5625F)
							.clientTrackingRange(10).build(prefix("leek_boat"))
	);
	public static final Supplier<EntityType<ChestBoat>> LEEK_CHEST_BOAT = ENTITIES.register(
			"leek_chest_boat",
			() ->
					EntityType.Builder.of(chestBoatFactory(TofuItems.LEEK_CHEST_BOAT), MobCategory.MISC)
							.noLootTable()
							.requiredFeatures(TofuCraftReload.EXPERIMENTAL)
							.sized(1.375F, 0.5625F)
							.eyeHeight(0.5625F)
							.clientTrackingRange(10).build(prefix("leek_chest_boat"))
	);
	public static final Supplier<EntityType<Boat>> LEEK_GREEN_BOAT = ENTITIES.register(
			"leek_green_boat",
			() ->
					EntityType.Builder.of(boatFactory(TofuItems.LEEK_GREEN_BOAT), MobCategory.MISC)
							.noLootTable()
							.requiredFeatures(TofuCraftReload.EXPERIMENTAL)
							.sized(1.375F, 0.5625F)
							.eyeHeight(0.5625F)
							.clientTrackingRange(10).build(prefix("leek_green_boat"))
	);
	public static final Supplier<EntityType<ChestBoat>> LEEK_GREEN_CHEST_BOAT = ENTITIES.register(
			"leek_green_chest_boat",
			() ->
					EntityType.Builder.of(chestBoatFactory(TofuItems.LEEK_GREEN_CHEST_BOAT), MobCategory.MISC)
							.noLootTable()
							.requiredFeatures(TofuCraftReload.EXPERIMENTAL)
							.sized(1.375F, 0.5625F)
							.eyeHeight(0.5625F)
							.clientTrackingRange(10).build(prefix("leek_green_chest_boat"))
	);
	public static final Supplier<EntityType<Boat>> TOFU_STEM_BOAT = ENTITIES.register(
			"tofu_stem_boat",
			() ->
					EntityType.Builder.of(boatFactory(TofuItems.TOFU_STEM_BOAT), MobCategory.MISC)
							.noLootTable()
							.requiredFeatures(TofuCraftReload.EXPERIMENTAL)
							.sized(1.375F, 0.5625F)
							.eyeHeight(0.5625F)
							.clientTrackingRange(10).build(prefix("tofu_stem_boat"))
	);
	public static final Supplier<EntityType<ChestBoat>> TOFU_STEM_CHEST_BOAT = ENTITIES.register(
			"tofu_stem_chest_boat",
			() ->
					EntityType.Builder.of(chestBoatFactory(TofuItems.TOFU_STEM_CHEST_BOAT), MobCategory.MISC)
							.noLootTable()
							.requiredFeatures(TofuCraftReload.EXPERIMENTAL)
							.sized(1.375F, 0.5625F)
							.eyeHeight(0.5625F)
							.clientTrackingRange(10).build(prefix("tofu_stem_chest_boat"))
	);

	private static EntityType.EntityFactory<Boat> boatFactory(Supplier<Item> p_376580_) {
		return (p_375558_, p_375559_) -> new Boat(p_375558_, p_375559_, p_376580_);
	}

	private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(Supplier<Item> p_376578_) {
		return (p_375555_, p_375556_) -> new ChestBoat(p_375555_, p_375556_, p_376578_);
	}

	public static final SpawnPlacementType IN_DOUBANJIANG = (p_325672_, p_325673_, p_325674_) -> {
		if (p_325674_ != null && p_325672_.getWorldBorder().isWithinBounds(p_325673_)) {
			BlockPos blockpos = p_325673_.above();
			return p_325672_.getFluidState(p_325673_).getType() == TofuFluidTypes.DOUBANJIANG;
		} else {
			return false;
		}
	};
	public static final SpawnPlacementType IN_SOYMILK = (p_325672_, p_325673_, p_325674_) -> {
		if (p_325674_ != null && p_325672_.getWorldBorder().isWithinBounds(p_325673_)) {
			BlockPos blockpos = p_325673_.above();
			return p_325672_.getFluidState(p_325673_).is(TofuTags.Fluids.SOYMILK) && !p_325672_.getBlockState(blockpos).isRedstoneConductor(p_325672_, blockpos);
		} else {
			return false;
		}
	};
	@SubscribeEvent
	public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
		event.put(TOFUCOW.get(), TofuCow.createAttributes().build());
		event.put(TOFUPIG.get(), TofuPig.createAttributes().build());
		event.put(TOFUNIAN.get(), Tofunian.createAttributes().build());
		event.put(TRAVELER_TOFUNIAN.get(), Tofunian.createAttributes().build());
		event.put(TOFUFISH.get(), AbstractFish.createAttributes().build());
		event.put(TOFU_GOLEM.get(), TofuGolem.createAttributes().build());
		event.put(TOFUSLIME.get(), Monster.createMonsterAttributes().build());
		event.put(TOFUCREEPER.get(), TofuCreeper.createAttributes().build());
		event.put(TOFUSPIDER.get(), TofuSpider.createAttributes().build());
		event.put(ZUNDAMITE.get(), Zundamite.createAttributes().build());
		event.put(TOFU_GANDLEM.get(), TofuGandlem.createAttributes().build());
		event.put(SHUDOFUSPIDER.get(), ShuDofuSpider.createAttributes().build());
		event.put(NATTO_COBWEB.get(), NattoCobWebEntity.createAttributes().build());
		event.put(FUKUMAME_THROWER.get(), FukumameThrower.createAttributes().build());
	}

	@SubscribeEvent
	public static void registerEntityAttribute(RegisterSpawnPlacementsEvent event) {
		event.register(TOFUCOW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuCow::checkTofuAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(TOFUPIG.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuPig::checkTofuAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(TOFUNIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(TRAVELER_TOFUNIAN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(TOFU_GOLEM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

		event.register(TOFUSLIME.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuSlime::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(TOFUCREEPER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuCreeper::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(TOFUSPIDER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuSpider::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(TOFUFISH.get(), IN_SOYMILK, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TofuFish::checkTofuFishSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(TOFU_GANDLEM.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);

		event.register(FUKUMAME_THROWER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FukumameThrower::checkFukumameSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
		event.register(ZUNDAMITE.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
	}

	private static ResourceKey<EntityType<?>> prefix(String path) {
		return ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, path));
	}
}

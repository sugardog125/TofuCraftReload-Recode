package baguchan.tofucraft;

import baguchan.tofucraft.api.tfenergy.IEnergyContained;
import baguchan.tofucraft.blockentity.SuspiciousTofuBlockEntity;
import baguchan.tofucraft.capability.SoyHealthCapability;
import baguchan.tofucraft.capability.TofuLivingCapability;
import baguchan.tofucraft.item.TofuPickaxeItem;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuDimensions;
import baguchan.tofucraft.registry.TofuEnchantments;
import baguchan.tofucraft.registry.TofuItems;
import baguchan.tofucraft.registry.TofuPoiTypes;
import baguchan.tofucraft.registry.TofuStructures;
import baguchan.tofucraft.registry.TofuTags;
import baguchan.tofucraft.utils.ContainerUtils;
import baguchan.tofucraft.utils.JigsawHelper;
import baguchan.tofucraft.utils.RecipeHelper;
import baguchan.tofucraft.world.TofuData;
import baguchan.tofucraft.world.TofuLevelData;
import baguchan.tofucraft.world.TravelerTofunianSpawner;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityMobGriefingEvent;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.village.VillageSiegeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class CommonEvents {
	private static final Map<ServerLevel, TravelerTofunianSpawner> TRAVELER_TOFUNIAN_SPAWNER_MAP = new HashMap<>();

	@SubscribeEvent
	public static void onRegisterEntityCapabilities(RegisterCapabilitiesEvent event) {
		event.register(SoyHealthCapability.class);
		event.register(TofuLivingCapability.class);
	}

	@SubscribeEvent
	public static void onAttachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof LivingEntity) {
			event.addCapability(new ResourceLocation(TofuCraftReload.MODID, "soy_health"), new SoyHealthCapability());
			event.addCapability(new ResourceLocation(TofuCraftReload.MODID, "tofu_living"), new TofuLivingCapability());
		}
	}

	@SubscribeEvent
	public static void onUpdate(LivingEvent.LivingTickEvent event) {
		LivingEntity livingEntity = event.getEntity();
		if (!livingEntity.level().isClientSide()) {
			livingEntity.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(cap -> {
				cap.tick(livingEntity);
			});
		}
		livingEntity.getCapability(TofuCraftReload.TOFU_LIVING_CAPABILITY).ifPresent(tofuLivingCapability -> {
			tofuLivingCapability.tick(livingEntity);
		});
	}

	protected static BlockHitResult getPlayerPOVHitResult(Level p_41436_, Player p_41437_, ClipContext.Fluid p_41438_) {
		float f = p_41437_.getXRot();
		float f1 = p_41437_.getYRot();
		Vec3 vec3 = p_41437_.getEyePosition();
		float f2 = Mth.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
		float f3 = Mth.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
		float f4 = -Mth.cos(-f * ((float) Math.PI / 180F));
		float f5 = Mth.sin(-f * ((float) Math.PI / 180F));
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d0 = p_41437_.getBlockReach();
		Vec3 vec31 = vec3.add((double) f6 * d0, (double) f5 * d0, (double) f7 * d0);
		return p_41436_.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, p_41438_, p_41437_));
	}


	@SubscribeEvent
	public static void onUsingItem(LivingEntityUseItemEvent event) {
		ItemStack stack = event.getItem();
		Level level = event.getEntity().level();

		if (stack.is(Items.BRUSH) && event.getEntity() instanceof Player player) {
			BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
			BlockPos $$7 = blockhitresult.getBlockPos();
			BlockState blockstate = level.getBlockState($$7);
			if (!level.isClientSide() && blockstate.is(TofuBlocks.SUSPICIOUS_TOFU_TERRAIN.get())) {
				BlockEntity $$11 = level.getBlockEntity($$7);
				if ($$11 instanceof SuspiciousTofuBlockEntity) {
					SuspiciousTofuBlockEntity suspicioussandblockentity = (SuspiciousTofuBlockEntity) $$11;
					boolean flag = suspicioussandblockentity.brush(level.getGameTime(), player, blockhitresult.getDirection());
					if (flag) {
						stack.hurtAndBreak(1, event.getEntity(), (p_272600_) -> {
							p_272600_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
						});
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onUsedEntity(PlayerInteractEvent.EntityInteractSpecific event) {
		ItemStack stack = event.getItemStack();
		Level level = event.getLevel();
		Entity entity = event.getTarget();
		if (entity instanceof AbstractHorse horse && stack.is(TofuItems.SALT.get())) {
			TofuLivingCapability capability = horse.getCapability(TofuCraftReload.TOFU_LIVING_CAPABILITY).orElse(null);
			if (capability != null && capability.getSaltBoostCooldown() <= 0) {
				capability.setSaltBoost(1200 * 3, 1200 * 3 + 600, horse);
				if (!event.getEntity().isCreative()) {
					stack.shrink(1);
				}
				//horse.eating();
				horse.gameEvent(GameEvent.EAT);
				horse.playSound(SoundEvents.HORSE_EAT);
				event.setCancellationResult(InteractionResult.SUCCESS);
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void onUsed(PlayerInteractEvent.RightClickItem event) {
		Level level = event.getLevel();
		Player playerIn = event.getEntity();
		InteractionHand handIn = event.getHand();
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		if (itemstack.is(TofuTags.Items.BITTERN)) {
			BlockHitResult blockraytraceresult = getPlayerPOVHitResult(level, playerIn, ClipContext.Fluid.SOURCE_ONLY);
			BlockHitResult blockraytraceresult1 = blockraytraceresult.withPosition(blockraytraceresult.getBlockPos());

			if (level instanceof ServerLevel serverLevel) {
				if (blockraytraceresult.getType() == HitResult.Type.BLOCK) {
					FluidState fluidState = level.getFluidState(blockraytraceresult1.getBlockPos());

					ItemStack result = RecipeHelper.getBitternResult(serverLevel, fluidState.getType(), itemstack.copyWithCount(1));
					if (result != null) {
						if (playerIn instanceof ServerPlayer serverPlayer) {
							CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, blockraytraceresult1.getBlockPos(), itemstack);
						}

						level.setBlock(blockraytraceresult1.getBlockPos(), Block.byItem(result.getItem()).defaultBlockState(), 11);
						level.levelEvent(2001, blockraytraceresult1.getBlockPos(), Block.getId(level.getBlockState(blockraytraceresult1.getBlockPos())));
						level.playSound(null, blockraytraceresult1.getBlockPos(), SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1F, 1F);
						ContainerUtils.addWithContainer(playerIn, handIn, itemstack, new ItemStack(Items.GLASS_BOTTLE), false);
						playerIn.swing(handIn);
						event.setCancellationResult(InteractionResult.SUCCESS);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onBlockUsed(PlayerInteractEvent.RightClickBlock event) {
		ItemStack stack = event.getItemStack();
		Level level = event.getLevel();

		if (stack.is(TofuItems.BUCKET_SOYMILK.get()))

			if (stack.is(TofuItems.BUCKET_SOYMILK.get()) && level.getBlockState(event.getPos()).is(Blocks.CAULDRON)) {
				level.setBlock(event.getPos(), TofuBlocks.SOYMILK_CAULDRON.get().defaultBlockState(), 2);
				event.getEntity().playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
				ItemStack itemstack2 = new ItemStack(Items.BUCKET);
				if (!event.getEntity().isCreative()) {
					stack.shrink(1);
				}
				if (stack.isEmpty()) {
					event.getEntity().setItemInHand(event.getHand(), itemstack2);
				} else if (!event.getEntity().isCreative() &&
						!event.getEntity().getInventory().add(itemstack2)) {
					event.getEntity().drop(itemstack2, false);
				}

				event.setCancellationResult(InteractionResult.SUCCESS);
				event.setCanceled(true);
			}
		if (stack.is(TofuItems.BUCKET_SOYMILK_NETHER.get()) && level.getBlockState(event.getPos()).is(Blocks.CAULDRON)) {
			level.setBlock(event.getPos(), TofuBlocks.SOYMILK_NETHER_CAULDRON.get().defaultBlockState(), 2);
			event.getEntity().playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
			ItemStack itemstack2 = new ItemStack(Items.BUCKET);
			if (!event.getEntity().isCreative()) {
				stack.shrink(1);
			}
			if (stack.isEmpty()) {
				event.getEntity().setItemInHand(event.getHand(), itemstack2);
			} else if (!event.getEntity().isCreative() &&
					!event.getEntity().getInventory().add(itemstack2)) {
				event.getEntity().drop(itemstack2, false);
			}

			event.setCancellationResult(InteractionResult.SUCCESS);
			event.setCanceled(true);
		}
		if (stack.is(TofuItems.BUCKET_SOYMILK_SOUL.get()) && level.getBlockState(event.getPos()).is(Blocks.CAULDRON)) {
			level.setBlock(event.getPos(), TofuBlocks.SOYMILK_SOUL_CAULDRON.get().defaultBlockState(), 2);
			event.getEntity().playSound(SoundEvents.BUCKET_FILL, 1.0F, 1.0F);
			ItemStack itemstack2 = new ItemStack(Items.BUCKET);
			if (!event.getEntity().isCreative()) {
				stack.shrink(1);
			}
			if (stack.isEmpty()) {
				event.getEntity().setItemInHand(event.getHand(), itemstack2);
			} else if (!event.getEntity().isCreative() &&
					!event.getEntity().getInventory().add(itemstack2)) {
				event.getEntity().drop(itemstack2, false);
			}

			event.setCancellationResult(InteractionResult.SUCCESS);
			event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onBlockGriefing(EntityMobGriefingEvent event) {
		Entity entity = event.getEntity();
		Level world = event.getEntity().level();

		if (world instanceof ServerLevel) {
			ServerLevel serverLevel = (ServerLevel) world;
			Structure structure = serverLevel.registryAccess().registryOrThrow(Registries.STRUCTURE).get(TofuStructures.TOFU_CASTLE);
			if (structure != null) {
				TofuData data = TofuData.get(serverLevel);
				Vec3 center = event.getEntity().position();
				StructureStart structureStart = serverLevel.structureManager().getStructureAt(new BlockPos((int) center.x, (int) center.y, (int) center.z), structure);
				if (structureStart.isValid() && !data.getBeatenDungeons().contains(structureStart.getBoundingBox())) {
					event.setResult(Event.Result.DENY);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onBreakingSpeed(PlayerEvent.BreakSpeed event) {
		ItemStack stack = event.getEntity().getMainHandItem();
		if (stack.getItem() instanceof TofuPickaxeItem pickaxeItem) {
			int level = EnchantmentHelper.getEnchantmentLevel(TofuEnchantments.BATCH.get(), event.getEntity());
			if (level > 0) {
				event.setNewSpeed(event.getOriginalSpeed() / (level + 1.25F));
			}
		}
	}

	@SubscribeEvent
	public static void onBlockDestroyByEntity(LivingDestroyBlockEvent event) {
		Level world = event.getEntity().level();

		if (world instanceof ServerLevel) {
			ServerLevel serverLevel = (ServerLevel) world;
			Structure structure = serverLevel.registryAccess().registryOrThrow(Registries.STRUCTURE).get(TofuStructures.TOFU_CASTLE);
			if (structure != null) {
				TofuData data = TofuData.get(serverLevel);
				Vec3 center = event.getEntity().position();
				StructureStart structureStart = serverLevel.structureManager().getStructureAt(new BlockPos((int) center.x, (int) center.y, (int) center.z), structure);
				if (structureStart.isValid() && !data.getBeatenDungeons().contains(structureStart.getBoundingBox())) {
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onBlockExplosion(ExplosionEvent.Detonate event) {
		Level world = event.getLevel();
		if (world instanceof ServerLevel) {
			ServerLevel serverLevel = (ServerLevel) world;
			Structure structure = serverLevel.registryAccess().registryOrThrow(Registries.STRUCTURE).get(TofuStructures.TOFU_CASTLE);
			if (structure != null) {
				TofuData data = TofuData.get(serverLevel);
				Vec3 center = event.getExplosion().getPosition();
				StructureStart structureStart = serverLevel.structureManager().getStructureAt(new BlockPos((int) center.x, (int) center.y, (int) center.z), structure);
				if (structureStart.isValid() && !data.getBeatenDungeons().contains(structureStart.getBoundingBox())) {
					event.getAffectedBlocks().clear();

				}
			}
		}
	}

	@SubscribeEvent
	public static void onBlockBreaked(BlockEvent.BreakEvent event) {
		if (event.getPlayer() instanceof ServerPlayer) {
			net.minecraft.world.level.LevelAccessor world = event.getPlayer().level();
			if (world instanceof ServerLevel) {
				ServerLevel serverLevel = (ServerLevel) world;
				Structure structure = serverLevel.registryAccess().registryOrThrow(Registries.STRUCTURE).get(TofuStructures.TOFU_CASTLE);
				if (structure != null) {
					TofuData data = TofuData.get(serverLevel);
					StructureStart structureStart = serverLevel.structureManager().getStructureAt(event.getPos(), structure);
					if (structureStart.isValid() && !data.getBeatenDungeons().contains(structureStart.getBoundingBox())) {

						ServerPlayer player = (ServerPlayer) event.getPlayer();
						if (!player.isCreative() && !(event.getState().getBlock() instanceof TorchBlock)) {
							player.displayClientMessage(Component.translatable("tofucraft.need_defeat_boss"), true);
							event.setCanceled(true);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event) {
		if (event.getEntity() instanceof ServerPlayer) {
			net.minecraft.world.level.LevelAccessor world = event.getEntity().level();
			if (world instanceof ServerLevel) {
				ServerLevel serverLevel = (ServerLevel) world;
				Structure structure = serverLevel.registryAccess().registryOrThrow(Registries.STRUCTURE).get(TofuStructures.TOFU_CASTLE);
				if (structure != null) {
					TofuData data = TofuData.get(serverLevel);
					StructureStart structureStart = serverLevel.structureManager().getStructureAt(event.getPos(), structure);
					if (structureStart.isValid() && !data.getBeatenDungeons().contains(structureStart.getBoundingBox())) {
						ServerPlayer player = (ServerPlayer) event.getEntity();
						if (!player.isCreative() && !(event.getState().getBlock() instanceof TorchBlock)) {
							player.displayClientMessage(Component.translatable("tofucraft.need_defeat_boss"), true);
							event.setCanceled(true);
						}

					}
				}
			}
		}
	}

	/*
	 * This Event make mob cannot spawn when morijio is near
	 *
	 * */
	@SubscribeEvent
	public static void onCheckSpawn(MobSpawnEvent.FinalizeSpawn event) {
		LivingEntity livingEntity = event.getEntity();
		LevelAccessor level = event.getLevel();
		if (livingEntity instanceof Enemy) {
			if (event.getSpawnType() != MobSpawnType.SPAWNER && event.getSpawnType() != MobSpawnType.EVENT && event.getSpawnType() != MobSpawnType.BREEDING && event.getSpawnType() != MobSpawnType.PATROL) {
				if (level instanceof ServerLevel) {
					Optional<BlockPos> optional = ((ServerLevel) level).getPoiManager().findClosest((p_184069_) -> {
						return p_184069_.is(TofuPoiTypes.MORIJIO);
					}, (p_184055_) -> {
						return true;
					}, livingEntity.blockPosition(), 32, PoiManager.Occupancy.ANY);

					if (optional.isPresent()) {
						event.setResult(Event.Result.DENY);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onCheckZombieSiege(VillageSiegeEvent event) {
		Vec3 vec3 = event.getAttemptedSpawnPos();
		LevelAccessor level = event.getLevel();
		if (level instanceof ServerLevel) {
			Optional<BlockPos> optional = ((ServerLevel) level).getPoiManager().findClosest((p_184069_) -> {
				return p_184069_.is(TofuPoiTypes.MORIJIO);
			}, (p_184055_) -> {
				return true;
			}, BlockPos.containing(vec3), 48, PoiManager.Occupancy.ANY);

			if (optional.isPresent()) {
				event.setCanceled(true);
			}
		}
	}

	@SubscribeEvent
	public static void onWorldLoad(LevelEvent.Load event) {
		if (event.getLevel() instanceof ServerLevel level && level.dimension().location().equals(TofuDimensions.tofu_world.location())) {
			TofuLevelData levelData = new TofuLevelData(level.getServer().getWorldData(), level.getServer().getWorldData().overworldData());
			level.serverLevelData = levelData;
			level.levelData = levelData;
		}
	}

	@SubscribeEvent
	public static void onServerTick(TickEvent.LevelTickEvent tick) {
		if (!tick.level.isClientSide && tick.level instanceof ServerLevel serverWorld) {
			TRAVELER_TOFUNIAN_SPAWNER_MAP.computeIfAbsent(serverWorld,
					k -> new TravelerTofunianSpawner(serverWorld));
			TravelerTofunianSpawner spawner = TRAVELER_TOFUNIAN_SPAWNER_MAP.get(serverWorld);
			spawner.tick();
		}
	}

	@SubscribeEvent
	public static void onServerAboutToStartEvent(ServerStartedEvent event) {
		// SETUP Tofu Worker House
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/plains/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_plains_1"), 10);
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/taiga/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_taiga_1"), 10);
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/savanna/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_savanna_1"), 10);
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/snowy/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_snowy_1"), 10);
		JigsawHelper.registerJigsaw(event.getServer(), new ResourceLocation("minecraft:village/desert/houses"),
				new ResourceLocation("tofucraft:village/tofu_craftsman_house_desert_1"), 10);
	}

	@SubscribeEvent
	public static void onClone(PlayerEvent.Clone event) {
		Player oldPlayer = event.getOriginal();
		Player newPlayer = event.getEntity();
		if (!event.isWasDeath()) {
			oldPlayer.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(oldcap -> {
				newPlayer.getCapability(TofuCraftReload.SOY_HEALTH_CAPABILITY).ifPresent(cap -> {
					cap.deserializeNBT(oldcap.serializeNBT());
				});
			});
		}
	}

	@SubscribeEvent
	public static void onHurt(LivingHurtEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity.isDamageSourceBlocked(event.getSource())) {
			if (entity.getUseItem().is(TofuItems.REFLECT_TOFU_SHIELD.get())) {
				Entity attacker = event.getSource().getDirectEntity();
				if (entity.getUseItem().getItem() instanceof IEnergyContained energyContained) {

					if (energyContained.getEnergy(entity.getUseItem()) >= 50) {
						energyContained.setEnergy(entity.getUseItem(), energyContained.getEnergy(entity.getUseItem()) - 50);

						if (attacker != null) {
							attacker.hurt(entity.damageSources().thorns(entity), 3.0F);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onShieldEvent(ShieldBlockEvent event) {
		if (event.getEntity().getUseItem().is(TofuItems.REFLECT_TOFU_SHIELD.get())) {
			if (event.getEntity().getUseItem().getItem() instanceof IEnergyContained energyContained) {

				if (energyContained.getEnergy(event.getEntity().getUseItem()) >= 50) {
					event.setShieldTakesDamage(false);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onBlockModificationEvent(PlayerInteractEvent.RightClickBlock event) {
		InteractionHand hand = event.getHand();
		ItemStack stack = event.getItemStack();
		Player player = event.getEntity();
		Level level = event.getLevel();
		BlockPos pos = event.getPos();
		if (player != null) {
			if (stack.is(Items.GLASS_BOTTLE)) {

				boolean shroomlight = level.getBlockState(pos).is(Blocks.SHROOMLIGHT);
				if (shroomlight) {
					if (!level.isClientSide) {
						level.levelEvent(2001, pos, Block.getId(Blocks.SHROOMLIGHT.defaultBlockState()));
					}
					player.playSound(SoundEvents.BOTTLE_FILL);
					ContainerUtils.addWithContainer(player, hand, stack, TofuItems.SHROOM_BOTTLE.get().getDefaultInstance(), true);
					level.removeBlock(pos, false);
					event.setCancellationResult(InteractionResult.SUCCESS);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onPotionEffectApplied(MobEffectEvent.Applicable event) {
		if (event.getEffectInstance() != null && event.getEffectInstance().getEffect().getCategory() == MobEffectCategory.HARMFUL) {
			if (EnchantmentHelper.getEnchantmentLevel(TofuEnchantments.EFFECT_PROTECTION.get(), event.getEntity()) > 0) {
				event.setResult(Event.Result.DENY);
			}
		}
	}
}

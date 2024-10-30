package baguchi.tofucraft;

import baguchi.tofucraft.api.tfenergy.IEnergyContained;
import baguchi.tofucraft.attachment.SoyHealthAttachment;
import baguchi.tofucraft.attachment.TofuLivingAttachment;
import baguchi.tofucraft.entity.TofuGandlem;
import baguchi.tofucraft.item.armor.BreakableTofuBootsItem;
import baguchi.tofucraft.registry.TofuAdvancements;
import baguchi.tofucraft.registry.TofuAttachments;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuDataComponents;
import baguchi.tofucraft.registry.TofuDimensions;
import baguchi.tofucraft.registry.TofuEffects;
import baguchi.tofucraft.registry.TofuEnchantments;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuPoiTypes;
import baguchi.tofucraft.registry.TofuStructures;
import baguchi.tofucraft.registry.TofuTags;
import baguchi.tofucraft.utils.ContainerUtils;
import baguchi.tofucraft.utils.JigsawHelper;
import baguchi.tofucraft.utils.RecipeHelper;
import baguchi.tofucraft.utils.TofuDiamondToolUtil;
import baguchi.tofucraft.world.TofuData;
import baguchi.tofucraft.world.TofuLevelData;
import baguchi.tofucraft.world.TravelerTofunianSpawner;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.SelectMusicEvent;
import net.neoforged.neoforge.event.entity.EntityMobGriefingEvent;
import net.neoforged.neoforge.event.entity.living.FinalizeSpawnEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDestroyBlockEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingShieldBlockEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.village.VillageSiegeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class CommonEvents {
	private static final Map<ServerLevel, TravelerTofunianSpawner> TRAVELER_TOFUNIAN_SPAWNER_MAP = new HashMap<>();

	@SubscribeEvent
	public static void onUpdate(EntityTickEvent.Pre event) {
		Entity entity = event.getEntity();
		SoyHealthAttachment soyHealth = entity.getData(TofuAttachments.SOY_HEALTH);

		if (!entity.level().isClientSide() && entity instanceof LivingEntity livingEntity) {
			soyHealth.tick(livingEntity);
			//bad omen
			if (livingEntity.hasEffect(MobEffects.BAD_OMEN)) {
				if (entity.level() instanceof ServerLevel serverLevel) {
					Structure structure = serverLevel.registryAccess().lookupOrThrow(Registries.STRUCTURE).getValueOrThrow(TofuStructures.TOFU_CASTLE);
					if (structure != null) {
						TofuData data = TofuData.get(serverLevel);
						StructureStart structureStart = serverLevel.structureManager().getStructureAt(entity.blockPosition(), structure);
						if (structureStart.isValid() && data.getBeatenDungeons().contains(structureStart.getBoundingBox())) {
							BlockPos.MutableBlockPos blockPos = entity.blockPosition().mutable();

							BoundingBox box = structureStart.getBoundingBox();
							Optional<TofuGandlem> tofuGandlemOptional = SpawnUtil.trySpawnMob(TofuEntityTypes.TOFU_GANDLEM.get(), EntitySpawnReason.TRIGGERED, serverLevel, blockPos, 2, 8, 8, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);

							if (tofuGandlemOptional.isPresent()) {
								TofuGandlem tofuGandlem = tofuGandlemOptional.get();
								tofuGandlem.setSleepSelf(true);
								livingEntity.removeEffect(MobEffects.BAD_OMEN);
								serverLevel.playSound(null, livingEntity.blockPosition(), SoundEvents.APPLY_EFFECT_BAD_OMEN, SoundSource.AMBIENT);
								serverLevel.levelEvent(3020, BlockPos.containing(livingEntity.getEyePosition()), 0);
								serverLevel.levelEvent(3020, BlockPos.containing(tofuGandlem.getEyePosition()), 0);
							}
						}
					}
				}
			}
		}
		TofuLivingAttachment tofuLivingAttachment = entity.getData(TofuAttachments.TOFU_LIVING);
		tofuLivingAttachment.tick(entity);
	}

	@SubscribeEvent
	public static void onMusicPlayed(SelectMusicEvent event) {
		if (Minecraft.getInstance().level != null && Minecraft.getInstance().player != null) {
			Holder<Biome> biome = Minecraft.getInstance().player.level().getBiome(Minecraft.getInstance().player.blockPosition());
			if (Minecraft.getInstance().level.dimension() == TofuDimensions.tofu_world) {
				event.setMusic(biome.value().getBackgroundMusic().orElse(Musics.GAME));
			}
		}
	}
	protected static BlockHitResult getPlayerPOVHitResult(Level p_41436_, Player p_41437_, ClipContext.Fluid p_41438_) {
		Vec3 vec3 = p_41437_.getEyePosition();
		Vec3 vec31 = vec3.add(p_41437_.calculateViewVector(p_41437_.getXRot(), p_41437_.getYRot()).scale(p_41437_.blockInteractionRange()));
		return p_41436_.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, p_41438_, p_41437_));
	}

	@SubscribeEvent
	public static void onUsedEntity(PlayerInteractEvent.EntityInteractSpecific event) {
		ItemStack stack = event.getItemStack();
		Level level = event.getLevel();
		Entity entity = event.getTarget();
		if (entity instanceof AbstractHorse horse && stack.is(TofuItems.SALT.get())) {
			horse.addEffect(new MobEffectInstance(TofuEffects.SALT_BOOST, 3600));
			if (!event.getEntity().isCreative()) {
				stack.shrink(1);
			}
			horse.eating();
			horse.gameEvent(GameEvent.EAT);
			horse.playSound(SoundEvents.HORSE_EAT);
			event.setCancellationResult(InteractionResult.SUCCESS);
			event.setCanceled(true);
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
	public static void onBreakingSpeed(PlayerEvent.BreakSpeed event) {
		ItemStack stack = event.getEntity().getMainHandItem();
		if (stack.is(TofuTags.Items.TOFU_DIAMOND_MINEABLE_ENCHANTABLE)) {
			ItemEnchantments enchantments = stack.get(DataComponents.ENCHANTMENTS);
			if (enchantments != null) {
				event.setNewSpeed(event.getOriginalSpeed() / (enchantments.getLevel(event.getEntity().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(TofuEnchantments.BATCH)) + 1.25F));
			}
		}
	}

	@SubscribeEvent
	public static void onBlockDestroyByEntity(LivingDestroyBlockEvent event) {
		Level world = event.getEntity().level();

		if (world instanceof ServerLevel) {
			ServerLevel serverLevel = (ServerLevel) world;
			Structure structure = serverLevel.registryAccess().lookupOrThrow(Registries.STRUCTURE).getValueOrThrow(TofuStructures.TOFU_CASTLE);
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
	public static void onBlockGriefing(EntityMobGriefingEvent event) {
		Entity entity = event.getEntity();
		Level world = event.getEntity().level();

		if (world instanceof ServerLevel) {
			ServerLevel serverLevel = (ServerLevel) world;
			Structure structure = serverLevel.registryAccess().lookupOrThrow(Registries.STRUCTURE).getValueOrThrow(TofuStructures.TOFU_CASTLE);
			if (structure != null) {
				TofuData data = TofuData.get(serverLevel);
				Vec3 center = event.getEntity().position();
				StructureStart structureStart = serverLevel.structureManager().getStructureAt(new BlockPos((int) center.x, (int) center.y, (int) center.z), structure);
				if (structureStart.isValid() && !data.getBeatenDungeons().contains(structureStart.getBoundingBox())) {
					event.setCanGrief(false);
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
				Structure structure = serverLevel.registryAccess().lookupOrThrow(Registries.STRUCTURE).getValueOrThrow(TofuStructures.TOFU_CASTLE);
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
				Structure structure = serverLevel.registryAccess().lookupOrThrow(Registries.STRUCTURE).getValueOrThrow(TofuStructures.TOFU_CASTLE);
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
	public static void onCheckSpawn(FinalizeSpawnEvent event) {
		LivingEntity livingEntity = event.getEntity();
		net.minecraft.world.level.LevelAccessor level = event.getLevel();
		if (livingEntity instanceof Enemy) {
			if (event.getSpawnType() != EntitySpawnReason.SPAWNER && event.getSpawnType() != EntitySpawnReason.TRIAL_SPAWNER && event.getSpawnType() != EntitySpawnReason.EVENT && event.getSpawnType() != EntitySpawnReason.BREEDING && event.getSpawnType() != EntitySpawnReason.PATROL) {
				if (level instanceof ServerLevel) {
					Optional<BlockPos> optional = ((ServerLevel) level).getPoiManager().findClosest((p_184069_) -> {
						return p_184069_.is(TofuPoiTypes.MORIJIO);
					}, (p_184055_) -> {
						return true;
					}, livingEntity.blockPosition(), 32, PoiManager.Occupancy.ANY);

					if (optional.isPresent()) {
						event.setCanceled(true);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onCheckZombieSiege(VillageSiegeEvent event) {
		Vec3 vec3 = event.getAttemptedSpawnPos();
		net.minecraft.world.level.LevelAccessor level = event.getLevel();
		if (level instanceof ServerLevel) {
			Optional<BlockPos> optional = ((ServerLevel) level).getPoiManager().findClosest((p_184069_) -> {
				return p_184069_.is(TofuPoiTypes.MORIJIO);
			}, (p_184055_) -> {
				return true;
			}, BlockPos.containing(vec3), 32, PoiManager.Occupancy.ANY);

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
	public static void onServerTick(LevelTickEvent.Post tick) {
		if (!tick.getLevel().isClientSide && tick.getLevel() instanceof ServerLevel serverWorld) {
			TRAVELER_TOFUNIAN_SPAWNER_MAP.computeIfAbsent(serverWorld,
					k -> new TravelerTofunianSpawner(serverWorld));
			TravelerTofunianSpawner spawner = TRAVELER_TOFUNIAN_SPAWNER_MAP.get(serverWorld);
			spawner.tick();
		}
	}

	@SubscribeEvent
	public static void onServerAboutToStartEvent(ServerStartedEvent event) {
		// SETUP Tofu Worker House
		JigsawHelper.registerJigsaw(event.getServer(), ResourceLocation.parse("minecraft:village/plains/houses"),
				ResourceLocation.parse("tofucraft:village/tofu_craftsman_house_plains_1"), 10);
		JigsawHelper.registerJigsaw(event.getServer(), ResourceLocation.parse("minecraft:village/taiga/houses"),
				ResourceLocation.parse("tofucraft:village/tofu_craftsman_house_taiga_1"), 10);
		JigsawHelper.registerJigsaw(event.getServer(), ResourceLocation.parse("minecraft:village/savanna/houses"),
				ResourceLocation.parse("tofucraft:village/tofu_craftsman_house_savanna_1"), 10);
		JigsawHelper.registerJigsaw(event.getServer(), ResourceLocation.parse("minecraft:village/snowy/houses"),
				ResourceLocation.parse("tofucraft:village/tofu_craftsman_house_snowy_1"), 10);
		JigsawHelper.registerJigsaw(event.getServer(), ResourceLocation.parse("minecraft:village/desert/houses"),
				ResourceLocation.parse("tofucraft:village/tofu_craftsman_house_desert_1"), 10);
	}

	@SubscribeEvent
	public static void onClone(PlayerEvent.Clone event) {
		Player oldPlayer = event.getOriginal();
		Player newPlayer = event.getEntity();
		if (!event.isWasDeath()) {
			SoyHealthAttachment soyHealth = oldPlayer.getData(TofuAttachments.SOY_HEALTH);
			SoyHealthAttachment soyHealth2 = newPlayer.getData(TofuAttachments.SOY_HEALTH);
			soyHealth2.deserializeNBT(newPlayer.level().registryAccess(), soyHealth.serializeNBT(newPlayer.level().registryAccess()));
		}
	}

	//on fall with tofu boots
	@SubscribeEvent
	public static void onFall(LivingDamageEvent.Post event) {
		float damage = event.getNewDamage();
		LivingEntity entity = event.getEntity();
		ItemStack feet = entity.getItemBySlot(EquipmentSlot.FEET);
		if (event.getSource().is(DamageTypeTags.IS_FALL)) {
			if (feet.getItem() instanceof BreakableTofuBootsItem bootsItem) {
				int unstability = feet.getOrDefault(TofuDataComponents.UNSTABILITY.get(), 0);
				if (damage + unstability < feet.getOrDefault(TofuDataComponents.MAX_FALL_DURABILITY.get(), 0)) {
					feet.set(TofuDataComponents.UNSTABILITY.get(), Mth.ceil(damage + 1) + unstability);
				} else {
					feet.hurtAndBreak(10, entity, EquipmentSlot.FEET);
					entity.playSound(SoundEvents.SLIME_BLOCK_FALL);
				}
				if (entity.isAlive() && damage >= 10) {
					if (entity instanceof ServerPlayer serverPlayer) {
						TofuAdvancements.NARROW_ESCAPE_TRIGGER.get().trigger((ServerPlayer) serverPlayer);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void onHurt(LivingIncomingDamageEvent event) {
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
	public static void onShieldEvent(LivingShieldBlockEvent event) {
		if (event.getEntity().getUseItem().is(TofuItems.REFLECT_TOFU_SHIELD.get())) {
			if (event.getEntity().getUseItem().getItem() instanceof IEnergyContained energyContained) {

				if (energyContained.getEnergy(event.getEntity().getUseItem()) >= 50) {
					event.setBlockedDamage(0);
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
		if (event.getEffectInstance() != null && event.getEffectInstance().getEffect().value() == MobEffects.BLINDNESS) {
			if (EnchantmentHelper.getEnchantmentLevel(event.getEntity().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(TofuEnchantments.EFFECT_PROTECTION), event.getEntity()) > 0) {
				event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
			}
		}

		if (event.getEffectInstance() != null && event.getEffectInstance().getEffect().value() == MobEffects.OOZING) {
			if (EnchantmentHelper.getEnchantmentLevel(event.getEntity().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(TofuEnchantments.EFFECT_PROTECTION), event.getEntity()) > 0) {
				event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
			}
		}

		if (event.getEffectInstance() != null && event.getEffectInstance().getEffect().value() == MobEffects.MOVEMENT_SLOWDOWN) {
			if (EnchantmentHelper.getEnchantmentLevel(event.getEntity().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(TofuEnchantments.EFFECT_PROTECTION), event.getEntity()) > 0) {
				event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
			}
		}

		if (event.getEffectInstance() != null && event.getEffectInstance().getEffect().value() == MobEffects.INFESTED) {
			if (EnchantmentHelper.getEnchantmentLevel(event.getEntity().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(TofuEnchantments.EFFECT_PROTECTION), event.getEntity()) > 0) {
				event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
			}
		}


		if (event.getEffectInstance() != null && event.getEffectInstance().getEffect().value() == MobEffects.HUNGER) {
			if (event.getEntity().hasEffect(TofuEffects.MISO_BOOST)) {
				event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
			}
		}

		if (event.getEffectInstance() != null && event.getEffectInstance().getEffect().value() == MobEffects.CONFUSION) {
			if (event.getEntity().hasEffect(TofuEffects.MISO_BOOST)) {
				event.setResult(MobEffectEvent.Applicable.Result.DO_NOT_APPLY);
			}
		}
	}


	@SubscribeEvent
	public static boolean onBlockStartBreak(BlockEvent.BreakEvent event) {
		ItemStack hand = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
		if (hand.getItem() instanceof PickaxeItem pickaxeItem) {
			Block blockDestroyed = event.getLevel().getBlockState(event.getPos()).getBlock();
			if (event.getLevel() instanceof ServerLevel level) {
				TofuDiamondToolUtil.onBlockStartBreak(hand, level, blockDestroyed, event.getPos(), event.getPlayer());
			}
		}
		return false;
	}
}

package baguchi.tofucraft.entity.projectile;

import baguchi.tofucraft.entity.TofuSlime;
import baguchi.tofucraft.registry.TofuDamageTypes;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuParticleTypes;
import baguchi.tofucraft.registry.TofuTags;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import javax.annotation.Nullable;
import java.util.List;

public class ZundaArrow extends AbstractArrow {
	private int duration = 100;
	private static final ItemStack DEFAULT_ARROW_STACK = new ItemStack(Items.ARROW);
	@Nullable
	private IntOpenHashSet piercingIgnoreEntityIds;
	@Nullable
	private List<Entity> piercedAndKilledEntities;

	public ZundaArrow(EntityType<? extends ZundaArrow> p_37411_, Level p_37412_) {
		super(p_37411_, p_37412_);
	}

	public ZundaArrow(Level p_37414_, LivingEntity p_309162_, ItemStack p_309167_, @Nullable ItemStack p_346408_) {
		super(TofuEntityTypes.ZUNDA_ARROW.get(), p_309162_, p_37414_, p_309167_, p_346408_);
	}

	public ZundaArrow(Level p_37419_, double p_309044_, double p_309099_, double p_308873_, ItemStack p_308959_, @Nullable ItemStack p_345907_) {
		super(TofuEntityTypes.ZUNDA_ARROW.get(), p_309044_, p_309099_, p_308873_, p_37419_, p_308959_, p_345907_);
	}

	public void tick() {
		super.tick();
		if (this.level().isClientSide && !this.isInGround()) {
			this.level().addParticle(TofuParticleTypes.ZUNDA_CLOUD.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
		}

	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(TofuItems.ZUNDA_ARROW.get());
	}

	public DamageSource zundaAttack(@Nullable Entity p_270857_) {
		return this.damageSources().source(TofuDamageTypes.ZUNDA, this, p_270857_);
	}

	@Override
	protected void onHitEntity(EntityHitResult p_36757_) {
		if (!this.level().isClientSide()) {
			Entity entity = p_36757_.getEntity();
			Entity entity1 = this.getOwner();
			float f = (float) this.getDeltaMovement().length();
			double d0 = this.getBaseDamage();
			DamageSource source = zundaAttack(this.getOwner());
			if (this.level() instanceof ServerLevel serverlevel) {
				if (this.getWeaponItem() != null) {
					d0 = (double) EnchantmentHelper.modifyDamage(serverlevel, this.getWeaponItem(), entity, source, (float) d0);
				}
				int i = entity.getRemainingFireTicks();
				int j = Mth.ceil(Mth.clamp((double) f * d0, 0.0, 2.147483647E9));

				if (p_36757_.getEntity() instanceof TofuSlime slime) {
					this.playSound(SoundEvents.ZOMBIE_VILLAGER_CONVERTED, 1.0F, 1.0F);
					this.spawnAtLocation(serverlevel, new ItemStack(TofuItems.TOFUZUNDA.get(), slime.getSize() * 2));
					p_36757_.getEntity().discard();
					this.discard();
				} else if (p_36757_.getEntity().getType().is(TofuTags.EntityTypes.EXTRA_DAMAGE_ZUNDA)) {

					if (p_36757_.getEntity().hurtServer(serverlevel, source, j)) {

						if (entity instanceof LivingEntity livingentity) {
							if (!this.level().isClientSide && this.getPierceLevel() <= 0) {
								livingentity.setArrowCount(livingentity.getArrowCount() + 1);
							}

							this.doKnockback(livingentity, source);
							if (this.level() instanceof ServerLevel serverlevel1) {
								EnchantmentHelper.doPostAttackEffectsWithItemSource(serverlevel1, livingentity, source, this.getWeaponItem());
							}

							this.doPostHurtEffects(livingentity);
							if (livingentity != entity1 && livingentity instanceof Player && entity1 instanceof ServerPlayer && !this.isSilent()) {
								((ServerPlayer) entity1).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
							}

							if (!this.level().isClientSide && entity1 instanceof ServerPlayer serverplayer) {
								if (this.piercedAndKilledEntities != null) {
									CriteriaTriggers.KILLED_BY_ARROW.trigger(serverplayer, this.piercedAndKilledEntities, this.getWeaponItem());
								} else if (!entity.isAlive()) {
									CriteriaTriggers.KILLED_BY_ARROW.trigger(serverplayer, List.of(entity), this.getWeaponItem());
								}
							}
						}

						this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
						this.discard();
					} else {
						entity.setRemainingFireTicks(i);
						this.deflect(ProjectileDeflection.REVERSE, entity, this.getOwner(), false);
						this.setDeltaMovement(this.getDeltaMovement().scale(0.2));
						if (!this.level().isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7) {
							if (this.pickup == AbstractArrow.Pickup.ALLOWED) {
								this.spawnAtLocation(serverlevel, this.getPickupItem(), 0.1F);
							}

							this.discard();
						}
					}
				} else {
					if (p_36757_.getEntity() instanceof LivingEntity) {
						MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.REGENERATION, (int) (this.duration * j), 0);
						((LivingEntity) p_36757_.getEntity()).addEffect(mobeffectinstance, this.getEffectSource());
						this.discard();
					}
				}
			}
		}
	}


	public void readAdditionalSaveData(CompoundTag p_37424_) {
		super.readAdditionalSaveData(p_37424_);
		if (p_37424_.contains("Duration")) {
			this.duration = p_37424_.getInt("Duration");
		}

	}

	public void addAdditionalSaveData(CompoundTag p_37426_) {
		super.addAdditionalSaveData(p_37426_);
		p_37426_.putInt("Duration", this.duration);
	}
}
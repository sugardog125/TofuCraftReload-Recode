package baguchi.tofucraft.entity.effect;

import baguchi.tofucraft.entity.ShuDofuSpider;
import baguchi.tofucraft.registry.TofuEntityTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.List;

public class NattoCobWebEntity extends LivingEntity {
	protected static final EntityDataAccessor<Direction> DATA_ATTACH_FACE_ID = SynchedEntityData.defineId(NattoCobWebEntity.class, EntityDataSerializers.DIRECTION);

	private final NonNullList<ItemStack> handItems = NonNullList.withSize(2, ItemStack.EMPTY);
	private final NonNullList<ItemStack> armorItems = NonNullList.withSize(4, ItemStack.EMPTY);
	private int lifeTime;
	private static final int discardTime = 600;

	public NattoCobWebEntity(EntityType<? extends NattoCobWebEntity> p_19870_, Level p_19871_) {
		super(p_19870_, p_19871_);
	}

	public NattoCobWebEntity(Level level, double x, double y, double z) {
		this(TofuEntityTypes.NATTO_COBWEB.get(), level);
		this.setPos(x, y, z);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DATA_ATTACH_FACE_ID, Direction.DOWN);
	}

	public Direction getAttachFace() {
		return this.entityData.get(DATA_ATTACH_FACE_ID);
	}

	public void setAttachFace(Direction p_149789_) {
		this.entityData.set(DATA_ATTACH_FACE_ID, p_149789_);
	}

	@Override
	protected AABB makeBoundingBox(Vec3 vec3) {
		Direction direction = this.getAttachFace().getOpposite();
		double d0 = (double) this.getX();
		double d1 = (double) this.getY();
		double d2 = (double) this.getZ();
		double d6 = (double) this.getType().getWidth() / 2;
		double d7 = (double) this.getType().getHeight() / 2;
		double d8 = (double) this.getType().getWidth() / 2;
		if (direction.getAxis() == Direction.Axis.Z) {
			d8 = this.getType().getHeight() / 2;
			d7 = this.getType().getWidth() / 2;
		} else if (direction.getAxis() == Direction.Axis.X) {
			d6 = this.getType().getHeight() / 2;
			d7 = this.getType().getWidth() / 2;
		} else if (direction == Direction.UP) {
			d7 = (double) this.getType().getHeight() / 2;
			d1 += (double) this.getType().getHeight() / 2;
		}

		return new AABB(d0 - d6, d1 - d7, d2 - d8, d0 + d6, d1 + d7, d2 + d8);
	}

	private double offs(int p_31710_) {
		return p_31710_ % 32 == 0 ? 0.5 : 0.0;
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> p_33434_) {
		if (DATA_ATTACH_FACE_ID.equals(p_33434_)) {
			this.setBoundingBox(this.makeBoundingBox());
		}

		super.onSyncedDataUpdated(p_33434_);
	}

	@Override
	public void lerpTo(double p_33411_, double p_33412_, double p_33413_, float p_33414_, float p_33415_, int p_33416_) {
		this.lerpSteps = 0;
		this.setPos(p_33411_, p_33412_, p_33413_);
		this.setRot(p_33414_, p_33415_);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag p_33432_) {
		super.readAdditionalSaveData(p_33432_);
		this.setAttachFace(Direction.from3DDataValue(p_33432_.getByte("AttachFace")));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag p_33443_) {
		super.addAdditionalSaveData(p_33443_);
		p_33443_.putByte("AttachFace", (byte) this.getAttachFace().get3DDataValue());
	}

	@Override
	public void tick() {
		stepSlow();

		if (this.tickCount > 40 && !this.level().isClientSide && !this.isPassenger() && this.level().noBlockCollision(this, this.getBoundingBox().inflate(0.1F))) {
			this.discard();
		}
		if (lifeTime >= discardTime) {
			this.discard();
		}
		lifeTime++;
		super.tick();
	}


	@Override
	public Vec3 getDeltaMovement() {
		return Vec3.ZERO;
	}

	@Override
	public void setDeltaMovement(Vec3 p_149804_) {
	}

	public HumanoidArm getMainArm() {
		return HumanoidArm.RIGHT;
	}

	public void stepSlow() {
		AABB area = this.getBoundingBox();
		List<LivingEntity> entitiesHit = this.level().getEntitiesOfClass(LivingEntity.class, area);
		for (LivingEntity entity : entitiesHit) {
			double d0 = Math.abs(entity.getDeltaMovement().y);
			double d1 = Math.abs(entity.getDeltaMovement().x) + Math.abs(entity.getDeltaMovement().z);
			this.resetFallDistance();
			//It's not a block, but need blockState, so I'll put in cobweb.
			entity.makeStuckInBlock(Blocks.COBWEB.defaultBlockState(), new Vec3(0.3D, (double) 0.05F, 0.3D));
		}
	}
	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 18.0D).add(Attributes.KNOCKBACK_RESISTANCE, 5.0D);
	}

	public boolean isSpawing() {
		return lifeTime < 4;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 3 && !this.level().isClientSide()) {
			this.discard();
		}

	}

	@Override
	public boolean canDrownInFluidType(FluidType type) {
		return false;
	}

	@Override
	public boolean hurtServer(ServerLevel serverLevel, DamageSource p_31461_, float p_31462_) {
		if (this.isInvulnerableTo(serverLevel, p_31461_)) {
			return false;
		} else if (!p_31461_.is(DamageTypes.SWEET_BERRY_BUSH) && !p_31461_.is(DamageTypes.CACTUS) && !p_31461_.is(DamageTypes.CRAMMING) && !p_31461_.is(DamageTypes.IN_WALL) && !p_31461_.is(DamageTypes.STALAGMITE) && !p_31461_.is(DamageTypes.DROWN) && !(p_31461_.getEntity() instanceof ShuDofuSpider)) {
			Entity entity = p_31461_.getDirectEntity();
			if (entity instanceof Projectile) {
				return false;
			}
			return super.hurtServer(serverLevel, p_31461_, p_31462_);
		} else {
			return false;
		}
	}

	protected void pushEntities() {
	}

	public Iterable<ItemStack> getHandSlots() {
		return this.handItems;
	}

	public Iterable<ItemStack> getArmorSlots() {
		return this.armorItems;
	}

	public ItemStack getItemBySlot(EquipmentSlot p_31612_) {
		switch (p_31612_.getType()) {
			case HAND:
				return this.handItems.get(p_31612_.getIndex());
			case HUMANOID_ARMOR:
				return this.armorItems.get(p_31612_.getIndex());
			default:
				return ItemStack.EMPTY;
		}
	}

	public void setItemSlot(EquipmentSlot p_31584_, ItemStack p_31585_) {
		this.verifyEquippedItem(p_31585_);
		switch (p_31584_.getType()) {
			case HAND:
				this.onEquipItem(p_31584_, this.handItems.set(p_31584_.getIndex(), p_31585_), p_31585_);
				break;
			case HUMANOID_ARMOR:
				this.onEquipItem(p_31584_, this.armorItems.set(p_31584_.getIndex(), p_31585_), p_31585_);
		}

	}

	public boolean isPushable() {
		return false;
	}

	@Override
	public boolean canBeAffected(MobEffectInstance p_21197_) {
		return false;
	}
}

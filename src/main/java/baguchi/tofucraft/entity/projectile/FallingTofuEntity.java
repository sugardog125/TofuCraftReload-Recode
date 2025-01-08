package baguchi.tofucraft.entity.projectile;

import baguchi.tofucraft.registry.TofuDamageTypes;
import baguchi.tofucraft.registry.TofuEntityTypes;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

import javax.annotation.Nullable;

/**
 * <p>Simple Falling Block Projectile.</p>
 * <p>Structure based on <a href=https://github.com/TeamTwilight/twilightforest/blob/1.19.x/src/main/java/twilightforest/entity/projectile/ThrownBlock.java>ThrownBlock</a></p>
 *
 * @author bagu_chan
 */

public class FallingTofuEntity extends ThrowableProjectile {

	private BlockState state = Blocks.STONE.defaultBlockState();
	private boolean canPlace = true;

	public FallingTofuEntity(EntityType<? extends FallingTofuEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	public FallingTofuEntity(Level world, LivingEntity thrower, @Nullable BlockState state) {
		super(TofuEntityTypes.FALLING_TOFU.get(), thrower.getX(), thrower.getY(), thrower.getZ(), world);
		this.setOwner(thrower);
		if (state != null) {
			this.state = state;
		}
	}

	@Override
	protected void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.put("BlockState", NbtUtils.writeBlockState(this.state));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	@Override
	protected void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.state = NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), tag.getCompound("BlockState"));
	}

	@Override
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			ParticleOptions particle = new BlockParticleOption(ParticleTypes.BLOCK, this.state);
			for (int i = 0; i < 20; i++) {
				this.level().addParticle(particle, this.getX(), this.getY(), this.getZ(), this.random.nextGaussian() * 0.05D, this.random.nextDouble() * 0.2D, this.random.nextGaussian() * 0.05D);
			}
		} else {
			super.handleEntityEvent(id);
		}
	}

	public DamageSource tofuAttack(@Nullable Entity p_270857_) {
		return this.damageSources().source(TofuDamageTypes.FALLING_TOFU, this, p_270857_);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		if (!this.level().isClientSide()) {
			result.getEntity().hurt(tofuAttack(this.getOwner()), 3);

			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if (!this.level().isClientSide()) {
			this.level().broadcastEntityEvent(this, (byte) 3);
			this.gameEvent(GameEvent.BLOCK_DESTROY, this.getOwner());
			if (this.canPlace) {
				this.level().setBlock(this.blockPosition(), this.state, 2);
			}
			this.discard();
		}
	}

	public BlockState getBlockState() {
		return this.state;
	}

	public void setCanPlace(boolean canPlace) {
		this.canPlace = canPlace;
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity p_352287_) {
		return new ClientboundAddEntityPacket(this, p_352287_, Block.getId(this.getBlockState()));
	}


	@Override
	public void recreateFromPacket(ClientboundAddEntityPacket packet) {
		super.recreateFromPacket(packet);
		this.state = Block.stateById(packet.getData());
	}
}
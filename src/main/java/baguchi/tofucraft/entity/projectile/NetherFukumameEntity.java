package baguchi.tofucraft.entity.projectile;

import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class NetherFukumameEntity extends FukumameEntity {
	public NetherFukumameEntity(EntityType<? extends NetherFukumameEntity> p_i50154_1_, Level p_i50154_2_) {
		super(p_i50154_1_, p_i50154_2_);
	}

	public NetherFukumameEntity(Level worldIn, LivingEntity throwerIn) {
		super(TofuEntityTypes.NETHER_FUKUMAME.get(), throwerIn, worldIn);
	}

	public NetherFukumameEntity(Level worldIn, LivingEntity throwerIn, ItemStack stack) {
		super(TofuEntityTypes.NETHER_FUKUMAME.get(), throwerIn, worldIn);
		this.firedFromWeapon = stack.copy();
	}

	public NetherFukumameEntity(Level worldIn, double x, double y, double z) {
		super(TofuEntityTypes.NETHER_FUKUMAME.get(), worldIn, x, y, z);
	}

	public NetherFukumameEntity(EntityType<? extends NetherFukumameEntity> p_i50154_1_, Level worldIn, double x, double y, double z) {
		super(p_i50154_1_, worldIn, x, y, z);
	}


	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 3) {
			double d0 = 0.08D;
			for (int i = 0; i < 6; i++) {
				this.level().addParticle(new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(TofuItems.SEEDS_SOYBEANS_NETHER.get())), getX(), getY(), getZ(), (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D);
			}
		}
		if (id == 4) {
			this.level().addParticle(ParticleTypes.CRIMSON_SPORE, getX(), getY(), getZ(), (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D);
		}
	}

	@Override
	public void tick() {
		super.tick();
		this.level().broadcastEntityEvent(this, (byte) 4);
	}

	@Override
	protected void onHitEntity(EntityHitResult p_37404_) {
		super.onHitEntity(p_37404_);
	}
}
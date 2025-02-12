package baguchi.tofucraft.fluid_type;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;

public class DoubanjiangFluidType extends FluidType {
	public DoubanjiangFluidType(Properties properties) {
		super(properties);
	}

	@Override
	public boolean move(FluidState state, LivingEntity entity, Vec3 movementVector, double gravity) {
		boolean flag = entity.getDeltaMovement().y <= 0.0D;
		double d8 = entity.getY();

		entity.moveRelative(0.02F, movementVector);
		entity.move(MoverType.SELF, entity.getDeltaMovement());

		if (entity.getFluidTypeHeight(this) <= entity.getFluidJumpThreshold()) {
			entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.8D, (double) 0.8F, 0.8D));
			Vec3 vec33 = entity.getFluidFallingAdjustedMovement(gravity, flag, entity.getDeltaMovement());
			entity.setDeltaMovement(vec33);
		} else {
			entity.setDeltaMovement(entity.getDeltaMovement().scale(0.8D));
		}


		return true;
	}

}

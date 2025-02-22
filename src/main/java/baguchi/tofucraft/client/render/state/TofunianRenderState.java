package baguchi.tofucraft.client.render.state;

import baguchi.tofucraft.entity.Tofunian;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;

import javax.annotation.Nullable;

public class TofunianRenderState extends AbstractTofunianRenderState {
	public final AnimationState happyAnimationState = new AnimationState();
	public final AnimationState eatFoodAnimationState = new AnimationState();
	public final AnimationState callAnimationState = new AnimationState();

	public Tofunian.Actions actions;
	public Tofunian.Roles roles;
	@Nullable
	public ResourceLocation texture;
}

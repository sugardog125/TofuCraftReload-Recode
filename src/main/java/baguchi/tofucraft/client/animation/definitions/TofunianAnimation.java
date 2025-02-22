package baguchi.tofucraft.client.animation.definitions;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class TofunianAnimation {

	public static final AnimationDefinition ASK_FOOD = AnimationDefinition.Builder.withLength(0f)
			.addAnimation("right_arm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(-100.81f, -22.14f, 4.11f),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("left_arm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(-97.98f, 19.82f, -2.72f),
									AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition HAPPY = AnimationDefinition.Builder.withLength(1.5f)
			.addAnimation("right_arm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 107.5f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 77.5f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 107.5f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 77.5f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 107.5f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
									AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("left_arm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, -107.5f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, -70f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, -107.5f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, -70f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, -107.5f),
									AnimationChannel.Interpolations.CATMULLROM),
							new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
									AnimationChannel.Interpolations.CATMULLROM))).build();
	public static final AnimationDefinition AGREE = AnimationDefinition.Builder.withLength(0.75f).looping()
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(25f, 0f, 0f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25f, KeyframeAnimations.degreeVec(-2.5f, 0f, 0f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5f, KeyframeAnimations.degreeVec(25f, 0f, 0f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.75f, KeyframeAnimations.degreeVec(-2.5f, 0f, 0f),
									AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition CRY = AnimationDefinition.Builder.withLength(0f)
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(35f, 0f, 0f),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("right_arm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(-97.17f, -15.06f, -1.01f),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("left_arm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(-94.18f, 13.53f, -3.08f),
									AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition EAT = AnimationDefinition.Builder.withLength(0.5f).looping()
			.addAnimation("right_arm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(-112.8f, -28.02f, 11.17f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25f, KeyframeAnimations.degreeVec(-87.8f, -28.02f, 11.17f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5f, KeyframeAnimations.degreeVec(-112.8f, -28.02f, 11.17f),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("left_arm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(-112.8f, 28.02f, -11.17f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.25f, KeyframeAnimations.degreeVec(-80.3f, 28.02f, -11.17f),
									AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.5f, KeyframeAnimations.degreeVec(-112.8f, 28.02f, -11.17f),
									AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition AVOIDING = AnimationDefinition.Builder.withLength(0f)
			.addAnimation("head",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(-25f, 0f, 0f),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("right_arm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(-208.81f, -25.66f, -16.1f),
									AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("left_arm",
					new AnimationChannel(AnimationChannel.Targets.ROTATION,
							new Keyframe(0f, KeyframeAnimations.degreeVec(-192.78f, -1.15f, 27.48f),
									AnimationChannel.Interpolations.LINEAR))).build();
	public static final AnimationDefinition BABY = AnimationDefinition.Builder.withLength(0.0F)
			.addAnimation("root", new AnimationChannel(AnimationChannel.Targets.SCALE,
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.6F, 0.6F, 0.6F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("root", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -10.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.SCALE,
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.3F, 1.3F, 1.3F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

	public static final AnimationDefinition SIT = AnimationDefinition.Builder.withLength(0.0F)
			.addAnimation("root", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(0F, 5F, 0F), AnimationChannel.Interpolations.LINEAR)
			))

			.build();

	public static final AnimationDefinition call = AnimationDefinition.Builder.withLength(1.25F)
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right_arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 110.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 140.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 110.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 140.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 110.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 110.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();

	public static final AnimationDefinition call_child = AnimationDefinition.Builder.withLength(1.0F)
			.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -35.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.25F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 0.78F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, 1.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -22.5F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();
}

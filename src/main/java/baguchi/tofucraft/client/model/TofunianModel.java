package baguchi.tofucraft.client.model;

import baguchi.tofucraft.client.animation.definitions.TofunianAnimation;
import baguchi.tofucraft.client.render.state.TofunianRenderState;
import baguchi.tofucraft.entity.Tofunian;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class TofunianModel<T extends TofunianRenderState> extends AbstractTofunianModel<T> {

	public TofunianModel(ModelPart p_170688_) {
		super(p_170688_);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("roots", CubeListBuilder.create(), PartPose.offset(0.0F, 25.0F, 0.0F));

		PartDefinition left_leg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(), PartPose.offset(1.5F, -6.0F, 0.0F));

		PartDefinition right_leg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -6.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -11.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(8, 16).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(28, 16).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -11.5F, 0.0F));

		PartDefinition left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(28, 16).mirror().addBox(0.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(), PartPose.offset(3.0F, -11.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(T entity) {
		super.setupAnim(entity);
		this.head.yRot = entity.yRot * 0.017453292F;
		if (entity.actions != Tofunian.Actions.NORMAL) {
			this.rightArm.xRot = 0.0F;
			this.leftArm.xRot = 0.0F;
		}
		this.animate(entity.happyAnimationState, TofunianAnimation.HAPPY, entity.ageInTicks);
		this.animate(entity.eatFoodAnimationState, TofunianAnimation.EAT, entity.ageInTicks);
		if (entity.isBaby) {
			this.animate(entity.callAnimationState, TofunianAnimation.call_child, entity.ageInTicks);
		} else {
			this.animate(entity.callAnimationState, TofunianAnimation.call, entity.ageInTicks);
		}

		if (entity.actions == Tofunian.Actions.CRY) {
			this.head.xRot = 0.0F;
			this.head.yRot = 0.0F;
			this.applyStatic(TofunianAnimation.CRY);
		} else if (entity.actions == Tofunian.Actions.AVOID) {
			this.applyStatic(TofunianAnimation.AVOIDING);
		} else if (entity.actions == Tofunian.Actions.SIT) {
			this.rightArm.xRot = -0.62831855F;
			this.rightArm.yRot = 0.0F;
			this.rightArm.zRot = 0.0F;
			this.leftArm.xRot = -0.62831855F;
			this.leftArm.yRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightLeg.xRot = (float) (-Math.PI / 2F);
			this.rightLeg.yRot = 0.31415927F;
			this.rightLeg.zRot = 0.07853982F;
			this.leftLeg.xRot = (float) (-Math.PI / 2F);
			this.leftLeg.yRot = -0.31415927F;
			this.leftLeg.zRot = -0.07853982F;
			this.applyStatic(TofunianAnimation.SIT);
		}
	}

}
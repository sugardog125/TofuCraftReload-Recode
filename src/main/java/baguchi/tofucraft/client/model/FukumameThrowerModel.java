package baguchi.tofucraft.client.model;// Made with Blockbench 4.7.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import baguchi.tofucraft.client.render.state.FukumameThrowerRenderState;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.PiglinRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.piglin.PiglinArmPose;

public class FukumameThrowerModel extends HumanoidModel<FukumameThrowerRenderState> {

	public final ModelPart fukumame;
	public final ModelPart rightEar = this.head.getChild("right_ear");
	public final ModelPart leftEar = this.head.getChild("left_ear");

	public FukumameThrowerModel(ModelPart root) {
		super(root);
		this.fukumame = root.getChild("body").getChild("bone2").getChild("fukumame");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("ear", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("cloak", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));



		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(52, 34).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_sleeve = partdefinition.addOrReplaceChild("left_sleeve", CubeListBuilder.create().texOffs(52, 50).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(12, 60).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition jacket = partdefinition.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(28, 28).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(32, 22).addBox(-2.0F, -4.0F, -5.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 3).addBox(2.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, -2.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild(
				"hat", CubeListBuilder.create(), PartPose.ZERO
		);
		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(36, 0).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -6.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(28, 60).addBox(0.0F, 0.0F, -2.0F, 1.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -6.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bone2 = body.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -24.0F, 2.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(30, 10).addBox(-3.0F, -24.0F, 3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition fukumame = bone2.addOrReplaceChild("fukumame", CubeListBuilder.create().texOffs(110, 122).addBox(-3.0F, -23.0F, 3.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_sleeve = partdefinition.addOrReplaceChild("right_sleeve", CubeListBuilder.create().texOffs(52, 18).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition left_pants = partdefinition.addOrReplaceChild("left_pants", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(36, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition right_pants = partdefinition.addOrReplaceChild("right_pants", CubeListBuilder.create().texOffs(20, 44).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(FukumameThrowerRenderState entity) {
		super.setupAnim(entity);
		float f = entity.walkAnimationPos;
		float f1 = entity.walkAnimationSpeed;
		float f2 = (float) (Math.PI / 6);
		float f3 = entity.ageInTicks * 0.1F + f * 0.5F;
		float f4 = 0.08F + f1 * 0.4F;
		this.leftEar.zRot = (float) (-Math.PI / 6) - Mth.cos(f3 * 1.2F) * f4;
		this.rightEar.zRot = (float) (Math.PI / 6) + Mth.cos(f3) * f4;
		float f5 = (float) (Math.PI / 6);
		float f6 = entity.attackTime;
		PiglinArmPose piglinarmpose = entity.armPose;
		if (piglinarmpose == PiglinArmPose.DANCING) {
			float f7 = entity.ageInTicks / 60.0F;
			this.rightEar.zRot = (float) (Math.PI / 6) + (float) (Math.PI / 180.0) * Mth.sin(f7 * 30.0F) * 10.0F;
			this.leftEar.zRot = (float) (-Math.PI / 6) - (float) (Math.PI / 180.0) * Mth.cos(f7 * 30.0F) * 10.0F;
			this.head.x = this.head.x + Mth.sin(f7 * 10.0F);
			this.head.y = this.head.y + Mth.sin(f7 * 40.0F) + 0.4F;
			this.rightArm.zRot = (float) (Math.PI / 180.0) * (70.0F + Mth.cos(f7 * 40.0F) * 10.0F);
			this.leftArm.zRot = this.rightArm.zRot * -1.0F;
			this.rightArm.y = this.rightArm.y + (Mth.sin(f7 * 40.0F) * 0.5F - 0.5F);
			this.leftArm.y = this.leftArm.y + Mth.sin(f7 * 40.0F) * 0.5F + 0.5F;
			this.body.y = this.body.y + Mth.sin(f7 * 40.0F) * 0.35F;
		} else if (piglinarmpose == PiglinArmPose.ATTACKING_WITH_MELEE_WEAPON && f6 == 0.0F) {
			this.holdWeaponHigh(entity);
		} else if (piglinarmpose == PiglinArmPose.CROSSBOW_HOLD) {
			AnimationUtils.animateCrossbowHold(this.rightArm, this.leftArm, this.head, entity.mainArm == HumanoidArm.RIGHT);
		} else if (piglinarmpose == PiglinArmPose.CROSSBOW_CHARGE) {
			AnimationUtils.animateCrossbowCharge(
					this.rightArm, this.leftArm, entity.maxCrossbowChageDuration, entity.ticksUsingItem, entity.mainArm == HumanoidArm.RIGHT
			);
		} else if (piglinarmpose == PiglinArmPose.ADMIRING_ITEM) {
			this.head.xRot = 0.5F;
			this.head.yRot = 0.0F;
			if (entity.mainArm == HumanoidArm.LEFT) {
				this.rightArm.yRot = -0.5F;
				this.rightArm.xRot = -0.9F;
			} else {
				this.leftArm.yRot = 0.5F;
				this.leftArm.xRot = -0.9F;
			}
		}
		if (entity.isCharge) {
			this.rightArm.xRot = (float) ((Math.PI / 180F) * 97.5F);
			this.rightArm.yRot = (float) ((Math.PI / 180F) * 40F);
		}

		if (entity.getFukumameCount > 64) {
			this.fukumame.y = 0F;
		} else if (entity.getFukumameCount > 0) {
			this.fukumame.y = 0F - ((entity.getFukumameCount - 64F) / 64F) * 5F;
		}
		this.fukumame.visible = entity.getFukumameCount > 0;
		this.hat.copyFrom(this.head);
	}


	protected void setupAttackAnimation(FukumameThrowerRenderState p_362671_, float p_103352_) {
		float f = p_362671_.attackTime;
		if (f > 0.0F && p_362671_.armPose == PiglinArmPose.ATTACKING_WITH_MELEE_WEAPON) {
			AnimationUtils.swingWeaponDown(this.rightArm, this.leftArm, p_362671_.mainArm, f, p_362671_.ageInTicks);
		} else {
			super.setupAttackAnimation(p_362671_, p_103352_);
		}
	}

	private void holdWeaponHigh(PiglinRenderState p_361494_) {
		if (p_361494_.mainArm == HumanoidArm.LEFT) {
			this.leftArm.xRot = -1.8F;
		} else {
			this.rightArm.xRot = -1.8F;
		}
	}
}
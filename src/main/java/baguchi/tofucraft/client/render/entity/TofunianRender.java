package baguchi.tofucraft.client.render.entity;

import baguchi.bagus_lib.client.layer.CustomArmorLayer;
import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.TofunianModel;
import baguchi.tofucraft.client.render.layer.TofunianClothLayer;
import baguchi.tofucraft.client.render.layer.TofunianEyeLayer;
import baguchi.tofucraft.client.render.layer.TofunianRoleLayer;
import baguchi.tofucraft.client.render.state.TofunianRenderState;
import baguchi.tofucraft.entity.Tofunian;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

@OnlyIn(Dist.CLIENT)
public class TofunianRender extends MobRenderer<Tofunian, TofunianRenderState, TofunianModel<TofunianRenderState>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/tofunian.png");
	public static final ResourceLocation BAGU_LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/secret/bagunian.png");

	public TofunianRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TofunianModel<>(p_173956_.bakeLayer(TofuModelLayers.TOFUNIAN)), 0.5F);
		this.addLayer(new TofunianEyeLayer<>(this));
		this.addLayer(new TofunianClothLayer(this));
		this.addLayer(new TofunianRoleLayer(this));
		this.addLayer(new CustomArmorLayer<>(this, p_173956_));
		this.addLayer(new ItemInHandLayer<>(this));
	}


	@Override
	protected void scale(TofunianRenderState p_362272_, PoseStack p_115315_) {
		super.scale(p_362272_, p_115315_);
		float var4 = p_362272_.scale;
		if (p_362272_.isBaby) {
			this.shadowRadius = 0.25F;
		} else {
			this.shadowRadius = 0.5F;
		}

		p_115315_.scale(var4, var4, var4);
	}

	public ResourceLocation getTextureLocation(TofunianRenderState entity) {
		if (entity.nameTag != null) {
			String s = ChatFormatting.stripFormatting(entity.nameTag.getString());
			if (s != null && "bagu_chan".equals(s)) {
				LocalDate localdate = LocalDate.now();
				int i = localdate.get(ChronoField.DAY_OF_MONTH);
				int j = localdate.get(ChronoField.MONTH_OF_YEAR);
				if ((j == 10 && i == 31) || (j == 12 && i == 15)) {
					return BAGU_LOCATION;
				}
			}
		}
		return LOCATION;
	}

	@Override
	public TofunianRenderState createRenderState() {
		return new TofunianRenderState();
	}

	@Override
	public void extractRenderState(Tofunian p_362733_, TofunianRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
		HumanoidMobRenderer.extractHumanoidRenderState(p_362733_, p_360515_, p_361157_, this.itemModelResolver);

		p_360515_.id = p_362733_.getId();
		p_360515_.riding = !p_362733_.getPassengers().isEmpty();
		p_360515_.unhappyCounter = p_362733_.getUnhappyCounter();
		p_360515_.attackTime = p_362733_.attackAnim;
		p_360515_.eatFoodAnimationState.copyFrom(p_362733_.eatFoodAnimationState);
		p_360515_.happyAnimationState.copyFrom(p_362733_.happyAnimationState);
		p_360515_.actions = p_362733_.getAction();
		p_360515_.roles = p_362733_.getRole();
		p_360515_.type = p_362733_.getTofunianType();
	}
}

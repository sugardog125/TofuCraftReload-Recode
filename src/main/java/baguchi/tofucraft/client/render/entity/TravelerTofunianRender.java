package baguchi.tofucraft.client.render.entity;

import baguchi.bagus_lib.client.layer.CustomArmorLayer;
import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.client.TofuModelLayers;
import baguchi.tofucraft.client.model.TravelerTofunianModel;
import baguchi.tofucraft.client.render.layer.TofunianEyeLayer;
import baguchi.tofucraft.client.render.state.AbstractTofunianRenderState;
import baguchi.tofucraft.entity.TravelerTofunian;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TravelerTofunianRender extends MobRenderer<TravelerTofunian, AbstractTofunianRenderState, TravelerTofunianModel<AbstractTofunianRenderState>> {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofunian/traveler_tofunian.png");

	public TravelerTofunianRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_, new TravelerTofunianModel<>(p_173956_.bakeLayer(TofuModelLayers.TRAVELER_TOFUNIAN)), 0.5F);
		this.addLayer(new TofunianEyeLayer<>(this));
		this.addLayer(new CustomArmorLayer<>(this, p_173956_));
		this.addLayer(new ItemInHandLayer<>(this));
	}

	@Override
	public AbstractTofunianRenderState createRenderState() {
		return new AbstractTofunianRenderState();
	}

	@Override
	public ResourceLocation getTextureLocation(AbstractTofunianRenderState p_368654_) {
		return LOCATION;
	}

	@Override
	public void extractRenderState(TravelerTofunian p_362733_, AbstractTofunianRenderState p_360515_, float p_361157_) {
		super.extractRenderState(p_362733_, p_360515_, p_361157_);
		HumanoidMobRenderer.extractHumanoidRenderState(p_362733_, p_360515_, p_361157_, this.itemModelResolver);

		p_360515_.id = p_362733_.getId();
		p_360515_.riding = !p_362733_.getPassengers().isEmpty();
		p_360515_.unhappyCounter = p_362733_.getUnhappyCounter();
		p_360515_.attackTime = p_362733_.attackAnim;
	}
}

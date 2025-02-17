package baguchi.tofucraft.client.render.entity;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuCreeperRender extends CreeperRenderer {
	private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/entity/tofucreeper.png");

	public TofuCreeperRender(EntityRendererProvider.Context p_173956_) {
		super(p_173956_);
	}

	@Override
	public ResourceLocation getTextureLocation(CreeperRenderState p_114029_) {
		return LOCATION;
	}
}

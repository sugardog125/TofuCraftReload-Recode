package baguchi.tofucraft.client.render;

import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;

public class TofuCraftRenderType extends RenderType {
	public TofuCraftRenderType(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
		super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
	}

	public static final ParticleRenderType PARTICLE_SHEET_TRANSLUCENT = new ParticleRenderType(
			"PARTICLE_SHEET_TRANSLUCENT", RenderType.opaqueParticle(TextureAtlas.LOCATION_PARTICLES)
	);
}

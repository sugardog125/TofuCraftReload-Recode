package baguchi.tofucraft.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.CoreShaders;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import org.joml.Matrix4f;

public class ClientUtils {

	@OnlyIn(Dist.CLIENT)
	public static void playPortalSound(Player localPlayer) {
		Minecraft.getInstance()
				.getSoundManager()
				.play(SimpleSoundInstance.forLocalAmbience(SoundEvents.PORTAL_TRIGGER, localPlayer.getRandom().nextFloat() * 0.4F + 0.8F, 0.25F));

	}

	public static void renderOverlay(Minecraft mc, PoseStack stack, IClientFluidTypeExtensions clientFluidTypeExtensions) {
		ResourceLocation texture = clientFluidTypeExtensions.getRenderOverlayTexture(mc);
		if (texture == null) return;
		RenderSystem.setShader(CoreShaders.POSITION_TEX);
		RenderSystem.setShaderTexture(0, texture);
		BufferBuilder buffer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		BlockPos playerEyePos = BlockPos.containing(mc.player.getX(), mc.player.getEyeY(), mc.player.getZ());
		float brightness = LightTexture.getBrightness(mc.player.level().dimensionType(), mc.player.level().getMaxLocalRawBrightness(playerEyePos));
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderColor(brightness, brightness, brightness, 0.85F);
		float uOffset = -mc.player.getYRot() / 64.0F;
		float vOffset = mc.player.getXRot() / 64.0F;
		Matrix4f pose = stack.last().pose();
		buffer.addVertex(pose, -1.0F, -1.0F, -0.5F).setUv(4.0F + uOffset, 4.0F + vOffset);
		buffer.addVertex(pose, 1.0F, -1.0F, -0.5F).setUv(uOffset, 4.0F + vOffset);
		buffer.addVertex(pose, 1.0F, 1.0F, -0.5F).setUv(uOffset, vOffset);
		buffer.addVertex(pose, -1.0F, 1.0F, -0.5F).setUv(4.0F + uOffset, vOffset);
		BufferUploader.drawWithShader(buffer.buildOrThrow());
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
	}
}

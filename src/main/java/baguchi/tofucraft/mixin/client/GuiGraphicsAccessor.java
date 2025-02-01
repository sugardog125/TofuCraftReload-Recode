package baguchi.tofucraft.mixin.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GuiGraphics.class)
public interface GuiGraphicsAccessor {
	@Accessor("bufferSource")
	public MultiBufferSource.BufferSource bufferSource();
}

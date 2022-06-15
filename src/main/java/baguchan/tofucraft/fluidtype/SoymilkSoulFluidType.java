package baguchan.tofucraft.fluidtype;

import baguchan.tofucraft.TofuCraftReload;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.IFluidTypeRenderProperties;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SoymilkSoulFluidType extends FluidType {
	public SoymilkSoulFluidType(Properties properties) {
		super(properties);
	}

	@Override
	public void initializeClient(Consumer<IFluidTypeRenderProperties> consumer) {
		consumer.accept(new IFluidTypeRenderProperties() {
			private static final ResourceLocation TEXTURE_STILL = new ResourceLocation(TofuCraftReload.MODID, "blocks/soymilk_soul_still");
			private static final ResourceLocation TEXTURE_FLOW = new ResourceLocation(TofuCraftReload.MODID, "blocks/soymilk_soul_flowing");
			private static final ResourceLocation TEXTURE_OVERLAY = new ResourceLocation(TofuCraftReload.MODID, "blocks/soymilk_soul_overlay");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getOverlayTexture() {
				return TEXTURE_OVERLAY;
			}
		});
	}
}

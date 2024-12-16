package baguchi.tofucraft.client;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.attachment.TofuLivingAttachment;
import baguchi.tofucraft.client.model.FukumameThrowerModel;
import baguchi.tofucraft.client.model.ShuDofuSpiderModel;
import baguchi.tofucraft.client.model.SoyBallModel;
import baguchi.tofucraft.client.model.TofuFishModel;
import baguchi.tofucraft.client.model.TofuGandlemModel;
import baguchi.tofucraft.client.model.TofuGolemModel;
import baguchi.tofucraft.client.model.TofuSpiderModel;
import baguchi.tofucraft.client.model.TofunianModel;
import baguchi.tofucraft.client.model.TravelerTofunianModel;
import baguchi.tofucraft.client.render.FukumameRender;
import baguchi.tofucraft.client.render.NattoBallRender;
import baguchi.tofucraft.client.render.NattoStringRender;
import baguchi.tofucraft.client.render.NetherFukumameRender;
import baguchi.tofucraft.client.render.SoulFukumameRender;
import baguchi.tofucraft.client.render.ZundaArrowRender;
import baguchi.tofucraft.client.render.blockentity.FoodPlateRender;
import baguchi.tofucraft.client.render.blockentity.TofuBedRenderer;
import baguchi.tofucraft.client.render.blockentity.TofuChestRenderer;
import baguchi.tofucraft.client.render.blockentity.TofunianStatueRender;
import baguchi.tofucraft.client.render.entity.FallingTofuRenderer;
import baguchi.tofucraft.client.render.entity.FukumameThrowerRenderer;
import baguchi.tofucraft.client.render.entity.ShuDofuSpiderRender;
import baguchi.tofucraft.client.render.entity.SoyballRenderer;
import baguchi.tofucraft.client.render.entity.TofuCowRender;
import baguchi.tofucraft.client.render.entity.TofuCreeperRender;
import baguchi.tofucraft.client.render.entity.TofuFishRender;
import baguchi.tofucraft.client.render.entity.TofuGandlemRender;
import baguchi.tofucraft.client.render.entity.TofuGolemRender;
import baguchi.tofucraft.client.render.entity.TofuPigRender;
import baguchi.tofucraft.client.render.entity.TofuSlimeRender;
import baguchi.tofucraft.client.render.entity.TofuSpiderRender;
import baguchi.tofucraft.client.render.entity.TofunianRender;
import baguchi.tofucraft.client.render.entity.TravelerTofunianRender;
import baguchi.tofucraft.client.render.entity.ZundamiteRender;
import baguchi.tofucraft.client.render.entity.effect.NattoCobWebRender;
import baguchi.tofucraft.client.render.special.TofuShieldSpecialRenderer;
import baguchi.tofucraft.client.render.special.TofunianStatueSpecialRenderer;
import baguchi.tofucraft.client.screen.ReceivingTofuLevelScreen;
import baguchi.tofucraft.client.screen.SaltFurnaceScreen;
import baguchi.tofucraft.client.screen.TFCrafterScreen;
import baguchi.tofucraft.client.screen.TFOvenScreen;
import baguchi.tofucraft.client.screen.TFStorageScreen;
import baguchi.tofucraft.registry.TofuAttachments;
import baguchi.tofucraft.registry.TofuBlockEntitys;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuDimensions;
import baguchi.tofucraft.registry.TofuEntityTypes;
import baguchi.tofucraft.registry.TofuFluidTypes;
import baguchi.tofucraft.registry.TofuMenus;
import baguchi.tofucraft.registry.TofuWoodTypes;
import baguchi.tofucraft.utils.ClientUtils;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.client.event.RegisterDimensionTransitionScreenEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = TofuCraftReload.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientRegistrar {
	private static final ResourceLocation TEXTURE_SOYHEARTS = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/gui/soy_hearts.png");

	public static void setup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			Sheets.addWoodType(TofuWoodTypes.LEEK);
			Sheets.addWoodType(TofuWoodTypes.LEEK_GREEN);
			Sheets.addWoodType(TofuWoodTypes.TOFU_STEM);
		});

	}

	@SubscribeEvent
	public static void registerDimensionTransitionScreens(RegisterDimensionTransitionScreenEvent event) {
		event.registerIncomingEffect(TofuDimensions.tofu_world, (supplier, reason) -> new ReceivingTofuLevelScreen(supplier));
		event.registerOutgoingEffect(TofuDimensions.tofu_world, (supplier, reason) -> new ReceivingTofuLevelScreen(supplier));
	}

	@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_flow");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/block/soymilk_overlay.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}

			@Override
			public void renderOverlay(Minecraft mc, PoseStack stack, MultiBufferSource buffers) {
				ClientUtils.renderOverlay(mc, stack, this);
			}
		}, TofuFluidTypes.SOYMILK.get());
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_hell");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_hell_flow");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/block/soymilk_hell_overlay.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}

			@Override
			public void renderOverlay(Minecraft mc, PoseStack stack, MultiBufferSource buffers) {
				ClientUtils.renderOverlay(mc, stack, this);
			}
		}, TofuFluidTypes.SOYMILK_HELL.get());
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_soul");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/soymilk_soul_flow");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/block/soymilk_soul_overlay.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}


			@Override
			public void renderOverlay(Minecraft mc, PoseStack stack, MultiBufferSource buffers) {
				ClientUtils.renderOverlay(mc, stack, this);
			}
		}, TofuFluidTypes.SOYMILK_SOUL.get());
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/bittern");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/bittern");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/block/bittern_overlay.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}

			@Override
			public void renderOverlay(Minecraft mc, PoseStack stack, MultiBufferSource buffers) {
				ClientUtils.renderOverlay(mc, stack, this);
			}
		}, TofuFluidTypes.BITTERN.get());

		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation TEXTURE_STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/doubanjiang");
			private static final ResourceLocation TEXTURE_FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/doubanjiang_flow");
			private static final ResourceLocation TEXTURE_OVERLAY = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "textures/block/doubanjiang_overlay.png");

			@Override
			public ResourceLocation getStillTexture() {
				return TEXTURE_STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return TEXTURE_FLOW;
			}

			@Override
			public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
				return TEXTURE_OVERLAY;
			}

			@Override
			public void renderOverlay(Minecraft mc, PoseStack stack, MultiBufferSource buffers) {
				ClientUtils.renderOverlay(mc, stack, this);
			}
		}, TofuFluidTypes.DOUBANJIANG.get());


		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/crimson"),
					FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/crimson");

			@Override
			public ResourceLocation getStillTexture() {
				return STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return FLOW;
			}
		}, TofuFluidTypes.CRIMSON.get());
		event.registerFluidType(new IClientFluidTypeExtensions() {
			private static final ResourceLocation STILL = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/warped"),
					FLOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "block/warped");

			@Override
			public ResourceLocation getStillTexture() {
				return STILL;
			}

			@Override
			public ResourceLocation getFlowingTexture() {
				return FLOW;
			}
		}, TofuFluidTypes.WARPED.get());
	}

	@SubscribeEvent
	public static void screenEvent(RegisterMenuScreensEvent event) {

		event.register(TofuMenus.SALT_FURNACE.get(), SaltFurnaceScreen::new);
		event.register(TofuMenus.TF_STORAGE.get(), TFStorageScreen::new);
		event.register(TofuMenus.TF_CRAFTER.get(), TFCrafterScreen::new);
		event.register(TofuMenus.TF_OVEN.get(), TFOvenScreen::new);
	}
	@SubscribeEvent
	public static void specialModelRender(RegisterSpecialModelRendererEvent event) {
		event.register(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_shield"), TofuShieldSpecialRenderer.Unbaked.MAP_CODEC);
		event.register(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofunian_statue"), TofunianStatueSpecialRenderer.Unbaked.MAP_CODEC);
	}


	@SubscribeEvent
	public static void registerColorBlock(RegisterColorHandlersEvent.Block event) {
		event.register((p_92621_, p_92622_, p_92623_, p_92624_) -> {
			return p_92622_ != null && p_92623_ != null ? BiomeColors.getAverageWaterColor(p_92622_, p_92623_) : -1;
		}, TofuBlocks.SALTPAN.get(), TofuBlocks.SPROUTSJAR.get());
	}

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(TofuEntityTypes.TOFUCOW.get(), TofuCowRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUPIG.get(), TofuPigRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUNIAN.get(), TofunianRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TRAVELER_TOFUNIAN.get(), TravelerTofunianRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUFISH.get(), TofuFishRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFU_GOLEM.get(), TofuGolemRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUSLIME.get(), TofuSlimeRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUCREEPER.get(), TofuCreeperRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFUSPIDER.get(), TofuSpiderRender::new);
		event.registerEntityRenderer(TofuEntityTypes.TOFU_GANDLEM.get(), TofuGandlemRender::new);
		event.registerEntityRenderer(TofuEntityTypes.SHUDOFUSPIDER.get(), ShuDofuSpiderRender::new);

		event.registerEntityRenderer(TofuEntityTypes.FUKUMAME.get(), FukumameRender::new);
		event.registerEntityRenderer(TofuEntityTypes.NETHER_FUKUMAME.get(), NetherFukumameRender::new);
		event.registerEntityRenderer(TofuEntityTypes.SOUL_FUKUMAME.get(), SoulFukumameRender::new);
		event.registerEntityRenderer(TofuEntityTypes.ZUNDA_ARROW.get(), ZundaArrowRender::new);
		event.registerEntityRenderer(TofuEntityTypes.SOYBALL.get(), SoyballRenderer::new);

		event.registerEntityRenderer(TofuEntityTypes.NATTO_STRNIG.get(), (context) -> new NattoStringRender<>(context, 1.0F, true));
		event.registerEntityRenderer(TofuEntityTypes.NATTO_COBWEB.get(), NattoCobWebRender::new);
		event.registerEntityRenderer(TofuEntityTypes.NATTO_BALL.get(), NattoBallRender::new);
		event.registerEntityRenderer(TofuEntityTypes.FALLING_TOFU.get(), FallingTofuRenderer::new);
		event.registerEntityRenderer(TofuEntityTypes.FUKUMAME_THROWER.get(), FukumameThrowerRenderer::new);
		event.registerEntityRenderer(TofuEntityTypes.ZUNDAMITE.get(), ZundamiteRender::new);
		event.registerEntityRenderer(TofuEntityTypes.LEEK_BOAT.get(), p_375462_ -> new BoatRenderer(p_375462_, TofuModelLayers.LEEK_BOAT));
		event.registerEntityRenderer(TofuEntityTypes.LEEK_GREEN_BOAT.get(), p_375462_ -> new BoatRenderer(p_375462_, TofuModelLayers.LEEK_GREEN_BOAT));
		event.registerEntityRenderer(TofuEntityTypes.TOFU_STEM_BOAT.get(), p_375462_ -> new BoatRenderer(p_375462_, TofuModelLayers.TOFU_STEM_BOAT));
		event.registerEntityRenderer(TofuEntityTypes.LEEK_CHEST_BOAT.get(), p_375462_ -> new BoatRenderer(p_375462_, TofuModelLayers.LEEK_CHEST_BOAT));
		event.registerEntityRenderer(TofuEntityTypes.LEEK_GREEN_CHEST_BOAT.get(), p_375462_ -> new BoatRenderer(p_375462_, TofuModelLayers.LEEK_GREEN_CHEST_BOAT));
		event.registerEntityRenderer(TofuEntityTypes.TOFU_STEM_CHEST_BOAT.get(), p_375462_ -> new BoatRenderer(p_375462_, TofuModelLayers.TOFU_STEM_CHEST_BOAT));


		event.registerBlockEntityRenderer(TofuBlockEntitys.TOFUBED.get(), TofuBedRenderer::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.TOFUCHEST.get(), TofuChestRenderer::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.FOODPLATE.get(), FoodPlateRender::new);
		event.registerBlockEntityRenderer(TofuBlockEntitys.TOFUNIAN_STATUE.get(), TofunianStatueRender::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(TofuModelLayers.TOFUNIAN, TofunianModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TRAVELER_TOFUNIAN, TravelerTofunianModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TOFUSPIDER, TofuSpiderModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TOFUFISH, TofuFishModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TOFU_GOLEM, TofuGolemModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TOFU_GANDLEM, TofuGandlemModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.SHUDOFUSPIDER, ShuDofuSpiderModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.FUKUMAME_THROWER, FukumameThrowerModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.SOYBALL, SoyBallModel::createBodyLayer);
		event.registerLayerDefinition(TofuModelLayers.TOFU_STEM_BOAT, BoatModel::createBoatModel);
		event.registerLayerDefinition(TofuModelLayers.LEEK_BOAT, BoatModel::createBoatModel);
		event.registerLayerDefinition(TofuModelLayers.LEEK_GREEN_BOAT, BoatModel::createBoatModel);
		event.registerLayerDefinition(TofuModelLayers.TOFU_STEM_CHEST_BOAT, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(TofuModelLayers.LEEK_CHEST_BOAT, BoatModel::createChestBoatModel);
		event.registerLayerDefinition(TofuModelLayers.LEEK_GREEN_CHEST_BOAT, BoatModel::createChestBoatModel);
	}

	@SubscribeEvent
	public static void registerOverlay(RegisterGuiLayersEvent event) {
		event.registerAboveAll(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_portal_overlay"), (guiGraphics, partialTicks) -> {
			Minecraft minecraft = Minecraft.getInstance();
			Window window = minecraft.getWindow();
			LocalPlayer player = minecraft.player;
			if (player != null) {
				renderTofuPortalOverlay(guiGraphics, minecraft, window, player.getData(TofuAttachments.TOFU_LIVING.get()), partialTicks);
			}
		});
	}

	private static void renderTofuPortalOverlay(GuiGraphics guiGraphics, Minecraft minecraft, Window window, TofuLivingAttachment handler, DeltaTracker partialTicks) {
		float timeInPortal = Mth.lerp(partialTicks.getGameTimeDeltaPartialTick(false), handler.getPrevPortalAnimTime(), handler.getPortalAnimTime());
		if (timeInPortal > 0.0F) {
			if (timeInPortal < 1.0F) {
				timeInPortal *= timeInPortal;
				timeInPortal *= timeInPortal;
				timeInPortal = timeInPortal * 0.8F + 0.2F;
			}

			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.enableBlend();
			TextureAtlasSprite textureatlassprite = minecraft.getBlockRenderer().getBlockModelShaper().getParticleIcon(TofuBlocks.TOFU_PORTAL.get().defaultBlockState());
			guiGraphics.blitSprite(RenderType::guiTexturedOverlay, textureatlassprite, 0, 0,
					guiGraphics.guiWidth(),
					guiGraphics.guiHeight(),
					0);
			RenderSystem.disableBlend();
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
		}
	}

	@SubscribeEvent
	public static void registerDimensionEffect(RegisterDimensionSpecialEffectsEvent event) {
		TofuDimensionEffects renderInfo = new TofuDimensionEffects();
		event.register(TofuCraftReload.prefix("renderer"), renderInfo);
	}
}

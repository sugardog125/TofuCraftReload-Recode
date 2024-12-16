package baguchi.tofucraft.client;

import baguchi.tofucraft.TofuCraftReload;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TofuModelLayers {
	public static final ModelLayerLocation TOFUNIAN = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofunian"), "main");
	public static final ModelLayerLocation TRAVELER_TOFUNIAN = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "traveler_tofunian"), "main");
	public static final ModelLayerLocation TOFUSPIDER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofuspider"), "main");
	public static final ModelLayerLocation TOFUFISH = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofufish"), "main");
	public static final ModelLayerLocation TOFU_GOLEM = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_golem"), "main");

	public static final ModelLayerLocation TOFU_GANDLEM = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_gandlem"), "main");

	public static final ModelLayerLocation SHUDOFUSPIDER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "shudofuspider"), "main");

	public static final ModelLayerLocation FUKUMAME_THROWER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "fukumame_thrower"), "main");

	public static final ModelLayerLocation SOYBALL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "soyball"), "main");

	public static final ModelLayerLocation LEEK_BOAT = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "leek_boat"), "main");
	public static final ModelLayerLocation LEEK_GREEN_BOAT = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "leek_green_boat"), "main");
	public static final ModelLayerLocation TOFU_STEM_BOAT = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_stem_boat"), "main");
	public static final ModelLayerLocation LEEK_CHEST_BOAT = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "leek_chest_boat"), "main");
	public static final ModelLayerLocation LEEK_GREEN_CHEST_BOAT = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "leek_green_chest_boat"), "main");
	public static final ModelLayerLocation TOFU_STEM_CHEST_BOAT = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_stem_chest_boat"), "main");
}

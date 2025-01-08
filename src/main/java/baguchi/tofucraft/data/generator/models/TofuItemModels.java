package baguchi.tofucraft.data.generator.models;

import baguchi.tofucraft.client.render.special.TofuShieldSpecialRenderer;
import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.RangeSelectItemModel;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class TofuItemModels extends ItemModelGenerators {
	public TofuItemModels(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
		super(itemModelOutput, modelOutput);
	}

	@Override
	public void run() {

		this.generateFlatItem(TofuItems.APRICOT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.APRICOTJERRY_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.APRICOTJERRY_BREAD.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.APRICOTSEED.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.KYONINSO.get(), ModelTemplates.FLAT_ITEM);

		//item
		this.generateFlatItem(TofuItems.TOFUKINU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUMOMEN.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUISHI.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUMETAL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUDIAMOND.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUDIAMOND_NUGGET.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFUHELL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUSOUL.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFUGRILLED.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUZUNDA.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUMISO.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUDRIED.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUFRIED.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUSMOKE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SHUDOFU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUSESAME.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUFRIED_POUCH.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUANNIN.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUSTRAWBERRY.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.AGEDASHI_TOFU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_STEAK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.OAGE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_MINCED.get(), ModelTemplates.FLAT_ITEM);


		this.generateFlatItem(TofuItems.BITTERN_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.CRIMSON_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.WARPED_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SHROOM_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SALT.get(), ModelTemplates.FLAT_ITEM);

		//this.generateFlatItem(TofuItems.SEEDS_SOYBEANS.get(), ModelTemplates.FLAT_ITEM);
		//this.generateFlatItem(TofuItems.SEEDS_SOYBEANS_NETHER.get(), ModelTemplates.FLAT_ITEM);
		//this.generateFlatItem(TofuItems.SEEDS_SOYBEANS_SOUL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SEEDS_SOYBEANS_PALE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SEEDS_SOYBEANS_PALE_GLOW.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYBEAN_PARCHED.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.KINAKO.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.EDAMAME.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.BOILED_EDAMAME.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.MINCEDPOTATO.get(), ModelTemplates.FLAT_ITEM);

		//this.generateFlatItem(TofuItems.SEEDS_CHILI.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.CHILI.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.DOUBANJIANG.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.MABODOFU.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.FUKUMENI.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.KOYADOFUSTEW.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.KOUJI_BASE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.KOUJI.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.MISO.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.BOTTLE_SOYSAUSE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.NANBAN.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.NANBANTOFU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.NATTO.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.NETHER_NATTO.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.STARCH.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.STARCH_RAW.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.FILTERCLOTH.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.GELATIN.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.GELATINRAW.get(), ModelTemplates.FLAT_ITEM);

		//this.generateFlatItem(TofuItems.LEEK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.RICE.get(), ModelTemplates.FLAT_ITEM);
		//this.generateFlatItem(TofuItems.SEEDS_RICE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SPROUTS.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.CHIKUWA.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_CHIKUWA.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.YUBA.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.SOYBALL.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.ZUNDA.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.ZUNDAMA.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.ZUNDARUBY.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFU_HAMBURG_RAW.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_HAMBURG.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.RAW_TOFU_FISH.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.COOKED_TOFU_FISH.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.MISODENGAKU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.MISO_CHEESE_DENGAKU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMEAT.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFUCOOKIE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TTTBURGER.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.MEAT_WRAPPED_YUBA.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYSTICK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.MISOSOUP.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.MOYASHIITAME.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.MOYASHIOHITASHI.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SALTYMELON.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.SOYMILK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_ANNIN.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_APPLE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_COCOA.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_FRUITS.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_HONEY.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_KINAKO.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_PUDDING.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_PUMPKIN.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_RAMUNE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_SAKURA.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_STRAWBERRY.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_TEA.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_HELL_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_SOUL_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_PALE_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMILK_PALE_GLOW_BOTTLE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.KINAKO_MANJU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.ZUNDA_MANJU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.NETHER_MANJU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOUL_MANJU.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.KINAKO_MOCHI.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.CRIMSON_SOUP.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.ZUNDA_MOCHI.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.PUDDING.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.PUDDING_SOYMILK.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.NIKUJAGA.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.ONIGIRI.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.ONIGIRI_SALT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.YAKIONIGIRI_MISO.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.YAKIONIGIRI_SHOYU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.RICE_BURGER.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.RICE_NATTO.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.RICE_NATTO_LEEK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.RICE_NETHER_NATTO.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.RICE_NETHER_NATTO_LEEK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.RICE_TOFU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.RICE_SOBORO_TOFU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.GOHEIMOCHI.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.INARI.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.OKARA.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.OKARASTICK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.OKARA_DONUT.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.SOBOROTOFUSAUTE.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.SOY_CHOCOLATE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUNIAN_SOY_CHOCOLATE.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.BUCKET_SOYMILK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.BUCKET_SOYMILK_NETHER.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.BUCKET_SOYMILK_SOUL.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.BUCKET_BITTERN.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.BUCKET_DOUBANJIANG.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFUFISH_BUCKET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUFISH_SOYMILK_BUCKET.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.GLASSBOWL.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFUSOMEN.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUSOMENBOWL_GLASS.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TASTYBEEFSTEW.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TASTYSTEW.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.HIYAYAKKO_GLASS.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.NATTOHIYAYAKKO_GLASS.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFUSCOOP.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUSTICK.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.ROLLINGPIN.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.BUGLE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.FUKUMAME.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.NETHER_FUKUMAME.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.INFERNO_NETHER_FUKUMAME.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOUL_FUKUMAME.get(), ModelTemplates.FLAT_ITEM);
		generateZundaBow(this, TofuItems.ZUNDA_BOW.get());
		this.generateFlatItem(TofuItems.ZUNDA_ARROW.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.ZUNDAMUSHROOM_ON_A_STICK.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.KINAKO_BREAD.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.EDAMAME_TEMPLA.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.NEGIMA.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOY_KARAAGE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYMEATDON.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFUGEM.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.ADVANCE_TOFUGEM.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TF_COIL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TF_CIRCUIT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TF_CAPACITOR.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TF_OSCILLATOR.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_CORE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TF_BATTERY.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFU_KINU_SWORD.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_KINU_PICKAXE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_KINU_AXE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_KINU_SHOVEL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_KINU_HOE.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFU_MOMEN_SWORD.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_MOMEN_PICKAXE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_MOMEN_AXE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_MOMEN_SHOVEL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_MOMEN_HOE.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFU_SOLID_SWORD.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_SOLID_PICKAXE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_SOLID_AXE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_SOLID_SHOVEL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_SOLID_HOE.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFU_METAL_SWORD.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_METAL_PICKAXE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_METAL_AXE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_METAL_SHOVEL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_METAL_HOE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_METAL_SHEARS.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFU_DIAMOND_SWORD.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_DIAMOND_PICKAXE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_DIAMOND_AXE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_DIAMOND_SHOVEL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_DIAMOND_HOE.get(), ModelTemplates.FLAT_ITEM);
		generateTofuShield(this, TofuItems.TOFU_SHIELD.get());


		this.generateFlatItem(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.ZUNDA_UPGRADE_SMITHING_TEMPLATE.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFU_KINU_HELMET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_KINU_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_KINU_LEGGINGS.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_KINU_BOOTS.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFU_MOMEN_HELMET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_MOMEN_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_MOMEN_LEGGINGS.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_MOMEN_BOOTS.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.ARMOR_TOFU_SOLIDHELMET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.ARMOR_TOFU_SOLIDCHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.ARMOR_TOFU_SOLIDLEGGINGS.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.ARMOR_TOFU_SOLIDBOOTS.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFU_METAL_HELMET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_METAL_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_METAL_LEGGINGS.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_METAL_BOOTS.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFU_DIAMOND_HELMET.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_DIAMOND_CHESTPLATE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_DIAMOND_LEGGINGS.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_DIAMOND_BOOTS.get(), ModelTemplates.FLAT_ITEM);

		this.generateSpawnEgg(TofuItems.TOFUNIAN_SPAWNEGG.get(), 15460584, 13291425);
		this.generateSpawnEgg(TofuItems.TRAVELER_TOFUNIAN_SPAWNEGG.get(), 15460584, 8763986);
		this.generateSpawnEgg(TofuItems.TOFU_GANDLEM_SPAWNEGG.get(), 15526085, 2332114);
		this.generateSpawnEgg(TofuItems.TOFU_GOLEM_SPAWNEGG.get(), 15526085, 14935011);
		this.generateSpawnEgg(TofuItems.SHUDOFUSPIDER_SPAWNEGG.get(), 15526085, 6198590);
		this.generateSpawnEgg(TofuItems.TOFUCOW_SPAWNEGG.get(), 15460584, 10724259);
		this.generateSpawnEgg(TofuItems.TOFUPIG_SPAWNEGG.get(), 15460584, 10066329);
		this.generateSpawnEgg(TofuItems.TOFUSLIME_SPAWNEGG.get(), 15460584, 3026478);
		this.generateSpawnEgg(TofuItems.TOFUCREEPER_SPAWNEGG.get(), 15460584, 3026478);
		this.generateSpawnEgg(TofuItems.TOFUSPIDER_SPAWNEGG.get(), 15460584, 3026478);
		this.generateSpawnEgg(TofuItems.TOFUFISH_SPAWNEGG.get(), 15460584, 3026478);
		this.generateSpawnEgg(TofuItems.FUKUMAME_THROWER_SPAWNEGG.get(), -6725824, -396380);
		this.generateSpawnEgg(TofuItems.ZUNDAMITE_SPAWNEGG.get(), 12770119, 13551297);

		this.generateFlatItem(TofuItems.TOMATO_SOYBEAN_STEW.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.YUDOFU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.EDAMAME_RICE.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.BOTTLE_DASHI.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.BOTTLE_SOYOIL.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFUEGG.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOYSAUSE_RAMEN.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.HELL_MABOU.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.RED_SOUP.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.HELL_RED_SOUP.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SUKIYAKI.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_BUNS_BURGER.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.STEAMED_BREAD.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.STEAMED_BREAD_COCOA.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.SOY_CHEESE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOY_NETHER_CHEESE.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.SOY_SOUL_CHEESE.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.NATTO_COBWEB.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.TOFUNIAN_BANNER_PATTERN.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_STEM_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.LEEK_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.LEEK_GREEN_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.TOFU_STEM_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.LEEK_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);
		this.generateFlatItem(TofuItems.LEEK_GREEN_CHEST_BOAT.get(), ModelTemplates.FLAT_ITEM);

		this.generateFlatItem(TofuItems.MUSIC_DISC_GREEN_BRANCH.get(), ModelTemplates.FLAT_ITEM);
	}

	public void generateTofuShield(ItemModelGenerators generators, Item p_386530_) {
		ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(p_386530_), new TofuShieldSpecialRenderer.Unbaked());
		ItemModel.Unbaked itemmodel$unbaked1 = ItemModelUtils.specialModel(
				ModelLocationUtils.getModelLocation(p_386530_, "_blocking"), new TofuShieldSpecialRenderer.Unbaked()
		);
		generators.generateBooleanDispatch(p_386530_, ItemModelUtils.isUsingItem(), itemmodel$unbaked1, itemmodel$unbaked);
	}

	public void generateZundaBow(ItemModelGenerators generators, Item p_387215_) {
		ItemModel.Unbaked itemmodel$unbaked = ItemModelUtils.plainModel(generators.createFlatItemModel(p_387215_, ModelTemplates.BOW));
		ItemModel.Unbaked itemmodel$unbaked1 = ItemModelUtils.plainModel(generators.createFlatItemModel(p_387215_, "_pulling_0", ModelTemplates.BOW));
		ItemModel.Unbaked itemmodel$unbaked2 = ItemModelUtils.plainModel(generators.createFlatItemModel(p_387215_, "_pulling_1", ModelTemplates.BOW));
		ItemModel.Unbaked itemmodel$unbaked3 = ItemModelUtils.plainModel(generators.createFlatItemModel(p_387215_, "_pulling_2", ModelTemplates.BOW));
		generators.itemModelOutput.accept(p_387215_, ItemModelUtils.conditional(ItemModelUtils.isUsingItem(), ItemModelUtils.rangeSelect(new UseDuration(false), 0.05F, itemmodel$unbaked1, new RangeSelectItemModel.Entry[]{ItemModelUtils.override(itemmodel$unbaked2, 0.65F), ItemModelUtils.override(itemmodel$unbaked3, 0.9F)}), itemmodel$unbaked));
	}
}
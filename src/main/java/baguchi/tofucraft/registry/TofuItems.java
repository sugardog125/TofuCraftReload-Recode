package baguchi.tofucraft.registry;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.data.generator.CustomTagGenerator;
import baguchi.tofucraft.dispenser.DamageableProjectileDispenseBehavior;
import baguchi.tofucraft.item.ApricotItem;
import baguchi.tofucraft.item.BugleItem;
import baguchi.tofucraft.item.ChiliItem;
import baguchi.tofucraft.item.DishItem;
import baguchi.tofucraft.item.DoubleUsageSeedItem;
import baguchi.tofucraft.item.FukumameItem;
import baguchi.tofucraft.item.GlassBowlItem;
import baguchi.tofucraft.item.HoneySoymilkBottleItem;
import baguchi.tofucraft.item.InfernoNetherFukumameItem;
import baguchi.tofucraft.item.KoujiBaseItem;
import baguchi.tofucraft.item.NattoCobWebItem;
import baguchi.tofucraft.item.NetherFukumameItem;
import baguchi.tofucraft.item.NourishmentItem;
import baguchi.tofucraft.item.RamuneSoymilkBottleItem;
import baguchi.tofucraft.item.ReflectTofuShieldItem;
import baguchi.tofucraft.item.RollingPinItem;
import baguchi.tofucraft.item.SaltFoodItem;
import baguchi.tofucraft.item.SeedAndRootItem;
import baguchi.tofucraft.item.SoulFukumameItem;
import baguchi.tofucraft.item.SoyBallItem;
import baguchi.tofucraft.item.SoymilkBottleItem;
import baguchi.tofucraft.item.SpecialBitternItem;
import baguchi.tofucraft.item.TFBatteryItem;
import baguchi.tofucraft.item.armor.BreakableTofuArmorItem;
import baguchi.tofucraft.item.armor.BreakableTofuBootsItem;
import baguchi.tofucraft.item.armor.TofuArmorItem;
import baguchi.tofucraft.item.block.DeferredBlockItem;
import baguchi.tofucraft.item.tool.TofuAxeItem;
import baguchi.tofucraft.item.tool.TofuHoeItem;
import baguchi.tofucraft.item.tool.TofuPickaxeItem;
import baguchi.tofucraft.item.tool.TofuScoopItem;
import baguchi.tofucraft.item.tool.TofuShearsItem;
import baguchi.tofucraft.item.tool.TofuShieldItem;
import baguchi.tofucraft.item.tool.TofuShovelItem;
import baguchi.tofucraft.item.tool.TofuStickItem;
import baguchi.tofucraft.item.tool.TofuSwordItem;
import baguchi.tofucraft.item.tool.ZundaArrowItem;
import baguchi.tofucraft.item.tool.ZundaBowItem;
import baguchi.tofucraft.item.tool.ZundaMushroomOnAStickItem;
import baguchi.tofucraft.utils.RecipeHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class TofuItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TofuCraftReload.MODID);

	public static final DeferredHolder<Item, Item> TOFUKINU = ITEMS.registerItem("tofukinu", (properties) -> new Item((properties).food(TofuFoods.TOFU)));
	public static final DeferredHolder<Item, Item> TOFUMOMEN = ITEMS.registerItem("tofumomen", (properties) -> new Item((properties).food(TofuFoods.TOFU)));
	public static final DeferredHolder<Item, Item> TOFUISHI = ITEMS.registerItem("tofuishi", (properties) -> new Item((properties).food(TofuFoods.ISHITOFU)));
	public static final DeferredHolder<Item, Item> TOFUMETAL = ITEMS.registerItem("tofumetal", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> TOFUDIAMOND = ITEMS.registerItem("tofudiamond", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> TOFUDIAMOND_NUGGET = ITEMS.registerItem("tofudiamondnugget", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> TOFUHELL = ITEMS.registerItem("tofuhell", (properties) -> new Item((properties).food(TofuFoods.TOFUHELL, TofuConsumables.HELL_FOOD)));
	public static final DeferredHolder<Item, Item> TOFUSOUL = ITEMS.registerItem("tofusoul", (properties) -> new Item((properties).food(TofuFoods.TOFUSOUL, TofuConsumables.SOUL_FOOD)));
	public static final DeferredHolder<Item, Item> TOFUGRILLED = ITEMS.registerItem("tofugrilled", (properties) -> new Item((properties).food(TofuFoods.TOFUGRILLED)));
	public static final DeferredHolder<Item, Item> TOFUZUNDA = ITEMS.registerItem("tofuzunda", (properties) -> new Item((properties).food(TofuFoods.TOFUZUNDA)));
	public static final DeferredHolder<Item, Item> TOFUMISO = ITEMS.registerItem("tofumiso", (properties) -> new Item((properties).food(TofuFoods.TOFUMISO)));
	public static final DeferredHolder<Item, Item> TOFUDRIED = ITEMS.registerItem("tofudried", (properties) -> new Item((properties).food(TofuFoods.TOFUDRIED)));
	public static final DeferredHolder<Item, Item> TOFUSMOKE = ITEMS.registerItem("smoketofu", (properties) -> new Item((properties).food(TofuFoods.TOFUSMOKE)));
	public static final DeferredHolder<Item, Item> SHUDOFU = ITEMS.registerItem("shudofu", (properties) -> new Item((properties).food(TofuFoods.SHUDOFU, TofuConsumables.COUGH)));

	public static final DeferredHolder<Item, Item> TOFUSESAME = ITEMS.registerItem("tofusesame", (properties) -> new Item((properties).food(TofuFoods.TOFUSESAME)));
	public static final DeferredHolder<Item, Item> TOFUFRIED = ITEMS.registerItem("tofufried", (properties) -> new Item((properties).food(TofuFoods.TOFUFRIED)));
	public static final DeferredHolder<Item, Item> TOFUFRIED_POUCH = ITEMS.registerItem("tofufried_pouch", (properties) -> new Item((properties).food(TofuFoods.TOFUFRIED_POUCH)));
	public static final DeferredHolder<Item, Item> TOFUEGG = ITEMS.registerItem("tofuegg", (properties) -> new Item((properties).food(TofuFoods.TOFU_EGG)));


	public static final DeferredHolder<Item, Item> TOFUANNIN = ITEMS.registerItem("tofuannin", (properties) -> new Item((properties).food(TofuFoods.TOFUANNIN)));
	public static final DeferredHolder<Item, Item> TOFUSTRAWBERRY = ITEMS.registerItem("tofustrawberry", (properties) -> new Item((properties).food(TofuFoods.TOFUSTRAWBERRY)));


	public static final DeferredHolder<Item, Item> TOFU_MINCED = ITEMS.registerItem("tofuminced", (properties) -> new Item((properties).food(TofuFoods.TOFU)));


	public static final DeferredHolder<Item, Item> BITTERN_BOTTLE = ITEMS.registerItem("bittern_bottle", (properties) -> new Item((properties).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> CRIMSON_BOTTLE = ITEMS.registerItem("crimson_fluid_bottle", (properties) -> new Item((properties).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> WARPED_BOTTLE = ITEMS.registerItem("warped_fluid_bottle", (properties) -> new Item((properties).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SHROOM_BOTTLE = ITEMS.registerItem("shroom_bottle", (properties) -> new Item((properties).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SALT = ITEMS.registerItem("salt", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> SEEDS_SOYBEANS = ITEMS.registerItem("seeds_soybeans", (properties) -> new DoubleUsageSeedItem(TofuBlocks.SOYBEAN, TofuBlocks.SPROUTS, (properties)));
	public static final DeferredHolder<Item, Item> SEEDS_SOYBEANS_NETHER = ITEMS.registerItem("seeds_soybeans_nether", (properties) -> new DeferredBlockItem(TofuBlocks.SOYBEAN_NETHER, (properties)));
	public static final DeferredHolder<Item, Item> SEEDS_SOYBEANS_SOUL = ITEMS.registerItem("seeds_soybeans_soul", (properties) -> new DeferredBlockItem(TofuBlocks.SOYBEAN_SOUL, (properties).rarity(Rarity.UNCOMMON)));
	public static final DeferredHolder<Item, Item> SOYBEAN_PARCHED = ITEMS.registerItem("soybeans_parched", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> KINAKO = ITEMS.registerItem("kinako", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> EDAMAME = ITEMS.registerItem("edamame", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> BOILED_EDAMAME = ITEMS.registerItem("edamame_boild", (properties) -> new Item((properties).food(TofuFoods.BOILED_EDAMAME)));
	public static final DeferredHolder<Item, Item> MINCEDPOTATO = ITEMS.registerItem("mincedpotato", (properties) -> new Item((properties)));

	public static final DeferredHolder<Item, Item> SEEDS_CHILI = ITEMS.registerItem("seeds_chili", (properties) -> new DeferredBlockItem(TofuBlocks.CHILI_CROP, (properties)));
	public static final DeferredHolder<Item, Item> CHILI = ITEMS.registerItem("chili", (properties) -> new ChiliItem((properties).food(TofuFoods.CHILI, TofuConsumables.COUGH)));
	public static final DeferredHolder<Item, Item> DOUBANJIANG = ITEMS.registerItem("doubanjiang", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> MABODOFU = ITEMS.registerItem("mabodofu", (properties) -> new DishItem((properties).food(TofuFoods.MABODOFU, TofuConsumables.CHILI_FOOD).stacksTo(16)));

	public static final DeferredHolder<Item, Item> FUKUMENI = ITEMS.registerItem("fukumeni", (properties) -> new Item((properties).food(TofuFoods.FUKUMENI)));
	public static final DeferredHolder<Item, Item> KOYADOFUSTEW = ITEMS.registerItem("koyadofustew", (properties) -> new DishItem((properties).food(TofuFoods.KOYADOFUSTEW).stacksTo(16)));

	public static final DeferredHolder<Item, Item> KOUJI_BASE = ITEMS.registerItem("koujibase", (properties) -> new KoujiBaseItem((properties).stacksTo(1)));
	public static final DeferredHolder<Item, Item> KOUJI = ITEMS.registerItem("kouji", (properties) -> new Item((properties)));

	public static final DeferredHolder<Item, Item> MISO = ITEMS.registerItem("miso", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> BOTTLE_SOYSAUSE = ITEMS.registerItem("bottle_soysause", (properties) -> new Item((properties).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
	public static final DeferredHolder<Item, Item> NANBAN = ITEMS.registerItem("nanban", (properties) -> new DishItem((properties).food(TofuFoods.NANBAN, TofuConsumables.CHILI_FOOD).stacksTo(16)));
	public static final DeferredHolder<Item, Item> NANBANTOFU = ITEMS.registerItem("nanbantofu", (properties) -> new DishItem((properties).food(TofuFoods.NANBANTOFU, TofuConsumables.CHILI_FOOD).stacksTo(16)));

	public static final DeferredHolder<Item, Item> SOY_CHEESE = ITEMS.registerItem("soy_cheese", (properties) -> new Item((properties).food(TofuFoods.SOY_CHEESE)));
	public static final DeferredHolder<Item, Item> SOY_NETHER_CHEESE = ITEMS.registerItem("soy_nether_cheese", (properties) -> new Item((properties).food(TofuFoods.SOY_NETHER_CHEESE, TofuConsumables.HELL_FOOD)));
	public static final DeferredHolder<Item, Item> SOY_SOUL_CHEESE = ITEMS.registerItem("soy_soul_cheese", (properties) -> new Item((properties).food(TofuFoods.SOY_SOUL_CHEESE, TofuConsumables.SOUL_FOOD)));

	public static final DeferredHolder<Item, Item> YUDOFU = ITEMS.registerItem("yudofu", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.YUDOFU)));
	public static final DeferredHolder<Item, Item> EDAMAME_RICE = ITEMS.registerItem("edamame_rice", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.EDAMAME_RICE)));
	public static final DeferredHolder<Item, Item> AGEDASHI_TOFU = ITEMS.registerItem("agedashi_tofu", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.AGEDASHI_TOFU)));
	public static final DeferredHolder<Item, Item> TOFU_STEAK = ITEMS.registerItem("tofusteak", (properties) -> new Item((properties).food(TofuFoods.TOFUSTEAK)));
	public static final DeferredHolder<Item, Item> OAGE = ITEMS.registerItem("oage", (properties) -> new Item((properties).food(TofuFoods.OAGE)));


	public static final DeferredHolder<Item, Item> NATTO = ITEMS.registerItem("natto", (properties) -> new Item((properties).food(TofuFoods.NATTO)));
	public static final DeferredHolder<Item, Item> NETHER_NATTO = ITEMS.registerItem("nether_natto", (properties) -> new Item((properties).food(TofuFoods.NETHER_NATTO)));

	public static final DeferredHolder<Item, Item> STARCH = ITEMS.registerItem("starch", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> STARCH_RAW = ITEMS.registerItem("starch_raw", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> FILTERCLOTH = ITEMS.registerItem("filtercloth", (properties) -> new Item((properties)));

	public static final DeferredHolder<Item, Item> GELATIN = ITEMS.registerItem("gelatin", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> GELATINRAW = ITEMS.registerItem("gelatinraw", (properties) -> new Item((properties)));

	public static final DeferredHolder<Item, Item> LEEK = ITEMS.registerItem("leek", (properties) -> new DeferredBlockItem(TofuBlocks.LEEK_CROP, (properties)));
	public static final DeferredHolder<Item, Item> RICE = ITEMS.registerItem("rice", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> SEEDS_RICE = ITEMS.registerItem("seeds_rice", (properties) -> new SeedAndRootItem(TofuBlocks.RICE_CROP.get(), TofuBlocks.RICE_ROOT.get(), (properties)));
	public static final DeferredHolder<Item, Item> SPROUTS = ITEMS.registerItem("sprouts", (properties) -> new Item(properties.food(TofuFoods.SPROUTS)));

	public static final DeferredHolder<Item, Item> CHIKUWA = ITEMS.registerItem("chikuwa", (properties) -> new Item((properties).food(TofuFoods.CHIKUWA)));
	public static final DeferredHolder<Item, Item> TOFU_CHIKUWA = ITEMS.registerItem("tofu_chikuwa", (properties) -> new Item((properties).food(TofuFoods.TOFU_CHIKUWA)));

	public static final DeferredHolder<Item, Item> APRICOT = ITEMS.registerItem("apricot", (properties) -> new ApricotItem((properties).food(TofuFoods.APRICOT)));
	public static final DeferredHolder<Item, Item> APRICOTJERRY_BOTTLE = ITEMS.registerItem("apricotjerry_bottle", (properties) -> new Item((properties.stacksTo(16))));
	public static final DeferredHolder<Item, Item> APRICOTJERRY_BREAD = ITEMS.registerItem("apricotjerry_bread", (properties) -> new Item((properties).food(TofuFoods.APRICOT_BREAD)));
	public static final DeferredHolder<Item, Item> APRICOTSEED = ITEMS.registerItem("apricotseed", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> KYONINSO = ITEMS.registerItem("kyoninso", (properties) -> new Item((properties)));

	public static final DeferredHolder<Item, Item> YUBA = ITEMS.registerItem("yuba", (properties) -> new Item((properties).food(TofuFoods.YUBA)));
	public static final DeferredHolder<Item, Item> SOYBALL = ITEMS.registerItem("soyball", (properties) -> new SoyBallItem((properties)));
	public static final DeferredHolder<Item, Item> ZUNDA = ITEMS.registerItem("zunda", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> ZUNDAMA = ITEMS.registerItem("zundama", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> ZUNDARUBY = ITEMS.registerItem("zundaruby", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> TOFU_HAMBURG_RAW = ITEMS.registerItem("tofuhamburg_raw", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> TOFU_HAMBURG = ITEMS.registerItem("tofuhamburg", (properties) -> new Item((properties).food(TofuFoods.TOFU_HAMBURG)));
	public static final DeferredHolder<Item, Item> RAW_TOFU_FISH = ITEMS.registerItem("raw_tofufish", (properties) -> new Item((properties).food(TofuFoods.RAW_TOFUFISH)));
	public static final DeferredHolder<Item, Item> COOKED_TOFU_FISH = ITEMS.registerItem("cooked_tofufish", (properties) -> new Item((properties).food(TofuFoods.COOKED_TOFUFISH)));
	public static final DeferredHolder<Item, Item> MISODENGAKU = ITEMS.registerItem("misodengaku", (properties) -> new DishItem((properties).food(TofuFoods.MISODENGAKU, TofuConsumables.MISO_FOOD)));
	public static final DeferredHolder<Item, Item> MISO_CHEESE_DENGAKU = ITEMS.registerItem("miso_cheese_dengaku", (properties) -> new DishItem((properties).food(TofuFoods.MISO_CHEESE_DENGAKU, TofuConsumables.MISO_FOOD)));
	public static final DeferredHolder<Item, Item> TOFUCOOKIE = ITEMS.registerItem("tofucookie", (properties) -> new Item((properties).food(TofuFoods.TOFUCOOKIE)));
	public static final DeferredHolder<Item, Item> TTTBURGER = ITEMS.registerItem("tttburger", (properties) -> new Item((properties).food(TofuFoods.TTTBURGER)));
	public static final DeferredHolder<Item, Item> MEAT_WRAPPED_YUBA = ITEMS.registerItem("meatwrapped_yuba", (properties) -> new Item((properties).food(TofuFoods.MEAT_WRAPPED_YUBA)));
	public static final DeferredHolder<Item, Item> SOYMEAT = ITEMS.registerItem("soymeat", (properties) -> new Item((properties).food(TofuFoods.SOYMEAT)));

	public static final DeferredHolder<Item, Item> SOYSTICK = ITEMS.registerItem("soystick", (properties) -> new Item((properties).food(TofuFoods.SOYSTICK)));
	public static final DeferredHolder<Item, Item> MISOSOUP = ITEMS.registerItem("misosoup", (properties) -> new DishItem((properties).food(TofuFoods.MISOSOUP, TofuConsumables.MISO_FOOD).stacksTo(16)));
	public static final DeferredHolder<Item, Item> MOYASHIITAME = ITEMS.registerItem("moyashiitame", (properties) -> new DishItem((properties).food(TofuFoods.MOYASHIITAME).stacksTo(16)));
	public static final DeferredHolder<Item, Item> MOYASHIOHITASHI = ITEMS.registerItem("moyashiohitashi", (properties) -> new DishItem((properties).food(TofuFoods.MOYASHIOHITASHI).stacksTo(16)));
	public static final DeferredHolder<Item, Item> SALTYMELON = ITEMS.registerItem("saltymelon", (properties) -> new SaltFoodItem((properties).food(TofuFoods.SALTYMELON, TofuConsumables.SMALL_SALT_FOOD)));

	public static final DeferredHolder<Item, Item> SOYMILK = ITEMS.registerItem("soymilk", (properties) -> new SoymilkBottleItem(MobEffects.REGENERATION, MobEffects.HEALTH_BOOST, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_ANNIN = ITEMS.registerItem("soymilk_annin", (properties) -> new SoymilkBottleItem(MobEffects.HEALTH_BOOST, MobEffects.ABSORPTION, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_APPLE = ITEMS.registerItem("soymilk_apple", (properties) -> new SoymilkBottleItem(MobEffects.DAMAGE_RESISTANCE, MobEffects.ABSORPTION, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_COCOA = ITEMS.registerItem("soymilk_cocoa", (properties) -> new SoymilkBottleItem(MobEffects.JUMP, MobEffects.MOVEMENT_SPEED, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_FRUITS = ITEMS.registerItem("soymilk_fruits", (properties) -> new SoymilkBottleItem(MobEffects.SLOW_FALLING, MobEffects.JUMP, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_HONEY = ITEMS.registerItem("soymilk_honey", (properties) -> new HoneySoymilkBottleItem((properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_KINAKO = ITEMS.registerItem("soymilk_kinako", (properties) -> new SoymilkBottleItem(MobEffects.MOVEMENT_SPEED, MobEffects.DIG_SPEED, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_PUDDING = ITEMS.registerItem("soymilk_pudding", (properties) -> new SoymilkBottleItem(MobEffects.REGENERATION, MobEffects.HEALTH_BOOST, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_PUMPKIN = ITEMS.registerItem("soymilk_pumpkin", (properties) -> new SoymilkBottleItem(MobEffects.DAMAGE_BOOST, MobEffects.DIG_SPEED, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_RAMUNE = ITEMS.registerItem("soymilk_ramune", (properties) -> new RamuneSoymilkBottleItem((properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_SAKURA = ITEMS.registerItem("soymilk_sakura", (properties) -> new SoymilkBottleItem(MobEffects.DAMAGE_RESISTANCE, MobEffects.FIRE_RESISTANCE, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_STRAWBERRY = ITEMS.registerItem("soymilk_strawberry", (properties) -> new SoymilkBottleItem(MobEffects.DIG_SPEED, MobEffects.MOVEMENT_SPEED, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_TEA = ITEMS.registerItem("soymilk_tea", (properties) -> new SoymilkBottleItem(MobEffects.LUCK, MobEffects.WATER_BREATHING, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_HELL_BOTTLE = ITEMS.registerItem("soymilk_hell_bottle", (properties) -> new SoymilkBottleItem(MobEffects.FIRE_RESISTANCE, MobEffects.DAMAGE_RESISTANCE, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYMILK_SOUL_BOTTLE = ITEMS.registerItem("soymilk_soul_bottle", (properties) -> new SoymilkBottleItem(MobEffects.ABSORPTION, MobEffects.HEALTH_BOOST, (properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));


	public static final DeferredHolder<Item, Item> KINAKO_MANJU = ITEMS.registerItem("kinakomanju", (properties) -> new Item((properties).food(TofuFoods.KINAKO_MANJU)));
	public static final DeferredHolder<Item, Item> ZUNDA_MANJU = ITEMS.registerItem("zundamanju", (properties) -> new Item((properties).food(TofuFoods.ZUNDA_MANJU, TofuConsumables.SOY_FOOD)));
	public static final DeferredHolder<Item, Item> NETHER_MANJU = ITEMS.registerItem("nethermanju", (properties) -> new Item((properties).food(TofuFoods.NETHER_MANJU, TofuConsumables.HELL_FOOD)));
	public static final DeferredHolder<Item, Item> SOUL_MANJU = ITEMS.registerItem("soulmanju", (properties) -> new Item((properties).food(TofuFoods.SOUL_MANJU, TofuConsumables.SOUL_FOOD)));
	public static final DeferredHolder<Item, Item> ZUNDA_MOCHI = ITEMS.registerItem("zunda_mochi", (properties) -> new Item((properties).food(TofuFoods.ZUNDA_MOCHI, TofuConsumables.SOY_FOOD)));
	public static final DeferredHolder<Item, Item> KINAKO_MOCHI = ITEMS.registerItem("kinako_mochi", (properties) -> new Item((properties).food(TofuFoods.KINAKO_MOCHI)));
	public static final DeferredHolder<Item, Item> CRIMSON_SOUP = ITEMS.registerItem("crimson_soup", (properties) -> new DishItem((properties).food(TofuFoods.CRIMSON_SOUP, TofuConsumables.CRIMSON_SOUP).stacksTo(16)));


	public static final DeferredHolder<Item, Item> ONIGIRI = ITEMS.registerItem("onigiri", (properties) -> new Item((properties).food(TofuFoods.ONIGIRI)));
	public static final DeferredHolder<Item, Item> ONIGIRI_SALT = ITEMS.registerItem("onigiri_salt", (properties) -> new SaltFoodItem((properties).food(TofuFoods.ONIGIRI_SALT)));

	public static final DeferredHolder<Item, Item> INARI = ITEMS.registerItem("inari", (properties) -> new Item((properties).food(TofuFoods.INARI)));

	public static final DeferredHolder<Item, Item> OKARA = ITEMS.registerItem("okara", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> OKARASTICK = ITEMS.registerItem("okarastick", (properties) -> new Item((properties).food(TofuFoods.OKARASTICK)));
	public static final DeferredHolder<Item, Item> OKARA_DONUT = ITEMS.registerItem("okara_donut", (properties) -> new Item((properties).food(TofuFoods.OKARA_DONUT)));

	public static final DeferredHolder<Item, Item> SOBOROTOFUSAUTE = ITEMS.registerItem("soborotofusaute", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.SOBOROTOFUSAUTE)));

	public static final DeferredHolder<Item, Item> YAKIONIGIRI_MISO = ITEMS.registerItem("yakionigiri_miso", (properties) -> new Item((properties).food(TofuFoods.YAKIONIGIRI_MISO, TofuConsumables.MISO_FOOD)));
	public static final DeferredHolder<Item, Item> YAKIONIGIRI_SHOYU = ITEMS.registerItem("yakionigiri_shoyu", (properties) -> new Item((properties).food(TofuFoods.YAKIONIGIRI_SHOYU)));
	public static final DeferredHolder<Item, Item> RICE_BURGER = ITEMS.registerItem("riceburger", (properties) -> new Item((properties).food(TofuFoods.RICE_BURGER)));
	public static final DeferredHolder<Item, Item> RICE_NATTO = ITEMS.registerItem("ricenatto", (properties) -> new NourishmentItem((properties).food(TofuFoods.RICE_NATTO)));
	public static final DeferredHolder<Item, Item> RICE_NATTO_LEEK = ITEMS.registerItem("ricenattoleek", (properties) -> new NourishmentItem((properties).food(TofuFoods.RICE_NATTOLEEK)));
	public static final DeferredHolder<Item, Item> RICE_NETHER_NATTO = ITEMS.registerItem("rice_nether_natto", (properties) -> new NourishmentItem((properties).food(TofuFoods.RICE_NETHER_NATTO, TofuConsumables.HELL_FOOD)));
	public static final DeferredHolder<Item, Item> RICE_NETHER_NATTO_LEEK = ITEMS.registerItem("rice_nether_natto_leek", (properties) -> new NourishmentItem((properties).food(TofuFoods.RICE_NETHER_NATTO_LEEK, TofuConsumables.HELL_FOOD)));

	public static final DeferredHolder<Item, Item> RICE_TOFU = ITEMS.registerItem("ricetofu", (properties) -> new Item((properties).food(TofuFoods.RICE_TOFU)));
	public static final DeferredHolder<Item, Item> RICE_SOBORO_TOFU = ITEMS.registerItem("ricesoborotofu", (properties) -> new Item((properties).food(TofuFoods.RICE_SOBORO_TOFU)));
	public static final DeferredHolder<Item, Item> GOHEIMOCHI = ITEMS.registerItem("goheimochi", (properties) -> new DishItem((properties).food(TofuFoods.GOHEIMOCHI, TofuConsumables.MISO_FOOD)));


	public static final DeferredHolder<Item, Item> SOY_CHOCOLATE = ITEMS.registerItem("soy_chocolate", (properties) -> new Item((properties).food(TofuFoods.SOY_CHOCOLATE)));
	public static final DeferredHolder<Item, Item> TOFUNIAN_SOY_CHOCOLATE = ITEMS.registerItem("tofunian_soy_chocolate", (properties) -> new Item((properties).food(TofuFoods.SOY_CHOCOLATE)));

	public static final DeferredHolder<Item, Item> BUCKET_SOYMILK = ITEMS.registerItem("bucket_soymilk", (properties) -> new BucketItem(TofuFluids.SOYMILK.value(), (properties).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredHolder<Item, Item> BUCKET_SOYMILK_NETHER = ITEMS.registerItem("bucket_soymilk_nether", (properties) -> new BucketItem(TofuFluids.SOYMILK_HELL.value(), (properties).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredHolder<Item, Item> BUCKET_SOYMILK_SOUL = ITEMS.registerItem("bucket_soymilk_soul", (properties) -> new BucketItem(TofuFluids.SOYMILK_SOUL.value(), (properties).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredHolder<Item, Item> TOFUFISH_BUCKET = ITEMS.registerItem("tofufish_bucket", (properties) -> new MobBucketItem(TofuEntityTypes.TOFUFISH.get(), Fluids.WATER, SoundEvents.BUCKET_EMPTY_FISH, (properties).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredHolder<Item, Item> TOFUFISH_SOYMILK_BUCKET = ITEMS.registerItem("tofufish_soymilk_bucket", (properties) -> new MobBucketItem(TofuEntityTypes.TOFUFISH.get(), TofuFluids.SOYMILK.get(), SoundEvents.BUCKET_EMPTY_FISH, (properties).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredHolder<Item, Item> BUCKET_BITTERN = ITEMS.registerItem("bucket_bittern", (properties) -> new BucketItem(TofuFluids.BITTERN.value(), (properties).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final DeferredHolder<Item, Item> BUCKET_DOUBANJIANG = ITEMS.registerItem("bucket_doubanjiang", (properties) -> new BucketItem(TofuFluids.DOUBANJIANG.value(), (properties).craftRemainder(Items.BUCKET).stacksTo(1)));

	public static final DeferredHolder<Item, Item> GLASSBOWL = ITEMS.registerItem("glassbowl", (properties) -> new Item((properties)));

	public static final DeferredHolder<Item, Item> PUDDING = ITEMS.registerItem("pudding", (properties) -> new GlassBowlItem((properties).stacksTo(16).food(TofuFoods.PUDDING)));
	public static final DeferredHolder<Item, Item> PUDDING_SOYMILK = ITEMS.registerItem("pudding_soymilk", (properties) -> new GlassBowlItem((properties).stacksTo(16).food(TofuFoods.PUDDING_SOYMILK)));
	public static final DeferredHolder<Item, Item> NIKUJAGA = ITEMS.registerItem("nikujaga", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.NIKUJAGA)));
	public static final DeferredHolder<Item, Item> TOFUSOMEN = ITEMS.registerItem("tofusomen", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> TOFUSOMENBOWL_GLASS = ITEMS.registerItem("tofusomenbowl_glass", (properties) -> new GlassBowlItem((properties).stacksTo(16).food(TofuFoods.TOFUSOMEN)));
	public static final DeferredHolder<Item, Item> TASTYBEEFSTEW = ITEMS.registerItem("tastybeefstew", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.TASTYSTEW), true, true));
	public static final DeferredHolder<Item, Item> TASTYSTEW = ITEMS.registerItem("tastystew", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.TASTYSTEW), true, true));

	public static final DeferredHolder<Item, Item> HIYAYAKKO_GLASS = ITEMS.registerItem("hiyayakko", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.HIYAYAKKO), false));
	public static final DeferredHolder<Item, Item> NATTOHIYAYAKKO_GLASS = ITEMS.registerItem("nattohiyayakko", (properties) -> new DishItem((properties).stacksTo(16).food(TofuFoods.NATTOHIYAYAKKO)));

	public static final DeferredHolder<Item, Item> TOFU_KINU_SWORD = ITEMS.registerItem("tofu_kinu_sword", (properties) -> new TofuSwordItem(TofuToolMaterials.KINU, 0, -0.5F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_AXE = ITEMS.registerItem("tofu_kinu_axe", (properties) -> new TofuAxeItem(TofuToolMaterials.KINU, 0, -0.5F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_PICKAXE = ITEMS.registerItem("tofu_kinu_pickaxe", (properties) -> new TofuPickaxeItem(TofuToolMaterials.KINU, 0, -0.5F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_SHOVEL = ITEMS.registerItem("tofu_kinu_shovel", (properties) -> new TofuShovelItem(TofuToolMaterials.KINU, 0, -0.5F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_HOE = ITEMS.registerItem("tofu_kinu_hoe", (properties) -> new TofuHoeItem(TofuToolMaterials.KINU, 0, -0.5F, (properties.stacksTo(1))));

	public static final DeferredHolder<Item, Item> TOFU_MOMEN_SWORD = ITEMS.registerItem("tofu_momen_sword", (properties) -> new TofuSwordItem(TofuToolMaterials.MOMEN, 0, -1.4F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_AXE = ITEMS.registerItem("tofu_momen_axe", (properties) -> new TofuAxeItem(TofuToolMaterials.MOMEN, 0, -1.4F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_PICKAXE = ITEMS.registerItem("tofu_momen_pickaxe", (properties) -> new TofuPickaxeItem(TofuToolMaterials.MOMEN, 0, -1.4F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_SHOVEL = ITEMS.registerItem("tofu_momen_shovel", (properties) -> new TofuShovelItem(TofuToolMaterials.MOMEN, 0, -1.4F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_HOE = ITEMS.registerItem("tofu_momen_hoe", (properties) -> new TofuHoeItem(TofuToolMaterials.MOMEN, 0, -1.4F, (properties.stacksTo(1))));

	public static final DeferredHolder<Item, Item> TOFU_SOLID_SWORD = ITEMS.registerItem("tofu_solid_sword", (properties) -> new TofuSwordItem(TofuToolMaterials.SOLID, 3, -2.3F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_SOLID_AXE = ITEMS.registerItem("tofu_solid_axe", (properties) -> new TofuAxeItem(TofuToolMaterials.SOLID, 6.0F, -2.9F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_SOLID_PICKAXE = ITEMS.registerItem("tofu_solid_pickaxe", (properties) -> new TofuPickaxeItem(TofuToolMaterials.SOLID, 1.0F, -2.8F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_SOLID_SHOVEL = ITEMS.registerItem("tofu_solid_shovel", (properties) -> new TofuShovelItem(TofuToolMaterials.SOLID, 1.5F, -2.7F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_SOLID_HOE = ITEMS.registerItem("tofu_solid_hoe", (properties) -> new TofuHoeItem(TofuToolMaterials.SOLID, -1.0F, 0.0F, (properties.stacksTo(1))));

	public static final DeferredHolder<Item, Item> TOFU_METAL_SWORD = ITEMS.registerItem("tofu_metal_sword", (properties) -> new TofuSwordItem(TofuToolMaterials.METAL, 3, -2.3F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_AXE = ITEMS.registerItem("tofu_metal_axe", (properties) -> new TofuAxeItem(TofuToolMaterials.METAL, 6.0F, -2.9F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_PICKAXE = ITEMS.registerItem("tofu_metal_pickaxe", (properties) -> new TofuPickaxeItem(TofuToolMaterials.METAL, 1.0F, -2.8F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_SHOVEL = ITEMS.registerItem("tofu_metal_shovel", (properties) -> new TofuShovelItem(TofuToolMaterials.METAL, 1.5F, -2.7F, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_SHEARS = ITEMS.registerItem("tofu_metal_shears", (properties) -> new TofuShearsItem((properties).stacksTo(1).durability(224)));
	public static final DeferredHolder<Item, Item> TOFU_METAL_HOE = ITEMS.registerItem("tofu_metal_hoe", (properties) -> new TofuHoeItem(TofuToolMaterials.METAL, -2.0F, -1.0F, (properties.stacksTo(1))));


	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_SWORD = ITEMS.registerItem("tofu_diamond_sword", (properties) -> new TofuSwordItem(TofuToolMaterials.TOFUDIAMOND, 3, -2.3F, (properties.rarity(Rarity.UNCOMMON).stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_AXE = ITEMS.registerItem("tofu_diamond_axe", (properties) -> new TofuAxeItem(TofuToolMaterials.TOFUDIAMOND, 6.0F, -2.9F, (properties.rarity(Rarity.UNCOMMON).stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_PICKAXE = ITEMS.registerItem("tofu_diamond_pickaxe", (properties) -> new TofuPickaxeItem(TofuToolMaterials.TOFUDIAMOND, 1.0F, -2.7F, (properties.rarity(Rarity.UNCOMMON).stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_SHOVEL = ITEMS.registerItem("tofu_diamond_shovel", (properties) -> new TofuShovelItem(TofuToolMaterials.TOFUDIAMOND, 1.5F, -2.9F, (properties.rarity(Rarity.UNCOMMON).stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_HOE = ITEMS.registerItem("tofu_diamond_hoe", (properties) -> new TofuHoeItem(TofuToolMaterials.TOFUDIAMOND, -3.0F, 0.0F, (properties.rarity(Rarity.UNCOMMON).stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("tofu_upgrade_smithing_template", TofuItems::createTofuUpgradeTemplate);
	public static final DeferredHolder<Item, Item> ZUNDA_UPGRADE_SMITHING_TEMPLATE = ITEMS.registerItem("zunda_upgrade_smithing_template", TofuItems::createZundaBowUpgradeTemplate);


	public static final DeferredHolder<Item, Item> TOFU_KINU_HELMET = ITEMS.registerItem("tofu_kinu_helmet", (properties) -> new BreakableTofuArmorItem(TofuArmorMaterials.KINU, ArmorType.HELMET, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_CHESTPLATE = ITEMS.registerItem("tofu_kinu_chestplate", (properties) -> new BreakableTofuArmorItem(TofuArmorMaterials.KINU, ArmorType.CHESTPLATE, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_LEGGINGS = ITEMS.registerItem("tofu_kinu_leggings", (properties) -> new BreakableTofuArmorItem(TofuArmorMaterials.KINU, ArmorType.LEGGINGS, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_KINU_BOOTS = ITEMS.registerItem("tofu_kinu_boots", (properties) -> new BreakableTofuBootsItem(TofuArmorMaterials.KINU, ArmorType.BOOTS, -0.5F, (properties.stacksTo(1).component(TofuDataComponents.MAX_FALL_DURABILITY, 1))));

	public static final DeferredHolder<Item, Item> TOFU_MOMEN_HELMET = ITEMS.registerItem("tofu_momen_helmet", (properties) -> new BreakableTofuArmorItem(TofuArmorMaterials.MOMEN, ArmorType.HELMET, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_CHESTPLATE = ITEMS.registerItem("tofu_momen_chestplate", (properties) -> new BreakableTofuArmorItem(TofuArmorMaterials.MOMEN, ArmorType.CHESTPLATE, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_LEGGINGS = ITEMS.registerItem("tofu_momen_leggings", (properties) -> new BreakableTofuArmorItem(TofuArmorMaterials.MOMEN, ArmorType.LEGGINGS, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_MOMEN_BOOTS = ITEMS.registerItem("tofu_momen_boots", (properties) -> new BreakableTofuBootsItem(TofuArmorMaterials.MOMEN, ArmorType.BOOTS, -0.25F, (properties.stacksTo(1).component(TofuDataComponents.MAX_FALL_DURABILITY, 16))));

	public static final DeferredHolder<Item, Item> ARMOR_TOFU_SOLIDHELMET = ITEMS.registerItem("tofu_solid_helmet", (properties) -> new TofuArmorItem(TofuArmorMaterials.SOLID, ArmorType.HELMET, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> ARMOR_TOFU_SOLIDCHESTPLATE = ITEMS.registerItem("tofu_solid_chestplate", (properties) -> new TofuArmorItem(TofuArmorMaterials.SOLID, ArmorType.CHESTPLATE, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> ARMOR_TOFU_SOLIDLEGGINGS = ITEMS.registerItem("tofu_solid_leggings", (properties) -> new TofuArmorItem(TofuArmorMaterials.SOLID, ArmorType.LEGGINGS, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> ARMOR_TOFU_SOLIDBOOTS = ITEMS.registerItem("tofu_solid_boots", (properties) -> new TofuArmorItem(TofuArmorMaterials.SOLID, ArmorType.BOOTS, (properties.stacksTo(1))));

	public static final DeferredHolder<Item, Item> TOFU_METAL_HELMET = ITEMS.registerItem("tofu_metal_helmet", (properties) -> new TofuArmorItem(TofuArmorMaterials.METAL, ArmorType.HELMET, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_CHESTPLATE = ITEMS.registerItem("tofu_metal_chestplate", (properties) -> new TofuArmorItem(TofuArmorMaterials.METAL, ArmorType.CHESTPLATE, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_LEGGINGS = ITEMS.registerItem("tofu_metal_leggings", (properties) -> new TofuArmorItem(TofuArmorMaterials.METAL, ArmorType.LEGGINGS, (properties.stacksTo(1))));
	public static final DeferredHolder<Item, Item> TOFU_METAL_BOOTS = ITEMS.registerItem("tofu_metal_boots", (properties) -> new TofuArmorItem(TofuArmorMaterials.METAL, ArmorType.BOOTS, (properties.stacksTo(1))));

	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_HELMET = ITEMS.registerItem("tofu_diamond_helmet", (properties) -> new TofuArmorItem(TofuArmorMaterials.DIAMOND, ArmorType.HELMET, (properties.stacksTo(1).rarity(Rarity.UNCOMMON))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_CHESTPLATE = ITEMS.registerItem("tofu_diamond_chestplate", (properties) -> new TofuArmorItem(TofuArmorMaterials.DIAMOND, ArmorType.CHESTPLATE, (properties.stacksTo(1).rarity(Rarity.UNCOMMON))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_LEGGINGS = ITEMS.registerItem("tofu_diamond_leggings", (properties) -> new TofuArmorItem(TofuArmorMaterials.DIAMOND, ArmorType.LEGGINGS, (properties.stacksTo(1).rarity(Rarity.UNCOMMON))));
	public static final DeferredHolder<Item, Item> TOFU_DIAMOND_BOOTS = ITEMS.registerItem("tofu_diamond_boots", (properties) -> new TofuArmorItem(TofuArmorMaterials.DIAMOND, ArmorType.BOOTS, (properties.stacksTo(1).rarity(Rarity.UNCOMMON))));

	public static final DeferredHolder<Item, Item> TOFU_SHIELD = ITEMS.registerItem("tofu_shield", (properties) -> new TofuShieldItem((properties).stacksTo(1).durability(420)));
	public static final DeferredHolder<Item, Item> REFLECT_TOFU_SHIELD = ITEMS.registerItem("reflect_tofu_shield", (properties) -> new ReflectTofuShieldItem((properties).stacksTo(1).durability(460)));

	public static final DeferredHolder<Item, Item> ROLLINGPIN = ITEMS.registerItem("rollingpin", (properties) -> new RollingPinItem((properties)));

	public static final DeferredHolder<Item, Item> BUGLE = ITEMS.registerItem("bugle", (properties) -> new BugleItem((properties).stacksTo(1)));
	public static final DeferredHolder<Item, Item> TOFUSCOOP = ITEMS.registerItem("tofuscoop", (properties) -> new TofuScoopItem((properties).stacksTo(1).durability(264)));
	public static final DeferredHolder<Item, Item> TOFUSTICK = ITEMS.registerItem("tofustick", (properties) -> new TofuStickItem((properties).stacksTo(1).rarity(Rarity.UNCOMMON).durability(264)));
	public static final DeferredHolder<Item, Item> FUKUMAME = ITEMS.registerItem("fukumame", (properties) -> new FukumameItem((properties).stacksTo(1).enchantable(3).durability(64)));
	public static final DeferredHolder<Item, Item> NETHER_FUKUMAME = ITEMS.registerItem("nether_fukumame", (properties) -> new NetherFukumameItem((properties).stacksTo(1).enchantable(3).durability(64)));
	public static final DeferredHolder<Item, Item> INFERNO_NETHER_FUKUMAME = ITEMS.registerItem("inferno_nether_fukumame", (properties) -> new InfernoNetherFukumameItem((properties).stacksTo(1).enchantable(3).durability(64)));

	public static final DeferredHolder<Item, Item> SOUL_FUKUMAME = ITEMS.registerItem("soul_fukumame", (properties) -> new SoulFukumameItem((properties).stacksTo(1).enchantable(3).durability(64).rarity(Rarity.UNCOMMON)));

	public static final DeferredHolder<Item, Item> ZUNDA_BOW = ITEMS.registerItem("zunda_bow", (properties) -> new ZundaBowItem((properties).rarity(Rarity.UNCOMMON).enchantable(3).durability(426)));

	public static final DeferredHolder<Item, Item> ZUNDA_ARROW = ITEMS.registerItem("zunda_arrow", (properties) -> new ZundaArrowItem((properties)));

	public static final DeferredHolder<Item, Item> ZUNDAMUSHROOM_ON_A_STICK = ITEMS.registerItem("zundamushroom_on_a_stick", (properties) -> new ZundaMushroomOnAStickItem<>((properties).durability(25), TofuEntityTypes.TOFUPIG, 7));

	public static final DeferredHolder<Item, Item> TOFUGEM = ITEMS.registerItem("tofugem", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> ADVANCE_TOFUGEM = ITEMS.registerItem("adv_tofugem", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> TF_CAPACITOR = ITEMS.registerItem("tf_capacitor", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> TF_COIL = ITEMS.registerItem("tf_coil", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> TF_CIRCUIT = ITEMS.registerItem("tf_circuit", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> TF_OSCILLATOR = ITEMS.registerItem("tf_oscillator", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> TOFU_CORE = ITEMS.registerItem("tofu_core", (properties) -> new Item((properties)));
	public static final DeferredHolder<Item, Item> TF_BATTERY = ITEMS.registerItem("tf_battery", (properties) -> new TFBatteryItem((properties.stacksTo(1))));

	public static final DeferredHolder<Item, Item> TOFUNIAN_SPAWNEGG = ITEMS.registerItem("tofunian_spawnegg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.TOFUNIAN, 15460584, 13291425, (properties)));
	public static final DeferredHolder<Item, Item> TOFUCOW_SPAWNEGG = ITEMS.registerItem("tofucow_spawnegg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.TOFUCOW, 15460584, 10724259, (properties)));
	public static final DeferredHolder<Item, Item> TOFUPIG_SPAWNEGG = ITEMS.registerItem("tofupig_spawnegg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.TOFUPIG, 15460584, 10066329, (properties)));
	public static final DeferredHolder<Item, Item> TOFUSLIME_SPAWNEGG = ITEMS.registerItem("tofuslime_spawnegg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.TOFUSLIME, 15460584, 3026478, (properties)));
	public static final DeferredHolder<Item, Item> TOFUCREEPER_SPAWNEGG = ITEMS.registerItem("tofucreeper_spawnegg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.TOFUCREEPER, 15460584, 3026478, (properties)));

	public static final DeferredHolder<Item, Item> TOFUSPIDER_SPAWNEGG = ITEMS.registerItem("tofuspider_spawnegg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.TOFUSPIDER, 15460584, 3026478, (properties)));
	public static final DeferredHolder<Item, Item> TOFUFISH_SPAWNEGG = ITEMS.registerItem("tofufish_spawnegg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.TOFUFISH, 15460584, 3026478, (properties)));
	public static final DeferredHolder<Item, Item> TRAVELER_TOFUNIAN_SPAWNEGG = ITEMS.registerItem("traveler_tofunian_spawnegg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.TRAVELER_TOFUNIAN, 15460584, 8763986, (properties)));
	public static final DeferredHolder<Item, Item> TOFU_GANDLEM_SPAWNEGG = ITEMS.registerItem("tofu_gandlem_spawnegg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.TOFU_GANDLEM, 0xDDD8B5, 0x92D4F9, (properties)));
	public static final DeferredHolder<Item, Item> TOFU_GOLEM_SPAWNEGG = ITEMS.registerItem("tofu_golem_spawnegg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.TOFU_GOLEM, 0xDDD8B5, 0xFFFFFF, (properties)));
	public static final DeferredHolder<Item, Item> SHUDOFUSPIDER_SPAWNEGG = ITEMS.registerItem("shudofuspider_spawnegg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.SHUDOFUSPIDER, 0xDDD8B5, 0x5FA039, (properties)));
	public static final DeferredHolder<Item, Item> FUKUMAME_THOWER_SPAWNEGG = ITEMS.registerItem("fukumame_thower_spawn_egg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.FUKUMAME_THOWER, 0xF2BA86, 0xAC452D, (properties)));
	public static final DeferredHolder<Item, Item> ZUNDAMITE_SPAWNEGG = ITEMS.registerItem("zundamite_spawn_egg", (properties) -> new DeferredSpawnEggItem(TofuEntityTypes.ZUNDAMITE, 15460584, 0xA4D148, (properties)));

	public static final DeferredHolder<Item, Item> NATTO_COBWEB = ITEMS.registerItem("natto_cobweb", (properties) -> new NattoCobWebItem((properties)));

	//Tofu delight item
	public static final DeferredHolder<Item, Item> TOMATO_SOYBEAN_STEW = ITEMS.registerItem("tomato_soybean_stew", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.TOMATO_SOYBEAN_STEW)));

	public static final DeferredHolder<Item, Item> BOTTLE_DASHI = ITEMS.registerItem("bottle_dashi", (properties) -> new Item((properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> BOTTLE_SOYOIL = ITEMS.registerItem("bottle_soyoil", (properties) -> new Item((properties).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredHolder<Item, Item> SOYSAUSE_RAMEN = ITEMS.registerItem("soysause_ramen", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.SOYSAUSE_RAMEN)));

	public static final DeferredHolder<Item, Item> HELL_MABOU = ITEMS.registerItem("hell_mabou", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.HELL_MABOU, TofuConsumables.HELL_CHILI_FOOD)));
	public static final DeferredHolder<Item, Item> RED_SOUP = ITEMS.registerItem("red_soup", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.RED_SOUP, TofuConsumables.HELL_CHILI_FOOD)));
	public static final DeferredHolder<Item, Item> HELL_RED_SOUP = ITEMS.registerItem("hell_red_soup", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.HELL_RED_SOUP, TofuConsumables.HELL_CHILI_FOOD)));

	public static final DeferredHolder<Item, Item> SUKIYAKI = ITEMS.registerItem("sukiyaki", (properties) -> new DishItem((properties).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.SUKIYAKI)));
	public static final DeferredHolder<Item, Item> TOFU_BUNS_BURGER = ITEMS.registerItem("tofu_buns_burger", (properties) -> new Item((properties).food(TofuFoods.TOFU_BUNS_BURGER)));

	public static final DeferredHolder<Item, Item> STEAMED_BREAD = ITEMS.registerItem("steamed_bread", (properties) -> new Item((properties).food(TofuFoods.STEAMED_BREAD)));
	public static final DeferredHolder<Item, Item> STEAMED_BREAD_COCOA = ITEMS.registerItem("steamed_bread_cocoa", (properties) -> new Item((properties).food(TofuFoods.STEAMED_BREAD)));

	public static final DeferredHolder<Item, Item> KINAKO_BREAD = ITEMS.registerItem("kinako_bread", (properties) -> new Item((properties).food(TofuFoods.KINAKO_BREAD)));

	public static final DeferredHolder<Item, Item> EDAMAME_TEMPLA = ITEMS.registerItem("edamame_templa", (properties) -> new SaltFoodItem((properties).food(TofuFoods.EDAMAME_TEMPLA)));
	public static final DeferredHolder<Item, Item> NEGIMA = ITEMS.registerItem("negima", (properties) -> new DishItem((properties).food(TofuFoods.NEGIMA)));
	public static final DeferredHolder<Item, Item> SOY_KARAAGE = ITEMS.registerItem("soy_karaage", (properties) -> new Item((properties).food(TofuFoods.SOY_KARAAGE)));
	public static final DeferredHolder<Item, Item> SOYMEATDON = ITEMS.registerItem("soymeatdon", (properties) -> new Item((properties).food(TofuFoods.SOYMEATDON)));

	public static final DeferredHolder<Item, Item> TOFUNIAN_BANNER_PATTERN = ITEMS.registerItem("tofunian_banner_pattern", (properties) -> new BannerPatternItem(CustomTagGenerator.BannerPatternTagGenerator.TOFUNIAN_BANNER_PATTERN, properties.stacksTo(1).rarity(Rarity.RARE)));

	public static final DeferredHolder<Item, Item> LEEK_BOAT = ITEMS.registerItem("leek_boat", (properties) -> new BoatItem(TofuEntityTypes.LEEK_BOAT.get(), properties.stacksTo(1)));
	public static final DeferredHolder<Item, Item> LEEK_GREEN_BOAT = ITEMS.registerItem("leek_green_boat", (properties) -> new BoatItem(TofuEntityTypes.LEEK_GREEN_BOAT.get(), properties.stacksTo(1)));
	public static final DeferredHolder<Item, Item> TOFU_STEM_BOAT = ITEMS.registerItem("tofu_stem_boat", (properties) -> new BoatItem(TofuEntityTypes.TOFU_STEM_BOAT.get(), properties.stacksTo(1)));

	public static final DeferredHolder<Item, Item> LEEK_CHEST_BOAT = ITEMS.registerItem("leek_chest_boat", (properties) -> new BoatItem(TofuEntityTypes.LEEK_CHEST_BOAT.get(), properties.stacksTo(1)));
	public static final DeferredHolder<Item, Item> LEEK_GREEN_CHEST_BOAT = ITEMS.registerItem("leek_green_chest_boat", (properties) -> new BoatItem(TofuEntityTypes.LEEK_GREEN_CHEST_BOAT.get(), properties.stacksTo(1)));
	public static final DeferredHolder<Item, Item> TOFU_STEM_CHEST_BOAT = ITEMS.registerItem("tofu_stem_chest_boat", (properties) -> new BoatItem(TofuEntityTypes.TOFU_STEM_CHEST_BOAT.get(), properties.stacksTo(1)));
	public static final DeferredHolder<Item, Item> TOFU_KEY = ITEMS.registerItem("tofu_key", (properties) -> new Item(properties));
	public static final DeferredHolder<Item, Item> MUSIC_DISC_GREEN_BRANCH = ITEMS.registerItem("music_disc_green_branch", (properties) -> new Item(properties.jukeboxPlayable(TofuJukeboxSongs.GREEN_BRANCH)));


	private static Supplier<Item> register(String name, Supplier<Item> item) {
		return ITEMS.register(name, item);
	}


	private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
	private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

	private static final Component TOFU_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "tofu_upgrade"))).withStyle(TITLE_FORMAT);
	private static final Component TOFU_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "smithing_template.tofu_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component TOFU_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "smithing_template.tofu_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component TOFU_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "smithing_template.tofu_upgrade.base_slot_description")));

	private static final Component ZUNDA_BOW_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "zunda_upgrade"))).withStyle(TITLE_FORMAT);

	private static final Component ZUNDA_BOW_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "smithing_template.zunda_bow_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component ZUNDA_BOW_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "smithing_template.zunda_bow_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component ZUNDA_BOW_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "smithing_template.zunda_bow_upgrade.base_slot_description")));


	private static final ResourceLocation EMPTY_SLOT_HELMET = ResourceLocation.parse("item/empty_armor_slot_helmet");
	private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.parse("item/empty_armor_slot_chestplate");
	private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ResourceLocation.parse("item/empty_armor_slot_leggings");
	private static final ResourceLocation EMPTY_SLOT_BOOTS = ResourceLocation.parse("item/empty_armor_slot_boots");
	private static final ResourceLocation EMPTY_SLOT_HOE = ResourceLocation.parse("item/empty_slot_hoe");
	private static final ResourceLocation EMPTY_SLOT_AXE = ResourceLocation.parse("item/empty_slot_axe");
	private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.parse("item/empty_slot_sword");
	private static final ResourceLocation EMPTY_SLOT_SHOVEL = ResourceLocation.parse("item/empty_slot_shovel");
	private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.parse("item/empty_slot_pickaxe");
	private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.parse("item/empty_slot_ingot");
	private static final ResourceLocation EMPTY_SLOT_BOW = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "item/empty_slot_bow");
	private static final ResourceLocation EMPTY_SLOT_ZUNDAMA = ResourceLocation.fromNamespaceAndPath(TofuCraftReload.MODID, "item/empty_slot_zundama");


	private static List<ResourceLocation> createTofuUpgradeIconList() {
		return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
	}

	private static List<ResourceLocation> createTofuUpgradeMaterialList() {
		return List.of(EMPTY_SLOT_INGOT);
	}


	public static SmithingTemplateItem createTofuUpgradeTemplate(Item.Properties p_363106_) {
		return new SmithingTemplateItem(TOFU_UPGRADE_APPLIES_TO, TOFU_UPGRADE_INGREDIENTS, TOFU_UPGRADE, TOFU_UPGRADE_BASE_SLOT_DESCRIPTION, createTofuUpgradeMaterialList(), createTofuUpgradeIconList(), p_363106_);
	}

	private static List<ResourceLocation> createZundaBowUpgradeIconList() {
		return List.of(EMPTY_SLOT_BOW);
	}

	private static List<ResourceLocation> createZundaBowUpgradeMaterialList() {
		return List.of(EMPTY_SLOT_ZUNDAMA);
	}


	public static SmithingTemplateItem createZundaBowUpgradeTemplate(Item.Properties p_363106_) {
		return new SmithingTemplateItem(ZUNDA_BOW_UPGRADE_APPLIES_TO, ZUNDA_BOW_UPGRADE_INGREDIENTS, ZUNDA_BOW_UPGRADE, ZUNDA_BOW_UPGRADE_BASE_SLOT_DESCRIPTION, createZundaBowUpgradeIconList(), createZundaBowUpgradeMaterialList(), p_363106_);
	}

	public static void registerDispenserItem() {

		DispenseItemBehavior dispenseitembehavior1 = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
				DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem) p_123562_.getItem();
				BlockPos blockpos = p_123561_.pos().relative(p_123561_.state().getValue(DispenserBlock.FACING));
				Level level = p_123561_.level();
				if (dispensiblecontaineritem.emptyContents((Player) null, level, blockpos, (BlockHitResult) null)) {
					dispensiblecontaineritem.checkExtraContent((Player) null, level, p_123562_, blockpos);
					return new ItemStack(Items.BUCKET);
				} else {
					return this.defaultDispenseItemBehavior.dispense(p_123561_, p_123562_);
				}
			}
		};
		DispenserBlock.registerBehavior(BUCKET_SOYMILK.get(), dispenseitembehavior1);
		DispenserBlock.registerBehavior(BUCKET_SOYMILK_NETHER.get(), dispenseitembehavior1);
		DispenserBlock.registerBehavior(BUCKET_SOYMILK_SOUL.get(), dispenseitembehavior1);
		DispenserBlock.registerBehavior(BUCKET_BITTERN.get(), dispenseitembehavior1);
		DispenseItemBehavior dispenseitembehavior2 = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
				BlockPos blockpos = p_123561_.pos().relative(p_123561_.state().getValue(DispenserBlock.FACING));
				FluidState fluidState = p_123561_.level().getFluidState(blockpos);

				ItemStack result = RecipeHelper.getBitternResult(p_123561_.level(), fluidState.getType(), p_123562_.copyWithCount(1));
				if (result != null) {
					p_123561_.level().setBlock(blockpos, Block.byItem(result.getItem()).defaultBlockState(), 11);
					p_123561_.level().levelEvent(2001, blockpos, Block.getId(p_123561_.level().getBlockState(blockpos)));
					p_123562_.shrink(1);
					this.defaultDispenseItemBehavior.dispense(p_123561_, new ItemStack(Items.GLASS_BOTTLE));
				}
				return p_123562_;
			}
		};
		DispenserBlock.registerBehavior(BITTERN_BOTTLE.get(), dispenseitembehavior2);
		DispenseItemBehavior dispenseitembehavior3 = new DefaultDispenseItemBehavior() {
			private boolean success = false;
			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
				BlockPos blockpos = p_123561_.pos().relative(p_123561_.state().getValue(DispenserBlock.FACING));
				if (p_123561_.level().getBlockState(blockpos).is(TofuTags.Blocks.SOFT_TOFU)) {
					ItemStack stack = new ItemStack(Item.BY_BLOCK.get(p_123561_.level().getBlockState(blockpos).getBlock()));
					p_123561_.level().levelEvent(2001, blockpos, Block.getId(p_123561_.level().getBlockState(blockpos)));
					p_123561_.level().removeBlock(blockpos, false);
					this.defaultDispenseItemBehavior.dispense(p_123561_, stack);
					p_123562_.hurtAndBreak(1, p_123561_.level(), null, p_348118_ -> {
					});

					setSuccess(true);
				}
				return p_123562_;
			}

			public boolean isSuccess() {
				return this.success;
			}

			public void setSuccess(boolean p_123574_) {
				this.success = p_123574_;
			}

			protected void playSound(BlockSource p_123572_) {
				p_123572_.level().levelEvent(this.isSuccess() ? 1000 : 1001, p_123572_.pos(), 0);
			}
		};
		DispenserBlock.registerBehavior(TOFUSCOOP.get(), dispenseitembehavior3);

		DispenserBlock.registerBehavior(FUKUMAME.get(), new DamageableProjectileDispenseBehavior(FUKUMAME.get()) {

			protected int shootCount() {
				return 6;
			}
		});
		DispenserBlock.registerBehavior(NETHER_FUKUMAME.get(), new DamageableProjectileDispenseBehavior(NETHER_FUKUMAME.get()) {

			protected int shootCount() {
				return 6;
			}
		});
		DispenserBlock.registerBehavior(INFERNO_NETHER_FUKUMAME.get(), new DamageableProjectileDispenseBehavior(INFERNO_NETHER_FUKUMAME.get()) {
			protected int shootCount() {
				return 6;
			}
		});
		DispenserBlock.registerBehavior(SOUL_FUKUMAME.get(), new DamageableProjectileDispenseBehavior(SOUL_FUKUMAME.get()) {

			protected int shootCount() {
				return 6;
			}
		});

		DispenserBlock.registerBehavior(ZUNDA_ARROW.get(), new ProjectileDispenseBehavior(ZUNDA_ARROW.get()) {
		});

		DispenseItemBehavior dispenseitembehavior4 = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
				BlockPos blockpos = p_123561_.pos().relative(p_123561_.state().getValue(DispenserBlock.FACING));
				FluidState fluidState = p_123561_.level().getFluidState(blockpos);
				if (p_123562_.getItem() instanceof SpecialBitternItem specialBitternItem && specialBitternItem.fluidSupplier.get() == fluidState.getType()) {
					p_123561_.level().setBlock(blockpos, specialBitternItem.blockSupplier.get().defaultBlockState(), 11);
					p_123561_.level().levelEvent(2001, blockpos, Block.getId(p_123561_.level().getBlockState(blockpos)));
					p_123562_.shrink(1);
					this.defaultDispenseItemBehavior.dispense(p_123561_, new ItemStack(Items.GLASS_BOTTLE));
				}
				return p_123562_;
			}
		};
		DispenserBlock.registerBehavior(CRIMSON_BOTTLE.get(), dispenseitembehavior4);
		DispenserBlock.registerBehavior(WARPED_BOTTLE.get(), dispenseitembehavior4);
	}
}

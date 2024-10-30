package baguchan.tofucraft.registry;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.data.CustomTagGenerator;
import baguchan.tofucraft.dispenser.DamageableProjectileDispenseBehavior;
import baguchan.tofucraft.entity.TofuBoat;
import baguchan.tofucraft.entity.projectile.FukumameEntity;
import baguchan.tofucraft.entity.projectile.NetherFukumameEntity;
import baguchan.tofucraft.entity.projectile.SoulFukumameEntity;
import baguchan.tofucraft.entity.projectile.ZundaArrow;
import baguchan.tofucraft.item.BugleItem;
import baguchan.tofucraft.item.ChiliItem;
import baguchan.tofucraft.item.FukumameItem;
import baguchan.tofucraft.item.HoneySoymilkBottleItem;
import baguchan.tofucraft.item.InfernoNetherFukumameItem;
import baguchan.tofucraft.item.KoujiBaseItem;
import baguchan.tofucraft.item.NattoCobWebItem;
import baguchan.tofucraft.item.NetherFukumameItem;
import baguchan.tofucraft.item.NourishmentItem;
import baguchan.tofucraft.item.RamuneSoymilkBottleItem;
import baguchan.tofucraft.item.ReflectTofuShieldItem;
import baguchan.tofucraft.item.ReturnableDishItem;
import baguchan.tofucraft.item.RollingPinItem;
import baguchan.tofucraft.item.SaltFoodItem;
import baguchan.tofucraft.item.SeedAndRootItem;
import baguchan.tofucraft.item.SoulFukumameItem;
import baguchan.tofucraft.item.SoymilkBottleItem;
import baguchan.tofucraft.item.TFBatteryItem;
import baguchan.tofucraft.item.TofuArmorItem;
import baguchan.tofucraft.item.TofuAxeItem;
import baguchan.tofucraft.item.TofuBoatItem;
import baguchan.tofucraft.item.TofuHoeItem;
import baguchan.tofucraft.item.TofuPickaxeItem;
import baguchan.tofucraft.item.TofuScoopItem;
import baguchan.tofucraft.item.TofuShearsItem;
import baguchan.tofucraft.item.TofuShieldItem;
import baguchan.tofucraft.item.TofuShovelItem;
import baguchan.tofucraft.item.TofuStickItem;
import baguchan.tofucraft.item.TofuSwordItem;
import baguchan.tofucraft.item.ZundaArrowItem;
import baguchan.tofucraft.item.ZundaBowItem;
import baguchan.tofucraft.item.ZundaMushroomOnAStickItem;
import baguchan.tofucraft.utils.RecipeHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = TofuCraftReload.MODID)
public class TofuItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TofuCraftReload.MODID);

	public static final RegistryObject<Item> TOFUKINU = ITEMS.register("tofukinu", () -> new Item((new Item.Properties()).food(TofuFoods.TOFU)));
	public static final RegistryObject<Item> TOFUMOMEN = ITEMS.register("tofumomen", () -> new Item((new Item.Properties()).food(TofuFoods.TOFU)));
	public static final RegistryObject<Item> TOFUISHI = ITEMS.register("tofuishi", () -> new Item((new Item.Properties()).food(TofuFoods.ISHITOFU)));
	public static final RegistryObject<Item> TOFUMETAL = ITEMS.register("tofumetal", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> TOFUDIAMOND = ITEMS.register("tofudiamond", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> TOFUDIAMOND_NUGGET = ITEMS.register("tofudiamondnugget", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> TOFUHELL = ITEMS.register("tofuhell", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUHELL)));
	public static final RegistryObject<Item> TOFUSOUL = ITEMS.register("tofusoul", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUSOUL)));
	public static final RegistryObject<Item> TOFUGRILLED = ITEMS.register("tofugrilled", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUGRILLED)));
	public static final RegistryObject<Item> TOFUZUNDA = ITEMS.register("tofuzunda", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUZUNDA)));
	public static final RegistryObject<Item> TOFUMISO = ITEMS.register("tofumiso", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUMISO)));
	public static final RegistryObject<Item> TOFUDRIED = ITEMS.register("tofudried", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUDRIED)));
	public static final RegistryObject<Item> TOFUSMOKE = ITEMS.register("smoketofu", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUSMOKE)));
	public static final RegistryObject<Item> SHUDOFU = ITEMS.register("shudofu", () -> new Item((new Item.Properties()).food(TofuFoods.SHUDOFU)));
	public static final RegistryObject<Item> SOY_SCULK_BONE = ITEMS.register("soy_sculk_bone", () -> new Item((new Item.Properties())));

	public static final RegistryObject<Item> TOFUSESAME = ITEMS.register("tofusesame", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUSESAME)));
	public static final RegistryObject<Item> TOFUFRIED = ITEMS.register("tofufried", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUFRIED)));
	public static final RegistryObject<Item> TOFUFRIED_POUCH = ITEMS.register("tofufried_pouch", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUFRIED_POUCH)));
	public static final RegistryObject<Item> TOFUEGG = ITEMS.register("tofuegg", () -> new Item((new Item.Properties()).food(TofuFoods.TOFU_EGG)));


	public static final RegistryObject<Item> TOFUANNIN = ITEMS.register("tofuannin", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUANNIN)));
	public static final RegistryObject<Item> TOFUSTRAWBERRY = ITEMS.register("tofustrawberry", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUSTRAWBERRY)));


	public static final RegistryObject<Item> TOFU_MINCED = ITEMS.register("tofuminced", () -> new Item((new Item.Properties()).food(TofuFoods.TOFU)));


	public static final RegistryObject<Item> BITTERN_BOTTLE = ITEMS.register("bittern_bottle", () -> new Item((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> CRIMSON_BOTTLE = ITEMS.register("crimson_fluid_bottle", () -> new Item((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> WARPED_BOTTLE = ITEMS.register("warped_fluid_bottle", () -> new Item((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SHROOM_BOTTLE = ITEMS.register("shroom_bottle", () -> new Item((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE)));

	public static final RegistryObject<Item> SALT = ITEMS.register("salt", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> SEEDS_SOYBEANS = ITEMS.register("seeds_soybeans", () -> new ItemNameBlockItem(TofuBlocks.SOYBEAN.get(), (new Item.Properties())));
	public static final RegistryObject<Item> SEEDS_SOYBEANS_NETHER = ITEMS.register("seeds_soybeans_nether", () -> new ItemNameBlockItem(TofuBlocks.SOYBEAN_NETHER.get(), (new Item.Properties())));
	public static final RegistryObject<Item> SEEDS_SOYBEANS_SOUL = ITEMS.register("seeds_soybeans_soul", () -> new ItemNameBlockItem(TofuBlocks.SOYBEAN_SOUL.get(), (new Item.Properties()).rarity(Rarity.UNCOMMON)));
	public static final RegistryObject<Item> SOYBEAN_PARCHED = ITEMS.register("soybeans_parched", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> KINAKO = ITEMS.register("kinako", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> EDAMAME = ITEMS.register("edamame", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> BOILED_EDAMAME = ITEMS.register("edamame_boild", () -> new Item((new Item.Properties()).food(TofuFoods.BOILED_EDAMAME)));
	public static final RegistryObject<Item> MINCEDPOTATO = ITEMS.register("mincedpotato", () -> new Item((new Item.Properties())));

	public static final RegistryObject<Item> SEEDS_CHILI = ITEMS.register("seeds_chili", () -> new ItemNameBlockItem(TofuBlocks.CHILI_CROP.get(), (new Item.Properties())));
	public static final RegistryObject<Item> CHILI = ITEMS.register("chili", () -> new ChiliItem((new Item.Properties()).food(TofuFoods.CHILI)));
	public static final RegistryObject<Item> DOUBANJIANG = ITEMS.register("doubanjiang", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> MABODOFU = ITEMS.register("mabodofu", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).food(TofuFoods.MABODOFU).stacksTo(16)));

	public static final RegistryObject<Item> FUKUMENI = ITEMS.register("fukumeni", () -> new SaltFoodItem((new Item.Properties()).food(TofuFoods.FUKUMENI)));
	public static final RegistryObject<Item> KOYADOFUSTEW = ITEMS.register("koyadofustew", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).food(TofuFoods.KOYADOFUSTEW).stacksTo(16)));

	public static final RegistryObject<Item> KOUJI_BASE = ITEMS.register("koujibase", () -> new KoujiBaseItem((new Item.Properties()).stacksTo(1)));
	public static final RegistryObject<Item> KOUJI = ITEMS.register("kouji", () -> new Item((new Item.Properties())));

	public static final RegistryObject<Item> MISO = ITEMS.register("miso", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> BOTTLE_SOYSAUSE = ITEMS.register("bottle_soysause", () -> new Item((new Item.Properties()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16)));
	public static final RegistryObject<Item> NANBAN = ITEMS.register("nanban", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).food(TofuFoods.NANBAN).stacksTo(16)));
	public static final RegistryObject<Item> NANBANTOFU = ITEMS.register("nanbantofu", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).food(TofuFoods.NANBANTOFU).stacksTo(16)));

	public static final RegistryObject<Item> SOY_CHEESE = ITEMS.register("soy_cheese", () -> new Item((new Item.Properties()).food(TofuFoods.SOY_CHEESE)));
	public static final RegistryObject<Item> SOY_NETHER_CHEESE = ITEMS.register("soy_nether_cheese", () -> new Item((new Item.Properties()).food(TofuFoods.SOY_NETHER_CHEESE)));
	public static final RegistryObject<Item> SOY_SOUL_CHEESE = ITEMS.register("soy_soul_cheese", () -> new Item((new Item.Properties()).food(TofuFoods.SOY_SOUL_CHEESE)));

	public static final RegistryObject<Item> YUDOFU = ITEMS.register("yudofu", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.YUDOFU)));
	public static final RegistryObject<Item> EDAMAME_RICE = ITEMS.register("edamame_rice", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.EDAMAME_RICE)));
	public static final RegistryObject<Item> AGEDASHI_TOFU = ITEMS.register("agedashi_tofu", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.AGEDASHI_TOFU)));
	public static final RegistryObject<Item> TOFU_STEAK = ITEMS.register("tofusteak", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUSTEAK)));
	public static final RegistryObject<Item> OAGE = ITEMS.register("oage", () -> new Item((new Item.Properties()).food(TofuFoods.OAGE)));


	public static final RegistryObject<Item> NATTO = ITEMS.register("natto", () -> new Item((new Item.Properties()).food(TofuFoods.NATTO)));
	public static final RegistryObject<Item> NETHER_NATTO = ITEMS.register("nether_natto", () -> new Item((new Item.Properties()).food(TofuFoods.NETHER_NATTO)));

	public static final RegistryObject<Item> STARCH = ITEMS.register("starch", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> STARCH_RAW = ITEMS.register("starch_raw", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> FILTERCLOTH = ITEMS.register("filtercloth", () -> new Item((new Item.Properties())));

	public static final RegistryObject<Item> GELATIN = ITEMS.register("gelatin", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> GELATINRAW = ITEMS.register("gelatinraw", () -> new Item((new Item.Properties())));

	public static final RegistryObject<Item> LEEK = ITEMS.register("leek", () -> new ItemNameBlockItem(TofuBlocks.LEEK_CROP.get(), (new Item.Properties())));
	public static final RegistryObject<Item> RICE = ITEMS.register("rice", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> SEEDS_RICE = ITEMS.register("seeds_rice", () -> new SeedAndRootItem(TofuBlocks.RICE_CROP.get(), TofuBlocks.RICE_ROOT.get(), (new Item.Properties())));
	public static final RegistryObject<Item> SPROUTS = ITEMS.register("sprouts", () -> new Item(new Item.Properties().food(TofuFoods.SPROUTS)));

	public static final RegistryObject<Item> CHIKUWA = ITEMS.register("chikuwa", () -> new Item((new Item.Properties()).food(TofuFoods.CHIKUWA)));
	public static final RegistryObject<Item> TOFU_CHIKUWA = ITEMS.register("tofu_chikuwa", () -> new Item((new Item.Properties()).food(TofuFoods.TOFU_CHIKUWA)));

	public static final RegistryObject<Item> APRICOT = ITEMS.register("apricot", () -> new ReturnableDishItem(TofuItems.APRICOTSEED, (new Item.Properties()).food(TofuFoods.APRICOT), false));
	public static final RegistryObject<Item> APRICOTJERRY_BOTTLE = ITEMS.register("apricotjerry_bottle", () -> new Item((new Item.Properties().stacksTo(16))));
	public static final RegistryObject<Item> APRICOTJERRY_BREAD = ITEMS.register("apricotjerry_bread", () -> new Item((new Item.Properties()).food(TofuFoods.APRICOT_BREAD)));
	public static final RegistryObject<Item> APRICOTSEED = ITEMS.register("apricotseed", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> KYONINSO = ITEMS.register("kyoninso", () -> new Item((new Item.Properties())));

	public static final RegistryObject<Item> YUBA = ITEMS.register("yuba", () -> new Item((new Item.Properties()).food(TofuFoods.YUBA)));
	public static final RegistryObject<Item> ZUNDA = ITEMS.register("zunda", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> ZUNDAMA = ITEMS.register("zundama", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> ZUNDARUBY = ITEMS.register("zundaruby", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> TOFU_HAMBURG_RAW = ITEMS.register("tofuhamburg_raw", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> TOFU_HAMBURG = ITEMS.register("tofuhamburg", () -> new Item((new Item.Properties()).food(TofuFoods.TOFU_HAMBURG)));
	public static final RegistryObject<Item> RAW_TOFU_FISH = ITEMS.register("raw_tofufish", () -> new Item((new Item.Properties()).food(TofuFoods.RAW_TOFUFISH)));
	public static final RegistryObject<Item> COOKED_TOFU_FISH = ITEMS.register("cooked_tofufish", () -> new Item((new Item.Properties()).food(TofuFoods.COOKED_TOFUFISH)));
	public static final RegistryObject<Item> MISODENGAKU = ITEMS.register("misodengaku", () -> new ReturnableDishItem(() -> Items.STICK, (new Item.Properties()).food(TofuFoods.MISODENGAKU)));
	public static final RegistryObject<Item> TOFUCOOKIE = ITEMS.register("tofucookie", () -> new Item((new Item.Properties()).food(TofuFoods.TOFUCOOKIE)));
	public static final RegistryObject<Item> TTTBURGER = ITEMS.register("tttburger", () -> new Item((new Item.Properties()).food(TofuFoods.TTTBURGER)));
	public static final RegistryObject<Item> MEAT_WRAPPED_YUBA = ITEMS.register("meatwrapped_yuba", () -> new Item((new Item.Properties()).food(TofuFoods.MEAT_WRAPPED_YUBA)));
	public static final RegistryObject<Item> SOYMEAT = ITEMS.register("soymeat", () -> new Item((new Item.Properties()).food(TofuFoods.SOYMEAT)));

	public static final RegistryObject<Item> SOYSTICK = ITEMS.register("soystick", () -> new Item((new Item.Properties()).food(TofuFoods.SOYSTICK)));
	public static final RegistryObject<Item> MISOSOUP = ITEMS.register("misosoup", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).food(TofuFoods.MISOSOUP).stacksTo(16)));
	public static final RegistryObject<Item> MOYASHIITAME = ITEMS.register("moyashiitame", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).food(TofuFoods.MOYASHIITAME).stacksTo(16)));
	public static final RegistryObject<Item> MOYASHIOHITASHI = ITEMS.register("moyashiohitashi", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).food(TofuFoods.MOYASHIOHITASHI).stacksTo(16)));
	public static final RegistryObject<Item> SALTYMELON = ITEMS.register("saltymelon", () -> new SaltFoodItem((new Item.Properties()).food(TofuFoods.SALTYMELON)));

	public static final RegistryObject<Item> SOYMILK = ITEMS.register("soymilk", () -> new SoymilkBottleItem(MobEffects.REGENERATION, MobEffects.HEALTH_BOOST, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_ANNIN = ITEMS.register("soymilk_annin", () -> new SoymilkBottleItem(MobEffects.HEALTH_BOOST, MobEffects.ABSORPTION, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_APPLE = ITEMS.register("soymilk_apple", () -> new SoymilkBottleItem(MobEffects.DAMAGE_RESISTANCE, MobEffects.ABSORPTION, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_COCOA = ITEMS.register("soymilk_cocoa", () -> new SoymilkBottleItem(MobEffects.JUMP, MobEffects.MOVEMENT_SPEED, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_FRUITS = ITEMS.register("soymilk_fruits", () -> new SoymilkBottleItem(MobEffects.SLOW_FALLING, MobEffects.JUMP, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_HONEY = ITEMS.register("soymilk_honey", () -> new HoneySoymilkBottleItem((new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_KINAKO = ITEMS.register("soymilk_kinako", () -> new SoymilkBottleItem(MobEffects.MOVEMENT_SPEED, MobEffects.DIG_SPEED, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_PUDDING = ITEMS.register("soymilk_pudding", () -> new SoymilkBottleItem(MobEffects.REGENERATION, MobEffects.HEALTH_BOOST, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_PUMPKIN = ITEMS.register("soymilk_pumpkin", () -> new SoymilkBottleItem(MobEffects.DAMAGE_BOOST, MobEffects.DIG_SPEED, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_RAMUNE = ITEMS.register("soymilk_ramune", () -> new RamuneSoymilkBottleItem((new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_SAKURA = ITEMS.register("soymilk_sakura", () -> new SoymilkBottleItem(MobEffects.DAMAGE_RESISTANCE, MobEffects.FIRE_RESISTANCE, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_STRAWBERRY = ITEMS.register("soymilk_strawberry", () -> new SoymilkBottleItem(MobEffects.DIG_SPEED, MobEffects.MOVEMENT_SPEED, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_TEA = ITEMS.register("soymilk_tea", () -> new SoymilkBottleItem(MobEffects.LUCK, MobEffects.WATER_BREATHING, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_HELL_BOTTLE = ITEMS.register("soymilk_hell_bottle", () -> new SoymilkBottleItem(MobEffects.FIRE_RESISTANCE, MobEffects.DAMAGE_RESISTANCE, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYMILK_SOUL_BOTTLE = ITEMS.register("soymilk_soul_bottle", () -> new SoymilkBottleItem(MobEffects.ABSORPTION, MobEffects.HEALTH_BOOST, (new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));


	public static final RegistryObject<Item> KINAKO_MANJU = ITEMS.register("kinakomanju", () -> new Item((new Item.Properties()).food(TofuFoods.KINAKO_MANJU)));
	public static final RegistryObject<Item> ZUNDA_MANJU = ITEMS.register("zundamanju", () -> new Item((new Item.Properties()).food(TofuFoods.ZUNDA_MANJU)));
	public static final RegistryObject<Item> NETHER_MANJU = ITEMS.register("nethermanju", () -> new Item((new Item.Properties()).food(TofuFoods.NETHER_MANJU)));
	public static final RegistryObject<Item> SOUL_MANJU = ITEMS.register("soulmanju", () -> new Item((new Item.Properties()).food(TofuFoods.SOUL_MANJU)));
	public static final RegistryObject<Item> ZUNDA_MOCHI = ITEMS.register("zunda_mochi", () -> new Item((new Item.Properties()).food(TofuFoods.ZUNDA_MOCHI)));
	public static final RegistryObject<Item> KINAKO_MOCHI = ITEMS.register("kinako_mochi", () -> new Item((new Item.Properties()).food(TofuFoods.KINAKO_MOCHI)));
	public static final RegistryObject<Item> CRIMSON_SOUP = ITEMS.register("crimson_soup", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).food(TofuFoods.CRIMSON_SOUP).stacksTo(16)));


	public static final RegistryObject<Item> ONIGIRI = ITEMS.register("onigiri", () -> new Item((new Item.Properties()).food(TofuFoods.ONIGIRI)));
	public static final RegistryObject<Item> ONIGIRI_SALT = ITEMS.register("onigiri_salt", () -> new Item((new Item.Properties()).food(TofuFoods.ONIGIRI_SALT)));

	public static final RegistryObject<Item> INARI = ITEMS.register("inari", () -> new Item((new Item.Properties()).food(TofuFoods.INARI)));

	public static final RegistryObject<Item> OKARA = ITEMS.register("okara", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> OKARASTICK = ITEMS.register("okarastick", () -> new Item((new Item.Properties()).food(TofuFoods.OKARASTICK)));
	public static final RegistryObject<Item> OKARA_DONUT = ITEMS.register("okara_donut", () -> new Item((new Item.Properties()).food(TofuFoods.OKARA_DONUT)));

	public static final RegistryObject<Item> SOBOROTOFUSAUTE = ITEMS.register("soborotofusaute", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).food(TofuFoods.SOBOROTOFUSAUTE)));

	public static final RegistryObject<Item> YAKIONIGIRI_MISO = ITEMS.register("yakionigiri_miso", () -> new Item((new Item.Properties()).food(TofuFoods.YAKIONIGIRI_MISO)));
	public static final RegistryObject<Item> YAKIONIGIRI_SHOYU = ITEMS.register("yakionigiri_shoyu", () -> new Item((new Item.Properties()).food(TofuFoods.YAKIONIGIRI_SHOYU)));
	public static final RegistryObject<Item> RICE_BURGER = ITEMS.register("riceburger", () -> new Item((new Item.Properties()).food(TofuFoods.RICE_BURGER)));
	public static final RegistryObject<Item> RICE_NATTO = ITEMS.register("ricenatto", () -> new NourishmentItem((new Item.Properties()).food(TofuFoods.RICE_NATTO)));
	public static final RegistryObject<Item> RICE_NATTO_LEEK = ITEMS.register("ricenattoleek", () -> new NourishmentItem((new Item.Properties()).food(TofuFoods.RICE_NATTOLEEK)));
	public static final RegistryObject<Item> RICE_NETHER_NATTO = ITEMS.register("rice_nether_natto", () -> new NourishmentItem((new Item.Properties()).food(TofuFoods.RICE_NETHER_NATTO)));
	public static final RegistryObject<Item> RICE_NETHER_NATTO_LEEK = ITEMS.register("rice_nether_natto_leek", () -> new NourishmentItem((new Item.Properties()).food(TofuFoods.RICE_NETHER_NATTO_LEEK)));

	public static final RegistryObject<Item> RICE_TOFU = ITEMS.register("ricetofu", () -> new Item((new Item.Properties()).food(TofuFoods.RICE_TOFU)));
	public static final RegistryObject<Item> RICE_SOBORO_TOFU = ITEMS.register("ricesoborotofu", () -> new Item((new Item.Properties()).food(TofuFoods.RICE_SOBORO_TOFU)));
	public static final RegistryObject<Item> GOHEIMOCHI = ITEMS.register("goheimochi", () -> new ReturnableDishItem(() -> Items.STICK, (new Item.Properties()).food(TofuFoods.GOHEIMOCHI)));


	public static final RegistryObject<Item> SOY_CHOCOLATE = ITEMS.register("soy_chocolate", () -> new Item((new Item.Properties()).food(TofuFoods.SOY_CHOCOLATE)));
	public static final RegistryObject<Item> TOFUNIAN_SOY_CHOCOLATE = ITEMS.register("tofunian_soy_chocolate", () -> new Item((new Item.Properties()).food(TofuFoods.SOY_CHOCOLATE)));

	public static final RegistryObject<Item> BUCKET_SOYMILK = ITEMS.register("bucket_soymilk", () -> new BucketItem(TofuFluids.SOYMILK, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final RegistryObject<Item> BUCKET_SOYMILK_NETHER = ITEMS.register("bucket_soymilk_nether", () -> new BucketItem(TofuFluids.SOYMILK_HELL, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final RegistryObject<Item> BUCKET_SOYMILK_SOUL = ITEMS.register("bucket_soymilk_soul", () -> new BucketItem(TofuFluids.SOYMILK_SOUL, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final RegistryObject<Item> TOFUFISH_BUCKET = ITEMS.register("tofufish_bucket", () -> new MobBucketItem(TofuEntityTypes.TOFUFISH, () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final RegistryObject<Item> TOFUFISH_SOYMILK_BUCKET = ITEMS.register("tofufish_soymilk_bucket", () -> new MobBucketItem(TofuEntityTypes.TOFUFISH, () -> TofuFluids.SOYMILK.get(), () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1)));
	public static final RegistryObject<Item> BUCKET_BITTERN = ITEMS.register("bucket_bittern", () -> new BucketItem(TofuFluids.BITTERN, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1)));

	public static final RegistryObject<Item> GLASSBOWL = ITEMS.register("glassbowl", () -> new Item((new Item.Properties())));

	public static final RegistryObject<Item> PUDDING = ITEMS.register("pudding", () -> new ReturnableDishItem(TofuItems.GLASSBOWL, (new Item.Properties()).stacksTo(16).food(TofuFoods.PUDDING), false));
	public static final RegistryObject<Item> PUDDING_SOYMILK = ITEMS.register("pudding_soymilk", () -> new ReturnableDishItem(TofuItems.GLASSBOWL, (new Item.Properties()).stacksTo(16).food(TofuFoods.PUDDING_SOYMILK), false));
	public static final RegistryObject<Item> NIKUJAGA = ITEMS.register("nikujaga", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).food(TofuFoods.NIKUJAGA)));
	public static final RegistryObject<Item> TOFUSOMEN = ITEMS.register("tofusomen", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> TOFUSOMENBOWL_GLASS = ITEMS.register("tofusomenbowl_glass", () -> new ReturnableDishItem(TofuItems.GLASSBOWL, (new Item.Properties()).stacksTo(16).food(TofuFoods.TOFUSOMEN), false));
	public static final RegistryObject<Item> TASTYBEEFSTEW = ITEMS.register("tastybeefstew", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).food(TofuFoods.TASTYSTEW), true, true));
	public static final RegistryObject<Item> TASTYSTEW = ITEMS.register("tastystew", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).food(TofuFoods.TASTYSTEW), true, true));

	public static final RegistryObject<Item> HIYAYAKKO_GLASS = ITEMS.register("hiyayakko", () -> new ReturnableDishItem(TofuItems.GLASSBOWL, (new Item.Properties()).stacksTo(16).food(TofuFoods.HIYAYAKKO), false));
	public static final RegistryObject<Item> NATTOHIYAYAKKO_GLASS = ITEMS.register("nattohiyayakko", () -> new ReturnableDishItem(TofuItems.GLASSBOWL, (new Item.Properties()).stacksTo(16).food(TofuFoods.NATTOHIYAYAKKO)));

	public static final RegistryObject<Item> TOFU_KINU_SWORD = ITEMS.register("tofu_kinu_sword", () -> new TofuSwordItem(TofuItemTier.KINU, 0, -2.2F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_KINU_AXE = ITEMS.register("tofu_kinu_axe", () -> new TofuAxeItem(TofuItemTier.KINU, 0.0F, -2.25F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_KINU_PICKAXE = ITEMS.register("tofu_kinu_pickaxe", () -> new TofuPickaxeItem(TofuItemTier.KINU, 0, -2.2F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_KINU_SHOVEL = ITEMS.register("tofu_kinu_shovel", () -> new TofuShovelItem(TofuItemTier.KINU, 0.0F, -2.2F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_KINU_HOE = ITEMS.register("tofu_kinu_hoe", () -> new TofuHoeItem(TofuItemTier.KINU, 0, 1.0F, (new Item.Properties())));

	public static final RegistryObject<Item> TOFU_MOMEN_SWORD = ITEMS.register("tofu_momen_sword", () -> new TofuSwordItem(TofuItemTier.MOMEN, 0, -2.2F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_MOMEN_AXE = ITEMS.register("tofu_momen_axe", () -> new TofuAxeItem(TofuItemTier.MOMEN, 1.0F, -2.5F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_MOMEN_PICKAXE = ITEMS.register("tofu_momen_pickaxe", () -> new TofuPickaxeItem(TofuItemTier.MOMEN, 0, -2.25F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_MOMEN_SHOVEL = ITEMS.register("tofu_momen_shovel", () -> new TofuShovelItem(TofuItemTier.MOMEN, 0.0F, -2.25F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_MOMEN_HOE = ITEMS.register("tofu_momen_hoe", () -> new TofuHoeItem(TofuItemTier.MOMEN, 0, 1.0F, (new Item.Properties())));

	public static final RegistryObject<Item> TOFU_SOLID_SWORD = ITEMS.register("tofu_solid_sword", () -> new TofuSwordItem(TofuItemTier.SOLID, 3, -2.3F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_SOLID_AXE = ITEMS.register("tofu_solid_axe", () -> new TofuAxeItem(TofuItemTier.SOLID, 6.0F, -2.9F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_SOLID_PICKAXE = ITEMS.register("tofu_solid_pickaxe", () -> new TofuPickaxeItem(TofuItemTier.SOLID, 1, -2.7F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_SOLID_SHOVEL = ITEMS.register("tofu_solid_shovel", () -> new TofuShovelItem(TofuItemTier.SOLID, 1.5F, -2.9F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_SOLID_HOE = ITEMS.register("tofu_solid_hoe", () -> new TofuHoeItem(TofuItemTier.SOLID, -1, -1.0F, (new Item.Properties())));

	public static final RegistryObject<Item> TOFU_METAL_SWORD = ITEMS.register("tofu_metal_sword", () -> new TofuSwordItem(TofuItemTier.METAL, 3, -2.3F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_METAL_AXE = ITEMS.register("tofu_metal_axe", () -> new TofuAxeItem(TofuItemTier.METAL, 5.0F, -3.1F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_METAL_PICKAXE = ITEMS.register("tofu_metal_pickaxe", () -> new TofuPickaxeItem(TofuItemTier.METAL, 1, -2.7F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_METAL_SHOVEL = ITEMS.register("tofu_metal_shovel", () -> new TofuShovelItem(TofuItemTier.METAL, 1.5F, -2.9F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_METAL_SHEARS = ITEMS.register("tofu_metal_shears", () -> new TofuShearsItem((new Item.Properties()).stacksTo(1).durability(224)));
	public static final RegistryObject<Item> TOFU_METAL_HOE = ITEMS.register("tofu_metal_hoe", () -> new TofuHoeItem(TofuItemTier.METAL, -2, -1.0F, (new Item.Properties())));


	public static final RegistryObject<Item> TOFU_DIAMOND_SWORD = ITEMS.register("tofu_diamond_sword", () -> new TofuSwordItem(TofuItemTier.TOFUDIAMOND, 3, -2.4F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_DIAMOND_AXE = ITEMS.register("tofu_diamond_axe", () -> new TofuAxeItem(TofuItemTier.TOFUDIAMOND, 5.0F, -3.2F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_DIAMOND_PICKAXE = ITEMS.register("tofu_diamond_pickaxe", () -> new TofuPickaxeItem(TofuItemTier.TOFUDIAMOND, 1, -2.8F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_DIAMOND_SHOVEL = ITEMS.register("tofu_diamond_shovel", () -> new TofuShovelItem(TofuItemTier.TOFUDIAMOND, 1.5F, -3.0F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_DIAMOND_HOE = ITEMS.register("tofu_diamond_hoe", () -> new TofuHoeItem(TofuItemTier.TOFUDIAMOND, -4, 0.0F, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("tofu_upgrade_smithing_template", TofuItems::createTofuUpgradeTemplate);
	public static final RegistryObject<Item> ZUNDA_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("zunda_upgrade_smithing_template", TofuItems::createZundaBowUpgradeTemplate);


	public static final RegistryObject<ArmorItem> TOFU_KINU_HELMET = ITEMS.register("tofu_kinu_helmet", () -> new TofuArmorItem(TofuArmorMaterial.KINU, ArmorItem.Type.HELMET, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> TOFU_KINU_CHESTPLATE = ITEMS.register("tofu_kinu_chestplate", () -> new TofuArmorItem(TofuArmorMaterial.KINU, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> TOFU_KINU_LEGGINGS = ITEMS.register("tofu_kinu_leggings", () -> new TofuArmorItem(TofuArmorMaterial.KINU, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> TOFU_KINU_BOOTS = ITEMS.register("tofu_kinu_boots", () -> new TofuArmorItem(TofuArmorMaterial.KINU, ArmorItem.Type.BOOTS, (new Item.Properties())));

	public static final RegistryObject<ArmorItem> TOFU_MOMEN_HELMET = ITEMS.register("tofu_momen_helmet", () -> new TofuArmorItem(TofuArmorMaterial.MOMEN, ArmorItem.Type.HELMET, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> TOFU_MOMEN_CHESTPLATE = ITEMS.register("tofu_momen_chestplate", () -> new TofuArmorItem(TofuArmorMaterial.MOMEN, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> TOFU_MOMEN_LEGGINGS = ITEMS.register("tofu_momen_leggings", () -> new TofuArmorItem(TofuArmorMaterial.MOMEN, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> TOFU_MOMEN_BOOTS = ITEMS.register("tofu_momen_boots", () -> new TofuArmorItem(TofuArmorMaterial.MOMEN, ArmorItem.Type.BOOTS, (new Item.Properties())));

	public static final RegistryObject<ArmorItem> ARMOR_TOFU_SOLIDHELMET = ITEMS.register("tofu_solid_helmet", () -> new TofuArmorItem(TofuArmorMaterial.SOLID, ArmorItem.Type.HELMET, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> ARMOR_TOFU_SOLIDCHESTPLATE = ITEMS.register("tofu_solid_chestplate", () -> new TofuArmorItem(TofuArmorMaterial.SOLID, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> ARMOR_TOFU_SOLIDLEGGINGS = ITEMS.register("tofu_solid_leggings", () -> new TofuArmorItem(TofuArmorMaterial.SOLID, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> ARMOR_TOFU_SOLIDBOOTS = ITEMS.register("tofu_solid_boots", () -> new TofuArmorItem(TofuArmorMaterial.SOLID, ArmorItem.Type.BOOTS, (new Item.Properties())));

	public static final RegistryObject<ArmorItem> TOFU_METAL_HELMET = ITEMS.register("tofu_metal_helmet", () -> new TofuArmorItem(TofuArmorMaterial.METAL, ArmorItem.Type.HELMET, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> TOFU_METAL_CHESTPLATE = ITEMS.register("tofu_metal_chestplate", () -> new TofuArmorItem(TofuArmorMaterial.METAL, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> TOFU_METAL_LEGGINGS = ITEMS.register("tofu_metal_leggings", () -> new TofuArmorItem(TofuArmorMaterial.METAL, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> TOFU_METAL_BOOTS = ITEMS.register("tofu_metal_boots", () -> new TofuArmorItem(TofuArmorMaterial.METAL, ArmorItem.Type.BOOTS, (new Item.Properties())));

	public static final RegistryObject<ArmorItem> TOFU_DIAMOND_HELMET = ITEMS.register("tofu_diamond_helmet", () -> new TofuArmorItem(TofuArmorMaterial.DIAMOND, ArmorItem.Type.HELMET, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> TOFU_DIAMOND_CHESTPLATE = ITEMS.register("tofu_diamond_chestplate", () -> new TofuArmorItem(TofuArmorMaterial.DIAMOND, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> TOFU_DIAMOND_LEGGINGS = ITEMS.register("tofu_diamond_leggings", () -> new TofuArmorItem(TofuArmorMaterial.DIAMOND, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> TOFU_DIAMOND_BOOTS = ITEMS.register("tofu_diamond_boots", () -> new TofuArmorItem(TofuArmorMaterial.DIAMOND, ArmorItem.Type.BOOTS, (new Item.Properties())));

	public static final RegistryObject<ArmorItem> SCULK_BONE_HELMET = ITEMS.register("sculk_bone_helmet", () -> new TofuArmorItem(TofuArmorMaterial.SCULK_BONE, ArmorItem.Type.HELMET, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> SCULK_BONE_CHESTPLATE = ITEMS.register("sculk_bone_chestplate", () -> new TofuArmorItem(TofuArmorMaterial.SCULK_BONE, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> SCULK_BONE_LEGGINGS = ITEMS.register("sculk_bone_leggings", () -> new TofuArmorItem(TofuArmorMaterial.SCULK_BONE, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
	public static final RegistryObject<ArmorItem> SCULK_BONE_BOOTS = ITEMS.register("sculk_bone_boots", () -> new TofuArmorItem(TofuArmorMaterial.SCULK_BONE, ArmorItem.Type.BOOTS, (new Item.Properties())));


	public static final RegistryObject<Item> TOFU_SHIELD = ITEMS.register("tofu_shield", () -> new TofuShieldItem((new Item.Properties()).stacksTo(1).durability(420)));
	public static final RegistryObject<Item> REFLECT_TOFU_SHIELD = ITEMS.register("reflect_tofu_shield", () -> new ReflectTofuShieldItem((new Item.Properties()).stacksTo(1).durability(460)));

	public static final RegistryObject<Item> ROLLINGPIN = ITEMS.register("rollingpin", () -> new RollingPinItem((new Item.Properties())));

	public static final RegistryObject<Item> BUGLE = ITEMS.register("bugle", () -> new BugleItem((new Item.Properties()).stacksTo(1)));
	public static final RegistryObject<Item> TOFUSCOOP = ITEMS.register("tofuscoop", () -> new TofuScoopItem((new Item.Properties()).stacksTo(1).durability(264)));
	public static final RegistryObject<Item> TOFUSTICK = ITEMS.register("tofustick", () -> new TofuStickItem((new Item.Properties()).stacksTo(1).durability(264)));
	public static final RegistryObject<Item> FUKUMAME = ITEMS.register("fukumame", () -> new FukumameItem((new Item.Properties()).stacksTo(1).durability(64)));
	public static final RegistryObject<Item> NETHER_FUKUMAME = ITEMS.register("nether_fukumame", () -> new NetherFukumameItem((new Item.Properties()).stacksTo(1).durability(64)));
	public static final RegistryObject<Item> INFERNO_NETHER_FUKUMAME = ITEMS.register("inferno_nether_fukumame", () -> new InfernoNetherFukumameItem((new Item.Properties()).stacksTo(1).durability(64)));

	public static final RegistryObject<Item> SOUL_FUKUMAME = ITEMS.register("soul_fukumame", () -> new SoulFukumameItem((new Item.Properties()).stacksTo(1).durability(64).rarity(Rarity.UNCOMMON)));

	public static final RegistryObject<Item> ZUNDA_BOW = ITEMS.register("zunda_bow", () -> new ZundaBowItem((new Item.Properties()).durability(426)));

	public static final RegistryObject<Item> ZUNDA_ARROW = ITEMS.register("zunda_arrow", () -> new ZundaArrowItem((new Item.Properties())));

	public static final RegistryObject<Item> ZUNDAMUSHROOM_ON_A_STICK = ITEMS.register("zundamushroom_on_a_stick", () -> new ZundaMushroomOnAStickItem<>((new Item.Properties()).durability(25), TofuEntityTypes.TOFUPIG, 7));

	public static final RegistryObject<Item> TOFUGEM = ITEMS.register("tofugem", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> ADVANCE_TOFUGEM = ITEMS.register("adv_tofugem", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> TF_CAPACITOR = ITEMS.register("tf_capacitor", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> TF_COIL = ITEMS.register("tf_coil", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> TF_CIRCUIT = ITEMS.register("tf_circuit", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> TF_OSCILLATOR = ITEMS.register("tf_oscillator", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> TOFU_CORE = ITEMS.register("tofu_core", () -> new Item((new Item.Properties())));
	public static final RegistryObject<Item> TF_BATTERY = ITEMS.register("tf_battery", () -> new TFBatteryItem((new Item.Properties().stacksTo(1))));


	public static final RegistryObject<Item> TOFUNIAN_SPAWNEGG = ITEMS.register("tofunian_spawnegg", () -> new ForgeSpawnEggItem(TofuEntityTypes.TOFUNIAN, 15460584, 13291425, (new Item.Properties())));
	public static final RegistryObject<Item> TOFUCOW_SPAWNEGG = ITEMS.register("tofucow_spawnegg", () -> new ForgeSpawnEggItem(TofuEntityTypes.TOFUCOW, 15460584, 10724259, (new Item.Properties())));
	public static final RegistryObject<Item> TOFUPIG_SPAWNEGG = ITEMS.register("tofupig_spawnegg", () -> new ForgeSpawnEggItem(TofuEntityTypes.TOFUPIG, 15460584, 10066329, (new Item.Properties())));
	public static final RegistryObject<Item> TOFUSLIME_SPAWNEGG = ITEMS.register("tofuslime_spawnegg", () -> new ForgeSpawnEggItem(TofuEntityTypes.TOFUSLIME, 15460584, 3026478, (new Item.Properties())));
	public static final RegistryObject<Item> TOFUCREEPER_SPAWNEGG = ITEMS.register("tofucreeper_spawnegg", () -> new ForgeSpawnEggItem(TofuEntityTypes.TOFUCREEPER, 15460584, 3026478, (new Item.Properties())));

	public static final RegistryObject<Item> TOFUSPIDER_SPAWNEGG = ITEMS.register("tofuspider_spawnegg", () -> new ForgeSpawnEggItem(TofuEntityTypes.TOFUSPIDER, 15460584, 3026478, (new Item.Properties())));
	public static final RegistryObject<Item> TOFUFISH_SPAWNEGG = ITEMS.register("tofufish_spawnegg", () -> new ForgeSpawnEggItem(TofuEntityTypes.TOFUFISH, 15460584, 3026478, (new Item.Properties())));
	public static final RegistryObject<Item> TRAVELER_TOFUNIAN_SPAWNEGG = ITEMS.register("traveler_tofunian_spawnegg", () -> new ForgeSpawnEggItem(TofuEntityTypes.TRAVELER_TOFUNIAN, 15460584, 8763986, (new Item.Properties())));
	public static final RegistryObject<Item> TOFU_GANDLEM_SPAWNEGG = ITEMS.register("tofu_gandlem_spawnegg", () -> new ForgeSpawnEggItem(TofuEntityTypes.TOFU_GANDLEM, 0xDDD8B5, 0x92D4F9, (new Item.Properties())));
	public static final RegistryObject<Item> SHUDOFUSPIDER_SPAWNEGG = ITEMS.register("shudofuspider_spawnegg", () -> new ForgeSpawnEggItem(TofuEntityTypes.SHUDOFUSPIDER, 0xDDD8B5, 0x5FA039, (new Item.Properties())));
	public static final RegistryObject<Item> FUKUMAME_THOWER_SPAWNEGG = ITEMS.register("fukumame_thower_spawn_egg", () -> new ForgeSpawnEggItem(TofuEntityTypes.FUKUMAME_THOWER, 0xF2BA86, 0xAC452D, (new Item.Properties())));
	public static final RegistryObject<Item> ZUNDAMITE_SPAWNEGG = ITEMS.register("zundamite_spawn_egg", () -> new ForgeSpawnEggItem(TofuEntityTypes.ZUNDAMITE, 15460584, 0xA4D148, (new Item.Properties())));


	public static final RegistryObject<Item> NATTO_COBWEB = ITEMS.register("natto_cobweb", () -> new NattoCobWebItem((new Item.Properties())));

	//Tofu delight item
	public static final RegistryObject<Item> TOMATO_SOYBEAN_STEW = ITEMS.register("tomato_soybean_stew", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.TOMATO_SOYBEAN_STEW)));

	public static final RegistryObject<Item> BOTTLE_DASHI = ITEMS.register("bottle_dashi", () -> new Item((new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> BOTTLE_SOYOIL = ITEMS.register("bottle_soyoil", () -> new Item((new Item.Properties()).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final RegistryObject<Item> SOYSAUSE_RAMEN = ITEMS.register("soysause_ramen", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.SOYSAUSE_RAMEN)));

	public static final RegistryObject<Item> HELL_MABOU = ITEMS.register("hell_mabou", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.HELL_MABOU)));
	public static final RegistryObject<Item> RED_SOUP = ITEMS.register("red_soup", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.RED_SOUP)));
	public static final RegistryObject<Item> HELL_RED_SOUP = ITEMS.register("hell_red_soup", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.HELL_RED_SOUP)));

	public static final RegistryObject<Item> SUKIYAKI = ITEMS.register("sukiyaki", () -> new ReturnableDishItem(() -> Items.BOWL, (new Item.Properties()).stacksTo(16).craftRemainder(Items.BOWL).food(TofuFoods.SUKIYAKI)));
	public static final RegistryObject<Item> TOFU_BUNS_BURGER = ITEMS.register("tofu_buns_burger", () -> new Item((new Item.Properties()).food(TofuFoods.TOFU_BUNS_BURGER)));

	public static final RegistryObject<Item> STEAMED_BREAD = ITEMS.register("steamed_bread", () -> new Item((new Item.Properties()).food(TofuFoods.STEAMED_BREAD)));
	public static final RegistryObject<Item> STEAMED_BREAD_COCOA = ITEMS.register("steamed_bread_cocoa", () -> new Item((new Item.Properties()).food(TofuFoods.STEAMED_BREAD)));

	public static final RegistryObject<Item> KINAKO_BREAD = ITEMS.register("kinako_bread", () -> new Item((new Item.Properties()).food(TofuFoods.KINAKO_BREAD)));

	public static final RegistryObject<Item> EDAMAME_TEMPLA = ITEMS.register("edamame_templa", () -> new SaltFoodItem((new Item.Properties()).food(TofuFoods.EDAMAME_TEMPLA)));
	public static final RegistryObject<Item> NEGIMA = ITEMS.register("negima", () -> new ReturnableDishItem(() -> Items.STICK, (new Item.Properties()).food(TofuFoods.NEGIMA)));
	public static final RegistryObject<Item> SOY_KARAAGE = ITEMS.register("soy_karaage", () -> new Item((new Item.Properties()).food(TofuFoods.SOY_KARAAGE)));
	public static final RegistryObject<Item> SOYMEATDON = ITEMS.register("soymeatdon", () -> new Item((new Item.Properties()).food(TofuFoods.SOYMEATDON)));

	public static final RegistryObject<Item> TOFUNIAN_BANNER_PATTERN = ITEMS.register("tofunian_banner_pattern", () -> new BannerPatternItem(CustomTagGenerator.BannerPatternTagGenerator.TOFUNIAN_BANNER_PATTERN, new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

	public static final RegistryObject<Item> LEEK_BOAT = ITEMS.register("leek_boat", () -> new TofuBoatItem(false, TofuBoat.Type.LEEK, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> LEEK_GREEN_BOAT = ITEMS.register("leek_green_boat", () -> new TofuBoatItem(false, TofuBoat.Type.LEEK_GREEN, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> TOFU_STEM_BOAT = ITEMS.register("tofu_stem_boat", () -> new TofuBoatItem(false, TofuBoat.Type.TOFU_STEM, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> LEEK_CHEST_BOAT = ITEMS.register("leek_chest_boat", () -> new TofuBoatItem(true, TofuBoat.Type.LEEK, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> LEEK_GREEN_CHEST_BOAT = ITEMS.register("leek_green_chest_boat", () -> new TofuBoatItem(true, TofuBoat.Type.LEEK_GREEN, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> TOFU_STEM_CHEST_BOAT = ITEMS.register("tofu_stem_chest_boat", () -> new TofuBoatItem(true, TofuBoat.Type.TOFU_STEM, new Item.Properties().stacksTo(1)));


	private static RegistryObject<Item> register(String name, Supplier<Item> item) {
		return ITEMS.register(name, item);
	}


	private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
	private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

	private static final Component TOFU_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(TofuCraftReload.MODID, "tofu_upgrade"))).withStyle(TITLE_FORMAT);

	private static final Component TOFU_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(TofuCraftReload.MODID, "smithing_template.tofu_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component TOFU_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(TofuCraftReload.MODID, "smithing_template.tofu_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component TOFU_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(TofuCraftReload.MODID, "smithing_template.tofu_upgrade.base_slot_description")));
	private static final Component TOFU_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(TofuCraftReload.MODID, "smithing_template.tofu_upgrade.additions_slot_description")));

	private static final Component ZUNDA_BOW_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation(TofuCraftReload.MODID, "zunda_upgrade"))).withStyle(TITLE_FORMAT);

	private static final Component ZUNDA_BOW_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(TofuCraftReload.MODID, "smithing_template.zunda_bow_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component ZUNDA_BOW_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(TofuCraftReload.MODID, "smithing_template.zunda_bow_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
	private static final Component ZUNDA_BOW_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(TofuCraftReload.MODID, "smithing_template.zunda_bow_upgrade.base_slot_description")));
	private static final Component ZUNDA_BOW_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation(TofuCraftReload.MODID, "smithing_template.zunda_bow_upgrade.additions_slot_description")));


	private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
	private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
	private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
	private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
	private static final ResourceLocation EMPTY_SLOT_HOE = new ResourceLocation("item/empty_slot_hoe");
	private static final ResourceLocation EMPTY_SLOT_AXE = new ResourceLocation("item/empty_slot_axe");
	private static final ResourceLocation EMPTY_SLOT_SWORD = new ResourceLocation("item/empty_slot_sword");
	private static final ResourceLocation EMPTY_SLOT_SHOVEL = new ResourceLocation("item/empty_slot_shovel");
	private static final ResourceLocation EMPTY_SLOT_PICKAXE = new ResourceLocation("item/empty_slot_pickaxe");
	private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");
	private static final ResourceLocation EMPTY_SLOT_BOW = new ResourceLocation(TofuCraftReload.MODID, "item/empty_slot_bow");
	private static final ResourceLocation EMPTY_SLOT_ZUNDAMA = new ResourceLocation(TofuCraftReload.MODID, "item/empty_slot_zundama");


	private static List<ResourceLocation> createTofuUpgradeIconList() {
		return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
	}

	private static List<ResourceLocation> createTofuUpgradeMaterialList() {
		return List.of(EMPTY_SLOT_INGOT);
	}


	public static SmithingTemplateItem createTofuUpgradeTemplate() {
		return new SmithingTemplateItem(TOFU_UPGRADE_APPLIES_TO, TOFU_UPGRADE_INGREDIENTS, TOFU_UPGRADE, TOFU_UPGRADE_BASE_SLOT_DESCRIPTION, TOFU_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createTofuUpgradeIconList(), createTofuUpgradeMaterialList());
	}

	private static List<ResourceLocation> createZundaBowUpgradeIconList() {
		return List.of(EMPTY_SLOT_BOW);
	}

	private static List<ResourceLocation> createZundaBowUpgradeMaterialList() {
		return List.of(EMPTY_SLOT_ZUNDAMA);
	}


	public static SmithingTemplateItem createZundaBowUpgradeTemplate() {
		return new SmithingTemplateItem(ZUNDA_BOW_UPGRADE_APPLIES_TO, ZUNDA_BOW_UPGRADE_INGREDIENTS, ZUNDA_BOW_UPGRADE, ZUNDA_BOW_UPGRADE_BASE_SLOT_DESCRIPTION, ZUNDA_BOW_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createZundaBowUpgradeIconList(), createZundaBowUpgradeMaterialList());
	}


	public static void registerCompostableItem() {
		ComposterBlock.COMPOSTABLES.put(TofuItems.EDAMAME.get(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.BOILED_EDAMAME.get(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.SEEDS_SOYBEANS.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.SEEDS_SOYBEANS_NETHER.get(), 0.4F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.SEEDS_SOYBEANS_SOUL.get(), 0.4F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.LEEK.get(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.TOFUKINU.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.TOFUMOMEN.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.TOFUDRIED.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.TOFUFRIED.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.TOFUFRIED_POUCH.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.TOFUZUNDA.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.TOFUANNIN.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.TOFUEGG.get(), 0.35F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.TOFUMISO.get(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.SEEDS_CHILI.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.SEEDS_RICE.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.RICE.get(), 0.4F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.TOFUCOOKIE.get(), 0.85F);
		ComposterBlock.COMPOSTABLES.put(TofuBlocks.TOFUCAKE.get(), 1.0F);
		ComposterBlock.COMPOSTABLES.put(TofuBlocks.ZUNDATOFUCAKE.get(), 1.0F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.TOFUCOOKIE.get(), 0.85F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.ONIGIRI.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.ONIGIRI_SALT.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuItems.SALTYMELON.get(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuBlocks.SAPLING_APRICOT.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(TofuBlocks.SAPLING_TOFU.get().asItem(), 0.3F);
	}

	public static void registerDispenserItem() {

		DispenseItemBehavior dispenseitembehavior1 = new DefaultDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

			public ItemStack execute(BlockSource p_123561_, ItemStack p_123562_) {
				DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem) p_123562_.getItem();
				BlockPos blockpos = p_123561_.getPos().relative(p_123561_.getBlockState().getValue(DispenserBlock.FACING));
				Level level = p_123561_.getLevel();
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
				BlockPos blockpos = p_123561_.getPos().relative(p_123561_.getBlockState().getValue(DispenserBlock.FACING));
				FluidState fluidState = p_123561_.getLevel().getFluidState(blockpos);
				ItemStack result = RecipeHelper.getBitternResult(p_123561_.getLevel(), fluidState.getType(), p_123562_);
				if (result != null) {
					p_123561_.getLevel().setBlock(blockpos, Block.byItem(result.getItem()).defaultBlockState(), 11);
					p_123561_.getLevel().levelEvent(2001, blockpos, Block.getId(p_123561_.getLevel().getBlockState(blockpos)));
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
				BlockPos blockpos = p_123561_.getPos().relative(p_123561_.getBlockState().getValue(DispenserBlock.FACING));
				if (p_123561_.getLevel().getBlockState(blockpos).is(TofuTags.Blocks.SOFT_TOFU)) {
					ItemStack stack = new ItemStack(Item.BY_BLOCK.get(p_123561_.getLevel().getBlockState(blockpos).getBlock()));
					p_123561_.getLevel().levelEvent(2001, blockpos, Block.getId(p_123561_.getLevel().getBlockState(blockpos)));
					p_123561_.getLevel().removeBlock(blockpos, false);
					this.defaultDispenseItemBehavior.dispense(p_123561_, stack);
					if (p_123562_.hurt(1, p_123561_.getLevel().getRandom(), null)) {
						p_123562_.setCount(0);
					}
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
				p_123572_.getLevel().levelEvent(this.isSuccess() ? 1000 : 1001, p_123572_.getPos(), 0);
			}
		};
		DispenserBlock.registerBehavior(TOFUSCOOP.get(), dispenseitembehavior3);

		DispenserBlock.registerBehavior(FUKUMAME.get(), new DamageableProjectileDispenseBehavior() {
			protected Projectile getProjectile(Level p_123476_, Position p_123477_, ItemStack p_123478_) {
				return Util.make(new FukumameEntity(p_123476_, p_123477_.x(), p_123477_.y(), p_123477_.z()), (p_123474_) -> {
				});
			}

			protected int shootCount() {
				return 6;
			}
		});
		DispenserBlock.registerBehavior(NETHER_FUKUMAME.get(), new DamageableProjectileDispenseBehavior() {
			protected Projectile getProjectile(Level p_123476_, Position p_123477_, ItemStack p_123478_) {
				return Util.make(new NetherFukumameEntity(p_123476_, p_123477_.x(), p_123477_.y(), p_123477_.z()), (p_123474_) -> {
				});
			}

			protected int shootCount() {
				return 6;
			}
		});
		DispenserBlock.registerBehavior(INFERNO_NETHER_FUKUMAME.get(), new DamageableProjectileDispenseBehavior() {
			protected Projectile getProjectile(Level p_123476_, Position p_123477_, ItemStack p_123478_) {
				return Util.make(new NetherFukumameEntity(p_123476_, p_123477_.x(), p_123477_.y(), p_123477_.z()), (p_123474_) -> {
				});
			}

			public ItemStack execute(BlockSource p_123366_, ItemStack p_123367_) {
				Level level = p_123366_.getLevel();
				Position position = DispenserBlock.getDispensePosition(p_123366_);
				Direction direction = p_123366_.getBlockState().getValue(DispenserBlock.FACING);
				for (int i = 0; i < shootCount(); i++) {
					Projectile projectile = this.getProjectile(level, position, p_123367_);
					projectile.shoot((double) direction.getStepX(), (double) ((float) direction.getStepY() + 0.1F), (double) direction.getStepZ(), this.getPower(), this.getUncertainty());
					projectile.setSecondsOnFire(60);
					level.addFreshEntity(projectile);
				}
				if (p_123367_.hurt(1, p_123366_.getLevel().getRandom(), null)) {
					p_123367_.setCount(0);
				}
				return p_123367_;
			}

			protected int shootCount() {
				return 6;
			}
		});
		DispenserBlock.registerBehavior(SOUL_FUKUMAME.get(), new DamageableProjectileDispenseBehavior() {
			protected Projectile getProjectile(Level p_123476_, Position p_123477_, ItemStack p_123478_) {
				return Util.make(new SoulFukumameEntity(p_123476_, p_123477_.x(), p_123477_.y(), p_123477_.z()), (p_123474_) -> {
				});
			}

			protected int shootCount() {
				return 6;
			}
		});

		DispenserBlock.registerBehavior(ZUNDA_ARROW.get(), new AbstractProjectileDispenseBehavior() {
			protected Projectile getProjectile(Level p_123456_, Position p_123457_, ItemStack p_123458_) {
				AbstractArrow abstractarrow = new ZundaArrow(p_123456_, p_123457_.x(), p_123457_.y(), p_123457_.z());
				abstractarrow.pickup = AbstractArrow.Pickup.ALLOWED;
				return abstractarrow;
			}
		});
	}

	public static void registerAnimalFeed() {
		Ingredient newChickenFood = Ingredient.of(TofuItems.SEEDS_RICE.get(), TofuItems.SOYBEAN_PARCHED.get());
		Chicken.FOOD_ITEMS = new CompoundIngredient(Arrays.asList(Chicken.FOOD_ITEMS, newChickenFood)) {
		};
	}
}

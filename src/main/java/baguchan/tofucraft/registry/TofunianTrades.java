package baguchan.tofucraft.registry;

import baguchan.tofucraft.entity.Tofunian;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;


public class TofunianTrades {
	public static final Int2ObjectMap<VillagerTrades.ItemListing[]> TRAVELER_TOFUNIAN_TRADE = getAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(Blocks.WHITE_WOOL, 8, 4, 5), new ZundaRubyForItemsTrade(Blocks.GLASS, 9, 4, 5), new ZundaRubyForItemsTrade(Items.WHEAT, 16, 3, 5), new ZundaRubyForItemsTrade(TofuItems.SEEDS_SOYBEANS.get(), 16, 4, 5)
					, new ZundaRubyForItemsTrade(Items.REDSTONE, 9, 2, 5), new ZundaRubyForItemsTrade(Items.SUGAR, 14, 3, 5), new ZundaRubyForItemsTrade(Items.POTATO, 12, 4, 5), new ZundaRubyForItemsTrade(Items.PUMPKIN, 4, 4, 5), new ZundaRubyForItemsTrade(Items.CARROT, 18, 3, 5)}
			, 2, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFUNIAN_SOY_CHOCOLATE.get(), 1, 9, 5, 5), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_HONEY.get(), 1, 3, 6, 5), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_PUMPKIN.get(), 1, 3, 6, 5), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_PUDDING.get(), 1, 3, 6, 5), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_APPLE.get(), 1, 3, 6, 5), new ItemsForZundaRubyTrade(TofuItems.SOYMILK.get(), 1, 3, 6, 5)
					, new ItemsForZundaRubyTrade(TofuItems.ZUNDA.get(), 1, 6, 4, 5), new ItemsForZundaRubyTrade(TofuItems.ZUNDA_ARROW.get(), 1, 8, 8, 5)
					, new ItemsForZundaRubyTrade(TofuItems.BOTTLE_SOYOIL.get(), 1, 5, 2, 5)
					, new ItemsForZundaRubyTrade(TofuItems.BOTTLE_SOYSAUSE.get(), 1, 3, 3, 5)}));

	public static final Map<Tofunian.Roles, Int2ObjectMap<VillagerTrades.ItemListing[]>> TOFUNIAN_TRADE = Util.make(Maps.newHashMap(), p_221237_0_ -> {
		p_221237_0_.put(Tofunian.Roles.TOFUCOOK, getAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.SEEDS_SOYBEANS.get(), 16, 14, 1), new ItemsForZundaRubyTrade(TofuItems.TOFUGRILLED.get(), 1, 9, 4, 3)}
				, 2, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.TOFUKINU.get(), 28, 9, 6, 0.075F), new ItemsForZundaRubyTrade(TofuItems.KINAKO_MANJU.get(), 1, 6, 8, 6)}
				, 3, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFUCOOKIE.get(), 1, 12, 6, 13), new ItemsForZundaRubyTrade(TofuItems.TOFUMISO.get(), 1, 6, 6, 13), new ItemsForZundaRubyTrade(TofuItems.TOFUSESAME.get(), 6, 6, 13)}
				, 4, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.YUBA.get(), 18, 12, 15, 0.075F), new ZundaRubyForItemsTrade(TofuBlocks.LEEK_STEM.get(), 16, 8, 15), new FairTrade(TofuItems.APRICOTJERRY_BOTTLE.get(), TofuItems.APRICOT.get(), 16, 2)}
				, 5, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuBlocks.TOFUCAKE.get().asItem(), 3, 1, 20), new ItemsForZundaRubyTrade(TofuItems.TOFUSTRAWBERRY.get().asItem(), 2, 6, 20)})));
		p_221237_0_.put(Tofunian.Roles.TOFUSMITH, getAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.TOFUISHI.get(), 16, 10, 2), new ItemsForZundaRubyTrade(TofuItems.TOFU_SOLID_SHOVEL.get(), 1, 1, 3)}
				, 2, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.TOFUMETAL.get(), 8, 8, 6, 0.1F), new ItemsForZundaRubyTrade(TofuItems.TOFU_SHIELD.get(), 4, 1, 7)}
				, 3, new VillagerTrades.ItemListing[]{new EnchantedItemsForZundaRubyTrade(TofuItems.TOFU_METAL_SWORD.get().getDefaultInstance(), 6, 1, 4, 10, 0.2F), new EnchantedItemsForZundaRubyTrade(TofuItems.TOFU_METAL_AXE.get().getDefaultInstance(), 6, 1, 4, 10, 0.2F), new EnchantedItemsForZundaRubyTrade(TofuItems.TOFU_METAL_PICKAXE.get().getDefaultInstance(), 6, 1, 4, 10, 0.2F), new EnchantedItemsForZundaRubyTrade(TofuItems.TOFU_METAL_SHOVEL.get().getDefaultInstance(), 6, 1, 4, 10, 0.2F)}
				, 4, new VillagerTrades.ItemListing[]{new EnchantedItemsForZundaRubyTrade(TofuItems.TOFU_METAL_BOOTS.get().getDefaultInstance(), 4, 1, 4, 18, 0.2F), new EnchantedItemsForZundaRubyTrade(TofuItems.TOFU_METAL_CHESTPLATE.get().getDefaultInstance(), 7, 1, 4, 18, 0.2F), new EnchantedItemsForZundaRubyTrade(TofuItems.TOFU_METAL_LEGGINGS.get().getDefaultInstance(), 6, 1, 4, 18, 0.2F), new EnchantedItemsForZundaRubyTrade(TofuItems.TOFU_METAL_HELMET.get().getDefaultInstance(), 5, 1, 4, 18, 0.2F)}
				, 5, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFU_UPGRADE_SMITHING_TEMPLATE.get().getDefaultInstance(), 24, 1, 4, 30, 0.2F), new ItemsForZundaRubyTrade(TofuItems.ZUNDA_ARROW.get().getDefaultInstance(), 3, 16, 12, 30, 0.2F)})));
		p_221237_0_.put(Tofunian.Roles.SOYWORKER, getAsIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(Blocks.GLASS, 10, 6, 2), new ZundaRubyForItemsTrade(TofuItems.LEEK.get(), 26, 6, 2)}
				, 2, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.TOFU_HAMBURG.get(), 1, 7, 6, 7), new ItemsForZundaRubyTrade(TofuItems.COOKED_TOFU_FISH.get(), 1, 9, 5, 7)}
				, 3, new VillagerTrades.ItemListing[]{new ZundaRubyForItemsTrade(TofuItems.BITTERN_BOTTLE.get(), 4, 8, 15), new ItemsForZundaRubyTrade(TofuItems.SOY_CHOCOLATE.get(), 1, 6, 8, 15)}
				, 4, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.SOYMILK.get(), 1, 3, 6, 20), new ItemsForZundaRubyTrade(TofuItems.TOFUNIAN_SOY_CHOCOLATE.get(), 1, 6, 8, 20)}
				, 5, new VillagerTrades.ItemListing[]{new ItemsForZundaRubyTrade(TofuItems.SOYMILK_ANNIN.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_APPLE.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_FRUITS.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_HONEY.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_KINAKO.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_PUMPKIN.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_RAMUNE.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_SAKURA.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_PUDDING.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_STRAWBERRY.get(), 1, 3, 6, 30), new ItemsForZundaRubyTrade(TofuItems.SOYMILK_TEA.get(), 1, 3, 6, 30)})));
	});

	private static Int2ObjectMap<VillagerTrades.ItemListing[]> getAsIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_221238_0_) {
		return new Int2ObjectOpenHashMap<>(p_221238_0_);
	}

	static class ZundaRubyForItemsTrade implements VillagerTrades.ItemListing {
		private final Item tradeItem;

		private final int count;

		private final int maxUses;

		private final int xpValue;

		private final float priceMultiplier;

		public ZundaRubyForItemsTrade(ItemLike tradeItemIn, int countIn, int maxUsesIn, int xpValueIn) {
			this.tradeItem = tradeItemIn.asItem();
			this.count = countIn;
			this.maxUses = maxUsesIn;
			this.xpValue = xpValueIn;
			this.priceMultiplier = 0.05F;
		}

		public ZundaRubyForItemsTrade(ItemLike tradeItemIn, int countIn, int maxUsesIn, int xpValueIn, float priceMultiplierIn) {
			this.tradeItem = tradeItemIn.asItem();
			this.count = countIn;
			this.maxUses = maxUsesIn;
			this.xpValue = xpValueIn;
			this.priceMultiplier = priceMultiplierIn;
		}

		public MerchantOffer getOffer(Entity trader, RandomSource rand) {
			ItemCost itemstack = new ItemCost(this.tradeItem, this.count);
			return new MerchantOffer(itemstack, new ItemStack(TofuItems.ZUNDARUBY.get()), this.maxUses, this.xpValue, this.priceMultiplier);
		}
	}

	static class EnchantedItemsForZundaRubyTrade implements VillagerTrades.ItemListing {
		private final ItemStack sellingItem;

		private final int rubyCount;

		private final int sellingItemCount;

		private final int maxUses;

		private final int xpValue;

		private final float priceMultiplier;

		public EnchantedItemsForZundaRubyTrade(Block sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
			this(new ItemStack(sellingItem), rubyCount, sellingItemCount, maxUses, xpValue);
		}

		public EnchantedItemsForZundaRubyTrade(Item sellingItem, int rubyCount, int sellingItemCount, int xpValue) {
			this(new ItemStack(sellingItem), rubyCount, sellingItemCount, 12, xpValue);
		}

		public EnchantedItemsForZundaRubyTrade(Item sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
			this(new ItemStack(sellingItem), rubyCount, sellingItemCount, maxUses, xpValue);
		}

		public EnchantedItemsForZundaRubyTrade(ItemStack sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
			this(sellingItem, rubyCount, sellingItemCount, maxUses, xpValue, 0.05F);
		}

		public EnchantedItemsForZundaRubyTrade(ItemStack sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue, float priceMultiplier) {
			this.sellingItem = sellingItem;
			this.rubyCount = rubyCount;
			this.sellingItemCount = sellingItemCount;
			this.maxUses = maxUses;
			this.xpValue = xpValue;
			this.priceMultiplier = priceMultiplier;
		}

		public MerchantOffer getOffer(Entity trader, RandomSource rand) {
			int i = 2 + rand.nextInt(5);
			int j = Math.min(this.rubyCount + i, 64);
			ItemStack stack = new ItemStack(this.sellingItem.getItem(), 1);
			RegistryAccess registryaccess = trader.level().registryAccess();

			Optional<HolderSet.Named<Enchantment>> optional = registryaccess.registryOrThrow(Registries.ENCHANTMENT)
					.getTag(EnchantmentTags.ON_TRADED_EQUIPMENT);
			EnchantmentHelper.enchantItem(rand, stack, i, registryaccess, optional);
			return new MerchantOffer(new ItemCost(TofuItems.ZUNDARUBY.get(), j), stack, this.maxUses, this.xpValue, this.priceMultiplier);
		}
	}

	static class ItemsForZundaRubyTrade implements VillagerTrades.ItemListing {
		private final ItemStack sellingItem;

		private final int rubyCount;

		private final int sellingItemCount;

		private final int maxUses;

		private final int xpValue;

		private final float priceMultiplier;

		public ItemsForZundaRubyTrade(Block sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
			this(new ItemStack(sellingItem), rubyCount, sellingItemCount, maxUses, xpValue);
		}

		public ItemsForZundaRubyTrade(Item sellingItem, int rubyCount, int sellingItemCount, int xpValue) {
			this(new ItemStack(sellingItem), rubyCount, sellingItemCount, 12, xpValue);
		}

		public ItemsForZundaRubyTrade(Item sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
			this(new ItemStack(sellingItem), rubyCount, sellingItemCount, maxUses, xpValue);
		}

		public ItemsForZundaRubyTrade(ItemStack sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue) {
			this(sellingItem, rubyCount, sellingItemCount, maxUses, xpValue, 0.05F);
		}

		public ItemsForZundaRubyTrade(ItemStack sellingItem, int rubyCount, int sellingItemCount, int maxUses, int xpValue, float priceMultiplier) {
			this.sellingItem = sellingItem;
			this.rubyCount = rubyCount;
			this.sellingItemCount = sellingItemCount;
			this.maxUses = maxUses;
			this.xpValue = xpValue;
			this.priceMultiplier = priceMultiplier;
		}

		public MerchantOffer getOffer(Entity trader, RandomSource rand) {
			return new MerchantOffer(new ItemCost(TofuItems.ZUNDARUBY.get(), this.rubyCount), new ItemStack(this.sellingItem.getItem(), this.sellingItemCount), this.maxUses, this.xpValue, this.priceMultiplier);
		}
	}

	static class FairTrade implements VillagerTrades.ItemListing {
		private final Item sellingItem;
		private final Item targetItem;
		private final int maxUses;
		private final int xpValue;

		public FairTrade(Item sellingItem, Item targetItem, int maxUses, int xpValue) {
			this.sellingItem = sellingItem;
			this.targetItem = targetItem;
			this.maxUses = maxUses;
			this.xpValue = xpValue;
		}

		@Nullable
		@Override
		public MerchantOffer getOffer(@NotNull Entity entity, @NotNull RandomSource randomSource) {
			return new MerchantOffer(new ItemCost(targetItem), new ItemStack(sellingItem), maxUses, xpValue, 0.05f);
		}
	}
}

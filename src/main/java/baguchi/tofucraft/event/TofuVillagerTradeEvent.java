package baguchi.tofucraft.event;

import baguchi.tofucraft.TofuCraftReload;
import baguchi.tofucraft.registry.TofuBlocks;
import baguchi.tofucraft.registry.TofuItems;
import baguchi.tofucraft.registry.TofuProfessions;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.List;

@EventBusSubscriber(modid = TofuCraftReload.MODID)
public class TofuVillagerTradeEvent {
	/*@SubscribeEvent
	public static void tofunianTradeTest(TofunianTradeEvent event) {
		List<VillagerTrades.ItemListing> trades = event.getTrades().get(1);
		if (event.getType() == Tofunian.Roles.TOFUCOOK) {
			trades.add(new ItemsForEmeralds(TofuItems.BOILED_EDAMAME.get(), 1, 12, 8, 6));
		}
	}*/

	@SubscribeEvent
	public static void wanderTradeEvent(VillagerTradesEvent event) {
		List<VillagerTrades.ItemListing> trades = event.getTrades().get(1);
		List<VillagerTrades.ItemListing> trades2 = event.getTrades().get(2);
		List<VillagerTrades.ItemListing> trades3 = event.getTrades().get(3);
		List<VillagerTrades.ItemListing> trades4 = event.getTrades().get(4);
		List<VillagerTrades.ItemListing> trades5 = event.getTrades().get(5);
		if (event.getType() == VillagerProfession.FARMER) {
			trades2.add(new ItemsForEmeralds(TofuItems.BOILED_EDAMAME.get(), 1, 12, 8, 6));

		}

		if (event.getType() == TofuProfessions.TOFU_CRAFTSMAN.get()) {
			trades.add(new EmeraldForItems(TofuItems.SEEDS_SOYBEANS.get(), 16, 12, 1));
			trades.add(new ItemsForEmeralds(TofuItems.TOFUGRILLED.get(), 1, 9, 8, 2));

			trades2.add(new ItemsForEmeralds(TofuItems.SOYMILK.get(), 1, 3, 8, 7));
			trades2.add(new EmeraldForItems(Items.GLASS_BOTTLE, 6, 8, 6));

			trades3.add(new EmeraldForItems(TofuItems.SALT.get(), 17, 10, 12, 0.1F));
			trades3.add(new ItemsForEmeralds(new ItemStack(TofuItems.TOFUCOOKIE.get()), 2, 8, 12, 12, 0.05F));
			trades3.add(new EmeraldForItems(TofuItems.NATTO.get(), 8, 14, 12, 0.1F));
			trades3.add(new ItemsForEmeralds(new ItemStack(TofuItems.BOTTLE_SOYOIL.get()), 3, 5, 10, 10, 0.1F));
			trades3.add(new ItemsForEmeralds(new ItemStack(TofuItems.YUDOFU.get()), 2, 6, 10, 10, 0.08F));

			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_ANNIN.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_APPLE.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_FRUITS.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_HONEY.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_KINAKO.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_PUDDING.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_PUMPKIN.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_RAMUNE.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_SAKURA.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_STRAWBERRY.get(), 1, 3, 6, 16));
			trades4.add(new ItemsForEmeralds(TofuItems.SOYMILK_TEA.get(), 1, 3, 6, 16));

			trades5.add(new ItemsForEmeralds(new ItemStack(TofuBlocks.MORIJIO.get()), 10, 2, 6, 20, 0.1F));
			trades5.add(new ItemsForEmeralds(new ItemStack(TofuItems.BOTTLE_SOYSAUSE.get()), 2, 6, 8, 20, 0.1F));
			trades5.add(new ItemsForEmeralds(new ItemStack(TofuItems.OKARA_DONUT.get()), 3, 8, 9, 20, 0.1F));
		}
	}

	private static Int2ObjectMap<VillagerTrades.ItemListing[]> gatAsIntMap(ImmutableMap<Integer, VillagerTrades.ItemListing[]> p_221238_0_) {
		return new Int2ObjectOpenHashMap<>(p_221238_0_);
	}

	static class EmeraldForItems implements VillagerTrades.ItemListing {
		private final Item item;
		private final int cost;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;

		public EmeraldForItems(ItemLike p_35657_, int p_35658_, int p_35659_, int p_35660_) {
			this.item = p_35657_.asItem();
			this.cost = p_35658_;
			this.maxUses = p_35659_;
			this.villagerXp = p_35660_;
			this.priceMultiplier = 0.05F;
		}

		public EmeraldForItems(ItemLike p_35657_, int p_35658_, int p_35659_, int p_35660_, float priceMultiplier) {
			this.item = p_35657_.asItem();
			this.cost = p_35658_;
			this.maxUses = p_35659_;
			this.villagerXp = p_35660_;
			this.priceMultiplier = priceMultiplier;
		}

		public MerchantOffer getOffer(Entity p_35662_, RandomSource p_35663_) {
			ItemCost itemstack = new ItemCost(this.item, this.cost);
			return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}

	static class ItemsForEmeralds implements VillagerTrades.ItemListing {
		private final ItemStack itemStack;
		private final int emeraldCost;
		private final int numberOfItems;
		private final int maxUses;
		private final int villagerXp;
		private final float priceMultiplier;

		public ItemsForEmeralds(Block p_35765_, int p_35766_, int p_35767_, int p_35768_, int p_35769_) {
			this(new ItemStack(p_35765_), p_35766_, p_35767_, p_35768_, p_35769_);
		}

		public ItemsForEmeralds(Item p_35741_, int p_35742_, int p_35743_, int p_35744_) {
			this(new ItemStack(p_35741_), p_35742_, p_35743_, 12, p_35744_);
		}

		public ItemsForEmeralds(Item p_35746_, int p_35747_, int p_35748_, int p_35749_, int p_35750_) {
			this(new ItemStack(p_35746_), p_35747_, p_35748_, p_35749_, p_35750_);
		}

		public ItemsForEmeralds(ItemStack p_35752_, int p_35753_, int p_35754_, int p_35755_, int p_35756_) {
			this(p_35752_, p_35753_, p_35754_, p_35755_, p_35756_, 0.05F);
		}

		public ItemsForEmeralds(ItemStack p_35758_, int p_35759_, int p_35760_, int p_35761_, int p_35762_, float p_35763_) {
			this.itemStack = p_35758_;
			this.emeraldCost = p_35759_;
			this.numberOfItems = p_35760_;
			this.maxUses = p_35761_;
			this.villagerXp = p_35762_;
			this.priceMultiplier = p_35763_;
		}

		public MerchantOffer getOffer(Entity p_35771_, RandomSource p_35772_) {
			return new MerchantOffer(new ItemCost(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
		}
	}
}

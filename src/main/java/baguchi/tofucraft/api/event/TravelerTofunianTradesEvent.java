package baguchi.tofucraft.api.event;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.neoforged.bus.api.Event;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

public class TravelerTofunianTradesEvent extends Event {
	protected List<VillagerTrades.ItemListing> buy;
	protected List<VillagerTrades.ItemListing> sale;
	private final HolderLookup.Provider registries;

	@ApiStatus.Internal
	public TravelerTofunianTradesEvent(List<VillagerTrades.ItemListing> buy, List<VillagerTrades.ItemListing> sale, HolderLookup.Provider registries) {
		this.buy = buy;
		this.sale = sale;
		this.registries = registries;
	}

	public List<VillagerTrades.ItemListing> getBuyTrades() {
		return buy;
	}

	public List<VillagerTrades.ItemListing> getSaleTrades() {
		return sale;
	}

	public HolderLookup.Provider getRegistries() {
		return registries;
	}
}

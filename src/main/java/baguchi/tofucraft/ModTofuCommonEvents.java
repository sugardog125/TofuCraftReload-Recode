package baguchi.tofucraft;

import baguchi.tofucraft.registry.TofuBlockEntitys;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.wrapper.ForwardingItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

@EventBusSubscriber(modid = TofuCraftReload.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModTofuCommonEvents {

	@SubscribeEvent
	private static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, // capability to register for
				TofuBlockEntitys.SALT_FURNACE.get(), // block entity type to register for
				(myBlockEntity, side) -> {
					return myBlockEntity.waterTank;
				});

		event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, // capability to register for
				TofuBlockEntitys.TF_STORAGE.get(), // block entity type to register for
				(myBlockEntity, side) -> {
					return myBlockEntity.getTank();
				});

		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, TofuBlockEntitys.SALT_FURNACE.get(), (blockEntity, side) -> {
			// Return a wrapper that gets re-evaluated every time it is accessed
			// Invalidation is taken care of by the patches to ComposterBlock

			// Note: re-query the block state everytime instead of using `state` because the state can change at any time!
			if (side == null) {
				return new ForwardingItemHandler(() -> new InvWrapper(blockEntity));
			} else {
				return new ForwardingItemHandler(() -> new SidedInvWrapper(blockEntity, side));
			}
		});
	}
}

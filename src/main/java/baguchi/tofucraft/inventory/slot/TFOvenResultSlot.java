package baguchi.tofucraft.inventory.slot;

import baguchi.tofucraft.blockentity.tfenergy.TFOvenBlockEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class TFOvenResultSlot extends Slot {
	private final Player player;
	private int removeCount;

	public TFOvenResultSlot(Player p_39542_, Container p_39543_, int p_39544_, int p_39545_, int p_39546_) {
		super(p_39543_, p_39544_, p_39545_, p_39546_);
		this.player = p_39542_;
	}

	@Override
	public boolean mayPlace(ItemStack p_39553_) {
		return false;
	}

	@Override
	public ItemStack remove(int p_39548_) {
		if (this.hasItem()) {
			this.removeCount += Math.min(p_39548_, this.getItem().getCount());
		}

		return super.remove(p_39548_);
	}

	@Override
	public void onTake(Player p_150563_, ItemStack p_150564_) {
		this.checkTakeAchievements(p_150564_);
		super.onTake(p_150563_, p_150564_);
	}

	@Override
	protected void onQuickCraft(ItemStack p_39555_, int p_39556_) {
		this.removeCount += p_39556_;
		this.checkTakeAchievements(p_39555_);
	}

	@Override
	protected void checkTakeAchievements(ItemStack p_39558_) {
		p_39558_.onCraftedBy(this.player.level(), this.player, this.removeCount);
		Player playerx = this.player;
		if (playerx instanceof ServerPlayer serverplayer) {
			Container container = this.container;
			if (container instanceof TFOvenBlockEntity abstractfurnaceblockentity) {
				abstractfurnaceblockentity.awardUsedRecipesAndPopExperience(serverplayer);
			}
		}
		if (this.removeCount != 0) {
			net.neoforged.neoforge.event.EventHooks.firePlayerSmeltedEvent(this.player, p_39558_, this.removeCount);
		}
		this.removeCount = 0;
	}
}

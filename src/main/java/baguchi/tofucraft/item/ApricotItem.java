package baguchi.tofucraft.item;

import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ApricotItem extends Item {
	public ApricotItem(Properties food) {
		super(food);
	}

	@Override
	public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
		var resultItem = super.finishUsingItem(itemStack, level, livingEntity);

		if (livingEntity instanceof Player player && !player.getAbilities().instabuild) {
			if (itemStack.isEmpty()) {
				resultItem = new ItemStack(TofuItems.APRICOTSEED.get());
			} else {
				ItemStack itemstack = new ItemStack(TofuItems.APRICOTSEED.get());
				if (!player.getInventory().add(itemstack)) {
					player.drop(itemstack, false);
				}
			}
		}
		return resultItem;
	}
}

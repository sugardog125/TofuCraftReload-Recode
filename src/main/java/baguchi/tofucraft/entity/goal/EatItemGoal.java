package baguchi.tofucraft.entity.goal;

import baguchi.tofucraft.entity.Tofunian;
import baguchi.tofucraft.registry.TofuLootTables;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class EatItemGoal<T extends Tofunian> extends Goal {
	private final T mob;
	private final Predicate<? super T> canUseSelector;

	public EatItemGoal(T p_25972_, @Nullable SoundEvent p_25974_, Predicate<? super T> p_25975_) {
		this.mob = p_25972_;
		this.canUseSelector = p_25975_;
	}

	public boolean canUse() {
		if (this.mob.isBaby()) {
			return false;
		}
		if (!this.mob.getMainHandItem().isEmpty() && this.mob.getMainHandItem().get(DataComponents.FOOD) != null) {
			return true;
		}
		if (this.canUseSelector.test(this.mob)) {
			return this.mob.eatFood();
		}

		return false;
	}

	public boolean canContinueToUse() {
		return this.mob.isUsingItem();
	}

	public void start() {
		this.mob.setAction(Tofunian.Actions.EAT);
		this.mob.startUsingItem(InteractionHand.MAIN_HAND);
	}

	public void stop() {
		this.mob.setAction(Tofunian.Actions.NORMAL);

		this.mob.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
	}

	private List<ItemStack> getItemToThrow(Tofunian p_23010_) {
		LootTable loottable = p_23010_.level().getServer().reloadableRegistries().getLootTable(TofuLootTables.TOFUNIAN_GIFT_LOOT_TABLE);
		LootParams lootparams = (new LootParams.Builder((ServerLevel) p_23010_.level())).withParameter(LootContextParams.ORIGIN, p_23010_.position()).withParameter(LootContextParams.THIS_ENTITY, p_23010_).create(LootContextParamSets.GIFT);
		return loottable.getRandomItems(lootparams);
	}
}
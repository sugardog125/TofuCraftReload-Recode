package baguchi.tofucraft.inventory;

import baguchi.tofucraft.inventory.slot.TFOvenResultSlot;
import baguchi.tofucraft.registry.TofuMenus;
import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipePropertySet;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

import java.util.List;

public class TFOvenMenu extends RecipeBookMenu {
	public static final int INGREDIENT_SLOT = 0;
	public static final int FUEL_SLOT = 1;
	public static final int RESULT_SLOT = 2;
	public static final int SLOT_COUNT = 3;
	public static final int DATA_COUNT = 4;
	private static final int INV_SLOT_START = 3;
	private static final int INV_SLOT_END = 30;
	private static final int USE_ROW_SLOT_START = 30;
	private static final int USE_ROW_SLOT_END = 39;
	final Container container;
	private final ContainerData data;
	protected final Level level;
	private final RecipeType<? extends AbstractCookingRecipe> recipeType;
	private final RecipePropertySet acceptedInputs;
	private final RecipeBookType recipeBookType;

	public TFOvenMenu(
			int p_38969_,
			Inventory p_38970_
	) {
		this(p_38969_, p_38970_, new SimpleContainer(2), new SimpleContainerData(3));
	}

	public TFOvenMenu(
			int p_38963_,
			Inventory p_38964_,
			Container p_379971_,
			ContainerData p_379737_
	) {
		super(TofuMenus.TF_OVEN.get(), p_38963_);
		this.recipeType = RecipeType.SMELTING;
		this.recipeBookType = RecipeBookType.FURNACE;
		checkContainerSize(p_379971_, 2);
		checkContainerDataCount(p_379737_, 3);
		this.container = p_379971_;
		this.data = p_379737_;
		this.level = p_38964_.player.level();
		this.acceptedInputs = this.level.recipeAccess().propertySet(RecipePropertySet.FURNACE_INPUT);
		this.addSlot(new Slot(p_379971_, 0, 39, 15));
		this.addSlot(new TFOvenResultSlot(p_38964_.player, p_379971_, 1, 109, 15));
		this.addStandardInventorySlots(p_38964_, 7, 83);
		this.addDataSlots(p_379737_);
	}

	@Override
	public void fillCraftSlotsStackedContents(StackedItemContents p_363436_) {
		if (this.container instanceof StackedContentsCompatible) {
			((StackedContentsCompatible) this.container).fillStackedContents(p_363436_);
		}
	}

	public Slot getResultSlot() {
		return this.slots.get(2);
	}

	/**
	 * Determines whether supplied player can use this container
	 */
	@Override
	public boolean stillValid(Player player) {
		return this.container.stillValid(player);
	}

	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player inventory and the other inventory(s).
	 */
	@Override
	public ItemStack quickMoveStack(Player p_38986_, int p_38987_) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(p_38987_);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (p_38987_ != 1 && p_38987_ != 0) {
				if (this.canSmelt(itemstack1)) {
					if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (p_38987_ >= 2 && p_38987_ < 29) {
					if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
						return ItemStack.EMPTY;
					}
				} else if (p_38987_ >= 29 && p_38987_ < 38 && !this.moveItemStackTo(itemstack1, 2, 29, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 2, 38, false)) {
				return ItemStack.EMPTY;
			}
			slot.onQuickCraft(itemstack1, itemstack);
			if (itemstack1.isEmpty()) {
				slot.setByPlayer(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(p_38986_, itemstack1);
		}

		return itemstack;
	}

	public float getTFForce() {
		int i = this.data.get(1);
		int j = this.data.get(2);
		return j != 0 && i != 0 ? Mth.clamp((float) i / (float) j, 0.0F, 1.0F) : 0.0F;
	}

	public float getProgress() {
		int i = 0;
		if (i == 0) {
			i = 200;
		}

		return Mth.clamp((float) this.data.get(0) / (float) i, 0.0F, 1.0F);
	}

	protected boolean canSmelt(ItemStack stack) {
		return this.acceptedInputs.test(stack);
	}


	@Override
	public RecipeBookType getRecipeBookType() {
		return this.recipeBookType;
	}

	@Override
	public RecipeBookMenu.PostPlaceAction handlePlacement(
			boolean p_361547_, boolean p_363944_, RecipeHolder<?> p_360938_, final ServerLevel p_379475_, Inventory p_361954_
	) {
		final List<Slot> list = List.of(this.getSlot(0), this.getSlot(2));
		return ServerPlaceRecipe.placeRecipe(new ServerPlaceRecipe.CraftingMenuAccess<AbstractCookingRecipe>() {
			@Override
			public void fillCraftSlotsStackedContents(StackedItemContents p_361824_) {
				TFOvenMenu.this.fillCraftSlotsStackedContents(p_361824_);
			}

			@Override
			public void clearCraftingContent() {
				list.forEach(p_362814_ -> p_362814_.set(ItemStack.EMPTY));
			}

			@Override
			public boolean recipeMatches(RecipeHolder<AbstractCookingRecipe> p_361040_) {
				return p_361040_.value().matches(new SingleRecipeInput(TFOvenMenu.this.container.getItem(0)), p_379475_);
			}
		}, 1, 1, List.of(this.getSlot(0)), list, p_361954_, (RecipeHolder<AbstractCookingRecipe>) p_360938_, p_361547_, p_363944_);
	}
}

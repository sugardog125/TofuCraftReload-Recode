package baguchan.tofucraft.compat.jei;

import baguchan.tofucraft.TofuCraftReload;
import baguchan.tofucraft.recipe.TofuWorkStationRecipe;
import baguchan.tofucraft.registry.TofuBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class TofuWorkStationCategory implements IRecipeCategory<TofuWorkStationRecipe> {

	public static final ResourceLocation UID = new ResourceLocation(TofuCraftReload.MODID, "tofu_work_station");
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	public TofuWorkStationCategory(IGuiHelper helper) {
		title = Component.translatable("tofucraft.jei.harden");
		ResourceLocation backgroundImage = new ResourceLocation(TofuCraftReload.MODID, "textures/gui/tofu_work_station.png");
		background = helper.createDrawable(backgroundImage, 19, 16, 166 - 19, 70 - 16);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(TofuBlocks.TOFU_WORK_STATION.get()));
	}

	@Override
	public ResourceLocation getRegistryName(TofuWorkStationRecipe recipe) {
		return UID;
	}

	@Override
	public RecipeType<TofuWorkStationRecipe> getRecipeType() {
		return JEIPlugin.TOFU_WORK_STATION_JEI_TYPE;
	}

	@Override
	public Component getTitle() {
		return title;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, TofuWorkStationRecipe recipe, IFocusGroup focuses) {
		Ingredient recipeIngredients = recipe.getIngredient();

		builder.addSlot(RecipeIngredientRole.INPUT, 20 - 19, 33 - 18 - 16)
				.addIngredients(recipeIngredients);
		builder.addSlot(RecipeIngredientRole.INPUT, 20 - 19, 33 - 16)
				.addIngredients(recipe.getBaseIngredient());
		builder.addSlot(RecipeIngredientRole.INPUT, 20 - 19, 33 + 18 - 16)
				.addIngredients(recipe.getSubIngredient());

		builder.addSlot(RecipeIngredientRole.OUTPUT, 143 - 19, 33 - 16).addItemStack(recipe.getResult());

	}
}



package baguchi.tofucraft.utils;

import baguchi.tofucraft.recipe.BitternRecipe;
import baguchi.tofucraft.recipe.HardenRecipe;
import baguchi.tofucraft.registry.TofuRecipes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.Nullable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecipeHelper {
	/*
	 * This method use in not condition type tofu recipe(like ishi tofu and metal tofu)
	 */
	@Nullable
	public static ItemStack getTofu(ServerLevel serverLevel, Block block) {

		final RecipeManager manager = serverLevel.recipeAccess();

		if (block.asItem() != null) {
			Stream<RecipeHolder<?>> tofuRecipe = manager.getRecipes().stream().filter(recipe -> {
				return recipe.value() instanceof HardenRecipe hardenRecipe && hardenRecipe.getType() == TofuRecipes.RECIPETYPE_HARDER.get();
			});
			for (RecipeHolder<?> recipe : tofuRecipe.collect(Collectors.toList())) {
				if (recipe.value() instanceof HardenRecipe hardenRecipe && hardenRecipe.getTofu().test(new ItemStack(block.asItem()))) {
					return ((HardenRecipe) recipe.value()).getResult();
				}
			}
		}

		return null;
	}

	@Nullable
	public static ItemStack getBitternResult(ServerLevel serverLevel, Fluid fluid, ItemStack stack) {
		final RecipeManager manager = serverLevel.recipeAccess();

		if (fluid != null) {
			Stream<RecipeHolder<?>> tofuRecipe = manager.getRecipes().stream().filter(recipe -> {
				return recipe.value() instanceof BitternRecipe bittern && bittern.getType() == TofuRecipes.RECIPETYPE_BITTERN.get();
			});
			for (RecipeHolder<?> recipe : tofuRecipe.collect(Collectors.toList())) {
				if (recipe.value() instanceof BitternRecipe bitternRecipe && bitternRecipe.getFluid().is(fluid)) {
					if (bitternRecipe.getBitternIngredient().test(stack)) {
						return bitternRecipe.getResult();
					}
				}
			}
		}

		return null;
	}
}
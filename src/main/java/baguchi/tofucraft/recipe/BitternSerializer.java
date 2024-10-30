package baguchi.tofucraft.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.fluids.FluidStack;

public class BitternSerializer implements RecipeSerializer<BitternRecipe> {
	private static final MapCodec<BitternRecipe> CODEC = RecordCodecBuilder.mapCodec(
			p_301227_ -> p_301227_.group(
							FluidStack.CODEC.fieldOf("process").forGetter(p_301070_ -> p_301070_.fluid),
							Ingredient.CODEC.fieldOf("extra_fluid").forGetter(p_300969_ -> p_300969_.ingredient),
							ItemStack.SINGLE_ITEM_CODEC.fieldOf("result").forGetter(p_300977_ -> p_300977_.result)
					)
					.apply(p_301227_, BitternRecipe::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, BitternRecipe> STREAM_CODEC = StreamCodec.of(
			BitternSerializer::toNetwork, BitternSerializer::fromNetwork
	);

	@Override
	public MapCodec<BitternRecipe> codec() {
		return CODEC;
	}

	@Override
	public StreamCodec<RegistryFriendlyByteBuf, BitternRecipe> streamCodec() {
		return STREAM_CODEC;
	}

	private static BitternRecipe fromNetwork(RegistryFriendlyByteBuf p_320719_) {
		FluidStack ingredient = FluidStack.STREAM_CODEC.decode(p_320719_);
		Ingredient ingredient1 = Ingredient.CONTENTS_STREAM_CODEC.decode(p_320719_);
		ItemStack ingredient2 = ItemStack.STREAM_CODEC.decode(p_320719_);
		return new BitternRecipe(ingredient, ingredient1, ingredient2);
	}

	private static void toNetwork(RegistryFriendlyByteBuf p_319922_, BitternRecipe p_320655_) {
		FluidStack.STREAM_CODEC.encode(p_319922_, p_320655_.fluid);
		Ingredient.CONTENTS_STREAM_CODEC.encode(p_319922_, p_320655_.ingredient);
		ItemStack.STREAM_CODEC.encode(p_319922_, p_320655_.result);
	}
}
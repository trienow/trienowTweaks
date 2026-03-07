package de.trienow.trienowtweaks.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.List;
import java.util.Map;

public class RecipeTTSerializer implements RecipeSerializer<RecipeTT>
{
	public static final MapCodec<RecipeTT> CODEC = RecordCodecBuilder.mapCodec(recipeTTInstance ->
			recipeTTInstance.group(
							Codec.INT.fieldOf("width").forGetter(RecipeTT::getWidth),
							Codec.INT.fieldOf("height").forGetter(RecipeTT::getHeight),
							Codec.list(Ingredient.CODEC).fieldOf("ingredients").forGetter(RecipeTT::getIngredients),
							ItemStack.CODEC.fieldOf("result").forGetter(RecipeTT::getResult),
							Codec.unboundedMap(Item.CODEC, Codec.INT).fieldOf("damageableItems").forGetter(RecipeTT::getDamageableItems),
							CraftingBookCategory.CODEC.fieldOf("category").forGetter(RecipeTT::getCategory))
					.apply(recipeTTInstance, RecipeTTSerializer::createFromCodec));

	public static final StreamCodec<RegistryFriendlyByteBuf, RecipeTT> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.INT, RecipeTT::getWidth,
			ByteBufCodecs.INT, RecipeTT::getHeight,
			Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), RecipeTT::getIngredients,
			ItemStack.STREAM_CODEC, RecipeTT::getResult,
			ByteBufCodecs.map(Object2IntOpenHashMap::new, Item.STREAM_CODEC, ByteBufCodecs.INT), RecipeTT::getDamageableItems,
			CraftingBookCategory.STREAM_CODEC, RecipeTT::getCategory,
			RecipeTTSerializer::createFromCodec
	);

	@Override public MapCodec<RecipeTT> codec()
	{
		return CODEC;
	}

	@Override public StreamCodec<RegistryFriendlyByteBuf, RecipeTT> streamCodec()
	{
		return STREAM_CODEC;
	}

	private static RecipeTT createFromCodec(Integer width, Integer height, List<Ingredient> ingredients, ItemStack result, Map<Holder<Item>, Integer> damageableItems, CraftingBookCategory category)
	{
		RecipeTT recipe = new RecipeTT(width, height, ingredients.toArray(new Ingredient[0]), result);
		recipe.setCategory(category);

		for (Map.Entry<Holder<Item>, Integer> entry : damageableItems.entrySet())
		{
			recipe.addDamageable(entry.getKey(), entry.getValue());
		}

		return recipe;
	}
}

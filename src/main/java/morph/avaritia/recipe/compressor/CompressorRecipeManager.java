package morph.avaritia.recipe.compressor;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static morph.avaritia.recipe.AvaritiaRecipeManager.COMPRESSOR_RECIPES;

public class CompressorRecipeManager {

    /**
     * Added by xXjudeXx on 25/01/2025
     * @param output The singularity as an ItemStack
     * @param input The input material as an ItemStack
     * @param inputAmount the amount the input will need
     * @param absolute
     */
    public static void addNeutroniumCompressorRecipe(ItemStack output, ItemStack input, int inputAmount, boolean absolute) {
        if(input == null) {
            throw new IllegalArgumentException("input cannot be null!");
        } else if (inputAmount <= 0) {
            throw new IllegalArgumentException("input Amount cannot be less than one!");
        } else if (output == null) {
            throw new IllegalArgumentException("output cannot be null!");
        } else {
            ICompressorRecipe recipe = new CompressorRecipe(output, inputAmount, absolute, Collections.singletonList(Ingredient.fromStacks(input)));
            recipe.setRegistryName(new ResourceLocation("avaritia", "internal/compressor/" + output.toString()));
            COMPRESSOR_RECIPES.put(new ResourceLocation(output.toString()), recipe);
        }
    }

    /**
     * Added by xXjudeXx on 25/01/2025
     * @param output Removes a recipe based on the Recipes Result
     */
    public static void removeNeutroniumCompressorRecipe(ItemStack output) {
        Map<ResourceLocation, ICompressorRecipe> TO_REMOVE = new HashMap<>();
        if (output == null){
            throw new IllegalArgumentException("stack cannot be null!");
        } else {
            COMPRESSOR_RECIPES.entrySet().stream()
                    .filter(recipe -> recipe.getValue().getResult().isItemEqual(output))
                    .forEach(recipe -> TO_REMOVE.put(recipe.getKey(), recipe.getValue()));
        }
        TO_REMOVE.entrySet().stream().forEach(entry -> COMPRESSOR_RECIPES.remove(entry.getKey(), entry.getValue()));
    }

}

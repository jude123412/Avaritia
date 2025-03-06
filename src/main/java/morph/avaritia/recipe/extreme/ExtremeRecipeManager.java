package morph.avaritia.recipe.extreme;

import static morph.avaritia.recipe.AvaritiaRecipeManager.EXTREME_RECIPES;

import java.util.*;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;

import morph.avaritia.Avaritia;

public class ExtremeRecipeManager {

    /**
     * Added By xXjudeXx on 18/02/2025
     * 
     * @param regName name of the Recipe in form of a String.
     * @param result  output of the recipe in form of an ItemStack.
     * @param objects the crafting grid array required to form the recipe.
     *                <p>
     *                This is the full Example of how to use this
     *                ExtremeCraftingRecipeManager.addExtremeShapedRecipe("armok_orb", new ItemStack(ModItems.armok_orb,
     *                1),
     *                " NNNNN ",
     *                " NNNNNNN ",
     *                "NNNIIINNN",
     *                "NNIOIOINN",
     *                "NNIIXIINN",
     *                "NNIOIOINN",
     *                "NNNIIINNN",
     *                " NNNNNNN ",
     *                " NNNNN ",
     *                'I', "ingotInfinity",
     *                'X', new ItemStack(ModItems.resource, 1, 5),
     *                'N', "ingotCosmicNeutronium",
     *                'O', archOrb);
     *                };
     */

    public static void addExtremeShapedRecipe(String regName, ItemStack result, Object... objects) {
        Object[] list = new Object[objects.length + 1];
        list[0] = false;
        System.arraycopy(objects, 0, list, 1, objects.length);

        CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(list);
        primer.mirrored = false;

        ExtremeShapedRecipe recipe = new ExtremeShapedRecipe(result, primer);
        recipe.setRegistryName(Avaritia.MOD_ID, regName);
        EXTREME_RECIPES.put(recipe.getRegistryName(), recipe);
    }

    /**
     * Added by xXjudeXx on 22/02/2025
     *
     * @param regName name of the Recipe : Example "avaritia:cosmic_meatballs"
     * @param result  the Item you want to make : Example new ItemStack(ModItems.cosmic_meatballs.getDefaultInstance())
     * @param inputs  the Input Items required to make the result : Example new ItemStack[] {
     *                ModItems.cosmic_meatballs.getDefaultInstance(),
     *                ModItems.ultimate_stew.getDefaultInstance(),
     *                ModItems.endest_pearl.getDefaultInstance()
     *                }
     */
    public static void addExtremeShapelessRecipe(String regName, ItemStack result, ItemStack[] inputs) {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        Arrays.stream(inputs).forEach(eachItem -> ingredients.add(Ingredient.fromStacks(eachItem)));

        ExtremeShapelessRecipe recipe = new ExtremeShapelessRecipe(ingredients, result);
        recipe.setRegistryName(Avaritia.MOD_ID, regName);
        EXTREME_RECIPES.put(recipe.getRegistryName(), recipe);
    }

    /**
     * Added by xXjudeXx on 22/02/2025
     * 
     * @param output Removes a recipe based on the Recipes Output
     */
    public static void removeExtremeRecipe(ItemStack output) {
        Map<ResourceLocation, IExtremeRecipe> TO_REMOVE = new HashMap<>();
        if (output == null) {
            throw new IllegalArgumentException("output cannot be null!");
        } else {
            EXTREME_RECIPES.entrySet().stream()
                    .filter(recipe -> recipe.getValue().getRecipeOutput().isItemEqual(output))
                    .forEach(recipe -> TO_REMOVE.put(recipe.getKey(), recipe.getValue()));
        }
        TO_REMOVE.forEach(EXTREME_RECIPES::remove);
    }
}

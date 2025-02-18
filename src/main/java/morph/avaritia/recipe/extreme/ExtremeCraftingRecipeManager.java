package morph.avaritia.recipe.extreme;

import morph.avaritia.Avaritia;
import morph.avaritia.recipe.AvaritiaRecipeManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ExtremeCraftingRecipeManager {
    /**
     * Added By xXjudeXx on 18/02/2025
     * @param regName name of the Recipe in form of a String.
     * @param result output of the recipe in form of an ItemStack.
     * @param objects the crafting grid array required to form the recipe.
     * <p>
     * This is the full Example of how to use this
     * ExtremeCraftingRecipeManager.addExtremeShapedRecipe("armok_orb", new ItemStack(ModItems.armok_orb, 1),
     *                 "  NNNNN  ",
     *                 " NNNNNNN ",
     *                 "NNNIIINNN",
     *                 "NNIOIOINN",
     *                 "NNIIXIINN",
     *                 "NNIOIOINN",
     *                 "NNNIIINNN",
     *                 " NNNNNNN ",
     *                 "  NNNNN  ",
     *                 'I', "ingotInfinity",
     *                 'X', new ItemStack(ModItems.resource, 1, 5),
     *                 'N', "ingotCosmicNeutronium",
     *                 'O', archOrb);
     *     };
     */

    public static void addExtremeShapedRecipe(String regName, ItemStack result, Object...objects) {
        Object[] list = new Object[objects.length + 1];
        list[0] = false;
        System.arraycopy(objects, 0, list, 1, objects.length);

        CraftingHelper.ShapedPrimer primer = CraftingHelper.parseShaped(list);
        primer.mirrored = false;

        ExtremeShapedRecipe recipe = new ExtremeShapedRecipe(result, primer);
        recipe.setRegistryName(Avaritia.MOD_ID, regName);
        AvaritiaRecipeManager.EXTREME_RECIPES.put(recipe.getRegistryName(), recipe);
    }

    public static void addExtremeShapelessRecipe() {

    }
}

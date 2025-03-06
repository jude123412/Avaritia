package morph.avaritia.compat.crafttweaker;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.item.MCItemStack;

/**
 * Created by covers1624 on 14/12/18.
 */
public class CTIngredientWrapper extends Ingredient {

    private final IIngredient ingredient;

    public CTIngredientWrapper(IIngredient ingredient) {
        super(CraftTweakerMC.getItemStacks(ingredient.getItems()));
        this.ingredient = ingredient;
    }

    @Override
    public boolean apply(@Nullable ItemStack stack) {
        return ingredient.matches(MCItemStack.createNonCopy(stack));
    }
}

package morph.avaritia.compat.thaumcraft;

import static morph.avaritia.compat.Compat.*;
import static morph.avaritia.compat.Compat.ThaumcraftIsLoaded;
import static morph.avaritia.init.ModItems.registerItem;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import morph.avaritia.Avaritia;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;

public class Thaumcraft {

    public static ItemDarkhold darkhold;

    public static void registerItems() throws ItemNotFoundException {
        if (ThaumcraftIsLoaded) {
            darkhold = registerItem(new ItemDarkhold());
        }
    }

    public static void registerInit() throws ItemNotFoundException {
        ResearchCategories.registerCategory("INFINITY",
                "UNLOCKELDRITCH",
                new AspectList(),
                new ResourceLocation("avaritia", "textures/items/darkhold.png"),
                new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_6.jpg"),
                new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_7.jpg"));

        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(Avaritia.MOD_ID, "darkhold"), new InfusionRecipe(
                "DARKHOLD",
                new ItemStack(darkhold),
                12,
                new AspectList().add(Aspect.MAGIC, 500).add(Aspect.AURA, 250).add(Aspect.FLUX, 250)
                        .add(Aspect.DARKNESS, 250).add(Aspect.ELDRITCH, 250).add(Aspect.SOUL, 250).add(Aspect.MAN, 250),
                Ingredient.fromItem(ItemsTC.thaumonomicon),
                "gemInfinityCatalyst",
                "gemInfinityCatalyst",
                "blockVoid",
                "blockVoid",
                "blockInfinity",
                "blockInfinity",
                "blockCosmicNeutronium",
                "blockCosmicNeutronium",
                Ingredient.fromItem(ItemsTC.primordialPearl),
                Ingredient.fromItem(ItemsTC.primordialPearl)));

        ThaumcraftApi.registerResearchLocation(new ResourceLocation(Avaritia.MOD_ID, "research/research.json"));
    }

    public static void registerRecipes() throws ItemNotFoundException {
        if (ThaumcraftIsLoaded) {

        }
    }
}

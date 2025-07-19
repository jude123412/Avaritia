package morph.avaritia.compat.botania;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.crafting.CraftingHelper;

import morph.avaritia.compat.Compat;
import morph.avaritia.init.ModItems;
import morph.avaritia.recipe.extreme.ExtremeRecipeManager;
import morph.avaritia.recipe.extreme.ExtremeShapedRecipe;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.ModBlocks;

public class Tsundere {

    public static void preInit() throws Compat.ItemNotFoundException {
        BotaniaAPI.registerSubTile("asgardandelion", SubTileAsgardandelion.class);
        BotaniaAPI.registerSubTileSignature(SubTileAsgardandelion.class, new Signature("asgardandelion"));
        BotaniaAPI.addSubTileToCreativeMenu("asgardandelion");
    }

    public static void init() throws Compat.ItemNotFoundException {}

    public static void postInit() throws Compat.ItemNotFoundException {
        ItemStack asgardandelion = getFlower("asgardandelion");

        SubTileAsgardandelion.lexicon = new LudicrousLexicon("asgardandelion", BotaniaAPI.categoryGenerationFlowers);
        SubTileAsgardandelion.lexicon.setLexiconPages(
                BotaniaAPI.internalHandler.textPage("avaritia.lexicon.asgardandelion.0"),
                new PageLudicrousLexicon("avaritia.lexicon.asgardandelion.1",
                        new ExtremeShapedRecipe(asgardandelion,
                                CraftingHelper.parseShaped(
                                        "   III   ",
                                        "  IIIII  ",
                                        "  IIXII  ",
                                        "  IIIII  ",
                                        "   III   ",
                                        " nn N nn ",
                                        "nnnnNnnnn",
                                        " nn N nn ",
                                        "    N    ",
                                        'I', new ItemStack(ModItems.resource, 1, 6),
                                        'X', new ItemStack(ModItems.resource, 1, 5),
                                        'N', new ItemStack(ModItems.resource, 1, 4),
                                        'n', new ItemStack(ModItems.resource, 1, 3)))))
                .setIcon(asgardandelion);
    }

    public static void registerRecipes() throws Compat.ItemNotFoundException {
        ItemStack asgardandelion = getFlower("asgardandelion");

        ExtremeRecipeManager.addExtremeShapedRecipe("asgardandelion", asgardandelion,
                "   III   ",
                "  IIIII  ",
                "  IIXII  ",
                "  IIIII  ",
                "   III   ",
                " nn N nn ",
                "nnnnNnnnn",
                " nn N nn ",
                "    N    ",
                'I', new ItemStack(ModItems.resource, 1, 6),
                'X', new ItemStack(ModItems.resource, 1, 5),
                'N', new ItemStack(ModItems.resource, 1, 4),
                'n', new ItemStack(ModItems.resource, 1, 3));
    }

    private static ItemStack getFlower(String name) {
        ItemStack flower = new ItemStack(ModBlocks.specialFlower, 1, 0);
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setString("type", name);
        flower.setTagCompound(tagCompound);
        return flower;
    }
}

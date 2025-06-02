package morph.avaritia.compat.botania;

import morph.avaritia.compat.Compat;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.ModBlocks;

public class Botania {

    public static void preInit() throws Compat.ItemNotFoundException {


        BotaniaAPI.registerSubTile("asgardandelion", SubTileAsgardandelion.class);
        BotaniaAPI.registerSubTileSignature(SubTileAsgardandelion.class, new Signature("asgardandelion"));
        BotaniaAPI.addSubTileToCreativeMenu("asgardandelion");

    }

    public static void init() throws Compat.ItemNotFoundException {

        ItemStack asgardandelion = getFlower("asgardandelion");

        SubTileAsgardandelion.lexiconEntry = new LudicrousLexicon("asgardandelion", BotaniaAPI.categoryGenerationFlowers);
        SubTileAsgardandelion.lexiconEntry.addPage(BotaniaAPI.internalHandler.textPage("avaritia.lexicon.asgardandelion.0"));
        SubTileAsgardandelion.lexiconEntry.setIcon(asgardandelion);

    }

    private static ItemStack getFlower(String name) {
        ItemStack flower = new ItemStack(ModBlocks.specialFlower, 1, 0);
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setString("type", name);
        flower.setTagCompound(tagCompound);
        return flower;
    }
}

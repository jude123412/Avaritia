package morph.avaritia.compat.bloodmagic;

import static morph.avaritia.compat.Compat.*;
import static morph.avaritia.init.ModItems.registerItem;

import java.util.Objects;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import WayofTime.bloodmagic.ConfigHandler;
import morph.avaritia.recipe.extreme.ExtremeRecipeManager;

public class BloodMagic {

    public static ItemOrbArmok armok_orb;

    public static void registerItems() throws ItemNotFoundException {
        if (BloodMagicIsLoaded) {
            armok_orb = registerItem(new ItemOrbArmok());
        }
    }

    public static void registerRecipes() throws ItemNotFoundException {
        if (BloodMagicIsLoaded) {
            ItemStack archOrb = new ItemStack(Objects
                    .requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation("bloodmagic", "blood_orb"))));
            NBTTagCompound tag = new NBTTagCompound();
            if (ConfigHandler.general.enableTierSixEvenThoughThereIsNoContent) {
                tag.setString("orb", "bloodmagic:transcendent");
            } else {
                tag.setString("orb", "bloodmagic:archmage");
            }
            archOrb.setTagCompound(tag);

            ExtremeRecipeManager.addExtremeShapedRecipe("armok_orb",
                    new ItemStack(armok_orb, 1),
                    "  NNNNN  ",
                    " NNNNNNN ",
                    "NNNIIINNN",
                    "NNIXIXINN",
                    "NNIIOIINN",
                    "NNIXIXINN",
                    "NNNIIINNN",
                    " NNNNNNN ",
                    "  NNNNN  ",
                    'I', "ingotInfinity",
                    'X', "gemInfinityCatalyst",
                    'N', "ingotCosmicNeutronium",
                    'O', archOrb);
        }
    }
}

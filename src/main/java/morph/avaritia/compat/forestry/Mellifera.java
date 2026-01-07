package morph.avaritia.compat.forestry;

import morph.avaritia.compat.Compat;

import static morph.avaritia.compat.Compat.ForestryIsModLoaded;
import static morph.avaritia.init.ModItems.registerItem;

public class Mellifera {

    public static ItemInfinityFrame infinityFrame;
    public static ItemInfinityFrame cosmicFrame;

    public static void registerItems() throws Compat.ItemNotFoundException {
        if (ForestryIsModLoaded) {
            infinityFrame = registerItem(new ItemInfinityFrame("infinity_frame", 20.0F, 1.0F, 5.0F, 0.0F));
            cosmicFrame = registerItem(new ItemInfinityFrame("cosmic_neutronium_frame", 0.0F, 25.0F, 0.0F, 2.0F));
        }
    }
}

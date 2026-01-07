package morph.avaritia.compat.forestry;

import morph.avaritia.compat.Compat;

import static morph.avaritia.compat.Compat.ForestryIsModLoaded;
import static morph.avaritia.init.ModItems.registerItem;

public class Mellifera {

    public static ItemInfinityFrame infinityFrame;
    public static ItemInfinityFrame cosmicFrame;

    public static void registerItems() throws Compat.ItemNotFoundException {
        if (ForestryIsModLoaded) {
            infinityFrame = registerItem(new ItemInfinityFrame(0.0F, 999.0F, 0.0F, "infinity_frame"));
            cosmicFrame = registerItem(new ItemInfinityFrame(999.0F, 0.0F, 999.0F, "cosmic_frame"));
        }
    }
}

package morph.avaritia.compat;

import net.minecraftforge.fml.common.Loader;

public class Compat {

    public static boolean BloodMagicIsLoaded = Loader.isModLoaded("bloodmagic");

    public static class ItemNotFoundException extends Exception {
        public ItemNotFoundException(String mod, String item) {
            super("Unable to find " + item + " from " + mod + "! Do you have " + mod + " installed?");
        }
    }
}

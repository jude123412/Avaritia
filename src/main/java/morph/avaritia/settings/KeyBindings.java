package morph.avaritia.settings;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBindings {
    public static KeyBinding modeSwitch;

    public static void init() {
        modeSwitch = new KeyBinding("key.modeSwitch", KeyConflictContext.IN_GAME, 50, "key.categories.avaritia");
        ClientRegistry.registerKeyBinding(modeSwitch);
    }
}

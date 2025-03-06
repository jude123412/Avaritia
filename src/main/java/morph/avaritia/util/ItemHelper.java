package morph.avaritia.util;

import java.util.Objects;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemHelper {

    public static ItemStack getItemStackFromString(String name, String path) {
        return new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(name, path))));
    }
}

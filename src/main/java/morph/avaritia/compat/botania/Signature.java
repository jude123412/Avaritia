package morph.avaritia.compat.botania;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import vazkii.botania.api.subtile.signature.SubTileSignature;

public class Signature implements SubTileSignature {

    String name;

    public Signature(String flowerName) {
        name = flowerName;
    }

    @Override
    public String getUnlocalizedNameForStack(ItemStack itemStack) {
        return "avaritia.flower." + name;
    }

    @Override
    public String getUnlocalizedLoreTextForStack(ItemStack itemStack) {
        return "tile.avaritia.flower." + name + ".lore";
    }

    @Override
    public void addTooltip(ItemStack stack, World world, List<String> tooltip) {
        SubTileSignature.super.addTooltip(stack, world, tooltip);
    }
}

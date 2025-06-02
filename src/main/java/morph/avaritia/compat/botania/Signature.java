package morph.avaritia.compat.botania;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.model.Models;
import vazkii.botania.api.subtile.signature.SubTileSignature;

import java.util.List;

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

package morph.avaritia.item.tools;

import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import morph.avaritia.Avaritia;

public class ItemSwordSkulls extends ItemSword {

    public ItemSwordSkulls() {
        super(ToolMaterial.DIAMOND);
        setTranslationKey("avaritia:skullfire_sword");
        setRegistryName("skullfire_sword");
        setCreativeTab(Avaritia.tab);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (GuiScreen.isShiftKeyDown()) {
            tooltip.add(TextFormatting.DARK_GRAY + "" + I18n.translateToLocal("tooltip.skullfire_sword.desc"));
        } else {
            tooltip.add(TextFormatting.GRAY + "" + I18n.translateToLocal("tooltip.item.avaritia:tool.desc"));
        }
    }
}

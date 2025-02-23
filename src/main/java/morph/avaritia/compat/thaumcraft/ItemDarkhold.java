package morph.avaritia.compat.thaumcraft;

import morph.avaritia.Avaritia;
import morph.avaritia.api.registration.IModelRegister;
import morph.avaritia.init.ModItems;
import morph.avaritia.util.ToolHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.common.lib.SoundsTC;

import java.util.List;

public class ItemDarkhold extends Item implements IModelRegister {

    ItemDarkhold() {
        setMaxStackSize(1);
        setTranslationKey("avaritia:darkhold");
        setRegistryName("darkhold");
        setCreativeTab(Avaritia.tab);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        IPlayerWarp warp = ThaumcraftCapabilities.getWarp((EntityPlayer)player);
        if (GuiScreen.isShiftKeyDown()) {
            warp.reduce(IPlayerWarp.EnumWarpType.PERMANENT, 25);
            warp.reduce(IPlayerWarp.EnumWarpType.NORMAL, 25);
            warp.reduce(IPlayerWarp.EnumWarpType.TEMPORARY, 25);
            player.world.playSound(player, player.getPosition(), SoundsTC.erase, SoundCategory.AMBIENT, 1.0F, 0.5F);
        } else {
            warp.add(IPlayerWarp.EnumWarpType.PERMANENT, 10);
            player.world.playSound(player, player.getPosition(), SoundsTC.whispers, SoundCategory.AMBIENT, 1.0F, 1.0F);
        }
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (GuiScreen.isShiftKeyDown()) {
            tooltip.add(TextFormatting.DARK_GRAY + "" + I18n.translateToLocal("tooltip." + getTranslationKey(stack) + ".desc"));
        } else {
            tooltip.add(TextFormatting.GRAY + "" + I18n.translateToLocal("tooltip.item.avaritia:tool.desc"));
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return ModItems.DEMONIC_RARITY;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        ModelResourceLocation orb = new ModelResourceLocation("avaritia:resource", "type=darkhold");
        ModelLoader.registerItemVariants(this, orb);
        ModelLoader.setCustomMeshDefinition(this, (ItemStack stack) -> orb);
    }
}

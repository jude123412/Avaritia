package morph.avaritia.compat.thaumcraft;

import morph.avaritia.Avaritia;
import morph.avaritia.api.registration.IModelRegister;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.common.lib.research.ResearchManager;

import java.util.Collection;
import java.util.List;

public class ItemAkashicRecord extends Item implements IModelRegister {

    ItemAkashicRecord() {
        setMaxStackSize(1);
        setTranslationKey("avaritia:akashic_record");
        setRegistryName("akashic_record");
        setCreativeTab(Avaritia.tab);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if(!world.isRemote) {
            Collection<ResearchCategory> researchCategory = ResearchCategories.researchCategories.values();
            for (ResearchCategory catagory : researchCategory) {
                ResearchManager.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, catagory, 999);
                ResearchManager.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, catagory, 999);
            }
        }

        stack.setCount(0);
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (GuiScreen.isShiftKeyDown()) {
            tooltip.add(TextFormatting.DARK_GRAY + "" +
                    I18n.translateToLocal("tooltip." + getTranslationKey(stack) + ".desc"));
        } else {
            tooltip.add(TextFormatting.GRAY + "" + I18n.translateToLocal("tooltip.item.avaritia:tool.desc"));
        }
    }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    public void registerModels() {
        ModelResourceLocation record = new ModelResourceLocation("avaritia:resource", "type=akashic_record");
        ModelLoader.registerItemVariants(this, record);
        ModelLoader.setCustomMeshDefinition(this, (ItemStack stack) -> record);
    }
}

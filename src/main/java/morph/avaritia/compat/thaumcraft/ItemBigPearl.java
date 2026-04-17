package morph.avaritia.compat.thaumcraft;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.util.TransformUtils;
import morph.avaritia.Avaritia;
import morph.avaritia.api.IHaloRenderItem;
import morph.avaritia.api.registration.IModelRegister;
import morph.avaritia.client.render.item.HaloRenderItem;

public class ItemBigPearl extends Item implements IModelRegister, IHaloRenderItem {

    public ItemBigPearl() {
        setMaxStackSize(1);
        setTranslationKey("avaritia:extremely_primordial_pearl");
        setRegistryName("extremely_primordial_pearl");
        setCreativeTab(Avaritia.tab);
        this.setContainerItem(this);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.EPIC;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldDrawHalo(ItemStack stack) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public TextureAtlasSprite getHaloTexture(ItemStack stack) {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getHaloColour(ItemStack stack) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getHaloSize(ItemStack stack) {
        return 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldDrawPulse(ItemStack stack) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        ModelResourceLocation pearl = new ModelResourceLocation("avaritia:resource", "type=extremely_primordial_pearl");
        IBakedModel wrapped = new HaloRenderItem(TransformUtils.DEFAULT_ITEM,
                modelRegistry -> modelRegistry.getObject(pearl));
        ModelRegistryHelper.register(pearl, wrapped);
        ModelLoader.registerItemVariants(this, pearl);
        ModelLoader.setCustomMeshDefinition(this, (ItemStack stack) -> pearl);
    }
}

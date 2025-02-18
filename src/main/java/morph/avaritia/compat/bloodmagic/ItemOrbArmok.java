package morph.avaritia.compat.bloodmagic;

import WayofTime.bloodmagic.altar.AltarTier;
import WayofTime.bloodmagic.core.data.Binding;
import WayofTime.bloodmagic.core.data.SoulNetwork;
import WayofTime.bloodmagic.core.data.SoulTicket;
import WayofTime.bloodmagic.core.registry.AltarRecipeRegistry;
import WayofTime.bloodmagic.iface.IBindable;
import WayofTime.bloodmagic.item.ItemBindableBase;
import WayofTime.bloodmagic.orb.BloodOrb;
import WayofTime.bloodmagic.orb.IBloodOrb;
import WayofTime.bloodmagic.util.helper.NetworkHelper;
import WayofTime.bloodmagic.util.helper.TextHelper;
import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.util.TransformUtils;
import morph.avaritia.Avaritia;
import morph.avaritia.api.IHaloRenderItem;
import morph.avaritia.api.IOrbBlood;
import morph.avaritia.api.registration.IModelRegister;
import morph.avaritia.client.render.item.HaloRenderItem;
import morph.avaritia.init.AvaritiaTextures;
import morph.avaritia.init.ModItems;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Optional.Interface(
        modid = "bloodmagic",
        iface = "WayofTime.bloodmagic.orb.IBloodOrb")
@Optional.Interface(
        modid = "bloodmagic",
        iface = "WayofTime.bloodmagic.iface.IBindable")

public class ItemOrbArmok extends ItemBindableBase implements IBloodOrb, IBindable, IOrbBlood, IHaloRenderItem, IModelRegister {

    public ItemOrbArmok() {
        setMaxStackSize(1);
        setTranslationKey("avaritia:armok_orb");
        setRegistryName("armok_orb");
        setCreativeTab(Avaritia.tab);
        AltarRecipeRegistry.registerFillRecipe(new ItemStack(this), AltarTier.ONE, 7, 200, 200);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        Binding binding = this.getBinding(stack);
        if (binding != null) {
            SoulNetwork soulNetwork = NetworkHelper.getSoulNetwork(binding);
            soulNetwork.add(SoulTicket.item(stack, world, player, 200), this.getCapacity());
        }
        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add(TextHelper.localizeEffect("tooltip.bloodmagic.orb.desc", new Object[0]));
        BloodOrb orb = this.getOrb(stack);
        if (flag.isAdvanced() && orb != null) {
            tooltip.add(TextHelper.localizeEffect("tooltip.bloodmagic.orb.owner", new Object[]{orb.getRegistryName().getNamespace()}));
        }
        super.addInformation(stack, world, tooltip, flag);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isRemote && entity instanceof EntityPlayer) {
            NBTTagCompound itemTag = stack.getTagCompound();
            Binding binding = getBinding(stack);
            if (itemTag == null || binding == null) {
                return;
            }

            SoulNetwork soulNetwork = NetworkHelper.getSoulNetwork(binding);
            soulNetwork.setCurrentEssence(getCapacity());
        }
    }

    @Override
    public int getTier() {
        return 666;
    }

    @Override
    public int getCapacity() {
        return 1000000000;
    }

    @Override
    public int getFillRate() {
        return 0;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return ModItems.COSMIC_RARITY;
    }

    @Nullable
    @Override
    public BloodOrb getOrb(ItemStack stack) {
        return null;
    }

    @Override
    @SideOnly (Side.CLIENT)
    public boolean shouldDrawHalo(ItemStack stack) {
        return true;
    }

    @Override
    @SideOnly (Side.CLIENT)
    public TextureAtlasSprite getHaloTexture(ItemStack stack) {
        return AvaritiaTextures.HALO;
    }

    @Override
    @SideOnly (Side.CLIENT)
    public int getHaloColour(ItemStack stack) {
        return 0xFFFF0000;
    }

    @Override
    @SideOnly (Side.CLIENT)
    public int getHaloSize(ItemStack stack) {
        return 4;
    }

    @Override
    @SideOnly (Side.CLIENT)
    public boolean shouldDrawPulse(ItemStack stack) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        ModelResourceLocation orb = new ModelResourceLocation("avaritia:resource", "type=armok_orb");
        ModelLoader.registerItemVariants(this, orb);
        IBakedModel wrapped = new HaloRenderItem(TransformUtils.DEFAULT_ITEM, modelRegistry -> modelRegistry.getObject(orb));
        ModelRegistryHelper.register(orb, wrapped);
        ModelLoader.setCustomMeshDefinition(this, (ItemStack stack) -> orb);
    }
}

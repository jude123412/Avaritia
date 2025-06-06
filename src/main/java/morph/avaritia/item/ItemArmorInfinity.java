package morph.avaritia.item;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import morph.avaritia.Avaritia;
import morph.avaritia.api.ICosmicRenderItem;
import morph.avaritia.client.render.entity.ModelArmorInfinity;
import morph.avaritia.init.AvaritiaTextures;
import morph.avaritia.init.ModItems;
import morph.avaritia.util.ModHelper;
import morph.avaritia.util.TextUtils;
import thaumcraft.api.items.IGoggles;
import thaumcraft.api.items.IVisDiscountGear;

@Optional.Interface(iface = "thaumcraft.api.items.IVisDiscountGear", modid = "thaumcraft")
@Optional.Interface(iface = "thaumcraft.api.items.IGoggles", modid = "thaumcraft")
public class ItemArmorInfinity extends ItemArmor implements IVisDiscountGear, IGoggles, ICosmicRenderItem {

    public static final ArmorMaterial infinite_armor = EnumHelper.addArmorMaterial("avaritia_infinity", "", 9999,
            new int[] { 6, 16, 12, 6 }, 1000, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F);
    public final EntityEquipmentSlot slot;

    public ItemArmorInfinity(EntityEquipmentSlot slot) {
        super(infinite_armor, 0, slot);
        this.slot = slot;
        setCreativeTab(Avaritia.tab);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "avaritia:textures/models/infinity_armor.png";
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        super.setDamage(stack, 0);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (armorType == EntityEquipmentSlot.HEAD) {
            player.setAir(300);
            player.getFoodStats().addStats(20, 20F);
            PotionEffect nv = player.getActivePotionEffect(MobEffects.NIGHT_VISION);
            if (nv == null || player.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration() <= 200) {
                nv = new PotionEffect(MobEffects.NIGHT_VISION, 319, 0, false, false);
                player.addPotionEffect(nv);
            }
        } else if (armorType == EntityEquipmentSlot.CHEST) {
            player.capabilities.allowFlying = true;
            List<PotionEffect> effects = Lists.newArrayList(player.getActivePotionEffects());
            for (PotionEffect potion : Collections2.filter(effects, potion -> potion.getPotion().isBadEffect())) {
                if (ModHelper.isHoldingCleaver(player) && potion.getPotion().equals(MobEffects.MINING_FATIGUE)) {
                    continue;
                }
                player.removePotionEffect(potion.getPotion());
            }
        } else if (armorType == EntityEquipmentSlot.LEGS) {
            if (player.isBurning()) {
                player.extinguish();
            }
        } else if (armorType == EntityEquipmentSlot.FEET) {
            player.stepHeight = 1.0f;
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return ModItems.COSMIC_RARITY;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemstack, EntityEquipmentSlot armorSlot,
                                    ModelBiped _deafult) {
        ModelArmorInfinity model = armorSlot == EntityEquipmentSlot.LEGS ? ModelArmorInfinity.legModel :
                ModelArmorInfinity.armorModel;

        model.update(entityLiving, itemstack, armorSlot);

        return model;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (slot == EntityEquipmentSlot.FEET) {
            tooltip.add("");
            tooltip.add(TextFormatting.BLUE + "+" + TextFormatting.ITALIC + TextUtils.makeSANIC("SANIC") +
                    TextFormatting.RESET + "" + TextFormatting.BLUE + "% Speed");
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack) {
        return false;
    }

    @Override
    public TextureAtlasSprite getMaskTexture(ItemStack stack, @Nullable EntityLivingBase player) {
        if (slot == EntityEquipmentSlot.HEAD) {
            return AvaritiaTextures.INFINITY_HELMET_MASK;
        } else if (slot == EntityEquipmentSlot.CHEST) {
            return AvaritiaTextures.INFINITY_CHESTPLATE_MASK;
        } else if (slot == EntityEquipmentSlot.LEGS) {
            return AvaritiaTextures.INFINITY_LEGGINGS_MASK;
        } else if (slot == EntityEquipmentSlot.FEET) {
            return AvaritiaTextures.INFINITY_BOOTS_MASK;
        } else {
            return null;
        }
    }

    @Override
    public float getMaskOpacity(ItemStack stack, @Nullable EntityLivingBase player) {
        return 1.0f;
    }

    @Override
    @Optional.Method(modid = "thaumcraft")
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer) {
        return 20;
    }

    @Override
    @Optional.Method(modid = "thaumcraft")
    public boolean showIngamePopups(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }
}

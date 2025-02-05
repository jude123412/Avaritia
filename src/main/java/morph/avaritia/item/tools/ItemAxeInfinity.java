/*
 *
 * Code blatantly jacked from Vazkii
 * Get the original here: https://github.com/Vazkii/Botania/blob/master/src/main/java/vazkii/botania/common/item/equipment/tool/terrasteel/ItemTerraAxe.java
 */

package morph.avaritia.item.tools;

import codechicken.lib.raytracer.RayTracer;
import morph.avaritia.Avaritia;
import morph.avaritia.api.ICosmicRenderItem;
import morph.avaritia.entity.EntityImmortalItem;
import morph.avaritia.handler.AvaritiaEventHandler;
import morph.avaritia.init.AvaritiaTextures;
import morph.avaritia.init.ModItems;
import morph.avaritia.util.ToolHelper;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;

public class ItemAxeInfinity extends ItemAxe implements ICosmicRenderItem {

    private static final ToolMaterial opAxe = EnumHelper.addToolMaterial("INFINITY_PICKAXE", 32, 9999, 9999F, 20.0F, 200);

    public ItemAxeInfinity() {
        super(opAxe, 20.0F, -3.0F);
        setTranslationKey("avaritia:infinity_axe");
        setRegistryName("infinity_axe");
        setCreativeTab(Avaritia.tab);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC + I18n.translateToLocal("tooltip." + getTranslationKey(stack) + ".desc"));
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        super.setDamage(stack, 0);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return ModItems.COSMIC_RARITY;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if (super.getDestroySpeed(stack, state) > 1.0F || state.getMaterial() == Material.LEAVES) {
            return efficiency;
        }
        return Math.max(super.getDestroySpeed(stack, state), 6.0F);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (player.isSneaking()) {
            player.swingArm(hand);

            int range = 13;
            BlockPos min = new BlockPos(-range, -3, -range);
            BlockPos max = new BlockPos(range, range * 2 - 3, range);

            ToolHelper.aoeBlocks(player, stack, world, player.getPosition(), min, max, null, ToolHelper.materialsAxe, false);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player) {

        RayTraceResult traceResult = RayTracer.retrace(player, 10, true);
        if (traceResult != null) {
            breakOtherBlock(player, stack, pos, traceResult.sideHit);
        }
        return false;
    }

    public void breakOtherBlock(EntityPlayer player, ItemStack stack, BlockPos pos, EnumFacing sideHit) {
        if (player.isSneaking()) {
            return;
        }
        AvaritiaEventHandler.startCrawlerTask(player.world, player, stack, pos, 32, false, true, new HashSet<>());
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Override
    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        return new EntityImmortalItem(world, location, itemstack);
    }

    @Override
    @SideOnly (Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack) {
        return false;
    }

    @Override
    public TextureAtlasSprite getMaskTexture(ItemStack stack, @Nullable EntityLivingBase player) {
        return AvaritiaTextures.INFINITY_AXE_MASK;
    }

    @Override
    public float getMaskOpacity(ItemStack stack, @Nullable EntityLivingBase player) {
        return 1.0f;
    }
}

package morph.avaritia.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import morph.avaritia.Avaritia;
import morph.avaritia.api.registration.IModelRegister;

public class BlockCompressedCraftingTable extends Block implements IModelRegister {

    public BlockCompressedCraftingTable() {
        super(Material.WOOD);
        setHardness(4.0F);
        setTranslationKey("avaritia:compressed_crafting_table");
        setSoundType(SoundType.WOOD);
        setCreativeTab(Avaritia.tab);
        setRegistryName("compressed_crafting_table");
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else {
            player.openGui(Avaritia.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        ModelResourceLocation loc = new ModelResourceLocation("avaritia:crafting", "type=compressed");
        ModelLoader.setCustomStateMapper(this, new StateMapperBase() {

            @Override
            protected @NotNull ModelResourceLocation getModelResourceLocation(@NotNull IBlockState state) {
                return loc;
            }
        });
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, loc);
    }
}

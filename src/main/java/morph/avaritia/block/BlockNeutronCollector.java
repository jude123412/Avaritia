package morph.avaritia.block;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import codechicken.lib.util.ItemUtils;
import codechicken.lib.util.RotationUtils;
import morph.avaritia.Avaritia;
import morph.avaritia.api.registration.IModelRegister;
import morph.avaritia.init.AvaritiaProps;
import morph.avaritia.tile.TileMachineBase;
import morph.avaritia.tile.TileNeutronCollector;

public class BlockNeutronCollector extends BlockContainer implements IModelRegister {

    public BlockNeutronCollector() {
        super(Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(20);
        setTranslationKey("avaritia:neutron_collector");
        setHarvestLevel("pickaxe", 3);
        setCreativeTab(Avaritia.tab);
        setRegistryName("neutron_collector");
        setDefaultState(getDefaultState().withProperty(AvaritiaProps.HORIZONTAL_FACING, EnumFacing.NORTH)
                .withProperty(AvaritiaProps.ACTIVE, false));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AvaritiaProps.HORIZONTAL_FACING, AvaritiaProps.ACTIVE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        int productionTicks = TileNeutronCollector.productionTicks;
        if (GuiScreen.isShiftKeyDown()) {
            if (productionTicks == 1) {
                tooltip.add(TextFormatting.DARK_GRAY +
                        I18n.translateToLocalFormatted("Produces a Pile of Neutrons every tick", new Object[] {}));
            }
            if (productionTicks > 1 && productionTicks < 20) {
                tooltip.add(TextFormatting.DARK_GRAY + I18n.translateToLocalFormatted(
                        "Produces a Pile of Neutrons every " + TileNeutronCollector.productionTicks + " ticks",
                        new Object[] {}));
            }
            if (productionTicks >= 20 && productionTicks < 40) {
                tooltip.add(TextFormatting.DARK_GRAY + I18n.translateToLocalFormatted(
                        "Produces a Pile of Neutrons approximately every second", new Object[] {}));
            }
            if (productionTicks >= 40) {
                tooltip.add(TextFormatting.DARK_GRAY + I18n.translateToLocalFormatted(
                        "Produces a Pile of Neutrons approximately every " +
                                Math.round((float) TileNeutronCollector.productionTicks / 20) + " seconds",
                        new Object[] {}));
            }
        } else {
            tooltip.add(TextFormatting.GRAY + I18n.translateToLocal("tooltip.item.avaritia:tool.desc"));
        }
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileMachineBase) {
            TileMachineBase machineBase = (TileMachineBase) tileEntity;
            state = state.withProperty(AvaritiaProps.HORIZONTAL_FACING, machineBase.getFacing());
            state = state.withProperty(AvaritiaProps.ACTIVE, machineBase.isActive());
        }
        return super.getActualState(state, worldIn, pos);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        } else {
            player.openGui(Avaritia.instance, 2, world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileNeutronCollector();
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player,
                                ItemStack stack) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileNeutronCollector) {
            TileNeutronCollector machine = (TileNeutronCollector) tile;
            machine.setFacing(RotationUtils.getPlacedRotationHorizontal(player));
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileNeutronCollector collector = (TileNeutronCollector) world.getTileEntity(pos);

        if (collector != null) {
            ItemUtils.dropInventory(world, pos, collector);
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerModels() {
        ResourceLocation location = new ResourceLocation("avaritia:machine");
        ModelLoader.setCustomStateMapper(this, new StateMapperBase() {

            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                String modelLoc = "type=neutron_collector";
                modelLoc += ",facing=" + state.getValue(AvaritiaProps.HORIZONTAL_FACING).getName();
                modelLoc += ",active=" + state.getValue(AvaritiaProps.ACTIVE).toString().toLowerCase();
                return new ModelResourceLocation(location, modelLoc);
            }
        });
        ModelResourceLocation invLoc = new ModelResourceLocation(location,
                "type=neutron_collector,facing=north,active=true");
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, invLoc);
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(this), stack -> invLoc);
    }
}

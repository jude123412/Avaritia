package morph.avaritia.compat.forestry;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.util.TransformUtils;
import forestry.api.apiculture.DefaultBeeModifier;
import forestry.api.apiculture.IBee;
import forestry.api.apiculture.IBeeGenome;
import forestry.api.apiculture.IBeeHousing;
import forestry.api.apiculture.IBeeModifier;
import forestry.api.apiculture.IHiveFrame;
import forestry.apiculture.items.ItemHiveFrame;
import forestry.core.items.ItemForestry;
import forestry.core.utils.Translator;
import morph.avaritia.Avaritia;
import morph.avaritia.api.IHaloRenderItem;
import morph.avaritia.api.registration.IModelRegister;
import morph.avaritia.client.render.item.HaloRenderItem;
import morph.avaritia.init.AvaritiaTextures;
import morph.avaritia.util.TextUtils;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class ItemInfinityFrame extends ItemForestry implements IHiveFrame, IHaloRenderItem, IModelRegister {
    private final InfinityFrameBeeModifier beeModifier;
    private final String regName;

    public ItemInfinityFrame( String regName, float productionModifier, float mutationModifier, float lifespanModifier, float geneticDecayModifier) {
        setMaxStackSize(1);
        setTranslationKey("avaritia:" + regName);
        setRegistryName(regName);
        setCreativeTab(Avaritia.tab);
        this.beeModifier = new InfinityFrameBeeModifier(productionModifier, mutationModifier, lifespanModifier, geneticDecayModifier);
        this.regName = regName;
    }

    public ItemStack frameUsed(IBeeHousing housing, ItemStack frame, IBee queen, int wear) {
        return frame;
    }

    public IBeeModifier getBeeModifier(ItemStack frame) {
        return this.beeModifier;
    }

    public IBeeModifier getBeeModifier() {
        return this.beeModifier;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
        if (!stack.isEmpty()) {
            if (!Objects.equals(regName, "cosmic_neutronium_frame")) {
                tooltip.add(TextFormatting.DARK_GRAY + "" + TextUtils.makeFabulous(I18n.translateToLocal("tooltip." + getTranslationKey(stack) + ".desc")));
            } else {
                tooltip.add(TextFormatting.DARK_GRAY + "" + TextFormatting.ITALIC +
                        I18n.translateToLocal("tooltip." + getTranslationKey(stack) + ".desc"));
            }
        }
        super.addInformation(stack, world, tooltip, advanced);
        this.beeModifier.addInformation(stack, world, tooltip, advanced);
    }

    @Override
    public boolean shouldDrawHalo(ItemStack stack) {
        return true;
    }

    @Override
    public TextureAtlasSprite getHaloTexture(ItemStack stack) {
        if (Objects.equals(regName, "cosmic_neutronium_frame")) {
            return AvaritiaTextures.HALO_NOISE;
        } else {
            return AvaritiaTextures.HALO;
        }
    }

    @Override
    public int getHaloColour(ItemStack stack) {
        if (Objects.equals(regName, "cosmic_neutronium_frame")) {
            return 0x99FFFFFF;
        } else {
            return 0xFF000000;
        }
    }

    @Override
    public int getHaloSize(ItemStack stack) {
        if (Objects.equals(regName, "cosmic_neutronium_frame")) {
            return 8;
        } else {
            return 10;
        }
    }

    @Override
    public boolean shouldDrawPulse(ItemStack stack) {
        return !Objects.equals(regName, "cosmic_neutronium_frame");
    }

    @Override
    public void registerModels() {
        ModelResourceLocation frame = new ModelResourceLocation("avaritia:resource", "type=" + regName);
        ModelLoader.registerItemVariants(this, frame);
        IBakedModel wrapped = new HaloRenderItem(TransformUtils.DEFAULT_ITEM,
                modelRegistry -> modelRegistry.getObject(frame));
        ModelRegistryHelper.register(frame, wrapped);
        ModelLoader.setCustomMeshDefinition(this, (ItemStack stack) -> frame);
    }

    private static class InfinityFrameBeeModifier extends DefaultBeeModifier {
        private final float productionModifier;
        private final float mutationModifier;
        private final float lifespanModifier;
        private final float geneticDecayModifier;

        public InfinityFrameBeeModifier(float productionModifier, float mutationModifier, float lifespanModifier, float geneticDecayModifier) {
            this.productionModifier = productionModifier;
            this.mutationModifier = mutationModifier;
            this.lifespanModifier = lifespanModifier;
            this.geneticDecayModifier = geneticDecayModifier;
        }

        @Override
        public float getProductionModifier(IBeeGenome genome, float currentModifier) {
            return this.productionModifier;
        }

        @Override
        public float getMutationModifier(IBeeGenome genome, IBeeGenome mate,float currentModifier) {
            return this.mutationModifier;
        }

        @Override
        public float getLifespanModifier(IBeeGenome genome, @Nullable IBeeGenome mate, float currentModifier) {
            return this.lifespanModifier;
        }

        @Override
        public float getGeneticDecay(IBeeGenome genome, float currentModifier) {
            return this.geneticDecayModifier;
        }

        @SideOnly(Side.CLIENT)
        public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced) {
            if (this.productionModifier != 1.0F) {
                tooltip.add(Translator.translateToLocalFormatted("item.for.bee.modifier.production", new Object[]{this.productionModifier}));
            }
            if (this.mutationModifier != 1.0F) {
                tooltip.add(Translator.translateToLocalFormatted("item.for.bee.modifier.mutation", new Object[]{this.mutationModifier}));
            }
            if (this.lifespanModifier != 1.0F) {
                tooltip.add(Translator.translateToLocalFormatted("item.for.bee.modifier.lifespan", new Object[]{this.lifespanModifier}));
            }
            if (this.geneticDecayModifier != 1.0F) {
                tooltip.add(Translator.translateToLocalFormatted("item.for.bee.modifier.genetic_decay", new Object[]{this.geneticDecayModifier}));
            }
        }
    }
}

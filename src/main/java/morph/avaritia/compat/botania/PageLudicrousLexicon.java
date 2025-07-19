package morph.avaritia.compat.botania;

import static net.minecraft.util.text.translation.I18n.translateToLocal;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

import morph.avaritia.recipe.extreme.ExtremeShapedRecipe;
import morph.avaritia.recipe.extreme.ExtremeShapelessRecipe;
import morph.avaritia.recipe.extreme.IExtremeRecipe;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.lexicon.page.PageRecipe;

public class PageLudicrousLexicon extends PageRecipe {

    private static final ResourceLocation craftingOverlay = new ResourceLocation("avaritia",
            "textures/gui/lexicon_crafting_overlay.png");

    IExtremeRecipe recipe;
    int ticksElapsed = 0;

    boolean oreDictRecipe, shapelessRecipe;

    public PageLudicrousLexicon(String unlocalizedName, IExtremeRecipe recipe) {
        super(unlocalizedName);
        this.recipe = recipe;
    }

    @Override
    public void onPageAdded(LexiconEntry entry, int index) {
        LexiconRecipeMappings.map(this.recipe.getRecipeOutput(), entry, index);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void renderRecipe(IGuiLexiconEntry gui, int mx, int my) {
        oreDictRecipe = shapelessRecipe = false;

        renderCraftingRecipe(gui, recipe);

        TextureManager render = Minecraft.getMinecraft().renderEngine;
        render.bindTexture(craftingOverlay);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1F, 1F, 1F, 1F);
        ((GuiScreen) gui).drawTexturedModalRect(gui.getLeft(), gui.getTop(), 0, 0, gui.getWidth(), gui.getHeight());

        int iconX = gui.getLeft() + 115;
        int iconY = gui.getTop() + 12;

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        if (shapelessRecipe) {
            ((GuiScreen) gui).drawTexturedModalRect(iconX, iconY, 240, 0, 16, 16);

            if (mx >= iconX && my >= iconY && mx < iconX + 16 && my < iconY + 16)
                RenderHelper.renderTooltip(mx, my, Arrays.asList(translateToLocal("botaniamisc.shapeless")));

            iconY += 20;
        }

        render.bindTexture(craftingOverlay);
        GL11.glEnable(GL11.GL_BLEND);

        if (oreDictRecipe) {
            ((GuiScreen) gui).drawTexturedModalRect(iconX, iconY, 240, 16, 16, 16);

            if (mx >= iconX && my >= iconY && mx < iconX + 16 && my < iconY + 16)
                RenderHelper.renderTooltip(mx, my, Arrays.asList(translateToLocal("botaniamisc.oredict")));
        }
        GL11.glDisable(GL11.GL_BLEND);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateScreen() {
        ++ticksElapsed;
    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void renderCraftingRecipe(IGuiLexiconEntry gui, IExtremeRecipe recipe) {
        if (recipe instanceof ExtremeShapedRecipe) {
            ExtremeShapedRecipe shaped = (ExtremeShapedRecipe) recipe;
            oreDictRecipe = true;
            for (int y = 0; y < shaped.getHeight(); y++)
                for (int x = 0; x < shaped.getWidth(); x++)
                    renderItemAtLudicrousGridPos(gui, x, y, shaped.getIngredients().get(y * shaped.getWidth() + x),
                            true);
        } else if (recipe instanceof ExtremeShapelessRecipe) {
            shapelessRecipe = true;
        }

        renderItemAtLudicrousGridPos(gui, 4, -1, Ingredient.fromItem(recipe.getRecipeOutput().getItem()), false);
    }

    @SideOnly(Side.CLIENT)
    public void renderItemAtLudicrousGridPos(IGuiLexiconEntry gui, int x, int y, Ingredient ingredient,
                                             boolean accountForContainer) {
        ItemStack stack;
        try {
            stack = ingredient.getMatchingStacks()[0];
        } catch (Throwable e){
            stack = new ItemStack(Blocks.AIR, 1);
        }

        if (stack == null || stack.isEmpty())
            return;
        stack = stack.copy();

        if (stack.getItemDamage() == Short.MAX_VALUE)
            stack.setItemDamage(0);

        int xPos = gui.getLeft() + x * 13 + 13;
        int yPos = gui.getTop() + y * 13 + 30 - (y == -1 ? 5 : 0);
        ItemStack stack1 = stack.copy();
        if (stack1.getItemDamage() == -1)
            stack1.setItemDamage(0);

        renderItem(gui, xPos, yPos, stack1, accountForContainer);
    }
}

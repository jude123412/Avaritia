package morph.avaritia.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import codechicken.lib.texture.TextureUtils;
import morph.avaritia.container.ContainerExtremeCrafting;
import morph.avaritia.tile.TileDireCraftingTable;

public class GUIExtremeCrafting extends GuiContainer {

    private static final ResourceLocation GUI_TEX = new ResourceLocation("avaritia",
            "textures/gui/dire_crafting_gui_new.png");

    public GUIExtremeCrafting(InventoryPlayer par1InventoryPlayer, World par2World, BlockPos pos,
                              TileDireCraftingTable table) {
        super(new ContainerExtremeCrafting(par1InventoryPlayer, par2World, pos, table));
        ySize = 256;
        xSize = 238;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        super.drawGuiContainerForegroundLayer(i, j);
        this.fontRenderer.drawString(I18n.format("container.extreme"), 180, 6, 4210752);
        this.fontRenderer.drawString(I18n.format("container.crafting"), 186, 18, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 180, this.ySize - 59, 4210752);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        TextureUtils.changeTexture(GUI_TEX);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);// TODO, this was, (x, y, 0, 0, ySize, ySize), Why was it ySize
                                                        // and not xSize..
    }
}

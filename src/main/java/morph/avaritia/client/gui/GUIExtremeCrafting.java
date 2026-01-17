package morph.avaritia.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import codechicken.lib.texture.TextureUtils;
import morph.avaritia.container.ContainerExtremeCrafting;
import morph.avaritia.tile.TileDireCraftingTable;

public class GUIExtremeCrafting extends GuiContainer {

    int offsetX = 0;
    int offsetY = 0;

    private static final ResourceLocation GUI_TEX = new ResourceLocation("avaritia",
            "textures/gui/dire_crafting_gui_new.png");

    public GUIExtremeCrafting(InventoryPlayer par1InventoryPlayer, World par2World, BlockPos pos,
                              TileDireCraftingTable table) {
        super(new ContainerExtremeCrafting(par1InventoryPlayer, par2World, pos, table));
        xSize = 257;
        ySize = 288;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        super.drawGuiContainerForegroundLayer(i, j);
        this.fontRenderer.drawString(I18n.format("container.extreme"), 180 + offsetX, 22, 4210752);
        this.fontRenderer.drawString(I18n.format("container.crafting"), 186 + offsetX, 34, 4210752);
        this.fontRenderer.drawString(I18n.format("container.inventory"), 180 + offsetX, this.ySize - 75, 4210752);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    private void drawTexturedRectUV(int x, int y, int u, int v, int w, int h, int texW, int texH) {
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buf = tess.getBuffer();

        float f = 1f / texW;
        float g = 1f / texH;

        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buf.pos(x, y + h, this.zLevel).tex(u * f, (v + h) * g).endVertex();
        buf.pos(x + w, y + h, this.zLevel).tex((u + w) * f, (v + h) * g).endVertex();
        buf.pos(x + w, y, this.zLevel).tex((u + w) * f, v * g).endVertex();
        buf.pos(x, y, this.zLevel).tex(u * f, v * g).endVertex();
        tess.draw();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1f, 1f, 1f, 1f);

        float scale = 1.0f; // safe max scale for your GUI size

        // Compute centered position for the scaled GUI
        int x = (int) ((width - xSize * scale) / 2);
        int y = (int) ((height - ySize * scale) / 2);

        TextureUtils.changeTexture(GUI_TEX);

        GlStateManager.pushMatrix();

        // Move to scaled GUI origin
        GlStateManager.translate(x, y, 0);

        // Apply scale
        GlStateManager.scale(scale, scale, 1f);

        // Draw using custom UV renderer
        drawTexturedRectUV(
                offsetX, offsetY,          // draw at origin
                0, 0,          // texture U/V start
                xSize, ySize,  // GUI size
                320, 320       // actual texture size
        );

        GlStateManager.popMatrix();
    }
}

package morph.avaritia.client.gui;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import codechicken.lib.math.MathHelper;
import morph.avaritia.container.ContainerNeutronCollector;
import morph.avaritia.tile.TileNeutronCollector;
import morph.avaritia.util.TextUtils;

@SideOnly(Side.CLIENT)
public class GUINeutronCollector extends GuiMachineBase<TileNeutronCollector, ContainerNeutronCollector> {

    private static final ResourceLocation GUI_TEX = new ResourceLocation("avaritia",
            "textures/gui/neutron_collector_gui.png");

    public GUINeutronCollector(InventoryPlayer player, TileNeutronCollector machine) {
        super(new ContainerNeutronCollector(player, machine));
        setBackgroundTexture(GUI_TEX);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        int color = 0x404040;

        String progress;

        if (machineTile.getProgress() > 1) {
            color = 0xFF0000;
        }
        if (machineTile.getProgress() > 1500) {
            color = 0xFF7F00;
        }
        if (machineTile.getProgress() > 3000) {
            color = 0xFFFF00;
        }
        if (machineTile.getProgress() > 4500) {
            color = 0x00FF00;
        }

        String s = I18n.format("container.neutron_collector");
        float scaled_progress = scaleF(machineTile.getProgress(), TileNeutronCollector.productionTicks, 100);

        // Shhhh, Don't tell anyone!
        if (machineTile.getProductionTicks() > 1) {
            progress = "Progress: " + MathHelper.round(scaled_progress, 10) + "%";
        } else {
            progress = TextUtils.makeFabulous("∞");
        }

        fontRenderer.drawString(s, xSize / 2 - fontRenderer.getStringWidth(s) / 2, 6, 0x404040);
        fontRenderer.drawString(progress, xSize / 2 - fontRenderer.getStringWidth(progress) / 2, 60, color);
        fontRenderer.drawString(I18n.format("container.inventory"), 8, ySize - 96 + 2, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawBackground();
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
    }
}

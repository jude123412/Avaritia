package morph.avaritia.client;

import java.nio.FloatBuffer;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import codechicken.lib.texture.TextureUtils;
import morph.avaritia.client.render.entity.ModelArmorInfinity;
import morph.avaritia.client.render.shader.CosmicShaderHelper;
import morph.avaritia.init.AvaritiaTextures;

public class AvaritiaClientEventHandler {

    public static FloatBuffer cosmicUVs = BufferUtils.createFloatBuffer(4 * 10);

    @SubscribeEvent
    public void textureStichPost(TextureStitchEvent.Post event) {
        TextureUtils.bindBlockTexture();
        ModelArmorInfinity.itempagewidth = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_WIDTH);
        ModelArmorInfinity.itempageheight = GL11.glGetTexLevelParameteri(GL11.GL_TEXTURE_2D, 0, GL11.GL_TEXTURE_HEIGHT);

        ModelArmorInfinity.armorModel.rebuildOverlay();
        ModelArmorInfinity.legModel.rebuildOverlay();
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        TextureAtlasSprite[] textures = AvaritiaTextures.COSMIC;

        if (CosmicShaderHelper.isHalloween) {
            textures = AvaritiaTextures.HALLOWEEN;
        }

        if (event.phase == TickEvent.Phase.START) {
            cosmicUVs = BufferUtils.createFloatBuffer(4 * textures.length);
            TextureAtlasSprite icon;
            for (TextureAtlasSprite cosmicIcon : textures) {
                icon = cosmicIcon;

                cosmicUVs.put(icon.getMinU());
                cosmicUVs.put(icon.getMinV());
                cosmicUVs.put(icon.getMaxU());
                cosmicUVs.put(icon.getMaxV());
            }
            cosmicUVs.flip();
        }
    }

    @SubscribeEvent
    public void onDrawScreenPre(GuiScreenEvent.DrawScreenEvent.Pre event) {
        CosmicShaderHelper.inventoryRender = true;
    }

    @SubscribeEvent
    public void drawScreenPost(GuiScreenEvent.DrawScreenEvent.Post event) {
        CosmicShaderHelper.inventoryRender = false;
    }
}

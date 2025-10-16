package morph.avaritia.client.render.shader;

import static morph.avaritia.handler.ConfigHandler.*;

import java.util.Calendar;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.lwjgl.opengl.ARBShaderObjects;

import morph.avaritia.client.AvaritiaClientEventHandler;
import morph.avaritia.handler.ConfigHandler;

public class CosmicShaderHelper {

    public static final ShaderCallback shaderCallback;
    public static final ShaderCallback alternateShaderCallback;

    public static float[] lightlevel = new float[3];

    public static boolean inventoryRender = false;

    public static float cosmicOpacity = 1.0f;

    public static boolean isHalloween = false;
    public static boolean isXmas = false;

    public static Calendar calendar = Calendar.getInstance();

    public static int month = calendar.get(2) + 1;
    public static int day = calendar.get(5);

    static {
        shaderCallback = new ShaderCallback() {

            @Override
            public void call(int shader) {
                // TODO, This can be optimized.
                Minecraft mc = Minecraft.getMinecraft();

                float yaw = 0;
                float pitch = 0;
                float scale = 1.0f;

                if (!inventoryRender) {
                    yaw = (float) ((mc.player.rotationYaw * 2 * Math.PI) / 360.0);
                    pitch = -(float) ((mc.player.rotationPitch * 2 * Math.PI) / 360.0);
                } else {
                    scale = 25.0f;
                }

                // Prevents Cosmic Opacity from being less than 1.0f
                // This fixes a Desync when Rendering Infinity Armor Cosmic Model and Items with Cosmic Opacity less
                // than 1.0f in JEI
                if (cosmicOpacity < 1.0f) {
                    cosmicOpacity = 1.0f;
                }

                // Seasonal Effects
                if (ConfigHandler.seasonal_effects) {
                    // Xmas
                    if (month == 10 && day >= 24 && day <= 26) {
                        isXmas = true;
                        cosmicChannelRed = 0.8f;
                        cosmicChannelGreen = 1.0f;
                        cosmicChannelBlue = 1.0f;
                    }

                    // Halloween
                    if (month == 10 && day >= 29) {
                        isHalloween = true;
                        cosmicChannelRed = 0.8f;
                        cosmicChannelGreen = 0.4f;
                        cosmicChannelBlue = 0.0f;
                    }
                }

                int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
                int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
                int l = ARBShaderObjects.glGetUniformLocationARB(shader, "lightlevel");
                int lightmix = ARBShaderObjects.glGetUniformLocationARB(shader, "lightmix");
                int uvs = ARBShaderObjects.glGetUniformLocationARB(shader, "cosmicuvs");
                int s = ARBShaderObjects.glGetUniformLocationARB(shader, "externalScale");
                int o = ARBShaderObjects.glGetUniformLocationARB(shader, "opacity");
                int r = ARBShaderObjects.glGetUniformLocationARB(shader, "channelRedBackground");
                int g = ARBShaderObjects.glGetUniformLocationARB(shader, "channelGreenBackground");
                int b = ARBShaderObjects.glGetUniformLocationARB(shader, "channelBlueBackground");

                ARBShaderObjects.glUniform1fARB(x, yaw);
                ARBShaderObjects.glUniform1fARB(z, pitch);
                ARBShaderObjects.glUniform3fARB(l, lightlevel[0], lightlevel[1], lightlevel[2]);
                ARBShaderObjects.glUniform1fARB(lightmix, 0.2f);
                ARBShaderObjects.glUniformMatrix2ARB(uvs, false, AvaritiaClientEventHandler.cosmicUVs);
                ARBShaderObjects.glUniform1fARB(s, scale);
                ARBShaderObjects.glUniform1fARB(o, cosmicOpacity);
                ARBShaderObjects.glUniform1fARB(r, cosmicChannelRed);
                ARBShaderObjects.glUniform1fARB(g, cosmicChannelGreen);
                ARBShaderObjects.glUniform1fARB(b, cosmicChannelBlue);
            }
        };
    }

    static {
        alternateShaderCallback = new ShaderCallback() {

            @Override
            public void call(int shader) {
                // Alternate Shader
                // Used to render an Item Model with Cosmic Effect While in toolbar

                float yaw = 0;
                float pitch = 0;
                float scale = 25.0f;

                // Prevents Cosmic Opacity from being less than 1.0f
                // This fixes Desync between Rendering Infinity Armor Cosmic Model and Items with Cosmic Opacity less
                // than 1.0f in JEI
                if (cosmicOpacity < 1.0f) {
                    cosmicOpacity = 1.0f;
                }

                // Seasonal Effects
                if (ConfigHandler.seasonal_effects) {
                    // Xmas
                    if (month == 12 && day >= 24 && day <= 26) {
                        isXmas = true;
                        cosmicChannelRed = 0.8f;
                        cosmicChannelGreen = 1.0f;
                        cosmicChannelBlue = 1.0f;
                    }

                    // Halloween
                    if (month == 10 && day >= 29) {
                        isHalloween = true;
                        cosmicChannelRed = 0.8f;
                        cosmicChannelGreen = 0.4f;
                        cosmicChannelBlue = 0.0f;
                    }
                }

                int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
                int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
                int l = ARBShaderObjects.glGetUniformLocationARB(shader, "lightlevel");
                int lightmix = ARBShaderObjects.glGetUniformLocationARB(shader, "lightmix");
                int uvs = ARBShaderObjects.glGetUniformLocationARB(shader, "cosmicuvs");
                int s = ARBShaderObjects.glGetUniformLocationARB(shader, "externalScale");
                int o = ARBShaderObjects.glGetUniformLocationARB(shader, "opacity");
                int r = ARBShaderObjects.glGetUniformLocationARB(shader, "channelRedBackground");
                int g = ARBShaderObjects.glGetUniformLocationARB(shader, "channelGreenBackground");
                int b = ARBShaderObjects.glGetUniformLocationARB(shader, "channelBlueBackground");

                ARBShaderObjects.glUniform1fARB(x, yaw);
                ARBShaderObjects.glUniform1fARB(z, pitch);
                ARBShaderObjects.glUniform3fARB(l, lightlevel[0], lightlevel[1], lightlevel[2]);
                ARBShaderObjects.glUniform1fARB(lightmix, 0.2f);
                ARBShaderObjects.glUniformMatrix2ARB(uvs, false, AvaritiaClientEventHandler.cosmicUVs);
                ARBShaderObjects.glUniform1fARB(s, scale);
                ARBShaderObjects.glUniform1fARB(o, cosmicOpacity);
                ARBShaderObjects.glUniform1fARB(o, cosmicOpacity);
                ARBShaderObjects.glUniform1fARB(r, cosmicChannelRed);
                ARBShaderObjects.glUniform1fARB(g, cosmicChannelGreen);
                ARBShaderObjects.glUniform1fARB(b, cosmicChannelBlue);
            }
        };
    }

    public static void useShader() {
        ShaderHelper.useShader(ShaderHelper.cosmicShader, shaderCallback);
    }

    public static void useAlternateShader() {
        ShaderHelper.useShader(ShaderHelper.cosmicShader, alternateShaderCallback);
    }

    public static void releaseShader() {
        ShaderHelper.releaseShader();
    }

    public static void setLightFromLocation(World world, BlockPos pos) {
        // This is a temporary fix for Light From Location

        if (world == null) {
            setLightLevel(1.0f);
            return;
        }

        if (pos == null) {
            setLightLevel(0.0f);
            return;
        }

        int coord = world.getLight(pos, true);

        int mx = (coord % 65536) / 16;
        int my = (coord / 65536) / 16;

        int lightcolour = my * 16 + mx;

        setLightLevel(((lightcolour >> 16) & 0xFF) / 256.0f, ((lightcolour >> 8) & 0xFF) / 256.0f,
                ((lightcolour) & 0xFF) / 256.0f);
    }

    public static void setLightLevel(float level) {
        setLightLevel(level, level, level);
    }

    public static void setLightLevel(float r, float g, float b) {
        lightlevel[0] = Math.max(0.0f, Math.min(1.0f, r));
        lightlevel[1] = Math.max(0.0f, Math.min(1.0f, g));
        lightlevel[2] = Math.max(0.0f, Math.min(1.0f, b));
    }
}

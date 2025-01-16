package morph.avaritia.client.render.shader;

import morph.avaritia.client.AvaritiaClientEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.opengl.ARBShaderObjects;

public class CosmicShaderHelper {

    public static final ShaderCallback shaderCallback;
    public static final ShaderCallback alternateShaderCallback;

    public static float[] lightlevel = new float[3];

    public static boolean inventoryRender = false;
    public static float cosmicOpacity = 1.0f;

    static {
        shaderCallback = new ShaderCallback() {
            @Override
            public void call(int shader) {
                //TODO, This can be optimized.
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

                int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
                int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
                int l = ARBShaderObjects.glGetUniformLocationARB(shader, "lightlevel");
                int lightmix = ARBShaderObjects.glGetUniformLocationARB(shader, "lightmix");
                int uvs = ARBShaderObjects.glGetUniformLocationARB(shader, "cosmicuvs");
                int s = ARBShaderObjects.glGetUniformLocationARB(shader, "externalScale");
                int o = ARBShaderObjects.glGetUniformLocationARB(shader, "opacity");

                ARBShaderObjects.glUniform1fARB(x, yaw);
                ARBShaderObjects.glUniform1fARB(z, pitch);
                ARBShaderObjects.glUniform3fARB(l, lightlevel[0], lightlevel[1], lightlevel[2]);
                ARBShaderObjects.glUniform1fARB(lightmix, 0.2f);
                ARBShaderObjects.glUniformMatrix2ARB(uvs, false, AvaritiaClientEventHandler.cosmicUVs);
                ARBShaderObjects.glUniform1fARB(s, scale);
                ARBShaderObjects.glUniform1fARB(o, cosmicOpacity);
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

                int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
                int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
                int l = ARBShaderObjects.glGetUniformLocationARB(shader, "lightlevel");
                int lightmix = ARBShaderObjects.glGetUniformLocationARB(shader, "lightmix");
                int uvs = ARBShaderObjects.glGetUniformLocationARB(shader, "cosmicuvs");
                int s = ARBShaderObjects.glGetUniformLocationARB(shader, "externalScale");
                int o = ARBShaderObjects.glGetUniformLocationARB(shader, "opacity");

                ARBShaderObjects.glUniform1fARB(x, yaw);
                ARBShaderObjects.glUniform1fARB(z, pitch);
                ARBShaderObjects.glUniform3fARB(l, lightlevel[0], lightlevel[1], lightlevel[2]);
                ARBShaderObjects.glUniform1fARB(lightmix, 0.2f);
                ARBShaderObjects.glUniformMatrix2ARB(uvs, false, AvaritiaClientEventHandler.cosmicUVs);
                ARBShaderObjects.glUniform1fARB(s, scale);
                ARBShaderObjects.glUniform1fARB(o, cosmicOpacity);
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

        setLightLevel(((lightcolour >> 16) & 0xFF) / 256.0f, ((lightcolour >> 8) & 0xFF) / 256.0f, ((lightcolour) & 0xFF) / 256.0f);
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

package morph.avaritia.compat;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import vazkii.botania.api.BotaniaAPIClient;

public class CompatClient {

    public static void compatPreInit() {
        BotaniaAPIClient.registerSubtileModel("asgardandelion",
                new ModelResourceLocation("botania:" + "asgardandelion"));
    }
}

package morph.avaritia.recipe;

import java.util.function.BooleanSupplier;

import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;

import com.google.gson.JsonObject;

public class ModExistsConditionalFactory implements IConditionFactory {

    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        return () -> Loader.isModLoaded(JsonUtils.getString(json, "mod"));
    }
}

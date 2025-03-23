package morph.avaritia.recipe;

import java.util.function.BooleanSupplier;

import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.OreDictionary;

import com.google.gson.JsonObject;

public class OreExistsSafeConditionalFactory implements IConditionFactory {

    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        String oreDict = JsonUtils.getString(json, "ore");

        return () -> OreDictionary.doesOreNameExist(oreDict) && !OreDictionary.getOres(oreDict).isEmpty();
    }
}

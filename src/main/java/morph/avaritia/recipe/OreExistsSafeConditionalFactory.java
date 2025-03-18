package morph.avaritia.recipe;

import com.google.gson.JsonObject;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.OreDictionary;

import java.util.function.BooleanSupplier;

public class OreExistsSafeConditionalFactory implements IConditionFactory {

    @Override
    public BooleanSupplier parse(JsonContext context, JsonObject json) {
        String oreDict = JsonUtils.getString(json, "ore");

        return () -> OreDictionary.doesOreNameExist(oreDict) && !OreDictionary.getOres(oreDict).isEmpty() ;
    }
}

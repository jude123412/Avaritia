package morph.avaritia.compat.botania;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.IAddonEntry;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;

public class LudicrousLexicon extends LexiconEntry implements IAddonEntry {

    public LudicrousLexicon(String unlocalizedName, LexiconCategory category) {
        super(unlocalizedName, category);
        BotaniaAPI.addEntry(this, category);
    }

    @Override
    public String getSubtitle() {
        return "[Avaritia]";
    }

    @Override
    public String getUnlocalizedName() {
        return "avaritia.lexicon." + super.getUnlocalizedName();
    }
}

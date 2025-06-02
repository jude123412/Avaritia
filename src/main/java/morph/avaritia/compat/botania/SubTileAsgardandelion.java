package morph.avaritia.compat.botania;

import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileGenerating;

public class SubTileAsgardandelion extends SubTileGenerating {

    public static LexiconEntry lexiconEntry;

    private int maxMana = 1000000;

    @Override
    public boolean canGeneratePassively() {
        return true;
    }

    @Override
    public int getDelayBetweenPassiveGeneration() {
        return 1;
    }

    @Override
    public int getValueForPassiveGeneration() {
        return maxMana - mana;
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public int getColor() {
        return 0x11FF00;
    }

    public LexiconEntry getEntry() {
        return lexiconEntry;
    }
}

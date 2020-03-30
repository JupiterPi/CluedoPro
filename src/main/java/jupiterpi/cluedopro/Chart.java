package jupiterpi.cluedopro;

import java.util.HashMap;
import java.util.Map;

public class Chart {
    Map<MysteryCard, ChartEntry> entries = new HashMap<>();

    public Chart() {
        StaticResourcesManager.getMysteryCards().forEach((mysteryCard) -> {
            entries.put(mysteryCard, new ChartEntry(mysteryCard));
        });
    }

    public ChartEntry getEntry(MysteryCard mysteryCard) {
        return entries.get(mysteryCard);
    }
}

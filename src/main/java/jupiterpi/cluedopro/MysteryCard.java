package jupiterpi.cluedopro;

import java.util.ArrayList;
import java.util.List;

public class MysteryCard {
    MysteryCardType type;
    String name;
    String shortName;

    public MysteryCard(MysteryCardType type, String name, String shortName) {
        this.type = type;
        this.name = name;
        this.shortName = shortName;
    }

    public MysteryCardType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    enum MysteryCardType {
        SUSPECT, ITEM, LOCATION
    }

    public String toString() {
        return name;
    }

    public static List<MysteryCard> load() {
        List<String> mysteryCardsData = StaticResources.getResourceFile("mysteryCards").getFile();
        List<MysteryCard> mysteryCards = new ArrayList<>();
        mysteryCardsData.forEach((line) -> {
            String[] lineParts = line.split(";");
            MysteryCard.MysteryCardType type = MysteryCard.MysteryCardType.valueOf(lineParts[0]);
            mysteryCards.add(new MysteryCard(type, lineParts[1], lineParts[2]));
        });
        return mysteryCards;
    }
}

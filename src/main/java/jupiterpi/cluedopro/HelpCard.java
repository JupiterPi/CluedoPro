package jupiterpi.cluedopro;

public class HelpCard {
    HelpCardType type;
    String name;

    public HelpCard(HelpCardType type, String name) {
        this.type = type;
        this.name = name;
    }

    public HelpCardType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    enum HelpCardType {
        SPELL, ITEM, ALLY;
    }
}

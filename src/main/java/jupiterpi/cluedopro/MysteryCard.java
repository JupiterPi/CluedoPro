package jupiterpi.cluedopro;

public class MysteryCard {
    MysteryCardType type;
    String name;

    public MysteryCard(MysteryCardType type, String name) {
        this.type = type;
        this.name = name;
    }

    public MysteryCardType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    enum MysteryCardType {
        SUSPECT, ITEM, LOCATION
    }

    public String toString() {
        return name;
    }
}

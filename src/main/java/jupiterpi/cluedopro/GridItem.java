package jupiterpi.cluedopro;

public class GridItem {
    private GridItemType type;
    private MysteryCard correspondingMysteryCard;

    public GridItem(GridItemType type) {
        this.type = type;
    }

    public GridItem(GridItemType type, MysteryCard correspondingMysteryCard) {
        this.type = type;
        this.correspondingMysteryCard = correspondingMysteryCard;
    }

    public GridItemType getType() {
        return type;
    }

    public MysteryCard getCorrespondingMysteryCard() {
        return correspondingMysteryCard;
    }

    enum GridItemType {
        FIELD, LOCATION, LOCATION_ENTRANCE
    }
}
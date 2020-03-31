package jupiterpi.cluedopro;

import java.util.List;

public class LocationField extends Field {
    MysteryCard correspondingMysteryCard;

    public LocationField(List<Field> connections, MysteryCard correspondingMysteryCard) {
        super(connections);
        this.correspondingMysteryCard = correspondingMysteryCard;
    }

    @Override
    public void explore(Path path) {
        if (path.getTarget().equals(correspondingMysteryCard)) path.end(ExplorationState.SUCCESS);
        else super.explore(path);
    }
}

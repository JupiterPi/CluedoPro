package jupiterpi.cluedopro;

import java.util.ArrayList;
import java.util.List;

public class LocationField extends Field {
    MysteryCard correspondingMysteryCard;

    public LocationField(List<String> connectionsPointers, MysteryCard correspondingMysteryCard) {
        super(connectionsPointers);
        this.correspondingMysteryCard = correspondingMysteryCard;
    }

    public void addConnectionsPointers(List<String> pointer) {
        connectionsPointers.addAll(pointer);
    }

    @Override
    public List<Path> explore(Path path) {
        if (path.getTarget().equals(correspondingMysteryCard)) {
            path.end(ExplorationState.SUCCESS);
            List<Path> paths = new ArrayList<>();
            paths.add(path);
            return paths;
        } else {
            return super.explore(path);
        }
    }
}

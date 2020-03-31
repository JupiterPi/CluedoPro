package jupiterpi.cluedopro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exploration {
    Field origin;
    MysteryCard target;
    List<Path> paths = new ArrayList<>();

    public Exploration(Field origin, MysteryCard target) {
        this.origin = origin;
        this.target = target;
    }

    public Path run() {
        paths = origin.explore(new Path(origin, target));
        Collections.sort(paths);
        return paths.get(0);
    }
}

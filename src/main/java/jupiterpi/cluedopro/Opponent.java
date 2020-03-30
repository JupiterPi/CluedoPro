package jupiterpi.cluedopro;

import java.util.ArrayList;
import java.util.List;

public class Opponent extends Player {
    List<MysteryCard> knownOfMyMysteryCards = new ArrayList<>();

    public Opponent(String name, PlayerColor color) {
        super(name, color);
    }
}

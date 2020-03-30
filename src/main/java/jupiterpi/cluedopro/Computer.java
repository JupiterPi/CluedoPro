package jupiterpi.cluedopro;

import java.util.ArrayList;
import java.util.List;

public class Computer extends Player {
    List<MysteryCard> mysteryCards = new ArrayList<>();
    Chart chart = new Chart();

    public Computer(PlayerColor color) {
        super("Computer", color);
    }

    public void addMysteryCards(List<MysteryCard> mysteryCards) {
        this.mysteryCards.addAll(mysteryCards);
    }

    public List<MysteryCard> getMysteryCards() {
        return mysteryCards;
    }
}

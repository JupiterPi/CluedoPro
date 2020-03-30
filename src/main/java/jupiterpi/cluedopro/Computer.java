package jupiterpi.cluedopro;

import java.util.ArrayList;
import java.util.List;

public class Computer extends Player {
    List<HelpCard> helpCards = new ArrayList<>();
    List<MysteryCard> mysteryCards;
    Chart chart = new Chart();

    public Computer(String name, List<MysteryCard> mysteryCards, HelpCard initialHelpCard) {
        super(name);
        this.mysteryCards = mysteryCards;
        helpCards.add(initialHelpCard);
    }
}

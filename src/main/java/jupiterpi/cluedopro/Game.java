package jupiterpi.cluedopro;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players = new ArrayList<>();
    private Computer computer;

    // initiation

    public void addOpponent(String name, Player.PlayerColor color) {
        players.add(new Opponent(name, color));
    }

    public void addComputer(Player.PlayerColor color) {
        Computer computer = new Computer(color);
        this.computer = computer;
        players.add(computer);
    }

    public void addComputersMysteryCards(List<String> mysteryCardNames) {
        List<MysteryCard> mysteryCards = new ArrayList<>();
        mysteryCardNames.forEach((mysteryCardName) -> {
            mysteryCards.add(StaticResourcesManager.findMysteryCardByName(mysteryCardName));
        });
        computer.addMysteryCards(mysteryCards);
    }

    //

    public List<Player> getPlayers() {
        return players;
    }

    public Computer getComputer() {
        return computer;
    }
}

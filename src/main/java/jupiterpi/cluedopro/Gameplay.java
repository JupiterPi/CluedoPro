package jupiterpi.cluedopro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Gameplay {
    private static Game game;

    public static Player play() {
        // pre-initiation
        game = new Game();
        StaticResources.load();

        // welcome
        out("Welcome to CluedoPro!");
        dnewline();

        initiate();

        dnewline();
        out("finished");
        return game.getComputer();
    }

    private static void initiate() {
        List<Player.PlayerColor> availableColors = new ArrayList<>();
        availableColors.addAll(Player.getColors());

        // players
        while (true) {
            // > player name
            String playerInputMessage = "Add a player";
            boolean computerRequired = true;
            for (Player player : game.getPlayers()) {
                if (player instanceof Computer) computerRequired = false;
            }
            boolean complete = (game.getPlayers().size() > 1) && !computerRequired;
            if (computerRequired || complete) {
                playerInputMessage += " (type ";
                if (computerRequired) playerInputMessage += "c to add computer";
                if (computerRequired && complete) playerInputMessage += ", ";
                if (complete) playerInputMessage += "f to finish";
                playerInputMessage += ")";
            }
            playerInputMessage += ": ";
            String playerName = in(playerInputMessage);
            if (playerName.equals("f")) break;

            // > color
            String colorInputMessage = "Choose a color (available: ";
            for (Player.PlayerColor availableColor : availableColors) {
                colorInputMessage += availableColor.toString().toLowerCase() + ", ";
            }
            colorInputMessage = colorInputMessage.substring(0, colorInputMessage.length()-2);
            colorInputMessage += "): ";
            Player.PlayerColor color = Player.PlayerColor.valueOf(in(colorInputMessage).toUpperCase());
            availableColors.remove(color);

            // > adding the player
            if (playerName.equals("c")) game.addComputer(color);
            else game.addOpponent(playerName, color);

            newline();
        }

        newline();

        // > print players
        String playersOutput = "";
        playersOutput += ("Players: ");
        for (Player player : game.getPlayers()) {
            playersOutput += player.toString() + ", ";
        }
        playersOutput = playersOutput.substring(0, playersOutput.length()-2);
        out(playersOutput);

        dnewline();

        // computer's mystery cards
        List<MysteryCard> mysteryCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String mysteryCardName = in("Add one of the computer's mystery cards: ");
            mysteryCards.add(StaticResources.findMysteryCardByName(mysteryCardName));
        }
        game.getComputer().addMysteryCards(mysteryCards);

        newline();

        // > > print computer's mystery cards
        String mysteryCardsOutput = "";
        mysteryCardsOutput += ("Computer's mystery cards: ");
        for (MysteryCard mysteryCard : game.getComputer().getMysteryCards()) {
            mysteryCardsOutput += mysteryCard.toString() + ", ";
        }
        mysteryCardsOutput = mysteryCardsOutput.substring(0, mysteryCardsOutput.length()-2);
        out(mysteryCardsOutput);
    }

    // console functions

    private static void out(String message) {
        System.out.println(message);
    }

    private static void print(String message) {
        System.out.print(message);
    }

    private static void newline() {
        out("");
    }

    private static void dnewline() {
        newline();
        newline();
    }

    private static String in(String message) {
        System.out.print(message);
        return in();
    }

    private static String in() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    // test

    public static void test() {
        List<String> names = new ArrayList<>();
        while(true) {
            String name = in("name: ");
            if (name.equals("f")) break;
            names.add(name);
        }
        for (String name : names) {
            out("TYPE;" + name);
        }
    }
}

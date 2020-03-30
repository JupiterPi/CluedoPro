package jupiterpi.cluedopro;

import java.util.Arrays;
import java.util.List;

public class Player {
    String name;
    PlayerColor color;

    public Player(String name, PlayerColor color) {
        this.name = name;
        this.color = color;
    }

    public static List<PlayerColor> getColors() {
        PlayerColor[] colors = PlayerColor.class.getEnumConstants();
        return Arrays.asList(colors);
    }

    enum PlayerColor {
        GREEN, BLUE, WHITE, VIOLET, YELLOW, RED;
    }

    public String toString() {
        return name + " (" + color.toString().toLowerCase() + ")";
    }
}

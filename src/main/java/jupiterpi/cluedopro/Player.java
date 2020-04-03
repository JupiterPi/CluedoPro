package jupiterpi.cluedopro;

import java.util.Arrays;
import java.util.List;

public class Player {
    String name;
    PlayerColor color;
    Field position;

    public Player(String name, PlayerColor color) {
        this.name = name;
        this.color = color;
    }

    public static List<PlayerColor> getColors() {
        PlayerColor[] colors = PlayerColor.class.getEnumConstants();
        return Arrays.asList(colors);
    }

    public Field getPosition() {
        return position;
    }

    public void move(Field position) {
        this.position = position;
    }

    enum PlayerColor {
        GREEN, BLUE, WHITE, VIOLET, YELLOW, RED;
    }

    public String toString() {
        return name + " (" + color.toString().toLowerCase() + ")";
    }
}

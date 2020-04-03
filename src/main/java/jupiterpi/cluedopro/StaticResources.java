package jupiterpi.cluedopro;

import jupiterpi.cluedopro.filetool.FileTool;

import java.io.File;
import java.net.URL;
import java.util.List;

public class StaticResources {
    static List<MysteryCard> mysteryCards;
    public static final float TURN = 4.5f;
    static List<Field> fields;

    public static void load() {
        mysteryCards = MysteryCard.load();
        fields = Field.load();
    }

    public static List<MysteryCard> getMysteryCards() {
        return mysteryCards;
    }

    public static MysteryCard findMysteryCardByName(String name) {
        for (MysteryCard mysteryCard : mysteryCards) {
            if (mysteryCard.getName().equals(name)) return mysteryCard;
        }
        return null;
    }

    public static MysteryCard findMysteryCardByShortName(String shortName) {
        for (MysteryCard mysteryCard : mysteryCards) {
            if (mysteryCard.getShortName().equals(shortName)) return mysteryCard;
        }
        return null;
    }

    public static List<Field> getFields() {
        return fields;
    }

    public static FileTool getResourceFile(String fileName) {
        ClassLoader classLoader = StaticResources.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found!");
        } else {
            File file = new File(resource.getFile());
            FileTool fileTool = new FileTool(file);
            return fileTool;
        }
    }
}

package jupiterpi.cluedopro;

import jupiterpi.cluedopro.filetool.FileTool;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StaticResources {
    static List<MysteryCard> mysteryCards = new ArrayList<>();
    public static final float TURN = 4.5f;

    public static void load() {
        File resourceFile = getResourceFile("mysteryCards.txt");
        FileTool fileTool = new FileTool(resourceFile);
        List<String> mysteryCardsData = fileTool.getFile();
        mysteryCardsData.forEach((line) -> {
            String[] lineParts = line.split(";");
            MysteryCard.MysteryCardType type = MysteryCard.MysteryCardType.valueOf(lineParts[0]);
            mysteryCards.add(new MysteryCard(type, lineParts[1]));
        });
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

    private static File getResourceFile(String fileName) {
        ClassLoader classLoader = StaticResources.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}

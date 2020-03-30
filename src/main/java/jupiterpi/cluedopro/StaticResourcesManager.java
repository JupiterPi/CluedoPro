package jupiterpi.cluedopro;

import jupiterpi.cluedopro.filetool.FileTool;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StaticResourcesManager {
    static List<MysteryCard> mysteryCards = new ArrayList<>();

    public StaticResourcesManager() {
        List<String> mysteryCardsData = new FileTool(getResourceFile("mysteryCards.txt")).getFile();
        mysteryCardsData.forEach((line) -> {
            String[] lineParts = line.split(";");
            MysteryCard.MysteryCardType type = MysteryCard.MysteryCardType.valueOf(lineParts[0]);
            mysteryCards.add(new MysteryCard(type, lineParts[1]));
        });
    }

    public static List<MysteryCard> getMysteryCards() {
        return mysteryCards;
    }

    private static File getResourceFile(String fileName) {
        ClassLoader classLoader = StaticResourcesManager.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}

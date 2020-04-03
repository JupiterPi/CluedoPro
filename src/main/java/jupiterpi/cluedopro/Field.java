package jupiterpi.cluedopro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jupiterpi.cluedopro.GridItem.GridItemType;

public class Field {
    List<String> connectionsPointers;
    List<Field> connections;
    List<Field> disabledConnections = new ArrayList<>();
    Player takenBy = null;

    public Field(List<String> connectionsPointers) {
        this.connectionsPointers = connectionsPointers;
    }

    public void evaluatePointers(Map<String, Field> fields) {
        for (String connectionPointer : connectionsPointers) {
            connections.add(fields.get(connectionPointer));
        }
    }

    public void disableConnection(Field to) {
        connections.remove(to);
        disabledConnections.add(to);
    }

    public boolean taken() {
        return takenBy != null;
    }

    public Player takenBy() {
        return takenBy;
    }

    public List<Path> explore(Path path) {
        path.addField(this);

        List<Field> availableConnections = new ArrayList<>(connections);
        availableConnections.remove(path.latestCaller());

        List<Path> paths = new ArrayList<>();
        paths.add(path);

        if (availableConnections.size() > 0) {
            paths.addAll(availableConnections.get(0).explore(path));
            if (availableConnections.size() > 1) {
                availableConnections.remove(0);
                for (Field connection : availableConnections) {
                    paths.addAll(connection.explore(path.split()));
                }
            }
        } else {
            path.end(ExplorationState.DEAD_END);
        }

        return paths;
    }

    public static List<Field> load() {
        List<String> rows = StaticResources.getResourceFile("gameplan.txt").getFile();
        Map<Coordinates, GridItem> grid = new HashMap<>();
        for (int y = 0; y < rows.size(); y++) {
            String row = rows.get(y);
            String[] rowFields = row.split(",");
            for (int x = 0; x < rowFields.length; x++) {
                String rowField = rowFields[x];
                Coordinates coordinates = new Coordinates(x, y);
                if (rowField.equals("f")) grid.put(coordinates, new GridItem(GridItem.GridItemType.FIELD));
                else if (rowField.substring(0, 1).equals("l")) {
                    MysteryCard correspondingMysteryCard = StaticResources.findMysteryCardByShortName(rowField.substring(1, 4));
                    if (rowField.length() == 4) grid.put(coordinates, new GridItem(GridItem.GridItemType.LOCATION, correspondingMysteryCard));
                    else grid.put(coordinates, new GridItem(GridItem.GridItemType.LOCATION_ENTRANCE, correspondingMysteryCard));
                }
            }
        }
        Map<String, Field> fields = new HashMap<>();
        for (GridItem gridItem : grid.values()) {
            GridItemType type = gridItem.getType();
            if (type == GridItemType.FIELD) {
                List<String> connectionsPointers = getConnectionsPointers(grid, gridItem);
                Coordinates coordinates = getKey(grid, gridItem);
                String pointer = getPointer(grid, coordinates);
                fields.put(pointer, new Field(connectionsPointers));
            } else if (type == GridItemType.LOCATION_ENTRANCE) {
                String pointer = getPointer(grid, getKey(grid, gridItem));
                List<String> connectionsPointers = getConnectionsPointers(grid, gridItem);
                if (!fields.containsKey(pointer)) {
                    fields.put(pointer, new LocationField(connectionsPointers, gridItem.getCorrespondingMysteryCard()));
                } else {
                    LocationField field = (LocationField) fields.get(pointer);
                    field.addConnectionsPointers(connectionsPointers);
                }
            }
        }
        for (Field field : fields.values()) {
            field.evaluatePointers(fields);
        }
        return (List<Field>) fields.values();
    }

    private static List<String> getConnectionsPointers(Map<Coordinates, GridItem> grid, GridItem gridItem) {
        List<String> connectionsPointers = new ArrayList<>();
        Coordinates coordinates = getKey(grid, gridItem);
        int x = coordinates.x(), y = coordinates.y();
        int[][] pointerCoordinatePairs = {{x, y + 1}, {x, y - 1}, {x + 1, y}, {x - 1, y}};
        for (int[] pointerCoordinatePair : pointerCoordinatePairs) {
            Coordinates pointerCoordinates = new Coordinates(pointerCoordinatePair[0], pointerCoordinatePair[0]);
            String connectionPointer = getPointer(grid, coordinates);
            if (connectionPointer != "") connectionsPointers.add(connectionPointer);
        }
        return connectionsPointers;
    }

    private static String getPointer(Map<Coordinates, GridItem> grid, Coordinates coordinates) {
        GridItem gridItem = grid.get(coordinates);
        switch (gridItem.getType()) {
            case FIELD: return coordinates.x() + "," + coordinates.y();
            case LOCATION_ENTRANCE: return gridItem.getCorrespondingMysteryCard().getShortName();
            default: return "";
        }
    }

    private static Coordinates getKey(Map<Coordinates, GridItem> grid, GridItem searchValue) {
        for (Coordinates key : grid.keySet()) {
            GridItem value = grid.get(key);
            if (value == searchValue) return key;
        }
        return null;
    }
}
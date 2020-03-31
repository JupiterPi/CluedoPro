package jupiterpi.cluedopro;

import java.util.ArrayList;
import java.util.List;

public class Field {
    List<Field> connections = new ArrayList<>();
    List<Field> disabledConnections = new ArrayList<>();

    public Field(List<Field> connections) {
        this.connections = connections;
    }

    public List<Field> getConnections() {
        return connections;
    }

    public void disableConnection(Field to) {
        connections.remove(to);
        disabledConnections.add(to);
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
}
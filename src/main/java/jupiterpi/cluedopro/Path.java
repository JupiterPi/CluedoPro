package jupiterpi.cluedopro;

import java.util.ArrayList;
import java.util.List;

public class Path implements Comparable {
    List<Field> fields = new ArrayList<>();
    MysteryCard target;
    ExplorationState state;

    public Path(Field field, MysteryCard target) {
        fields.add(field);
        this.target = target;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    public MysteryCard getTarget() {
        return target;
    }

    public Path split() {
        try {
            return (Path) this.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e);
        }
        return null;
    }

    public Field latestCaller() {
        return fields.get(fields.size()-1);
    }

    public void end(ExplorationState state) {
        this.state = state;
    }

    public List<Field> getLocationFields() {
        List<Field> locationFields = new ArrayList<>();
        for (Field field : fields) {
            if (field instanceof LocationField) locationFields.add(field);
        }
        return locationFields;
    }

    public int estimatedTurns() {
        List<Field> locationFields = getLocationFields();
        List<List<Field>> fieldsParts = new ArrayList<>();
        List<Integer> locationFieldIndices = new ArrayList<>();
        for (Field locationField : locationFields) {
            locationFieldIndices.add(locationFields.indexOf(locationField));
        }
        for (int i = 0; i < locationFieldIndices.size(); i++) {
            int locationFieldIndex = locationFieldIndices.get(i);
            int lastLocationFieldIndex = 0;
            if (i > 0) lastLocationFieldIndex = locationFieldIndices.get(i-1);
            fieldsParts.add(fields.subList(lastLocationFieldIndex, locationFieldIndex));
        }

        int turnsNumber = 0;
        for (List<Field> fieldsPart : fieldsParts) {
            int fieldsNumber = fieldsPart.size();
            turnsNumber += (int) (fieldsNumber / StaticResources.TURN);
        }

        return turnsNumber;
    }

    @Override
    public int compareTo(Object o) {
        Path otherPath = (Path) o;
        Integer thisTurns = estimatedTurns();
        Integer otherTurns = otherPath.estimatedTurns();
        return thisTurns.compareTo(otherTurns);
    }
}

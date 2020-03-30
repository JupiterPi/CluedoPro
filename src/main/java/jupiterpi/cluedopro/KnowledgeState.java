package jupiterpi.cluedopro;

public class KnowledgeState {
    PrimitiveKnowledgeState state = PrimitiveKnowledgeState.UNKNOWN;
    int choiceId;

    public KnowledgeState() {}

    public PrimitiveKnowledgeState getState() {
        return state;
    }

    public int getChoiceId() {
        return choiceId;
    }

    public void setState(PrimitiveKnowledgeState state) {
        this.state = state;
    }

    public void setChoiceState(int choiceId) {
        state = PrimitiveKnowledgeState.CHOICE;
        this.choiceId = choiceId;
    }

    enum PrimitiveKnowledgeState {
        UNKNOWN, NOT, CHOICE, HAS
    }
}
package jupiterpi.cluedopro;

import java.util.HashMap;
import java.util.Map;

public class ChartEntry {
    MysteryCard mysteryCard;
    Map<Player, KnowledgeState> knowledge = new HashMap<>();

    public ChartEntry(MysteryCard mysteryCard) {
        this.mysteryCard = mysteryCard;
        knowledge.forEach((key, value) -> {
            value = new KnowledgeState();
        });
    }

    public MysteryCard getMysteryCard() {
        return mysteryCard;
    }

    public KnowledgeState getKnowledgeState(Player player) {
        return knowledge.get(player);
    }

    public void unknown(Player player) {
        knowledge.get(player).setState(KnowledgeState.PrimitiveKnowledgeState.UNKNOWN);
    }

    public void not(Player player) {
        knowledge.get(player).setState(KnowledgeState.PrimitiveKnowledgeState.NOT);
    }

    public void choice(Player player, int choiceId) {
        knowledge.get(player).setChoiceState(choiceId);
    }

    public void has(Player player) {
        knowledge.forEach((key, value) -> {
            if (key != player) value.setState(KnowledgeState.PrimitiveKnowledgeState.NOT);
        });
        knowledge.get(player).setState(KnowledgeState.PrimitiveKnowledgeState.HAS);
    }
}
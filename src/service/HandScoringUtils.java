package service;

import model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HandScoringUtils {

    public static List<Card> orderCardsByRank(List<Card> cards) {
        return cards.stream().sorted((a, b) -> b.getRankInt() - a.getRankInt()).collect(Collectors.toList());
    }

    public static List<Card> cloneCards(List<Card> List) {
        List<Card> clone = new ArrayList<>();
        clone.addAll(List);
        return clone;
    }

    public static Card getHighestCard(List<Card> cards) {
        return orderCardsByRank(cards).get(0);

    }
}

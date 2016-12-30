package service;

import model.Card;
import model.Player;

import java.util.List;

public class CardsComparator {

    public static int getWinnerIndex(Player[] players) {
        Player playerOne = players[0];
        Player playerTwo = players[1];

        if (playerOne.getRanking().ordinal() > playerTwo.getRanking().ordinal()) {
            return 1;
        } else if (playerOne.getRanking().ordinal() < playerTwo.getRanking().ordinal()) {
            return 2;
        } else {
            List<Card> cardsToCheck = playerOne.getCards();
            cardsToCheck.removeAll(playerOne.getRankingCards());
            List<Card> cardsToCheck2 = playerTwo.getCards();
            cardsToCheck2.removeAll(playerTwo.getRankingCards());
            return comparePlayersCards(cardsToCheck, cardsToCheck2);
        }
    }

    private static int comparePlayersCards(List<Card> p1cards, List<Card> p2cards) {
        Card p1card = HandScoringUtils.getHighestCard(p1cards);
        Card p2card = HandScoringUtils.getHighestCard(p2cards);
        if (p1card.getRank() == p2card.getRank()) {
            p1cards.remove(p1card);
            p2cards.remove(p2card);
            return comparePlayersCards(p1cards, p2cards);
        } else if (p1card.getRankInt() > p2card.getRankInt())
            return 1;
        else
            return 2;
    }

}

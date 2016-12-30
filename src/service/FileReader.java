package service;

import model.Card;
import model.CardRankEnum;
import model.CardSuitEnum;
import model.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileReader {
    private final String fileName = "poker.txt";

    public Set<Player[]> getSetOfGames() throws IOException {
        return Files.lines(Paths.get(fileName)).map(this::getPlayersWithCards).collect(Collectors.toSet());
    }
    private Player[] getPlayersWithCards(String singleLine) {
        List<Card> hand1 = new ArrayList<>();
        List<Card> hand2 = new ArrayList<>();
        String[] cards = singleLine.split(" ");
        int i = 0;
        for (String s : cards) {
            if (i < 5)
                hand1.add(readCard(s));
            else
                hand2.add(readCard(s));
            i++;
        }
        return new Player[]{new Player(hand1), new Player(hand2)};
    }

    private Card readCard(String cardString) {
        assert cardString.length() >= 2;
        if (cardString.length() == 3)
            return new Card(CardRankEnum.TEN, getSuit(cardString.substring(2)));
        return new Card(getRank(cardString.substring(0, 1)), getSuit(cardString.substring(1)));
    }

    private CardRankEnum getRank(String rankString) {
        switch (rankString) {
            case "2":
                return CardRankEnum.TWO;
            case "3":
                return CardRankEnum.THREE;
            case "4":
                return CardRankEnum.FOUR;
            case "5":
                return CardRankEnum.FIVE;
            case "6":
                return CardRankEnum.SIX;
            case "7":
                return CardRankEnum.SEVEN;
            case "8":
                return CardRankEnum.EIGHT;
            case "9":
                return CardRankEnum.NINE;
            case "T":
                return CardRankEnum.TEN;
            case "J":
                return CardRankEnum.JACK;
            case "Q":
                return CardRankEnum.QUEEN;
            case "K":
                return CardRankEnum.KING;
            case "A":
                return CardRankEnum.ACE;
            default:
                throw new RuntimeException("no CardRankEnum match " + rankString);
        }
    }
    private CardSuitEnum getSuit(String suitString) {
        switch (suitString) {
            case "D":
                return CardSuitEnum.DIAMONDS;
            case "C":
                return CardSuitEnum.CLUBS;
            case "S":
                return CardSuitEnum.SPADES;
            case "H":
                return CardSuitEnum.HEARTS;
            default:
                throw new RuntimeException("no CardSuitEnum match " + suitString);
        }
    }
}

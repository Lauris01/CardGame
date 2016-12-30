package model;

public class Card {

    private CardRankEnum rank;
    private CardSuitEnum suit;

    public Card(CardRankEnum rank, CardSuitEnum suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardRankEnum getRank() {
        return rank;
    }

    public void setRank(CardRankEnum rank) {
        this.rank = rank;
    }

    public int getRankInt() {
        return rank.ordinal();
    }

    public CardSuitEnum getSuit() {
        return suit;
    }

    public void setSuit(CardSuitEnum suit) {
        this.suit = suit;
    }
}

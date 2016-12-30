package model;

import java.util.List;

public class Player {

    private List<Card> cards;

    private RankingEnum ranking;

    private List<Card> rankingCards;

    public Player(List<Card> cards) {
        this.cards = cards;
    }

    public RankingEnum getRanking() {
        return ranking;
    }

    public void setRanking(RankingEnum ranking) {
        this.ranking = ranking;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getRankingCards() {
        return rankingCards;
    }

    public void setRankingCards(List<Card> rankingCards) {
        this.rankingCards = rankingCards;
    }

}

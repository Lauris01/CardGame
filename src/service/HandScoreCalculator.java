package service;

import model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HandScoreCalculator {

    public void calculatePlayersScores(List<Player> players) {
        players.forEach(this::calculatePlayerScore);
    }

    private void calculatePlayerScore(Player player) {
        ListRanking(player);
    }

    private void ListRanking(Player player) {

        List<Card> rankCards = getRoyalFlush(player.getCards());

        if (rankCards != null) {
            setPlayerScores(RankingEnum.ROYAL_FLUSH, rankCards, player);
            return;
        }

        rankCards = getStraightFlush(player.getCards());
        if (rankCards != null) {
            setPlayerScores(RankingEnum.STRAIGHT_FLUSH, rankCards, player);

            return;
        }

        rankCards = getFourOfAKind(player.getCards());
        if (rankCards != null) {
            setPlayerScores(RankingEnum.FOUR_OF_A_KIND, rankCards, player);
            return;
        }
        rankCards = getFullHouse(player.getCards());
        if (rankCards != null) {
            setPlayerScores(RankingEnum.FULL_HOUSE, rankCards, player);
            return;
        }

        rankCards = getFlush(player.getCards());
        if (rankCards != null) {
            setPlayerScores(RankingEnum.FLUSH, rankCards, player);
            return;

        }
        rankCards = getStraight(player.getCards());
        if (rankCards != null) {
            setPlayerScores(RankingEnum.STRAIGHT, rankCards, player);
            return;

        }
        rankCards = getThreeOfAKind(player.getCards());
        if (rankCards != null) {
            setPlayerScores(RankingEnum.THREE_OF_A_KIND, rankCards, player);
            return;

        }

        rankCards = getTwoPairs(player.getCards());
        if (rankCards != null) {
            setPlayerScores(RankingEnum.TWO_PAIR, rankCards, player);
            return;

        }
        rankCards = getPair(player.getCards());
        if (rankCards != null) {
            setPlayerScores(RankingEnum.ONE_PAIR, rankCards, player);
            return;
        }
        rankCards = Arrays.asList(HandScoringUtils.getHighestCard(player.getCards()));
        setPlayerScores(RankingEnum.HIGH_CARD,rankCards,player);
    }

    private void setPlayerScores(RankingEnum ranking, List<Card> cards, Player player) {
        player.setRanking(ranking);
        player.setRankingCards(cards);
    }

    private List<Card> getPair(List<Card> cards) {
        return checkPair(cards, 2);
    }

    private List<Card> getTwoPairs(List<Card> cards) {
        List<Card> pairOne = checkPair(cards, 2);
        if (pairOne != null) {
            List<Card> cardsLeft = HandScoringUtils.cloneCards(cards);
            cardsLeft.removeAll(pairOne);
            List<Card> pairTwo = checkPair(cardsLeft, 2);
            if (pairTwo != null) {
                pairOne.addAll(pairTwo);
                return pairOne;
            }
        }
        return null;
    }

    private List<Card> getThreeOfAKind(List<Card> cards) {
        return checkPair(cards, 3);
    }

    private List<Card> getStraight(List<Card> cards) {
        return getSequence(cards, 5, false);
    }

    private List<Card> getFlush(List<Card> cards) {
        CardSuitEnum cardSuit = null;
        for (Card card : cards) {
            if (cardSuit == null) {
                cardSuit = card.getSuit();
            } else if (!cardSuit.equals(card.getSuit())) {
                return null;
            }
        }
        return cards;
    }

    private List<Card> getFullHouse(List<Card> cards) {
        List<Card> threeCards = checkPair(cards, 3);
        if (threeCards != null) {
            List<Card> cardsLeft = HandScoringUtils.cloneCards(cards);
            cardsLeft.removeAll(threeCards);
            List<Card> twoCards = checkPair(cardsLeft, 2);
            if (twoCards != null)
                return cards;
        }
        return null;
    }

    private List<Card> getFourOfAKind(List<Card> cards) {
        return checkPair(cards, 4);
    }


    private List<Card> getRoyalFlush(List<Card> cards) {
        List<CardRankEnum> cardRanks = cards.stream().map(Card::getRank).collect(Collectors.toList());
        if (cardRanks.containsAll(Arrays.asList(CardRankEnum.ACE, CardRankEnum.KING, CardRankEnum.QUEEN, CardRankEnum.JACK, CardRankEnum.TEN))) {
            return cards;
        } else return null;
    }

    private List<Card> getStraightFlush(List<Card> cards) {
        return getSequence(cards, 5, true);
    }


    private List<Card> getSequence(List<Card> cards, Integer sequenceSize, Boolean compareCardSuit) {
        List<Card> orderedCards = HandScoringUtils.orderCardsByRank(cards);
        List<Card> sequenceCards = new ArrayList<>();


        Card previousCard = null;
        for (Card card : orderedCards) {
            if (previousCard != null) {
                if (previousCard.getRankInt() - card.getRankInt() == 1) {
                    if (!compareCardSuit || previousCard.getSuit().equals(card.getSuit())) {
                        if (sequenceCards.size() == 0) {
                            sequenceCards.add(previousCard);
                        }
                        sequenceCards.add(card);
                    }
                } else {
                    if (sequenceSize == sequenceCards.size()) {
                        return sequenceCards;
                    } else {
                        sequenceCards.clear();
                    }
                }
            }
            previousCard = card;
        }
        return sequenceCards.size() == sequenceSize ? sequenceCards : null;

    }

    private List<Card> checkPair(List<Card> cards, int size) {
        List<Card> checkedCards = new ArrayList<>();
        for (Card c1 : cards) {
            checkedCards.add(c1);
            for (Card c2 : cards) {
                if (!c1.equals(c2)) {
                    if (c1.getRank().equals(c2.getRank())) {
                        checkedCards.add(c2);
                    }
                }
            }
            if (checkedCards.size() == size) {
                return checkedCards;
            }
            checkedCards.clear();
        }
        return null;
    }


}

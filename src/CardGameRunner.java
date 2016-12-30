import model.Player;
import service.CardsComparator;
import service.FileReader;
import service.HandScoreCalculator;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CardGameRunner {

    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader();
        Set<Player[]> games = reader.getSetOfGames();
        HandScoreCalculator handScoreCalculator = new HandScoreCalculator();
        long timesPlayerOneWon = games.stream().map(g -> {
            handScoreCalculator.calculatePlayersScores(Arrays.stream(g).collect(Collectors.toList()));
            return CardsComparator.getWinnerIndex(g);
        }).filter(winner -> winner == 1).count();

        System.out.println(String.format("Times player one won: %s",timesPlayerOneWon));
    }
}

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void firstPart(List<String> input) {
        Map<CardSet, Integer> cardToBid = new HashMap<>();
        final List<CardSet> cardSets = new ArrayList<>();
        input.stream()
                .map(line -> line.split(" "))
                .forEach(item -> {
                    CardSet currentSet = new CardSet(item[0]);
                    cardToBid.put(currentSet, Integer.parseInt(item[1]));
                    cardSets.add(currentSet);
                });

        ;
        cardSets.sort(CardSet::compareTo);

        long winnings = 0;
        for (int i = 0; i < cardSets.size(); ++i) {
            winnings += (long) cardToBid.get(cardSets.get(i)) * (i + 1);
        }

        System.out.println(winnings);

    }
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./Day7/src/input"));
        firstPart(lines);
    }
}
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CardSet implements Comparable<CardSet> {
    private final List<Card> hand;
    private int type; //7 - Five of a kind, 6 - 4 of a Kind, 5 - Full House, 4 - Three of a kind, 3 - 2 Pairs, 2 - 1 pair, 1 - high card

    private int jokers;

    public CardSet(String cardSet) {
        String[] cards = cardSet.split("");
        this.hand = new ArrayList<>();
        this.jokers = 0;
        for (String card : cards) {
            Card currentCard = new Card(card);
            if (card.equals("J")) {
                jokers++;
            }
            this.hand.add(currentCard);
        }
        this.type = computeType();
    }

    private int computeType() {
        Map<Card, Long> cardSet =
                hand.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        if (cardSet.containsValue(5L)) {
            return 7;
        } else if (cardSet.containsValue(4L)) {
            return jokers == 0 ? 6 : 7;
        } else if (cardSet.containsValue(3L) && cardSet.size() == 2) {
            return jokers == 0 ? 5 : 7;
        } else if (cardSet.containsValue(3L) && cardSet.size() == 3){
            return switch (jokers) {
                case 1 -> 6;
                case 2,3 -> 7;
                default -> 4;
            };
        } else if (cardSet.containsValue(2L) && cardSet.size() == 3) {
            return switch (jokers) {
                case 1 -> 5;
                case 2 -> 6;
                default -> 3;
            };
        } else if (cardSet.containsValue(2L) && cardSet.size() == 4) {
            return switch (jokers) {
                case 1,2 -> 4;
                default -> 2;
            };
        } else {
            return 1 + jokers;
        }
    }

    @Override
    public int compareTo(CardSet o) {
        if (this.type != o.type) {
            return this.type - o.type;
        } else {
            for (int i = 0; i < hand.size(); ++i) {
                if (!this.hand.get(i).equals(o.hand.get(i))) {
                    return hand.get(i).compareTo(o.hand.get(i));
                }
            }
        }
        return 0;
    }

}

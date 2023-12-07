import java.util.Objects;

public class Card implements Comparable<Card> {
    private final int label; //T - 10, J - 1(1), Q - 12, K - 13, A - 14

    public Card(String label) {
        if (label.equals("A")) {
            this.label = 14;
        } else if (label.equals("K")) {
            this.label = 13;
        } else if (label.equals("Q")) {
            this.label = 12;
        } else if (label.equals("J")) {
            this.label = 1;
        } else if (label.equals("T")) {
            this.label = 10;
        } else {
            this.label = Integer.parseInt(label);
        }
    }

    public int getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Card card = (Card) object;
        return label == card.label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public int compareTo(Card o) {
        return this.label - o.label;
    }
}

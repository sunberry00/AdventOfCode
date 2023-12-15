import java.util.function.IntBinaryOperator;

public class Lens {
    private String label;
    private int box;
    private char operation;
    private int focalLength;

    public Lens(String input) {
        StringBuilder label = new StringBuilder();

        IntBinaryOperator accumulator = (x, y) -> {
            return (x + y) * 17 % 256;
        };

        for (int i = 0; i < input.length(); ++i) {
            char currentChar = input.charAt(i);
            if (currentChar != '=' && currentChar != '-') {
                label.append(currentChar);
            } else {
                this.label = label.toString();
                this.box = label.toString().chars().reduce(0, accumulator);
                this.operation = currentChar;
                this.focalLength = i == input.length() - 1
                        ? 0
                        : Integer.parseInt("" + input.charAt((input.length() - 1)));
            }
        }
    }

    public int getBox() {
        return box;
    }

    public char getOperation() {
        return operation;
    }

    public int getFocalLength() {
        return focalLength;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Lens otherLens = (Lens) obj;
        return label != null && label.equals(otherLens.label);
    }
}

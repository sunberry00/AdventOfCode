import java.util.ArrayList;
import java.util.List;

public class Pattern {
    private List<String> horizontalPattern;
    private List<String> verticalPattern;

    private int number;

    public Pattern(List<String> pattern) {
        this.horizontalPattern = pattern;
        this.verticalPattern = computeVerticalPattern(pattern);
        computeNumber();
    }

    private List<String> computeVerticalPattern(List<String> pattern) {
        List<String> result = new ArrayList<>();
        for (int j = 0; j < pattern.getFirst().length(); ++j) {
            StringBuilder currentColumn = new StringBuilder();
            for (int i = 0; i < pattern.size(); ++i) {
                currentColumn.append(pattern.get(i).charAt(j));
            }
            result.add(currentColumn.toString());
        }
        return result;
    }

    private void computeNumber() {
        int horizontal = findReflection(getHorizontalPattern());
        int vertical = findReflection(getVerticalPattern());

        if (horizontal == 0) {
            this.number = vertical + 1;
        } else if (vertical == 0) {
            this.number = (horizontal + 1) * 100;
        } else {
            if (getNumberOfRows(horizontal) > getNumberOfRows(vertical)) {
                this.number = (horizontal + 1) * 100;
            } else {
                this.number = vertical + 1;
            }
        }
    }

    public int getNumber() {
        return number;
    }

    public int findReflection(List<String> pattern) {
        for (int i = 0; i < pattern.size() - 1; ++i) {
            String upperLine = pattern.get(i);
            String bottomLine = pattern.get(i + 1);
            if (upperLine.equals(bottomLine)) return i;
        }
        return -1;
    }

    public int getNumberOfColumns(int vertReflection) {
        String upperLine;
        String bottomLine;
        int count = 0;
        do {
            count++;
            if (vertReflection - count >= 0 && vertReflection + count + 1 < verticalPattern.size()) {
                upperLine = verticalPattern.get(vertReflection - count);
                bottomLine = verticalPattern.get(vertReflection + count + 1);
            } else {
                return count;
            }
        } while (upperLine.equals(bottomLine));
        return 0;
    }

    public int getNumberOfRows(int horizontalReflection) {
        String upperLine;
        String bottomLine;
        int count = 0;
        do {
            count++;
            if (horizontalReflection - count >= 0 && horizontalReflection + count + 1 < horizontalPattern.size()) {
                upperLine = horizontalPattern.get(horizontalReflection - count);
                bottomLine = horizontalPattern.get(horizontalReflection + count + 1);
            } else {
                return count;
            }
        } while (upperLine.equals(bottomLine));
        return 0;
    }

    public List<String> getHorizontalPattern() {
        return horizontalPattern;
    }

    public List<String> getVerticalPattern() {
        return verticalPattern;
    }
}

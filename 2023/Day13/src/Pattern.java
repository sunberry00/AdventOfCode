import java.util.ArrayList;
import java.util.List;

public class Pattern {
    List<String> horizontalPattern;
    List<String> verticalPattern;

    public Pattern(List<String> pattern) {
        this.horizontalPattern = pattern;
        this.verticalPattern = computeVerticalPattern(pattern);
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

    private int findHorizontalReflection() {

        return 0;
    }
}

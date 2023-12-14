import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    private List<String> puzzleInput;

    private List<String> flippedPuzzleInput;

    private List<String> afterTilting;

    public Puzzle(List<String> puzzleInput) {
        this.puzzleInput = puzzleInput;
        this.flippedPuzzleInput = flipPuzzle(puzzleInput);
        this.afterTilting = new ArrayList<>();
    }

    public static List<String> flipPuzzle(List<String> pattern) {
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

    public static boolean hasRoundRocks(String line) {
        return line.indexOf('O') != -1;
    }

    public static boolean hasCubeRocks(String line) {
        return line.indexOf('#') != -1;
    }

    public void tiltStones() {

    }

    public List<String> getFlippedPuzzleInput() {
        return flippedPuzzleInput;
    }

}

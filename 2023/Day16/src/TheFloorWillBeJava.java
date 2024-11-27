import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;



public class TheFloorWillBeJava {

    public static final int N = 110;

    public static void firstPart(List<String> lines) {
        List<List<Cell>> puzzle = lines.stream()
                .map(row -> Arrays.stream(row.split(""))
                        .map(i -> new Cell(i))
                        .toList()
                ).toList();

        //Init values
        Stack<Cell> path = new Stack<>();
        path.push(puzzle.getFirst().getFirst());
        Direction direction;
        int x = 0;
        int y = 0;

        while (!path.isEmpty()) {
            Cell currentCell = path.pop();
            currentCell.setVisited();

            if (direction == Direction.RIGHT) {
                if (isPossibleMove(x + 1, y)) {
                    path.push(puzzle.get(++x).get(y));
                }
            } else if (d)
        }

    }

    public static boolean isPossibleMove(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    public static void secondPart(List<String> lines) {

    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./2023/Day16/example"));
        firstPart(lines);
    }
}



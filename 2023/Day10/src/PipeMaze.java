import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PipeMaze {

    public static List<List<Cell>> map = new ArrayList<>();
    public static Stack<Cell> stack = new Stack<>();

    private static boolean isValidCell(int i, int j) {
        return i >= 0 && i < map.size() && j >= 0 && j < map.get(i).size();
    }

    public static void backtracking(Cell start) {
        stack.push(start);

        while (!stack.isEmpty()) {
            Cell currentCell = stack.pop();
            drawMap(currentCell);
            currentCell.visit();
        }
    }

    private static void exploreDirection(int counter, int i, int j) {
        if (isValidCell(i, j) && !stack.contains(map.get(i).get(j))) {
            Cell nextCell = map.get(i).get(j);
            if (!nextCell.isVisited() || counter < nextCell.getSteps()) {
                stack.push(nextCell);
            }
        }
    }

    private static void draw(Cell cell, int steps) {
            if (steps < cell.getSteps() || !cell.isVisited()) {
                if (isValidCell(cell.getRow(), cell.getColumn())) {
                    stack.push(cell);
                    cell.setSteps(steps);
                }
            }
    }

    private static void drawMap(Cell cell) {
        switch (cell.getLabel()) {
            case '|' -> {
                draw(map.get(cell.getRow() - 1).get(cell.getColumn()), cell.getSteps() + 1); //N
                draw(map.get(cell.getRow() + 1).get(cell.getColumn()), cell.getSteps() + 1); //S
            }
            case '-' -> {
                draw(map.get(cell.getRow()).get(cell.getColumn() - 1), cell.getSteps() + 1); //W
                draw(map.get(cell.getRow()).get(cell.getColumn() + 1), cell.getSteps() + 1); //E
            }
            case 'L' -> {
                draw(map.get(cell.getRow() - 1).get(cell.getColumn()), cell.getSteps() + 1); //N
                draw(map.get(cell.getRow()).get(cell.getColumn() + 1), cell.getSteps() + 1); //E
            }
            case 'J' -> {
                draw(map.get(cell.getRow() - 1).get(cell.getColumn()), cell.getSteps() + 1); //N
                draw(map.get(cell.getRow()).get(cell.getColumn() - 1), cell.getSteps() + 1); //W
            }
            case '7' -> {
                draw(map.get(cell.getRow() + 1).get(cell.getColumn()), cell.getSteps() + 1); //S
                draw(map.get(cell.getRow()).get(cell.getColumn() - 1), cell.getSteps() + 1); //W
            }
            case 'F' -> {
                draw(map.get(cell.getRow() + 1).get(cell.getColumn()), cell.getSteps() + 1); //S
                draw(map.get(cell.getRow()).get(cell.getColumn() + 1), cell.getSteps() + 1); //E
            }
        }
    }

    public static void firstPart(List<String> input) {
        int currentX = 0;
        int currentY = 0;

        for (int i = 0; i < input.size(); ++i) {
            map.add(new ArrayList<>());
            for (int j = 0; j < input.get(i).length(); ++j) {
                if (input.get(i).charAt(j) == 'S') {
                    currentX = i;
                    currentY = j;
                    map.get(i).add(new Cell(i, j, '|'));
                    map.get(i).get(j).setSteps(0);
                } else {
                    map.get(i).add(new Cell(i, j, input.get(i).charAt(j)));
                }
            }
        }

        backtracking(map.get(currentX).get(currentY));

        System.out.println(map.stream().mapToInt(item ->
                item.stream().mapToInt(Cell::getSteps).max().getAsInt()).max().getAsInt()
        );
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("./2023/Day10/src/input"));
        firstPart(lines);
    }
}

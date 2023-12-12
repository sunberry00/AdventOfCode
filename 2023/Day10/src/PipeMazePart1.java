import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
public class PipeMazePart1 {

    List<List<Character>> pipeMaze;

    protected Position findS(List<List<Character>> pipeMaze){
        for (int i = 0; i < pipeMaze.size(); i++) {
            for (int j = 0; j < pipeMaze.get(i).size(); j++) {
                if(pipeMaze.get(i).get(j) == 'S'){
                    return new Position(j, i, null, null, 'S');
                }
            }
        }
        return null;
    }

    public void findResult() throws IOException {
        pipeMaze = Files.readAllLines(Path.of("./2023/Day10/src/input"))
                .stream()
                .map(line -> line.chars().mapToObj(c -> (char) c).toList())
                .toList();

        //Find S
        Position positionOfS = findS(pipeMaze);

        //Find the two paths from S
        List<Position> nextPositionsFromS = positionOfS.findNextPositions(pipeMaze);

        //Follow the two paths until they hit
        //Return the turn count of the hit
        int farthestAway = findFarthestAway(nextPositionsFromS);

        System.out.println("Result of day 10 part 1: " + farthestAway);
    }

    private int findFarthestAway(List<Position> nextPositionsFromS) {
        //Stating at 1, as path from S was already found
        int pathCounter = 1;
        Position firstPath = nextPositionsFromS.getFirst();
        Position secondPath = nextPositionsFromS.getLast();

        while (!firstPath.equals(secondPath)){
            firstPath = firstPath.findNextPositions(pipeMaze).getFirst();
            secondPath = secondPath.findNextPositions(pipeMaze).getFirst();
            pathCounter++;
        }
        return pathCounter;
    }

    public static void main(String[] args) throws IOException {
        PipeMazePart1 day10Part1 = new PipeMazePart1();
        day10Part1.findResult();
    }
}
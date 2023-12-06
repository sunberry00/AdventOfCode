import java.util.function.Function;
import java.util.stream.IntStream;

public class RaceFunction implements Function<Race, Integer> {
    @Override
    public Integer apply(Race race) {
        int numberOfWays = 0;
        return Math.toIntExact(IntStream.rangeClosed(0, race.getTime())
                .map(x -> (race.getTime() - x) * x)
                .filter(x -> x > race.getDistance())
                .count());
    }
}

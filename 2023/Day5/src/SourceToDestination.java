import java.util.List;
import java.util.function.Function;

public class SourceToDestination implements Function<Long, Long> {

    private List<Long[]> initValues;
    private Source source;
    private Source destination;

    public SourceToDestination(List<Long[]> initValues, Source source, Source destination) {
        this.source = source;
        this.destination = destination;
        this.initValues = initValues;
    }

    @Override
    public Long apply(Long aLong) {
        for (int i = 0; i < initValues.size(); ++i) {
            if (aLong >= initValues.get(i)[1] && aLong < initValues.get(i)[1] + initValues.get(i)[2]) {
                return (aLong - initValues.get(i)[1]) + initValues.get(i)[0];
            }
        }
        return aLong;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Galaxy {
    public int x;
    public int y;
    List<Long> distances;

    public Galaxy(int i, int j) {
        this.x = i;
        this.y = j;
        distances = new ArrayList<>();
    }
}

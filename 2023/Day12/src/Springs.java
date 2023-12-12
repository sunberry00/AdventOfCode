import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Springs {
    private String representation;
    private List<Integer> contiguousGroups;
    private String[] arrangements;

    public Springs(String representation, String[] groups) {
        this.representation = representation;
        contiguousGroups = new ArrayList<>();

        for (int i = 0; i < groups.length; ++i) {
            contiguousGroups.add(Integer.parseInt(groups[i]));
        }
    }

    public int countArrangements() {
        int sum = 0;
        for (int i = 0; i < arrangements.length; ++i) {
            String[] currentArrangement = Arrays.stream(arrangements[i].split("\\.+")).filter(x -> !x.isEmpty()).toArray(String[]::new);
            if (currentArrangement.length == contiguousGroups.size()) {
                sum++;
                for (int j = 0; j < contiguousGroups.size(); ++j) {
                    if (currentArrangement[j].length() != contiguousGroups.get(j)) {
                        sum--;
                        break;
                    }
                }
            }
        }
        return sum;
    }

    public String getRepresentation() {
        return representation;
    }

    public List<Integer> getContiguousGroups() {
        return contiguousGroups;
    }

    public void setArrangements(String[] arrangements) {
        this.arrangements = arrangements;
    }
}

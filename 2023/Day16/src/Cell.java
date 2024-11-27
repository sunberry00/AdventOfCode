import java.util.List;

public class Cell {
    private String symbol;
    private boolean isVisited;

    public Cell(String symbol) {
        this.symbol = symbol;
        this.isVisited = false;
    }

    public void setVisited() {
        isVisited = true;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isVisited() {
        return isVisited;
    }
}

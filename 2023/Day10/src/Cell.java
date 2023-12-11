public class Cell {
    private int row;
    private int column;
    private char label;
    private boolean isVisited;
    public int steps;

    public Cell(int i, int j, char label) {
        this.row = i;
        this.column = j;
        this.label = label;
        this.isVisited = false;
        this.steps = -1;
    }

    public Cell(Cell other) {
        this.row = other.row;
        this.column = other.column;
        this.label = other.label;
        this.isVisited = other.isVisited;
        this.steps = other.steps;
    }

    public char getLabel() {
        return label;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void visit() {
        isVisited = true;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public int getSteps() {
        return steps;
    }


}

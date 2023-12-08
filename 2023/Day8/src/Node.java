public class Node {
    private String left;
    private String right;
    public Node(String string) {
        String[] leftRight = string.split(",");
        this.left = leftRight[0].substring(1);
        this.right = leftRight[1].substring(0, leftRight[1].length() - 1);
    }

    public String getNext(String path) {
        if (path.equals("L")) {
            return left;
        } else {
            return right;
        }
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }
}

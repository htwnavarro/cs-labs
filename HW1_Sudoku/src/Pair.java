public class Pair {
    private final int x;
    private final int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean contains(int value) {
        return this.x == value || this.y == value;
    }
}

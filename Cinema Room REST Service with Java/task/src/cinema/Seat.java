package cinema;

public class Seat {
    private final int row;
    private final int column;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Seat(int r, int c) {
        row = r;
        column = c;
    }
}

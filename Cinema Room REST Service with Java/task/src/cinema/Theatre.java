package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    @JsonProperty("rows")
    private final int totalRows;
    @JsonProperty("columns")
    private final int totalColumns;
    @JsonProperty("seats")
    private final List<Seat> seats = new ArrayList<>();

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Theatre(int rows, int columns) {
        this.totalRows = rows;
        this.totalColumns = columns;
        for (int r = 1; r <= totalRows; r++) {
            for (int c = 1; c <= totalColumns; c++) {
                seats.add(new Seat(r, c));
            }
        }
    }
}

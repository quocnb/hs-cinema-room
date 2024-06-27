package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Theatre {
    @JsonProperty("rows")
    private final int totalRows;
    @JsonProperty("columns")
    private final int totalColumns;
    @JsonIgnore
    private final Seat[][] seats;
    @JsonIgnore
    private final Map<UUID, Seat> purchased = new HashMap<>();

    public static Theatre theatre = new Theatre(9, 9);

    public int getTotalRows() {
        return totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    @JsonProperty("seats")
    public List<Seat> getSeats() {
        List<Seat> seatList = new ArrayList<>();
        for (Seat[] row : seats) {
            seatList.addAll(Arrays.asList(row));
        }
        return seatList;
    }

    public Theatre(int rows, int columns) {
        this.totalRows = rows;
        this.totalColumns = columns;
        seats = new Seat[rows][columns];
        for (int r = 0; r < totalRows; r++) {
            for (int c = 0; c < totalColumns; c++) {
                seats[r][c] = new Seat(r + 1, c + 1);
            }
        }
    }

    public PurchaseResponse purchase(int r, int c) throws Exception {
        if ((r < 1 || r > seats.length) || (c < 1 || c > seats.length)) {
            throw new Exception("The number of a row or a column is out of bounds!");
        }
        Seat purchaseSeat = seats[r-1][c-1];
        if (purchaseSeat.isPurchased()) {
            throw new Exception("The ticket has been already purchased!");
        }
        purchaseSeat.setPurchased(true);
        UUID token = UUID.randomUUID();
        purchased.put(token, purchaseSeat);
        return new PurchaseResponse(token, purchaseSeat);
    }

    public TicketReturnResponse returnTicket(UUID token) throws Exception {
        if (!purchased.containsKey(token)) {
            throw new Exception("Wrong token!");
        }
        Seat seat = purchased.get(token);
        seat.setPurchased(false);
        purchased.remove(token);
        return new TicketReturnResponse(seat);
    }

    @JsonIgnore
    public StatsResponse getStats() {
        int income = 0;
        int purchasedCount = 0;
        for (UUID token : purchased.keySet()) {
            Seat s = purchased.get(token);
            purchasedCount += 1;
            income += s.getPrice();
        }
        int available = totalColumns * totalRows - purchasedCount;
        return new StatsResponse(income, available, purchasedCount);
    }
}

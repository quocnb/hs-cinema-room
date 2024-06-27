package cinema;

import java.util.UUID;

public record PurchaseResponse(UUID token, Seat ticket) {
}

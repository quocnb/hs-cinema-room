package cinema;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("seats")
public class SeatController {
    @GetMapping("")
    public Theatre getSeats() {
        return Theatre.theatre;
    }
}

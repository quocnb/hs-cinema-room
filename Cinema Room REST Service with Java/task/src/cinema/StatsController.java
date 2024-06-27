package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {
    private static final String SUPER_SECRET_PASSWORD = "super_secret";

    @GetMapping
    public ResponseEntity<Object> getStats(@RequestParam(required = false) String password) {
        if (!SUPER_SECRET_PASSWORD.equals(password)) {
            Map<String, String> err = Map.of("error", "The password is wrong!");
            return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
        }
        StatsResponse response = Theatre.theatre.getStats();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

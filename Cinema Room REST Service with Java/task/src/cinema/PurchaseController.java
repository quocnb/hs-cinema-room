package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @PostMapping
    public ResponseEntity<Object> purchase(@RequestBody PurchaseRequest request) {
        try {
            PurchaseResponse response = Theatre.theatre.purchase(request.getRow(), request.getColumn());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> err = Map.of("error", e.getMessage());
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
        }
    }
}

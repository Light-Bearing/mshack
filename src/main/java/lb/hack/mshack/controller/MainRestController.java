package lb.hack.mshack.controller;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lb.hack.mshack.dto.Profit;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@Tag(name = "Главный контроллер", description = "Все функции тут")
public class MainRestController {
    @RequestMapping("/")
    @Hidden
    public ResponseEntity<?> root() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/swagger-ui.html");
        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/profit")
    public String  profit(@RequestBody Profit profit) throws URISyntaxException {



        return "result";
    }

}


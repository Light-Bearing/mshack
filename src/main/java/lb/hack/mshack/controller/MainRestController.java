package lb.hack.mshack.controller;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lb.hack.mshack.utils.ScriptManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptException;

@RestController
@Tag(name = "Главный контроллер", description = "Все функции тут")
@RequiredArgsConstructor
public class MainRestController {

    private final ScriptManager engine;
    @RequestMapping("/")
    @Hidden
    public ResponseEntity<?> root() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/swagger-ui.html");
        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/profit")
    public Object  profit() throws ScriptException {

       return engine.eval("function f(arg) { return 5+arg; }", 12);

    }




}


package lb.hack.mshack.controller;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lb.hack.mshack.message.request.Equation;
import lb.hack.mshack.message.request.Parameter;
import lb.hack.mshack.service.ModelService;
import lb.hack.mshack.utils.ScriptManager;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptException;
import java.util.List;

@RestController
@Tag(name = "Главный контроллер", description = "Все функции тут")
@AllArgsConstructor
public class MainRestController {

    private ModelService modelService;

    @RequestMapping("/")
    @Hidden
    public ResponseEntity<?> root() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/swagger-ui.html");
        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }

    //return engine.eval("const f=(arg1,arg2)=>{var x = 5; return x*arg1+arg2;}", 1,2);


    @PostMapping("/setModel")
    public ResponseEntity<?> setModel(@RequestBody List<Equation> equationList){
        return ResponseEntity.ok(modelService.addModel(equationList));
    }

    @PostMapping("/setParametr")
    public ResponseEntity<?> setParametr(@RequestBody List<Parameter> parameterList){
        return ResponseEntity.ok(modelService.setParameter(parameterList));
    }

}


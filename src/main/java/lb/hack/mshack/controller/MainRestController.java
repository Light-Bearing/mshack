package lb.hack.mshack.controller;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lb.hack.mshack.dto.Result;
import lb.hack.mshack.message.request.Equation;
import lb.hack.mshack.message.request.Parameter;
import lb.hack.mshack.service.ModelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/model")
    public ResponseEntity<?> setModel(@RequestBody List<Equation> equationList){
        return ResponseEntity.ok(modelService.addModel(equationList));
    }

    @GetMapping("model")
    public ResponseEntity<?> getModel(){
        return ResponseEntity.ok(modelService.getModel());
    }

    @DeleteMapping("/model")
    public ResponseEntity<?> deleteElementModel(@RequestParam Long id){
        return ResponseEntity.ok(modelService.deleteModel(id));
    }

    @PostMapping("/setParameter")
    public ResponseEntity<Result> setParameter(@RequestBody List<Parameter> parameterList){
        return ResponseEntity.ok(modelService.setParameter(parameterList));
    }

}


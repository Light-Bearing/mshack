package lb.hack.mshack.utils;

import lb.hack.mshack.message.request.Pair;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JavaScriptEngine {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");

    {
        try {
            engine.eval("let f");
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    public String eval(String equation, List<Pair> param)
    {
        try {
            engine.eval("f = "+equation);
            String[] params=equation.substring(equation.indexOf('(')+1,equation.indexOf(')')).split(",");
            StringBuilder str= new StringBuilder();
            for (String parameter : params) {
                String value = param.stream()
                        .filter(el->el.getKey().equals(parameter))
                        .collect(Collectors.toList())
                        .get(0)
                        .getValue();
                str.append(str.length() == 0 ? value : ("," + value));
            }
            return engine.eval("f("+str+")").toString();
        }catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }
}

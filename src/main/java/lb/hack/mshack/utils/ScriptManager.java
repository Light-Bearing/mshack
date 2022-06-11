package lb.hack.mshack.utils;

import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Component
public class ScriptManager {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");

    public Object eval(String func, int arg1, int arg2) throws ScriptException {
        engine.eval(func);
        return engine.eval("f(" + arg1 +", "+ arg2+");");

    }
}

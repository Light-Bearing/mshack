package lb.hack.mshack.utils;

import org.springframework.stereotype.Component;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

@Component
public class ScriptManager {
    ScriptEngineManager engineManager = new ScriptEngineManager();
    javax.script.ScriptEngine engine = engineManager.getEngineByName("nashorn");

    public Object eval(String func, int arg) throws ScriptException {

//        engine.eval("function f(arg) { return 5+arg; }");
        engine.eval(func);
        return engine.eval("f(" + arg + ");");

    }
}

package lb.hack.mshack.message.request;

import com.google.gson.JsonObject;
import com.ibm.icu.impl.Pair;
import lombok.Data;

import java.util.List;

@Data
public class Parameter {
    private List<JsonObject> param;
}

package lb.hack.mshack.message.request;

import lombok.Data;

import java.util.List;

@Data
public class Parameter {
    private Long equationId;
    private List<Pair> param;
}

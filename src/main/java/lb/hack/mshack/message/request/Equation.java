package lb.hack.mshack.message.request;

import lombok.Data;

import java.util.List;

@Data
public class Equation {
    private String name;
    private String equation;
    private List<Param> paramList;


    @Data
    private static class Param {
        private String name;
        private String title;
        private String type;
    }
}

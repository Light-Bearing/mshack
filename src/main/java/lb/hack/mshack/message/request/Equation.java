package lb.hack.mshack.message.request;

import lombok.Data;

import java.util.List;

@Data
public class Equation {
    private String name;
    private String equation;
    private List<Param> paramList;



}

package lb.hack.mshack.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Profit {
    /**
     * число абонентов
     */
    int accounts;
    /**
     * затараты на 1 канал
     */
    int channelCosts;
    /**
     * количество смс
     */
    int sms;

    String formula;
    String period;
    List<Balanse> items = new ArrayList();
}

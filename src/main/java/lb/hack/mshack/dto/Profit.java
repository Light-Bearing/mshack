package lb.hack.mshack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Profit {
    /**
     * число абонентов
     */
    @JsonProperty("count_accounts")
    int countAccounts;
    /**
     * затараты на 1 канал
     */
    @JsonProperty("channelCosts")
    int channelCosts;
    /**
     * количество смс
     */
    @JsonProperty("countSms")
    int sms;

    @JsonProperty("formula")
    String formula;

    @JsonProperty("period")
    String period;

    /**
     * среднее значение прибыли
     */
    @JsonProperty("avgProfit")
    int avgProfit;

}

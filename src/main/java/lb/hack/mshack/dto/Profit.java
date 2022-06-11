package lb.hack.mshack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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
    @JsonProperty("channel_costs")
    int channelCosts;
    /**
     * количество смс
     */
    @JsonProperty("count_sms")
    int countSms;

    @JsonProperty("formula")
    String formula;

    @JsonProperty("period")
    String period;

    /**
     * среднее значение прибыли
     */
    @JsonProperty("avg_profit")
    int avgProfit;

}

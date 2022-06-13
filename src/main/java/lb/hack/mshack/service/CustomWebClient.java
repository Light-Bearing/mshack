package lb.hack.mshack.service;

import lb.hack.mshack.dto.vk.Channel;
import lb.hack.mshack.dto.vk.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service

public class CustomWebClient {
    private final WebClient webClient;
    @Value("${channel.vk.version}")
    private String vkApiVerson;
    @Value("${channel.vk.url}")
    private String urlVk;
    @Value("${channel.vk.token}")
    private String token;

    /**
     * значение на 12.06.2022. Используется для сравнения с текущими значениями // todo вынести в базу
     */
    List<Channel> channels = Arrays.asList(
            new Channel(1, "Valeri Chekalina", "vk.com", 971391, "18216993", false, false),
            new Channel(2, "Ольга Бузова", "vk.com", 1500000, "32707600", false, false),
            new Channel(3, "Ирина Дубцова", "vk.com", 64706, "55136424", false, false),
            new Channel(4, "Елена Сажина", "vk.com", 129265, "287301056", false, false)
    );

    public CustomWebClient(WebClient webClientA) {
        this.webClient = webClientA;
    }

    public Mono<Users> getFollowers(final String id) {
        String method = "users.getFollowers";

        return webClient
                .get()
                .uri(String.join("", urlVk, method, "?", vkApiVerson, "&user_id=", id))
                .headers(h -> h.setBearerAuth(token))
                .retrieve()
                .bodyToMono(Users.class);
    }

    public List<Channel> getActualChannels() {
        channels.forEach(s -> {
            Mono<Users> user = getFollowers(s.getInnerId());
            Integer count = user.block().getResponse().getCount();
            if (s.getInvolvement().compareTo(count) > 0) {
                s.setDown(true);
            } else if (s.getInvolvement().compareTo(count) < 0) {
                s.setUp(true);
            }
            s.setInvolvement(count);
        });
        return channels;
    }


}

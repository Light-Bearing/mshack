package lb.hack.mshack.service;

import lb.hack.mshack.dto.vk.Channel;
import lb.hack.mshack.dto.vk.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service

public class CustomWebClient {
    RestTemplate restTemplate = new RestTemplate();
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
            new Channel(1, "Valeri Chekalina", "vk.com", 971391, "18216993", false, false, "https://vk.com/id18216993"),
            new Channel(2, "Ольга Бузова", "vk.com", 1500000, "32707600", false, false, "https://vk.com/id32707600"),
            new Channel(3, "Ирина Дубцова", "vk.com", 64706, "55136424", false, false, "https://vk.com/id55136424"),
            new Channel(4, "Елена Сажина", "vk.com", 129265, "287301056", false, false, "https://vk.com/id287301056")
    );

    public Users getFollowers(final String id) {
        String method = "users.getFollowers";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = String.join("", urlVk, method, "?", vkApiVerson, "&user_id=", id);
        return restTemplate.postForObject(url, entity, Users.class);
    }

    public List<Channel> getActualChannels() {
        channels.forEach(s -> {
            Users user = getFollowers(s.getInnerId());
            Integer count = user.getResponse().getCount();
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

package lb.hack.mshack.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionGA {
    @Value("${ml.address}")
    private String url;
    public String getGeneticResponse(List<Double> profit){
        final RestTemplate restTemplate =new RestTemplate();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        String request = String.format("{\"equation_inputs\": %s}",profit.stream().
                map(Object::toString)
                .collect(Collectors.toList()));
        System.out.println(request);
        // build the request
        HttpEntity<String> entity = new HttpEntity<>(request, headers);
        // send POST request
        return restTemplate.postForObject(url, entity, String.class);
    }
}

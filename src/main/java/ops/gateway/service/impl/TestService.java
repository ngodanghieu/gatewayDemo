package ops.gateway.service.impl;

import ops.gateway.model.request.SampleDto;
import ops.gateway.service.TestServiceInterface;
import ops.gateway.util.Constant;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class TestService implements TestServiceInterface {

    private static final String URL = "http://192.168.137.74:8080/pay-maker-checker";
//    private static final String URL = "http://localhost:8080api/demo" ;

    @Override
    public ResponseEntity<String> callCheckerAnMaker(SampleDto sampleDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constant.FilterConstant.X_API_KEY, "ops-pay-key");
        headers.add(Constant.FilterConstant.SECRET, "ops-pay-secret");
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Long int_random = random.nextLong(10_000_000_000L, 100_000_000_000L);
        personJsonObject.put("session_id", sampleDto.getSessionId());
        personJsonObject.put("file_name", sampleDto.getInput_file_name());
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        HttpEntity<String> request = new HttpEntity<>(personJsonObject.toString(), headers);
        return restTemplate.postForEntity(URL, request, String.class);
    }
}

package ops.gateway.controllertest;

import com.google.gson.Gson;
import ops.gateway.controller.TestController;
import ops.gateway.model.request.SampleDto;
import ops.gateway.model.response.ResponseDTO;
import ops.gateway.service.TestService;
import ops.gateway.util.Constant;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

public class TestConttrollerTest {

    private SampleDto sampleDto;

    @InjectMocks
    private TestController testController;

    @Mock
    private TestService userService;

    @BeforeEach
    void initDataRequest(){
        MockitoAnnotations.initMocks(this);
        sampleDto = new SampleDto();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Long int_random = random.nextLong(10_000_000_000L, 100_000_000_000L);
        sampleDto.setSessionId(String.valueOf(int_random));
    }

    @Test
    void userSuccess(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constant.FilterConstant.HEADER_AUTHENTICATION,"Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODAzODk5MzIsImlhdCI6MTU4MDM0NjczMiwidXNlcl9uYW1lIjoidGVzdF9odW5nbnQzMSIsInVzZXJfaWQiOjE1OSwicGVybWlzc2lvbnMiOnsiQWNjb3VudGluZ1NtcyI6WzEsMl0sIkFwcFZlcnNpb24iOlsxLDIsMyw0LDVdLCJCSUxMX1JFQ09OSUxFX0JBVENIIjpbMV0sIkJJTExfUkVDT05JTEVfUEFZT08iOlsyXSwiQmFsYW5jZUFkanVzdG1lbnQiOlsxLDIsMyw0LDUsNl0sIkJpbGxQYXlNYW5hZ2VtZW50IjpbMSwyXSwiQ2FjaGUiOlsxLDIsM10sIkNhbXBhaWduIjpbMSwyLDMsNCw1LDZdLCJDbGFpbUxvdHRlcnlQcml6ZSI6WzEsMl0sIkNsYWltUmV3YXJkVHJhbnNhY3Rpb24iOlsxLDJdLCJDbXNMb2ciOlsxXSwiQ29ubmVjdGVkQXBwIjpbMSwyLDNdLCJDdXN0b21lcjM2MF9SZXF1ZXN0TWFuYWdlbWVudCI6WzEwMDAsMTAwN10sIkN1c3RvbWVyMzYwX1NNU0NhbXBhaWduTWFuYWdlbWVudCI6WzEwMDAsMTAwMiwxMDAxLDEwMDNdLCJDdXN0b21lcjM2MF9TZWdtZW50TWFuYWdlbWVudCI6WzEsMl0sIkN1c3RvbWVyUHJvZmlsZSI6WzEsNCw1LDYsNyw4LDksMiwzLDEwLDExXSwiRVdhbGxldFJlY29uY2lsaWF0aW9uIjpbMV0sIkV2b3VjaGVyX09yZGVyIjpbMSwzLDZdLCJFdm91Y2hlcl9Wb3VjaGVyVGFnIjpbMSwyLDMsNF0sIkV2b3VjaGVyX1ZvdWNoZXJUYXJnZXQiOlsxLDIsM10sIkV4Y2VwdGlvbkxvZyI6WzFdLCJFeHBvcnRMb2ciOlsxXSwiRmNtVGVtcGxhdGVNb2R1bGUiOlsxLDIsMyw0LDUsNiw3LDhdLCJGbGFnIjpbMSwyLDMsNF0sIkd1YXJhbnRlZUFjY291bnQiOlsxLDIsMyw0LDUsNl0sIklEVkFZX0FnZW50IjpbMSwyLDMsNF0sIklEVkFZX0FwcGxpY2F0aW9uIjpbMSwyXSwiSURWQVlfRGVhbGVyTWFuYWdlbWVudCI6WzEsMiwzXSwiSURWQVlfTGVuZGVyTWFuYWdlbWVudCI6WzEsMiwzLDRdLCJJRFZBWV9Ob3RpZmljYXRpb24iOlsxXSwiSURWQVlfUHJvZHVjdCI6WzIsMSwzLDQsNSw2LDcsOCw5LDEwXSwiSURWQVlfUHJvZHVjdEdyb3VwIjpbMSwyLDNdLCJJRFZBWV9SZXBvcnQiOlsxLDJdLCJJbXBvcnRMb2ciOlsxXSwiTUNfTWVyY2hhbnRJbmZvcm1hdGlvbiI6WzEsMiwzXSwiTUNfVXNlck1hbmFnZW1lbnQiOlsxLDIsMyw0XSwiTWFrZXJDaGVja2VyIjpbMV0sIk1ha2VyQ2hlY2tlckNvbmZpZyI6WzEsMiwzXSwiTWVyY2hhbnQiOlsxLDIsMyw0LDUsNl0sIk1lcmNoYW50X0FjY291bnQiOlsxLDIsMyw0LDUsNiw3LDhdLCJNZXJjaGFudF9NYWludGFpbkFQSSI6WzEsMiwzXSwiTWVyY2hhbnRfTWVyY2hhbnQiOlsxLDIsMyw0LDUsNiw5LDcsOF0sIk1lcmNoYW50X01lcmNoYW50Q29uZmlnIjpbMSwyXSwiTWVyY2hhbnRfUE9TIjpbMSwyLDMsNCw1LDYsN10sIk1lcmNoYW50X1N0YWZmIjpbMSwzLDIsNCw1XSwiTWVyY2hhbnRfU3RvcmUiOlsxLDIsMyw0LDVdLCJNb2JpbGVMb2ciOlsxXSwiTm90aWZpY2F0aW9uIjpbMSwyLDMsNCw1XSwiT0F1dGgyQ2xpZW50IjpbMSwyLDMsNF0sIk90aGVyUHJvbW90aW9uIjpbMSwyLDMsNCw1LDYsN10sIlBFX1Jld2FyZCI6WzEsMiwzLDQsNSw2XSwiUGFydG5lclBheW1lbnQiOlsxLDIsMyw0XSwiUGF5cm9sbE1hbmFnZW1lbnQiOlsxLDIsMyw0LDUsNl0sIlBlcm1pc3Npb24iOlsxLDJdLCJQcm9tb3Rpb24iOlsxLDIsMyw0LDUsNiw3XSwiUHJvbW90aW9uRW5naW5lX0NhbXBhaWduTWFuYWdlbWVudCI6WzEsMiwzXSwiUHJvbW90aW9uRW5naW5lX0NsYWltUmV3YXJkTWFuYWdlbWVudCI6WzEsMl0sIlByb21vdGlvbkVuZ2luZV9SZXdhcmRNYW5hZ2VtZW50IjpbMSwyLDMsNF0sIlJlY2VpdmVyVGlja2V0VHlwZSI6WzEsMl0sIlJlZnVuZCI6WzEsMiwzLDQsNSw2XSwiUmV3YXJkIjpbMSwyLDMsNF0sIlJld2FyZFBvb2wiOlsxLDIsMyw0XSwiUmV3YXJkVHJhbnNhY3Rpb24iOlsxLDIsM10sIlJvbGUiOlsxLDIsMyw0LDVdLCJTbXNQcmVmaXhOdW1iZXJNb2R1bGUiOlsxLDIsM10sIlNtc1Byb3ZpZGVyTW9kdWxlIjpbMSwyLDNdLCJTbXNUZW1wbGF0ZU1vZHVsZSI6WzEsMiwzLDQsNSw2XSwiU3RhZmYiOlsxLDIsMyw0LDUsNiw3LDhdLCJTdG9yZSI6WzEsMiwzLDQsNSw2XSwiU3ViRXZlbnQiOlsxLDIsNCw1LDZdLCJUMjRfUmVjb25jaWFsaWF0aW9uIjpbM10sIlRhZyI6WzEsMiwzLDQsNSw2XSwiVHJhbnNhY3Rpb24iOlsxXSwiVXNlciI6WzEsMiwzLDQsNV0sIlVzZXJXYWxsZXQiOlsxLDIsMyw0LDUsNiw3LDgsOSwxMCwxMSwxMiwxMywxNCwxNSwxNiwxNywxOCwxOSwyMCwyMSwyMiwyMywyNCwyNSwyNiwyNywyOCwyOSwzMCwzMSwzMiwzMywzNCwzNSwzNiwzNywzOCwzOSw0MCw0MSw0Miw0Myw0NCw0NSw0Nl0sIlZDYXJ0X0NhcnRPcmRlckNvbmZpZ3VyYXRpb24iOlsxLDJdLCJWQ2FydF9Db25maWd1cmF0aW9uIjpbMV0sIlZDYXJ0X0RlbGl2ZXJ5QWRkcmVzcyI6WzEsMiwzXSwiVkNhcnRfRGVsaXZlcnlGZWUiOlsxLDIsMyw0LDVdLCJWQ2FydF9EZWxpdmVyeU1ldGhvZCI6WzEsMiwzLDRdLCJWQ2FydF9LZXl3b3JkIjpbMV0sIlZDYXJ0X09yZGVyIjpbMSwyLDMsNCw1XSwiVkNhcnRfUGF5bWVudE1ldGhvZCI6WzEsMiwzLDRdLCJWQ2FydF9QYXltZW50T3B0aW9uIjpbMSwyLDMsNF0sIlZDYXJ0X1JhdGVMaW1pdCI6WzJdLCJWQ2FydF9Ta3VCYXJjb2RlIjpbMSwyLDMsNF0sIlZDYXJ0X1NrdUl0ZW0iOlsxLDIsMyw0LDUsNiwxMF0sIlZDYXJ0X1VuaXRPZk1lYXN1cmUiOlsxLDIsMyw0XSwiVkNhcnRfVkNNIjpbMSwyLDMsNF0sIlZDYXJ0X1ZvdWNoZXJTY2FuQW5kR28iOlsxXSwiVkhPTUVTX0NoZWNrTG9ncyI6WzFdLCJWSE9NRVNfRGViaXROb3RlIjpbMSwyLDNdLCJWSE9NRVNfRmVhdHVyZVR5cGVzIjpbMSwyLDMsNCw1XSwiVkhPTUVTX0ZlYXR1cmVzIjpbMSwyLDMsNF0sIlZIT01FU19JbnZvaWNlIjpbMV0sIlZIT01FU19MaXN0Q2l0aXplbiI6WzFdLCJWSE9NRVNfUGVybWlzc2lvbiI6WzEsMl0sIlZIT01FU19SZXNpZGVudCI6WzEsMiwzXSwiVklOSE9NRVNfT3RoZXJTZXJ2aWNlcyI6WzEwMDAxLDEwMDAyLDEwMDAzXSwiVlRpY2tldF9DaGVja2luQXBwIjpbMSwyLDMsNF0sIlZUaWNrZXRfRXZlbnQiOlsxLDQsNSw2LDldLCJWVGlja2V0X09yZGVyIjpbMSw4LDldLCJWVGlja2V0X1Byb3ZpZGVyIjpbMSw0LDUsOV0sIlZUaWNrZXRfUmVzZXJ2ZSI6WzEsNSw2LDcsOF0sIlZUaWNrZXRfVGlja2V0IjpbMSw5LDhdLCJWVGlja2V0X1RpY2tldFR5cGUiOlsxLDQsNSw2LDldLCJWaW5QYXlfQ2FtcGFpZ25NYW5hZ2VtZW50IjpbMSwyLDMsMTAwMl0sIlZpblBheV9DYXNoYmFja01hbmFnZW1lbnQiOlsxLDIsM10sIlZpblBheV9SZWZ1bmRNYW5hZ2VtZW50IjpbMSwyLDNdLCJWaW5QYXlfU2V0dGxlbWVudE1hbmFnZW1lbnQiOlsxLDIsMyw0XSwiVm91Y2hlciI6WzEsMiwzLDQsNSw2LDcsOF0sIlZvdWNoZXJUaWNrZXQiOlsxLDIsMyw0LDUsNl19LCJ0b2tlbl9oYXNoIjoiIn0.RKpX0_65gUFKk8nY80vArH3DpuVUvfx4--CWekbh9mpxTUM2OLswo9fdCg-w-xsdgWWiDmHu2tAXGBgJp8pdnJ37HJQfqkDFl7OQ4dyFD3ROdSXXSP76Ys_YguwCPR13dZoCSolCtmW2-faaP8gJFcrA0xWg8qN2wgHgcPDNtaxYqbKaF0h7libDsmU4se7AcUr_mU1DX6k_Lfptx5wIvmKWkig5ZO9KHY3d6XNzkqBlk1YKsOciBm40o1ezkL2vB9B9nFAe8ufNvq8uFoel2IjHWeNqYn5u6OCqx8ib_t0y8VoeTwOblSbDvfNqN3OZPi29wV_BxZA6Pdbaf6ix6jSXtV8JzDXhpeckB1VB1Dx9gKGzVqThvAqzp1yb8SDVZ0iVr4lSbkd3xbyvmU28YdT_aueFshzSPuOmUntn3bwzfgDVV0sO2scIubap3Q31B5iElN_s3_w4ga7yX4hqn5kj7FAe6ARq9r0rp3oyKW4XDUqFIwavtzGS_d4A2aGMO9vSaV-dkcMWfKqrhJf7A7_OIEnC39jBtJ2CrzzwLqbnN6Wl9Lp6RC8mF7ACCGIbxiqX1KkEvIK76y94U068BjHM58tgJJNCfwkUG3AU7ko3X9asNcNMnImtQgj3gr1aq611U5FWCst9CfSCVJneXmEBt8eNK9WTUC2gUVKt9i4");
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Long int_random = random.nextLong(10_000_000_000L, 100_000_000_000L);
        personJsonObject.put("session_id", String.valueOf(int_random));
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        HttpEntity<String> request = new HttpEntity<>(personJsonObject.toString(), headers);
        ResponseDTO responseDTO = new Gson().fromJson(request.getBody(),ResponseDTO.class);



    }
}

package ops.gateway.service;

import ops.gateway.model.request.SampleDto;
import ops.gateway.model.response.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface TestServiceInterface {
    ResponseEntity<String> callCheckerAnMaker(SampleDto sampleDto);
}

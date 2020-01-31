package ops.gateway.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import ops.gateway.annotation.LoggingActivityGateway;
import ops.gateway.model.request.SampleDto;
import ops.gateway.model.response.ResponseConstant;
import ops.gateway.model.response.ResponseDTO;
import ops.gateway.service.TestService;
import ops.gateway.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/")
@Validated
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "test")
    public ResponseEntity<?> testResponse(HttpServletRequest request) {
        String user_name = (String) request.getAttribute("user_name");

        try {
            ResponseEntity<String> response = testService.callCheckerAnMaker(new SampleDto());
            return new ResponseEntity<ResponseDTO>(ResponseConstant.responseOK(user_name, new Gson().fromJson(response.toString(), ResponseDTO.class)), HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<ResponseDTO>(ResponseConstant.responseOK(user_name, new Gson().fromJson(ex.getResponseBodyAsString(), ResponseDTO.class)), HttpStatus.UNAUTHORIZED);
        }
    }

    @SneakyThrows
    @LoggingActivityGateway
    @PostMapping(value = "sample")
    public ResponseEntity<?> sample(@RequestBody @Valid SampleDto sampleDto, BindingResult bindingResult, HttpServletRequest request) {
        String user_name = "null";
        try {

            user_name = (String) request.getAttribute("user_name");
            ResponseEntity<String> response = testService.callCheckerAnMaker(sampleDto);

            return new ResponseEntity<ResponseDTO>(ResponseConstant.responseOK(user_name,
                    Util.convert(response.getBody())),
                    HttpStatus.OK);
        } catch (HttpClientErrorException ex) {
            return new ResponseEntity<ResponseDTO>(ResponseConstant.responseOK(user_name, Util.convert(ex.getResponseBodyAsString())),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @LoggingActivityGateway
    @PostMapping(value = "demo", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> test(@RequestBody SampleDto sampleDto) {
        return new ResponseEntity<SampleDto>(sampleDto, HttpStatus.OK);
    }


}

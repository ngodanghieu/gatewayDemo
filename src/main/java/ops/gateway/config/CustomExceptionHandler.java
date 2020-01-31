package ops.gateway.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ops.gateway.model.response.ResponseConstant;
import ops.gateway.model.response.ResponseDTO;
import ops.gateway.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        String[] arr = ex.getLocalizedMessage().split(",");
        List<String> details = new ArrayList<>();
        if (arr != null && arr.length > 0){
            for (String s : arr) {
                String result = s.substring(s.indexOf("["));
                details.add(result);
            }
        }
        details = details.stream()
                .distinct()
                .collect(Collectors.toList());
        ResponseDTO responseDTO = ResponseConstant.ERROR_INVALID_INPUT_EXCEPTION(Constant.MessageApi.VALIDATION_FALSE,details);
        return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
    }

}

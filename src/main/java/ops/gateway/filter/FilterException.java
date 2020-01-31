package ops.gateway.filter;

import ops.gateway.model.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FilterException {
    public static ResponseEntity<?> errorUserNotRole(ResponseDTO responseDTO) {
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
    }
}

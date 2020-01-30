package ops.gateway.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

public class Util {
    public static Object mapperObject(ResponseEntity<?> data){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

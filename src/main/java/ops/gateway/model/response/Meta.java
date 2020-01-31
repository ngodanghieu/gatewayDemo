package ops.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Meta {
    private String code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> details;

    public Meta(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Meta(String code, String message, List<String> details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }
}

package ops.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class Body {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String user_name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("extra_data")
    private Object extra_data;

//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
//    @JsonProperty("function_name")
//    private String function_name;

    public Body(String user_name, Object data) {
        this.user_name = user_name;
        this.extra_data = data;
    }

    public Body(String user_name) {
        this.user_name = user_name;
    }

    public Body(Object object) {
        this.extra_data = object;
    }
}

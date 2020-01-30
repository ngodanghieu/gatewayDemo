package ops.gateway.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonInclude(content = Include.NON_NULL)
public class SampleDto implements Serializable {

    private static final long serialVersionUID = -2269848546953903790L;

    @NotNull(message = "[session_id] truyền vào không được null")
    @JsonProperty("session_id")
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}

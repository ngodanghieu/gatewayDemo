package ops.gateway.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ops.gateway.util.Constant;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public class SampleDto implements Serializable {

    @NotNull(message = Constant.ErrorValidation.MESSAGE_NOT_NULL_SESSION)
    @JsonProperty("session_id")
    private String sessionId;

    @NotNull(message = Constant.ErrorValidation.MESSAGE_NOT_NULL_INPUT_FILE_NAME)
    @Length(max = 20, message = Constant.ErrorValidation.MESSAGE_ERROR_MAX)
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = Constant.ErrorValidation.MESSAGE_ERROR_REGULAR)
    @JsonProperty("input_file_name")
    private String input_file_name;

}

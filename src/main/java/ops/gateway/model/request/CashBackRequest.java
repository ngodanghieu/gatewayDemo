package ops.gateway.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ops.gateway.util.Constant;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CashBackRequest {

    @NotNull(message = Constant.ErrorValidation.MESSAGE_NOT_NULL_TRANSACTION_ID)
    @JsonProperty("original_transaction_id")
    private Long original_transaction_id;

    @Min(value = 0,message =Constant.ErrorValidation.MESSAGE_ERROR_AMOUNT)
    @JsonProperty("amount")
    private Double amount;

    @Length(max = 20 , message = Constant.ErrorValidation.MESSAGE_ERROR_DES)
    @JsonProperty("desc")
    private String desc;
}

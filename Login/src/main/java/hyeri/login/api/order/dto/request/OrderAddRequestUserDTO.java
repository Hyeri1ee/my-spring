package hyeri.login.api.order.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class OrderAddRequestUserDTO {

    @NotNull
    @NotEmpty
    private String  userid;

    @NotNull
    @NotEmpty
    private String itemname;

    public static OrderAddRequestUserDTO of(OrderAddRequestDTO orderAddRequestDTO){
        return OrderAddRequestUserDTO.builder()
                .userid(orderAddRequestDTO.getKnums())
                .itemname(orderAddRequestDTO.getItemname())
                .build();
    }
}

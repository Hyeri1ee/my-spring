package hyeri.login.api.order.dto.request;

import hyeri.login.api.user.enums.RoleName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderAddRequestDTO {

    @NotNull
    private String knums; //k를 4인 경우로 각 숫자값은 20이하 : 예시 "12 3 4 5"

    @NotNull
    @NotEmpty
    private String itemname;

}

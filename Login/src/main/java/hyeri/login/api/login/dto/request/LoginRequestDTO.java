package hyeri.login.api.login.dto.request;

import lombok.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
public class LoginRequestDTO {

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    private String password;

}

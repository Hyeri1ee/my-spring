package hyeri.login.api.login.application;

import hyeri.login.api.login.dto.request.LoginRequestDTO;
import hyeri.login.api.login.dto.response.LoginResponseDTO;

public interface LoginService {

    LoginResponseDTO login(final LoginRequestDTO loginRequestDTO);

}
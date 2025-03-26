package hyeri.login.api.user.controller;

import hyeri.login.api.common.response.entity.ApiResponseEntity;
import hyeri.login.api.user.application.UserAddService;
import hyeri.login.api.user.dto.request.UserAddRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserAddService userAddService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseEntity> register(@RequestBody @Valid UserAddRequestDTO userAddRequestDTO) throws NoSuchAlgorithmException {
        // 사용자 정보 저장
        String privatekey = userAddService.addUser(userAddRequestDTO);

        return ApiResponseEntity.successResponseEntity(privatekey);
    }

}
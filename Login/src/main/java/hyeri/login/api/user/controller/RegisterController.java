package hyeri.login.api.user.controller;

import hyeri.login.api.common.response.entity.ApiResponseEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final UserAddService userAddService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseEntity> register(@RequestBody @Valid UserAddRequestDTO userAddRequestDTO) {
        // 사용자 정보 저장
        userAddService.addUser(userAddRequestDTO);

        return ApiResponseEntity.successResponseEntity();
    }

}

package hyeri.login.api.order.controller;

import hyeri.login.api.common.response.entity.ApiResponseEntity;
import hyeri.login.api.user.application.UserDelService;
import hyeri.login.api.user.application.UserGetService;
import hyeri.login.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/order")
@RequiredArgsConstructor
public class OrderController {
    private final Ord userGetService;

    private final UserDelService userDelService;

    @GetMapping("/info")
    public ResponseEntity<ApiResponseEntity> dashboard(Authentication authentication) {
        // 사용자 정보 조회
        var result = userGetService.getUserById(JwtUtil.getLoginId(authentication));

        return ApiResponseEntity.successResponseEntity(result);
    }
}

package hyeri.login.api.order.controller;

import hyeri.login.api.common.response.entity.ApiResponseEntity;
import hyeri.login.api.order.application.OrderAddService;
import hyeri.login.api.order.dto.request.OrderAddRequestDTO;
import hyeri.login.api.user.application.UserAddService;
import hyeri.login.util.jwt.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController("/order")
@RequiredArgsConstructor
public class OrderRegisterController {

    private final OrderAddService orderAddService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseEntity> register(Authentication authentication, @RequestBody @Valid OrderAddRequestDTO orderAddRequestDTO) throws NoSuchAlgorithmException {
        // 사용자의 주문 정보 저장
        String response = orderAddService.addOrder(JwtUtil.getLoginId(authentication),orderAddRequestDTO);

        return ApiResponseEntity.successResponseEntity(response);
    }

}
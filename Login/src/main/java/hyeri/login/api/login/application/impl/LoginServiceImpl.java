package hyeri.login.api.login.application.impl;

import hyeri.login.api.login.application.LoginService;
import hyeri.login.api.login.dto.request.LoginRequestDTO;
import hyeri.login.api.login.dto.response.LoginResponseDTO;
import hyeri.login.api.login.exception.LoginException;
import hyeri.login.api.login.exception.LoginExceptionResult;
import hyeri.login.api.token.vo.RefreshToken;
import hyeri.login.api.user.application.UserGetService;
import hyeri.login.api.user.dto.response.UserGetResponseDTO;
import hyeri.login.config.security.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserGetService userGetService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtProvider jwtProvider;

    private final RefreshToken refreshToken;

    @Override
    @Transactional
    public LoginResponseDTO login(final LoginRequestDTO loginRequestDTO) {
        // 사용자 정보 조회
        UserGetResponseDTO userInfo = userGetService.getUserByUserId(loginRequestDTO.getUserId());

        // password 일치 여부 체크
        if(!bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(), userInfo.password()))
            throw new LoginException(LoginExceptionResult.NOT_CORRECT);

        // jwt 토큰 생성
        String accessToken = jwtProvider.generateAccessToken(userInfo.id());

        // 기존에 가지고 있는 사용자의 refresh token 제거
        refreshToken.removeUserRefreshToken(userInfo.id());

        // refresh token 생성 후 저장
        String refreshTokenNew = jwtProvider.generateRefreshToken(userInfo.id());
        refreshToken.putRefreshToken(refreshTokenNew, userInfo.id());

        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenNew)
                .build();
    }

}
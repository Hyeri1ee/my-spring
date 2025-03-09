package hyeri.login.api.token.application.impl;

import hyeri.login.api.token.application.RefreshTokenService;
import hyeri.login.api.token.dto.response.RefreshTokenResponseDTO;
import hyeri.login.api.token.exception.RefreshTokenException;
import hyeri.login.api.token.exception.RefreshTokenExceptionResult;
import hyeri.login.api.token.vo.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final JwtProvider jwtProvider;
    private final RefreshToken refreshTokenManager;

    /**
     * refresh token을 이용하여 access token, refresh token 재발급
     *
     * @param refreshToken refresh token
     * @return RefreshTokenResponseDTO
     */
    @Override
    public RefreshTokenResponseDTO refreshToken(final String refreshToken) {
        // refresh token 유효성 검증
        checkRefreshToken(refreshToken);

        // refresh token id 조회
        var id = refreshTokenManager.getRefreshToken(refreshToken);

        // 새로운 access token 생성
        String newAccessToken = jwtProvider.generateAccessToken(id);

        // 기존에 가지고 있는 사용자의 refresh token 제거
        refreshTokenManager.removeUserRefreshToken(id);

        // 새로운 refresh token 생성 후 저장
        String newRefreshToken = jwtProvider.generateRefreshToken(id);
        refreshTokenManager.putRefreshToken(newRefreshToken, id);

        return RefreshTokenResponseDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    /**
     * refresh token 검증
     *
     * @param refreshToken refresh token
     */
    private void checkRefreshToken(final String refreshToken) {
        if(Boolean.FALSE.equals(jwtProvider.validateToken(refreshToken)))
            throw new RefreshTokenException(RefreshTokenExceptionResult.INVALID);
    }

}
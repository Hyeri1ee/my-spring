package hyeri.login.api.token.vo;

import hyeri.login.api.token.exception.RefreshTokenException;
import hyeri.login.api.token.exception.RefreshTokenExceptionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * RefreshToken 저장 객체
 *
 * <p>
 * Redis를 사용하여 RefreshToken을 관리
 * 만료 시간을 설정하여 자동으로 토큰 관리가 가능
 * </p>
 */
@Component
@RequiredArgsConstructor
public class RefreshToken {

    private final RedisTemplate<String, Long> redisTemplate;
    private static final String TOKEN_PREFIX = "refresh_token:";
    private static final long TOKEN_EXPIRATION_TIME = 14; // 14일 (필요에 따라 조정)

    /**
     * refresh token get
     *
     * @param refreshToken refresh token
     * @return id
     */
    public Long getRefreshToken(final String refreshToken) {
        ValueOperations<String, Long> valueOperations = redisTemplate.opsForValue();
        Long id = valueOperations.get(TOKEN_PREFIX + refreshToken);

        if (id == null) {
            throw new RefreshTokenException(RefreshTokenExceptionResult.NOT_EXIST);
        }

        return id;
    }

    /**
     * refresh token put
     *
     * @param refreshToken refresh token
     * @param id id
     */
    public void putRefreshToken(final String refreshToken, Long id) {
        ValueOperations<String, Long> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(
                TOKEN_PREFIX + refreshToken,
                id,
                TOKEN_EXPIRATION_TIME,
                TimeUnit.DAYS
        );
    }

    /**
     * refresh token remove
     *
     * @param refreshToken refresh token
     */
    public void removeRefreshToken(final String refreshToken) {
        redisTemplate.delete(TOKEN_PREFIX + refreshToken);
    }

    /**
     * user refresh token remove
     * Redis에서는 역방향 조회가 어려우므로 사용자 ID 기반으로 별도의 인덱스 키를 관리
     *
     * @param userId user id
     */
    public void removeUserRefreshToken(final long userId) {
        String userTokenKey = "user_tokens:" + userId;

        // 사용자의 모든 리프레시 토큰 키 조회
        // 참고: 실제 구현에서는 사용자와 토큰의 매핑을 별도로 관리해야 함
        // 이 예제에서는 단순화를 위해 직접 패턴 검색 사용
        for (String key : redisTemplate.keys(TOKEN_PREFIX + "*")) {
            Long storedId = redisTemplate.opsForValue().get(key);
            if (storedId != null && storedId == userId) {
                redisTemplate.delete(key);
            }
        }
    }
}
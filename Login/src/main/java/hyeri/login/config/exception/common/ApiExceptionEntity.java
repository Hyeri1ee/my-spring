package hyeri.login.config.exception.common;

import lombok.Builder;

@Builder
public record ApiExceptionEntity(String errorCode, String errorMsg) {
}

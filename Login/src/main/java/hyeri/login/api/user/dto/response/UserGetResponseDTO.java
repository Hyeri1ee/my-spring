package hyeri.login.api.user.dto.response;

import hyeri.login.api.user.domain.entity.User;
import hyeri.login.api.user.enums.RoleName;
import lombok.Builder;

@Builder
public record UserGetResponseDTO(long id, String userId, String password, String name, String tel, RoleName roleName) {

    public static UserGetResponseDTO of(User user) {
        return UserGetResponseDTO.builder()
                .id(user.getId())
                .userId(user.getLoginInfo().getUserId())
                .password(user.getLoginInfo().getPassword())
                .name(user.getUserInfo().getName())
                .tel(user.getUserInfo().getTel())
                .roleName(user.getRoleInfo().getRoleName())
                .build();
    }

}
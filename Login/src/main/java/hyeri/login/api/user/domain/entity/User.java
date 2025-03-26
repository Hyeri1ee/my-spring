package hyeri.login.api.user.domain.entity;

import hyeri.login.api.common.entity.RegModDt;
import hyeri.login.api.user.domain.entity.value.RoleInfo;
import hyeri.login.api.user.dto.request.UserAddRequestDTO;
import hyeri.login.api.user.domain.entity.value.LoginInfo;
import hyeri.login.api.user.domain.entity.value.UserInfo;
import hyeri.login.util.jwt.RSAUtill;
import hyeri.login.util.jwt.dto.RSAkeys;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.security.NoSuchAlgorithmException;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE user SET del_yn = true WHERE id = ?")
@SQLRestriction("del_yn = false")
public class User extends RegModDt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private LoginInfo loginInfo;

    @Embedded
    private RoleInfo roleInfo;

    @Embedded
    private UserInfo userInfo;

    private boolean delYn = Boolean.FALSE; // 삭제 여부 기본값 false

    @Column(length = 2048)
    private String RSApublickey;//개인 공개키

    /**
     * UserAddRequestDTO to User Entity
     *
     * @param dto UserAddRequestDTO
     * @return User Entity
     */
    public static User of(UserAddRequestDTO dto) {
        // Login Info
        LoginInfo inputLoginInfo = LoginInfo.builder()
                .userId(dto.getUserId())
                .password(dto.getPassword())
                .build();

        // User Info
        UserInfo inputUserInfo = UserInfo.builder()
                .name(dto.getName())
                .tel(dto.getTel())
                .build();

        // Role Info
        RoleInfo inputRoleInfo = RoleInfo.builder()
                .roleName(dto.getRoleName())
                .build();

        return User.builder()
                .loginInfo(inputLoginInfo)
                .userInfo(inputUserInfo)
                .roleInfo(inputRoleInfo)
                .build();
    }

    public String makeRSA() throws NoSuchAlgorithmException {
        RSAkeys rsAkeys = RSAUtill.generateRsaKeyPair();
        this.RSApublickey = rsAkeys.publickey();
        return rsAkeys.privatekey();
    }

}
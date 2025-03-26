package hyeri.login.api.user.application;

import hyeri.login.api.user.dto.request.UserAddRequestDTO;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;

@Repository
public interface UserAddService {

    /**
     * 사용자 추가
     *
     * @param userAddRequestDTO UserAddRequestDTO
     */
    String addUser(final UserAddRequestDTO userAddRequestDTO) throws NoSuchAlgorithmException;

}
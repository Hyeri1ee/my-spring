package hyeri.login.api.user.application.impl;

import hyeri.login.api.user.application.UserAddService;
import hyeri.login.api.user.domain.repository.UserRepository;
import hyeri.login.api.user.dto.request.UserAddRequestDTO;
import hyeri.login.api.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserAddServiceImpl implements UserAddService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 사용자 추가
     *
     * @param userAddRequestDTO UserAddRequestDTO
     */
    @Override
    @Transactional
    public void addUser(final UserAddRequestDTO userAddRequestDTO) {
        // User DTO to Entity
        User user = User.of(userAddRequestDTO);

        // password 암호화
        user.getLoginInfo().encryptPassword(bCryptPasswordEncoder);

        // save
        userRepository.save(user);
    }
}

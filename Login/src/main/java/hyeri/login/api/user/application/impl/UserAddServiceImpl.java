package hyeri.login.api.user.application.impl;

import hyeri.login.api.user.application.UserAddService;
import hyeri.login.api.user.domain.repository.UserRepository;
import hyeri.login.api.user.dto.request.UserAddRequestDTO;
import hyeri.login.api.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

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
    public String addUser(final UserAddRequestDTO userAddRequestDTO) throws NoSuchAlgorithmException {
        // User DTO to Entity
        User user = User.of(userAddRequestDTO);

        // password 암호화
        user.getLoginInfo().encryptPassword(bCryptPasswordEncoder);

        //RSA 키 쌍
        String privateKey = user.makeRSA();

        // save
        userRepository.save(user);

        return privateKey;
    }
}

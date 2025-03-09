package hyeri.login.api.user.application;

public interface UserAddService {

    /**
     * 사용자 추가
     *
     * @param userAddRequestDTO UserAddRequestDTO
     */
    void addUser(final UserAddRequestDTO userAddRequestDTO);

}

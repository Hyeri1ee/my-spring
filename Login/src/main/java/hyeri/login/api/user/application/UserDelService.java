package hyeri.login.api.user.application;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDelService {

    /**
     * 사용자 삭제
     *
     * @param id 사용자 idx
     */
    void delUser(final long id);

}
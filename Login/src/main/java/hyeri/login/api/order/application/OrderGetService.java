package hyeri.login.api.order.application;

import hyeri.login.api.order.dto.response.OrderGetResponseDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGetService {
    /**
     * 사용자 idx를 이용해 사용자 정보 조회
     *
     * @param id 사용자 idx
     * @return 사용자 정보 UserGetResponseDTO
     */
    OrderGetResponseDTO getUserById(final long id);
}

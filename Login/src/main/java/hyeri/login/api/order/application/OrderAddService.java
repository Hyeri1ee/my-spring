package hyeri.login.api.order.application;

import hyeri.login.api.order.dto.request.OrderAddRequestDTO;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;

@Repository
public interface OrderAddService {
    String addOrder(long loginId, final OrderAddRequestDTO orderAddRequestDTO) throws NoSuchAlgorithmException;

}

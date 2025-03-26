package hyeri.login.api.order.domain.repository;

import hyeri.login.api.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {

    /**
     * 사용자 id를 이용해 사용자 정보 조회
     *
     * @param userId 사용자 id
     * @return Optional User
     */
    Optional<Order> findByorderId(final String userId);

}
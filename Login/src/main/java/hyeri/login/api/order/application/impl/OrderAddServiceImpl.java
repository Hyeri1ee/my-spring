package hyeri.login.api.order.application.impl;

import hyeri.login.api.order.application.OrderAddService;
import hyeri.login.api.order.domain.repository.OrderRepository;
import hyeri.login.api.order.dto.request.OrderAddRequestDTO;
import hyeri.login.api.order.dto.request.OrderAddRequestUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderAddServiceImpl implements OrderAddService {
    private final OrderRepository orderRepository;

    @Override
    public String addOrder(long loginId, OrderAddRequestDTO orderAddRequestDTO){
        orderRepository.save(OrderAddRequestUserDTO.of(orderAddRequestDTO));
        return "저장성공";
    }
}

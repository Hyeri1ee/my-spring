package hyeri.login.api.order.application.impl;

import hyeri.login.api.order.domain.repository.OrderRepository;
import hyeri.login.api.order.dto.request.OrderAddRequestDTO;
import hyeri.login.api.order.dto.request.OrderAddRequestUserDTO;
import hyeri.login.api.order.dto.response.OrderGetResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderGetServiceImpl {
    private final OrderRepository orderRepository;

    @Override
    public List<OrderGetResponseDTO> getOrder(long user){
        orderRepository.findByorderId(OrderAddRequestUserDTO.of(orderAddRequestDTO));
        return "저장성공";
    }
}

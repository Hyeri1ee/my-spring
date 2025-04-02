package hyeri.login.api.order.dto.response;

import hyeri.login.api.order.domain.entity.Order;
import lombok.Builder;

@Builder
public record OrderGetResponseDTO(long id, String userId, String itemname) {

    public static OrderGetResponseDTO of (Order order){
        return OrderGetResponseDTO.builder()
                .id(order.getId())
                .userId(order.getUserid())
                .itemname(order.getItemname())
                .build();
    }

}
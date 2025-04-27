package hyeri.login.api.order.domain.entity;

import hyeri.login.api.common.entity.RegModDt;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "orders")
@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends RegModDt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userid;//k-1로 암호화된 값 // k = 4 (4 5 12 100)

    private String itemname;

    public static Order of (long id, String userid, String itemname){
        return Order.builder()
                .id(id)
                .userid(userid)
                .itemname(itemname)
                .build();
    }

}

package jpabook.jpashop.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;
    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING) // enum타입 사용 방
    private DeliveryStatus status;// READY COMP


}

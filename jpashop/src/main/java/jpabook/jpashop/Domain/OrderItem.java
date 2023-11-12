package jpabook.jpashop.Domain;

import jakarta.persistence.*;
import jpabook.jpashop.Domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "oder_item_id")
    private Long id;
    @ManyToOne(fetch = LAZY)// 모든 Xtoone관계는 지연로딩으로 설정해주자.
    @JoinColumn(name = "item_id")
    private Item item;
    @ManyToOne(fetch = LAZY)// 모든 Xtoone관계는 지연로딩으로 설정해주자.
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

}

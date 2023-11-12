package jpabook.jpashop.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    @ManyToOne(fetch = LAZY)// 모든 Xtoone관계는 지연로딩으로 설정해주자.
    @JoinColumn(name = "member_id") // fk 지정해주기
    private Member member;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)//order를 cas cascade하면 나머지 orderItems에도 persist를 전파해준다.
    private List<OrderItem> orderItems = new ArrayList<>();
    //1대1 관계에서  fk의 값은 접근을 많이 하는 테이블의 값을 fk로 설정하자
    @OneToOne(fetch = LAZY,cascade = CascadeType.ALL)//모든 xtoone관계는 지연로딩으로 설정, order를 cascade해주면서 delivery도 같이 persist해주도록 만든다

    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
    private LocalDateTime orderDate; // 주문 시간
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // enum으로 주문 상태 order cancel로 상태 표시

    //====연관관계 편의 메소드=====
    //양방향 연관관계에서 주인키를 설정했기 때문에 db에는 적용되지만 로직상에서 연관된 객체에 같은 값을 헷갈리지 않고 넣어주기 위해 사용한다.
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }



}

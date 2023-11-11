package jpabook.jpashop.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;
    @Embedded // 내장타입을 포함했다.
    private Address address;
    @OneToMany(mappedBy = "member") // 주인키는 그대로 두고 주인키가 아닌곳에 mappedBy사용 주인키는 fk가 있는 곳으로 정하자
    private List<Order> orders = new ArrayList<>();

}

package jpabook.jpashop.Domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.Domain.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속 관계 데이터 베이스 형성을 싱글테이블 전략으로 한다 가장 단순하게 한테이블에 다 넣는 방법이다.
@DiscriminatorColumn(name = "dtype") //book album movie마다 어떻게 할지
@Getter
@Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

}

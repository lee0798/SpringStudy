package jpabook.jpashop.Domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.Domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
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

    // === 비지니스 로직 === //
    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity){
        if(stockQuantity - quantity < 0){
            throw new NotEnoughStockException("need more stock");
        }else {
            this.stockQuantity -= quantity;
        }
    }


}

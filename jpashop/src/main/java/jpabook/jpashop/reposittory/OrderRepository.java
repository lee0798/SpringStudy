package jpabook.jpashop.reposittory;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.Domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;
    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // 검색 동적 쿼리 만들어야함

}

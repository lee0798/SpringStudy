package jpabook.jpashop.reposittory;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.Domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em ;
    public void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        }else{
            em.merge(item); // 이미 등록된 아이템은 update를 통해 로직을 구현한다.
        }
    }
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}

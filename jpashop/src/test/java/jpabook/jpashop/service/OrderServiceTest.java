package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.Domain.Address;
import jpabook.jpashop.Domain.Member;
import jpabook.jpashop.Domain.Order;
import jpabook.jpashop.Domain.OrderStatus;
import jpabook.jpashop.Domain.item.Book;
import jpabook.jpashop.Domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.reposittory.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = creatMember();//extract method = option + command + m

        Item book = createBook("jpa", 10000, 10);
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), 4);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.ORDER,getOrder.getStatus());
        assertEquals(1, getOrder.getOrderItems().size());
        assertEquals(10000 * 4, getOrder.getTotalPrice());
        assertEquals(6, book.getStockQuantity());

    }



    @Test
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = creatMember();
        Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 11;
        
        //when
        try {
            orderService.order(member.getId(), item.getId(), orderCount);
        }catch (NotEnoughStockException e){
            System.out.println("재고수량 부족 오류 테스트 성공");
            return;
        }
        //then
        fail("재고수량 부족 예외가 발생해야한다.");
        
    }
    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = creatMember();
        Item item = createBook("JPA", 10000, 10);
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals(10, item.getStockQuantity());

    }
    private Item createBook(String name, int price, int stockQuantity) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private static int getStockQuantity() {
        return 10;
    }

    private Member creatMember() {
        Member member = new Member();
        member.setName("이도환");
        member.setAddress(new Address("서울", "집", "123-123"));
        em.persist(member);
        return member;
    }

}
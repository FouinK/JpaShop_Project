package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문_테스트() {
        // given
        Member member = createMember();

        Book book = createBook("JPA BOOK", 10000, 10);

        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order getOrder = orderRepository.findOne(orderId);

        Assertions.assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        Assertions.assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 수가 정확해야 한다.");
        Assertions.assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문한 상품으 개수에 맞게 가격이 설정 되어야 한다,");
        Assertions.assertEquals(10 - orderCount, book.getStockQuantity(), "주문한 수량만큼 재고가 줄어야한다");
    }

    @Test
    public void 상품주문_재고수량초과_테스트() {
        // given
        Member member = createMember();
        Book book = createBook("JPA BOOK", 10000, 10);

        int orderCount = 1;

        // when
        try {
            orderService.order(member.getId(), book.getId(), orderCount);
        } catch (NotEnoughStockException e) {
            e.printStackTrace();
            return;
        }

        Assertions.fail("상품 재고수량 예와가 발생해야함");

        // then

    }

    @Test
    public void 주문취소_테스트() {
        // given
        Member member = createMember();
        Book book = createBook("JPA BOOK", 10000, 10);

        int orderCount = 3;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // when
        orderService.cancelOrder(orderId);

        // then
        Order findOrder = orderRepository.findOne(orderId);
        Assertions.assertEquals(10, book.getStockQuantity(), "상품의 재고가 원상복구 되어야한다");
        Assertions.assertEquals(OrderStatus.ORDER_CANCEL, findOrder.getStatus(), "상품의 주문 상태가 ORDER_CANCEL로 변경되어야 한다");

    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        em.flush();
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원 1");
        member.setAddress(new Address("서울", "경기", "123-123"));
        em.persist(member);
        em.flush();
        return member;
    }


    @Test
    public void OrderFindOneTest() {

        long startTime = System.currentTimeMillis();


        List<Order> all = orderRepository.findAll();


        for (Order order : all) {
            order.getId();
            order.getMember().getId();
            order.getDelivery().getId();
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                orderItem.getId();
            }
        }

        long stopTime = System.currentTimeMillis();
        System.out.println("no fetch join result time");
        System.out.println("걸린 시간 : "+(stopTime - startTime) + "ms");


    }

    @Test
    public void FetchJoinOrderFindOneTest() {

        long startTime = System.currentTimeMillis();

        List<Order> all = orderRepository.findAll_fetch_join();


        for (Order order : all) {
            order.getId();
            order.getMember().getId();
            order.getDelivery().getId();
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                orderItem.getId();
            }
        }

        long stopTime = System.currentTimeMillis();
        System.out.println("fetch join result time");
        System.out.println("걸린 시간 : "+(stopTime - startTime) + "ms");

    }
}

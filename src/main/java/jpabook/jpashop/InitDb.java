package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();

    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "111");
            em.persist(member);

            Book book1 = createBook("JPA1 BOOK", 20000, 200);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 40000, 300);
            em.persist(book2);

            Book book3 = createBook("JPA2 BOOK", 40000, 300);
            Book book4 = createBook("JPA2 BOOK", 40000, 300);
            Book book5 = createBook("JPA2 BOOK", 40000, 300);
            Book book6 = createBook("JPA2 BOOK", 40000, 300);
            Book book7 = createBook("JPA2 BOOK", 40000, 300);
            Book book8 = createBook("JPA2 BOOK", 40000, 300);


            em.persist(book3);
            em.persist(book4);
            em.persist(book5);
            em.persist(book6);
            em.persist(book7);
            em.persist(book8);


            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
            OrderItem orderItem3 = OrderItem.createOrderItem(book3, 40000, 4);
            OrderItem orderItem4 = OrderItem.createOrderItem(book4, 40000, 4);
            OrderItem orderItem5 = OrderItem.createOrderItem(book5, 40000, 4);
            OrderItem orderItem6 = OrderItem.createOrderItem(book6, 40000, 4);
            OrderItem orderItem7 = OrderItem.createOrderItem(book7, 40000, 4);
            OrderItem orderItem8 = OrderItem.createOrderItem(book8, 40000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2,orderItem3,orderItem4,orderItem5,orderItem6,orderItem7,orderItem8);
            em.persist(order);


            List<OrderItem> orderItemList = new ArrayList<>();

//            for (int i = 0; i < 1500; i++) {
//                Book book = createBook("book"+i, 100+i, 100+i);
//                em.persist(book);
//                OrderItem orderItem = OrderItem.createOrderItem(book, 1, 1);
//                orderItemList.add(orderItem);
//                Order order1 = Order.createOrder(member,delivery,orderItem);
//                em.persist(order1);
//            }

        }

        public void dbInit2() {
            Member member = createMember("userB", "진주", "2", "222");
            em.persist(member);

            Book book1 = createBook("SPRING1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("SRPING2 BOOK", 50000, 500);
            em.persist(book2);

            Book book3 = createBook("JPA2 BOOK", 40000, 300);
            Book book4 = createBook("JPA2 BOOK", 40000, 300);
            Book book5 = createBook("JPA2 BOOK", 40000, 300);
            Book book6 = createBook("JPA2 BOOK", 40000, 300);
            Book book7 = createBook("JPA2 BOOK", 40000, 300);
            Book book8 = createBook("JPA2 BOOK", 40000, 300);

            em.persist(book3);
            em.persist(book4);
            em.persist(book5);
            em.persist(book6);
            em.persist(book7);
            em.persist(book8);




            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 50000, 5);
            OrderItem orderItem3 = OrderItem.createOrderItem(book3, 40000, 4);
            OrderItem orderItem4 = OrderItem.createOrderItem(book4, 40000, 4);
            OrderItem orderItem5 = OrderItem.createOrderItem(book5, 40000, 4);
            OrderItem orderItem6 = OrderItem.createOrderItem(book6, 40000, 4);
            OrderItem orderItem7 = OrderItem.createOrderItem(book7, 40000, 4);
            OrderItem orderItem8 = OrderItem.createOrderItem(book8, 40000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2,orderItem3,orderItem4,orderItem5,orderItem6,orderItem7,orderItem8);
            em.persist(order);

//            for (int i = 0; i < 500; i++) {
//                Book book = createBook("book"+i, 100+i, 100+i);
//                em.persist(book);
//                OrderItem orderItem = OrderItem.createOrderItem(book, 1, 1);
//                Order order1 = Order.createOrder(member,delivery,orderItem);
//                em.persist(order1);
//            }

        }

        private static Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private static Book createBook(String name, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        private static Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
    }

}


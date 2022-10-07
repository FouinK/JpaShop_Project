package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @PersistenceContext
    EntityManager em;

    @Test
    @Rollback(value = false)
    void 아이템_저장_테스트() {

        // given
        Book book = new Book();
        book.setAuthor("testAuthor");
        book.setIsbn("121");
        book.setName("잠자는 숲속의 공주");
        book.setPrice(12000);
        book.addStock(1);

        // when
        Long findItemId = itemService.saveItem(book);

        // then
        em.flush();
        Assertions.assertEquals(book.getId(), findItemId);

    }

    @Test
    void findItems() {
        List<Item> itemList = itemService.findItems();
        System.out.println(itemList.get(0).getName());

    }

    @Test
    void findItem() {
    }
}
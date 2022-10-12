package jpabook.jpashop.service;

import jpabook.jpashop.Controller.BookForm;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional      //메서드에 있는 어노테이션이 우선권을 가지기 때문에 저장 가능
    public Long saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, BookForm param) {
        Book findItem = (Book) itemRepository.findOne(itemId);

        findItem.setName(param.getName());
        findItem.setPrice(param.getPrice());
        findItem.setStockQuantity(param.getStockQuantity());
        findItem.setAuthor(param.getAuthor());
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findItem(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}

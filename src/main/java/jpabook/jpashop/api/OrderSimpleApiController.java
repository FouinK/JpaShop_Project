package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * xToOne (ManyToOne, OneToOne의 관계에서 최적화)
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();        //겟 멤버까지는 프록시라서 쿼리가 날아가지 않는데 겟 네임을 작성하고부터는 진짜로 날아감      (Lazy강제 초기화)
            order.getDelivery().getAddress();   //겟 딜리버리까지는 프록시라서 쿼리가 날아가지 않는데 겟 어드레스를 작성하고부터는 진짜로 날아감 (Lazy강제 초기화)
        }
        return all;
    }

}

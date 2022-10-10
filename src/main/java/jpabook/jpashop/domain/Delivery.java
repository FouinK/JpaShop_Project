package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @JsonIgnore     //JSON에서 빼줌 (무한루프 일어나지 않도록 미연에 방지 JsonBack이랑은 다름)
    @OneToOne(mappedBy = "delivery",fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)        //디폴트는 오디널인데 오디널은 숫자로 들어감 만약 변동사항이 생기면 중간에 있던 값이 꼬여버림 그래서 무조건 String으로 해야함
    private DeliveryStatus status; //READY, COMP
}

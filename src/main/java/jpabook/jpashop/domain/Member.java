package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @Column(name = "member_id")
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")     //오더 테이블에 있는 member 객체에 매핑이 됐다는 의미(연관관계의 주인이 아님)
    private List<Order> orderList = new ArrayList<>();

}

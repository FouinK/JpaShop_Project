package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@SpringBootTest     //통합테스트 할 떄 필요함. 스프링 띄우고 테스트를 실행하게 됨
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    void join_test() {
        //given
        Member member = new Member();
        member.setName("TestName");

        //when
        Long joinId = memberService.join(member);

        //then
        em.flush();     //트랜잭션 롤백을 하게 되어도 insert 쿼리문을 로그에 찍을 수 있음
        Assertions.assertEquals(joinId, member.getId());
    }

    @Test
    void join_exception_test() {
        //given
        Member member1 = new Member();
        member1.setName("Lee");

        Member member2 = new Member();
        member2.setName("Lee");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2);    //예외가 터져야 함.
        } catch (IllegalStateException e) {
            System.out.println("테스트 성공 !");
            return ;
        }

//        Assertions.assertThrows(IllegalStateException.class, ()->{
//            memberService.join(member2);
//        });

        //then
        Assertions.fail("테스트 실패 ! 예외가 터져야함 ");
    }

    @Test
    void findMembers() {
        List<Member> members = memberService.findMembers();
        Optional<Member> optionalMember = members.stream().findAny();
        System.out.println("저장된 회원 가져오기 : "+optionalMember.get().getName());
    }

    @Test
    void findMember() {

    }

}
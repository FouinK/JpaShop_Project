package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //JPQL은 from의 대상이 Table이 아니라 클래스의 Entity임
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByname(String name) {
        return em.createQuery("select m from Member m where m.name = :name")
                .setParameter("name", name)
                .getResultList();
    }

    public Optional<Member> existsMember(String name) {
        List<Member> members = em.createQuery("SELECT m FROM Member m where m.name = :name")
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultList();

        return members.stream().findAny();
    }
}

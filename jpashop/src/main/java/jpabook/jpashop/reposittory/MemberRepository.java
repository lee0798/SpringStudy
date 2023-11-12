package jpabook.jpashop.reposittory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.Domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository// 자동으로 스프링 bean에 등록해줌
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);//jpa가 member를 저장하는 로직
    }
    public Member findOne(Long id){
        return em.find(Member.class, id); //단건 조회 기능
    }
    public List<Member> findAll(){
//        List<Member> result = em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//        return result;  option + command + n 인라인 단축키로 합치기
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}

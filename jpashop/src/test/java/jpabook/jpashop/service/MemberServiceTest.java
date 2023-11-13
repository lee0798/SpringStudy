package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.Domain.Member;
import jpabook.jpashop.reposittory.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    //@Autowired
    //EntityManager em; // 실제 쿼리문을 보고 싶을때 em.flush()랑 같이 사용

    @Test
    //@Rollback(value = false) 롤백 안하고 커밋하면서 db에 저장
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("Lee");

        //when
        Long saveId = memberService.join(member);
        //then
        //em.flush();
        assertEquals(member, memberRepository.findOne(saveId));
        
    }
    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("lee1");
        Member member2 = new Member();
        member2.setName("lee1");
        
        //when
        memberService.join(member1);
        try {
            memberService.join(member2); // 예외가 발생해야한다.
        } catch (IllegalStateException e) {
            return;
        }
        //then
        fail("예외가 발생해야하는데 안발생했어!!");
        
    }

}
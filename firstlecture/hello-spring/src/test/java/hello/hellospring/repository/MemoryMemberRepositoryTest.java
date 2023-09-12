package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    @AfterEach
    public void afterEach(){
        repostiyory.clearStore();
    }
    MemoryMemberRepository repostiyory = new MemoryMemberRepository();
    @Test
    public void save(){
         Member member = new Member();
         member.setName("도환");
         repostiyory.save(member);
         Member result = repostiyory.findById(member.getId()).get();
         //Assertions.assertEquals(member, null);
         assertThat(member).isEqualTo(result);
     }
     @Test
     public void findByName(){
         Member member1 = new Member();
         member1.setName("jo88");
         repostiyory.save(member1);
        //member2 shift+f6 단축키 실행시 한번에 변수 바꾸기 가능
         Member member2 = new Member();
         member2.setName("jo89");
         repostiyory.save(member2);
         Member result = repostiyory.findByName("jo88").get();
         assertThat(result).isEqualTo(member1);
     }
     @Test
     public void findAll(){
         Member member1 = new Member();
         member1.setName("jo88");
         repostiyory.save(member1);
         //member2 shift+f6 단축키 실행시 한번에 변수 바꾸기 가능
         Member member2 = new Member();
         member2.setName("jo89");
         repostiyory.save(member2);
         List<Member> result = repostiyory.findAll();
         assertThat(result.size()).isEqualTo(2);
     }

}

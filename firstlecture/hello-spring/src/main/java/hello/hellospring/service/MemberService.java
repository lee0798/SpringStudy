package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//@Service component어노테이션을 이용한 의존관계 설정 방법
@Transactional
public class MemberService {//com+shift+t 누를시 테스트 파일 저절로 생성
    private final MemberRepository memberRepository;

    //construcctor 단축키 com+n
    //@Autowired component어노테이션을 이용한 의존관계 설정 방법
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */

    public Long join(Member member) {
        //중복 이름 안됨
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     *
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findone(Long memberId){
        return memberRepository.findById(memberId);
    }




}

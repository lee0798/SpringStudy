package jpabook.jpashop.service;

import jpabook.jpashop.Domain.Member;
import jpabook.jpashop.reposittory.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 기본적으로 데이터를 읽기만 하는 메소드들은 true로 해놓지만 그렇지 않은건 따로 트렌잭션을 걸어 놓는다.
@RequiredArgsConstructor
public class MemberService {

    // 스프링인 스프링 빈에 등록되어 있는 MemberRepository를 인젝션 해준다.
    //요즘 권장하는 방법으로 생성자 인젝션을 활용하여 test할때도 번거롭지 않도록 하기 위해 생성자를 만들어준다.
    // command + n (constructer를 활용)
    private final MemberRepository memberRepository;
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }  lombok requireArgsConstructor를 활용하면 생략하고 사용가능.



    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //Exception
        //option + command + v를 통해서 반환값을 만드는 단축키
        // 실무에서 두명이 동시에 중복 메소드를 통과할 경우를 대비해서 name에 unique제약 조건을 걸어 놓는 방식으로 문제를 예방한다.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    //단건 조회 기능
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}

package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;
    @Autowired //dependency injection(DI) component어노테이션을 이용한 의존관계 설정 방법
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}

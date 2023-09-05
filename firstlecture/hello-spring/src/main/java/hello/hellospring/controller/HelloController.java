package hello.hellospring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")//엔드포인트
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello";//hello.html 파일
    }
}

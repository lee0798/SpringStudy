package jpabook.jpashop.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수 입니다.") // springboot 2.3이상부터는 build.gradle에implementation 'org.springframework.boot:spring-boot-starter-validation' 추가해서 사용
    private String name;
    private String city;
    private String street;
    private String zipcode;

}

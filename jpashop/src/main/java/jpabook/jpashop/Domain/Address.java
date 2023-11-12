package jpabook.jpashop.Domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable // 내장 타입을  포함할 수 있다.
@Getter
public class Address {//
    private String city;
    private String street;
    private String zipcode;

    protected Address(){}
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

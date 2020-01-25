package com.example.demo;

import javax.persistence.Embeddable;


// Value 타입 매핑

@Embeddable
public class Address {
    private String street;

    private String city;

    private String state;

    private String zipCode;

}

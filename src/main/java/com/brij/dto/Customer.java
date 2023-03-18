package com.brij.dto;

import lombok.Data;

@Data
public class Customer {
    private int id;
    private String name;
    private CustomerType customerType;

}

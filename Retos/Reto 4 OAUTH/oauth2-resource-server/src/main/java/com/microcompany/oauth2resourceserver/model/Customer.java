package com.microcompany.oauth2resourceserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Customer {
    private Long id;
    private String name;
    private String email;
}
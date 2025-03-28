package com.microcompany.oauth2apiclient.model;

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
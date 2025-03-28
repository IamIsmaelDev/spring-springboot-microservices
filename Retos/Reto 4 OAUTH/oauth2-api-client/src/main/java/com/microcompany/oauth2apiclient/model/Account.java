package com.microcompany.oauth2apiclient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter @Setter
public class Account {
    private Long id;
    private String type;
    Date openingDate;
    private int balance;
    private Long ownerId;
}
package com.reto2.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank
    private String type;

    //@NotBlank
    @DateTimeFormat
    Date openingDate;

    //@NotBlank
    private int balance;

    //@NotBlank
    private Long ownerId;

    @Transient
    Customer owner;

    /*
    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    Customer owner;
    */


}

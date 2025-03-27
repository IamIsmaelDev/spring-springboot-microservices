package com.reto2.model;

import com.reto2.constraints.AccountType;
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
    @AccountType
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

    public Account(String type, Date openingDate, int balance, Long ownerId) {
        this.type = type;
        this.openingDate = openingDate;
        this.balance = balance;
        this.ownerId = ownerId;
    }


    /*
    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    Customer owner;
    */


}

package com.reto2.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Entity
//@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20, message = "Debe contener entre 3 - 20 carácteres")
    private String name;

    @NotBlank
    @Email
    private String email;

    /*
    @OneToMany(mappedBy = "owner")
    private List<Account> accountList;
     */
}

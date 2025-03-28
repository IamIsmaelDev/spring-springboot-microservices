package com.reto2.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatusMessage {
    private Integer status;
    private String message;
}
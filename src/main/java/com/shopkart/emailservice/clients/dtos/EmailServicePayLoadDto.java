package com.shopkart.emailservice.clients.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailServicePayLoadDto {
    private String to;
    private String from;
    private String subject;
    private String body;
}

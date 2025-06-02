package com.email.sender.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactFormDto {
    private String name;
    private String email;
    private String phone;
    private String message;

    
}

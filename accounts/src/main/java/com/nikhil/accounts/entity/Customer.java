package com.nikhil.accounts.entity;


import jakarta.persistence.*;
import com.eazybytes.nikhil.entity.BaseEntity;

import lombok.*;

@Entity
@Setter@Getter@ToString@AllArgsConstructor@NoArgsConstructor
public class Customer  extends  BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;






}

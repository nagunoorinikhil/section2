package com.nikhil.accounts.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter@Getter@ToString@AllArgsConstructor@NoArgsConstructor
public class Customer  extends  BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CustomerId;

    private String name;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;






}

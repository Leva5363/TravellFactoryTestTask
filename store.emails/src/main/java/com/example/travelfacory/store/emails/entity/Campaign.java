package com.example.travelfacory.store.emails.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Campaign extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;
}

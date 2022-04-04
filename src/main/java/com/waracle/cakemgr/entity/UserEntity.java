package com.waracle.cakemgr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name","email"})})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(name = "user_name")
    private String userName;
    @NotBlank
    @Column(name = "password")
    private String password;
    @NotBlank
    @Column(name = "email")
    private String email;

    public UserEntity(String user, String password, String email) {
        this.userName= user;
        this.password = password;
        this.email = email;
    }
}
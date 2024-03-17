package ru.netology.diplomproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_data", schema = "public")
public class AppUser {

    @Id
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}

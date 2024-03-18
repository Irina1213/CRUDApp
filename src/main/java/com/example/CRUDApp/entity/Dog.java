package com.example.CRUDApp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="dogs")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int age;
    private String breed;

}

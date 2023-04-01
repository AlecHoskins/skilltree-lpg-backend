package com.alechoskins.skilltreelpgbackend.database.pojos;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "role")
public class Role {

    //region PROPERTIES

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    //endregion

    //region RELATIONSHIPS

    @ManyToMany(mappedBy = "roles")
    List<User> users;

    //endregion
}
package com.bookstore.library.api.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "authors")
public class AuthorsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
    @SequenceGenerator(name = "author_id_seq ", sequenceName = "author_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private Integer age;

}
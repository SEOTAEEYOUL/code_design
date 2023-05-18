package com.code.design.lombok;


import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, updatable = false, unique = true)
    private String email;

    @Column(name = "name", nullable = false)
    private String name;

    @CreationTimestamp
    @Column(name = "creat_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Builder
    public Student(final String email, final String name) {
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString( ) {
        return "Student{" +
                "id=" + id +
                ", email = '" + email + '\'' +
                ", name = '" + name + '\'' +
                ", createAt = '" + createAt + '\'' +
                ", updateAt = '" + updateAt + '\'' +
                '}';
    }
}

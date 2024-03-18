package kz.learn.todolist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    private String username;
    private String authority;
}

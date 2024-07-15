package org.ismetg.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "first_name" , length = 50)
    String firstname;
    @Column(name = "last_name" , length = 50)
    String lastname;
    @Column(length = 100)
    @Email
    String email;
    @Column(length = 50)
    String password;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    List<Post> posts;

    @OneToMany(mappedBy = "user")
    List<Like> likes;

    @OneToMany(mappedBy = "user")
    List<Comment> comments;


}

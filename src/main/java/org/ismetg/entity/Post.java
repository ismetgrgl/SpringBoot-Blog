package org.ismetg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String content;
    @Column(name = "published_at")
    @CreationTimestamp
    LocalDate publishedat;
    @ManyToOne
    User user;

    @ManyToMany()
    @JoinTable(name = "posts_categories",
            joinColumns = @JoinColumn(name = "postid"),
            inverseJoinColumns = @JoinColumn(name = "categoryid")
    )
    List<Category> categories;

    @OneToMany(mappedBy = "post")
    List<Like> likes;

    @OneToMany(mappedBy = "post")
    List<Comment> comments;
}

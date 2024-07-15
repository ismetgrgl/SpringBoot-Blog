package org.ismetg.repository;

import org.ismetg.entity.Like;
import org.ismetg.entity.Post;
import org.ismetg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like , Long> {
    Like findByUserAndPost(User user, Post post);
}

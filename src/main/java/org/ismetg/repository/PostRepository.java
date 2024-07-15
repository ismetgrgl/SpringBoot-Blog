package org.ismetg.repository;

import org.ismetg.dto.response.PostFindAllResponseDto;
import org.ismetg.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post , Long> {

    List<Post> findByOrderByPublishedatDesc();

}

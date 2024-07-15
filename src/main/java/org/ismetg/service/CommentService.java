package org.ismetg.service;

import org.ismetg.entity.Comment;
import org.ismetg.entity.Like;
import org.ismetg.entity.Post;
import org.ismetg.entity.User;
import org.ismetg.exception.BlogException;
import org.ismetg.exception.ErrorType;
import org.ismetg.repository.CommentRepository;
import org.ismetg.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService extends ServiceManager<Comment,Long> {
    public final CommentRepository commentRepository;
    public final UserService userService;
    public final PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        super(commentRepository);
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public void saveComment(Long userId , Long postId , String content){
        User user = userService.userFindByIdPrivate(userId);
        Post post = postService.findByIdPost(postId);

        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(content)
                .build();
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isEmpty()){
            throw new BlogException(ErrorType.COMMENT_NOT_FOUND);
        }
        commentRepository.delete(comment.get());
    }

    public void updateComment(Long commentId, String content){
        Optional<Comment> comment = findById(commentId);
        if (comment.isEmpty()){
            throw new BlogException(ErrorType.COMMENT_NOT_FOUND);
        }
        if (content != null && !content.isEmpty()){
            comment.get().setContent(content);
        }
        commentRepository.save(comment.get());
    }


    public String commentFindById(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (!comment.isPresent()){
            throw new BlogException(ErrorType.COMMENT_NOT_FOUND);
        }
        Long userId = comment.get().getUser().getId();
        Long postId = comment.get().getPost().getId();
        return "User id: "+userId +  "\nPost id: " + postId +  "\ncontent: " + comment.get().getContent();
    }
}

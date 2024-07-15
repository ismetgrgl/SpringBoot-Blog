package org.ismetg.service;

import org.ismetg.entity.Like;
import org.ismetg.entity.Post;
import org.ismetg.entity.User;
import org.ismetg.exception.BlogException;
import org.ismetg.exception.ErrorType;
import org.ismetg.repository.LikeRepository;
import org.ismetg.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService extends ServiceManager<Like, Long> {
    private final LikeRepository likeRepository;
    private final UserService userService;
    private final PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        super(likeRepository);
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    /**
     * gelen userId ve postId var mı diye kontrol ediliyor.
     * Sonra o user aynı postu beğenmiş mi diye kontrol ediyor. kullanıcı var post var ve beğenmemişse kaydediyor.
     * @param userId beğenen userın id'si
     * @param postId beğenilen postun id'si
     */
    public void saveLike(Long userId , Long postId){
        User user = userService.userFindByIdPrivate(userId);
        Post post = postService.findByIdPost(postId);

        Like likeControl = likeRepository.findByUserAndPost(user, post);
        if ((likeControl != null)) {
            throw new BlogException(ErrorType.LIKED_BEFORE);
        }

        Like like = Like.builder()
                .user(user)
                .post(post)
                .build();
        likeRepository.save(like);
    }

    /**
     * like kontrol ediliyor yoksa hata fırlatıyor varsa siliniyor.
     * @param likeId silinmesi istenen like id'si
     */
    public void deleteLike(Long likeId) {
        Optional<Like> like = likeRepository.findById(likeId);
        if (like.isEmpty()){
            throw new BlogException(ErrorType.LIKE_NOT_FOUND);
        }
        likeRepository.delete(like.get());
    }
}

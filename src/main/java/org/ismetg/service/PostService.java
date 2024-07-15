package org.ismetg.service;


import org.ismetg.dto.request.PostSaveRequestDto;
import org.ismetg.dto.request.PostUpdateRequestDto;
import org.ismetg.dto.response.PostFindAllResponseDto;
import org.ismetg.dto.response.UserFindAllResponseDto;
import org.ismetg.entity.*;
import org.ismetg.exception.BlogException;
import org.ismetg.exception.ErrorType;
import org.ismetg.mapper.PostMapper;
import org.ismetg.mapper.UserMapper;
import org.ismetg.repository.PostRepository;
import org.ismetg.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService extends ServiceManager<Post , Long> {
    private final PostRepository postRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    public PostService(PostRepository postRepository, UserService userService, CategoryService categoryService) {
        super(postRepository);
        this.postRepository = postRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }
    public void savePost(PostSaveRequestDto dto){
        User user = userService.userFindByIdPrivate(dto.userid());
        List<Category> categories = categoryService.findAllByIds(dto.categoryids());
        Post post = Post.builder()
                .title(dto.title())
                .content(dto.content())
                .user(user)
                .categories(categories)
                .build();
        postRepository.save(post);
    }

    /**
     * burada postu atan kullanıcının adı ile kategorilerin adını döndürdüm idleri bilerek döndürmedim.
     * @return
     */
    public List<PostFindAllResponseDto> findAllPosts(){
        List<PostFindAllResponseDto> responseDtoList = new ArrayList<>();

        findAll().forEach(dto -> responseDtoList.add(PostMapper.INSTANCE.postToPostFindAllResponseDto(dto)));
        return responseDtoList;
    }

    public PostFindAllResponseDto postFindByResponseDto(Long postId){
        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()){
            throw new BlogException(ErrorType.POST_NOT_FOUND);
        }
        PostFindAllResponseDto responseDto = PostMapper.INSTANCE.postToPostFindAllResponseDto(post.get());
        return responseDto;
    }

    public Post findByIdPost(Long postId){
        Optional<Post> post = postRepository.findById(postId);
        if (!post.isPresent()){
            throw new BlogException(ErrorType.POST_NOT_FOUND);
        }
        return post.get();
    }

    public void updatePost(Long postId , PostUpdateRequestDto dto){
        Post post = findByIdPost(postId);
        if (dto.title() != null && !dto.title().isEmpty()){
            post.setTitle(dto.title());
        }
        if (dto.content() != null && !dto.content().isEmpty()){
            post.setContent(dto.content());
        }
        postRepository.save(post);
    }
    public void deletePost(Long postId){
        Post post = findByIdPost(postId);

        postRepository.delete(post);
    }

    /**
     * Daha anlaşılır olması için direkt nesne çağırmıyorum onun yerine dto kullanıyorum hepsinde
     * @param userId postlarının gelmesi istenen userın idsi
     * @return kullanıcının postlarını liste halinde getiriyor.
     */
    public List<PostFindAllResponseDto> findUserPosts(Long userId){
        User user = userService.userFindByIdPrivate(userId);
        List<Post> posts = user.getPosts();
        return postToPostFindAllResponseDto(posts);
    }

    /**
     * istenen kategorideki postları çekmek için idsi verilen kategoriyi bulup sonrasında kategori içerisinde bulunan postsları getirip dtoya çeviriyorum.
     * @param categoryId içindeki postların getirilmesi istenen kategorinin idsi
     * @return girilen kategoriye göre postlar
     */
    public List<PostFindAllResponseDto> findByCategoryInPosts(Long categoryId) {
        Category category = categoryService.categoryFindByIdCategory(categoryId);
        List<Post> posts = category.getPosts();
        return postToPostFindAllResponseDto(posts);
    }

    /**
     * postun atıldığı tarih otomatik olarak çevrilip tutuluyor. o tarihe göre sıralamak için repositorydeki hazır sorguyu kullanıp dtoya çevirip controller katmanında kullanılıyor.
     * @return tüm postların tarihe göre sıralı halde listesi
     */
    public List<PostFindAllResponseDto> findByOrderByPublishedAtDesc(){
        List<Post> posts = postRepository.findByOrderByPublishedatDesc();
        return postToPostFindAllResponseDto(posts);
    }


    /**
     * post listesini dto post listesi hale çeviriyor birçok yerde kullandığım için bu hale getirdim.
     * @param posts dışardan gelen post listesi
     * @return PostFindAllResponseDto listesi döndürüyor.
     */
    private List<PostFindAllResponseDto> postToPostFindAllResponseDto(List<Post> posts){
        List<PostFindAllResponseDto> responseDtoList = new ArrayList<>();
        for (Post post : posts){
            assert false;
            responseDtoList.add(PostMapper.INSTANCE.postToPostFindAllResponseDto(post));
        }
        return responseDtoList;
    }

    /**
     * postu beğenenen kullanıcıları bulan metod.
     * @param postId
     * @return UserFindAllResponseDto listesi dönüyor.
     */
    public List<UserFindAllResponseDto> findUsersLikedPost(Long postId){
        Post post = findByIdPost(postId);
        List<UserFindAllResponseDto> responseDtoList = new ArrayList<>();
        List<Like> likes = post.getLikes();
        for (Like like : likes){
            responseDtoList.add(UserMapper.INSTANCE.userToUserFindAllResponseDto(like.getUser()));
        }
        return responseDtoList;
    }

    /**
     * posta yorum atan kullanıcıları bulan metod
     * @param postId
     * @return UserFindAllResponseDto listesi dönüyor.
     */
    public List<UserFindAllResponseDto> findPostCommentedUsers(Long postId){
        Post post = findByIdPost(postId);
        List<UserFindAllResponseDto> responseDtoList = new ArrayList<>();
        List<Comment> comments = post.getComments();
        for (Comment comment : comments){
            responseDtoList.add(UserMapper.INSTANCE.userToUserFindAllResponseDto(comment.getUser()));
        }
        return responseDtoList;
    }

}

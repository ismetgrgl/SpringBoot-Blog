package org.ismetg.service;

import jakarta.validation.constraints.Email;
import org.ismetg.dto.request.UserSaveRequestDto;
import org.ismetg.dto.response.PostFindAllResponseDto;
import org.ismetg.dto.response.UserFindAllResponseDto;
import org.ismetg.entity.Comment;
import org.ismetg.entity.Like;
import org.ismetg.entity.Post;
import org.ismetg.entity.User;
import org.ismetg.exception.BlogException;
import org.ismetg.exception.ErrorType;
import org.ismetg.mapper.PostMapper;
import org.ismetg.mapper.UserMapper;
import org.ismetg.repository.UserRepository;
import org.ismetg.utility.ServiceManager;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.ismetg.constant.Constants.EMAIL_PATTERN;

@Service
public class UserService extends ServiceManager<User , Long> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    /**
     * @validation için uğraştım ama olmadı bir şey kaçıyor o yüzden regex ve pattern kullanarak email kontrolü yaptım
     * @param dto
     */
    public void userRegister(UserSaveRequestDto dto){
        if (!isValidEmail(dto.email())) {
            throw new BlogException(ErrorType.EMAIL_NOT_VALIDATE);
        }
        userRepository.save(UserMapper.INSTANCE.userSaveRequestDtoToUser(dto));
    }
    private boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public List<UserFindAllResponseDto> findAllUsers(){
        List<UserFindAllResponseDto> responseDtoList = new ArrayList<>();
        findAll().forEach(user -> {
            responseDtoList.add(UserMapper.INSTANCE.userToUserFindAllResponseDto(user));
        });
        return responseDtoList;
    }

    /**
     * kullanıcının görmesi için password gizlenmiş halde oluşturulmuş dtoyu döndürür. Yoksa hata fırlatır.
     * @param userId dışardan girilen user id si
     * @return girilen id ile bulunan User
     */
    public UserFindAllResponseDto userFindByIdResponseDto(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()){
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
        UserFindAllResponseDto responseDto = UserMapper.INSTANCE.userToUserFindAllResponseDto(user.get());
        return responseDto;
    }

    /**
     * girilen idye göre varsa user döndürür. Yoksa hata fırlatır.
     * @param userId dışarıdan gelen user idsi
     * @return girilen id ile bulunan User
     */
    public User userFindByIdPrivate(Long userId){
         Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()){
            throw new BlogException(ErrorType.USER_NOT_FOUND);
        }
        return user.get();
    }

    /**
     * burada kullanıcıdan alınan değerler yeni olarak güncelleniyor eğer kullanıcı sadece birini değiştirip diğerlerini boş bırakırsa değişmiyor.
     * @param userId güncellenmesi istenen userın Id si
     */
    public void updateUser(Long userId , UserSaveRequestDto dto){
        User user = userFindByIdPrivate(userId);

        if (dto.firstname()!= null && !dto.firstname().isEmpty()){
            user.setFirstname(dto.firstname());
        }
        if (dto.lastname() != null && !dto.lastname().isEmpty()){
            user.setLastname(dto.lastname());
        }
        if (dto.email() != null && !dto.email().isEmpty()){
            if (!isValidEmail(dto.email())) {
                throw new BlogException(ErrorType.EMAIL_NOT_VALIDATE);
            }
            user.setEmail(dto.email());
        }
        if (dto.password() != null && !dto.password().isEmpty()){
            user.setPassword(dto.password());
        }
            userRepository.save(user);
    }

    /**
     * burada silme işlemi gerçekleştirirken bilerek postları silmesinide sağladım user olmadan postun durmasının anlamı yok diye düşündüm.
     * @param userId silinmesi istenen userın idsi
     */
    public void deleteUser(Long userId){
        User user = userFindByIdPrivate(userId);
        userRepository.delete(user);
    }

    /**
     * userın beğendiği postları dto halinde getiriyor.
     * @param userId beğenileri görüntülenmek istenen userın Id'si
     * @return PostFindAllResponseDto listesi dönüyor.
     */
    public List<PostFindAllResponseDto> findUserLikedPosts(Long userId){
        User user = userFindByIdPrivate(userId);
        List<PostFindAllResponseDto> responseDtoList = new ArrayList<>();
        List<Like> likes = user.getLikes();
        for (Like like : likes){
            assert false;
            responseDtoList.add(PostMapper.INSTANCE.postToPostFindAllResponseDto(like.getPost()));
        }
        return responseDtoList;
    }

    public List<PostFindAllResponseDto> findUserCommentedPosts(Long userId){
        User user = userFindByIdPrivate(userId);
        List<PostFindAllResponseDto> responseDtoList = new ArrayList<>();
        List<Comment> comments = user.getComments();
        for (Comment comment : comments){
            assert false;
            responseDtoList.add(PostMapper.INSTANCE.postToPostFindAllResponseDto(comment.getPost()));
        }
        return responseDtoList;
    }

}

package org.ismetg.controller;

import lombok.RequiredArgsConstructor;
import org.ismetg.dto.request.CategorySaveRequestDto;
import org.ismetg.dto.request.PostSaveRequestDto;
import org.ismetg.dto.request.PostUpdateRequestDto;
import org.ismetg.dto.response.PostFindAllResponseDto;
import org.ismetg.dto.response.UserFindAllResponseDto;
import org.ismetg.entity.Post;
import org.ismetg.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.ismetg.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT+POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping(SAVE)
    public ResponseEntity<String> savePost(@RequestBody PostSaveRequestDto dto){
        postService.savePost(dto);
        return ResponseEntity.ok("Post Kayıt İşlemi başarılı");
    }
    @GetMapping(FIND_ALL)
    public ResponseEntity<List<PostFindAllResponseDto>> findAll(){
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @GetMapping(FIND_BY_ID + POSTID)
    public ResponseEntity<PostFindAllResponseDto> postFindById(@PathVariable Long postId){
        return ResponseEntity.ok(postService.postFindByResponseDto(postId));
    }

    @PutMapping(UPDATE + POSTID)
    public ResponseEntity<String> updatePost(@RequestBody PostUpdateRequestDto dto , @PathVariable Long postId ){
        postService.updatePost(postId , dto);
        return ResponseEntity.ok("Güncelleme işlemi başarılı.");
    }

    @DeleteMapping(DELETE + POSTID)
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return ResponseEntity.ok("Silme işlemi başarılı.");
    }

    @GetMapping("user" +USERID)
    public ResponseEntity<List<PostFindAllResponseDto>> findUserPosts(@PathVariable Long userId){
        return ResponseEntity.ok(postService.findUserPosts(userId));
    }

    @GetMapping("category" + CATEGORYID)
    public ResponseEntity<List<PostFindAllResponseDto>> findByCategoryInPosts(@PathVariable Long categoryId){
        return ResponseEntity.ok(postService.findByCategoryInPosts(categoryId));
    }

    @GetMapping("findByOrderByPublishedAtDesc")
    public ResponseEntity<List<PostFindAllResponseDto>> findByOrderByPublishedAtDesc(){
        return ResponseEntity.ok(postService.findByOrderByPublishedAtDesc());
    }

    @GetMapping("findUsersLikedPost"+POSTID)
    public ResponseEntity<List<UserFindAllResponseDto>> findUsersLikedPost(@PathVariable Long postId){
        return ResponseEntity.ok((postService.findUsersLikedPost(postId)));
    }

    @GetMapping("findPostCommentedUsers"+POSTID)
    public ResponseEntity<List<UserFindAllResponseDto>> findPostCommentedUsers(@PathVariable Long postId){
        return ResponseEntity.ok((postService.findPostCommentedUsers(postId)));
    }


}

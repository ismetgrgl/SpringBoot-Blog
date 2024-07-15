package org.ismetg.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ismetg.dto.request.UserSaveRequestDto;

import org.ismetg.dto.response.PostFindAllResponseDto;
import org.ismetg.dto.response.UserFindAllResponseDto;
import org.ismetg.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.ismetg.constant.EndPoints.*;



@RequestMapping(ROOT+USER)
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping(SAVE)
    public ResponseEntity<String> userRegister(@Valid @RequestBody UserSaveRequestDto dto ) {
            userService.userRegister(dto);
            return ResponseEntity.ok("Kayıt işlemi başarılı.");
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<UserFindAllResponseDto>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping(FIND_BY_ID + USERID)
    public ResponseEntity<UserFindAllResponseDto> userFindById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.userFindByIdResponseDto(userId));
    }

    @PutMapping(UPDATE + USERID)
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserSaveRequestDto dto , @PathVariable Long userId ){
        userService.updateUser(userId , dto);
        return ResponseEntity.ok("Güncelleme işlemi başarılı.");
    }

    @DeleteMapping(DELETE + USERID)
    public ResponseEntity<String> deleteUser(Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("Silme işlemi başarılı.");
    }

    @GetMapping("findUserLikedPosts"+USERID)
    public ResponseEntity<List<PostFindAllResponseDto>> findUserLikedPosts(@PathVariable Long userId){
        return ResponseEntity.ok(userService.findUserLikedPosts(userId));
    }

    @GetMapping("findUserCommentedPosts"+USERID)
    public ResponseEntity<List<PostFindAllResponseDto>> findUserCommentedPosts(@PathVariable Long userId){
        return ResponseEntity.ok(userService.findUserCommentedPosts(userId));
    }

}

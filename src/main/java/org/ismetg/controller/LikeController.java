package org.ismetg.controller;

import lombok.RequiredArgsConstructor;
import org.ismetg.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.ismetg.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT+LIKE)
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping(SAVE+USERID+POSTID)
    public ResponseEntity<String> saveLike(@PathVariable  Long userId, @PathVariable Long postId){
        likeService.saveLike(userId , postId);
        return ResponseEntity.ok("beğenme işlemi başarılı");
    }
    @DeleteMapping(DELETE + LIKEID)
    public ResponseEntity<String> deleteLike(@PathVariable Long likeId){
        likeService.deleteLike(likeId);
        return ResponseEntity.ok("Silme işlemi başarılı.");
    }
}

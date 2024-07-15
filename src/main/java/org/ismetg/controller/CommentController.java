package org.ismetg.controller;

import lombok.RequiredArgsConstructor;
import org.ismetg.dto.request.PostUpdateRequestDto;
import org.ismetg.dto.response.UserFindAllResponseDto;
import org.ismetg.entity.Comment;
import org.ismetg.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.ismetg.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT+COMMENT)
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping(SAVE+USERID + POSTID)
    public ResponseEntity<String> saveComment(@PathVariable Long userId , @PathVariable Long postId , String content){
        commentService.saveComment(userId , postId , content);
        return ResponseEntity.ok("yorum atma işlemi başarılı");
    }

    @DeleteMapping(DELETE + COMMENTID)
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Silme işlemi başarılı.");
    }

    @GetMapping(FIND_BY_ID + COMMENTID)
    public ResponseEntity<String> commentFindById(@PathVariable Long commentId){
        return ResponseEntity.ok(commentService.commentFindById(commentId));
    }

    @PutMapping(UPDATE + COMMENTID)
    public ResponseEntity<String> updateComment(@PathVariable Long commentId , String content){
        commentService.updateComment(commentId , content);
        return ResponseEntity.ok("Güncelleme işlemi başarılı.");
    }


}

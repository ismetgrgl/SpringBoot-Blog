package org.ismetg.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ismetg.dto.request.CategorySaveRequestDto;
import org.ismetg.dto.request.UserSaveRequestDto;
import org.ismetg.dto.response.CategoryFindAllResponseDto;
import org.ismetg.dto.response.UserFindAllResponseDto;
import org.ismetg.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.ismetg.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT+CATEGORY)
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(SAVE)
    public ResponseEntity<String> addCategory(@RequestBody CategorySaveRequestDto dto){
        categoryService.addCategory(dto);
        return ResponseEntity.ok("Kategori ekleme işlemi başarılı");
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<CategoryFindAllResponseDto>> findAllCategories(){
        return ResponseEntity.ok(categoryService.findAllCategories());
    }

    @GetMapping(FIND_BY_ID + CATEGORYID)
    public ResponseEntity<CategoryFindAllResponseDto> categoryFindById(@PathVariable Long categoryId){
        return ResponseEntity.ok(categoryService.categoryFindById(categoryId));
    }

    @PutMapping(UPDATE + CATEGORYID)
    public ResponseEntity<String> updateCategory(@RequestBody CategorySaveRequestDto dto , @PathVariable Long categoryId ){
        categoryService.updateCategory(categoryId , dto);
        return ResponseEntity.ok("Güncelleme işlemi başarılı.");
    }

    @DeleteMapping(DELETE + CATEGORYID)
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Silme işlemi başarılı.");
    }

    @GetMapping("findbyname")
    public ResponseEntity<CategoryFindAllResponseDto> findByNameIgnoreCase(@RequestParam(name = "search") String name){
        return ResponseEntity.ok(categoryService.findByNameIgnoreCase(name));
    }


}

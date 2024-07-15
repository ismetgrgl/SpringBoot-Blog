package org.ismetg.service;

import org.ismetg.dto.request.CategorySaveRequestDto;
import org.ismetg.dto.response.CategoryFindAllResponseDto;
import org.ismetg.dto.response.UserFindAllResponseDto;
import org.ismetg.entity.Category;
import org.ismetg.entity.Post;
import org.ismetg.entity.User;
import org.ismetg.exception.BlogException;
import org.ismetg.exception.ErrorType;
import org.ismetg.mapper.CategoryMapper;
import org.ismetg.mapper.UserMapper;
import org.ismetg.repository.CategoryRepository;
import org.ismetg.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService extends ServiceManager<Category, Long> {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    public void addCategory(CategorySaveRequestDto dto) {
        categoryRepository.save(CategoryMapper.INSTANCE.categorySaveRequestDtoToCategory(dto));
    }

    /**
     * past kayıt işleminde kategorilerin idlerini bulmak için
     *
     * @param ids post kaydederken gelen idler
     * @return kategoriler varsa kategorileri liste halinde döndürüyor. Yoksa hata döndürüyor.
     */
    public List<Category> findAllByIds(List<Long> ids) {
        return ids.stream().map(id -> findById(id).orElseThrow(() -> new BlogException(ErrorType.CATEGORY_NOT_FOUND))).collect(Collectors.toList());
    }

    public List<CategoryFindAllResponseDto> findAllCategories() {
        List<CategoryFindAllResponseDto> responseDtoList = new ArrayList<>();
        findAll().forEach(category -> {
            responseDtoList.add(CategoryMapper.INSTANCE.categoryToCategoryFindAllResponseDto(category));
        });
        return responseDtoList;
    }

    public CategoryFindAllResponseDto categoryFindById(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if (!category.isPresent()) {
            throw new BlogException(ErrorType.CATEGORY_NOT_FOUND);
        }
        return CategoryMapper.INSTANCE.categoryToCategoryFindAllResponseDto(category.get());
    }

    public Category categoryFindByIdCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (!category.isPresent()) {
            throw new BlogException(ErrorType.CATEGORY_NOT_FOUND);
        }
        return category.get();
    }

    public void updateCategory(Long categoryId, CategorySaveRequestDto dto) {
        Category category = categoryFindByIdCategory(categoryId);

        if (dto.name() != null && !dto.name().isEmpty()) {
            category.setName(dto.name());
        }
        if (dto.description() != null && !dto.description().isEmpty()) {
            category.setDescription(dto.description());
        }
        categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        Category category = categoryFindByIdCategory(categoryId);

        for (Post post : category.getPosts()) {
            post.getCategories().remove(category);
        }
        categoryRepository.delete(category);
    }

    /**
     * CategoryRepository'den gelen findByNameIgnoreCase hazır sorgusundan gelen kategorinin Idsini alıp categoryFindById metoduna
     * gönderiyorum orda kategori var ise dönüyor yoksa hata veriyor.
     * @return CategoryFindAllResponseDto döndürüyor.
     */
    public CategoryFindAllResponseDto findByNameIgnoreCase(String name) {
        Optional<Category> category = categoryRepository.findBynameIgnoreCase(name);
        if (!category.isPresent()) {
            throw new BlogException(ErrorType.CATEGORY_NOT_FOUND2);
        }
        return categoryFindById(category.get().getId());
    }
}

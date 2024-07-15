package org.ismetg.mapper;

import org.ismetg.dto.request.CategorySaveRequestDto;
import org.ismetg.dto.response.CategoryFindAllResponseDto;
import org.ismetg.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categorySaveRequestDtoToCategory(CategorySaveRequestDto dto);

    CategoryFindAllResponseDto categoryToCategoryFindAllResponseDto(Category category);

}

package org.ismetg.mapper;

import org.ismetg.dto.request.PostSaveRequestDto;
import org.ismetg.dto.response.PostFindAllResponseDto;
import org.ismetg.entity.Category;
import org.ismetg.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post postSaveRequestDtoToPost(PostSaveRequestDto dto);

    @Mapping(target = "username", source = "post.user.firstname")
    @Mapping(target = "categoriesname",expression = "java(getCategoriesName(post))")

    PostFindAllResponseDto postToPostFindAllResponseDto(Post post);

    default List<String> getCategoriesName(Post post) {
        List<String> categoriesName = new ArrayList<>();
        for (Category category : post.getCategories()) {
            categoriesName.add(category.getName());
        }
        return categoriesName;
    }
}





package org.ismetg.mapper;

import org.ismetg.dto.request.UserSaveRequestDto;
import org.ismetg.dto.response.UserFindAllResponseDto;
import org.ismetg.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User userSaveRequestDtoToUser(UserSaveRequestDto dto);
    UserFindAllResponseDto userToUserFindAllResponseDto(User user);
}

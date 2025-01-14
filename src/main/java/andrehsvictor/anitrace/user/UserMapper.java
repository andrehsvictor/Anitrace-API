package andrehsvictor.anitrace.user;

import org.mapstruct.Mapper;

import andrehsvictor.anitrace.user.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

}

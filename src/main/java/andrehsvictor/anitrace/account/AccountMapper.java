package andrehsvictor.anitrace.account;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import andrehsvictor.anitrace.account.dto.AccountDto;
import andrehsvictor.anitrace.account.dto.CreateAccountDto;
import andrehsvictor.anitrace.account.dto.EditAccountDto;
import andrehsvictor.anitrace.user.User;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    User createAccountDtoToUser(CreateAccountDto createAccountDto);

    AccountDto userToAccountDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User updateUserFromEditAccountDto(EditAccountDto editAccountDto, @MappingTarget User user);

    @AfterMapping
    default void afterUpdateUserFromEditAccountDto(EditAccountDto editAccountDto, @MappingTarget User user) {
        if (editAccountDto.getAvatarUrl() != null && editAccountDto.getAvatarUrl().isEmpty()) {
            user.setAvatarUrl(null);
        }
        if (editAccountDto.getBio() != null && editAccountDto.getBio().isEmpty()) {
            user.setBio(null);
        }
    }

}

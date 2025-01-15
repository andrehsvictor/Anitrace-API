package andrehsvictor.anitrace.list;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import andrehsvictor.anitrace.list.dto.CreateListDto;
import andrehsvictor.anitrace.list.dto.EditListDto;
import andrehsvictor.anitrace.list.dto.ListDto;

@Mapper(componentModel = "spring")
public interface ListMapper {

    AnitraceList createListDtoToList(CreateListDto createListDto);

    ListDto listToListDto(AnitraceList list);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AnitraceList updateListFromEditListDto(EditListDto editListDto, @MappingTarget AnitraceList list);

    @AfterMapping
    default void afterMapping(EditListDto editListDto, @MappingTarget AnitraceList list) {
        if (editListDto.getDescription() != null && editListDto.getDescription().isBlank()) {
            list.setDescription(null);
        }
    }

}

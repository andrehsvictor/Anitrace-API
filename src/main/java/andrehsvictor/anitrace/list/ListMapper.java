package andrehsvictor.anitrace.list;

import org.mapstruct.Mapper;

import andrehsvictor.anitrace.list.dto.ListDto;

@Mapper(componentModel = "spring")
public interface ListMapper {

    ListDto listToListDto(AnitraceList list);
    
}

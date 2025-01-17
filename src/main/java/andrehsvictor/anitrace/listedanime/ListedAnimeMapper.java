package andrehsvictor.anitrace.listedanime;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import andrehsvictor.anitrace.anime.AnimeService;
import andrehsvictor.anitrace.list.ListMapper;
import andrehsvictor.anitrace.listedanime.dto.EditListedAnimeDto;
import andrehsvictor.anitrace.listedanime.dto.ListAnimeDto;
import andrehsvictor.anitrace.listedanime.dto.ListedAnimeDto;

@Mapper(componentModel = "spring", uses = { ListMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ListedAnimeMapper {

    @Autowired
    protected AnimeService animeService;

    @Mapping(target = "anime", expression = "java(animeService.getById(listedAnime.getAnimeId()))")
    public abstract ListedAnimeDto listedAnimeToListedAnimeDto(ListedAnime listedAnime);

    public abstract ListedAnime listAnimeDtoToListedAnime(ListAnimeDto listAnimeDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract ListedAnime updateListedAnimeFromEditListedAnimeDto(EditListedAnimeDto editListedAnimeDto,
            @MappingTarget ListedAnime listedAnime);

    @AfterMapping
    protected void afterMapping(EditListedAnimeDto editListedAnimeDto, @MappingTarget ListedAnime listedAnime) {
        if (editListedAnimeDto.getScore() != null && editListedAnimeDto.getScore().toString().isBlank()) {
            listedAnime.setScore(null);
        }
    }

}

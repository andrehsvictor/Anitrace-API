package andrehsvictor.anitrace.listedanime;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import andrehsvictor.anitrace.anime.AnimeService;
import andrehsvictor.anitrace.list.ListMapper;
import andrehsvictor.anitrace.listedanime.dto.ListAnimeDto;
import andrehsvictor.anitrace.listedanime.dto.ListedAnimeDto;

@Mapper(componentModel = "spring", uses = { ListMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ListedAnimeMapper {

    @Autowired
    protected AnimeService animeService;

    @Mapping(target = "anime", expression = "java(animeService.getById(listedAnime.getAnimeId()))")
    public abstract ListedAnimeDto listedAnimeToListedAnimeDto(ListedAnime listedAnime);

    public abstract ListedAnime listAnimeDtoToListedAnime(ListAnimeDto listAnimeDto);

}

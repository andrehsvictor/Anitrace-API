package andrehsvictor.anitrace.jikan;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import andrehsvictor.anitrace.anime.dto.AnimeDto;
import net.sandrohc.jikan.model.GenreEntity;
import net.sandrohc.jikan.model.anime.Anime;

@Mapper(componentModel = "spring")
public interface JikanAnimeMapper {

    @Mapping(target = "averageEpisodeDuration", source = "duration")
    @Mapping(target = "imageUrl", source = "images.jpg.imageUrl")
    @Mapping(target = "trailerUrl", source = "trailer.url")
    AnimeDto animeToAnimeDto(Anime anime);

    default List<String> genreEntityCollectionToStringList(Collection<GenreEntity> genres) {
        return genres.stream().map(genre -> genre.getName().displayName()).toList();
    }
}

package andrehsvictor.anitrace.jikan;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.anime.AnimeService;
import andrehsvictor.anitrace.anime.dto.AnimeDto;
import andrehsvictor.anitrace.exception.BadRequestException;
import andrehsvictor.anitrace.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;
import net.sandrohc.jikan.model.anime.AnimeOrderBy;
import net.sandrohc.jikan.model.enums.SortOrder;

@Service
@RequiredArgsConstructor
public class JikanAnimeService implements AnimeService {

    private final Jikan jikan;
    private final JikanAnimeMapper mapper;

    @Override
    public Page<AnimeDto> getAll(Pageable pageable) {
        AnimeOrderBy animeOrderBy = AnimeOrderBy.POPULARITY;
        SortOrder sortOrder = SortOrder.DESCENDING;
        if (pageable.getSort().isSorted()) {
            for (AnimeOrderBy orderBy : AnimeOrderBy.values()) {
                if (orderBy.name().equalsIgnoreCase(pageable.getSort().toList().get(0).getProperty())) {
                    animeOrderBy = orderBy;
                    break;
                }
            }
            sortOrder = pageable.getSort().toList().get(0).isAscending() ? SortOrder.ASCENDING : SortOrder.DESCENDING;
            if (animeOrderBy == null || sortOrder == null) {
                throw new BadRequestException("Invalid sort property");
            }
        } else {
            animeOrderBy = AnimeOrderBy.POPULARITY;
            sortOrder = SortOrder.DESCENDING;
        }
        try {
            Collection<Anime> animes = jikan.query()
                    .anime()
                    .search()
                    .page(pageable.getPageNumber() + 1)
                    .limit(pageable.getPageSize())
                    .orderBy(animeOrderBy, sortOrder)
                    .execute()
                    .collectList()
                    .block();
            Long total = jikan.query()
                    .anime()
                    .search()
                    .execute()
                    .count()
                    .block();
            List<AnimeDto> animeDtos = animes.stream().map(mapper::animeToAnimeDto).toList();
            Page<AnimeDto> animesPage = new PageImpl<>(animeDtos, pageable, total);
            return animesPage;
        } catch (JikanQueryException e) {
            throw new RuntimeException("Failed to fetch animes", e);
        }
    }

    @Override
    public AnimeDto getById(Integer id) {
        try {
            Anime anime = jikan.query()
                    .anime()
                    .get(id)
                    .execute()
                    .block();
            if (anime == null) {
                throw new ResourceNotFoundException(Anime.class, "MyAnimeList ID", id);
            }
            return mapper.animeToAnimeDto(anime);
        } catch (JikanQueryException e) {
            throw new RuntimeException("Failed to fetch anime", e);
        }
    }

}

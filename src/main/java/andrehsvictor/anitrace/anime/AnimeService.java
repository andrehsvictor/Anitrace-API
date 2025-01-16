package andrehsvictor.anitrace.anime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import andrehsvictor.anitrace.anime.dto.AnimeDto;

public interface AnimeService {
    Page<AnimeDto> getAll(Pageable pageable);

    AnimeDto getById(Integer animeId);
}

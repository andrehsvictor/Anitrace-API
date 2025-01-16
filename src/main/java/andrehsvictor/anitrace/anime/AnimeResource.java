package andrehsvictor.anitrace.anime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.anitrace.anime.dto.AnimeDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AnimeResource {

    private final AnimeService animeService;

    @GetMapping("/api/v1/animes")
    public Page<AnimeDto> getAll(Pageable pageable) {
        return animeService.getAll(pageable);
    }
}

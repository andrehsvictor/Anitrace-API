package andrehsvictor.anitrace.listedanime;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.anime.AnimeService;
import andrehsvictor.anitrace.list.AnitraceList;
import andrehsvictor.anitrace.list.ListService;
import andrehsvictor.anitrace.listedanime.dto.ListAnimeDto;
import andrehsvictor.anitrace.listedanime.dto.ListedAnimeDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListedAnimeService {

    private final ListedAnimeRepository listedAnimeRepository;
    private final ListedAnimeMapper listedAnimeMapper;
    private final ListService listService;
    private final AnimeService animeService;

    public ListedAnimeDto toDto(ListedAnime listedAnime) {
        return listedAnimeMapper.listedAnimeToListedAnimeDto(listedAnime);
    }

    public Page<ListedAnimeDto> toDto(Page<ListedAnime> listedAnimePage) {
        return listedAnimePage.map(this::toDto);
    }

    public ListedAnime list(UUID listId, ListAnimeDto listAnimeDto) {
        animeService.getById(listAnimeDto.getAnimeId());
        AnitraceList list = listService.getById(listId);
        ListedAnime listedAnime = listedAnimeMapper.listAnimeDtoToListedAnime(listAnimeDto);
        listedAnime.setList(list);
        return listedAnimeRepository.save(listedAnime);
    }

}

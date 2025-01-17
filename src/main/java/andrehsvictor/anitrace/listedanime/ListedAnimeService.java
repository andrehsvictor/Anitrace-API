package andrehsvictor.anitrace.listedanime;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.anime.AnimeService;
import andrehsvictor.anitrace.anime.dto.AnimeDto;
import andrehsvictor.anitrace.exception.BadRequestException;
import andrehsvictor.anitrace.exception.ForbiddenOperationException;
import andrehsvictor.anitrace.exception.ResourceNotFoundException;
import andrehsvictor.anitrace.list.AnitraceList;
import andrehsvictor.anitrace.list.ListService;
import andrehsvictor.anitrace.list.ListVisibility;
import andrehsvictor.anitrace.listedanime.dto.EditListedAnimeDto;
import andrehsvictor.anitrace.listedanime.dto.ListAnimeDto;
import andrehsvictor.anitrace.listedanime.dto.ListedAnimeDto;
import andrehsvictor.anitrace.user.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListedAnimeService {

    private final ListedAnimeRepository listedAnimeRepository;
    private final ListedAnimeMapper listedAnimeMapper;
    private final ListService listService;
    private final AnimeService animeService;
    private final UserService userService;

    public ListedAnimeDto toDto(ListedAnime listedAnime) {
        return listedAnimeMapper.listedAnimeToListedAnimeDto(listedAnime);
    }

    public Page<ListedAnimeDto> toDto(Page<ListedAnime> listedAnimePage) {
        return listedAnimePage.map(this::toDto);
    }

    public Page<ListedAnime> getAll(UUID listId, Pageable pageable) {
        UUID userId = userService.getAuthenticatedUserUuid();
        return listedAnimeRepository.findAllByListIdAndListUserIdOrListVisibility(listId, userId, ListVisibility.PUBLIC,
                pageable);
    }

    public ListedAnime list(UUID listId, ListAnimeDto listAnimeDto) {
        AnimeDto animeDto = animeService.getById(listAnimeDto.getAnimeId());
        AnitraceList list = listService.getById(listId);
        ListedAnime listedAnime = listedAnimeMapper.listAnimeDtoToListedAnime(listAnimeDto);
        if (listedAnime.getEpisodesWatched() > animeDto.getEpisodes()) {
            throw new BadRequestException("Episodes watched can't be greater than total episodes");
        }
        listedAnime.setList(list);
        listedAnime.setTotalEpisodes(animeDto.getEpisodes());
        return listedAnimeRepository.save(listedAnime);
    }

    public ListedAnime getById(UUID id) {
        UUID userId = userService.getAuthenticatedUserUuid();
        return listedAnimeRepository.findByIdAndListUserIdOrListVisibility(id, userId, ListVisibility.PUBLIC)
                .orElseThrow(() -> new ResourceNotFoundException(ListedAnime.class, "ID", id));
    }

    public void delete(UUID id) {
        ListedAnime listedAnime = getById(id);
        UUID userId = userService.getAuthenticatedUserUuid();
        if (!isUserOwner(listedAnime, userId)) {
            throw new ForbiddenOperationException("You can't delete this item");
        }
        listedAnimeRepository.delete(listedAnime);
    }

    public ListedAnime edit(UUID id, EditListedAnimeDto editListedAnimeDto) {
        ListedAnime listedAnime = getById(id);
        UUID userId = userService.getAuthenticatedUserUuid();
        if (!isUserOwner(listedAnime, userId)) {
            throw new ForbiddenOperationException("You can't edit this item");
        }
        listedAnimeMapper.updateListedAnimeFromEditListedAnimeDto(editListedAnimeDto, listedAnime);
        return listedAnimeRepository.save(listedAnime);
    }

    private boolean isUserOwner(ListedAnime listedAnime, UUID userId) {
        return listedAnime.getList().getUser().getId().equals(userId);
    }

}

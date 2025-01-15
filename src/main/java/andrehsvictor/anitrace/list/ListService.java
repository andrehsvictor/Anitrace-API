package andrehsvictor.anitrace.list;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.exception.ForbiddenOperationException;
import andrehsvictor.anitrace.exception.ResourceNotFoundException;
import andrehsvictor.anitrace.list.dto.CreateListDto;
import andrehsvictor.anitrace.list.dto.EditListDto;
import andrehsvictor.anitrace.list.dto.ListDto;
import andrehsvictor.anitrace.user.User;
import andrehsvictor.anitrace.user.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListService {

    private final ListRepository listRepository;
    private final UserService userService;
    private final ListMapper listMapper;

    public ListDto toDto(AnitraceList list) {
        return listMapper.listToListDto(list);
    }

    public Page<ListDto> toDto(Page<AnitraceList> lists) {
        return lists.map(this::toDto);
    }

    public AnitraceList create(CreateListDto createListDto) {
        AnitraceList list = listMapper.createListDtoToList(createListDto);
        UUID userId = userService.getAuthenticatedUserUuid();
        User user = userService.getById(userId);
        list.setUser(user);
        return listRepository.save(list);
    }

    public Page<AnitraceList> getAllByUserId(UUID userId, String query, Pageable pageable) {
        return listRepository.findAllByUserIdAndVisibility(userId, query, ListVisibility.PUBLIC, pageable);
    }

    public Page<AnitraceList> getAllByAuthenticatedUser(String query, Pageable pageable) {
        UUID userId = userService.getAuthenticatedUserUuid();
        return listRepository.findAllByUserId(userId, query, pageable);
    }

    public AnitraceList getById(UUID id) {
        UUID userId = userService.getAuthenticatedUserUuid();
        return listRepository.findByIdAndUserIdOrVisibility(id, userId, ListVisibility.PUBLIC)
                .orElseThrow(() -> new ResourceNotFoundException(AnitraceList.class, "ID", id));
    }

    public AnitraceList edit(UUID id, EditListDto editListDto) {
        AnitraceList list = getById(id);
        UUID userId = userService.getAuthenticatedUserUuid();
        if (isUserOwner(list, userId)) {
            throw new ForbiddenOperationException("You can only edit your own lists");
        }
        listMapper.updateListFromEditListDto(editListDto, list);
        return listRepository.save(list);
    }

    public void delete(UUID id) {
        AnitraceList list = getById(id);
        UUID userId = userService.getAuthenticatedUserUuid();
        if (isUserOwner(list, userId)) {
            throw new ForbiddenOperationException("You can only delete your own lists");
        }
        listRepository.deleteById(id);
    }

    private boolean isUserOwner(AnitraceList list, UUID userId) {
        return !list.getUser().getId().equals(userId);
    }

}

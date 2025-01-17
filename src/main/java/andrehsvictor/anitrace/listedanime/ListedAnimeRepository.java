package andrehsvictor.anitrace.listedanime;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import andrehsvictor.anitrace.list.ListVisibility;

public interface ListedAnimeRepository extends JpaRepository<ListedAnime, UUID> {

    Page<ListedAnime> findAllByListIdAndListUserIdOrListVisibility(UUID listId, UUID userId, ListVisibility visibility,
            Pageable pageable);

    Page<ListedAnime> findAllByListId(UUID listId, Pageable pageable);

    Optional<ListedAnime> findByIdAndListUserIdOrListVisibility(UUID id, UUID userId, ListVisibility visibility);
    
}

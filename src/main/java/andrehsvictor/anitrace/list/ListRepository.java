package andrehsvictor.anitrace.list;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ListRepository extends JpaRepository<AnitraceList, UUID> {

    @Query("SELECT l FROM AnitraceList l" +
            " WHERE l.user.id = :userId" +
            " AND (:query IS NULL OR l.name ILIKE %:query% OR l.description ILIKE %:query%)")
    Page<AnitraceList> findAllByUserId(UUID userId, String query, Pageable pageable);

    Optional<AnitraceList> findByIdAndUserIdOrVisibility(UUID id, UUID userId, ListVisibility visibility);

    @Query("SELECT l FROM AnitraceList l" +
            " WHERE l.user.id = :userId" +
            " AND (:query IS NULL OR l.name ILIKE %:query% OR l.description ILIKE %:query%)" +
            " AND l.visibility = :visibility")
    Page<AnitraceList> findAllByUserIdAndVisibility(UUID userId, String query, ListVisibility visibility,
            Pageable pageable);

}

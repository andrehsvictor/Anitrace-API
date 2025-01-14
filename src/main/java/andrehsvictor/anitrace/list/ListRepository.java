package andrehsvictor.anitrace.list;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<AnitraceList, UUID> {

    Page<AnitraceList> findAllByUserId(UUID userId, Pageable pageable);

    Page<AnitraceList> findAllByUserIdAndVisibility(UUID userId, ListVisibility visibility, Pageable pageable);

}

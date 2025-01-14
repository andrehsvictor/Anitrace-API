package andrehsvictor.anitrace.list;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository<AnitraceList, UUID> {

}

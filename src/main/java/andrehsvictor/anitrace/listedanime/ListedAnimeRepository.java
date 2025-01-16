package andrehsvictor.anitrace.listedanime;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ListedAnimeRepository extends JpaRepository<ListedAnime, UUID> {

}

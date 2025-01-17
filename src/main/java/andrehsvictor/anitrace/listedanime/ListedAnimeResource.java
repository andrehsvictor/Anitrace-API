package andrehsvictor.anitrace.listedanime;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.anitrace.listedanime.dto.EditListedAnimeDto;
import andrehsvictor.anitrace.listedanime.dto.ListAnimeDto;
import andrehsvictor.anitrace.listedanime.dto.ListedAnimeDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ListedAnimeResource {

    private final ListedAnimeService listedAnimeService;

    @GetMapping("/api/v1/lists/{listId}/listed-animes")
    public Page<ListedAnimeDto> getAll(@PathVariable UUID listId, Pageable pageable) {
        Page<ListedAnime> listedAnimes = listedAnimeService.getAll(listId, pageable);
        return listedAnimeService.toDto(listedAnimes);
    }

    @GetMapping("/api/v1/listed-animes/{id}")
    public ListedAnimeDto getById(@PathVariable UUID id) {
        ListedAnime listedAnime = listedAnimeService.getById(id);
        return listedAnimeService.toDto(listedAnime);
    }

    @PostMapping("/api/v1/lists/{listId}/listed-animes")
    public ResponseEntity<ListedAnimeDto> list(@PathVariable UUID listId, @RequestBody ListAnimeDto listAnimeDto) {
        ListedAnime listedAnime = listedAnimeService.list(listId, listAnimeDto);
        URI location = URI.create("/api/v1/listed-animes/" + listedAnime.getId());
        return ResponseEntity.created(location).body(listedAnimeService.toDto(listedAnime));
    }

    @PutMapping("/api/v1/listed-animes/{id}")
    public ListedAnimeDto edit(@PathVariable UUID id, @RequestBody EditListedAnimeDto editListedAnimeDto) {
        ListedAnime listedAnime = listedAnimeService.edit(id, editListedAnimeDto);
        return listedAnimeService.toDto(listedAnime);
    }

    @DeleteMapping("/api/v1/listed-animes/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        listedAnimeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

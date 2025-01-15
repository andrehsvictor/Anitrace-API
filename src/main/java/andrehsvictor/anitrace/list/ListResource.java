package andrehsvictor.anitrace.list;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import andrehsvictor.anitrace.list.dto.CreateListDto;
import andrehsvictor.anitrace.list.dto.EditListDto;
import andrehsvictor.anitrace.list.dto.ListDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ListResource {

    private final ListService listService;

    @GetMapping("/api/v1/users/{id}/lists")
    public Page<ListDto> getAllByUserId(@PathVariable UUID id,
            @RequestParam(required = false, name = "q") String query,
            Pageable pageable) {
        return listService.toDto(listService.getAllByUserId(id, query, pageable));
    }

    @GetMapping("/api/v1/users/me/lists")
    public Page<ListDto> getAllByAuthenticatedUser(
            @RequestParam(required = false, name = "q") String query,
            Pageable pageable) {
        return listService.toDto(listService.getAllByAuthenticatedUser(query, pageable));
    }

    @GetMapping("/api/v1/lists/{id}")
    public ListDto getById(@PathVariable UUID id) {
        return listService.toDto(listService.getById(id));
    }

    @PostMapping("/api/v1/lists")
    public ResponseEntity<ListDto> create(@Valid @RequestBody CreateListDto createListDto) {
        ListDto listDto = listService.toDto(listService.create(createListDto));
        URI location = URI.create("/api/v1/lists/" + listDto.getId());
        return ResponseEntity.created(location).body(listDto);
    }

    @PutMapping("/api/v1/lists/{id}")
    public ListDto edit(@PathVariable UUID id, @Valid @RequestBody EditListDto editListDto) {
        return listService.toDto(listService.edit(id, editListDto));
    }

    @DeleteMapping("/api/v1/lists/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        listService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package andrehsvictor.anitrace.listedanime.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListAnimeDto {

    @NotNull(message = "Anime ID is required")
    private Integer animeId;

    @Pattern(regexp = "^(PLAN_TO_WATCH|WATCHING|COMPLETED|DROPPED|ON_HOLD)$", message = "Invalid status")
    private String status;

    @Min(value = 0, message = "Score must be between 0.0 and 10.0")
    @Max(value = 10, message = "Score must be between 0.0 and 10.0")
    private BigDecimal score;

    @Pattern(regexp = "^(\\d{4}-\\d{2}-\\d{2})$", message = "Invalid date format")
    private String startedAt;

    @Pattern(regexp = "^(\\d{4}-\\d{2}-\\d{2})$", message = "Invalid date format")
    private String finishedAt;

    private Integer episodesWatched;
    private boolean favorite;
}

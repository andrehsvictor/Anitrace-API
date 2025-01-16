package andrehsvictor.anitrace.anime.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimeDto {
    private Integer malId;
    private String title;
    private String imageUrl;
    private String synopsis;
    private String type;
    private Integer episodes;
    private Double score;
    private String status;
    private String trailerUrl;
    private boolean airing;
    private Object aired;
    private String averageEpisodeDuration;
    private String rating;
    private List<String> genres;
}

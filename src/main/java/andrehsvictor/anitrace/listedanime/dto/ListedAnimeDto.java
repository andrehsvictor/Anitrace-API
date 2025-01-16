package andrehsvictor.anitrace.listedanime.dto;

import andrehsvictor.anitrace.anime.dto.AnimeDto;
import andrehsvictor.anitrace.list.dto.ListDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListedAnimeDto {
    private String id;
    private ListDto list;
    private AnimeDto anime;
    private boolean favorite;
    private String status;
    private Float score;
    private String startedAt;
    private String finishedAt;
    private Integer episodesWatched;
    private Integer totalEpisodes;
    private String createdAt;
    private String updatedAt;
}

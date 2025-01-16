package andrehsvictor.anitrace.listedanime;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import andrehsvictor.anitrace.list.AnitraceList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "lists")
@ToString(exclude = { "list" })
@EqualsAndHashCode(of = { "id" })
public class ListedAnime implements Serializable {

    private static final long serialVersionUID = -3586680998445818900L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private AnitraceList list;

    private Integer animeId;
    private boolean favorite = false;

    @Enumerated(EnumType.STRING)
    private ListedAnimeStatus status = ListedAnimeStatus.PLAN_TO_WATCH;

    private Float score;
    private LocalDate startedAt;
    private LocalDate finishedAt;

    @Column(name = "episodes_watched_count")
    private Integer episodesWatched;

    private Integer totalEpisodes;

    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

}

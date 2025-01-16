package andrehsvictor.anitrace.listedanime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListedAnimeService {

    private final ListedAnimeRepository listedAnimeRepository;
    private final ListedAnimeMapper listedAnimeMapper;
    
}

package andrehsvictor.anitrace.list;

import org.springframework.stereotype.Service;

import andrehsvictor.anitrace.user.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListService {

    private final ListRepository listRepository;
    private final UserService userService;

}

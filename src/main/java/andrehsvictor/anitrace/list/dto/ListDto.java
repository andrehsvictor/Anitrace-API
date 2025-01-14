package andrehsvictor.anitrace.list.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListDto {
    private String id;
    private String userId;
    private String name;
    private String description;
    private Integer animesCount;
    private String visibility;
    private String createdAt;
    private String updatedAt;
}

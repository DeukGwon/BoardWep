package threeclass.threeclassboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class CommentDto {
    private LocalDateTime comment_date;
    @NotBlank(message = "비우면안됑")
    private String comment_content;
    private String username;
}

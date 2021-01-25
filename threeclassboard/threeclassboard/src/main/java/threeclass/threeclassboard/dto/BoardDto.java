package threeclass.threeclassboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import threeclass.threeclassboard.domain.Member;

import javax.persistence.Lob;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private String board_subject; //제목
    private String board_content; //내용
    private LocalDateTime board_date; //게시날짜
    private Member member; //회원
    private int board_count;//조회수

}

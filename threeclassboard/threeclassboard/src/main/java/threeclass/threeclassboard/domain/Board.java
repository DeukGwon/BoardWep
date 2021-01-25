package threeclass.threeclassboard.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_num")
    private Long id;

    @NotNull
    private String board_subject; //제목
    @Lob
    private String board_content; //내용
    private LocalDateTime board_date; //게시날짜
    private int board_count; // 조회수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member; //회원

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();//댓글

    // 연관관계 편의 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

    // 비즈니스 로직
    // 게시물 조회수 증가
   public void addBoardCount(int count) {
       this.board_count += count;
   }

   // 생성 메서드
    public Board(String board_subject, String board_content, LocalDateTime board_date, Member member, int board_count) {
        this.board_subject = board_subject;
        this.board_content = board_content;
        this.board_date = board_date;
        this.member = member;
        this.board_count = board_count;
    }
}

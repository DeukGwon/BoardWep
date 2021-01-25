package threeclass.threeclassboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_num")
    private Long id;

    private LocalDateTime comment_date;

    private String comment_content;
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_num")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Comment parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Member member;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

    public Comment(LocalDateTime comment_date, String comment_content,String username) {
        this.comment_date = comment_date;
        this.comment_content = comment_content;
        this.username = username;
    }
    public void setBoard(Board board) {
        this.board = board;
        board.getComments().add(this);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getComments().add(this);
    }

}

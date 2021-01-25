package threeclass.threeclassboard.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_num")
    private Long id;

    @NotBlank(message = "아이디 필수입니다")
    private String name; // 이름
    @NotBlank(message = "비밀번호도 필수입니다.")
    private String password; // 비밀번호

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();
    // ==== 생성 메서드 =====
    @Builder
    public Member(String name,String password) {
        this.name = name;
        this.password = password;
    }

}

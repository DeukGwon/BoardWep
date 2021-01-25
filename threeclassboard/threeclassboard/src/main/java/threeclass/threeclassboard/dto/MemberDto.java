package threeclass.threeclassboard.dto;

import lombok.*;
import threeclass.threeclassboard.domain.Member;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    @NotBlank(message = "아이디 필수입니다")
    private String name;
    @NotBlank(message = "아이디 필수입니다")
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .password(password)
                .build();
    }

    @Builder
    public MemberDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}


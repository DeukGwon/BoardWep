package threeclass.threeclassboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import threeclass.threeclassboard.Service.CommentService;
import threeclass.threeclassboard.dto.BoardDto;
import threeclass.threeclassboard.dto.CommentDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public String create(Long board_id, @Valid CommentDto commentDto, BindingResult result,
                         Authentication authentication, HttpServletRequest request) {

        String referer = request.getHeader("Referer");
        // Service 로 등록하기
        if(result.hasErrors()){
            return "redirect:"+referer;
        }

        String username = authentication.getName();

        commentService.saveComment(commentDto,board_id,username);
        return "redirect:"+referer;
    }

}

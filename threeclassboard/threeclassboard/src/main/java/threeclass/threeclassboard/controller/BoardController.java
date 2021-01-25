package threeclass.threeclassboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import threeclass.threeclassboard.Service.BoardService;
import threeclass.threeclassboard.Service.CommentService;
import threeclass.threeclassboard.domain.Board;
import threeclass.threeclassboard.domain.Comment;
import threeclass.threeclassboard.dto.BoardDto;
import threeclass.threeclassboard.dto.CommentDto;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/board/new")
    public String createBoard(Model model) {
        model.addAttribute("board", new BoardDto());
        return "board/createBoardForm";
    }

    @PostMapping("/board/new")
    public String create(BoardDto boardDto) {
        boardService.saveBoard(boardDto);
        return "redirect:/board/List";
    }

    @GetMapping("/board/List")
    public String list(Model model, @RequestParam(value = "page",defaultValue = "1") int pageNum) {
        List<Board> board = boardService.findBoard();

        model.addAttribute("boards",board);

        return "board/boardList";
    }


    @GetMapping("/post/{no}")
        public String detaile(@PathVariable("no") Long no, Model model) {
        Board board = boardService.findOne(no);
        List<Comment> comment = commentService.findComments(no);
        board.setBoard_count(board.getBoard_count()+1);

        model.addAttribute("comment", new CommentDto());
        model.addAttribute("comments", comment);
        model.addAttribute("board",board);
        return "board/detail";
    }



}

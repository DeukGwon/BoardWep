package threeclass.threeclassboard.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import threeclass.threeclassboard.domain.Board;
import threeclass.threeclassboard.domain.Comment;
import threeclass.threeclassboard.dto.CommentDto;
import threeclass.threeclassboard.repository.CommentRepository;
import threeclass.threeclassboard.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final BoardService boardService;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveComment(CommentDto commentDto,Long id,String username) {

        commentDto.setComment_date(LocalDateTime.now());
        commentDto.setUsername(username);
        Board board = boardService.findOne(id);

        Comment comment = new Comment(commentDto.getComment_date(), commentDto.getComment_content(),commentDto.getUsername());
        comment.setBoard(board);
        commentRepository.save(comment);
    }

    @Transactional
    public void removeComment(Comment comment) {
        commentRepository.remove(comment);
    }
    public List<Comment> findComments(Long id){

        return commentRepository.findAll(id);
    }

    public Comment findOne(Long id) {
        return commentRepository.findOne(id);
    }

}

package threeclass.threeclassboard.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import threeclass.threeclassboard.domain.Board;
import threeclass.threeclassboard.dto.BoardDto;
import threeclass.threeclassboard.repository.BoardRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private static final int BLOCK_PAGE_NUM_COUNT = 5;
    private static final int PAGE_POST_COUNT = 5;
    private final BoardRepository boardRepository;
    private final MemberService memberService;

    @Transactional
    public void saveBoard(BoardDto boardDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 쓴사람의 id
        // 날짜
        boardDto.setBoard_date(LocalDateTime.now());
        boardDto.setMember(memberService.findByName(authentication.getName()));

        Board board = new Board(boardDto.getBoard_subject(), boardDto.getBoard_content(), boardDto.getBoard_date(), boardDto.getMember(),boardDto.getBoard_count());

        boardRepository.save(board);
    }

    @Transactional
    public void removeBoard(Board board) {
        boardRepository.remove(board);
    }

    public List<Board> findBoard() {

        return boardRepository.findAll();
    }

    public Board findOne(Long id){
        return boardRepository.findOne(id);
    }


}

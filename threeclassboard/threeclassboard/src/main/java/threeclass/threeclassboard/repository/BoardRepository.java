package threeclass.threeclassboard.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import threeclass.threeclassboard.domain.Board;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository{

    private final EntityManager em;

    public void save(Board board){
        em.persist(board);
    }

    public void remove(Board board) {
        em.remove(board);
    }


    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll(){
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();

    }


}

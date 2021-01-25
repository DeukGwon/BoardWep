package threeclass.threeclassboard.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import threeclass.threeclassboard.domain.Comment;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public void remove(Comment comment) {
        em.remove(comment);
    }

    public Comment findOne(Long id) {
        return em.find(Comment.class, id);
    }

    public List<Comment> findAll(Long board) {
        return em.createQuery("select c from Comment c where c.board.id=:board", Comment.class)
                .setParameter("board", board)
                .getResultList();
    }

}

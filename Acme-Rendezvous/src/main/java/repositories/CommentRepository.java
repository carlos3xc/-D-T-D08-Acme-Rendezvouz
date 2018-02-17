package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Answer;
import domain.Comment;
import domain.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
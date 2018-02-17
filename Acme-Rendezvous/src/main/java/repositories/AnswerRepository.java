package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Answer;
import domain.User;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
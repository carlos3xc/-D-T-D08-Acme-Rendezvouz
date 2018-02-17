package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Answer;
import domain.Comment;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {

}
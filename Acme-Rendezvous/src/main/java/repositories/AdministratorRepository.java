package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.User;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

}
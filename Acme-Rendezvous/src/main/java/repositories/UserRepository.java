package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;
import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select count(r.user) from Rendezvous r")
	Long NumberUsersCreatedRendezvouses();
	
	@Query("select u from User u where u.userAccount == ?1")
	User FindByUserAccount(UserAccount u);
}
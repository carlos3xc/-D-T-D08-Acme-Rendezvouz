package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Question;
import domain.Rendezvous;
import domain.User;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {
	
	@Query("select (count(r)/(select count(u) from User u)) from Rendezvous r")
	Double getAverageRendezvousPerUser();
		
//	@Query("select sqrt(sum(u.rendezvouses.size * u.rendezvouses.size) / count(u.rendezvouses.size) - (avg(u.rendezvouses.size) * avg(u.rendezvouses.size))) from User u;")
//	Double getDeviatonRendezvousCreatedPerUser();
	
//	@Query("select avg(u.rendezvouses.size) from Rendezvous r join r.user u where u.rendezvouses.RSVd = true")
//	Double getAverageRendezvousRSVdPerUser();

//	@Query("select sqrt(sum(u.rendezvouses.size * u.rendezvouses.size) / count(u.rendezvouses.size) - (avg(u.rendezvouses.size) * avg(u.rendezvouses.size))) from User u where u.rendezvouses.RSVd = true")
//	Double getDeviatonRendezvousRSVdPerUser();
	
//	@Query("select r from Rendezvous r where r.announcements.size > (select 0.75 * avg(u.rendezvouses.size) from User u)")
//	Double getAverageRendezvousPerAnnouncement();
	
	@Query("select r from Rendezvous r where ?1 member of r.listAttendants")
	Collection<Rendezvous> getRendezvousUser(User user);
	
	@Query("select r from Rendezvous r where ?1 member of r.questions")
	Rendezvous getRendezvousQuestion(Question question);
	
	@Query("select r from Rendezvous r where r.id = ?1")
	Rendezvous getRendezvousById(int rendezvousId);
}
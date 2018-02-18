package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rendezvous;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {
	
	@Query("select avg(r.user) from Rendezvous r")
	Double getAverageRendezvousPerUser();
	
	@Query("select sqrt(sum(u.rendezvouses.size * u.rendezvouses.size) / count(u.rendezvouses.size) - (avg(u.rendezvouses.size) * avg(u.rendezvouses.size))) from User u;")
	Double getDeviatonRendezvousCreatedPerUser();
	
	@Query("select avg(u.rendezvouses.size) from User u where u.rendezvouses.RSVd == true")
	Double getAverageRendezvousRSVdPerUser();
	
	@Query("select sqrt(sum(u.rendezvouses.size * u.rendezvouses.size) / count(u.rendezvouses.size) - (avg(u.rendezvouses.size) * avg(u.rendezvouses.size))) from User u where u.rendezvouses.RSVd == true")
	Double getDeviatonRendezvousRSVdPerUser();
	
	@Query("select r from Rendezvous r where r.announcements.size > (0.75 * avg(u.rendezvouses.size) from User u)")
	Double getAverageRendezvousPerAnnouncement();
}
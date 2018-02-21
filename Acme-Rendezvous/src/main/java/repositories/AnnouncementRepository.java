
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

	@Query("select avg(r.announcements.size) from Rendezvous r")
	Double averageAnnouncementRendezvous();

	@Query("select sqrt(sum(r.announcements.size * r.announcements.size) / count(r.announcements.size) - (avg(r.announcements.size) * avg(r.announcements.size))) from Rendezvous r")
	Double desviationAnnouncementRendezvous();

	@Query("select a from Announcement a order by  a.moment desc")
	Collection<Announcement> findAllByOrderDescending();
}

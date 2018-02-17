package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Announcement;
import domain.User;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

}
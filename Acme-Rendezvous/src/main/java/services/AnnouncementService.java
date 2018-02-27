
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import security.Authority;
import security.LoginService;
import domain.Announcement;

@Service
@Transactional
public class AnnouncementService {

	@Autowired
	private AnnouncementRepository	announcementRepository;


	public Announcement create() {
		Assert.isTrue(this.checkUser());
		Announcement result;
		result = new Announcement();
		Assert.notNull(result, "The announcement must not be null.");
		return result;
	}

	public Announcement save(final Announcement announcement) {
		Assert.notNull(announcement, "The announcement must not be null.");
		long millis;
		Date moment;
		millis = System.currentTimeMillis() - 1000;
		moment = new Date(millis);
		announcement.setMoment(moment);
		final Announcement aSave = this.announcementRepository.save(announcement);
		return aSave;
	}

	public void delete(final Announcement announcement) {
		Assert.isTrue(this.checkAdmin());
		Assert.notNull(announcement, "The announcement must not be null.");
		final Announcement aDelete = this.announcementRepository.findOne(announcement.getId());
		this.announcementRepository.delete(aDelete);
	}

	public Announcement findOne(final int announcement) {
		Announcement result;
		result = this.announcementRepository.findOne(announcement);
		Assert.notNull(result);
		return result;
	}

	public Collection<Announcement> findAll() {
		Collection<Announcement> result;
		result = this.announcementRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	private Boolean checkUser() {
		final Collection<Authority> a = LoginService.getPrincipal().getAuthorities();
		Boolean res = false;
		for (final Authority b : a)
			if (b.getAuthority().equals(Authority.USER))
				res = true;
		return res;
	}

	private Boolean checkAdmin() {
		final Collection<Authority> a = LoginService.getPrincipal().getAuthorities();
		Boolean res = false;
		for (final Authority b : a)
			if (b.getAuthority().equals(Authority.ADMIN)) {
				res = true;
				break;
			}
		return res;
	}

	public Double getAverageAnnouncementRendezvous() {
		Double res = 0.0;
		res = this.announcementRepository.averageAnnouncementRendezvous();
		Assert.notNull(res);
		return res;
	}

	public Double getDesviationAnnouncementRendezvous() {
		Double res = 0.0;
		res = this.announcementRepository.desviationAnnouncementRendezvous();
		Assert.notNull(res);
		return res;
	}

	public Collection<Announcement> getFindAllByOrderDescending() {
		Collection<Announcement> result;
		result = this.announcementRepository.findAllByOrderDescending();
		Assert.notNull(result);
		return result;
	}
}

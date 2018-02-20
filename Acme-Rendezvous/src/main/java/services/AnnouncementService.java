
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import domain.Announcement;

@Service
@Transactional
public class AnnouncementService {

	@Autowired
	private AnnouncementRepository	announcementRepository;


	public Announcement create() {
		Announcement result;
		result = new Announcement();
		Assert.notNull(result, "The announcement must not be null.");
		return result;
	}

	public Announcement save(final Announcement announcement) {
		Assert.notNull(announcement, "The announcement must not be null.");
		final Announcement aSave = this.announcementRepository.save(announcement);
		return aSave;
	}

	public void delete(final Announcement announcement) {
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
}

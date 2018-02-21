
package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Announcement;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AnnouncementServiceTest extends AbstractTest {

	// Service under test ---------------------------------

	@Autowired
	private AnnouncementService	announcementService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {
		Announcement announcement;
		announcement = this.announcementService.create();
		Assert.isNull(announcement.getTitle());
		Assert.isNull(announcement.getDescription());
		Assert.isNull(announcement.getMoment());
	}

	@Test
	public void testSave() {
		Announcement announcement, result;
		announcement = this.announcementService.create();
		Date current;
		current = new Date();
		announcement.setTitle("Example title");
		announcement.setDescription("Example description");
		announcement.setMoment(current);
		result = this.announcementService.save(announcement);
		Assert.isTrue(this.announcementService.findAll().contains(result), "The announcement must exist");
	}

	@Test
	public void testDelete() {
		Announcement announcement, result;
		announcement = this.announcementService.create();
		Date current;
		current = new Date();
		announcement.setTitle("Example title");
		announcement.setDescription("Example description");
		announcement.setMoment(current);
		result = this.announcementService.save(announcement);
		Assert.isTrue(this.announcementService.findAll().contains(result), "The announcement must exist");
		this.announcementService.delete(result);
		Assert.isTrue(!this.announcementService.findAll().contains(result));
	}

}

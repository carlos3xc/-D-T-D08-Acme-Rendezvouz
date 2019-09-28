/*
 * SampleTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Rendezvous;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml",
	"classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RendezvousServiceTest extends AbstractTest {

	// Support Services ---------------------------------------------------------------------------------------------------
		
	@Autowired
		private RendezvousService rendezvousService;	
	
	// Tests ------------------------------------------------------------------

	// The following are fictitious test cases that are intended to check that 
	// JUnit works well in this project.  Just righ-click this class and run 
	// it using JUnit.

	@Test
	public void createTest() {
		
		authenticate("user1");
		
		Rendezvous result = rendezvousService.create();
		
		Assert.isTrue(result.getAnnouncements().isEmpty());
		Assert.isTrue(result.getComments().isEmpty());
		Assert.isTrue(result.getLinkedRendezvous().isEmpty());
		Assert.isTrue(result.getListAttendants().isEmpty());
		Assert.isTrue(result.getQuestions().isEmpty());
		
		Assert.isNull(result.getDescription());
		Assert.isTrue(result.getFinalMode().equals(false));
		Assert.isNull(result.getFlag());
		Assert.isNull(result.getGpsCoordinates());
		Assert.isNull(result.getIsAdultContent());
		Assert.isNull(result.getMoment());
		Assert.isNull(result.getName());
		Assert.isNull(result.getPicture());
		Assert.isNull(result.getUser());
		
		unauthenticate();
	}

	@Test
	public void save() {
		
		 Rendezvous result = rendezvousService.create();
		 
		 result.setDescription("Gonzalo");
		 
		 
	}
/*
	// Ancillary methods ------------------------------------------------------

	protected void sampleTemplate(final String beanName, final int id, final Class<?> expected) {
		Class<?> caught;
		int dbId;

		caught = null;
		try {
			dbId = super.getEntityId(beanName);
			Assert.isTrue(dbId == id);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.checkExceptions(expected, caught);
	}
*/
}

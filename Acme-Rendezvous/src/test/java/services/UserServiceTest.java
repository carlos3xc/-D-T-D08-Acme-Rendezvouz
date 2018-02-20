
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.User;

import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceTest extends AbstractTest{

// Support Services ---------------------------------------------------------------------------------------------------

	private UserService userService;
	
// Tests ------------------------------------------------------------------

// The following are fictitious test cases that are intended to check that 
// JUnit works well in this project.  Just righ-click this class and run 
// it using JUnit.


	@Test(expected = IllegalArgumentException.class)
	public void sampleNegativeTest() {
		Assert.isTrue(false);
	}
	
	@Test
	public void createAndSaveTest() {
		int antes = userService.findAll().size();
		User test= userService.create();
		userService.save(test);
		int despues = userService.findAll().size();
		
		Assert.isTrue(antes != despues);
	}
	
	@Test
	public void deleteUser(){
		int antes = userService.findAll().size();
		User test = userService.create();
		userService.save(test);
		userService.delete(test);
		int despues = userService.findAll().size();
		
		Assert.isTrue(antes == despues);
	}
	
	@Test
	public void updateUser(){
		User test = userService.create();
		userService.save(test);
		User mid = userService.findOne(test.getId());
		test.setEmail("emaildeprueba@gmail.com");
		userService.save(test);
		Assert.isTrue(!(mid.equals(userService.findOne(test.getId()))));
	}

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
	
}

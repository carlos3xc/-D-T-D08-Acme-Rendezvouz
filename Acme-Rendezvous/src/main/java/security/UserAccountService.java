/*
 * UserAccountService.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;


@Service
@Transactional
public class UserAccountService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserAccountRepository	userAccountRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public UserAccountService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public UserAccount findByActor(final Actor actor) {
		Assert.notNull(actor);

		UserAccount result;

		result = userAccountRepository.findByActorId(actor.getId());

		return result;
	}
	
	public UserAccount findOne(int userId) {
		UserAccount result;
		result = userAccountRepository.findOne(userId);
		Assert.notNull(result);
		return result;
	}
	
	public UserAccount findUsername(final String username) {
		Assert.notNull(username);

		UserAccount result;

		result = userAccountRepository.findByUsername(username);

		return result;
	}
	
	public UserAccount create(){
		UserAccount result = new UserAccount();
		return result;
	}
	
	public UserAccount save(UserAccount userAccount){
		UserAccount result;
		result = userAccountRepository.save(userAccount);
		return result;
	}
	
	public void delete(UserAccount userAccount){

		userAccountRepository.delete(userAccount);
	}

	// Other business methods -------------------------------------------------

}

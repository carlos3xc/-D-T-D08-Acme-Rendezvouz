/*
 * ActorService.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Actor;
import domain.Administrator;
import domain.User;

@Service
@Transactional
public class ActorService {
	
	//Migue estuvo aqui

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActorRepository actorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private UserService userService;

	@Autowired
	private AdministratorService adminService;

	// Constructors -----------------------------------------------------------

	public ActorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Actor save(Actor actor) {
		Actor result = null;
		Assert.notNull(actor.getUserAccount(),
				"The actor you pretend to save is not associated to any user account.");

		// We use the corresponding save() method
		if (getAuthorityName(actor).equals("USER")) {
			User user = userService.findOne(actor.getId());
			if (user == null)
				user = userService.create();

			user.setUserAccount(actor.getUserAccount());

			user.setName(actor.getName());
			user.setSurname(actor.getSurname());
			user.setEmail(actor.getEmail());
			user.setPhoneNumber(actor.getPhoneNumber());
			user.setPostalAddress(actor.getPostalAddress());

			result = userService.save(user);
			System.out.println("User saved.");
			
		} else if (getAuthorityName(actor).equals("ADMIN")) {
			Administrator admin = adminService.findOne(actor.getId());
			if (admin == null)
				admin = adminService.create();

			admin.setUserAccount(actor.getUserAccount());

			admin.setName(actor.getName());
			admin.setSurname(actor.getSurname());
			admin.setEmail(actor.getEmail());
			admin.setPhoneNumber(actor.getPhoneNumber());
			admin.setPostalAddress(actor.getPostalAddress());

			result = adminService.save(admin);
			System.out.println("Admin saved.");
		}

		Assert.notNull(result, "Actor couldn't be saved.");
		return result;
	}

	private String getAuthorityName(Actor actor) {
		String result = "";

		for (Authority a : actor.getUserAccount().getAuthorities()) {
			result = a.getAuthority();
			break;
		}
		return result;
	}

	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = actorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Actor findOne(int actorId) {
		Actor result;

		result = actorRepository.findOne(actorId);

		return result;
	}

	// Other business methods -------------------------------------------------
	
	public Actor findByUserAccountId(int id) {
		Actor result;
		result = actorRepository.findByUserAccountId(id);
		return result;
	}

	public Actor findByUserAccountUsername(String username) {
		Actor result;
		result = actorRepository.findByUserAccount(userAccountService
				.findUsername(username));
		return result;
	}

	public Actor findByUserAccount(UserAccount userAccount) {
		Actor result;
		result = findByUserAccountId(userAccount.getId());
		return result;
	}

	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();

		assert userAccount != null;
		result = findByUserAccount(userAccount);
		assert result != null;
		return result;
	}
}

/* ProfileController.java
 *
 * Copyright (C) 2015 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;


import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.LoginService;
import services.ActorService;
import services.AdministratorService;
import services.RendezvousService;

import services.UserService;
import domain.Actor;
import domain.Administrator;
import domain.Announcement;
import domain.Rendezvous;

import domain.User;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {
	
	@Autowired
	ActorService actorService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AdministratorService administratorService;
	
	@Autowired
	RendezvousService rendezvousService;

	
	// Info ---------------------------------------------------------------		

	@RequestMapping("/info")
	public ModelAndView info(@RequestParam(required=false) final Integer actorId) {
		ModelAndView result;
		String auth = "";
		String userOk = "";
		Actor aux = null, a=null;
		User user;
		Administrator administrator;
		result = new ModelAndView("profile/info");
		
		if(actorId != null)
			 a = actorService.findOne(actorId);
		else
			 a = actorService.findByPrincipal();

		if(LoginService.getPrincipal2() != null){
			aux = actorService.findByUserAccount(LoginService.getPrincipal());
			if(aux.equals(a)){
			  userOk = "OK";
			}
		}		
		
		for(Authority at: a.getUserAccount().getAuthorities()){
			auth = at.getAuthority();
			break;
		}
		
		if(auth.equals("USER")){
			user = userService.findOne(a.getId());
			Collection<Rendezvous> rendezvouses = rendezvousService.getRendezvousUser(user);
			Collection<Announcement> announs = new LinkedList<Announcement>();
			for(Rendezvous r : rendezvouses){
				announs.addAll(r.getAnnouncements());
			}
			result.addObject("rendezvouses",rendezvouses);
			result.addObject("announcements",announs);
			result.addObject("actor", user);
			result.addObject("OK",userOk);
		}else if(auth.equals("ADMIN")){
			administrator = administratorService.findOne(a.getId());
			result.addObject("actor",administrator);
		}
		
		result.addObject("requestURI", "profile/info.do");
		return result;
	}
/*	
	@RequestMapping("/infoUser")
	public ModelAndView infoUser(@RequestParam final int actorId) {
		ModelAndView result;
		String auth = "";
		User user;
		Administrator administrator;
		result = new ModelAndView("profile/infoUser");

		Actor a = actorService.findOne(actorId);
		
		for(Authority at: a.getUserAccount().getAuthorities()){
			auth = at.getAuthority();
			break;
		}
		
		if(auth.equals("USER")){
			user = userService.findOne(a.getId());
			Collection<Rendezvous> rendezvouses = rendezvousService.getRendezvousUser(user);
			Collection<Announcement> announs = new LinkedList<Announcement>();
			for(Rendezvous r : rendezvouses){
				announs.addAll(r.getAnnouncements());
			}
			result.addObject("rendezvouses",rendezvouses);
			result.addObject("announcements",announs);
			result.addObject("actor", user);
		}else if(auth.equals("ADMIN")){
			administrator = administratorService.findOne(a.getId());
			result.addObject("actor",administrator);
		}
		
		result.addObject("requestURI", "profile/infoUser.do");
		return result;
	}*/
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int actorId) {
		ModelAndView result = null;
		User user;
		Administrator administrator;
		String auth ="";
		//actorService.findOne(actorId)
		Actor a = actorService.findByUserAccount(LoginService.getPrincipal());
		for(Authority at: a.getUserAccount().getAuthorities()){
			auth = at.getAuthority();
			break;
		}
		
		if(auth.equals("USER")){
			user = userService.findOne(a.getId());
			result = this.createEditModelAndView(user);
		}else if(auth.equals("ADMIN")){
			administrator = administratorService.findOne(a.getId());
			result = this.createEditModelAndView(administrator);
		}
		return result;
	}
	
	/*@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveExplorer")
	public ModelAndView save(@Valid final Explorer explorer, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			System.out.println("Algo ha fallao: \n" + binding.getFieldErrors());
			result = this.createEditModelAndView(explorer);
		} else
			try {
				explorerService.save(explorer);
				result = new ModelAndView("redirect:info.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(explorer, "actor.commit.error");
			}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveManager")
	public ModelAndView save(@Valid final Manager manager, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			System.out.println("Algo ha fallao: \n" + binding.getFieldErrors());
			result = this.createEditModelAndView(manager);
		} else
			try {
				managerService.save(manager);
				result = new ModelAndView("redirect:info.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(manager, "actor.commit.error");
			}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveAdministrator")
	public ModelAndView save(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			System.out.println("Algo ha fallao: \n" + binding.getFieldErrors());
			result = this.createEditModelAndView(administrator);
		} else
			try {
				administratorService.save(administrator);
				result = new ModelAndView("redirect:info.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(administrator, "actor.commit.error");
			}
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveRanger")
	public ModelAndView save(@Valid final Ranger ranger, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			System.out.println("Algo ha fallao: \n" + binding.getFieldErrors());
			result = this.createEditModelAndView(ranger);
		} else
			try {
				rangerService.save(ranger);
				result = new ModelAndView("redirect:info.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(ranger, "actor.commit.error");
			}
		return result;
	}*/
	
	protected ModelAndView createEditModelAndView(final Actor actor) {
		ModelAndView result;
		result = this.createEditModelAndView(actor, null);
		return result;
	}

	private ModelAndView createEditModelAndView(final Actor actor, final String message) {
		ModelAndView result;
		Authority auth = null;

		result = new ModelAndView("profile/edit");
		result.addObject("actor", actor);
		for(Authority a : actor.getUserAccount().getAuthorities()){
			auth = a;
			break;
		}

		result.addObject("authority", auth.getAuthority());
		result.addObject("message", message);

		return result;
	}
	
}
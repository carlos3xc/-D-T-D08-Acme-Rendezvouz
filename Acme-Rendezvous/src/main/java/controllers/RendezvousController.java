package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ActorService;
import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;
import domain.Actor;
import domain.Rendezvous;
import domain.User;


@Controller
@RequestMapping("/rendezvous")
public class RendezvousController extends AbstractController {

	@Autowired
	private RendezvousService rendezvousService;
	
	@Autowired
	private ActorService actorService;
	
	public RendezvousController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Rendezvous> rendezvouses;
		rendezvouses = this.rendezvousService.findAll();
		
		User logged = (User) actorService.findByPrincipal();

		result = new ModelAndView("rendezvous/list");
		result.addObject("logged", logged);
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/list.do");
		return result;
	}
	
	//Display -----------------------------------------------------------------
	
		@RequestMapping(value = "/display", method = RequestMethod.GET)
		public ModelAndView display(final Integer rendezvousId) {
			ModelAndView result;

			Rendezvous rendezvous = rendezvousService.findOne(rendezvousId);
			String comment= null;
			Actor aux = null;
			
			if(actorService.findByPrincipal() != null){
				aux = actorService.findByPrincipal();
				for(Actor a: rendezvous.getListAttendants()){
					if(a.getId() == aux.getId()){
						comment = "OK";
						break;
					}
				}
			}		
			

			Integer loggedUserId = actorService.findByPrincipal().getId();
			System.out.println(loggedUserId);

			result = new ModelAndView("rendezvous/display");
			result.addObject("rendezvous", rendezvous);
			result.addObject("comment", comment);
			result.addObject("logged", loggedUserId);
			result.addObject("requestURI", "rendezvous/display.do");
			return result;
		}

	
	
}

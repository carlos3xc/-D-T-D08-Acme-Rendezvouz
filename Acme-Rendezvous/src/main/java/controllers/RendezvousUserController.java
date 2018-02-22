package controllers;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
import controllers.AbstractController;



@Controller
@RequestMapping("/rendezvous")
public class RendezvousUserController extends AbstractController {

	@Autowired
	private RendezvousService rendezvousService;

	public RendezvousUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Rendezvous> rendezvouses;
		rendezvouses = rendezvousService.findAll();

		result = new ModelAndView("user/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/user/list.do");
		return result;
	}
}

package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import services.AdministratorService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Rendezvous;
import domain.User;


@Controller
@RequestMapping("/administrator/rendezvous")
public class RendezvousAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService adminService;
	
	@Autowired
	private RendezvousService rendezvousService;
	
	@Autowired
	private UserAccountService userAccountService;

	public RendezvousAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<Rendezvous> rendezvouses;
		rendezvouses = rendezvousService.findAll();

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvouses/administrator/list.do");
		return result;
	}
	

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Rendezvous rendezvous, final BindingResult binding) {
		//the delete is virtual 
		
		ModelAndView result;

		try {
			this.rendezvousService.delete(rendezvous);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createListModelAndView(rendezvous, "user.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous) {
		ModelAndView result;

		result = this.createListModelAndView(rendezvous, null);
		return result;
	}

	protected ModelAndView createListModelAndView(final Rendezvous rendezvous,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvous", rendezvous);
		result.addObject("message", message);
		return result;
	}
}

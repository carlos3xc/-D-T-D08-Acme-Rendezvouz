
package controllers.user;

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

import services.ActorService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Actor;
import domain.Rendezvous;
import domain.User;
import forms.RendezvousForm;

@Controller
@RequestMapping("/rendezvous/user")
public class RendezvousUserController extends AbstractController {

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private ActorService		actorService;


	public RendezvousUserController() {
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

	//	// Profile ---------------------------------------------------------------
	//	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	//	public ModelAndView list(@RequestParam final int userId) {
	//		ModelAndView result;
	//
	//		User user;
	//		user = userService.findOne(userId);
	//
	//		result = new ModelAndView("user/profile");
	//		result.addObject("user", user);
	//		result.addObject("requestURI", "user/profile.do");
	//		return result;
	//	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Rendezvous rendezvous = this.rendezvousService.create();
		//Se asume que el actor va a atender a su propio rendezvous asi que se le añade a la lista de atendidos (implementado en servicio)

		result = this.createEditModelAndView(rendezvous);
		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int rendezvousId) {
		ModelAndView result;
		final Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);

		Assert.notNull(rendezvous);

		final Actor loggedActor = this.actorService.findByPrincipal();
		final User beingModified = this.rendezvousService.findOne(rendezvousId).getUser();

		Assert.isTrue(loggedActor.equals(beingModified));

		result = this.createEditModelAndView(rendezvous);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final RendezvousForm rendezvousForm, final BindingResult binding) {
		ModelAndView result;
		Rendezvous rendezvous;
		rendezvous = rendezvousService.reconstruct(rendezvousForm, binding);
		if (binding.hasErrors()){
			System.out.println(binding.getFieldErrors());
			result = this.createEditModelAndView(rendezvous);
		}

		else
			try {
				this.rendezvousService.save(rendezvous);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(rendezvous, "rendezvous.commit.error");
			}
		return result;
	}

	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	//	public ModelAndView delete(final User user, final BindingResult binding) {
	//		ModelAndView result;
	//
	//		try {
	//			this.userService.delete(rendezvous);
	//			result = new ModelAndView("redirect:list.do");
	//		} catch (final Throwable oops) {
	//			result = this.createEditModelAndView(rendezvous, "user.commit.error");
	//		}
	//		return result;
	//	}

	//Extra methods
	@RequestMapping(value = "/list", method = RequestMethod.POST, params = "reserve")
	//TODO: si se quiere que se pueda hacer el registro desde la vista de list se cambia esto
	public ModelAndView reserve(@Valid final Rendezvous rendezvous, final BindingResult binding) {
		ModelAndView result;

		rendezvous.getListAttendants().add((User) this.actorService.findByPrincipal());
		//TODO: mirar esto por si hay que hacer un set de la lista de atendidos.

		if (binding.hasErrors())
			result = this.createEditModelAndView(rendezvous);
		else
			try {
				this.rendezvousService.save(rendezvous);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(rendezvous, "rendezvous.commit.error");
			}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous) {
		ModelAndView result;

		result = this.createEditModelAndView(rendezvous, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Rendezvous rendezvous, final String message) {
		ModelAndView result;
		
		result = new ModelAndView("rendezvous/edit");
//		
//		Collection<User> attendants = new ArrayList<>();
//		
//		attendants = rendezvous.getListAttendants(); 

		result.addObject("rendezvous", rendezvous);
//		result.addObject("listAttendants", attendants);
		result.addObject("message", message);
		return result;
	}
}

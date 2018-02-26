package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccountService;
import services.AdministratorService;
import services.CommentService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Comment;
import domain.Rendezvous;


@Controller
@RequestMapping("/comment/administrator")
public class CommentAdministratorController extends AbstractController {

	@Autowired
	private CommentService commentService;
	
	@Autowired 
	private RendezvousService rendezvousService;

	public CommentAdministratorController() {
		super();
	}
	
	// No deberia hacer falta listarlos nunca porque van vinculados al rendezvous y salen en el display rendezvous.

	// Listing ----------------------------------------------------------------
//
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView list() {
//		ModelAndView result;
//
//		Collection<Rendezvous> rendezvouses;
//		rendezvouses = rendezvousService.findAll();
//
//		result = new ModelAndView("rendezvous/list");
//		result.addObject("rendezvouses", rendezvouses);
//		result.addObject("requestURI", "rendezvouses/administrator/list.do");
//		return result;
//	}
	

	// Delete ----------------------------------------------------------------

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(final Integer commentId, final Integer rendezvousId) { 
		ModelAndView result;
		
		Comment comment = commentService.findOne(commentId);
		commentService.delete(comment);
		Rendezvous rendezvous = rendezvousService.findOne(rendezvousId);

		result = new ModelAndView("rendezvous/display.do");
		result.addObject("rendezvous", rendezvous);
		result.addObject("requestURI", "rendezvous/administrator/display.do");
		return result;
	}
}

package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccountService;
import services.AdministratorService;
import services.CommentService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Announcement;
import domain.Comment;
import domain.Rendezvous;
import forms.CommentForm;


@Controller
@RequestMapping("/comment/user")
public class CommentUserController extends AbstractController {

	@Autowired
	private CommentService commentService;
	
	@Autowired 
	private RendezvousService rendezvousService;

	public CommentUserController() {
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
	
	// Creation -----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Comment comment;

		comment = this.commentService.create();

		result = this.createEditModelAndView(comment);

		return result;
	}

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
	
	// Edition -----------------------------------------------------------------

	/*	@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam final int announcementId) {

			ModelAndView result;
			Announcement announcement;

			announcement = this.announcementService.findOne(announcementId);
			result = this.createEditModelAndView(announcement);

			return result;
		}*/

		// Save -----------------------------------------------------------------

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid final CommentForm commentForm, final BindingResult binding) {
			ModelAndView result;
			Comment comment;
			comment = commentService.reconstruct(commentForm, binding);

			if (binding.hasErrors())
				result = this.createEditModelAndView(comment);
			else
				try {
					this.commentService.save(comment);
					Rendezvous r = rendezvousService.getRendezvousByComment(comment);
					result = new ModelAndView("redirect:rendezvous/display.do?rendezvousId="+r.getId());
				} catch (final Throwable oops) {
					result = this.createEditModelAndView(comment, "announcement.commit.error");
				}
			return result;
		}
	
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;
		result = this.createEditModelAndView(comment, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment, final String message) {

		ModelAndView result;
		result = new ModelAndView("comment/edit");
		System.out.println("bueno buenoooo");
		Rendezvous r = rendezvousService.getRendezvousByComment(comment);
		System.out.println("pero no me grites");
		result.addObject("rendezvous",r);
		System.out.println("joder chocho");
		result.addObject("comment", comment);
		result.addObject("message", message);
		return result;
	}
}

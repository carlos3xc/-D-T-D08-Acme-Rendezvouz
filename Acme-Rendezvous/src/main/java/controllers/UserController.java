package controllers;

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

import services.UserService;
import domain.User;


@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Autowired
	private UserService userService;

	public UserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<User> users;
		users = userService.findAll();

		result = new ModelAndView("user/list");
		result.addObject("users", users);
		result.addObject("requestURI", "user/list.do");
		return result;
	}
	
	// Profile ---------------------------------------------------------------
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int userId) {
		ModelAndView result;

		User user;
		user = userService.findOne(userId);

		result = new ModelAndView("user/profile");
		result.addObject("user", user);
		result.addObject("requestURI", "user/profile.do");
		return result;
	}
	
	

	//Creation ---------------------------------------------------------------

	 @RequestMapping(value = "/create", method = RequestMethod.GET)
	 public ModelAndView create() {
	 ModelAndView result;
	 User user = this.userService.create();
	
	 result = this.createEditModelAndView(user);
	 return result;
	 }


	 
	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int userId) {
		ModelAndView result;
		User user = this.userService.findOne(userId);

		Assert.notNull(user);

		result = this.createEditModelAndView(user);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final User user, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(user);
		else
			try {
				this.userService.save(user);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(user, "user.commit.error");
			}
		return result;
	}

//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
//	public ModelAndView delete(final User user, final BindingResult binding) {
//		ModelAndView result;
//
//		try {
//			this.userService.delete(user);
//			result = new ModelAndView("redirect:list.do");
//		} catch (final Throwable oops) {
//			result = this.createEditModelAndView(user, "user.commit.error");
//		}
//		return result;
//	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final User user) {
		ModelAndView result;

		result = this.createEditModelAndView(user, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final User user,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("user/edit");
		result.addObject("user", user);
		result.addObject("message", message);
		return result;
	}
}

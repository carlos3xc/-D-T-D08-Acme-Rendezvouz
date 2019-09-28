package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//import services.AdministratorService;
import services.UserService;
import domain.User;


@Controller
@RequestMapping("/register")
public class RegisterController extends AbstractController{
	
	//SERVICES -------------------------------------------------------------------
	
	@Autowired
	private UserService	userService;
	
//	@Autowired
//	private AdministratorService adminService;
//	
	//CONSTRUCTORS ---------------------------------------------------------------
	
	public RegisterController() {
		super();
	}

	// LISTING -----------------------------------------------------------------

	@RequestMapping(value = "/listUsers", method = RequestMethod.GET)
	public ModelAndView listUser(){
		ModelAndView result;
		Collection<User> users;
		String a = "USER";
		
		users = userService.findAll();
		
		result = new ModelAndView("register/listUsers");
		result.addObject("users",users);
		result.addObject("RequestURI", "register/listUsers.do");
		result.addObject("actor", a);
		
		return result;
	}
	
	// Create -----------------------------------------------------------------

	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public ModelAndView createUser() {
		
		ModelAndView result;
		User user;

		user = userService.create();

		result = this.createEditModelAndView(user);

		return result;
	}
	
	// Edit -----------------------------------------------------------------

	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(@RequestParam final int userId) {
		
		ModelAndView result;
		User user;

		user = userService.findOne(userId);
		result = createEditModelAndView(user);

		return result;
	}


	// Save -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveUser")
	public ModelAndView saveUser(@Valid final User user, final BindingResult binding) {
		ModelAndView result;
		
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		String pass = encoder.encodePassword(user.getUserAccount().getPassword(),null);
		user.getUserAccount().setPassword(pass);

		if (binding.hasErrors()) {
			System.out.println(binding.getFieldErrors());
			result = this.createEditModelAndView(user);
		} else
			try {
				userService.save(user);
				String ok = "OK";
				result = new ModelAndView("redirect:/");
				result.addObject("success", ok);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(user, "register.commit.error");
			}
		return result;
	}
	
	
	// Ancillary methods -----------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final User user) {
		ModelAndView result;
		result = this.createEditModelAndView(user, null);
		return result;
	}

	private ModelAndView createEditModelAndView(final User user, final String message) {
		ModelAndView result = new ModelAndView();
		
			result = new ModelAndView("register/edit");
			result.addObject("user", user);
		result.addObject("message", message);

		return result;
	}
}
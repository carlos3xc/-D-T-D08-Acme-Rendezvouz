
package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.QuestionService;
import controllers.AbstractController;
import domain.Question;
import forms.QuestionForm;

@Controller
@RequestMapping("/question/user")
public class QuestionUserController extends AbstractController {

	@Autowired
	private QuestionService	questionService;


	public QuestionUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public ModelAndView list() {
//		ModelAndView result;
//
//		Collection<Question> Questions;
//		Questions = this.questionService.findAll();
//
//		result = new ModelAndView("Question/list");
//		result.addObject("Questions", Questions);
//		result.addObject("requestURI", "Question/list.do");
//		return result;
//	}

	// Creation -----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Question question;

		question = this.questionService.create();

		result = this.createEditModelAndView(question);

		return result;
	}

	// Edition -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int questionId) {

		ModelAndView result;
		Question question;

		question = this.questionService.findOne(questionId);
		result = this.createEditModelAndView(question);

		return result;
	}

	// Save -----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final QuestionForm questionForm, final BindingResult binding) {
		ModelAndView result;
		Question question;
		
		question = questionService.reconstruct(questionForm, binding);
		if (binding.hasErrors()){
			System.out.println(binding.getFieldErrors());
			result = this.createEditModelAndView(question);
		}
		else
			try {
				this.questionService.save(question);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(question, "question.commit.error");
			}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Question question) {
		ModelAndView result;
		result = this.createEditModelAndView(question, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Question question, final String message) {

		ModelAndView result;
		result = new ModelAndView("question/edit");
		result.addObject("question", question);
		result.addObject("message", message);
		return result;
	}

}

/*
 * ActorService.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.QuestionRepository;
import domain.Answer;
import domain.Question;
import forms.QuestionForm;

@Service
@Transactional
public class QuestionService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private QuestionRepository	questionRepository;


	// Supporting services ----------------------------------------------------
	
	@Autowired
	private Validator validator;
	
	// Constructors -----------------------------------------------------------

	public QuestionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Question create() {
		Question res = new Question();
		
		res.setAnswers(new ArrayList<Answer>());
		res.setText("");
		
		return res;
	}
	
	public Question save(final Question question) {
		Question result;

		result = this.questionRepository.save(question);

		return result;
	}

	public void delete(final Question question) {
		this.questionRepository.delete(question);
	}

	public Collection<Question> findAll() {
		Collection<Question> result;

		result = this.questionRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Question findOne(final int questionId) {
		Question result;

		result = this.questionRepository.findOne(questionId);

		return result;
	}

	// Other business methods -------------------------------------------------
	
	public Question reconstruct(QuestionForm question, BindingResult binding){
		Question result;
		
		if(question.getId()==0){
			result = this.create();
			
			result.setText(question.getText());
		}else{
			result = questionRepository.findOne(question.getId());
			
			result.setText(question.getText());
			
			validator.validate(result, binding);
		}
		return result;
	}
	
}

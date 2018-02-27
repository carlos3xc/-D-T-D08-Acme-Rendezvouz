
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CommentRepository;
import security.Authority;
import security.LoginService;
import domain.Comment;
import forms.CommentForm;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository	commentRepository;
	
	@Autowired
	private Validator validator;


	// Simple CRUD methods ----------------------------------------------------

	public Comment create() {
		Assert.isTrue(this.checkUser());
		Comment result;
		result = new Comment();
		result.setReplies(new ArrayList<Comment>());
		return result;
	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment, "The comment must not be null.");
		long millis;
		Date moment;
		millis = System.currentTimeMillis() - 1000;
		moment = new Date(millis);
		comment.setMoment(moment);
		final Comment cSave = this.commentRepository.save(comment);
		return cSave;
	}
	
	public Comment reconstruct(CommentForm commentForm, BindingResult binding){
		Comment result;
		
		if(commentForm.getId()==0){
			result = this.create();
		
			result.setText(commentForm.getText());
			result.setPicture(commentForm.getPicture());
		}else{
			result = commentRepository.findOne(commentForm.getId());
			
			result.setText(commentForm.getText());
			result.setPicture(commentForm.getPicture());
			
			validator.validate(result, binding);
		}
		return result;
	}

	public void delete(final Comment comment) {
		Assert.isTrue(this.checkAdmin());
		Assert.notNull(comment, "The comment must not be null.");
		this.commentRepository.delete(comment);
	}

	public Comment findOne(final int comment) {
		Comment result;
		result = this.commentRepository.findOne(comment);
		Assert.notNull(result);
		return result;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;
		result = this.commentRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	private Boolean checkUser() {
		final Collection<Authority> a = LoginService.getPrincipal().getAuthorities();
		Boolean res = false;
		for (final Authority b : a)
			if (b.getAuthority().equals(Authority.USER))
				res = true;
		return res;
	}

	private Boolean checkAdmin() {
		final Collection<Authority> a = LoginService.getPrincipal().getAuthorities();
		Boolean res = false;
		for (final Authority b : a)
			if (b.getAuthority().equals(Authority.ADMIN)) {
				res = true;
				break;
			}
		return res;
	}
}

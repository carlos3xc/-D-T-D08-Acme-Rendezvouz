
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	@Autowired
	private CommentRepository	commentRepository;


	// Simple CRUD methods ----------------------------------------------------

	public Comment create() {
		Assert.isTrue(this.checkUser());
		Comment result;
		result = new Comment();
		return result;
	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment, "The comment must not be null.");
		final Comment cSave = this.commentRepository.save(comment);
		return cSave;
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
		Boolean result = false;
		final UserAccount ua = LoginService.getPrincipal();
		for (final Authority a : ua.getAuthorities())
			if (a.equals(Authority.USER))
				result = true;
		return result;
	}

	private Boolean checkAdmin() {
		Boolean result = false;
		final UserAccount ua = LoginService.getPrincipal();
		for (final Authority a : ua.getAuthorities())
			if (a.equals(Authority.ADMIN))
				result = true;
		return result;
	}
}

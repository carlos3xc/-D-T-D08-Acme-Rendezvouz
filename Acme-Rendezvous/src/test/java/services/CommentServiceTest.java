
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Comment;
import domain.User;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CommentServiceTest extends AbstractTest {

	@Autowired
	private CommentService	commentService;
	private UserService		userService;


	// Tests ----------------------------------------------

	@Test
	public void testCreate() {
		this.authenticate("user2");
		Comment comment;
		comment = this.commentService.create();
		Assert.isNull(comment.getText());
		Assert.isNull(comment.getPicture());
		Assert.isNull(comment.getMoment());
		Assert.isNull(comment.getUser());
		Assert.notNull(comment.getReplies());
		this.unauthenticate();
	}

	@Test
	public void testSave() {
		Comment comment, result;
		comment = this.commentService.create();
		final User user = this.userService.findOne(15);
		final Collection<Comment> replies = new ArrayList<Comment>();
		replies.add(comment);
		Date current;
		current = new Date();
		comment.setText("Example text");
		comment.setPicture("http://www.examplepicture.com");
		comment.setMoment(current);
		comment.setUser(user);
		comment.setReplies(replies);
		result = this.commentService.save(comment);
		Assert.isTrue(this.commentService.findAll().contains(result), "The comment must exist");
	}

	@Test
	public void testDelete() {
		this.authenticate("admin");
		Comment comment, result;
		comment = this.commentService.create();
		final User user = this.userService.findOne(15);
		final Collection<Comment> replies = new ArrayList<Comment>();
		replies.add(comment);
		Date current;
		current = new Date();
		comment.setText("Example text");
		comment.setPicture("http://www.examplepicture.com");
		comment.setMoment(current);
		comment.setUser(user);
		comment.setReplies(replies);
		result = this.commentService.save(comment);
		Assert.isTrue(this.commentService.findAll().contains(result), "The comment must exist");
		this.commentService.delete(result);
		Assert.isTrue(!this.commentService.findAll().contains(result));
		this.unauthenticate();
	}
}

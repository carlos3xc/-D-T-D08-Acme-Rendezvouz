package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AdministratorRepository;
import repositories.AnnouncementRepository;
import repositories.AnswerRepository;
import repositories.CommentRepository;
import repositories.UserRepository;

import domain.Administrator;
import domain.Announcement;
import domain.Answer;
import domain.Comment;
import domain.User;

@Component
@Transactional
public class StringToCommentConverter implements Converter<String, Comment> {

	@Autowired
	CommentRepository commentRepository;

	@Override
	public Comment convert(final String text) {
		Comment result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.commentRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}

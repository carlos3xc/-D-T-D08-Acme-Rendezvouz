package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import domain.Announcement;
import domain.Answer;
import domain.Comment;
import domain.User;

@Component
@Transactional
public class CommentToStringConverter implements Converter<Comment, String> {

	@Override
	public String convert(final Comment comment) {
		String result;

		if (comment == null)
			result = null;
		else
			result = String.valueOf(comment.getId());
		return result;
	}
}

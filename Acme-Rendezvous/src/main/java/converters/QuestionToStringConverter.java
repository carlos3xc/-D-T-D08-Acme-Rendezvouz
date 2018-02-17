package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import domain.Announcement;
import domain.Answer;
import domain.Comment;
import domain.Question;
import domain.User;

@Component
@Transactional
public class QuestionToStringConverter implements Converter<Question, String> {

	@Override
	public String convert(final Question question) {
		String result;

		if (question == null)
			result = null;
		else
			result = String.valueOf(question.getId());
		return result;
	}
}

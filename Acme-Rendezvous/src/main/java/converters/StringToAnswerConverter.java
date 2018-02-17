package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AdministratorRepository;
import repositories.AnnouncementRepository;
import repositories.AnswerRepository;
import repositories.UserRepository;

import domain.Administrator;
import domain.Announcement;
import domain.Answer;
import domain.User;

@Component
@Transactional
public class StringToAnswerConverter implements Converter<String, Answer> {

	@Autowired
	AnswerRepository answerRepository;

	@Override
	public Answer convert(final String text) {
		Answer result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.answerRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}

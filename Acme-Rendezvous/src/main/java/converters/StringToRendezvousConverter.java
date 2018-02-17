package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AdministratorRepository;
import repositories.AnnouncementRepository;
import repositories.AnswerRepository;
import repositories.CommentRepository;
import repositories.QuestionRepository;
import repositories.RendezvousRepository;
import repositories.UserRepository;

import domain.Administrator;
import domain.Announcement;
import domain.Answer;
import domain.Comment;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@Component
@Transactional
public class StringToRendezvousConverter implements Converter<String, Rendezvous> {

	@Autowired
	RendezvousRepository rendezvousRepository;

	@Override
	public Rendezvous convert(final String text) {
		Rendezvous result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.rendezvousRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}

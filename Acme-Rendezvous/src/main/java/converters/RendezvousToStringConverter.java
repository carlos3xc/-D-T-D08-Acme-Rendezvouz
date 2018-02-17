package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import domain.Announcement;
import domain.Answer;
import domain.Comment;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@Component
@Transactional
public class RendezvousToStringConverter implements Converter<Rendezvous, String> {

	@Override
	public String convert(final Rendezvous rendezvous) {
		String result;

		if (rendezvous == null)
			result = null;
		else
			result = String.valueOf(rendezvous.getId());
		return result;
	}
}

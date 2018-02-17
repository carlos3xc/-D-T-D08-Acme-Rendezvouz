package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Administrator;
import domain.Announcement;
import domain.User;

@Component
@Transactional
public class AnnouncementToStringConverter implements Converter<Announcement, String> {

	@Override
	public String convert(final Announcement announcement) {
		String result;

		if (announcement == null)
			result = null;
		else
			result = String.valueOf(announcement.getId());
		return result;
	}
}

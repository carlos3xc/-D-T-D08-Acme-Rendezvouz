/*
 * StringToCurriculumConverter.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import security.UserAccount;
import security.UserAccountRepository;

// TODO: Implement this converter from scratch.
@Component
@Transactional
public class StringToUserAccountConverter implements Converter<String, UserAccount> {

	@Autowired
	UserAccountRepository	userAccountRepository;


	@Override
	public UserAccount convert(final String text) {
		UserAccount result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.userAccountRepository.findOne(id);
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}

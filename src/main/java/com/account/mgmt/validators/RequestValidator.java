package com.account.mgmt.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.account.mgmt.exception.ValidationException;
import com.account.mgmt.util.AccountMgmtConstant;

public class RequestValidator<T> {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestValidator.class);

	public void payloadValidator(T data) throws ValidationException {
		Configuration<?> config = Validation.byDefaultProvider().configure();
		ValidatorFactory factory = config.buildValidatorFactory();

		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(data);

		List<String> errors = new ArrayList<String>();

		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			LOGGER.error(constraintViolation.getPropertyPath() + " -> " + constraintViolation.getMessage() + "   "
					+ constraintViolation.getMessageTemplate());
			errors.add(constraintViolation.getMessage());
		}

		if (errors != null && !errors.isEmpty()) {

			throw new ValidationException("" + errors);
		}
	}

	public void paloadNullCheck(T data) throws ValidationException {
		if (data == null) {
			throw new ValidationException(AccountMgmtConstant.INVALID_PAYLOAD_REQUEST_ERR_MSG);
		}
	}

	public void valueNullAndEmptyCheck(String propertyName, String value) throws ValidationException {
		if (value == null || value.isEmpty()) {
			throw new ValidationException(AccountMgmtConstant.INVALID_PROPERTY_REQUEST_ERR_MSG + propertyName);
		}

	}

	public void validateEmail(String email) throws ValidationException {
		try {
			Pattern pattern = Pattern.compile(AccountMgmtConstant.EMAIL_PATTERN);
			
			Matcher matcher = pattern.matcher(email);
			if (!matcher.matches()) {
				throw new ValidationException(AccountMgmtConstant.INVALID_EMAIL_FORMAT_ERR_MSG);
			}
		} catch (Exception e) {
			throw new ValidationException(AccountMgmtConstant.INVALID_EMAIL_FORMAT_ERR_MSG);
		}

	}

}

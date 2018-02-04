package com.github.cheergoivan.fenkins.util.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.springframework.util.CollectionUtils;

public class ValidationUtils {
	private ValidationUtils() {}
	
	private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

	public static <T> ValidationResult validate(T obj) {
		ValidationResult result = new ValidationResult();
		Set<ConstraintViolation<T>> set = VALIDATOR.validate(obj, Default.class);
		if (!CollectionUtils.isEmpty(set)) {
			result.setHasErrors(true);
			Map<String, String> errorMsg = new HashMap<String, String>();
			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
			}
			result.setErrorMsg(errorMsg);
		}
		return result;
	}
}

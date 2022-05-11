package com.se.codingclub.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

	@Override
	public void initialize(ValidFile constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		boolean result = true;

		String contentType = value.getContentType();
		if (!isSupportedContentType(contentType)) {
			result = false;
		}

		return result;
	}

	private boolean isSupportedContentType(String contentType) {
		return contentType.equals("audio/mpeg");
	}
}

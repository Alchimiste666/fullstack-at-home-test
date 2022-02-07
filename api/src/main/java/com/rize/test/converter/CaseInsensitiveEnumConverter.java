package com.rize.test.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rize.test.model.Category;

@Component
public class CaseInsensitiveEnumConverter implements Converter<String, Category> {

	@Override
	public Category convert(String value) {
		return Category.valueOf(value.toUpperCase());
	}
	
}
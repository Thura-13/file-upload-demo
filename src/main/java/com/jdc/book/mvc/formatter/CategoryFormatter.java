package com.jdc.book.mvc.formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jdc.book.root.dto.Category;
import com.jdc.book.root.service.CategoryService;
@Component
public class CategoryFormatter implements Formatter<Category>{
	
	@Autowired
	private CategoryService categoryService;

	@Override
	public String print(Category cat, Locale locale) {
		return Optional.ofNullable(cat).map(c->c.getName()).orElse(null);
	}

	@Override
	public Category parse(String value, Locale locale) throws ParseException {
		
		return Optional.ofNullable(value)
				.filter(s->StringUtils.hasLength(s))
				.map(str -> Integer.parseInt(str))
				.flatMap(id->categoryService.findById(id))
				.orElse(null);
	}

}

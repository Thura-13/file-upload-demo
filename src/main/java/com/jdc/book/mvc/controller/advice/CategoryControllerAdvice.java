package com.jdc.book.mvc.controller.advice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jdc.book.root.dto.Category;
import com.jdc.book.root.service.CategoryService;

@ControllerAdvice
public class CategoryControllerAdvice {
	
	@Autowired
	private CategoryService categoryService;
	
	@ModelAttribute("categoryList")
	public List<Category> getCategoryList(){
		return categoryService.getAll();
	}

}

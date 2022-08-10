package com.jdc.book.mvc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.book.mvc.formatter.CategoryFormatter;
import com.jdc.book.root.dto.Book;
import com.jdc.book.root.service.BookService;

@Controller
@RequestMapping({"/home","/book"})
public class HomeController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String index(
			@RequestParam(required = false) Integer categoryId,
			@RequestParam(required = false) String keyword,
			ModelMap model) {
		List<Book> list =  bookService.search(categoryId,keyword);
		model.put("list", list);
		return "home";
	}
	
	@GetMapping("edit")
	public String edit() {
		return "book-edit";
	}
	
	@PostMapping
	public String save(@Validated @ModelAttribute("book") Book book,BindingResult br,RedirectAttributes rd) {
		
		if(br.hasErrors()) {
			return "book-edit";
		}
		
		var id = bookService.save(book);
		rd.addAttribute("id", id);
		return "redirect:/book/detail";
	}
	
	@GetMapping("detail")
	public String detail() {
		return "book-detail";
	}
	
	@ModelAttribute("book")
	Book book(@RequestParam Optional<Integer> id) {
		return id.flatMap(a->bookService.findById(a)).orElse(new Book());
	}
}

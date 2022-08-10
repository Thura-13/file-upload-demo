package com.jdc.book.root.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.book.root.dto.Book;
import com.jdc.book.root.dto.Category;


@Service
public class FileUploadService {
	
	@Autowired
	private Validator validator;

	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BookService bookService;

	@Transactional
	public String upload(MultipartFile file) throws FileUploadAppException {
		var list = readLines(file);
	 var  categoryBookMap= list.stream().collect(Collectors.groupingBy(b->b.getCategory().getName()));
	 
	 for(var entry : categoryBookMap.entrySet()) {
		 
		Category category = categoryService.getCategoryByName(entry.getKey());
				
		for(var book :entry.getValue()) {

//			Validate category field property in service layer
			var bindingResult = new BeanPropertyBindingResult(book, "target");
			validator.validate(category, bindingResult);
			
			if(bindingResult.hasErrors()) {
				String mes = bindingResult.getAllErrors().stream().map(e->e.getDefaultMessage()).findAny().get();
				throw new FileUploadAppException(mes);
			}
			
			book.setCategory(category);
			
//			Validate book field property in service layer
			var result = new BeanPropertyBindingResult(book, "target");
			validator.validate(book, result);
			
			if(result.hasErrors()) {
				String message = result.getAllErrors().stream().map(a->a.getDefaultMessage()).findAny().get();
				throw new FileUploadAppException(message);
			}
			bookService.save(book);
		}
	 }
	 
	 return "%d Book Upload Successfully!".formatted(list.size());
	}
	
	private List<Book> readLines(MultipartFile file){
		var list = new ArrayList<Book>();
		try (var reader =new BufferedReader(new InputStreamReader(file.getInputStream()))){
			String line = null;
			while(null != (line = reader.readLine())) {
				Book book = readBook(line);
				list.add(book);
			}
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		return list;
	}
	
	private Book readBook(String line) {
		var book = new Book();
		String str[] = line.split("\t");
		if(str.length < 4 && str.length > 5) {
//			throw Exception
		}
		book.setTitle(str[0]);
		book.setAuthor(str[1]);
		book.setCategory(new Category(str[2]));
		book.setPrice(Integer.parseInt(str[3]));
		if(str.length > 4) {
			book.setRemarks(str[4]);
		}
		return book;
	}

}

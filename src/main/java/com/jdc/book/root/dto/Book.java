package com.jdc.book.root.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Book {
	
	private int id;
	@NotBlank(message = "{book.title.notBlank}")
	private String title;
	@NotBlank(message = "{book.author.notBlank}")
	private String author;
	@NotNull(message = "{book.category.notNull}")
	private Category category;
	@Min(value = 3000,message = "{book.price.min}")
	private Integer price;
	private String remarks;
	
	

}

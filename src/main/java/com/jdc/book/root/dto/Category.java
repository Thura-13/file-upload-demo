package com.jdc.book.root.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

	private int id;
	@NotBlank(message = "Enter Category Name")
	private String name;
	
	public Category() {
		// TODO Auto-generated constructor stub
	}
	
	public Category(String name) {
		super();
		this.name = name;
	}
	
	
	
	
}

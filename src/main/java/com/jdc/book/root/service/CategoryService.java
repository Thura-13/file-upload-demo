package com.jdc.book.root.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import com.jdc.book.root.dto.Category;

@Service
public class CategoryService {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired 
	private SimpleJdbcInsert jdbcInsert;
	
	@PostConstruct
	void init() {
		jdbcInsert.setTableName("category");
		jdbcInsert.setGeneratedKeyName("id");
	}
	public List<Category> getAll() {
		return jdbcTemplate.query("select * from category order by name", new BeanPropertyRowMapper<>(Category.class));
	}

	public Optional<Category> findById(int id) {
		return jdbcTemplate.queryForStream(
				"select * from category where id =:id",
				Map.of("id",id),
				new BeanPropertyRowMapper<Category>(Category.class)
				).findFirst();
				}

	public Category getCategoryByName(String name) {
	 var searchResult = 	jdbcTemplate.queryForStream(
				"select * from category where name =:name",
				Map.of("name",name),
				new BeanPropertyRowMapper<Category>(Category.class)
				).findFirst();
	 return searchResult.orElseGet(()->{
		 var id = jdbcInsert.executeAndReturnKeyHolder(Map.of("name",name)).getKey().intValue();
		 return new Category(id,name);
	 });
	}

}

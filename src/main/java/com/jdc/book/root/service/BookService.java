package com.jdc.book.root.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.book.root.dto.Book;
import com.jdc.book.root.dto.row.BookRowMapper;

@Service
public class BookService {

	@Autowired
	private BookRowMapper rowMapper;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private SimpleJdbcInsert jdbcInsert;

	@PostConstruct
	void init() {
		jdbcInsert.setTableName("book");
		jdbcInsert.setGeneratedKeyName("id");
	}

	private static final String SELECT = """
			select b.id,b.title,b.author,
			b.price,b.remarks,c.id category_id,
			c.name category_name from book b join
			category c on c.id = b.category_id where 1 = 1
			""";

	private static final String UPDATE = """
			update book set title =:title,author =:author,price =:price,
			remarks =:remarks,category_id =:category_id where id =:id
			""";

	public List<Book> search(Integer categoryId, String keyword) {

		var sb = new StringBuilder(SELECT);
		var paramMap = new HashMap<String, Object>();

		if (null != categoryId) {
			sb.append(" and c.id =:category_id");
			paramMap.put("category_id", categoryId);
		}

		if (StringUtils.hasLength(keyword)) {
			sb.append("""
					and (lower(b.title) like :keyword or
						 lower(b.author) like :keyword or
						 lower(c.name) like :keyword
						 )""");
			paramMap.put("keyword", keyword.toLowerCase().concat("%"));
		}
		return jdbcTemplate.query(sb.toString(), paramMap, rowMapper);
	}

	public Optional<Book> findById(int id) {
		return jdbcTemplate.queryForStream(SELECT.concat("and b.id = :id"), Map.of("id", id), rowMapper).findFirst();
	}

	public int save(Book book) {

		if (book.getId() == 0) {

			var paramMap = new HashMap<String, Object>();
			paramMap.put("title", book.getTitle());
			paramMap.put("author", book.getAuthor());
			paramMap.put("price", book.getPrice());
			paramMap.put("remarks", book.getRemarks());
			paramMap.put("category_id", book.getCategory().getId());

			return jdbcInsert.executeAndReturnKeyHolder(paramMap).getKey().intValue();
		}

		var paramMap = new HashMap<String, Object>();
		paramMap.put("id", book.getId());
		paramMap.put("title", book.getTitle());
		paramMap.put("author", book.getAuthor());
		paramMap.put("price", book.getPrice());
		paramMap.put("remarks", book.getRemarks());
		paramMap.put("category_id", book.getCategory().getId());

		jdbcTemplate.update(UPDATE, paramMap);

		return book.getId();
	}

}

package com.mongotemplate.mdbspringbook.item;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
public class Book {

	@Id
	private Integer id;
	
	private String name;
	private Integer pages;
	private String author;
	private Double cost;
	
	public Book(Integer id, String name, Integer pages, String author, Double cost) {
		this.id = id;
		this.name = name;
		this.pages = pages;
		this.author = author;
		this.cost = cost;
	}
	
	
}

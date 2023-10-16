package com.mongotemplate.mdbspringbook.operations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Component;
import com.mongotemplate.mdbspringbook.item.*;

@Component
public class BookOperations implements CommandLineRunner{

	@Autowired
	private MongoTemplate mt;
	
	public void run(String... args) throws Exception {

	    mt.save(new Book(500, "Core Java", 200, "Kathy Sierra", 1065.5));
	    mt.save(new Book(501, "JSP & Servlets", 350, "Kathy Sierra", 1749.0));
	 // mt.save(new Book(501, "JSP & Servlets", 350, "Kathy Sierra", 1749.0),"Book"); // save () with collection name 'Book'
	    mt.save(new Book(502, "Spring in Action", 480, "Craig Walls", 940.75));
	    mt.save(new Book(503, "Pro Angular", 260, "Freeman", 1949.25));
	    mt.save(new Book(504, "HTML CSS", 100, "Thomas Powell", 2317.09));
	    mt.save(new Book(505, "Hibernate in Action", 180, "Gavin King", 889.25));
	    mt.save(new Book(506, "Practical MongoDB", 180, "Shakuntala Gupta", 785.0));
	    mt.save(new Book(507, "Pro Spring Boot", 850, "Felipe Gutierrez", 2167.99));
	    mt.save(new Book(508, "Beginning jQuery", 180, "Franklin", 1500.00));
	    mt.save(new Book(509, "Java Design Patterns", 114, "Devendra Singh", 919.99));

	    System.out.println("------All records has been saved successfully-------");
	    
	    //Trova tutti i libri
	    
	    List<Book> list = mt.findAll(Book.class);
	  //List<Book> list = mt.findAll(Book.class,"Book");  //If collection name & the Entity Class Name are different (case-sensitive)
	  //  list.forEach(System.out::println);
	    
	    // Trova un libro in base all'id
	    
	    Book book = mt.findById(504, Book.class);
	  //Book book = mt.findById(501, Book.class,"Book");
	    System.out.println("Libro con id = 504: " + book);
	    
	    //Fetch data base on given conditon (query) and then modify data for given update type
	    
	    Query query = new Query();
	    query.addCriteria(Criteria.where("id").is(501));
	    
	    Update update = new Update();
	    update.set("cost", 1065.25);
	    update.set("name", "Core Java");
	    
	    mt.findAndModify(query, update, Book.class);
	    
	    //Update all the records of the DB that matches given criteria 
	    
	    Query query2 = new Query();
	    query.addCriteria(Criteria.where("pages").lte(180));
	    Update update2 = new Update();
	    update.set("cost", 999.0);
	    mt.updateMulti(query2, update2, Book.class);
	    
	    //Remove data fetched from a given condition
	    
	    Query query3= new Query();
	    query.addCriteria(Criteria.where("cost").is(1749.0));
	    mt.findAndRemove(query3, Book.class);
	    
	    //Remove multiple records which are fetched from a given condition
//	    Query query4 = new Query();
//	    query4.addCriteria(Criteria.where("cost").gte(1000.0));
//	    mt.findAllAndRemove(query4, Book.class);
//	    
	    //upsert = update + insert
	    //when given criteria matches any record, it will update the record
	    //If it doesn't find any record of the given criteria, it will insert a new record
	    
//	    Query query5 = new Query();
//	    query.addCriteria(Criteria.where("id").is(520));
//	    Update update3=new Update();
//	    update.set("cost", 1065.25);
//	    update.set("name", "Core Java");
//	    mt.upsert(query, update, Book.class);
	    
	}
	
}

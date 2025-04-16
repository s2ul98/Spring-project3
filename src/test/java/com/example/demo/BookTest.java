//package com.example.demo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.example.demo.entity.Book;
//import com.example.demo.repository.BookRepository;
//
//public class BookTest {
//
//	@Autowired
//	BookRepository bookRepository;
//	
//	@Test
//	public void 데이터일괄등록() {
//		List<Book> list = new ArrayList<>();
//		Book book1 = new Book(0,"자바프로그래밍입문","한빛출판사",20000); 
//		Book book2 = new Book(0,"스프링부트프로젝트","남가람북스",25000);
//		Book book3 = new Book(0,"실무로 끝내는 PHP","남가람북스",40000);
//		Book book4 = new Book(0,"알고리즘코딩테스트","이지스퍼블리싱",35000);
//		list.add(book1);
//		list.add(book2);
//		list.add(book3);
//		list.add(book4);
//		bookRepository.saveAll(list);
//		
//}

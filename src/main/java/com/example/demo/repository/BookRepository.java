package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query(value = "select * from tbl_book where title = :title", nativeQuery = true) // 3.파라미터 처리한다
	List<Book> get1(@Param("title") String title); // 2.파라미터를 매개변수로 선언한다

	@Query(value = "SELECT * FROM tbl_book WHERE price > :price AND publisher = :publisher", nativeQuery = true)
	List<Book> get2(@Param("price") int price, @Param("publisher") String publisher);

	@Query(value = "SELECT * FROM tbl_book WHERE publisher IN (:publisher1,:publisher2)", nativeQuery = true)
	List<Book> get3(@Param("publisher1") String publisher1, @Param("publisher2") String publisher2);

}

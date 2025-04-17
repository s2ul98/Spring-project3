package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Order;

// Repository란? 테이블에 데이터를 조회, 추가, 수정, 삭제하는 컴포넌트
// Repository 만드는 방법 :
// 1. JapRepository 상속
// 2. entity, pk 타입 작성

// 인터페이스는 인스턴스로 생성할 수 없음

// 리파지토리가 만들어지는 과정
// 1. JpaRepository를 상속받는 인터페이스 생성
// 2. OrderRepository를 상속받는 자식클래스(구현체) 생성
// 3. 자식클래스로 인스턴스를 생성
// 4. 컨테이너에 인스턴스(빈)가 등록됨<자동>
public interface OrderRepository extends JpaRepository<Order, Integer> {
	// 순수SQL
	// 주소지가 '인천'인 데이터 검색
	// select * from tbl_order where ship_address like '인천%';
	@Query(value = "select * from tbl_order where ship_address like :address%", nativeQuery = true)
	List<Order> get1(@Param("address") String address);

	// 주문일이 '7월3일'인 데이터 검색
	// select * from tbl_order where order_date = '2023-07-03';
	@Query(value = "select * from tbl_order where order_date = :orderDate", nativeQuery = true)
	List<Order> get2(@Param("orderDate") LocalDate orderDate);
	
	
	// 주문일로 주문이력을 검색
	@Query(value = "select * from tbl_order where order_date = :orderDate", nativeQuery = true)
	List<Order> get3(@Param("orderDate") LocalDate orderDate);
	
	
	
	
	
	
}

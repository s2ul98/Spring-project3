//package com.example.demo;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.example.demo.entity.Order;
//import com.example.demo.repository.OrderRepository;
//
//@SpringBootTest
//public class OrderRepositoryTest {
//
//	@Autowired // 컨테이너에 있는 빈을 꺼내서 빈을 주입
//	OrderRepository repository;
//
//	@Test
//	public void 리파지토리확인() {
//		System.out.println(repository);
//		
//		// OrderRepository는 JpaRepository로 부터 save, findAll, deleteAll함수를 조작할 
//		repository.save(null);
//		repository.findAll();
//		repository.deleteAll();
//	}
//	
//	@Test
//	public void 데이터추가() {
//		// 데이터 중에서 주문번호는 자동으로 생성됨
//		// 0을 입력하면 빈값으로 처리 됨
//		// 데이터 중에서 고객명, 배송지, 주문일은 직접 입력
//		
//		LocalDate date1 = LocalDate.of(2023, 7, 1);
//		LocalDate date2 = LocalDate.of(2023, 8, 12);
//		LocalDate date3 = LocalDate.of(2023, 9, 1);
//		
//		Order order1 = new Order(0, "또치", date1, "인천 구월동");
//		
//		Order order2 = Order.builder()
//									.customerName("또치")
//									.orderDate(date2)
//									.shipAddress("인천 연수동")
//									.build();
//		
//		Order order3 = Order.builder()
//				.customerName("도우너")
//				.orderDate(date3)
//				.shipAddress("부산 동래동")
//				.build();
//		
//		Order order4 = Order.builder()
//				.customerName("마이콜")
//				.orderDate(date1)
//				.build();
//		
//		Order order5 = Order.builder()
//				.customerName("고길동")
//				.orderDate(date2)
//				.build();
//		
//		// order 테이블에 새로운 데이터를 추가
//		// save함수를 호출하면 insert sql이 생성됨
//		repository.save(order1);
//		repository.save(order2);
//		repository.save(order3);
//		repository.save(order4);
//		repository.save(order5);
//		
//		
//	}
//	
//	@Test
//	public void 데이터단건조회() {
//		
//		// order 테이블에서 no가 1번인 데이터 조회
//		// find : 조회
//		// by :  조건
//		// id : primary key
//		Optional<Order> optional = repository.findById(1);
//		
//		if(optional.isPresent()) {
//			Order order = optional.get();
//			System.out.println(order);
//		}	
//	}
//	@Test
//	public void 데이터수정() {
//		
//		// 1. 1번 데이터 조회
//		Optional<Order> optional = repository.findById(1);
//		
//		// 2. 데이터가 존재하는지 확인
//		if(optional.isPresent()) {
//			// 수정이 필요한 부분만 일부 변경
//			Order order = optional.get();
//			order.setShipAddress("서울 신림동");
//			// 변경된 데이터를 테이블에 업데이트
//			// save 함수 호출 시 no가 1인 데이터가 있으면 update
//			// 없으면 insert
//			repository.save(order);
//		}
//	}
//
//	@Test
//	public void 데이터단건삭제() {
//		// 1번 데이터 삭제
//		// delete : 데이터 삭제
//		// by : 조건
//		// id : primary key
//		repository.delete(1);
//	}
//
//	@Test
//	public void 일괄삭제() {
//		
//		repository.deleteAll(); 
//	}
//}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Memo;

import jakarta.transaction.Transactional;

// 리파지토리 만드는 방법
// "JpaRepository" 인터페아스 상속받기
// 제네릭 타입은 엔티티와 pk 타입을 작성

// JpaRepository를 상속받아 조회, 추가, 수정, 삭제 기능을 물려받음

// 조회는 데이터 영향이 없음
// 하지만 추가나 삭제는 데이터 영향이 있음
// sql 실행한 후에 commit을 해야 db에 결과가 반영됨

// update 또는 delete => commit
@Transactional
public interface MemoRepository extends JpaRepository<Memo, Integer> {

	// 메모의 번호가 10에서 20사이인 데이터를 검색
	// 함수 이름 작성
	// findBy~ : 조회
	// No : 기준 컬럼
	// Between : 연산자
	// Between은 2개의 파라미터를 사용함
	// 파라미터는 함수의 매개변수로 선언
	// 조회함수의 반환값 : Memo 또는 List<Memo>countBy
	// ... where no between ? and ?
	List<Memo> findByNoBetween(int from, int to);
	
	// 10번 아래 메모 검색
	// findBy! : 조회
	// No : 기준 컬럼
	// LessThan : 비교연산자 <
	// ...where no < ?
	List<Memo> findByNoLessThan(int mno);
	
	// 내용이 없는 데이터를 선택
	// findBy : 조회
	// Text : 기준컬럼
	// IsNull : is null 연산자
	List<Memo> findByTextIsNull();
	
	//메모의 번호를 기준으로 역정렬
	// findAll : 전체 조회
	List<Memo> findAllByOrderByNoDesc();
	
	//  deleteMemoBy : 삭제
	// LessThan : 비교연산자 <
	// No : 기준컬럼
	// 예시 : delete from tbl_memo where no < ?
	void deleteMemoByNoLessThan(int mno);
	
	// 어노테이션으로 함수 추가
	// 규칙 : 
	// 함수 이름은 자유롭게 작성
	// 괄호 안에 sql을 직접 작성 (1. jqpl문법 / 2. maraidb에서 사용하는 sql)
	// jqpl 문법 : 
	// table 이름 대신 entity 이름을 사용
	// 컬럼명 대신 변수명을 사용
	
	// 3번 아래 메모를 검색
	// 테이블명 : tbl_memo 엔티티명 : Memo
	// jqpl문법으로 sql 작성하면 실제 데잍베이스에 맞는 sql로 변환됨
	// jqpl 장점 : 개발 중간에 데이터베이스가 oracle로 변경되어도 쿼리를 변경할 필요가 없음
	@Query("select m from Memo m where m.no < :mno")
	List<Memo> get1(@Param("mno") int mno);
	
	// 순수한 sql 장점 : 작성하기 편함
	// 순수한 sql 단점 : 데이터베이스가 변경되면 쿼리를 수정해야됨
	
	// 결론 :
	// 쿼리메소드/어노테이션(jqpl)/어노테이션(순수한sql)
	
	
	/* JPQL 규칙
	 * 1. 테이블 이름대신 엔티티 이름을 사용
	 * 2. 컬럼명 대신 변수명을 사용
	 * 3. 함수 이름은 자유롭게 사용
	 */
	// 내용이 없는 메모를 검색
	// SPQL SQL은 실제 데이터베이스에 맞는 SQL로 변경됨
	@Query("select m from Memo m where text is null")
	List<Memo> get2();
	
	/*
	 * JPQL 규칙
	 * 1. 테이블 이름 대신 엔티티 이름을 사용
	 * 2. 컬럼명 대신 변수명을 사용
	 */
	
	// sql의 파라미터는 함수의 매개변수로 처리
	// 파라미터를 작성하는 방법 : 변수명
	// 10번에서 20번 사이의 메모 검색
	// SELECT * FROM TBL_MEMO WHERE NO BETWEEN 10 AND 20;
	@Query("select m from Memo m where no between :start and :end")
	List<Memo> get3(@Param("start") int start, @Param("end") int end);
	
	
	// 순수한 sql로 작성
	
	// value : sql
	// nativeQuery : 순수 쿼리를 사용하는지 여부
	// 번호를 기준으로 역정렬
	@Query( value = "SELECT * FROM TBL_MEMO ORDER BY NO DESC", nativeQuery = true )
	List<Memo> get4();
	
	
	// 10~20 사이의 메모를 삭제
	@Query( value = "DELETE FROM TBL_MEMO WHERE NO BETWEEN :start AND :end", nativeQuery = true)
	void delete1(@Param("start") int start, @Param("end") int end);
	
	
	// 객체 파라미터
	// 파라미터를 Memo 객체로 전달
	// 객체 파라미터 표기 : :#{}
	// 객체 안에 프로퍼티 표기 => #객체.프로퍼티
	@Modifying
	@Query(value = "UPDATE TBL_MEMO SET text = :#{#memo.text} WHERE NO = :#{#memo.no}", nativeQuery = true)
	int update1(@Param("memo")Memo memo);
	
	
	
	
	
	
	
	
	
	
}





/*
 * 조건 검색 기능을 추가하는 방법
 * 1. 쿼리 메소드
 * 2. 어노테이션 (jqpl 문법)
 * 3. 어노테이션 (순수한 sql)
 * */













// MemoRepository는 ListPagingAndSortingRepository 로부터 
// 페이지 처리 함수를 물려받음

// MemoRepository는 부모로부터 CRUD 기능과 페이지 처리 기능을 물려받음


/*
 *  jpa 기능을 사용하는 방법
 *  1. 엔티티 생성 (테이블 정의)
 *  2. 리파지토리 생성 (테이블 안에 있는 데이터를 조회, 수정, 추가, 삭제 처리)
 *  
 * */

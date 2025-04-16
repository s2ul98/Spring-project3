package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository boardRepository;
	
	@Test
	public void 리파지토리객체를가져왔는지확인() {
		System.out.println("boardRepository = " + boardRepository);
	}
	
	@Test
	public void 데이터등록() {
		Board board1 = new Board(0, "1번글", "내용입니다", null, null);
		boardRepository.save(board1);
		
		Board board2 = Board.builder()
				.title("2번글")
				.content("내용입니다")
				.build();

		boardRepository.save(board2);
	}
	@Test
	public void 데이터단건조회() {
		
		Optional<Board> result = boardRepository.findById(3); 
		
		if (result.isPresent()) {
			Board board = result.get();
			System.out.println(board);
		}
	}
	@Test
	public void 데이터전체조회() {
		List<Board> list = boardRepository.findAll(); 
		
		for (Board board : list) {
			System.out.println(board);
		}
	}
	@Test
	public void 데이터수정() {
		
		Optional<Board> result = boardRepository.findById(3);
		Board board = result.get();
		
		board.setContent("내용수정완");
		
		boardRepository.save(board);
		
//		Board board1 = new Board(1, "1번글", "글이수정되었습니다", null, null);
//		boardRepository.save(board1);
	}
	@Test
	public void 데이터삭제() {
		boardRepository.deleteById(4);
	}

	@Test
	public void 데이터전체삭제() {
		boardRepository.deleteAll();
	}
	
}

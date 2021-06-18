package com.freeflux.sjw.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {

	public static void main(String[] args) {
		//1. Spring Container를 구동
		AbstractApplicationContext container=new GenericXmlApplicationContext("applicationContext.xml");
		
		//2. Spring Container로부터 BoardServiceImpl 객체를 요청(lookup) 한다.
		BoardService boardService=(BoardService) container.getBean("boardService");
		
		//3. 글 등록 기능 테스트
		BoardVO vo=new BoardVO();
		vo.setTitle("테스트용 제목");
		vo.setWriter("테스트용 작성자");
		vo.setContent("테스트용 내용");
		
		boardService.insertBoard(vo);
		
		//4. 글 목록 검색 기능 테스트
		List<BoardVO> boardList=boardService.getBoardList(vo);
		for(BoardVO board : boardList){
			System.out.println(board.toString() );
		}
		
		//5. Spring Container 종료
		container.close();
	}

}

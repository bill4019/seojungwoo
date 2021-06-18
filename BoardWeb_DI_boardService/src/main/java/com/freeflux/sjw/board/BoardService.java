package com.freeflux.sjw.board;

import java.util.List;

/** CRUD에 관련된 메서드(기능 구현) **/
public interface BoardService {

	//글 등록
	void insertBoard(BoardVO vo);
	
	//글 수정
	void updateBoard(BoardVO vo);
	
	//글 삭제
	void deleteBoard(BoardVO vo);
	
	//글 목록
	List<BoardVO> getBoardList(BoardVO vo);
	
	//글 상세
	BoardVO getBoard(BoardVO vo);
}

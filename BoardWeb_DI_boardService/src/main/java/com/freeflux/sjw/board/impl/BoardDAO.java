package com.freeflux.sjw.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.freeflux.sjw.board.BoardVO;
import com.freeflux.sjw.common.JDBCUtil;

/** 테이블에 데이터를 실제로 접근 **/

@Repository("boardDAO")
public class BoardDAO {
	
	//JDBC 관련 멤버변수
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	//SQL 명령어 상수
	private final String BOARD_INSERT="insert into board (seq, title, writer, content) values((select nvl(max(seq), 0)+1 from board), ?, ?, ?)";
	private final String BOARD_UPDATE="update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE="delete board where seq=?";
	private final String BOARD_GET="select * from board where seq=?";
	private final String BOARD_LIST="select * from board order by seq desc";
	
	public BoardDAO() {
	}
	
	/** CRUD 기능 상세 구현 메서드들  **/
	//글 등록
	public void insertBoard(BoardVO vo){
		System.out.println("jdbc로 insertBoard() 기능 처리");
		try{
			this.conn=JDBCUtil.getConnection();
			this.pstmt=conn.prepareStatement(BOARD_INSERT);
			this.pstmt.setString(1, vo.getTitle());
			this.pstmt.setString(2, vo.getWriter());
			this.pstmt.setString(3, vo.getContent());
			this.pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	//글 수정
	public void updateBoard(BoardVO vo){
		System.out.println("jdbc로 updateBoard() 기능 처리");
		try{
			this.conn=JDBCUtil.getConnection();
			this.pstmt=conn.prepareStatement(BOARD_UPDATE);
			this.pstmt.setString(1, vo.getTitle());
			this.pstmt.setString(2, vo.getWriter());
			this.pstmt.setInt(3, vo.getSeq());
			this.pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(pstmt, conn);
		}
	}
		
	//글 삭제
	public void deleteBoard(BoardVO vo){
		System.out.println("jdbc로 deleteBoard() 기능 처리");
		try{
			this.conn=JDBCUtil.getConnection();
			this.pstmt=conn.prepareStatement(BOARD_DELETE);
			this.pstmt.setInt(1, vo.getSeq());
			this.pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(pstmt, conn);
		}
	}

	//글 상세 조회
	public BoardVO getBoard(BoardVO vo){
		System.out.println("jdbc로 getBoard() 기능 처리");
		BoardVO board=null;
		try{
			this.conn=JDBCUtil.getConnection();
			this.pstmt=conn.prepareStatement(BOARD_GET);
			this.pstmt.setInt(1, vo.getSeq());
			this.pstmt.executeUpdate();
			if(rs.next()){
				board=new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(rs, pstmt, conn);
		}
		return board;
	}
	
	//글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("jdbc로 getBoardList() 기능 처리");
		List<BoardVO> boardList=new ArrayList<BoardVO>();
		try{
			this.conn=JDBCUtil.getConnection();
			this.pstmt=conn.prepareStatement(BOARD_LIST);
			this.rs=pstmt.executeQuery();
			if(rs.next()){
				BoardVO board=new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				boardList.add(board);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(rs, pstmt, conn);
		}
		return boardList;
	}
}

package com.freeflux.sjw.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {

	public JDBCUtil() {
	}
	
	/** 1. 데이터베이스 접속 객체 반환 메서드 **/
	public static Connection getConnection(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
			
			return DriverManager.getConnection(url, "spring", "1234");
			
		}catch(Exception e){
			System.err.println("getConnection() ERR" + e.getMessage());
			
		}
		return null;
	}
	
	/** 각 자원 해제 메서드 **/
	//1. PreparedStatement / Connection 해제
	public static void close(PreparedStatement pstmt, Connection conn){
		if(pstmt != null){				//pstmt가 null이 아닐 경우
			try{
				if(pstmt.isClosed()){	//pstmt가 닫혀(해제되어)있지 않을 경우
					pstmt.close();		//pstmt를 닫기(자원 해제)
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				pstmt=null;				//pstmt를 초기화
			}
		}
		
		if(conn != null){
			try{
				if(!conn.isClosed()){
					conn.close();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				conn=null;
			}
		}
	}
	//2. ResetSet / PreparedStatement / Connection 해제
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
		if(rs != null){
			try{
				if(!rs.isClosed()){
					rs.close();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				rs=null;
			}
		}
		
		if(pstmt != null){
			try{
				if(pstmt.isClosed()){	
					pstmt.close();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				pstmt=null;
			}
		}
		
		if(conn != null){
			try{
				if(!conn.isClosed()){
					conn.close();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}finally{
				conn=null;
			}
		}
	}
}

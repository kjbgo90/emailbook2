package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.EmailBookVo;

public class EmailBookDao {
	
	public List<EmailBookVo> getList() {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmailBookVo> list = new ArrayList<EmailBookVo>(); 
		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@192.168.56.101:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "select no, "
		    				+ "last_name, "
		    				+ "first_name, "
		    				+ "email "
		    			+ "from emailbook ";
		    pstmt = conn.prepareStatement(query);
		    rs = pstmt.executeQuery();
		    // 4.결과처리
		    while(rs.next()) {
		    	int no = rs.getInt("no");
		    	String lastName = rs.getString("last_name");
		    	String firstName = rs.getString("first_name");
		    	String email = rs.getString("email");
		    	
		    	EmailBookVo vo = new EmailBookVo(no, lastName, firstName, email);
		    	list.add(vo);
		    }
		    
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}

		return list;
	}

	public int insert(EmailBookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@192.168.56.101:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "INSERT into emailbook " + 
		    				"    (no, " + 
		    				"    last_name, " + 
		    				"    first_name, " + 
		    				"    email) " + 
		    				"values ( " + 
		    				"    seq_emailbook_no.nextval, " + 
		    				"    ?, " + 
		    				"    ?, " + 
		    				"    ?) ";
		    
		    
		    pstmt = conn.prepareStatement(query);
		    pstmt.setString(1, vo.getLastName());
		    pstmt.setString(2, vo.getFirstName());
		    pstmt.setString(3, vo.getEmail());
		    count = pstmt.executeUpdate();
		    
		    // 4.결과처리
		    
		    
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {              
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}
		
		return count;
	}
}



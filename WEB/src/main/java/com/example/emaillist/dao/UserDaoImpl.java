package com.example.emaillist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.emaillist.vo.UserVo;

public class UserDaoImpl implements UserDao {

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";

			conn = DriverManager.getConnection(dburl, "C##BITUSER", "bituser");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return conn;
	}

	// 로그인용
	@Override
	public UserVo getUserByEmailAndPassword(String email, String password) {
		UserVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "SELECT no,name,email,gender FROM users " + " WHERE email=? AND password=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {// 결과 레코드가 있다.
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String emailStr = rs.getString(3);
				String gender = rs.getString(4);
				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setEmail(emailStr);
				vo.setGender(gender);
//				vo.setPassword(password);
				System.out.println("User Found:" + vo);

			} else {
				System.err.println("User Not Found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		return vo;
	}

	@Override
	public int insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount=0;

		try {
			conn = getConnection();
			String sql = "INSERT INTO users (no,name,password,email,gender) "
					+" VALUES(seq_users_pk.NEXTVAL,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getGender());

			insertedCount = pstmt.executeUpdate();

		
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		return insertedCount;
	}

}

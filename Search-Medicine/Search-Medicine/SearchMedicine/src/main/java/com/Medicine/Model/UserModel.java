package com.Medicine.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Medicine.Bean.UserBean;
import com.Medicine.Exception.ApplicationException;
import com.Medicine.Exception.DuplicateRecordException;
import com.Medicine.Utility.JDBCDataSource;

public class UserModel {
	
	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM USER");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public UserBean findByLogin(String login) throws Exception {

		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER WHERE email=?");
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setEmail(rs.getString(2));
				bean.setEnable(rs.getBoolean(3));
				bean.setPassword(rs.getString(4));
				bean.setRoleid(rs.getLong(5));
				bean.setUserName(rs.getString(6));
				bean.setRoleName(rs.getString(7));
			}
			rs.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return bean;
	}

	public UserBean findByPk(long pk) throws Exception {

		UserBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER WHERE id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setEmail(rs.getString(2));
				bean.setEnable(rs.getBoolean(3));
				bean.setPassword(rs.getString(4));
				bean.setRoleid(rs.getLong(5));
				bean.setUserName(rs.getString(6));
				bean.setRoleName(rs.getString(7));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	public UserBean Authenticate(String Email, String Password) throws Exception {
		UserBean bean = null;
		Connection conn = null;

		conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER WHERE EMAIL =? AND PASSWORD =?");
		ps.setString(1, Email);
		ps.setString(2, Password);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setEmail(rs.getString(2));
			bean.setEnable(rs.getBoolean(3));
			bean.setPassword(rs.getString(4));
			bean.setRoleid(rs.getLong(5));
			bean.setUserName(rs.getString(6));
			bean.setRoleName(rs.getString(7));
		}
		return bean;
	}

	public long add(UserBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;

		UserModel model = new UserModel();
		UserBean existbean = findByLogin(bean.getEmail());
		if (existbean != null) {
			throw new DuplicateRecordException("Login Id already exite");
		}

		try {

			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO USER VALUES(?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getEmail());
			ps.setBoolean(3, bean.isEnable());
			ps.setString(4, bean.getPassword());
			System.out.println("11111111111111111");
			ps.setLong(5, bean.getRoleid());
			System.out.println("222222222222222");
			ps.setString(6, bean.getUserName());
			ps.setString(7, bean.getRoleName());
			System.out.println("ookkkkk");
			ps.executeUpdate();
			System.out.println("nooooookkkk");
			conn.commit();
			ps.close();
		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + e.getMessage());
			}
		} finally {
			JDBCDataSource.closeconnection(conn);
		}
		return pk;
	}

	public List list() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from USER");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			UserBean bean = new UserBean();
			bean.setId(rs.getLong(1));
			bean.setEmail(rs.getString(2));
			bean.setEnable(rs.getBoolean(3));
			bean.setPassword(rs.getString(4));
			bean.setRoleid(rs.getLong(5));
			bean.setUserName(rs.getString(6));
			bean.setRoleName(rs.getString(7));
			list.add(bean);
		}
		return list;
	}

	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from USER where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}



	public long Update(UserBean bean) {

		System.out.println("in model update method");
		int pk = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"update user set email=?, enabled=?, password=?,roleid=?,username=?,rolename=? where id=?");
			ps.setString(1, bean.getEmail());
			ps.setBoolean(2, bean.isEnable());
			ps.setString(3, bean.getPassword());
			ps.setLong(4, bean.getRoleid());
			ps.setString(5, bean.getUserName());
			ps.setString(6, bean.getRoleName());
			ps.setLong(7, bean.getId());
			 ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}

	
	public List search(UserBean bean) throws Exception {
		StringBuffer sql = new StringBuffer("SELECT * from USER WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getUserName() != null && bean.getUserName().length() > 0) {
				sql.append(" AND username like '" + bean.getUserName() + "%'");
			}
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setEmail(rs.getString(2));
				bean.setEnable(rs.getBoolean(3));
				bean.setPassword(rs.getString(4));
				bean.setRoleid(rs.getLong(5));
				bean.setUserName(rs.getString(6));
				bean.setRoleName(rs.getString(7));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {

		} finally {
			JDBCDataSource.closeconnection(conn);
		}

		return list;

	}
	
}

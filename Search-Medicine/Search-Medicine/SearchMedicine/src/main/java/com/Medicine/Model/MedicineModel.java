package com.Medicine.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Medicine.Bean.MedicineBean;
import com.Medicine.Bean.OrderMedicineBean;
import com.Medicine.Bean.UserBean;
import com.Medicine.Exception.ApplicationException;
import com.Medicine.Utility.JDBCDataSource;

public class MedicineModel {

	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM medicine");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}
	
	public MedicineBean findByPk(long pk) throws Exception {

		MedicineBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM medicine WHERE id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new MedicineBean();
				bean.setId(rs.getLong(1));
				bean.setCompanyName(rs.getString(2));
				bean.setMedicineName(rs.getString(3));
				bean.setQuantity(rs.getLong(4));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	/*
	 * public OrderMedicineBean OrderfindByPk(long pk) throws Exception {
	 * 
	 * OrderMedicineBean bean = null; Connection conn = null; try { conn =
	 * JDBCDataSource.getConnection(); } catch (SQLException e) {
	 * e.printStackTrace(); } try { PreparedStatement ps =
	 * conn.prepareStatement("SELECT * FROM medicine WHERE id=?"); ps.setLong(1,
	 * pk); ResultSet rs = ps.executeQuery(); while (rs.next()) { bean = new
	 * OrderMedicineBean(); bean.setId(rs.getLong(1));
	 * bean.setMedicineName(rs.getString(2)); bean.setMedicineid(rs.getLong(3));
	 * bean.setQuantity(rs.getLong(4)); bean.setUserid(rs.getLong(5)); } rs.close();
	 * } catch (SQLException e) { e.printStackTrace(); } return bean; }
	 */
	public long add(MedicineBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;

		try {

			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO medicine VALUES(?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getCompanyName());
			ps.setString(3, bean.getMedicineName());
			ps.setLong(4, bean.getQuantity());
			ps.executeUpdate();
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
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from medicine");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			MedicineBean bean = new MedicineBean();
			bean.setId(rs.getLong(1));
			bean.setCompanyName(rs.getString(2));
			bean.setMedicineName(rs.getString(3));
			bean.setQuantity(rs.getLong(4));
			list.add(bean);
		}
		return list;
	}
	
	public List Showlist(long userid) throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		try {
			Connection conn = null;
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * from userorder where userid=?");
			ps.setLong(1, userid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MedicineBean bean = new MedicineBean();
				bean.setId(rs.getLong(1));
				bean.setMedicineName(rs.getString(2));
				bean.setMedicineid(rs.getLong(3));
				bean.setQuantity(rs.getLong(4));
				bean.setUserid(rs.getLong(5));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List orderlist() throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * from userorder");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			MedicineBean bean = new MedicineBean();
			bean.setId(rs.getLong(1));
			bean.setMedicineName(rs.getString(2));
			bean.setMedicineid(rs.getLong(3));
			bean.setQuantity(rs.getLong(4));
			bean.setUserid(rs.getLong(5));
			list.add(bean);
		}
		return list;
	}
	

	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from medicine where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public long Update(MedicineBean bean) {

		System.out.println("in model update method");
		int pk = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"update medicine set company_name=?, medicine_name=?, quantity=? where id=?");
			ps.setString(1, bean.getCompanyName());
			ps.setString(2, bean.getMedicineName());
			ps.setLong(3, bean.getQuantity());
			ps.setLong(4, bean.getId());
			 ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pk;
	}
	
	public List search(MedicineBean bean) throws Exception {
		StringBuffer sql = new StringBuffer("SELECT * from medicine WHERE 1=1");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getMedicineName() != null && bean.getMedicineName().length() > 0) {
				sql.append(" AND medicine_name like '" + bean.getMedicineName() + "%'");
			}
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MedicineBean();
				bean.setId(rs.getLong(1));
				bean.setCompanyName(rs.getString(2));
				bean.setMedicineName(rs.getString(3));
				bean.setQuantity(rs.getLong(4));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
e.printStackTrace();
		} finally {
			JDBCDataSource.closeconnection(conn);
		}

		return list;

	}
	
	public Integer ordernextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM userorder");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}
	
	public long useradd(MedicineBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = ordernextpk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO userorder VALUES(?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getMedicineName());
			ps.setLong(3, bean.getMedicineid());
			ps.setLong(4, bean.getQuantity());
			ps.setLong(5, bean.getUserid());
			ps.executeUpdate();
			conn.commit();
			ps.close();
		} catch (Exception e) {
               e.printStackTrace();
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
	
	
}

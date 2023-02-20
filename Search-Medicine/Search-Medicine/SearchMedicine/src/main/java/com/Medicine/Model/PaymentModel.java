package com.Medicine.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Medicine.Bean.PaymentBean;
import com.Medicine.Exception.ApplicationException;
import com.Medicine.Utility.JDBCDataSource;

public class PaymentModel {
	
	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM payment");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}
	
	public long add(PaymentBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO payment VALUES(?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setLong(2, bean.getAmount());
			ps.setString(3, bean.getCardNumber());
			ps.setLong(4, bean.getOrderid());
			ps.setLong(5, bean.getUserid());
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
		PreparedStatement pstmt = conn.prepareStatement(

				"SELECT * from payment");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			PaymentBean bean = new PaymentBean();
			bean.setId(rs.getLong(1));
			bean.setAmount(rs.getLong(2));
			bean.setCardNumber(rs.getString(3));
			bean.setOrderid(rs.getLong(4));
			bean.setUserid(rs.getLong(5));
			list.add(bean);
		}
		return list;
	}
	
	public List Showlist(long userid) throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * from payment where userid=?");
		ps.setLong(1, userid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			PaymentBean bean = new PaymentBean();
			bean.setId(rs.getLong(1));
			bean.setAmount(rs.getLong(2));
			bean.setCardNumber(rs.getString(3));
			bean.setOrderid(rs.getLong(4));
			bean.setUserid(rs.getLong(5));
			list.add(bean);
		}
		return list;
	}
	

}

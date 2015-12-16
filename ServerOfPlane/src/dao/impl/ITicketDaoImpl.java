/**
 * @Author yeye
 * @date 2015-12-11  下午9:19:05
 */
package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.LogUtil;

import dao.ITicketDao;
import db.ConnectionOracle;
import entity.ContactPerson;
import entity.Flight;
import entity.Passenger;
import entity.Ticket;

/**
 * @author yeye
 * 
 */
public class ITicketDaoImpl implements ITicketDao {

	@Override
	public boolean insertTicket(Passenger p, ContactPerson c, int uId,
			String flightId,String insurance,String type) {
		boolean flag = false;
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into pticket values (order_id.nextval,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getCardNumber());
			pstmt.setString(3, type);
			pstmt.setString(4, insurance);
			pstmt.setString(5, c.getName());
			pstmt.setString(6, c.getPhoneNumber());
			pstmt.setString(7, c.getEmail());
			pstmt.setInt(8, uId);
			pstmt.setString(9, flightId);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				flag = true;
			}
		} catch (SQLException e) {
			LogUtil.e(e);
		} finally {
			close(conn, pstmt, null);
		}

		return flag;
	}

	public boolean checkOrderId(long orderId) {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		String sql = "select * from pticket where order_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, orderId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			LogUtil.e(e);
		} finally {
			close(conn, pstmt, rs);
		}
		return flag;
	}

	@Override
	public List<Ticket> queryTicketContent(String username) {
		List<Ticket> ticket = new ArrayList<Ticket>();
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from PTICKET where u_id=(select u_id from ticketuser where user_name=?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String orderId = rs.getString(1);
				String pName = rs.getString(2);
				String pCardNumber = rs.getString(3);
				String pTicketType = rs.getString(4);
				String pInsurance = rs.getString(5);
				String cName = rs.getString(6);
				String cPhone = rs.getString(7);
				String cEmail = rs.getString(8);
				String UId = rs.getString(9);
				String flightId = rs.getString(10);

				Ticket t = new Ticket(orderId, pName, pCardNumber, pTicketType,
						pInsurance, cName, cPhone, cEmail, UId, flightId);

				ticket.add(t);
			}
		} catch (Exception e) {
			LogUtil.e(e);
		} finally {
			close(conn, pstmt, null);
		}
		return ticket;
	}

	public void clear() {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from pticket";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
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
			LogUtil.e(e);
		}
	}

	/**
	 * ----------Clear test data. BE CAREFUL!--------
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ITicketDaoImpl().clear();
	}
}

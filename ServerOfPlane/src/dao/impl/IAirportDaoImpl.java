package dao.impl;

import dao.IAirportDao;
import entity.Airport;
import entity.Flight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.LogUtil;
import db.ConnectionOracle;

public class IAirportDaoImpl implements IAirportDao {

	public long queryAirportId(Airport a) {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long aId = 0;

		String sql = "select * from Airport where a_location=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getaLocation());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String tmp = rs.getString(4);
				aId = Long.parseLong(tmp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.e(e);
		} finally {
			close(conn, pstmt, null);
		}
		return aId;

	}

	public long queryAirportPhone(Airport a) {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		long aPhone = 0;

		String sql = "select * from Airport where a_location=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getaLocation());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String tmp = rs.getString(2);
				aPhone = Long.parseLong(tmp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.e(e);
		} finally {
			close(conn, pstmt, null);
		}
		return aPhone;

	}

	public String queryAirportName(Airport a) {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String aName = null;

		String sql = "select * from Airport where a_location=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, a.getaLocation());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				aName = rs.getString(1);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.e(e);
		} finally {
			close(conn, pstmt, null);
		}
		return aName;

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

}

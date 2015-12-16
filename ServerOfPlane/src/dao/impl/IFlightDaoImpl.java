package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.LogUtil;
import dao.IFlightDao;
import db.ConnectionOracle;
import entity.Flight;

public class IFlightDaoImpl implements IFlightDao {

	public List<Flight> selectFlight(String origin, String dest,
			String flightDate) {
		List<Flight> flight = new ArrayList<Flight>();
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select flight_id, origin_id, dest_id, to_char(start_time,'yyyy-mm-dd hh24:mi'), to_char(arrive_time,'yyyy-mm-dd hh24:mi'), fare "
				+ "from Flight f "
				+ "where "
				+ "f.origin_id=(select a_id from airport where a_location = ?) "
				+ "and "
				+ "f.dest_id=(select a_id from airport where a_location = ?) "
				+ "and " + "to_date(?,'yyyy-mm-dd')<f.start_time";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, origin);
			pstmt.setString(2, dest);
			pstmt.setString(3, flightDate);
			rs = pstmt.executeQuery();
			System.out.println("yeyeey");
			System.out.println(origin);
			while (rs.next()) {
				String fId = rs.getString(1);
				int originId = rs.getInt(2);
				int destId = rs.getInt(3);
				String flightStartTime = rs.getString(4);
				String flightArriveTime = rs.getString(5);
				int flightFare = rs.getInt(6);

				Flight f1 = new Flight(fId, originId, destId, flightStartTime,
						flightArriveTime, flightFare);
				flight.add(f1);
			}
		} catch (Exception e) {
			LogUtil.e(e);
		} finally {
			close(conn, pstmt, null);
		}
		return flight;

	}

	public String queryFlightId(String origin, String dest, String flightDate) {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String fId = null;

		String sql = "select * from Flight f  where f.origin_id=(select a_id from airport where a_location = ?) and f.dest_id=(select a_id from airport where a_location = ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, origin);
			pstmt.setString(2, dest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fId = rs.getString(1);
			}
		} catch (Exception e) {

			LogUtil.e(e);
		} finally {
			close(conn, pstmt, null);
		}
		return fId;
	}

	public String queryFlightStartTime(String origin, String dest,
			String flightDate) {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String flightStartTime = null;

		String sql = "select * from Flight f  where f.origin_id=(select a_id from airport where a_location = ?) and f.dest_id=(select a_id from airport where a_location = ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, origin);
			pstmt.setString(2, dest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flightStartTime = rs.getString(4);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.e(e);
		} finally {
			close(conn, pstmt, null);
		}
		return flightStartTime;
	}

	public String queryFlightArriveTime(String origin, String dest,
			String flightDate) {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String flightArriveTime = null;

		String sql = "select * from Flight f where f.origin_id=(select a_id from airport where a_location = ?) and f.dest_id=(select a_id from airport where a_location = ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, origin);
			pstmt.setString(2, dest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flightArriveTime = rs.getString(5);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.e(e);
		} finally {
			close(conn, pstmt, null);
		}
		return flightArriveTime;
	}

	public int queryFlightFare(String origin, String dest, String flightDate) {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int flightFare = 0;

		String sql = "select * from Flight f where f.origin_id=(select a_id from airport where a_location = ?)? and f.dest_id=(select a_id from airport where a_location = ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, origin);
			pstmt.setString(2, dest);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flightFare = rs.getInt(6);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.e(e);
		} finally {
			close(conn, pstmt, null);
		}
		return flightFare;
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

	/*
	 * 测试代码块
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IFlightDaoImpl i2 = new IFlightDaoImpl();
		// Flight f = new
		// Flight((int)i1.queryAirportId(a),(int)i1.queryAirportId(b));
		/*
		 * Flight f = new Flight("北京", "广州"); System.out.println("航班ID:\t" +
		 * i2.queryFlightId(f)); System.out.println("startTime:" +
		 * i2.queryFlightStartTime(f)); System.out.println("arriveTime:" +
		 * i2.queryFlightArriveTime(f)); System.out.println("fare:\t" +
		 * i2.queryFlightFare(f));
		 */

	}

}

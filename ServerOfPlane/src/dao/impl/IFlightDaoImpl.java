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

	
	public List<Flight> selectFlight(Flight f){
		List<Flight> flight = new ArrayList<Flight>();
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		String sql = "select * from Flight where origin_id=? and dest_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, f.getOriginId());
			pstmt.setInt(2, f.getDestId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String fId = rs.getString(1);
				int originId = rs.getInt(2);
				int destId = rs.getInt(3);
				String flightStartTime = rs.getString(4);
				String flightArriveTime = rs.getString(5);
				int flightFare = rs.getInt(6);
				Flight f1 = new Flight(fId,originId,destId,flightStartTime,flightArriveTime,flightFare);
				flight.add(f1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.e(e);
		} finally {
			close(conn, pstmt, null);
		}
		return flight;
		
	}
	public String queryFlightId(Flight f) {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String fId = null;

		String sql = "select * from Flight where origin_id=? and dest_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, f.getOriginId());
			pstmt.setInt(2, f.getDestId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fId = rs.getString(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.e(e);
		} finally {
			close(conn, pstmt, null);
		}
		return fId;
	}

	public String queryFlightStartTime(Flight f) {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String flightStartTime = null;

		String sql = "select * from Flight where origin_id=? and dest_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, f.getOriginId());
			pstmt.setInt(2, f.getDestId());
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

	public String queryFlightArriveTime(Flight f) {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String flightArriveTime = null;

		String sql = "select * from Flight where origin_id=? and dest_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, f.getOriginId());
			pstmt.setInt(2, f.getDestId());
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

	public int queryFlightFare(Flight f) {
		Connection conn = new ConnectionOracle().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int flightFare = 0;

		String sql = "select * from Flight where origin_id=? and dest_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, f.getOriginId());
			pstmt.setInt(2, f.getDestId());
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
	 * ²âÊÔ´úÂë¿é
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IFlightDaoImpl i2 = new IFlightDaoImpl();
		//Flight f = new Flight((int)i1.queryAirportId(a),(int)i1.queryAirportId(b));
		Flight f = new Flight(100100,100102);
		System.out.println("º½°àID:\t"+i2.queryFlightId(f));
		System.out.println("startTime:"+i2.queryFlightStartTime(f));
		System.out.println("arriveTime:"+i2.queryFlightArriveTime(f));
		System.out.println("fare:\t"+i2.queryFlightFare(f));
		
	}

}

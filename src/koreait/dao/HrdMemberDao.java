package koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day1.OracleConnectUtil;
import koreait.vo.HrdMember;

public class HrdMemberDao {
	private static HrdMemberDao dao = new HrdMemberDao();
	private HrdMemberDao() {}
	public static HrdMemberDao getInstance() {
		return dao;
	}
	
	// 회원정보수정 : phone,address,city 컬럼만 수정하고 조건은 기본키 값입니다.
	public void update(HrdMember vo) {
		Connection conn = OracleConnectUtil.connect();
		String sql = "UPDATE MEMBER_TBL_02 "
					+ "SET phone=?, address=?, city=? "
					+ "WHERE custno = ?";

		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPhone());
			pstmt.setString(2, vo.getAddress());
			pstmt.setString(3, vo.getCity());
			pstmt.setInt(4, vo.getCustNo());
			pstmt.execute();
			conn.commit();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("SQL 실행오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
	}
	
	public HrdMember selectOne(int custno) {	// 기본키 값으로 조회
		Connection conn = OracleConnectUtil.connect();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql="select * from member_tbl_02 where custno = ?";
		HrdMember result = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,custno);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				result = new HrdMember(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDate(5),
						rs.getString(6),
						rs.getString(7));
			pstmt.close();
			
			} catch (SQLException e) {
			System.out.println("HrdMemberDao selectOne 오류 : " + e.getMessage());
		}
		
		OracleConnectUtil.close(conn);
		return result;
	}
	
	
	public List<HrdMember> selectAll(){
		Connection conn = OracleConnectUtil.connect();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT * FROM MEMBER_TBL_02";
		List<HrdMember> list = new ArrayList<HrdMember>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new HrdMember(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getDate(5),
						rs.getString(6),
						rs.getString(7)));
			}
			
		} catch (SQLException e) {
			System.out.println("HrdMemberDao selectAll 오류 : " + e.getMessage());
		}
		return list;
		
	}
	
	
}

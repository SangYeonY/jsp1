package day2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day1.OracleConnectUtil;
import day2.vo.Member;
import day2.vo.SaleSum;


public class HrdProblemDao {
	
	private static HrdProblemDao dao = new HrdProblemDao(); 
	private HrdProblemDao() { }
	public static HrdProblemDao getCustomDao() {
		return dao;
	}
	
	public void insert(Member vo) {
		Connection conn = OracleConnectUtil.connect();
		String sql = ""; 
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
		
			
			pstmt.execute();
			System.out.println("회원 등록이 완료 되었습니다.");
			pstmt.close();
		}catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " +  e.getMessage());
		}
		OracleConnectUtil.close(conn);	
	}
	
	public void update(Member vo) {
		Connection conn = OracleConnectUtil.connect();
		String sql = ""; 
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
		
			
			pstmt.execute();
			System.out.println("회원 수정이 완료 되었습니다.");
			pstmt.close();
		}catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " +  e.getMessage());
		}
		OracleConnectUtil.close(conn);	
	}
	
	public List<Member> selectMemberAll() {
		Connection conn = OracleConnectUtil.connect();
		String sql="";
		PreparedStatement pstmt = null;
		ResultSet rs= null;		
		List<Member> members = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();	//select 
			
			while(rs.next()) {
			}
			pstmt.close();
			
		}catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		
		}
		
		OracleConnectUtil.close(conn);
		return members;
	}
	
	public Member selectMember(int cust_no) {
		
		Connection conn = OracleConnectUtil.connect();
		ResultSet rs = null;
		String sql="";
		Member member=null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
			}
			
		}catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		
		}
		return member;
	}
	
	
	public List<SaleSum> selectSale(){
		Connection conn = OracleConnectUtil.connect();
		String sql="SELECT mt.CUSTNO , CUSTNAME ," + 
				" decode(grade,'A','VIP','B','일반','C','직원') AS agrade," + 
				" asum FROM MEMBER_TBL_02 mt ," + 
				" (SELECT custno, sum(price) AS asum FROM MONEY_TBL_02 mt " + 
				" GROUP BY CUSTNO" + 
				" ORDER BY asum desc) sale" + 
				" WHERE mt.CUSTNO = sale.custno";
		PreparedStatement pstmt = null;
		ResultSet rs= null;		
		List<SaleSum> sales = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();	//select 
			
			while(rs.next()) {
				SaleSum temp = new SaleSum(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				sales.add(temp);
			}
			pstmt.close();
			
		}catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		
		}
		
		OracleConnectUtil.close(conn);
		return sales;
	}
}
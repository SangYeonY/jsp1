package koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day1.OracleConnectUtil;
import koreait.vo.SaleSum;


public class HrdSaleDao {

	private static HrdSaleDao dao = new HrdSaleDao();
	private HrdSaleDao() {  }
	public static HrdSaleDao getInstance() {
		return dao;
	}
	
	
	public List<SaleSum> selectSale(){
		Connection conn = OracleConnectUtil.connect();
		String sql="SELECT mt.CUSTNO , CUSTNAME ," + 
				" decode(grade,'A','VIP','B','일반','C','직원') AS agrade," + 
				" asum FROM MEMBER_TBL_02 mt ," + 
				" (SELECT custno, sum(price) AS asum FROM MONEY_TBL_02 mt " + 
				" GROUP BY CUSTNO" + 
				" ORDER BY asum desc) sale" + 
				" WHERE mt.CUSTNO = sale.custno ORDER BY asum desc";
		PreparedStatement pstmt = null;
		ResultSet rs= null;		
		List<SaleSum> sales = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();	//select 
			
			while(rs.next()) {
				SaleSum temp = new SaleSum(rs.getInt(1), 
						rs.getString(2), rs.getString(3), rs.getInt(4));
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
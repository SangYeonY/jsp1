package day1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day1.OracleConnectUtil;
import day1.Custom;
import day1.Product;

public class ProductDao {
   // 변수명 한번에 바꾸기 : alt + shift + r
	
	// 지금 Dao 역할 클래스는 전역변수(클래스의 프로퍼티) 가 없습니다.
	// -> 객체를 new 로 새로 만들어서 메모리 할당받아도 저장할 데이터가 없습니다.
	// -> 그래서 메소드만 실행하도록 싱글톤 패턴으로 객체를 생성하도록 구현했습니다.
   private static ProductDao pdao= new ProductDao();
   private ProductDao() {}
   public static ProductDao getProductDao() {
      return pdao;
   }
   // selectOne 쿼리
   public static Product selectOne(String pcode) {;
   Connection conn = OracleConnectUtil.connect();
   PreparedStatement pstmt = null;
   String sql = "select * from tbl_product# where pcode = ?";
   ResultSet rs = null;
   Product vo = null;
   
   try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, pcode);
      rs = pstmt.executeQuery();
      
      if(rs.next()) {      // 조회 결과 1개 행 있으면
         vo = new Product();
         vo.setPcode(rs.getString(1));
         vo.setCategory(rs.getString(2));
         vo.setPname(rs.getString(3));
         vo.setPrice(rs.getInt(4));
      }
      
   }catch(SQLException e) {
      System.out.println("SQL 실행 오류 : " + e.getMessage());
   }
   
   OracleConnectUtil.close(conn);
   return vo;
}
   //insert 쿼리
   public void insert(Product vo) {
      Connection conn = OracleConnectUtil.connect();
      String sql="INSERT INTO TBL_PRODUCT# " + 
            "(PCODE, CATEGORY, PNAME, PRICE)" + 
   " VALUES(?, ?, ?, ?)";
   
   try {
      PreparedStatement pstmt = conn.prepareStatement(sql);
   
      //? 에 해당하는 값을 전달합니다. ?순서를 1부터 시작하는 index로 지정합니다.
      pstmt.setString(1, vo.getPcode());
      pstmt.setString(2, vo.getCategory());
      pstmt.setString(3, vo.getPname());
      pstmt.setInt(4, vo.getPrice());
      
      pstmt.execute();      //sql 실행 메소드
      pstmt.close();
   }catch (SQLException e) {
      System.out.println("SQL 실행 오류 : " + e.getMessage());
   }   
   
   OracleConnectUtil.close(conn);
}
   // update 쿼리
   public void update(Product vo) {   
      Connection conn = OracleConnectUtil.connect();
      PreparedStatement pstmt = null;
      String sql = "UPDATE TBL_PRODUCT# SET PNAME and PRICE and CATEGORY = ? WHERE PCODE = ?";

      
      try {
         pstmt = conn.prepareStatement(sql);

      
         pstmt.setString(1,vo.getPname());      
         pstmt.setInt(2,vo.getPrice());    
         pstmt.setString(3,vo.getCategory());    
         pstmt.setString(24,vo.getPcode());    
         
         pstmt.execute();
         pstmt.close();
         
   } catch (SQLException e) {
      System.out.println("SQL 실행 오류 : " + e.getMessage());
   }
   System.out.println("정상적으로 email이 변경 되었습니다.");
   OracleConnectUtil.close(conn);
   }

   // delete 쿼리 실행
   public void delete(String custom_id) {
      // custom_id 는 이 메소드를 호출할 때 값이 전달됩니다.
      Connection conn = OracleConnectUtil.connect();
      PreparedStatement pstmt = null;
      String sql = "DELETE FROM TBL_CUSTOM# WHERE CUSTOM_ID=?";
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, custom_id);
         pstmt.execute();
         pstmt.close();
      } catch (SQLException e) {
         System.out.println("SQL 실행 오류 : " + e.getMessage());
      }
      OracleConnectUtil.close(conn);
   }
   
   //select All 쿼리(조건없음)
   public List<Product> selectAll() {
      Connection conn = OracleConnectUtil.connect();
      String sql="select * from tbl_product#";
      PreparedStatement pstmt = null;
      ResultSet rs= null;      
      List<Product> product = new ArrayList<>();
      try {
         pstmt = conn.prepareStatement(sql);
         rs=pstmt.executeQuery();   //select 
         
         while(rs.next()) {
            product.add(new Product(rs.getString(1), // 방법1) 커스텀 생성자
                     rs.getString(2), 
                     rs.getString(3), 
                     rs.getInt(4))); 
            
         }
         
         pstmt.close();
         
      }catch (SQLException e) {
         System.out.println("SQL 실행 오류 : " + e.getMessage());
      
      }
      
      OracleConnectUtil.close(conn);
      
      return product;
   }
   // selectCartegory 
   public static Product selectCategory(String category) {;
   Connection conn = OracleConnectUtil.connect();
   PreparedStatement pstmt = null;
   String sql = "select category from tbl_product# where pcode = ?";
   ResultSet rs = null;
   Product vo = null;
   
   try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, category);
      rs = pstmt.executeQuery();
      
      if(rs.next()) {      // 조회 결과 1개 행 있으면
         vo = new Product();
         vo.setPcode(rs.getString(1));
      }
      
   }catch(SQLException e) {
      System.out.println("SQL 실행 오류 : " + e.getMessage());
   }
   
   OracleConnectUtil.close(conn);
   return vo;
}
}

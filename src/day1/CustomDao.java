package day1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day1.OracleConnectUtil;
import day1.Custom;

public class CustomDao {
	
	// 필드값이 없는 클래스 입니다. -> new 연산으로 객체를 생성했을때 서로 다른 필드값을 갖는 것이 없습니다.
	// -> static 키워드로 실행 가능한 메소드입니다. static 은 메모리의 공유영역입니다.
	// -> jdbc 에서 dao 클래스는 static 으로 하지 않습니다. -> 싱글턴(singleton) 객체로 사용합니다.
	
	private static CustomDao customDao = new CustomDao();
	private CustomDao() {}
	public static CustomDao getCustomDao() {
		return customDao;
	}
	
	
	
	
	// select 쿼리 (기본키 컬럼으로 조회하고 결과 반환)
		public static Custom selectOne(String custom_id) {
			Connection conn = OracleConnectUtil.connect();
			PreparedStatement pstmt = null;
			String sql = "select * from tbl_custom# where custom_id = ?";
			// 기본키컬럼 조회결과는 0또는 1개 행입니다.
			ResultSet rs = null;	
			Custom vo = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, custom_id);
				rs = pstmt.executeQuery();		
				if(rs.next()) {		// 조회 결과 1개 행 있으면
				vo = new Custom();
				vo.setCustom_id(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setAge(rs.getInt(4));
				vo.setReg_date(rs.getDate(5));
				} // 없으면 (else) null 반환
				pstmt.close();
				
				
				
			}catch(SQLException e) {
				System.out.println("SQL 실행 오류 : " + e.getMessage());
			}
			
			OracleConnectUtil.close(conn);
			return vo;
		}
	// insert 쿼리
	public void insert(Custom vo) {
		Connection conn = OracleConnectUtil.connect();
		System.out.println("main메소드 확인용 : " + conn);
		
		String sql = "INSERT INTO TBL_CUSTOM#" + 
				"(CUSTOM_ID, NAME, EMAIL, AGE, REG_DATE)" + 
		"VALUES(?, ?, ?, ?, sysdate)" ;	// 값 부분에 ? -> 해당값은 execute전에 대입
		
		
//		String id = sc.nextLine();
//		String name = sc.nextLine();
//		String email = sc.nextLine();
//		int age = Integer.parseInt(sc.nextLine());
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// ? 에 해당하는 값을 전달합니다. ? 순서를 1부터 시작하는 index로 지정합니다.
			pstmt.setString(1, vo.getCustom_id());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setInt(4, vo.getAge());
			
			
			
			pstmt.execute();		
			pstmt.close();
			
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
	}
	
	//update 쿼리 실행
//	public void update(String custom_id, String email) {	// 방법 1)
		public void update(Custom vo) {	// 방법 2)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "UPDATE TBL_CUSTOM# SET email = ? WHERE CUSTOM_ID = ?";
//		String custom_id = null;
//		String email = null;
		Connection conn = OracleConnectUtil.connect();
		
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, email);			방법 1 	
//			pstmt.setString(2, custom_id);		방법 1
			
			// 방법2) 수정할 컬럼이 많을때 매개변수를 모두 나열하는 것은 비효율적이므로 객체로 받아옵니다.
			pstmt.setString(1, vo.getEmail());		//방법2
			pstmt.setString(2, vo.getCustom_id());	// 방법2
			
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		System.out.println("email 변경 완료.");
		OracleConnectUtil.close(conn);
	}

	//delete 쿼리 실행
	public void delete(String custom_id) {		
		// custom_id 는 이 메소드를 호출할 때 값이 전달됩니다. 
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM TBL_CUSTOM# WHERE CUSTOM_ID=?";
//		String custom_id = null;
		Connection conn = OracleConnectUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custom_id);
			pstmt.execute();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		System.out.println("정상적으로 회원 탈퇴되었습니다.");
		OracleConnectUtil.close(conn);
	}
	
	
	// selectOne() 메소드와 쿼리는 동일하지만 리턴형식이 다른 예
	public boolean idCheck(String id) {
		Connection conn = OracleConnectUtil.connect();
		boolean result = false;
		ResultSet rs = null;
		String sql = "select * from tbl_custom# where custom_id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);	// 쿼리 실행에 필요한 매개변수(?) 전달합니다.
			rs = pstmt.executeQuery();		// select
		if(!rs.next()) {
			result = true;
		}
		}catch(SQLException e) {
			System.out.println("SQL 실행오류 :" + e.getMessage());
		}
		
		return result;	// 중복되면 false, 중복없으면 true(rs.next() 가 false일때)
	}
	
	// select 쿼리(조건없음)
	public List<Custom> selectAll() {
		Connection conn = OracleConnectUtil.connect();
		String sql = "select * from tbl_custom#";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Custom> customs = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				customs.add(new Custom(rs.getString(1),		
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getDate(5)));
			}
			pstmt.close();
			
		}catch(SQLException e){
			System.out.println("SQL 실행 오류 : " + e.getMessage());
		}
		OracleConnectUtil.close(conn);
		return customs;
	}// 메소드 끝
	
	
	
	
	
	
}

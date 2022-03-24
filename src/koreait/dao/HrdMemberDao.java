package koreait.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		
		public int getNextSeq() {
			Connection conn=OracleConnectUtil.connect();
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			String sql="select member_seq.nextval from dual";
			int result = 0;
			
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next())
					result = rs.getInt(1);
				pstmt.close();
			}catch (SQLException e) {
				System.out.println("HrdMemberDao next seq 오류 : " + e.getMessage());
			}
			
			OracleConnectUtil.close(conn);
			return result;
		}
		
		public void insert (HrdMember vo) {
			Connection conn = OracleConnectUtil.connect();
//			String sql = "INSERT INTO MEMBER_TBL_02 (CUSTNO, CUSTNAME, PHONE, ADDRESS, "
//					+ "JOINDATE, GRADE, CITY) "
//					+ "VALUES(member_seq.nextval,?,?,?,sysdate,?,?)";
			
			String sql = "INSERT INTO MEMBER_TBL_02 (CUSTNO, CUSTNAME, PHONE, ADDRESS, "
					+ "JOINDATE, GRADE, CITY) "
					+ "VALUES(?,?,?,?,sysdate,?,?)";
			
			try {
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(2, vo.getCustName());
				pstmt.setString(3, vo.getPhone());
				pstmt.setString(4, vo.getAddress());
				pstmt.setString(5, vo.getGrade());
				pstmt.setString(6, vo.getCity());
				pstmt.setInt(1, vo.getCustNo());
				
				pstmt.execute();
				System.out.println("회원 등록이 완료되었습니다.");
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("HrdMemberDao insert 오류 : " + e.getMessage());
			}
			
			OracleConnectUtil.close(conn);
		}
		
		//회원정보수정 : phone,address,city 컬럼만 수정하고 조건은 기본키 값입니다.  -> 줌 dm 채팅으로 이 부분 코드 보내주세요.
		public void update(HrdMember vo) {
			Connection conn = OracleConnectUtil.connect();
			PreparedStatement pstmt = null;
			String sql = "UPDATE MEMBER_TBL_02 SET phone=?, address=?, city=? "
					+ "WHERE custno = ?";
			try {
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getPhone());
				pstmt.setString(2, vo.getAddress());
				pstmt.setString(3, vo.getCity());
				pstmt.setInt(4, vo.getCustNo());
				pstmt.execute();
				pstmt.close();

			} catch (SQLException e) {
				System.out.println("HrdMemberDao update 오류 :" + e.getMessage());
			}
				OracleConnectUtil.close(conn);
		}
		
		public HrdMember selectOne(int custno) {   //기본키 값으로 조회
			Connection conn=OracleConnectUtil.connect();
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			String sql="select * from member_tbl_02 where custno = ?";
			HrdMember result = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, custno);
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
			}catch (SQLException e) {
				System.out.println("HrdMemberDao selectOne 오류 : " + e.getMessage());
			}
			
			OracleConnectUtil.close(conn);
			return result;
		}
		
		
		public List<HrdMember> selectAll(){
			Connection conn = OracleConnectUtil.connect();
			ResultSet rs =null;
			PreparedStatement pstmt=null;
			String sql = "select * from member_tbl_02";
			List<HrdMember> list = new ArrayList<HrdMember>();
			try {
				pstmt = conn.prepareStatement(sql);
				//매개변수 값이 필요없는 sql : set메소드 없음.
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					//테이블 조회된 컬럼을 자바 객체로 생성해서 저장.(매핑)
					list.add(new HrdMember(rs.getInt(1), 
							rs.getString(2), 
							rs.getString(3), 
							rs.getString(4), 
							rs.getDate(5), 
							rs.getString(6), 
							rs.getString(7)));
				}
				
			}catch (SQLException e) {
				System.out.println("HrdMemberDao selectAll 오류 : " + e.getMessage());
			}
			OracleConnectUtil.close(conn);
			
			return list;
		}
		
		
		
		
		
}
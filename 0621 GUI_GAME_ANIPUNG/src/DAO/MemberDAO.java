package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.MemberDTO;

public class MemberDAO {
	private static MemberDAO daoinstance = null;

	public static MemberDAO getInatance() {
		if (daoinstance == null) {
			daoinstance = new MemberDAO();
		}
		return daoinstance;
	}
	private MemberDAO() {}
	
	
	private Connection getConnection() {
		
		Connection conn=null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",
					"system", "1111");//localhost=자기 자신의 주소, 1521=port번호 
			
		} catch (Exception e) {
			
			e.getStackTrace();
			System.out.println("Connection Faile"+e.toString());
			
		}
		
		return conn;
	}
	
	public void insert(MemberDTO dto) {
		
		Connection conn = getConnection();
		PreparedStatement ppst = null;
		
		try {
			
			ppst=conn.prepareStatement("insert into member values(?,?)");
			ppst.setString(1,dto.getName());
			ppst.setInt(2,dto.getScore());
			
			ppst.executeUpdate();
			
		} catch (Exception e) {
			
			System.out.println("SQL error :"+e);
			
		} finally {
			
			try {
				if (conn!=null) conn.close();
				if (ppst!=null) ppst.close();
			} catch (Exception e) {
				System.out.println("connection close error :"+e);
			}
		}
	}
public ArrayList<MemberDTO> getList() {
		
		Connection conn = getConnection();
		PreparedStatement ppst=null;
		ResultSet rs = null;
		
		ArrayList<MemberDTO> dtoList = null;
		
		try {
			
			ppst=conn.prepareStatement("select * from member");
			rs=ppst.executeQuery();
			
			if (rs.next()) {
				
				dtoList = new ArrayList<MemberDTO>();
				do {
					
					MemberDTO dto = new MemberDTO();
					dto.setName(rs.getString("name"));
					dto.setScore(rs.getInt("score"));
					
					dtoList.add(dto);
					
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("SQL error :"+e);
		} finally {
			try {
				if (conn!=null) conn.close();
				if (ppst!=null) ppst.close();
				if (rs!=null)	rs.close();
			} catch (Exception e) {
				System.out.println("connection close error :"+e);
			}
		}
		return dtoList;
	}
}

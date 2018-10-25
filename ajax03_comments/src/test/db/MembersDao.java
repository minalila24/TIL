package test.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import test.db.DBConnection;
import test.db.MembersVo;

public class MembersDao {
	
	public int del(int num){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			con = DBConnection.getConn();
			String sql= "delete members where num =?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1,num);
			return pstmt.executeUpdate(); 
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			return -1;
		}
		
	}
	
	public ArrayList<MembersVo> serch(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = DBConnection.getConn();
			String sql = "select *from members where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			ArrayList<MembersVo> list=new ArrayList<>();
			
			while(rs.next()) {
				
				int num1 =rs.getInt("num");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String addr=rs.getString("addr");
				Date regdate = rs.getDate("regdate");
				
				MembersVo vo = new MembersVo(num1,name,phone,addr,regdate);
				list.add(vo);
				
			}
			return list;
			
			
			
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}	
		
	}
	
	
	
	
	public ArrayList<MembersVo> list(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConn();
			String sql="select * from members";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<MembersVo> list=new ArrayList<>();
			while(rs.next()) {
				int num=rs.getInt("num");
				String name=rs.getString("name");
				String phone=rs.getString("phone");
				String addr=rs.getString("addr");
				Date regdate=rs.getDate("regdate");
				MembersVo vo=new MembersVo(num, name, phone, addr, regdate);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			DBConnection.closeConn(rs, pstmt, con);
		}	
	}
	
	public int insert(MembersVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBConnection.getConn();
			String sql="insert into members values(mem_seq.nextval,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getName());
			pstmt.setString(2,vo.getPhone());
			pstmt.setString(3,vo.getAddr());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			DBConnection.closeConn(null, pstmt, con);
		}
	}
	
	
	/*public int edit(MembersVo vo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			con = DBConnection.getConn();
			String sql="update members set name=?,phone=?,regdate=? where num=?";
			pstmt= con.prepareStatement(sql);
			
			//pstmt.setString(1,vo.);
			
			
			
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			
			
			
		}
		
	}
	*/
	
	
}







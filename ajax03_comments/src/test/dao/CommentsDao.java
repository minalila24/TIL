package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import test.db.DBConnection;
import test.vo.CommentsVo;

public class CommentsDao {
	
	private static CommentsDao instance=new CommentsDao();
	private CommentsDao() {}
	public static CommentsDao getInstance() {
		return instance;
	}
	
	
	public int delete(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBConnection.getConn();
		String sql = "delete comments where num=?";
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,num);
			return pstmt.executeUpdate();
			
			
		}catch(SQLException se){
			
			System.out.println(se.getMessage());
			return -1;
		}finally {
			
			DBConnection.closeConn(null,pstmt,con);
			
		}


		
	}
	
	
	
	public int insert(CommentsVo vo) {
		
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DBConnection.getConn();
		String sql = "insert into comments values(comments_seq.nextval,?,?)";
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getComments());
			return pstmt.executeUpdate();
			
			
		}catch(SQLException se){
			
			System.out.println(se.getMessage());
			return -1;
		}finally {
			
			DBConnection.closeConn(null,pstmt,con);
			
		}
		
	}
	
	public ArrayList<CommentsVo> list(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		con=DBConnection.getConn();
		try {
			String sql="select * from comments order by num desc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<CommentsVo> list=new ArrayList<>();
			while(rs.next()) {
				int num=rs.getInt("num");
				String id=rs.getString("id");
				String comments=rs.getString("comments");
				CommentsVo vo=new CommentsVo(num, id, comments);
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
		
	

}

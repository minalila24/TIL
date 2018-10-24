<%@page import="java.sql.SQLException"%>
<%@page import="test.db.DBConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String email=request.getParameter("email");
	String phone=request.getParameter("phone");
	String id="";
	boolean find=false;
	
	
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try{
		con=DBConnection.getConn();
		String sql="select * from myusers where email=? and phone=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,email);
		pstmt.setString(2,phone);
		rs=pstmt.executeQuery();
		
	
		while(rs.next()){
			System.out.println("id있다"+id);
			id=rs.getString("id1");
			find=true;
			
		}
		
		
	}catch(SQLException se){
		System.out.println(se.getMessage());
	}finally{
		DBConnection.closeConn(rs, pstmt, con);
	}
	
	
	if(find == true){
		
%>
	<h1>회원님의 아이디는 <%=id %> 입니다.</h1>

<% 		

	}else{
		
	%>
		<h1>조회된 정보가 없습니다.</h1>
	
	<%
		
	}		
	%>
<img src="../images/1.png">










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
	boolean using=false;
	
	
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
			using=true;
			
		}
		
		
	}catch(SQLException se){
		System.out.println(se.getMessage());
	}finally{
		DBConnection.closeConn(rs, pstmt, con);
	}
	
	//응답을 XML로 하기
	response.setContentType("text/xml;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.print("<?xml version='1.0' encoding='UTF-8'?>");
	pw.print("<result>");
	if(using==true){
		
		pw.print("<code>"+id+"</code>");
	}else{
		pw.print("<code>false</code>");
	}
	pw.print("</result>");
	pw.close();
%>












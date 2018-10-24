<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num1 = Integer.parseInt(request.getParameter("num1"));
	int num2 = Integer.parseInt(request.getParameter("num2"));
	int num3 = 0;
	String oper = request.getParameter("oper");
	if(oper.equals("1")){
		num3=num1+num2;
	}else if(oper.equals("2")){
		num3=num1-num2;
	}else if(oper.equals("3")){
		num3=num1*num2;
	}else if(oper.equals("4")){
		num3=num1/num2;
	}
	response.setContentType("text/xml;charset=utf-8");
	PrintWriter pw = response.getWriter();
	pw.print("<?xml version='1.0' encoding='UTF-8'?>");
	pw.print("<result>");
	pw.print("<code>"+num3+"</code>");
	pw.print("</result>");
	pw.close();

%>

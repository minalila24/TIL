package test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.dao.CommentsDao;
import test.vo.CommentsVo;

@WebServlet("/comments.do")
public class CommentsController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd!=null && cmd.equals("insert")) {
			insert(request,response);
		}else if(cmd!=null && cmd.equals("list")) {
			list(request,response);
		}else if(cmd!=null && cmd.equals("delete")) {
			
			delete(request,response);
		}
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CommentsDao dao = CommentsDao.getInstance();
		String dnum = request.getParameter("num");
		int n= dao.delete(Integer.parseInt(dnum));
		
		//2.����� XML�� �����ϱ�
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version='1.0' encoding='UTF-8'?>");
		pw.println("<result>");
		
		if(n>0) {
			pw.println("<code>success</code>");
			
			
		}else {
			
			pw.println("<code>fail</code>");
		}
		
		pw.print("</result>");
		pw.close();
		
	
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentsDao dao=CommentsDao.getInstance();
		ArrayList<CommentsVo> list=dao.list();
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version='1.0' encoding='UTF-8'?>");
		pw.println("<result>");
		for(CommentsVo vo:list) {
			pw.println("<comm>");
			pw.println("<num>" + vo.getNum() +"</num>");
			pw.println("<id>"+ vo.getId() + "</id>");
			pw.println("<comments>" + vo.getComments() +"</comments>");
			pw.println("</comm>");
		}		
		pw.println("</result>");
		pw.close();
	}
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. �Ķ���ͷ� ���޵� ���� DB�� �����ϱ�
		String id=request.getParameter("id");
		String comments=request.getParameter("comments");
		CommentsVo vo=new CommentsVo(0, id, comments);
		//dao�� ���ؼ� vo�� �����غ�����.
		CommentsDao dao=CommentsDao.getInstance(); //�̱��� Dao�� �̿뤾��
		int n=dao.insert(vo);
		//2. ����� XML�� �����ϱ�
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version='1.0' encoding='UTF-8'?>");
		pw.println("<result>");
		if(n>0) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}
		pw.println("</result>");
		pw.close();
	}
	
}

































package com.servlet;

import Tessocr.Train;
import java.io.File;
import java.io.IOException;
import net.sourceforge.tess4j.*;  
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//response.setContentType("text/html;charset=UTF-8");
		
		File imageFile1=(File)request.getAttribute("File");//取出File对象
		//File imageFile1 = new File("e:\\wubiaoge3.JPG");
		Train t = new Train();
		//File imageFile1 = new File("e:\\wubiaoge3.JPG");
		
		String path1=this.getServletContext().getRealPath("/WEB-INF/xinziku/tessdata");
		//System.out.println("####################################################");
		//System.out.println("转为JSON对象:");
		t.recognize(imageFile1,path1);//调用Train识别模块，返回值是识别的结果，结果为字符串数组类型。
		//把字符数串组转为字符串
		String[] str=t.recognize(imageFile1,path1);
		StringBuffer sd = new StringBuffer();
		for(int i = 0; i < str.length; i++){
		 sd. append(str[i]);
		}
		String iMessage = sd.toString();
		System.out.print(iMessage);
		request.setAttribute("message", iMessage);
		request.getRequestDispatcher("/upload-message.jsp").forward(request, response);//转发到upload-message.jsp页面，显示识别的结果
		
		
		
		//System.out.println(t.recognize(imageFile1,path1));
		//System.out.println(path1);
		//response.getWriter().write("success!");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

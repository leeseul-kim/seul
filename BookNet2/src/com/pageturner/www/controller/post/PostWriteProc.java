package com.pageturner.www.controller.post;

import javax.servlet.http.*;
import javax.swing.JOptionPane;

import com.pageturner.www.controller.*;
import com.pageturner.www.dao.*;
import com.pageturner.www.util.*;
import com.pageturner.www.vo.*;

public class PostWriteProc implements PageController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/main/main.cls";
		req.setAttribute("isRedirect", true);
		/*
		 	글 작성 버튼을 눌렀던 페이지로 redirect 시켜주어야한다.
		 */
		
		//세션에 저장되어있는 id 값 불러오기
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("SID");
		
		if(id == null || id.length() == 0) {
			//재로그인하게 하기 
			view = "/member/login.cls";
		}
		
		//파라미터 가져오기 
		String sbno = req.getParameter("bno");
		String body = req.getParameter("body");
		String seno = req.getParameter("eno");
		int bno = 0;
		int eno = 0;
		
		try {
			bno = Integer.parseInt(sbno);
			eno = Integer.parseInt(seno);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//데이터베이스 작업하기 
		PostsDAO dao = new PostsDAO();
		
		int cnt = dao.addPost(id, bno, body, eno);
		
		if(cnt != 1) {
			//게시글을 업로드 하지 못함  
			//창을 띄워 알려주기 
			JOptionPane.showMessageDialog(null, "게시글 업로드에 실패하였습니다.");
		}
				
		return view;
	}

}

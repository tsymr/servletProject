package com.study.web;

import com.study.entity.Member;
import com.study.service.MemberService;
import com.study.service.impl.MemberServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MemberServlet extends BasicServlet {

    private MemberService memberService = new MemberServiceImpl();

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Member member = new Member(null, username, password, email);
        boolean register = memberService.register(member);
        if (register) {
            request.getRequestDispatcher("/views/member/register_ok.html").forward(request, response);
        } else {
            request.getRequestDispatcher("/views/member/register_fail.html").forward(request, response);
        }
    }


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Member member = memberService.login(username, password);
        if (member == null) {
            request.getRequestDispatcher("/views/member/login.html").forward(request, response);
        } else {
            request.getSession().setAttribute("member", member);
            if ("admin".equals(member.getUsername())) {
                request.getRequestDispatcher("/views/member/manage_menu.html").forward(request, response);
            } else {
                request.getRequestDispatcher("/views/member/login_ok.html").forward(request, response);
            }
        }

    }
}

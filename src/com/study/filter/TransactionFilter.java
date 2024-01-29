package com.study.filter;

import com.study.utils.JDBCUtilsByDruid;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       try {
           filterChain.doFilter(servletRequest, servletResponse);
           System.out.println("commit");
           JDBCUtilsByDruid.commit();
       }catch (Exception e){
           System.out.println("rollback");
           JDBCUtilsByDruid.rollback();
           throw new RuntimeException(e);
       }
    }

    @Override
    public void destroy() {

    }
}

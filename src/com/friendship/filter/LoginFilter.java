package com.friendship.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.friendship.model.Manager;


/**
 * 过滤后台未登录用户
 * @author Administrator
 */
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		//拿到登录对象
		Manager m = new Manager();
		m = (Manager)session.getAttribute("admin");
		//获取请求地址
		String url[] = req.getRequestURI().split("/");
		//登录页面和登录请求操作不用过滤
		if(url[url.length-1].startsWith("login")){
			chain.doFilter(req, resp);
			return;
		}
		//判断是否已经登录，若是没有登录返回登录页面
		if(m==null||m.equals("")){
			resp.sendRedirect("home/login.jsp");
		}else{
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
}

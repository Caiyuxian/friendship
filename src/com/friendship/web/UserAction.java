package com.friendship.web;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.friendship.model.User;
import com.friendship.service.UserService;

@InjectName("userAction")
@IocBean
@At("/base")
public class UserAction {
	
	private UserService userService;

	/**
	 * 退出登录
	 * @param session
	 */
	@At
	@Ok("redirect:/base/list.nut?currentPage=1")
	public void logout(HttpSession session){
		session.removeAttribute("user");
	}
	
	/**
	 * 检验登录的用户名密码是否正确
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@At
	@Ok("json")
	public String login(@Param("username") String username,
			@Param("password") String password,HttpSession session){
		List<User> userlist = userService.alluser();
		for(User u:userlist){
			if(u.getUsername().equals(username)|u.getEmail().equals(username)){
				if(u.getPassword().equals(password)){
					session.setAttribute("user", u);
					return "2";
				}else{
					return "0";
				}
			}
		}
		return "1";
	}
	/**
	 * 跳转到登录页面
	 */
	@At
	@Ok("jsp:jsp.login")
	public void tologin(){
		
	}
	
	/**
	 * 用户注册
	 * @param username
	 * @param phone
	 * @param email
	 * @param password
	 */
	@At
	@Ok("jsp:jsp.login")
	public void register(@Param("username") String username,@Param("phone") String phone,
			@Param("email") String email,@Param("password") String password){
		User u = new User();
		u.setUsername(username);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(password);
		userService.dao().insert(u);
	}
}

package com.friendship.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.friendship.model.User;
import com.friendship.service.UserService;
import com.friendship.util.MD5;

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
	 * @param username	用户名
	 * @param password	密码
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
				if(u.getPassword().equals(MD5.toMD5(password))){
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
	 * @param username	用户名
	 * @param phone		手机号码
	 * @param email		邮箱
	 * @param password	密码
	 */
	@At
	@Ok("jsp:jsp.login")
	public void register(@Param("username") String username,@Param("phone") String phone,
			@Param("email") String email,@Param("password") String password){
		User u = new User();
		u.setUsername(username);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(MD5.toMD5(password));
		userService.dao().insert(u);
	}
	/**
	 * 修改联系信息和邮箱信息
	 * @param id	用户ID	
	 * @param phone	联系方式
	 * @param email	邮箱
	 */
	@At
	@Ok("redirect:/base/myAct.nut")
	public void modifyInfo(@Param("userid")int id,@Param("phone")String phone,@Param("email")String email){
		User u = userService.fetch(id);
		u.setEmail(email);
		u.setPhone(phone);
		userService.dao().update(u);
	}
	/**
	 * 修改密码
	 * @param id	用户ID
	 * @param oldPass	旧密码
	 * @param newPass	新密码
	 * @param resp		response对象
	 * @throws IOException
	 */
	@At
	@Ok("json")
	public void modifyPass(@Param("userid")int id,@Param("oldPass")String oldPass,
			@Param("newPass")String newPass,HttpServletResponse resp) throws IOException{
		User u = userService.fetch(id);
		if(!MD5.toMD5(oldPass).equals(u.getPassword())){
			resp.getWriter().write('0');
		}else{
			u.setPassword(MD5.toMD5(newPass));
			userService.dao().update(u);
			resp.getWriter().write('1');
		}
	}
}

package com.friendship.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.friendship.model.Manager;
import com.friendship.service.ManagerService;
import com.friendship.util.MD5;

@InjectName
@IocBean
@At("/admin")
public class ManagerAdminAction {

	private ManagerService managerService;
	/**
	 * 后台管理员登录
	 * @param name
	 * @param password
	 * @param req
	 */
	@At
	@Ok("redirect:/admin/home/user_index.html")
	@Fail("redirect:/admin/home/login.jsp")
	public void login(@Param("username")String name,@Param("password") String password,
			HttpSession session){
		List<Manager> userlist = managerService.getAll();
		for(Manager u:userlist){
			if(u.getName().equals(name)&&u.getPassword().equals(MD5.toMD5(password))){
				System.out.println("登录成功");
				session.setAttribute("admin", u);
				session.removeAttribute("error");
			}else{
				session.setAttribute("error", "用户名或密码错误");
				throw new RuntimeException("用户名或密码错误！");
			}
		}
	} 
	/**
	 * 推出登录
	 * @param session
	 */
	@At
	@Ok("redirect:/admin/home/login.jsp")
	public void logout(HttpSession session){
		session.removeAttribute("admin");
	}
	/**
	 * 修改密码
	 * @param id
	 * @param oldPass
	 * @param newPass
	 * @return
	 */
	@At
	@Ok("json")
	public int changePass(@Param("id")int id,
			@Param("oldpass")String oldPass,@Param("newpass")String newPass){
		System.out.println("--------------------");
		System.out.println(id);
		System.out.println(oldPass);
		System.out.println(newPass);
		System.out.println("--------------------");
		Manager m = managerService.fetch(id);
		if(!MD5.toMD5(oldPass).equals(m.getPassword())){
			return 0;
		}else{
			m.setPassword(MD5.toMD5(newPass));
			managerService.dao().update(m);
			return 1;
		}
	}
}

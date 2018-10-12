package com.qsh.shopping.web.controller;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qsh.shopping.model.User;
import com.qsh.shopping.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * 分页显示所有用户信息
	 * @param request
	 * @param writer
	 */
	@RequestMapping(value = "/list_user", method = RequestMethod.GET)
	public void listUser(HttpServletRequest request, PrintWriter writer){
		String startStr = request.getParameter("start");
		String limitStr = request.getParameter("limit");

		int start = Integer.parseInt(startStr);
		int limit = Integer.parseInt(limitStr);
		List<User> users = userService.getUsers(start, start + limit);
		
		int len = userService.getUsers().size();
		String preStr = "{totalCount:" + len + ",rows:[";
		String cenStr = "";
		int i = 0;
		String douhao = ",";
		for(User user : users){
			i++;
			cenStr += "{id:'" + user.getId() + "'," +
			"name:'" + user.getName() + "'," +
			"password:'" + user.getPassword() + "'," +
			"phone:'" + user.getPhone() + "'," +
			"QQ:'" + user.getQQ() + "'," +
			"addr:'" + user.getAddr() + "'," +
			"regDate:'" + user.getRegDate() + "'," +
			"IP:'" + user.getIP() + "'," +
			"email:'" + user.getEmail() + "'}";
			if(i < len){
				cenStr += douhao;
			}
		}
		String endStr = "]}";
		String resultStr = preStr + cenStr + endStr;
		writer.write(resultStr);
	}
	
	@RequestMapping(value = "/add_user", method = RequestMethod.POST)
	public void addUser(HttpServletRequest request, PrintWriter writer){
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setPhone(Long.parseLong(request.getParameter("phone")));
		user.setAddr(request.getParameter("addr"));
		user.setQQ(Long.parseLong(request.getParameter("QQ")));
		user.setIP(request.getRemoteAddr());
		user.setRegDate(new Timestamp(System.currentTimeMillis()));
		if(null != userService.register(user)){
			writer.write("{success:true,msg:'保存成功'}");
		}else{
			writer.write("{success:false,msg:'保存失败'}");
		}
	}
	
	@RequestMapping(value = "/update_user", method = RequestMethod.POST)
	public void updateUser(HttpServletRequest request, User user, PrintWriter writer){
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		user.setPhone(Long.parseLong(request.getParameter("phone")));
		user.setAddr(request.getParameter("addr"));
		user.setQQ(Long.parseLong(request.getParameter("QQ")));
		user.setIP(request.getRemoteAddr());
		user.setRegDate(new Timestamp(System.currentTimeMillis()));
		if(userService.modify(user)){
			writer.write("{success:true,msg:'修改成功'}");
		}else{
			writer.write("{success:false,msg:'修改失败'}");
		}
	}
	
	@RequestMapping(value = "/remove_user", method = RequestMethod.POST)
	public void deleteUser(HttpServletRequest request, User user, PrintWriter writer){
		String ids = request.getParameter("ids").replaceAll("\"", "");
		String[] arrIds = ids.split(",");
		int[] intIds = new int[arrIds.length];
		int i = 0;
		for(String strId : arrIds){
			intIds[i++] = Integer.parseInt(strId);
		}
		if(userService.remove(intIds)){
			writer.write("{success:true,msg:'删除成功'}");
		}else{
			writer.write("{success:false,msg:'删除失败'}");
		}
	}
}

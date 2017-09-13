package com.gym.controller;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.pojo.User;
import com.gym.util.QrcodeUtil;

@Controller
public class LonginController {
	@RequestMapping(value = "/login")
	public String login(User user,String validatecode,Model model){
		//当前对象
		Subject subject = SecurityUtils.getSubject();
		String code =(String)subject.getSession().getAttribute("CODE");
		String password ="";
		if(user.getPassword()!= null && user.getPassword() !=""){
			password = new SimpleHash("SHA-1",user.getUserName(),user.getPassword()).toString();
		}
		if(user.getPassword()== null || user.getPassword() ==""){
			model.addAttribute("msg", "请先登录！");
			return "../../../index";
		}
		if(!code.equalsIgnoreCase(validatecode)){
			model.addAttribute("msg", "验证码错误!!!");
			return "../../../index";
		}
		UsernamePasswordToken uPasswordToken = new UsernamePasswordToken(user.getUserName(),password);
		try {
			subject.login(uPasswordToken);
			return "/car";
		} catch (Exception e) {
			model.addAttribute("msg", "用户名或者密码错误！");
			return "../../../index";
		}
	}
	@RequestMapping(value = "/toSuccess")
	public String admin(){
		return "/car";
	}
	/**
	 * ajax轮训校验
	 * @return
	 */
	@RequestMapping(value = "/vailQrcode")
	@ResponseBody
	public String vailQrcode(HttpServletResponse response,HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
		String sessionId = request.getSession().getId();
		if(!QrcodeUtil.user.containsKey(sessionId)){
			return "false";
		}
		String[] prams = QrcodeUtil.user.get(sessionId).split(",");
		String userName = prams[1];
		String password = prams[2];
		password = new SimpleHash("SHA-1",userName,password).toString();
		UsernamePasswordToken uPasswordToken = new UsernamePasswordToken(userName,password);
		try {
			subject.login(uPasswordToken);
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}
	/**
	 * 生成二维码
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/loginCode")
	public void getImg(HttpServletResponse response,HttpServletRequest request){
		response.setContentType("image/jpeg");
		//不让浏览器缓存图片
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		String sessionId = request.getSession().getId();
		//因为没有app  所以模拟已经存在用户信息
		String userName = "admin";
		String password = "123";
		String conten = sessionId+","+userName+","+password;
		//加密内容
		//conten = EncryptUtil.encrypt(conten);
		String url  = "http://192.168.0.109:8080/ssms/vailLogin?token="+conten;
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = QrcodeUtil.getQrcode(url);
			ImageIO.write(bufferedImage, "jpeg", response.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 扫描二维码
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/vailLogin")
	public void vailLogin(HttpServletResponse response,HttpServletRequest request){
		try {
		String pram = request.getParameter("token");
		if("".equals(pram)|| pram == null){
			response.getWriter().println("Wrong Request!");
			return;
		}
		//解密
		//String centent =EncryptUtil.decrypt(token);
		String[] prams = pram.split(",");
		String sessionId = prams[0];
		QrcodeUtil.user.put(sessionId, pram);
		response.getWriter().println("Scanner success!");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		//生成密码
		String password = new SimpleHash("SHA-1","admin","123").toString();
		System.out.println(password);
	}
}

package com.gym.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.util.CarCodeUtil;
import com.gym.util.ImageUtil;

@Controller
public class CodeController {
	@RequestMapping(value="/code")
	public void getCode(HttpServletResponse response){
		try {
			response.setContentType("image/jpeg");
			//不让浏览器缓存图片
			response.setDateHeader("expries", -1);
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			String code = ImageUtil.getRandImg(response.getOutputStream(), 120, 38);
			SecurityUtils.getSubject().getSession().setAttribute("CODE", code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/validate")
	@ResponseBody
	public String validate(String validatecode){
		//当前对象
		Subject subject = SecurityUtils.getSubject();
		String code =(String)subject.getSession().getAttribute("CODE");
		if(code.equalsIgnoreCase(validatecode)){
			return "true";
		}else{
			return "false";
		}
	}
	@RequestMapping(value="/CarCode")
	public void getCard(HttpServletResponse response){
		response.setContentType("image/jpeg");
		//不让浏览器缓存图片
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		try {
			CarCodeUtil.getCar(response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

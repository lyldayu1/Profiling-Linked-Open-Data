package com.mvc.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.resource.Operation_LOD;



@Controller
@RequestMapping("/hello")
public class HelloMvcController {
	
	
	
	@Resource
	HttpServletRequest request;

	@Resource
	Operation_LOD handle1;
	@RequestMapping("/mvc")
	public ModelAndView helloMvc() throws IOException
	{
		handle1.operator();
		return new ModelAndView("home");
	}
//	@RequestMapping("/mvc1")
//	public String helloMvc1(String id,Model model)
//	{
//		
//		return "";
//	}
	

}

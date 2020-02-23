package com.example.filedemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticPagesController {

	@RequestMapping("/upload")
	public String uploadPage() {
		return "upload.html";
	}
	
	@RequestMapping("/fileList")
	public String fileListPage() {
		return "fileList.html";
	}
}

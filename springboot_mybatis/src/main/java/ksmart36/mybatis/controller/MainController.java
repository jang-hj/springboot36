package ksmart36.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	//request get 방식 http://localhost
	@GetMapping("/")
	public String main(Model model) {		
		model.addAttribute("title", "main화면");
		return "main";
	}

}

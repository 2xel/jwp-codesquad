package com.codesquad.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codesquad.dao.Qna;

@Controller
public class QnaController {
	ArrayList<Qna> qnas = new ArrayList<>();
	
	@PostMapping("/qna")
	public String qna(Qna qna) {
		System.out.println(qna);
		qnas.add(qna);
		System.out.println(qnas.size());
		return "redirect:/index.html";
	}
	
	@GetMapping("/index")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("qnas", qnas);
		mav.setViewName("index");
		
		return mav;
	}
}

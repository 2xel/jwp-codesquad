package com.codesquad.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codesquad.dao.Qna;

@Controller
public class QnaController {
	ArrayList<Qna> qnas = new ArrayList<>();
	
	//질문 폼 이동
	@GetMapping("/qnas")
	public String form() {
		return "/qna/form";
	}
	
	//질문작성 한 후 전송
	@PostMapping("/qnas")
	public String create(Qna qna) {
		qnas.add(qna);
		System.out.println(qnas.size());
		return "redirect:/";
	}
	
	//질문목록보기, 인덱스
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("qnas", qnas);
		mav.setViewName("/index");
		return mav;
	}
	
	//질문글 상세보기
	@GetMapping("/qnas/{index}")
	public ModelAndView show(@PathVariable int index) {
		Qna qna = qnas.get(index);
		ModelAndView mav = new ModelAndView();
		mav.addObject("write", qna.getWrite());
		mav.addObject("contents",qna.getContents());
		mav.addObject("title",qna.getTitle());
		mav.setViewName("qna/show");
		return mav;
	}
	
}

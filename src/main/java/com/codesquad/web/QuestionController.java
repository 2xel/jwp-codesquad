package com.codesquad.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codesquad.dao.Qna;
import com.codesquad.dao.QnaRepository;

@Controller
public class QnaController {
	@Autowired
	QnaRepository qnaRepository;
	
	//질문 폼 이동
	@GetMapping("/qnas")
	public String form() {
		return "/qna/form";
	}
	
	//질문작성 한 후 전송
	@PostMapping("/qnas")
	public String create(Qna qna) {
		qnaRepository.save(qna);
		return "redirect:/";
	}
	
	//질문목록보기, 인덱스
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("qnas", qnaRepository.findAll());
		mav.setViewName("/index");
		return mav;
	}
	
	//질문글 상세보기
	@GetMapping("/qnas/{id}")
	public ModelAndView show(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("qna", qnaRepository.findOne(id));
		mav.setViewName("qna/show");
		return mav;
	}
	
	//질문글 수정하기 페이지
	@GetMapping()
	public ModelAndView updateForm(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("qna", qnaRepository.findOne(id));
		mav.setViewName("qna/updateForm");
		return mav;
	}
}

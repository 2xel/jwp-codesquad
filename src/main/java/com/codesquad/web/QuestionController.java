package com.codesquad.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codesquad.dao.Question;
import com.codesquad.dao.QuestionRepository;

@Controller
public class QuestionController {
	@Autowired
	QuestionRepository questionRepository;
	
	//질문 폼 이동
	@GetMapping("/questions")
	public String form() {
		return "/qna/form";
	}
	
	//질문작성완료 전송
	@PostMapping("/questions")
	public String create(Question question) {
		questionRepository.save(question);
		return "redirect:/";
	}
	
	//질문목록보기, 인덱스
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("questions", questionRepository.findAll());
		mav.setViewName("/index");
		return mav;
	}
	
	//질문 상세보기
	@GetMapping("/questions/{id}")
	public ModelAndView show(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("questions", questionRepository.findOne(id));
		mav.setViewName("qna/show");
		return mav;
	}
	
	//질문 수정하기 폼 이동
	@GetMapping("/questions/{id}/form")
	public ModelAndView updateForm(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("questions", questionRepository.findOne(id));
		mav.setViewName("qna/updateForm");
		return mav;
	}
	
	@PostMapping("/questions/{id}/form")
	public String update(@PathVariable Long id, Question question) {
		questionRepository.save(question);
		return "redirect:/";
	}
}

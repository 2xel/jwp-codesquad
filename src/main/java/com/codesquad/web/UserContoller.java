package com.codesquad.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codesquad.dao.User;
import com.codesquad.dao.UserRepository;
@Controller
public class UserContoller {
	@Autowired
	UserRepository userRepository;
	
	//로그인 페이지로 이동
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	//회원가입 폼 화면이동
	@GetMapping("/form")
	public String form() {
		return "user/form";
	}
	
	//회원가입 내용 리스트에 저장
	@PostMapping("/users")
	public String create(User user) {
		userRepository.save(user);
		return "redirect:/users";
	}
	
	//유저 목록보기로 이동
	@GetMapping("/users")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", userRepository.findAll());
		mav.setViewName("user/list");
		return mav;
	}
	
	//유저상세보기
	@GetMapping("/users/{id}")
	public ModelAndView show(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", userRepository.findOne(id));
		mav.setViewName("user/profile");
		return mav;
	}
}
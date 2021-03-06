package com.codesquad.web;

import javax.servlet.http.HttpSession;

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

	// 로그인 페이지로 이동
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}

	// 회원가입 폼 화면이동
	@GetMapping("/form")
	public String form() {
		return "user/form";
	}

	// 회원가입 내용 리스트에 저장
	@PostMapping("/users")
	public String create(User user) {
		userRepository.save(user);
		return "redirect:/users";
	}

	// 유저 목록보기로 이동
	@GetMapping("/users")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", userRepository.findAll());
		mav.setViewName("user/list");
		return mav;
	}

	// 유저상세보기
	@GetMapping("/users/{id}")
	public ModelAndView show(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", userRepository.findOne(id));
		mav.setViewName("user/profile");
		return mav;
	}

	// 회원정보 수정페이지로 이동
	@GetMapping("/user/{id}/form")
	public ModelAndView updateform(@PathVariable Long id, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		Object tempUser = session.getAttribute("loginUser");
		if (tempUser == null) throw new Exception();

		User loginUser = (User) tempUser;
		if (!loginUser.matchId(id))	throw new Exception();

		mav.addObject("user", userRepository.findOne(id));
		mav.setViewName("user/updateForm");
		return mav;
	}

	// 회원정보 수정완료
	@PostMapping("/user/{id}")
	public String update(@PathVariable Long id, User user, HttpSession session) throws Exception {
		Object tempUser = session.getAttribute("loginUser");
		if (tempUser == null) throw new Exception();
		User loginUser = (User) tempUser;
		if (!loginUser.matchId(id)) throw new Exception();

		User dbUser = userRepository.findOne(id);
		if (dbUser.update(user)) {
			userRepository.save(dbUser);
		}
		return "redirect:/users";
	}

	// 로그인
	@PostMapping("/user/login")
	public String login(String userId, String password, HttpSession session) {
		System.out.println(userId + "//" + password);
		User dbUser = userRepository.findByUserId(userId);
		if (dbUser == null) {
			return "user/login_failed";
		}
		if (!dbUser.matchPassword(password)) {
			return "user/login_failed";
		}

		session.setAttribute("loginUser", dbUser);

		return "redirect:/";
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
}
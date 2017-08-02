package com.codesquad.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codesquad.dao.User;

@Controller
public class UserContoller {
	ArrayList<User> users = new ArrayList<>();
	
	@PostMapping("/users")
	public ModelAndView create(String userId, String password, String name, String email) {
		System.out.println("아이디 :" + userId + " / 비밀번호 : " + password + " / 이름:" + name + " / 이메일 : " + email);

		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		user.setName(name);
		user.setEmail(email);
		
		users.add(user);
		System.out.println("size : " + users.size());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", users);
		mav.setViewName("user/list");
		return mav;
	}
}

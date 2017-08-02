package com.codesquad.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codesquad.dao.User;

@Controller
public class UserContoller {
	ArrayList<User> users = new ArrayList<>();
	
	@PostMapping("/users")
	public String create(User user) {
		users.add(user);
		System.out.println("size : " + users.size());
		
		return "redirect:/users";
	}

	@GetMapping("/users")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", users);
		mav.setViewName("user/list");
		
		return mav;
	}
	
	@GetMapping("/users/{index}")
	public ModelAndView show(@PathVariable int index) {
		User user = users.get(index);
		ModelAndView mav = new ModelAndView();
		mav.addObject("name", user.getName());
		mav.addObject("email",user.getEmail());
		mav.setViewName("user/profile");
		
		return mav;
	}
}
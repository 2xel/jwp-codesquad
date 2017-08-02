package com.codesquad.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codesquad.dao.Baseball;

@Controller
public class BaseballController {

	ArrayList<Integer> computerBalls;
	@GetMapping("/baseball")
	public ModelAndView input(String inputValue) {
		System.out.println(inputValue);
		int strike = 0;
		int ball = 0;
		ArrayList<Integer> userBalls = Baseball.inputUserBalls(inputValue);
		for (int i = 0; i < userBalls.size(); i++) {
			int result = Baseball.calculateBall(computerBalls, userBalls.get(i), i);
			if (result == 2) {
				strike++;
			} else if (result == 1) {
				ball++;
			}
		}
		System.out.println(String.format("결과 : %d strike, %d ball", strike, ball));

		if (strike == 3) {
			System.out.println("게임 종료");
		}

		ModelAndView mav = new ModelAndView();
		mav.addObject("strike", strike);
		mav.addObject("ball", ball);
		mav.setViewName("baseball/result");
		return mav;
	}
}
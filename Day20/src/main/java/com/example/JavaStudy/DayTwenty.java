package com.example.JavaStudy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

@Controller
public class DayTwenty {
@RequestMapping(value="/", method=RequestMethod.GET)
public ModelAndView indexGet(ModelAndView mv){
	mv.addObject("text","階乗の計算をします。");
	mv.setViewName("factorial");
	return mv;
}
@RequestMapping(value="/", method=RequestMethod.POST)
public ModelAndView indexPost(ModelAndView mv,
		@RequestParam("number")int number) {
	int answer=0;
	int n = number;
	for(int i=number; i>0; i--) {
		answer = answer + n;
		n--;
	} 
	mv.addObject("text",(number) + "の階乗は" + (answer) + "です！");
	mv.setViewName("factorial");
	return mv;
}
}

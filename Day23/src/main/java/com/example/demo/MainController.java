package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
@Autowired
UserDataRepository repository;

@RequestMapping(value="/",method=RequestMethod.GET)
public ModelAndView indexGet(ModelAndView mv) {
	List<UserData> customers = repository.findAll();
	mv.addObject("customers",customers);
	mv.setViewName("index");
	return mv;
}

@RequestMapping(value="/", method=RequestMethod.POST)
public ModelAndView indexPost(@ModelAttribute("formModel")
	UserData userData, ModelAndView mv) {
	repository.saveAndFlush(userData);
	return new ModelAndView("redirect:/");
}
}

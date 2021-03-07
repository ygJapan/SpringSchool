package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

@RequestMapping(value="/delete/", method=RequestMethod.POST)
public ModelAndView delete(@RequestParam("id")long id, ModelAndView mv) {
	repository.deleteById(id);
	return new ModelAndView("redirect:/");
}

@RequestMapping(value="/mypage/{id}",method=RequestMethod.GET)
public ModelAndView mypageGet(@ModelAttribute UserData userData, ModelAndView mv,@PathVariable long id) {
	Optional<UserData> user = repository.findById(id);
	mv.addObject("user", user.get());
	mv.setViewName("mypage");
	return mv;
}

@RequestMapping(value="/mypage/",method=RequestMethod.POST)
public ModelAndView mypagePost(@ModelAttribute UserData userData, ModelAndView mv) {
	repository.saveAndFlush(userData);
	return new ModelAndView("redirect:/");
}
}

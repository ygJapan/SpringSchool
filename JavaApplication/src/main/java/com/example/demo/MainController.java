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

/*
====================================
		springboot_Day23,24		
====================================
*/
@RequestMapping(value = "/", method = RequestMethod.GET)
public ModelAndView indexGet(ModelAndView mv) {
	List<UserData> customers = repository.findAll();
	mv.addObject("customers",customers);
	mv.setViewName("crud");
	return mv;
}

@RequestMapping(value = "/", method = RequestMethod.POST)
public ModelAndView indexPost(@ModelAttribute("formModel")
	UserData userData,ModelAndView mv) {
	repository.saveAndFlush(userData);
	return new ModelAndView("redirect:/");
}


@RequestMapping(value = "/mypage/{id}", method = RequestMethod.GET)
public ModelAndView mypageGet(@ModelAttribute
		UserData userData, ModelAndView mv, @PathVariable long id){
	Optional<UserData> user = repository.findById(id);
	mv.addObject("user", user.get());
	mv.setViewName("mypage");
	return mv;
}

@RequestMapping(value = "/mypage/", method = RequestMethod.POST)
public ModelAndView mypagePost(@ModelAttribute
		UserData userData, ModelAndView mv) {
	repository.saveAndFlush(userData);
	return new ModelAndView("redirect:/");
}

@RequestMapping(value = "/delete/", method = RequestMethod.POST)
public ModelAndView delete(@RequestParam("id")
		long id, ModelAndView mv) {
	repository.deleteById(id);
	return new ModelAndView("redirect:/");
}

/*
================================
		iPhone査定サイト			
================================
*/
@RequestMapping(value="/assessment/", method = RequestMethod.GET)
public ModelAndView assessmentGet(ModelAndView mv) {
	mv.addObject("price", "査定中");
	mv.setViewName("assessment");
	return mv;
}
@RequestMapping(value="/assessment/", method = RequestMethod.POST)
public ModelAndView assessmentPost(@RequestParam("number")String number,
								@RequestParam("maker")String maker,
								@RequestParam("otherGlass")String otherGlass,
								@RequestParam("otherButton")String otherButton,
								@RequestParam("otherBox")String otherBox,
								ModelAndView mv) {
/*
10,docomo->¥19,000 au->¥19,500 softbank->¥20,000 simFree->¥20,500
11,docomo->¥24,000 au->¥24,500 softbank->¥25,000 simFree->¥25,500
12,docomo->¥29,000 au->¥29,500 softbank->¥30,000 simFree->¥30,500
	
*/
	int[] priceList10 = {19000,19500,20000,20500};
	int[] priceList11 = {24000,24500,25000,25500};
	int[] priceList12 = {29000,29500,30000,30500};
	int price = 0;
	
	switch(number) {
	case "iphone10":
		switch(maker) {
		case "docomo":
			price=priceList10[0];
			break;
		case "au":
			price=priceList10[1];
			break;
		case "softbank":
			price=priceList10[2];
			break;
		case "simFree":
			price=priceList10[3];
			break;
		}
		break;
	case "iphone11":
		switch(maker) {
		case "docomo":
			price=priceList11[0];
			break;
		case "au":
			price=priceList11[1];
			break;
		case "softbank":
			price=priceList11[2];
			break;
		case "simFree":
			price=priceList11[3];
			break;
		}
		break;
	case "iphone12":
		switch(maker) {
		case "docomo":
			price=priceList12[0];
			break;
		case "au":
			price=priceList12[1];
			break;
		case "softbank":
			price=priceList12[2];
			break;
		case "simFree":
			price=priceList12[3];
			break;
		}
		break;
	}
	if(otherGlass == "") {
	}else {		
		price -= 5000;
	}
	if(otherButton == "") {
	}else {
		price -= 4000;
	}
	if(otherBox == "") {
	}else {
		price += 3000;
	}
	
	String message = number + "," + maker + "," + otherGlass + otherButton + otherBox;
	String pricemsg = "査定結果は¥" + price + "-です。";
	mv.addObject("message",message);
	mv.addObject("price",pricemsg);
	return mv;
}
}

package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
@RequestMapping("/{number}")
public ModelAndView index(@PathVariable int number, ModelAndView mv) {
int count=0;
String line="";

for(int i=number-1; i>1; i--) {
	if(number%i==0) {
		count++;
	}else {
	}
}
if(count==0) {
	line= number + "は素数です！";	
}else {
	line= number + "は素数ではありません...";				
}

mv.addObject("answer",line);
mv.setViewName("index");
return mv;
}
}
	
	
	
	
//@RequestMapping("/{name}")
//public ModelAndView index(@PathVariable String name, ModelAndView mv) {
//mv.addObject("name",name);
//mv.setViewName("index");
//return mv;
//}
////@RequestMapping(value="/",method=RequestMethod.POST)
////public ModelAndView indexPost(ModelAndView mv) {
////mv.addObject("suzuki",true);
////mv.setViewName("index");
////return mv;
////}
//}

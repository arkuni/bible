package com.arkuni.bible.search.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.arkuni.bible.search.service.BibleSearchService;

@Controller
public class BibleSearchController {
	@Autowired
	private BibleSearchService service;
	
	@RequestMapping("/data.do")
	 public ModelAndView hello(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("data");
		mv.addObject("data", service.getData());
		return mv;
	}
	@RequestMapping("/today.do")
	 public ModelAndView todayweek(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("data");
		mv.addObject("data", service.today());
		return mv;
	}
}

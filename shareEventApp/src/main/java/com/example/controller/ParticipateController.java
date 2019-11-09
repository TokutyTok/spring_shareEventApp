package com.example.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.CalenderEvent;
import com.example.domain.ParticipateEvent;
import com.example.mapper.CalenderEventMapper;
import com.example.mapper.ParticipateEventMapper;

@Controller
public class ParticipateController {

	@Autowired
	CalenderEventMapper calenderEventMapper;

	@Autowired
	ParticipateEventMapper participateEventMapper;

	@Transactional
	@PostMapping("/calender/event_details_join")
	public ModelAndView join(@ModelAttribute("username")String username,
			@ModelAttribute("id")int id, Principal principal,ModelAndView mav) {
		mav.setViewName("calender/event_details");

		String join = "参加";

	    participateEventMapper.join(id,username,join);

	    ParticipateEvent findResult = participateEventMapper.findJoin(id,username,join);

	    mav.addObject("join",findResult);

		Optional<CalenderEvent> eventDetails = calenderEventMapper.findId(id);

		if (principal != null) {

			mav.addObject("login","login");

			final String loginUser = principal.getName();
			final String user = eventDetails.get().getName();

			mav.addObject("loginUser",loginUser);

			if (loginUser.equals(user) == true) {
				mav.addObject("same", "same");
			}
		}

		List<ParticipateEvent> participateList = participateEventMapper.participateList(id);

	    mav.addObject("participateList",participateList);

		mav.addObject("eventDetails", eventDetails.get());

		mav.addObject("map","http://maps.google.co.jp/maps?&output=embed&q="+eventDetails.get().getPlace());

		return mav;
	}

	@Transactional
	@PostMapping("/calender/event_details_unjoin")
	public ModelAndView unjoin(@ModelAttribute(name="username")String username,
			@ModelAttribute("id")int id,Principal principal,ModelAndView mav) {

		mav.setViewName("calender/event_details");

		participateEventMapper.unjoin(username);

		Optional<CalenderEvent> eventDetails = calenderEventMapper.findId(id);

		if (principal != null) {

			mav.addObject("login","login");

			final String loginUser = principal.getName();
			final String user = eventDetails.get().getName();

			mav.addObject("loginUser",loginUser);

			if (loginUser.equals(user) == true) {
				mav.addObject("same", "same");
			}
		}

		List<ParticipateEvent> participateList = participateEventMapper.participateList(id);

	    mav.addObject("participateList",participateList);

		mav.addObject("eventDetails", eventDetails.get());

		mav.addObject("map","http://maps.google.co.jp/maps?&output=embed&q="+eventDetails.get().getPlace());

		return mav;
	}

}
package com.lefanfs.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BackendLogoutController extends BackendBaseController {

	@RequestMapping(value = "/logout")
	public String login(HttpServletRequest req) {
		req.getSession().invalidate();
		return "redirect:/login";
	}
}

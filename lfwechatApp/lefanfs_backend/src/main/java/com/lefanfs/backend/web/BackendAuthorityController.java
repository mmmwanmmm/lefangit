package com.lefanfs.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BackendAuthorityController extends BackendBaseController {

	@RequestMapping(value = "/noAuthority")
	public String noAuthority(HttpServletRequest req) {

		return "/common/noAuthority";
	}

}

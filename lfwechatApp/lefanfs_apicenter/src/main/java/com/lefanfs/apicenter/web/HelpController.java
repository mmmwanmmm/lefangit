package com.lefanfs.apicenter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api/page")
public class HelpController {

	/**
	 * 关于我们
	 * 
	 * @return
	 * @author Daniel
	 */
	@RequestMapping(value = "/aboutUs")
	public String aboutUs() {
		return "/help/aboutUs";
	}
	/**
	 * 使用帮助
	 *
	 * @return
	 * @author Jason
	 */
	@RequestMapping(value = "/help")
	public String help() {
		return "/help/help";
	}

	/**
	 * 平台协议
	 *
	 * @return
	 * @author Jason
	 */
	@RequestMapping(value = "/platformRule")
	public String platformRule() {
		return "/help/platformRule";
	}


	/**
	 * 积分规则
	 *
	 * @return
	 * @author Jason
	 */
	@RequestMapping(value = "/integralRule")
	public String integralRule() {
		return "/help/integralRule";
	}
	/**
	 * 收费标准
	 * 
	 * @return
	 * @author Daniel
	 */
	@RequestMapping(value = "/pricing")
	public String pricing() {
		return "/help/pricing";
	}

	/**
	 * 司机协议
	 * 
	 * @return
	 * @author Daniel
	 */
	@RequestMapping(value = "/driverTerm")
	public String driverTerm() {
		return "/help/driverTerm";
	}

	/**
	 * 司机准则
	 * 
	 * @return
	 * @author Daniel
	 */
	@RequestMapping(value = "/driverRule")
	public String driverRule() {
		return "/help/driverRule";
	}

	/**
	 * 货主协议
	 * 
	 * @return
	 * @author Daniel
	 */
	@RequestMapping(value = "/customerTerm")
	public String customerTerm() {
		return "/help/customerTerm";
	}

	/**
	 * 货主准则
	 * 
	 * @return
	 * @author Daniel
	 */
	@RequestMapping(value = "/customerRule")
	public String customerRule() {
		return "/help/customerRule";
	}

}

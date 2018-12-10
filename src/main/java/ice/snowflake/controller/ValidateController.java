package ice.snowflake.controller;

import javax.validation.Valid;

import ice.snowflake.model.Summoner;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ice.snowflake.model.Apple;
import ice.snowflake.model.People;
import ice.snowflake.validation.Add;
import ice.snowflake.validation.Delete;
import ice.snowflake.validation.Update;

@Controller	
public class ValidateController {
	@ResponseBody
	@RequestMapping("add")
	public String add(@Valid People people) {
		return "添加成功";
	}
	/**
	 * 基础校验，分组校验
	 * */
	@ResponseBody
	@RequestMapping("apple/add")
	public String add(@RequestBody @Validated(Add.class) Apple apple) {
		return "apple/add";
	}
	/**
	 * 基础校验，分组校验
	 * */
	@ResponseBody
	@RequestMapping("apple/update")
	public String Update(@RequestBody @Validated({Update.class}) Apple apple) {
		System.out.println("对象信息："+apple);
		return "apple/update";
	}
	
	//BindingResult 这个可以获得错误结果,可以自己封装一下
	@ResponseBody
	@RequestMapping("apple/delete")
	public String delete(@RequestBody @Validated(Delete.class) Apple apple,BindingResult bindingResult) {
		System.out.println("对象信息："+apple);
		execute(bindingResult);
		return "apple/delete";
	}
	/**
	 * 嵌套校验
	 * */
	@ResponseBody
	@RequestMapping("summoner/add")
	public String summnerAdd(@RequestBody @Valid Summoner summoner) {
		return "summoner/add";
	}


	public void execute(BindingResult bindingResult) {
        //此处有大量代码重复，感觉可以做成统一的，类似放在aop中,稍后在考虑了
		if (bindingResult.hasErrors()) {
        	throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        	//System.out.println(bindingResult.getFieldError().getDefaultMessage());
        }
	};
}

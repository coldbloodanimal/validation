package ice.snowflake.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ice.snowflake.validation.Add;
import ice.snowflake.validation.Delete;
import ice.snowflake.validation.Update;
import lombok.Data;

@Data
public class Apple {
	/**
	 * Update和Delete只是两个标记接口，没有任何内容，下面的意思是修改和删除时候都要校验id，但是新增的时候就不需要
	 * 具体可看ValidateControllerTest.group()方法
	 * notblank字符串不能为空
	 * */
	@NotBlank(groups={Update.class,Delete.class},message="id不能为空")
	private String id;
	@NotBlank(groups=Add.class,message="名称不能为空")
	private String name;
	/**
	 * 对象不能为空
	 * */
	@NotNull(groups=Add.class,message="电话号码不能为空")
	private String phonenumber;
	/**
	 * 数字最大长值不能超过200，
	 * 最小值不能小于0
	 * */
	@Max(value=200,groups=Add.class)
	@Min(value=0,groups=Add.class)
	private int age;
	@Max(3)
	@Min(0)
	private int sex;
	@Size(max=20)
	private String description;
}

package ice.snowflake.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import ice.snowflake.validation.Delete;
import ice.snowflake.validation.Update;
import lombok.Data;
@Data
public class Summoner {
	private String username;
	private int age;
	private String sex;
	/**
	 * valid支持嵌套校验，就是在传入Summoner对象的时候，也会校验Pet中的合法性
	 * 具体可看ValidateControllerTest.addsummoner() .addsummoner1() addsummoner2()方法
	 * */
    @Valid
	@NotNull(message = "你需要一个宠物")
	private Pet pet;
}

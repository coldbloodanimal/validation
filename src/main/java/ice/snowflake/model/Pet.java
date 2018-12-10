package ice.snowflake.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Pet {
	@NotBlank(message = "宠物名称不能为空")
	private String name;
	private String sex;
}

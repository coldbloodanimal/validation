package ice.snowflake.model;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class People {
	@Size(max=3,min=2)
	private String username;
	@Max(1000)
	@Min(0)
	private int age;
	@NotBlank
	private String sex;
}

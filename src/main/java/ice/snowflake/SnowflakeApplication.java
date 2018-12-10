package ice.snowflake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 1.测试token在不同位置时候的获取方式
 * 2.后台参数校验
 * 
 * */
@SpringBootApplication
public class SnowflakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnowflakeApplication.class, args);
	}
}

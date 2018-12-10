package ice.snowflake.controller;

import ice.snowflake.model.Pet;
import ice.snowflake.model.Summoner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ice.snowflake.model.Apple;

/**
 * 官方文档 official url:https://spring.io/guides/gs/testing-web/ restTemplate
 * url:https://www.baeldung.com/rest-template
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ValidateControllerTest {
	private static final String URL_HOST = "http://localhost";
	private static final String URL_ADD = "/apple/add";
	private static final String URL_UPDATE = "/apple/update";
	private static final String URL_DELETE = "/apple/delete";
	private static final String URL_ADD_SUMMONER = "/summoner/add";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	// 空字符串校验
	public void add1() throws Exception {
		Apple apple = getApple();
		apple.setName("");
		excute(apple, URL_ADD);
	}

	@Test
	// null值校验
	public void add2() throws Exception {
		Apple apple = getApple();
		apple.setPhonenumber(null);
		excute(apple, URL_ADD);
	}

	@Test
	// 好的，正常下看看
	public void add3() throws Exception {
		Apple apple = getApple();
		excute(apple, URL_ADD);
	}

	@Test
	/**
	 * 
	 * 分组校验
	 * 分组校验
	 * 分组校验
	 * 		插入id是不做空值校验，既可以为空
	 * 		修改id是做空值校验，既不可以为空
	 * 		但它们都在同一个字段上
	 * 
	 * */
	public void group() throws Exception {
		//这个不报错
		Apple apple = getApple();
		apple.setId(null);
		excute(apple, URL_ADD);
		//这个报错
		Apple apple2 = getApple();
		apple2.setId(null);
		excute(apple, URL_UPDATE);
	}

	@Test
	//嵌套校验
	public void addsummoner() throws Exception {
		Summoner summoner=new Summoner();
		excute(summoner, URL_ADD_SUMMONER);
	}

	@Test
	//嵌套校验
	public void addsummoner2() throws Exception {
		Summoner summoner=new Summoner();
		Pet pet=new Pet();
		pet.setName("旺财");
		summoner.setPet(pet);
		excute(summoner, URL_ADD_SUMMONER);
	}
	@Test
	//嵌套校验
	public void addsummoner3() throws Exception {
		Summoner summoner=new Summoner();
		Pet pet=new Pet();
		summoner.setPet(pet);
		excute(summoner, URL_ADD_SUMMONER);
	}

	@Test
	//个性化异常
	public void delete() throws Exception {
		Apple apple = getApple();
		apple.setId(null);
		excute(apple, URL_DELETE);
	}

	public <T> String excute(T t, String url) {
		HttpEntity<T> request = new HttpEntity<>(t);
		String fullurl = URL_HOST + ":" + port + url;

		ResponseEntity<String> r = restTemplate.postForEntity(fullurl, request,String.class );
		String result=r.getBody();

		if(!r.getStatusCode().equals(HttpStatus.OK)){
			throw new RuntimeException(r.getBody());
		}
		System.out.println(result);
		return result;
	}

	public String excute(Apple apple, String url) {
		HttpEntity<Apple> request = new HttpEntity<>(apple);
		java.lang.Class<String> responseType;
		String fullurl = URL_HOST + ":" + port + url;
		//String result = restTemplate.postForObject(fullurl, request, String.class);
		ResponseEntity<String> r = restTemplate.postForEntity(fullurl, request,String.class );
		String result=r.getBody();

		if(!r.getStatusCode().equals(HttpStatus.OK)){
			throw new RuntimeException(r.getBody());
		}
		System.out.println(result);
		return result;
	}


/*	public String excute(Apple apple, String url) {
		HttpEntity<Apple> request = new HttpEntity<>(apple);
		java.lang.Class<String> responseType;
		String fullurl = URL_HOST + ":" + port + url;
		//String result = restTemplate.postForObject(fullurl, request, String.class);
		ResponseEntity<String> r = restTemplate.postForEntity(fullurl, request,String.class );
		if(!r.getStatusCode().equals(HttpStatus.OK)){
			throw new RuntimeException(r.getBody());
		}
		System.out.println(result);
		return result;
	}*/
	/**
	 * apple属性，为了方便自己看
	 * 
	 * @NotBlank(groups={Update.class,Delete.class},message="id不能为空") private String
	 *                                                                id;
	 * @NotBlank(groups=Add.class,message="名称不能为空") private String name;
	 * @NotNull(groups=Add.class,message="电话号码不能为空") private String phonenumber;
	 * @Max(value=200,groups=Add.class) @Min(value=0,groups=Add.class) private int
	 *                                  age; @Max(3) @Min(0) private int sex;
	 * @Size(max=20) private String description;
	 * 
	 */
	public static Apple getApple() {
		Apple apple = new Apple();
		apple.setId("1");
		apple.setName("apple");
		apple.setPhonenumber("110");
		apple.setAge(60);
		apple.setSex(2);
		apple.setDescription("i am an apple");
		apple.setAge(6);
		return apple;
	}

}

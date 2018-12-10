package ice.snowflake;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 全局过滤错误过滤，参考文章
 * https://blog.csdn.net/kinginblue/article/details/70186586
 * */
/**
 * 从名字看出这玩意儿是个controller层次的通知，应该是aop的一种使用
 * */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 对于参数错误统一处理，负责就需要单个处理，如: ValidateController.delete
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    String handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        throw new RuntimeException(e.getBindingResult().getFieldError().getDefaultMessage());
    }
}